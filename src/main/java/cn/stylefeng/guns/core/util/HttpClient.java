package cn.stylefeng.guns.core.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//SAP接口访问地址和方式
public class HttpClient {

	public String httpClientPostJson(String url, JSONObject jsonObject, String userName, String passWord){
		//返回responseBody
		HttpEntity responseBody = null;
		String isSuccess="";
		int  stateCode;
		int i=0;
		HttpClient httpClient = new HttpClient();
		CredentialsProvider  credsProvider=new BasicCredentialsProvider();
		//设置了账户和密码
		if(userName!=null&&userName.length()>0&passWord!=null&&passWord.length()>0) {
			credsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(userName, passWord));
		}else{
			credsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("", ""));
		}
		CloseableHttpClient createDefault = HttpClients.custom()
				.setDefaultCredentialsProvider(credsProvider)
				.build();
		HttpPost post=new HttpPost(url);
		StringEntity entity=new StringEntity(jsonObject.toString(),"utf-8");
		entity.setContentEncoding("UTF-8");
		entity.setContentType("application/json");
		post.setEntity(entity);
		try {
			do{
				CloseableHttpResponse httpResponse = createDefault.execute(post);
				stateCode=httpResponse.getStatusLine().getStatusCode();
				if(stateCode != HttpStatus.SC_OK){
					isSuccess +="false,接口调用失败";
				}else{
					responseBody = httpResponse.getEntity();
					isSuccess +=EntityUtils.toString(responseBody);//返回正确
				}
				System.out.println(responseBody.toString().indexOf("0"));
				System.out.println("isSuccess:"+isSuccess);
				i++;
			}while((stateCode != HttpStatus.SC_OK)&&i<10);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(post != null){
				post.releaseConnection();//释放接口连接
			}
		}

		return isSuccess;
	}

	/**
	 * httpClient采用content-type为application/x-www-form-urlencoded
	 */
	public String httpClientPostForm(String url, JSONObject jsonObject, String userName, String passWord){
		//返回responseBody
		HttpEntity responseBody = null;
		String isSuccess="";
		int  stateCode;
		int i=0;
		HttpClient httpClient = new HttpClient();
		CredentialsProvider  credsProvider=new BasicCredentialsProvider();
		//设置了账户和密码
		if(userName!=null&&userName.length()>0&passWord!=null&&passWord.length()>0) {
			credsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(userName, passWord));
		}else{
			credsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("", ""));
		}
		CloseableHttpClient createDefault = HttpClients.custom()
				.setDefaultCredentialsProvider(credsProvider)
				.build();
		HttpPost post=new HttpPost(url);
		Map<String ,Object> map= JSON.parseObject(jsonObject.toString());
		List<NameValuePair> valuePairs = new ArrayList<>();
		for(Map.Entry<String,Object> entry : map.entrySet()) {
			NameValuePair valuePair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
			valuePairs.add(valuePair);
		}
		HttpEntity entity = null;
		try {
			entity = new UrlEncodedFormEntity(valuePairs,"utf-8");
		    post.setEntity(entity);
			do{
				CloseableHttpResponse httpResponse = createDefault.execute(post);
				stateCode=httpResponse.getStatusLine().getStatusCode();
				if(stateCode != HttpStatus.SC_OK){
					isSuccess +="false,接口调用失败";
				}else{
					responseBody = httpResponse.getEntity();
					isSuccess =EntityUtils.toString(responseBody);//返回正确
				}
				System.out.println(isSuccess);
//				System.out.println(responseBody.toString().indexOf("0"));
//				System.out.println("isSuccess:"+isSuccess);
				i++;
			}while((stateCode != HttpStatus.SC_OK)&&i<10);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(post != null){
				post.releaseConnection();//释放接口连接
			}
		}

		return isSuccess;
	}

}
