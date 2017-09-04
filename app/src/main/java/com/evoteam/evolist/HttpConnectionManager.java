package com.evoteam.evolist;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Base64;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by user on 7/23/2017.
 */
public class HttpConnectionManager {

    static HttpURLConnection urlConnection = null;
    final static String saeedURL = "https://farzad001.herokuapp.com/api/signup";
    final static  String serverSignINUrl    = "http://23.227.201.71:3001/api/signin";
    final static  String serverSignUpUrl    = "http://23.227.201.71:3001/api/signup";
    final static  String serverSendDataUrl  = "http://23.227.201.71:3001/api/sendtodo";
    final static  String serverGetDataUrl   = "http://23.227.201.71:3001/api/gettodo";
    /*"https://farzad007.herokuapp.com/api/signup";*/

    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected())
            return true;
        else
            return false ;
    }

    public static String inputStreamToString(InputStream inputstream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputstream));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static String getData() {
        try {
            String uri = serverGetDataUrl;
            uri += "?username=farzad";
            URL url = new URL(uri);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            InputStream is = urlConnection.getInputStream();
            String response = inputStreamToString(is);
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }finally {
            urlConnection.disconnect();
        }
    }

    public static String postSignUp(String json) {
        try {
            URL url = new URL(serverSignUpUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            String urlParameters = json;
            urlConnection.setRequestProperty("Authorization", "basic " +
                    Base64.encodeToString(("farzad:767676").getBytes(), Base64.NO_WRAP));
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setUseCaches(false);
            urlConnection.setRequestProperty("Host", "android.schoolportal.gr");
            urlConnection.connect();
            urlConnection.setConnectTimeout(10000);
            urlConnection.setReadTimeout(10000);
            OutputStreamWriter dStream = new OutputStreamWriter(
                    urlConnection.getOutputStream(), "UTF-8");
            dStream.write(urlParameters);
            dStream.flush();
            dStream.close();
            InputStream is = urlConnection.getInputStream();
            String response = inputStreamToString(is);
            return response;


        } catch (MalformedURLException e) {
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    //

    public static String postSignIn(String username, String password) {
        try {
            String uri = serverSignINUrl;
            uri += "?username=" + username + "&password=" + password;
            URL url = new URL(uri);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Authorization", "basic " +
                    Base64.encodeToString(("farzad:767676").getBytes(), Base64.NO_WRAP));
//            urlConnection.setRequestMethod("GET");
//            urlConnection.setDoOutput(true);
//            urlConnection.setDoInput(true);
            urlConnection.setUseCaches(false);
            urlConnection.setRequestProperty("Host", "android.schoolportal.gr");
            urlConnection.setConnectTimeout(10000);
            urlConnection.setReadTimeout(10000);
            urlConnection.connect();
//            OutputStreamWriter dStream = new OutputStreamWriter(
//                    urlConnection.getOutputStream(), "UTF-8");
//            dStream.write(urlParameters);
//            dStream.flush();
//            dStream.close();
            InputStream is = urlConnection.getInputStream();
            String response = inputStreamToString(is);

            return response;


        } catch (MalformedURLException e) {
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static String postData(String json) {
        try {
            URL url = new URL(serverSendDataUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Authorization", "basic " +
                    Base64.encodeToString(("farzad:767676").getBytes(), Base64.NO_WRAP));
            urlConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setUseCaches(false);
            urlConnection.setRequestProperty("Host", "android.schoolportal.gr");
            urlConnection.connect();
            urlConnection.setConnectTimeout(10000);
            urlConnection.setReadTimeout(10000);
            OutputStreamWriter dStream = new OutputStreamWriter(
                    urlConnection.getOutputStream(), "UTF-8");
            dStream.write(json);
            dStream.flush();
            dStream.close();
            InputStream is = urlConnection.getInputStream();
            String response = inputStreamToString(is);
            return response;


        } catch (MalformedURLException e) {
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }


}


