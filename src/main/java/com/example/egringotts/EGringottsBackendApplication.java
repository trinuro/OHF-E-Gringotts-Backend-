package com.example.egringotts;

import com.example.egringotts.utilities.Gmailer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EGringottsBackendApplication {

	public static void main(String[] args) {
	// Send recovery email to user
		try{
			String receiver = "trinurofast@gmail.com";
			String subject = "Harimau Account Recovery";
			String message = "Your recovery password is "+1234;
			Gmailer.sendEmail( receiver, subject, message);

		}catch(Exception e){
			System.out.println(e);
		}
//		SpringApplication.run(EGringottsBackendApplication.class, args);
	}

}
