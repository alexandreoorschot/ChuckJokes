package com.android.chuckjokes;

import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import android.widget.Toast;



public class HttpConnect extends AsyncTask<String, Integer, String> {
    @Override
    protected String doInBackground(String... strings) {
        String responseText = null;
        try {
            String url = (strings[0]);
            URL obj = new URL(url);


            HttpURLConnection httpURLConnection = (HttpURLConnection) obj.openConnection();

            httpURLConnection.setRequestMethod("GET");

            int httpResponseCode = httpURLConnection.getResponseCode();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String inputLine = null;
            StringBuffer stringBuffer = new StringBuffer();

            while ((inputLine = bufferedReader.readLine()) != null) {
                stringBuffer.append(inputLine);
            }
            bufferedReader.close();
            System.out.println(stringBuffer.toString());
            responseText = stringBuffer.toString();

        } catch (MalformedURLException e) {

        } catch (ProtocolException e) {

        } catch (IOException e) {

        }
        return responseText;

    }

    public String BGhelper(String linha) {

            //linha = new HttpConnect().BGhelper("https://api.chucknorris.io/jokes/random");
            //HttpConnect H1 = new HttpConnect();

            //Json json = new Json(linha);

            //json.setText(json.getValue());


            return doInBackground(linha);
    }
}
