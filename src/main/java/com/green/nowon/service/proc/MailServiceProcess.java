package com.green.nowon.service.proc;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.green.nowon.service.MailService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class MailServiceProcess implements MailService{

	private final JavaMailSender javaMailSender;
    private static final String senderEmail= "godme7058@gmail.com";
    private static int number;
    private static char[] password={ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
            'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };;
	
    public String createpass(){
          String str="";
          int idx=0;
            	for(int i =0;i<10;i++) {	
            	idx=(int)(password.length*Math.random());
            	str+=password[idx];
            }
            return str;
        }
            
    public static void createNumber(){
        number = (int)(Math.random() * (90000)) + 100000;// (int) Math.random() * (최댓값-최소값+1) + 최소값
    }
    
    public MimeMessage createMail(String mail){
        createNumber();
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            message.setFrom(senderEmail);
            message.setRecipients(MimeMessage.RecipientType.TO, mail);
            message.setSubject("이메일 인증");
            String body = "";
            body += "<h3>" + "요청하신 인증 번호입니다." + "</h3>";
            body += "<h1>" + number + "</h1>";
            body += "<h3>" + "감사합니다." + "</h3>";
            message.setText(body,"UTF-8", "html");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return message;
    }
    
    public MimeMessage createPassMail(String mail,String str){
       
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            message.setFrom(senderEmail);
            message.setRecipients(MimeMessage.RecipientType.TO, mail);
            message.setSubject("이메일 인증");
            String body = "";
            body += "<h3>" + "요청하신 임시 비밀번호 입니다." + "</h3>";
            body += "<h1>" + str + "</h1>";
            body += "<h3>" + "감사합니다." + "</h3>";
            message.setText(body,"UTF-8", "html");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return message;
    }
    
	@Override
	public int sendMail(String mail) {
		 MimeMessage message = createMail(mail);
	        javaMailSender.send(message);

	        return number;
	}

	@Override
	public String mail(String mail) {
		String str=createpass();
		MimeMessage message = createPassMail(mail,str);
        javaMailSender.send(message);

        return str;
	}

}
