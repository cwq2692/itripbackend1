package com.cwq.message;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
@Component
public class GetMessage {
	/**
     * 用户ID
     */
    public static final String ACCOUNT_SID = "dc9efb24bebb464888c35f529bbf3cff";

    /**
     * 密钥
     */
    public static final String AUTH_TOKEN = "e6c7c87281ca4284aded62fa4b035354";

    /**
     * 请求地址前半部分
     */
    public static final String BASE_URL = "https://api.miaodiyun.com/20150822/industrySMS/sendSMS";//请求地址是固定的不用改

    /**
     * (获取短信验证码)
     * @param to
     * @return String
     */
    public static String getResult(String to) {

        String randNum = RandUtil.getRandNum();

        //必须和秒滴官网上的模板一致
        String smsContent = "【互动科技】登录验证码："+randNum+"，如非本人操作，请忽略此短信。";


        String args = QueryUtil.queryArguments(ACCOUNT_SID, AUTH_TOKEN, smsContent, to);
        OutputStreamWriter out = null;
        InputStream in = null;
        BufferedReader br = null;
        StringBuffer sb = new StringBuffer();


        try {
            URL url = new URL(BASE_URL);
            URLConnection connection = url.openConnection(); //打开链接
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setConnectTimeout(5000);  //设置链接超时
            connection.setReadTimeout(10000);    //设置读取超时

            //提交数据
            out = new OutputStreamWriter(connection.getOutputStream(),"utf-8");
            out.write(args);
            out.flush();

            //读取返回数据
            br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            while((line = br.readLine())!=null){
                sb.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (br!=null) {
                    br.close();
                }
                if (out!=null) {
                    out.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        JSONObject jsonObject = JSONObject.fromObject(sb.toString());
        Object object = jsonObject.get("respCode");
        System.out.println(object);
        return randNum;
    }
//  测试功能
  public static void main(String[] args) {
      String result = getResult("18514250662");
      System.out.println("验证码："+result);
  }
}
