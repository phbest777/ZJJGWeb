package org.ph.ssm.ZJJGWeb.httpcomm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.ph.ssm.ZJJGWeb.httpcomm.XzhouseComm;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.List;

public class XzhouseCommImp extends XzhouseComm {

    private String IP;
    private String PORT;
    private String URI;
    private String PREFIX;
    @Override
    public List<String> GetCommIni(String fileName) throws IOException {Properties properties=new Properties();
       BufferedReader bufferedReader=new BufferedReader(new FileReader(fileName));
       properties.load(bufferedReader);
        IP=properties.getProperty("IP");
        PORT=properties.getProperty("PORT");
        PREFIX=properties.getProperty("PREFIX");
        List<String> Paras=new ArrayList<String>();
        Paras.add(IP);
        Paras.add(PORT);
        Paras.add(PREFIX);
        URI="http://"+IP+":"+PORT;
        return Paras;
    }
    @Override
    public String GetHttPost(String PostStr)
    {
        CloseableHttpResponse httpResponse=null;
        HttpPost httpPost=new HttpPost(URI);
        HttpClientBuilder httpClientBuilder=HttpClientBuilder.create();
        CloseableHttpClient closeableHttpClient=httpClientBuilder.build();
        StringEntity entity;
        String resData="";

       try
       {
           entity=new StringEntity(PostStr,"UTF-8");
           entity.setContentEncoding("UTF-8");
           entity.setContentType("application/x-www-form-urlencoded");
           httpPost.setEntity(entity);
            httpResponse=closeableHttpClient.execute(httpPost);
           resData=EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
           return resData;

       }
       catch(Exception ex)
       {
           ex.printStackTrace();
       }
      return resData;
    }
    @Override
    public JsonNode DealHttpPost(String resdata) throws IOException {
        ObjectMapper mapper=new ObjectMapper();
        JsonNode actualObj=mapper.readTree(resdata);
        return actualObj;
    }
    @Override
    public String MapToJsonStr(Map map) throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        String JsonStr=objectMapper.writeValueAsString(map);
        return JsonStr;
    }
}
