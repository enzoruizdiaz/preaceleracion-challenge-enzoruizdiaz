package com.api.challenge.sendgrid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sendgrid.SendGrid;




@Configuration

public class SendGridConfig {
	
	@Value("${app.email.key}")
	private String appKey;
	@Bean
	public SendGrid getSendGrid() {
		return new SendGrid(appKey);
	}
	public String getAppKey() {
		return appKey;
	}
	
}
