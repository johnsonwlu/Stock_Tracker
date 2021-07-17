package com.example.Stock_Tracker;

import java.net.URLEncoder;
import java.util.Scanner;

import com.google.gson.*;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import gui.main_GUI;
import org.json.JSONArray;
import org.json.JSONObject;

public class Main {

    private main_GUI window = null;

    private void GUI() {
        window = new main_GUI();
        window.showFrame(this::GUI);
    }



    public static void main( String[] args ) throws Exception
    {
        Main file = new Main();
        file.GUI();
//        Scanner sc = new Scanner(System.in);
//
//
//        System.out.print("Enter Company ");
//        String qInput = sc.next();
//
//        API_Connect ac = new API_Connect();
//        System.out.println(ac.api(qInput));
    }


}
