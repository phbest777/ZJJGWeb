package org.ph.ssm.ZJJGWeb.httpcomm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.ph.ssm.ZJJGWeb.httpcomm.HttpComm;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
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

 public abstract class XzhouseComm implements HttpComm{
     public abstract List<String>GetCommIni(String FilePath) throws IOException;
     public abstract String GetHttPost(String PostStr);
     public abstract JsonNode DealHttpPost(String resdata) throws IOException;
     public abstract String MapToJsonStr(Map map) throws JsonProcessingException;
     @Override
     public String DealHttpGet(String resdata)
     {
         String s=null;
         return s;
     }
     @Override
     public String GetHttpGet(String GetStr)
     {
         String s=null;
         return s;
     }

}
