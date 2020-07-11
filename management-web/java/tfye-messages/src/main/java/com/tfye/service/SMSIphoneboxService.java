package com.tfye.service;

import com.alibaba.fastjson.JSONObject;
import com.tfye.adapter.MessageAdapter;
import com.tfye.utils.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;


@Service
public class SMSIphoneboxService  implements MessageAdapter {

   
	@Override
	public void distribute(JSONObject jsonObject) {
		// TODO 自动生成的方法存根
		String iphone=jsonObject.getString("iphone");
		String userName=jsonObject.getString("userName");
		String code=jsonObject.getString("code");
		String company=jsonObject.getString("company");
		String text = "恭喜您"+userName+",正在注册成为"+company+"商城的新用户!您的验证码为\""+code+"\",谢谢您的注册!  www.tfye.com" ;
        Integer resultCode = SendIphoneUtil.send("qxyy","d41d8cd98f00b204e5856",iphone,"验证码:"+code);
        if (resultCode==-3) {
        	resultCode = SendIphoneUtil.send("hejing","d41d8cd98f00b204e250",iphone,"验证码:"+code);
        	if (resultCode==-3) {
        		resultCode = SendIphoneUtil.send("hejing","d41d8cd98f00b204e250",iphone,"验证码:"+code);
			}
		} 
        String message = SendIphoneUtil.getMessage(resultCode);
        System.out.println(message+"*****************************");
	}
	
	
}
