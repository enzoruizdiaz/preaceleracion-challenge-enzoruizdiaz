package com.api.challenge;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.modelmapper.AbstractConverter;
import org.modelmapper.AbstractProvider;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.api.challenge.repository.UserRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class ChallengeApplication {

	@Bean
	public ModelMapper modelMapper() {
			
		  ModelMapper modelmapper = new ModelMapper();
		   Provider<LocalDate> localDateProvider = new AbstractProvider<LocalDate>() {
	            @Override
	            public LocalDate get() {
	                return LocalDate.now();
	            }
	        };

	        Converter<String, LocalDate> toStringDate = new AbstractConverter<String, LocalDate>() {
	            @Override
	            protected LocalDate convert(String source) {
	                DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	                LocalDate localDate = LocalDate.parse(source, format);
	                return localDate;
	            }
	        };


	        modelmapper.createTypeMap(String.class, LocalDate.class);
	        modelmapper.addConverter(toStringDate);
	        modelmapper.getTypeMap(String.class, LocalDate.class).setProvider(localDateProvider);

	       return modelmapper;
	    }
	
	public static void main(String[] args) {
		SpringApplication.run(ChallengeApplication.class, args);
	}

}
