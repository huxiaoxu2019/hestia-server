package com.ihuxu.hestia.server.library.map;

import com.ihuxu.hestia.server.config.CommonConfig;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

@SuppressWarnings("deprecation")
public class MapHandler {
  @SuppressWarnings("unused")
  private static String post(String url, List<NameValuePair> nameValuePair) throws IOException {
    String body = "{}";
    @SuppressWarnings("resource")
    DefaultHttpClient httpclient = new DefaultHttpClient();
    try {
      HttpPost httpost = new HttpPost(url);
      httpost.setEntity(new UrlEncodedFormEntity(nameValuePair, StandardCharsets.UTF_8));
      HttpResponse response = httpclient.execute(httpost);
      HttpEntity entity = response.getEntity();
      body = EntityUtils.toString(entity);
    } finally {
      httpclient.getConnectionManager().shutdown();
    }
    return body;
  }

  private static String get(String url) throws ClientProtocolException, IOException {
    String body = "{}";
    @SuppressWarnings("resource")
    DefaultHttpClient httpclient = new DefaultHttpClient();
    try {
      HttpGet httpget = new HttpGet(url);
      HttpResponse response = httpclient.execute(httpget);
      HttpEntity entity = response.getEntity();
      body = EntityUtils.toString(entity);
    } finally {
      httpclient.getConnectionManager().shutdown();
    }
    return body;
  }

  public static JSONObject geocoder(double lnt, double lat) {
    String url =
        "http://api.map.baidu.com/geocoder/v2/?coordtype=wgs84ll&location="
            + lat
            + ","
            + lnt
            + "&output=json&pois=1&ak="
            + CommonConfig.BAIDU_MAP_AK;
    try {
      return new JSONObject(MapHandler.get(url));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
