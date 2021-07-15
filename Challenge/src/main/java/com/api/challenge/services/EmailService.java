package com.api.challenge.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

@Service
public class EmailService {

	@Value("${api.key}")
	private String appKey;
	

	public void sendEmail(String email) throws IOException {
		 Email from = new Email("mimail@example.com");//aca va el email validado en send grid x el cual se quiere enviar el mail
		    String subject = "Registro";
		    Email to = new Email(email);//recibe el mail generado cuando se registra un nuevo usuario
		    Content content = new Content("text/plain", "su registro se realizo con éxito");
		    Mail mail = new Mail(from, subject, to, content);
		    SendGrid sg = new SendGrid(appKey);
		    Request request = new Request();
		    try {
		      request.setMethod(Method.POST);
		      request.setEndpoint("mail/send");
		      request.setBody(mail.build());
		      Response response = sg.api(request);
		      System.out.println(response.getStatusCode());
		      System.out.println(response.getBody());
		      System.out.println(response.getHeaders());
		      
		    } catch (IOException ex) {
		      throw ex;
		    }
		    
		  }
}
