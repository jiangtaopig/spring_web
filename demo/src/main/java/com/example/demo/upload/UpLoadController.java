package com.example.demo.upload;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class UpLoadController {

    /**
     * @param zjt_file 一定要和 upload.jsp 中的  <input type="file" name="zjt_file" ><br> 中的 name 的值保持一致
     *                 否则会报错
     * @throws IOException
     */
    @PostMapping("onfile")
    public String onfile(MultipartFile zjt_file) throws IOException {
        System.out.println("UpLoadController ------ onfile fileName is : " + zjt_file.getOriginalFilename());
        // 本案例就是将上传的文件保存到本地 D 盘
        File file1 = new File("D:/fileupload/" + zjt_file.getOriginalFilename());//创建file对象
        if (!file1.exists())
            file1.createNewFile();//在磁盘创建该文件
        zjt_file.transferTo(file1);//将接受的文件存储
        return "success";
    }

    @PostMapping("multi_upload")
    public String multiUpload(MultipartFile img[]) throws IOException {
        for (MultipartFile multipartFile : img) {
            if (!multipartFile.isEmpty()) {
                File imgFile = new File("D:/fileupload/" + multipartFile.getOriginalFilename());
                imgFile.createNewFile();
                multipartFile.transferTo(imgFile);
                System.out.println("" + multipartFile.getOriginalFilename());
            }
        }
        return "success";
    }

    // filename 后面加上 ":.+" 是为了不过滤掉后缀，不然传 zjt.png，过滤掉后缀后只剩下 zjt 了。
    @GetMapping("/my_download/{filename:.+}")
    public ResponseEntity<byte[]> download(@PathVariable String filename) throws IOException {
        System.out.println("download ..... filename = " + filename);
        //下载文件的路径(这里绝对路径)
        String filepath = "D:/download/" + filename;
        File file = new File(filepath);
        //创建字节输入流，这里不实用Buffer类
        InputStream in = new FileInputStream(file);
        //available:获取输入流所读取的文件的最大字节数
        byte[] body = new byte[in.available()];
        //把字节读取到数组中
        in.read(body);
        //设置请求头
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("Content-Disposition", "attchement;filename=" + file.getName());
        //设置响应状态
        HttpStatus statusCode = HttpStatus.OK;
        in.close();
        ResponseEntity<byte[]> entity = new ResponseEntity<>(body, headers, statusCode);
        return entity;//返回
    }
}
