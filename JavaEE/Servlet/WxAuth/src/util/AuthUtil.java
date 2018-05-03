package util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONObject;

public class AuthUtil {
    public static final String APPID = "wx3754070a8b018a43";
    public static final String APPSECRET = "2faeda9e7e747d631270d250f1cab874";
    public static JSONObject doGetJson(String url)
            throws ClientProtocolException, IOException {
        JSONObject json = null;
        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = client.execute(httpGet);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            String result = EntityUtils.toString(entity, "utf-8");
            json = JSONObject.fromObject(result);
        }
        httpGet.releaseConnection();
        return json;
    }
}
