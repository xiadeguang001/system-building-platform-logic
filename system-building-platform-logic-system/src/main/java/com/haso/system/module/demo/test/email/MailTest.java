package com.haso.system.module.demo.test.email;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class MailTest {

    private String sendMailUserName;

    private String sendMailPassword;

    private String toMail;

    private String title;

    private String content;

    public MailTest(String sendMailUserName, String sendMailPassword, String toMail, String title,
                    String content){
        this.sendMailPassword = sendMailPassword;
        this.sendMailUserName= sendMailUserName;
        this.toMail=toMail;
        this.title =title;
        this.content=content;
    }


    public void send() throws MessagingException, UnsupportedEncodingException {
        Properties props = new Properties();
        props.put("mail.smtp.host", "mail.xxx.com.cn");
        props.put("mail.smtp.auth", "true");
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sendMailUserName,sendMailPassword);
            }
        });
        Transport transport = session.getTransport("smtp");
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("root@xxx.com.cn","社会人"));
        message.setSubject(title);
        message.setText(content);
        message.setRecipient(Message.RecipientType.TO,new InternetAddress(toMail));
        transport.send(message);
    }
}