package com.example.demo;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HttpPost {


    public static void main(String[] args) {
        // 阿里云的合格证验证
        String url = "https://vehiclec.market.alicloudapi.com/s/api/ocr/vehicleCertificate";
        Map<String, Object> params = new HashMap<>();
        params.put("imgUrl",""
        );

        doPost(url, params);
    }

    public static void doPost(String httpUrl, Map<String, Object> params) {
        String result;
        //try..catch外声明资源,方便finally统一关闭资源
        HttpURLConnection httpURLConnection = null;
        OutputStream os = null;
        DataOutputStream out = null;
        InputStream is = null;
        BufferedReader br = null;
        try {
            URL url = new URL(httpUrl);
            //通过指定url开启一个连接
            httpURLConnection = (HttpURLConnection) url.openConnection();
            //声明发送POST请求类型
            httpURLConnection.setRequestMethod("POST");
            //注意:当向服务器传送数据时,setDoOutput方法要设置成true.默认为false
            httpURLConnection.setDoOutput(true);
            // 设置请求报头信息
            httpURLConnection.setRequestProperty("Authorization", "APPCODE " + "37b7c381ad824535bd479819c87183c8");
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            //开启连接
            //httpURLConnection.connect();
            //获取连接对象的字符输出流对象
            // 【注意此处,getOutputStream方法会自动的进行connect连接.
            //  所以不需要手动编写httpURLConnection.connect();】
            out = new DataOutputStream(httpURLConnection.getOutputStream());
            StringBuffer stringBuffer = new StringBuffer();
            //判断是否携带参数
            if (!params.isEmpty()) {
                Set<String> keySet = params.keySet();
                for (String key : keySet) {
                    Object value = params.get(key);
                    if (!stringBuffer.toString().equals("")) {
                        stringBuffer.append("&");
                    }
                    stringBuffer.append(key);
                    stringBuffer.append("=");
                    stringBuffer.append(URLEncoder.encode(value.toString(), "UTF-8"));
                }
            }
            //将参数写入httpURLConnection对象的输出流
            out.writeBytes(stringBuffer.toString());

            //HTTP：200状态码表示请求已成功
            System.out.println("---------cccccccccccccc---------- statusCode = " + httpURLConnection.getResponseCode());
            if (httpURLConnection.getResponseCode() == 200) {
                //请求成功后,响应值可从httpURLConnection对象的输入流中读取
                is = httpURLConnection.getInputStream();
                //缓冲流包装字符输入流,放入内存中,读取效率更快
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                StringBuilder stringBuffer1 = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    //将每次读取的行进行保存
                    stringBuffer1.append(line);
                    stringBuffer1.append("\r\n");
                }
                result = stringBuffer1.toString();
                System.out.println("result = " + result);
            }
        } catch (Exception e) {
            System.out.println("-----------------");
            e.printStackTrace();
        } finally {
            try {
                //依次关闭资源
                if (br != null) {
                    br.close();
                }
                if (is != null) {
                    is.close();
                }
                if (out != null) {
                    out.close();
                }
                if (os != null) {
                    os.close();
                }
                httpURLConnection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testPost() {
        sendErrMsg("测试错误2zhu");
    }

    private void sendErrMsg(String msg){
        System.out.println(msg);
        try {
            URL url = new URL("http://xxxx/app/business/appErrSave");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            connection.setRequestMethod("POST");
            //连接超时
            connection.setConnectTimeout(8000);
            connection.setReadTimeout(8000);
            connection.setDoOutput(true);
            //连接打开输出流
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());;

            JSONObject json = new JSONObject();
            json.put("errMsg", msg);
            json.put("taskId", "016");
            // 解决入参为中文导致的 400 , 下面的 writeBytes 就会导致400
//            out.writeBytes(json.toJSONString());
            out.write(json.toJSONString().getBytes("utf-8"));
            System.out.println("code = " + connection.getResponseCode());
            if (connection.getResponseCode() == 200) {
                //接收服务器输入流信息
                InputStream is = connection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                //拿到信息
                String  result = br.readLine();
                System.out.println("result >>> " + result);
                is.close();
            }
            out.close();
            connection.disconnect();
        } catch (IOException | JSONException ioException) {
            ioException.printStackTrace();
        }
    }

}
