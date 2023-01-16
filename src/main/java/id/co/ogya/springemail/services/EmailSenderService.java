package id.co.ogya.springemail.services;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailSenderService {
	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private TemplateEngine htmlTemplateEngine;
	
	public void sendEmailWithoutAttachment() {
		 SimpleMailMessage mailMessage = new SimpleMailMessage();
		 mailMessage.setTo("singgihpraditya@gmail.com");
		 mailMessage.setSubject("Test email dari Spring");
		 mailMessage.setText("Ini email dari spring");
		 
		 javaMailSender.send(mailMessage);
	}
	
	public void sendEmailWithAttachment() {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			
			mimeMessageHelper.setFrom("admin@xyzbank.com");
			mimeMessageHelper.setTo("singgihpraditya@gmail.com");
			mimeMessageHelper.setSubject("Notifikasi dari admin");
		
			Context ctx = new Context(LocaleContextHolder.getLocale());
		    ctx.setVariable("name", "singgih");
		    ctx.setVariable("email", "singgihpraditya@gmail.com");
		    
			String body = htmlTemplateEngine.process("registration", ctx);
			mimeMessageHelper.setText(body, true);
			
			File attachmentFile = new File("d:/next_task.txt");
			mimeMessageHelper.addAttachment("attachment.txt", attachmentFile);
			javaMailSender.send(mimeMessage);
			
			System.out.println("Email sent");
		} catch (MessagingException e) {
			System.err.print("Failed send email");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
}
