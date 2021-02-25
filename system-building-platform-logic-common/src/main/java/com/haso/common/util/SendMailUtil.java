package com.haso.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.haso.common.api.vo.SendMailInfo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SendMailUtil {

	private static JavaMailSender javaMailSender;
	
	public static void sendMail(SendMailInfo info) {
		SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(info.getFrom());
        message.setTo(info.getTo());
        message.setSubject(info.getSubject());
        message.setText(info.getText());
        try {
        	javaMailSender.send(message);
        } catch (Exception e) {
            log.error("发送邮件失败", e);
        }
	}
	
	@SuppressWarnings("static-access")
	@Autowired(required = true)
	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
}
