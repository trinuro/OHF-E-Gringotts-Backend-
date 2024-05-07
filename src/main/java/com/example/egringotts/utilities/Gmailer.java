package com.example.egringotts.utilities;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.Value;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import org.apache.commons.codec.binary.Base64;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Properties;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.gmail.GmailScopes;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;

/**
 * Class that contains all methods to communicate with Google API
 * @author Khiew
 */
@Component
public class Gmailer {

    private static final String APPLICATION_NAME = "test-project";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    /**
     * Method to send gmail
     * @param destinationEmail  The email address that receives this email
     * @param subject   The subject/ title of email
     * @param bodyText  The contents of email
     * @throws IOException
     * @throws GeneralSecurityException
     * @throws MessagingException
     */
    public static void sendEmail(String destinationEmail, String subject, String bodyText) throws IOException, GeneralSecurityException, MessagingException {
        // Function to send email

        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        System.out.println("1");
        InputStream inputStream = Gmailer.class.getClassLoader()
                .getResourceAsStream(Secrets.getJSON());
        // use \\ instead because windows
        System.out.println(inputStream);
        System.out.println("2");
        GoogleCredentials googleCredentials = GoogleCredentials.fromStream(inputStream);
        System.out.println("3");
        HttpRequestInitializer credentials = new HttpCredentialsAdapter(googleCredentials);

        Gmail service = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, credentials)
                .setApplicationName(APPLICATION_NAME)
                .build();

        final Message message = createEmail(destinationEmail, Secrets.getSenderEmail(), subject, bodyText);
        service.users().messages().send("me", message).execute();
    }

    /**
     * Method to create email
     * @param toEmailAddress    Email address to send email to
     * @param fromEmailAddress  Email address that will receive email
     * @param subject   Subject/title of email
     * @param bodyText  Content of email
     * @return Message object
     * @throws MessagingException
     * @throws IOException
     */
    public static Message createEmail(String toEmailAddress,
                                      String fromEmailAddress,
                                      String subject,
                                      String bodyText)
            throws MessagingException, IOException {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        MimeMessage email = new MimeMessage(session);

        email.setFrom(new InternetAddress(fromEmailAddress));
        email.addRecipient(javax.mail.Message.RecipientType.TO,
                new InternetAddress(toEmailAddress));
        email.setSubject(subject);
        email.setText(bodyText);

        MimeBodyPart mimeBodyPart = new MimeBodyPart();

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        email.writeTo(buffer);
        byte[] rawMessageBytes = buffer.toByteArray();
        String encodedEmail = Base64.encodeBase64URLSafeString(rawMessageBytes);
        final Message message = new Message();
        message.setRaw(encodedEmail);

        return message;
    }

    /**
     *  Method to get refresh token from user (Run once)
     */
    public static void getRefreshToken(){
        try{
            final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            Credential credentials = getCredentials(HTTP_TRANSPORT);

            System.out.println(credentials.getRefreshToken());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method to prompt user to give permission to app to send email on behalf of user
     * @param transport
     * @return
     * @throws IOException
     */
    private static Credential getCredentials(final NetHttpTransport transport) throws IOException {
        AuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(transport,
                GsonFactory.getDefaultInstance(),
                Secrets.getClientID(), // Client id
                Secrets.getClientSecret(), // Client secrets
                Arrays.asList(GmailScopes.GMAIL_SEND))
                .setAccessType("offline")
                .build();

        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();

        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }
}