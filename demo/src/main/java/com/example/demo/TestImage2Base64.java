package com.example.demo;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * base64 和 图片互转
 */
public class TestImage2Base64 {

    public static String getImageBinary(String imageFileName, String suffix) {
        BASE64Encoder encoder = new BASE64Encoder();
        File imageFile = new File(imageFileName);
        try {
            BufferedImage bufferedImage = ImageIO.read(imageFile);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, suffix, baos);
            byte[] bytes = baos.toByteArray();
            return encoder.encodeBuffer(bytes).trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String imageFileName = "D:\\download\\flutter.png";
        String imageBinary = getImgFileToBase64(imageFileName);
        System.out.println(imageBinary);
//
//        generateImage(imageBinary);

    }

    /**
     * imgFile 图片本地存储路径
     * 本地图片转base64
     */
    public static String getImgFileToBase64(String imgFile) {
        //将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        InputStream inputStream = null;
        byte[] buffer = null;
        //读取图片字节数组
        try {
            inputStream = new FileInputStream(imgFile);
            int count = 0;
            while (count == 0) {
                count = inputStream.available();
            }
            buffer = new byte[count];
            inputStream.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    // 关闭inputStream流
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        // 对字节数组Base64编码, 可以复制数据在浏览器中直接打开，要加上前缀：data:image/png;base64,
        return "data:image/png;base64," + new BASE64Encoder().encode(buffer);
    }

    /**
     * base64转图片
     * @param imgStr
     * @return
     */
    public static boolean generateImage(String imgStr) {
        if (imgStr == null) {
            // 图像数据为空
            return false;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            // 生成jpeg图片
            String imgFilePath = "D:\\download\\aa.png";// 新生成的图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
