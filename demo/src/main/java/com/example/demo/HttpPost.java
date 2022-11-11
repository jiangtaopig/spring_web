package com.example.demo;

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
        params.put("imgUrl",
                "https://insaic-ocr-test.oss-cn-shanghai-finance-1-pub.aliyuncs.com/ocr/aliyun/2022/11/10/XFile1668072194750191.jpg?Expires=1668075795&OSSAccessKeyId=STS.NSzbvQV7UP3k9A4LGp8BM4L9e&Signature=j3Pm1kDKfHrI9emd7qp9huzF538%3D&security-token=CAISjgJ1q6Ft5B2yfSjIr5DPKczlu%2Bh05%2FGAO2eFqEclNM1h24mShzz2IHpIe3FhBO8dtPUymWBY6%2FkclrBzWpVfRECBatBrq51M6h6kbs%2Fatteu7LsC0F4qs4g9U0yV5tTbRsmkZvG%2FE67fRjKpvyt3xqSABlfGdle5MJqPpId6Z9AMJGeRZiZHA9EkTWkL6rVtVx3rOO2qLwThj0fJEUNsoXAcs25k7rmlycDugXi3zn%2BCk7JN%2Fdmgfcj8Mpc3ZM8lCO3YhrImKvDztwdL8AVP%2BatMi6hJxCzKpNn1ASMKuUnZa7uJrYY1fVIkNvhnRPBe0v%2Fnjrh5vPfalo%2ByzB1XeP1YSDiaXp%2Bwb2dr8yYSi7wagAFXxXDbXzQcGJZFUF2IwYjPMmorRfILy2T0jYoSanVbECOLuw6If4530PPZUXG0SnCHhDoSQCRwwKd82K%2BvWlKFBw4biOmMZIT0WbqSS5KIpBN8q0jKHbg2rqsUnluOX2yhK06IuSJaDjYoVNySQ8u%2Bfc%2Bf8sHALkMl4jivDh7x1g%3D%3D"
        );

        doPost(url, params);
    }

    public static String doPost(String httpUrl, Map<String, Object> params) {
        String result = null;
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
                Iterator iterator = keySet.iterator();
                while (iterator.hasNext()) {
                    String key = (String) iterator.next();
                    Object value = (Object) params.get(key);
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
                StringBuffer stringBuffer1 = new StringBuffer();
                String line = null;
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
        return result;
    }

}
