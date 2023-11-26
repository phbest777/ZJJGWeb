package org.ph.ssm.ZJJGWeb.httpcomm;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.http.*;
import org.apache.http.client.methods.CloseableHttpResponse;

public interface HttpComm {
    List<String> GetCommIni(String FilePath) throws IOException;
    String GetHttPost(String PostStr);
    String GetHttpGet(String GetStr);
    JsonNode DealHttpPost(String resdata) throws IOException;
    String DealHttpGet(String resdata);
    String MapToJsonStr(Map map) throws JsonProcessingException;

}
