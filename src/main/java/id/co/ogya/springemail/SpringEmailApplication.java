package id.co.ogya.springemail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import id.co.ogya.springemail.services.EmailSenderService;

@SpringBootApplication
public class SpringEmailApplication implements CommandLineRunner {
	@Autowired
	private EmailSenderService emailSenderService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringEmailApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		emailSenderService.sendEmailWithAttachment();
	}
}
