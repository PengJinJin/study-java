package kkx.testhttpclient;

import org.apache.http.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestHttpClient {
	@Test
	public void test_get() throws IOException {
		//创建httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//创建请求方法实例
		HttpGet get = new HttpGet("http://www.besttest.cn");
		//发送请求，用Http response接收
		HttpResponse response = httpClient.execute(get);
		//获取响应状态码
		int code = response.getStatusLine().getStatusCode();
		//获取响应头
		Header[] headers = response.getAllHeaders();
		//获取响应体
		HttpEntity entity = response.getEntity();
		System.out.println(code);
		System.out.println(Arrays.toString(headers));
		System.out.println(EntityUtils.toString(entity));
	}

	@Test
	public void test_get_parameter() throws URISyntaxException, IOException {
		//创建httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//发送带参数的get请求
		//创建uri对象
		URIBuilder uriBuilder = new URIBuilder("http://localhost:8090/test_get");
		uriBuilder.addParameter("name", "besttest");
		//创建请求方法实例
		HttpGet get = new HttpGet(uriBuilder.build());
		//发送请求，用Http response接收
		HttpResponse response = httpClient.execute(get);
		//获取响应状态码
		int code = response.getStatusLine().getStatusCode();
		//获取响应头
		Header[] headers = response.getAllHeaders();
		//获取响应体
		HttpEntity entity = response.getEntity();
		System.out.println(code);
		System.out.println(Arrays.toString(headers));
		System.out.println(EntityUtils.toString(entity));
	}

	@Test
	public void test_post_paramter() throws URISyntaxException, IOException {
		//创建httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//发送带参数的post请求
		//创建请求方法实例
		HttpPost post = new HttpPost("http://localhost:8090/test_post");
		//创建entity,模拟一个表单
		List<NameValuePair> kvlist = new ArrayList<>();
		kvlist.add(new BasicNameValuePair("name", "andashu"));
		//包装成entity对象
		StringEntity se = new UrlEncodedFormEntity(kvlist, "utf-8");
		post.setEntity(se);
		//发送请求，用Http response接收
		HttpResponse response = httpClient.execute(post);
		//获取响应状态码
		int code = response.getStatusLine().getStatusCode();
		//获取响应头
		Header[] headers = response.getAllHeaders();
		//获取响应体
		HttpEntity entity = response.getEntity();
		System.out.println(code);
		System.out.println(Arrays.toString(headers));
		System.out.println(EntityUtils.toString(entity));
	}

	@Test
	public void redirect() throws Exception {
		LaxRedirectStrategy redirectStrategy = new LaxRedirectStrategy();
		CloseableHttpClient httpclient = HttpClients.custom().setRedirectStrategy(redirectStrategy).build();
		HttpClientContext context = HttpClientContext.create();
		HttpPost httpPost = new HttpPost("http://47.94.108.11/bugfree/site/login");
		List<NameValuePair> kvList = new ArrayList<>();
		kvList.add(new BasicNameValuePair("LoginForm[username]", "admin"));
		kvList.add(new BasicNameValuePair("LoginForm[password]", "123456"));
		kvList.add(new BasicNameValuePair("LoginForm[language]", "zh_cn"));
		kvList.add(new BasicNameValuePair("LoginForm[rememberMe]", "0"));
		StringEntity entity = new UrlEncodedFormEntity(kvList, "utf-8");
		httpPost.setEntity(entity);
		CloseableHttpResponse response = httpclient.execute(httpPost, context);
		HttpHost target = context.getTargetHost();
		List<URI> redirectLocations = context.getRedirectLocations();
		System.out.println(redirectLocations);
		URI location = URIUtils.resolve(httpPost.getURI(), target, redirectLocations);
		System.out.println("Final HTTP location: " + location.toASCIIString());
	}

	@Test
	public void test_getHttps() throws IOException {
		//创建httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//创建请求方法实例
		HttpGet get = new HttpGet("https://www.baidu.com");
		//发送请求，用Http response接收
		HttpResponse response = httpClient.execute(get);
		//获取响应状态码
		int code = response.getStatusLine().getStatusCode();
		//获取响应头
		Header[] headers = response.getAllHeaders();
		//获取响应体
		HttpEntity entity = response.getEntity();
		System.out.println(code);
		System.out.println(Arrays.toString(headers));
		System.out.println(EntityUtils.toString(entity, "utf-8"));
	}

	@Test
	public void test_httpclient_uploads() throws IOException {
		File file = new File("MapStringDemo.txt");
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse httpResponse = null;
		HttpPost post = new HttpPost("http://localhost:8090/upload");
		MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
		multipartEntityBuilder.addBinaryBody("file", file);
		post.setEntity(multipartEntityBuilder.build());
		httpResponse = httpClient.execute(post);
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		if (statusCode == HttpStatus.SC_OK) {
			HttpEntity resEntity = httpResponse.getEntity();
			String result = EntityUtils.toString(resEntity);
			EntityUtils.consume(resEntity);
			System.out.println(result);
		}
		HttpClientUtils.closeQuietly(httpClient);
		HttpClientUtils.closeQuietly(httpResponse);
	}

	@Test
	public void test_httpclient_downloads() throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet get = new HttpGet("http://localhost:8090/download");
		HttpResponse response = httpClient.execute(get);
		HttpEntity httpEntity = response.getEntity();
		//从服务器到内存里。输入流
		InputStream in = httpEntity.getContent();
		//从内存输出到磁盘 。输出流
		File file = new File("a.txt");
		if (!file.exists()) {
			file.createNewFile();
		}
		FileOutputStream out = new FileOutputStream(file);

		//建立一个缓冲区
		byte[] buffer = new byte[512];
		int readLength = 0;
		//从内存输入流里读，写到缓冲区里
		while ((readLength = in.read(buffer)) > 0) {
			out.write(buffer, 0, readLength);
//			byte[] bytes = new byte[readLength];
//			System.arraycopy(buffer, 0, bytes, 0, readLength);
//			out.write(bytes);
		}
		out.close();
	}

	@Test
	public void test_post_json() throws IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String e = "{\"name\":\"andashu\"}";
		StringEntity entity = new StringEntity(e, "utf-8");
		HttpPost post = new HttpPost("http://localhost:8090/json_test");
		post.setHeader("content-type", "application/json");
		post.setEntity(entity);
		HttpResponse response = httpClient.execute(post);
		int code = response.getStatusLine().getStatusCode();
		System.out.println(code);
		HttpEntity httpEntity = response.getEntity();
		System.out.println(EntityUtils.toString(httpEntity));

	}

}

