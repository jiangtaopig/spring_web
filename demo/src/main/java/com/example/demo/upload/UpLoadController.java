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

    /**
     * 下载，对应的是 download.jsp
     *
     */
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


    /**
     *
     * 个人信息上传，对应的是 upload_info.jsp
     *
     * 这个和上面主要的区别就是函数中的多参数，其实每一个参数都是要和前端页面的form表单input标签的内容对应(名称一致)。
     * form 表单中的 file 类型在SpringMVC的controller中就是对应MultipartFile类型，
     * form表单中的text类型对应controller中的String类型。
     * 如果上传单个文件，在服务端就用MultipartFile类型参数接收，如果多文件就用MultipartFile[]进行接收
     */
    @PostMapping("info_upload")
    public String onfile(String name, String age, MultipartFile img[], MultipartFile resume) throws IOException {
        System.out.println("name = " + name + ", age = " + age);
        //接收img[]
        for (int i = 0; i < img.length; i++) {
            if (!img[i].isEmpty())//文件不空
            {
                File imgFile = new File("D:/fileupload/" + img[i].getOriginalFilename());
                imgFile.createNewFile();
                img[i].transferTo(imgFile);
            }
        }
        //接收resume
        File resumeFile = new File("D:/fileupload/" + resume.getOriginalFilename());
        //在磁盘中创建文件，此时文件存在但没有内容
        resumeFile.createNewFile();
        //将接受的文件复制到创建的文件中
        resume.transferTo(resumeFile);
        return "sucucess";
    }
}
