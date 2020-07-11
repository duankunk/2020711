
package com.tfye.mq.roducer;

import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * 
 * @classDesc: 功能描述:(往消息服务 推送 邮件信息)
 * @author: 蚂蚁课堂创始人-余胜军
 * @QQ: 644064779
 * @QQ粉丝群: 116295598
 * @createTime: 2017年10月24日 下午11:40:45
 * @version: v1.0
 * @copyright:每特学院(蚂蚁课堂)上海每特教育科技有限公司
 */
@Service
public class RegisterMailboxProducer {
	@Autowired
    private JmsMessagingTemplate jmsMessagingTemplate ; 
	
	public void send(Destination destination,String json){
		jmsMessagingTemplate.convertAndSend(destination, json);
	}
}

//@Value("${messages.queue}")
//private String MESSAGES_QUEUE;
//@Override
//public void regist(UserEntity userEntity) {
//	userEntity.setCreated(DateUtils.getTimestamp());
//	userEntity.setUpdated(DateUtils.getTimestamp());
//	String phone = userEntity.getPhone();
//	String password = userEntity.getPassword();
//	userEntity.setPassword(md5PassSalt(phone, password));
//	userDao.save(userEntity, DBTableName.TABLE_MB_USER);
//	// 队列
//	Destination activeMQQueue = new ActiveMQQueue(MESSAGES_QUEUE);
//	// 组装报文格式
//	String mailMessage = mailMessage(userEntity.getEmail(), userEntity.getUserName());
//	log.info("###regist() 注册发送邮件报文mailMessage:{}", mailMessage);
//	// mq
//	registerMailboxProducer.send(activeMQQueue, mailMessage);
//}
//private String mailMessage(String email, String userName) {
//	JSONObject root = new JSONObject();
//	JSONObject header = new JSONObject();
//	header.put("interfaceType", MQInterfaceType.SMS_MAIL);
//	JSONObject content = new JSONObject();
//	content.put("mail", email);
//	content.put("userName", userName);
//	root.put("header", header);
//	root.put("content", content);
//	return root.toJSONString();
//}