package com.wastemanagement.phyllis.cleandisposalapp;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by Phyllis on 4/29/2015.
 */
public class JSonPaser {

    //captures the inputs
    static InputStream inputStream = null;
    //returns, sends data as objects
    static JSONObject jsonObject = null;
    // stores returned data into a string format
    static String json = "";

    // constructor

    public JSonPaser() {
    }

    public JSONObject makeResquest(String url, String method, List<NameValuePair> params){
        // Making Http request.
        try{
            //check for request method
            if (method == "POST"){
                //defaulfHttpClient
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(url);
                httpPost.setEntity(new UrlEncodedFormEntity(params));

                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                inputStream = httpEntity.getContent();

            }

            else if (method == "GET"){
                DefaultHttpClient httpClient = new DefaultHttpClient();
                String parametersstring = URLEncodedUtils.format(params, "utf-8");
                url += "?" +parametersstring;
                HttpGet httpGet = new HttpGet(url);

                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                inputStream = httpEntity.getContent();
            }

        }

        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"), 8);
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line= reader.readLine()) !=null){
                stringBuilder.append(line + "\n");
            }
            inputStream.close();
            json = stringBuilder.toString();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        // turns the string to jason
        try {
            jsonObject = new JSONObject(json);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
