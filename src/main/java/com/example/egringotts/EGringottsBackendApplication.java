package com.example.egringotts;

import com.example.egringotts.user.SilverSnitch;
import com.example.egringotts.utilities.Gmailer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EGringottsBackendApplication {

	public static void main(String[] args) {
		// Send recovery email to user
		SpringApplication.run(EGringottsBackendApplication.class, args);
//		try{
//			String receiver = "<email>";
//			String subject = "Gringgots love you";
//			String message = new SilverSnitch().toString();
//			Gmailer.sendEmail( receiver, subject, message);
//
//		}catch(Exception e) {
//			System.out.println(e);
//		}
//		Gmailer.getRefreshToken();
	}

}
