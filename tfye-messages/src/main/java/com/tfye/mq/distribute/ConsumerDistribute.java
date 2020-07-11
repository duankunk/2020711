
package com.tfye.mq.distribute;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.netflix.discovery.converters.Auto;
import com.tfye.adapter.MessageAdapter;
import com.tfye.constants.MQInterfaceType;
import com.tfye.service.SMSIphoneboxService;
import com.tfye.service.SMSMailboxService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class ConsumerDistribute {
	@Autowired
	private SMSMailboxService smsMailboxService;
	@Autowired
	private SMSIphoneboxService smsIphoneboxService;
	@JmsListener(destination = "mail_queue")
	public void distribute(String json) {
		log.info("###消息服务###收到消息,消息内容 json:{}", json);
		if (StringUtils.isEmpty(json)) {
			return;
		}
		JSONObject jsonObject = new JSONObject().parseObject(json);
		JSONObject header = jsonObject.getJSONObject("header");
		String interfaceType = header.getString("interfaceType");
		MessageAdapter messageAdapter = null;
		switch (interfaceType) {
		// 发送邮件
		case MQInterfaceType.SMS_MAIL:
			messageAdapter=smsMailboxService;
			break;
		case MQInterfaceType.SMS_IPHON:
			messageAdapter=smsIphoneboxService;
			break;
		default:
			break;
		}
		JSONObject content=jsonObject.getJSONObject("content");
		messageAdapter.distribute(content);

	}

}
