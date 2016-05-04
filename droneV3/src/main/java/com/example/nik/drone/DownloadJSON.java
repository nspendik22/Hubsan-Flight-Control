package com.example.nik.drone;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class DownloadJSON {

    String url;
    String json;

    public DownloadJSON(String url) {

        this.url = url;

    }

    public String getJSON() {

        try {
            URL requestUrl = new URL(url);
            URLConnection con = requestUrl.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder sb = new StringBuilder();
            int cp;

            while ((cp = in.read()) != -1) {
                sb.append((char) cp);
            }
            json = sb.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return json;
    }

}
