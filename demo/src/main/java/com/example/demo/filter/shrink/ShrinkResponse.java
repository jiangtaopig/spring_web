package com.example.demo.filter.shrink;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class ShrinkResponse extends HttpServletResponseWrapper {

    private ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    private PrintWriter printWriter ;

    private HttpServletResponse response;

    public ShrinkResponse(HttpServletResponse response) {
        super(response);
        this.response = response;
    }


    @Override
    public ServletOutputStream getOutputStream() throws IOException {

        //这个的ServletOutputSteam对象调用write()方法的时候，把数据是写在byteArrayOutputSteam上的
        return new MyServletOutputStream(byteArrayOutputStream);
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        printWriter = new PrintWriter(new OutputStreamWriter(byteArrayOutputStream, this.response.getCharacterEncoding()));

        return printWriter;
    }

    public byte[] getBuffer() {
        try {
            //防止数据在缓存中，要刷新一下！
            if (printWriter != null) {
                printWriter.close();
            }
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.flush();
                return byteArrayOutputStream.toByteArray();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
