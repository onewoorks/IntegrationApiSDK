/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.htp.integrationapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.HashMap;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 *
 * @author lydiairwan
 */
public class DeviceApi {

    public String restUrl = "http://172.19.64.7:23111";
    public String restMethod;

    HashMap<String, String> getParams;
    public String postData;

    private final String restAuthorization = "Basic aXdhbmc6aXdhbmc=";

    public String getOrders() throws Exception {
        String postUri;
        
        if(getParams==null){
            postUri = this.restUrl + "/" + this.restMethod;
        } else {
            System.out.println(getParams.toString());
            postUri = this.restUrl + "/order-info?order_id=" + getParams.get("order_id");
        }

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(postUri);
        request.addHeader("Authorization", this.restAuthorization);
        HttpResponse response = client.execute(request);

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line;
        String returnLine = "";
        while ((line = rd.readLine()) != null) {
            returnLine = line;
        }
        System.out.println("method executed");
        return returnLine;
    }

    public void postOrder() throws Exception {
        try {
            String postUri = this.restUrl + "/" + this.restMethod;
            HttpClient client = HttpClientBuilder.create().build();
            HttpPost postRequest = new HttpPost(postUri);
            postRequest.addHeader("Authorization", this.restAuthorization);

            StringEntity input = new StringEntity(this.postData);
            input.setContentType("application/json");
            postRequest.setEntity(input);

            HttpResponse response = client.execute(postRequest);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

            client.getConnectionManager().shutdown();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
