package com.example.demo.filter.shrink;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/*增强ServletOutputSteam，让writer方法不把数据直接返回给浏览器*/
public class MyServletOutputStream extends ServletOutputStream {
    private ByteArrayOutputStream byteArrayOutputStream;

    public MyServletOutputStream(ByteArrayOutputStream byteArrayOutputStream) {
        this.byteArrayOutputStream = byteArrayOutputStream;
    }

    //当调用write()方法的时候，其实是把数据写byteArrayOutputSteam上
    @Override
    public void write(int b) throws IOException {
        this.byteArrayOutputStream.write(b);

    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {

    }
}
