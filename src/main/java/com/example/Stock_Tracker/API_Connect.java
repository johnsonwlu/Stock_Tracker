package com.example.Stock_Tracker;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.Scanner;
import java.net.URLEncoder;
import java.util.Scanner;

import com.google.gson.*;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import gui.main_GUI;
import org.json.JSONArray;
import org.json.JSONObject;

public class API_Connect {
    public Integer api(String qInput) throws Exception {
//        Integer overallCost = 0;
//        while(true) {
//            System.out.println("What would you like to do? ");
//            System.out.println("1. add stocks");
//            System.out.println("2. remove stocks");
//            System.out.println("3. exit");
//
//            Scanner sc = new Scanner(System.in);
//            Integer stop = sc.nextInt();
//            if (stop == 3) {
//                System.out.println("Thank you for using our tracker");
//                break;
//            }
//            else
//                System.out.print("Enter Company ");
//            String qInput = sc.next();

            // Host url
            String host = "https://apidojo-yahoo-finance-v1.p.rapidapi.com/market/v2/get-quotes?region=US&symbols=" + qInput + "%2C";

            // Headers for a request
            String x_rapidapi_host = "apidojo-yahoo-finance-v1.p.rapidapi.com";
            String x_rapidapi_key = "fbc0efed9emshf31f9309e9aef73p1aa77ejsn2c24b61978cd";//Type here your key
            String charset = "UTF-8";
            // Params
            String q = "AMD";
            String region = "US";
            // Format query for preventing encoding problems
            String query = String.format("q=%s",
                    URLEncoder.encode(q, charset));
            String queryOne = String.format("region=%s",
                    URLEncoder.encode(region, charset));

            HttpResponse<JsonNode> response = Unirest.get(host + "?" + query)
                    .header("x-rapidapi-host", x_rapidapi_host)
                    .header("x-rapidapi-key", x_rapidapi_key)
                    .asJson();

            JSONObject testObject = new JSONObject(response);
            //System.out.println(testObject);
            Integer value = testObject.getJSONObject("body").getJSONObject("object").getJSONObject("quoteResponse").getJSONArray("result").getJSONObject(0).getInt("regularMarketPreviousClose");
            //System.out.println(Value);
            return value;
//            System.out.println("How many shares did you buy? ");
//            Integer totalValue = value * sc.nextInt();
//            System.out.println("You have added: " + totalValue);
//            overallCost += totalValue;
//            System.out.println("Your current total is: " + overallCost);
        }
    }

