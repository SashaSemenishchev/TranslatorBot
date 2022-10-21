package me.mrfunny.devroomtranslate.util;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class HttpUtil {
    public static String makePost(String url, Map<String, String> headers, String body) {
        try {
            HttpsURLConnection connection = (HttpsURLConnection) new URL(url).openConnection();
            for(Map.Entry<String, String> header : headers.entrySet()) {
                connection.addRequestProperty(header.getKey(), header.getValue());
            }
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);

            connection.getOutputStream().write(body.getBytes(StandardCharsets.UTF_8));
            connection.connect();
            String result = new String(connection.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            connection.getInputStream().close();
            if(connection.getResponseCode() == 200) {
                return result;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static String makeGet(String url) {
        try {
            HttpsURLConnection connection = (HttpsURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.addRequestProperty("User-Agent", "DevRoom/V3");
            connection.setDoInput(true);
            connection.connect();
            String result = new String(connection.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            connection.getInputStream().close();
            if(connection.getResponseCode() == 200) {
                return result;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
