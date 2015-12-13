package com.alexshelton.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Julian on 12/7/2015.
 */
public class ParseUtil implements Net.HttpResponseListener {

    //variables holding the Parse connection URL and connection strings
    private URL url = null;
    private URLConnection conn = null;
    private String app_id;
    private String app_key;

    protected ParseUtil() {
        try {
            url = new URL("https://api.parse.com/1/classes/score/");
            app_id = "aj6gqhUuxJeewWLQVONrgcnaEF4z1haJAIAZbbiI";
            app_key = "822bKFKvwmPWRNRo8xlKCMff1QfCwOpNOYFTb6FM";
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void add_net_score(){
        // LibGDX NET CLASS
        //Add final game score to Parse database using HTTP POST
        Net.HttpRequest httpPost = new Net.HttpRequest(Net.HttpMethods.POST);
        httpPost.setUrl("https://api.parse.com/1/classes/score/");
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("X-Parse-Application-Id", app_id);
        httpPost.setHeader("X-Parse-REST-API-Key", app_key);
        httpPost.setContent("{\"score\": 1337, \"user\": \"CarelessLabs Java\"}");
        Gdx.net.sendHttpRequest(httpPost, ParseUtil.this);
    }


    public boolean add_score(){
        // USING JAVA IO AND NET CLASS
        try {
            conn = url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestProperty("X-Parse-Application-Id", app_id);
            conn.setRequestProperty("X-Parse-REST-API-Key", app_key);
            conn.setRequestProperty("Content-type", "application/json");
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
            out.write("{\"score\": 1337, \"user\": \"CarelessLabs GDX\"}");

            out.close();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String decodedString;
            while ((decodedString = in.readLine()) != null) {
                System.out.println(decodedString);
            }
            in.close();
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean get_score(){
        // USING JAVA IO AND NET CLASS
        try {
            conn = url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestProperty("X-Parse-Application-Id", app_id);
            conn.setRequestProperty("X-Parse-REST-API-Key", app_key);
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                System.out.println(inputLine);
            in.close();
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void get_net_score(){
        // LibGDX NET CLASS
        //Retrieve game scores from Parse database using HTTP GET request
        Net.HttpRequest httpGet = new Net.HttpRequest(Net.HttpMethods.GET);
        httpGet.setUrl("https://api.parse.com/1/classes/score/");
        httpGet.setHeader("Content-Type", "application/json");
        httpGet.setHeader("X-Parse-Application-Id", app_id);
        httpGet.setHeader("X-Parse-REST-API-Key", app_key);
        Gdx.net.sendHttpRequest(httpGet,ParseUtil.this);
    }

    @Override
    public void handleHttpResponse(Net.HttpResponse httpResponse) {
        final int statusCode = httpResponse.getStatus().getStatusCode();
        System.out.println(statusCode + " " + httpResponse.getResultAsString());
    }

    @Override
    public void failed(Throwable t) {
        System.out.println(t.getMessage());
    }

    @Override
    public void cancelled() {

    }
}