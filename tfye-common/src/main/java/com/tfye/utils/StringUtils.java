package com.tfye.utils;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;

import com.tfye.constants.BaseApiConstants;

public class StringUtils {
	public static List<String> getList(String text) {
		List<String> list = Arrays.asList(text.split(","));
		return list;
	}
	public static String getSalt(){
        char[]chars="0123456789abcdefghijklmnopqrwtuvzxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        char[] saltchars=new char[6];
        Random RANDOM=new SecureRandom();
        for(int i=0;i<6;i++)
        {
            int n=RANDOM.nextInt(62);
            saltchars[i]=chars[n];
        }
        String salt=new String(saltchars);
        return salt;
    }
	public static String getPassword(String P , String s){

        return  DigestUtils.sha1Hex(P + s);
    }
	
	
	public static String getRandomCode(Integer code){
        Random random = new Random();
        StringBuffer result= new StringBuffer();
        for (int i=0;i<code;i++){
            result.append(random.nextInt(10));
        }
        return result.toString();
    }
	

	
	   /**
	    * 
	    * String转map
	    * @param str
	    * @return
	    */
	   public static Map<String,Object> getStringToMap(String str){
	       //根据逗号截取字符串数组
		   str=  str.replace("{","" );
		   str = str.replace("}", "");
	       String[] str1 = str.split(",");
	       //创建Map对象
	       Map<String,Object> map = new HashMap<String, Object>();
	       //循环加入map集合
	       for (int i = 0; i < str1.length; i++) {
	           //根据":"截取字符串数组
	           String[] str2 = str1[i].split("=");
	           //str2[0]为KEY,str2[1]为值
	           if (str2[1].equals("null")) continue;
	           String key = str2[0];
	           String valus = str2[1];
	           map.put(key,valus);
	       }
	      
	       return map;
	   }
	   
	   public static void main(String[] args) {
		System.out.println(7/10);
		}
}
