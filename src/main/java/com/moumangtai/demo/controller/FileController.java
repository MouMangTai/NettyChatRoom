package com.moumangtai.demo.controller;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.moumangtai.demo.constant.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${server.port}")
    private String port;

    @Value("${file.ip}")
    private String ip;

    @Value("${file.path}")
    private String path;

    @PostMapping("/upload")
    @CrossOrigin
    public Result upload(@RequestParam MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        // 定义前缀
        String uuid = IdUtil.fastSimpleUUID();
        String uploadPath = null;
        // 定义路径
        if (path.equals("local")) {
            uploadPath = System.getProperty("user.dir") + "/src/main/resources/resources/file/" + uuid + "-" + fileName;
        } else {
            uploadPath = path + uuid + "-" + fileName;
        }
        // 上传
        FileUtil.writeBytes(file.getBytes(), uploadPath);
        return Result.success("http://" + ip + ":" + port + "/file/download/" + uuid); //返回结果url
    }

    /**
     * 下载文件
     *
     * @param flag
     * @param response
     */
    @GetMapping("/download/{flag}")
    @CrossOrigin
    public void download(@PathVariable String flag, HttpServletResponse response) {
        OutputStream os;  // 新建一个输出流对象
        String basePath = null;
        if(path.equals("local")){
            basePath = System.getProperty("user.dir") + "/src/main/resources/resources/file/";  // 定于文件上传的根路径
        }else{
            basePath = path ;
        }
        List<String> fileNames = FileUtil.listFileNames(basePath);  // 获取所有的文件名称
        String fileName = fileNames.stream().filter(name -> name.contains(flag)).findAny().orElse("");  // 找到跟参数一致的文件
        try {
            if (StrUtil.isNotEmpty(fileName)) {
                response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
                response.setContentType("application/octet-stream");
                byte[] bytes = FileUtil.readBytes(basePath + fileName);  // 通过文件的路径读取文件字节流
                os = response.getOutputStream();   // 通过输出流返回文件
                os.write(bytes);
                os.flush();
                os.close();
            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("文件下载失败");
        }
    }
}
