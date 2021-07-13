package com.api.challenge.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.challenge.sendgrid.SendGridConfig;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

@Service
public class EmailService {

	@Autowired
	SendGrid sendGrid;
	@Autowired
	SendGridConfig sendGridConfig;
	public Request sendEmail(String email) throws IOException {
		 Email from = new Email("enzojuliann1@gmail.com");
		    String subject = "Registro";
		    Email to = new Email(email);
		    Content content = new Content("text/plain", "su registro se realizo con éxito");
		    Mail mail = new Mail(from, subject, to, content);
		    SendGrid sg = new SendGrid(System.getenv(sendGridConfig.getAppKey()));
		    Request request = new Request();
		    try {
		      request.setMethod(Method.POST);
		      request.setEndpoint("auth/register");
		      request.setBody(mail.build());
		      Response response = sg.api(request);
		      System.out.println(response.getStatusCode());
		      System.out.println(response.getBody());
		      System.out.println(response.getHeaders());
		      return request;
		    } catch (IOException ex) {
		      throw ex;
		    }
		    
		  }
}
