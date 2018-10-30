package com.chonger.sso.utils;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
/**
 * 为手机产生验证码的类
 * @author hzy
 *
 */
public class SendValidateCode {
	/**
	 * 为手机发送验证码的静态方法
	 * @param code 随机验证码
	 * @param phone 目标电话
	 * @return 状态码
	 * @throws Exception
	 */
	public static int send(String code, String phone) throws Exception {
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod("http://gbk.api.smschinese.cn");
		String smsText = "您的验证码为:" + code;
		post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=gbk");// 在头文件中设置转码
		NameValuePair[] data = { new NameValuePair("Uid", "HzyDeSms"), new NameValuePair("Key", "18702506365sms"),
				new NameValuePair("smsMob", phone), new NameValuePair("smsText", smsText) };
		post.setRequestBody(data);

		client.executeMethod(post);
		Header[] headers = post.getResponseHeaders();
		int statusCode = post.getStatusCode();
		System.out.println("statusCode:" + statusCode);
		for (Header h : headers) {
			System.out.println(h.toString());
		}
		String result = new String(post.getResponseBodyAsString().getBytes("gbk"));
		System.out.println(result); // 打印返回消息状态

		post.releaseConnection();
		return statusCode;
	}
}
