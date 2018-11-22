package com.ftp.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@RestController("fileController.spr")
public class UploadController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(params = "method=ftpUpload")
    @ResponseBody
    public Map fileUpload(@RequestParam("fileName") MultipartFile file) throws IOException {
        //@RequestParam("fileName") CommonsMultipartFile file
//        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
//        MultipartFile file = multipartRequest.getFile("fileName");
        String fileName = file.getOriginalFilename();
        InputStream inputStream = file.getInputStream();
        String msg;
        String ip = null;
        int port = 0;
        String username = null;
        String password = null;
        String path = null;

        System.out.println(fileName);
        FTPClient ftp = new FTPClient();
        try {
            try {
                ftp.connect(ip, port);
            } catch (Exception e) {
            }
            ftp.login(username, password);
            int reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
            }
            //设置文件上传类型为二进制流
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            if (!ftp.changeWorkingDirectory(path)) {
                ftp.makeDirectory(path);
                ftp.changeWorkingDirectory(path);
            }
            boolean n = ftp.storeFile(fileName, inputStream);
            if (!n) {
                ftp.disconnect();
            }
            inputStream.close();
            ftp.logout();
            msg = ftp.getReplyString();
        } catch (IOException e) {
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return null;
    }

}
