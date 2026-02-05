package in.rahulscripts.authify.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class EmailService {
	public final JavaMailSender mailSender;
	private final TemplateEngine templateEngine;
	
	@Value("${spring.mail.properties.mail.smtp.from}")
	private String fromEmail;
	
	public void sendWelcomeEmail(String toEmail, String name) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(fromEmail);
		message.setTo(toEmail);
		message.setSubject("Welcome to Our Platforms");
		message.setText("Hello "+name+",\n\n Thanks For Registering With Us!\n\nRegards,\nAuthify Team");
		mailSender.send(message);
	}
	
//	public void sendResetOtpEmail(String toEmail,String otp) {
//		SimpleMailMessage message = new SimpleMailMessage();
//		message.setFrom(fromEmail);
//		message.setTo(toEmail);
//		message.setSubject("Password Reset OTP");
//		message.setText("Your otp for reseting your password is "+ otp + ". User this OTP to proceed with resetting your password. ");
//		mailSender.send(message);
//	}
//	
//	public void sendOtpEmail(String toEmail,String otp) {
//		SimpleMailMessage message = new SimpleMailMessage();
//		message.setFrom(fromEmail);
//		message.setTo(toEmail);
//		message.setSubject("Account Verification OTP");
//		message.setText("Your OTP is "+ otp + ". Verify Your Account using this OTP");
//		mailSender.send(message);
//	}
	
	public void sendOtpEmail(String toEmail, String otp) throws MessagingException {
		Context context = new Context();
		context.setVariable("email", toEmail);
		context.setVariable("otp", otp);
		
		String process = templateEngine.process("verify-email", context);
		MimeMessage mimiMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimiMessage);
		
		helper.setFrom(fromEmail);
		helper.setTo(toEmail);
		helper.setSubject("Account Verification OTP");
		helper.setText(process, true);
		
		mailSender.send(mimiMessage);;
	}
	
	public void sendResetOtpEmail(String toEmail, String otp) throws MessagingException {
		Context context = new Context();
		context.setVariable("email", toEmail);
		context.setVariable("otp", otp);
		
		String process = templateEngine.process("password-reset-email", context);
		MimeMessage mimiMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimiMessage);
		
		helper.setFrom(fromEmail);
		helper.setTo(toEmail);
		helper.setSubject("Password Reset OTP");
		helper.setText(process, true);
		
		mailSender.send(mimiMessage);
	}
}
