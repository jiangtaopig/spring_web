package com.example.demo.download;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CountDownLatch;

public class FileDownloadThread extends Thread {

    private final String urlStr;
    private final File destFile;
    private int startPosition;
    private final int endPosition;
    //用于标识当前线程是否下载完成
    private final CountDownLatch countDownLatch;

    public FileDownloadThread(String urlStr, File file, int startPosition, int endPosition, CountDownLatch countDownLatch) {
        this.urlStr = urlStr;
        this.destFile = file;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            System.out.println("FileDownloadThread >> " + Thread.currentThread().getName() + " , startPosition >>> " + startPosition + " , endPosition = " + endPosition);
            int total = 0;
            long start = System.currentTimeMillis();
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5_000);
            conn.setRequestMethod("GET");
            //设置当前线程下载的起点，终点
            conn.setRequestProperty("Range", "bytes=" + startPosition + "-" + endPosition);
            //使用java中的RandomAccessFile 对文件进行随机读写操作
            int responseCode = conn.getResponseCode();
            if (206 == responseCode) {
                RandomAccessFile randomAccessFile = new RandomAccessFile(destFile, "rw");
                //设置开始写文件的位置
                randomAccessFile.seek(startPosition);
                int len;
                byte[] buffer = new byte[1024];
                InputStream is = conn.getInputStream();
                try {
                    while (startPosition <= endPosition) {
                        if ((len = is.read(buffer)) != -1) {
                            randomAccessFile.write(buffer, 0, len);
                            total += len;
                            startPosition += len;
                        } else {
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    is.close();
                    randomAccessFile.close();
                }
            }
            long time = System.currentTimeMillis() - start;
            System.out.println("FileDownloadThread >> " + Thread.currentThread().getName() + " , total >>> " + total + " , time = " + time);
            countDownLatch.countDown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
