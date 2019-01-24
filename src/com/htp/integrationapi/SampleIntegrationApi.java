package com.htp.integrationapi;

import java.util.HashMap;

/**
 *
 * @author lydiairwan
 */
public class SampleIntegrationApi {

    public static void main(String[] args) throws Exception {
//        SampleIntegrationApi sia = new SampleIntegrationApi();
//        System.out.println(SampleIntegrationApi.getOrder());
        SampleIntegrationApi.postOrder();
    }
    
    
    // method to post data via integration restful
    // required:
    // post data = Object in String formatted with JSON
    private static void postOrder() throws Exception{
        DeviceApi deviceApi = new DeviceApi();
        deviceApi.restMethod = "new-order";
        deviceApi.postData = "{"
                + "\"lab_name\":\"lab name input from jar 7 \","
                + "\"test_name\":\"test name input harun\","
                + "\"additional_info\":\"additional data input\","
                + "\"performing_location\":\"performing location\","
                + "\"container\":\"container input\","
                + "\"specimen\":\"specimen\","
                + "\"priority\":\"priority input\","
                + "\"patient_preparation_instruction\":\"patient preparation instruction input\","
                + "\"clinical_notes\":\"clinic notes input\","
                + "\"planned_date\":\"planned date input\","
                + "\"order_by\":\"order by input\","
                + "\"specimen_id\":\"specimen id input\","
                + "\"order_id\":\"order id input\"}";
        deviceApi.postOrder();
    }
    
    public static String getOrders() throws Exception {
        DeviceApi deviceApi = new DeviceApi();
        deviceApi.restMethod = "order-list";
        return deviceApi.getOrders();
    }
    
    public static String getOrder() throws Exception {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("order_id","1234");
        DeviceApi deviceApi = new DeviceApi();
        deviceApi.getParams = params;
        return deviceApi.getOrders().toString();
    }

}
