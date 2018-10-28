
package com.jt.httpclient;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class HttpClientTest {
	@Test
	public void test01() throws ClientProtocolException, IOException{
		//创建一个http链接对象
		CloseableHttpClient client = HttpClients.createDefault();
		//封装一个request对象,httpclient中,get的request post的request对象
		String url="http://manage.jt.com/item/cat/list";
		HttpGet get=new HttpGet(url);
		
		//client调用执行方法,利用request对象发起请求
		CloseableHttpResponse response = client.execute(get);
		//获取响应体来查看
		HttpEntity entity = response.getEntity();
		String body=EntityUtils.toString(entity);
		System.out.println(body);

	}
}
