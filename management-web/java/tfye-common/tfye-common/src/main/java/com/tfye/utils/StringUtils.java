package com.tfye.utils;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
	
	public static void main(String[] args) {
//		String a = getSalt();
//		System.out.println(a);
		String b = getRandomCode(8);
		System.out.println(b);
//		W3ikaS
//		3aff4c72eed39c69ea52d819bb8b6756c1d11f3a
	}
	
}
