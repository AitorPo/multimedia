package com.androidavanzado.retrof_movies.utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class Post {
    private InputStream inputStream = null;
    private String response = "";

        private String getEncodedData(Map<String, String> data){
            StringBuilder stringBuilder = new StringBuilder();
            for(String key : data.keySet()){
                String value = null;
                try {
                    value = URLEncoder.encode(data.get(key), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                if(stringBuilder.length() > 0)
                    stringBuilder.append("&");

                stringBuilder.append(key + "=" + value);
            }
            return stringBuilder.toString();
        }

        private HttpURLConnection urlConn = null;
        private OutputStreamWriter writer = null;
            private void conectaPost(Map<String, String> dataToSend, String page){
                // Preparo la cadena con las claves y valores que quiero que le llegue al Servidor
                // ?USUARIO=PEPE&CONTRASENA=1234&.........
                String encodedString = getEncodedData(dataToSend);
                // Objeto URL
                try {
                    URL url = new URL(page);
                    // Petición HTTP al WS/API
                    urlConn = (HttpURLConnection) url.openConnection();
                    // Petición al servidor por método POST
                    urlConn.setRequestMethod("POST");
                    // Habilitar conexión para que nos permita enviar datos por POST
                    urlConn.setDoOutput(true);
                    // Preparación del OutPutStream para adjuntar datos que queremos enviar al WS/API
                    writer = new OutputStreamWriter(urlConn.getOutputStream());
                    writer.write(encodedString);
                    // Cerramos el buffer de memoria que nos permite almacenar datos para enviar y recibir.
                    // En este caso hemos escrito, ahora lo vacío porque después querré leer los datos que me envíe el
                    // Servidor
                    writer.flush();
                    // Recupero un InputStream del WS/API hacia la App
                    inputStream = urlConn.getInputStream();

                } catch (IOException ioe) {
                    Log.e("log_tag", "Error in http connection " + ioe.toString());
                /* Dentro del finally pondríamos lo que queremos que se ejecute SIEMPRE, en cualquier caso */
                } finally {

                }
            }
            private void conectaGet(String page){
                HttpURLConnection urlConn = null;
                try {
                    // Objeto URL
                    URL url = new URL(page);
                    // Petición al servidor
                    urlConn = (HttpURLConnection) url.openConnection();
                    // Recuperamos el lector del Servidor hacia la App
                    inputStream = urlConn.getInputStream();
                } catch (IOException ioe) {
                    Log.e("log_tag", "Error in HTTP connection " + ioe.toString());
                } finally {

                }

            }
            private JSONArray getOnPostJSONResponse() {
                JSONArray jsonArray = null;
                try {
                    if (inputStream != null) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                        StringBuilder stringBuilder = new StringBuilder();
                        String line = null;
                        while ((line = reader.readLine()) != null) {
                            stringBuilder.append(line + "\n");
                        }
                        inputStream.close();
                        response = stringBuilder.toString();
                        jsonArray = new JSONArray(response);
                    }
                    Log.e("log_tag", "JSONArray " + response);
                } catch (IOException | JSONException ioe) {
                    Log.e("log_tag", "Error in JSONArray response" + ioe.toString());
                } finally {
                    try {
                        if (writer != null) {
                            writer.close();
                        }
                        if (urlConn != null){
                            urlConn.disconnect();
                        }
                    } catch (IOException ioe) {
                        Log.e("log_tag", "Error in HTTP connection " + ioe.toString());
                    }
                    return jsonArray;
                }
            }
    public JSONObject getJSONObjectResponse(){
        JSONObject jsonObject = null;
        if (inputStream != null){
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                StringBuilder stringBuilder = new StringBuilder();
                String line = null;
                while((line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line + "\n");
                }
                inputStream.close();
                response = stringBuilder.toString();
                //jsonArray = new JSONArray(response);
                jsonObject = new JSONObject(response);
            } catch (UnsupportedEncodingException ue) {
                Log.e("log_tag", "Error in Encodint part " + ue.toString());
            } catch (IOException ioe) {
                Log.e("log_tag", "Error in HTTP connection " + ioe.toString());
            } catch (JSONException jsone) {
                Log.e("log_tag", "Error JSON fetch " + jsone.toString());
            } finally {
                try {
                    if (writer != null)
                        writer.close();

                    if (urlConn != null)
                        urlConn.disconnect();
                } catch (IOException ioe) {
                    Log.e("log_tag", "Error in HTTP connection " + ioe.toString());
                }

            }
        }
        return jsonObject;
    }

    public JSONObject getServerDataGetObject(String URL){
                conectaGet(URL);
                return getJSONObjectResponse();
    }

    public JSONArray getServerDataPost(Map<String, String> dataToSend, String URL){
                conectaPost(dataToSend, URL);
                return getOnPostJSONResponse();
    }

    public JSONArray getServerDataGet(String URL){
                conectaGet(URL);
                return getOnPostJSONResponse();
    }
}

