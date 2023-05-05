package com.example.demo.download;

import org.junit.Test;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CountDownLatch;

public class TestDownload {


    @Test
    public void test() {
        //https://img1.baidu.com/it/u=3281726704,3757046501&fm=253&fmt=auto&app=138&f=JPEG?w=753&h=500
        new DownLoadTask(
                "https://img1.baidu.com/it/u=3281726704,3757046501&fm=253&fmt=auto&app=138&f=JPEG?w=753&h=500"
                , 5, "aa.jpg").startDownload();
    }

    class DownLoadTask {
        private final int threadNum;
        private final String urlStr;
        private final String fileName;

        public DownLoadTask(String urlStr, int threadNum, String fileName) {
            this.urlStr = urlStr;
            this.threadNum = threadNum;
            this.fileName = fileName;
        }

        public void startDownload() {
            // 阻塞等待所有下载子线程执行完，统计下载总时间
            CountDownLatch countDownLatch = new CountDownLatch(threadNum);
            try {
                long startTime = System.currentTimeMillis();
                URL url = new URL(urlStr);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(5_000);
                conn.setRequestMethod("GET");
                //获取下载文件的总大小
                int fileSize = conn.getContentLength();
                //计算每个线程要下载的数据量
                int blockSize = fileSize / threadNum;

                File apkFile = new File("D:/fileupload/", fileName);
                RandomAccessFile rafAccessFile = new RandomAccessFile(apkFile, "rw");
                rafAccessFile.setLength(fileSize);
                rafAccessFile.close();
                conn.disconnect();
                for (int i = 0; i < threadNum; i++) {
                    //启动线程，分别下载自己需要下载的部分
                    int startPos = i * blockSize;
                    int endPos = (i + 1) * blockSize - 1;
                    if (i == threadNum - 1) { // 最后一个线程下载剩下的部分
                        endPos = fileSize -1;
                    }
                    FileDownloadThread fdt = new FileDownloadThread(urlStr, apkFile,
                            startPos, endPos, countDownLatch);
                    fdt.setName("Thread" + i);
                    fdt.start();
                }
                countDownLatch.await();
                long end = System.currentTimeMillis();
                System.out.println("下载完成, 耗时 >>> " + (end - startTime));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
