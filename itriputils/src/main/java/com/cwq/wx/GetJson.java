package com.cwq.wx;

/**
 * @Author:崔文青
 * @Description:
 * @Date:Created in 19:08 2019/5/22
 */

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


@Component
public class GetJson {
    public GetJson() {
    }

    public JSONObject getHttpJson(String url, int comefrom) {
        try {
            URL realUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection)realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.connect();
            if (connection.getResponseCode() == 200) {
                InputStream is = connection.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[10485760];
                boolean var8 = false;

                int len;
                while((len = is.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }

                String jsonString = baos.toString();
                baos.close();
                is.close();
                JSONObject jsonArray = this.getJsonString(jsonString, comefrom);
                return jsonArray;
            }
        } catch (MalformedURLException var11) {
            var11.printStackTrace();
        } catch (IOException var12) {
            var12.printStackTrace();
        }

        return null;
    }

    public JSONObject getJsonString(String str, int comefrom) {
        JSONObject jo = null;
        if (comefrom == 1) {
            return new JSONObject(str);
        } else if (comefrom != 2) {
            return (JSONObject)jo;
        } else {
            int indexStart = 0;

            for(int i = 0; i < str.length(); ++i) {
                if (str.charAt(i) == '(') {
                    indexStart = i;
                    break;
                }
            }

            String strNew = "";

            for(int i = indexStart + 1; i < str.length() - 1; ++i) {
                strNew = strNew + str.charAt(i);
            }

            return new JSONObject(strNew);
        }
    }
}
