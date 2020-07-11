
package com.tfye.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.tfye.adapter.MessageAdapter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SMSMailboxService  implements MessageAdapter {
	@Autowired
	private JavaMailSender mailSender; // 自动注入的Bean
	@Value("${spring.mail.username}")
	private String usere;
	
	@Override
	public void distribute(JSONObject jsonObject) {
		String mail=jsonObject.getString("mail");
		String userName=jsonObject.getString("userName");
		String code=jsonObject.getString("code");
		String company=jsonObject.getString("company");
		log.info("###消费者收到消息... mail:{},userName:{}",mail,userName,code,company);
		// 发送邮件
		// 开始发送邮件
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(usere);
		message.setTo(mail); // 自己给自己发送邮件
		message.setSubject(company+"商城注册验证码");
		message.setText("恭喜您"+userName+",正在注册成为"+company+"商城的新用户!您的验证码为\""+code+"\",谢谢您的注册!  www.tfye.com");
		log.info("###发送短信邮箱 mail:{}", mail);
		mailSender.send(message);
	}

}
