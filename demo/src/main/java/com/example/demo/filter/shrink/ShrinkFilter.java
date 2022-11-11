package com.example.demo.filter.shrink;

import com.example.demo.filter.MyFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.zip.GZIPOutputStream;

public class ShrinkFilter implements Filter {

    protected static Logger logger = LoggerFactory.getLogger(ShrinkFilter.class);
//    private AtomicBoolean isFiltered = new AtomicBoolean(false);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        logger.info("----- ShrinkFilter init ------");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("----- ShrinkFilter doFilter ------");
//        if (isFiltered.get()) {
//            logger.info("----- ShrinkFilter already doFilter ------");
//            return;
//        }
//        isFiltered.set(true);
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        filterChain.doFilter(request, response);

//        ShrinkResponse shrinkResponse = new ShrinkResponse(response);
//        // 把被增强的response对象传递进去，目标资源调用write()方法的时候就不会直接把数据写在浏览器上了
//        filterChain.doFilter(request, shrinkResponse);
//
//        //得到目标资源想要返回给浏览器的数据
//        byte[] bytes = shrinkResponse.getBuffer();
//
//        //输出原来的大小
//        System.out.println("压缩前："+bytes.length);
//
//
//        //使用GZIP来压缩资源，再返回给浏览器
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
//        gzipOutputStream.write(bytes);
//        gzipOutputStream.flush();
//
//        //得到压缩后的数据
//        byte[] gzip = byteArrayOutputStream.toByteArray();
//
//        System.out.println("压缩后：" + gzip.length);
//
//        //还要设置头，告诉浏览器，这是压缩数据！
//        response.setHeader("content-encoding", "gzip");
//        response.setContentLength(gzip.length);
//        response.getOutputStream().write(gzip);

        logger.info("----- ShrinkFilter doFilter end ------");

    }
}
