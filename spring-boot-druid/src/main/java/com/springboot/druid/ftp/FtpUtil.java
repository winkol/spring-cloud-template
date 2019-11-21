package com.springboot.druid.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

/**
 * @Author: Dong.L
 * @Create: 2019-11-21 10:23
 * @ClassName: 创建FtpUtil类-FTP工具类
 * @Description: 提供文件上传和下载
 */
@Component
public class FtpUtil {
    @Autowired
    FtpConfig config;
    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    FtpPool pool;

    /**
     * @param: file 上传到FTP服务器上的文件
     * @return: 成功返回文件名，否则返回null
     * @exception:
     * @Author: Dong.L
     * @Date: 2019/11/21 10:35
     * @Description: 向FTP服务器上传文件
     */
    public String upload(MultipartFile file) throws Exception {
        FTPClient ftpClient = pool.getFTPClient();
        //开始进行文件上传
        String fileName =
                UUID.randomUUID() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        InputStream input = file.getInputStream();
        try {
            //执行文件传输
            boolean result = ftpClient.storeFile(fileName, input);
            //上传失败
            if (!result) {
                throw new RuntimeException("上传失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {//关闭资源
            input.close();
            System.out.println("开始归还连接");
            //归还资源
            pool.returnFTPClient(ftpClient);
        }
        return fileName;
    }

    /**
     * @param: fileName FTP服务器中的文件名
     * @param: resp     响应客户的响应体
     * @return: void
     * @exception: IOException
     * @Author: Dong.L
     * @Date: 2019/11/21 10:37
     * @Description: 从FTP服务器下载文件
     */
    public void downLoad(String fileName, HttpServletResponse resp) throws IOException {
        FTPClient ftpClient = pool.getFTPClient();
        // 设置强制下载不打开 MIME
        resp.setContentType("application/force-download");
        // 设置文件名
        resp.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
        //将文件直接读取到响应体中
        OutputStream out = resp.getOutputStream();
        ftpClient.retrieveFile(config.getWorkDir() + "/" + fileName, out);
        out.flush();
        out.close();
        pool.returnFTPClient(ftpClient);
    }

    /**
     * @param: fileName 需要读取的文件名
     * @return: 返回文件对应的Entity
     * @exception:
     * @Author: Dong.L
     * @Date: 2019/11/21 10:38
     * @Description: 从FTP服务器读取图片
     */
    public ResponseEntity show(String fileName) {
        String username = config.getUserName();
        String password = config.getPassWord();
        String host = config.getHost();
        String work = config.getWorkDir();
        //ftp://root:root@192.168.xx.xx/+fileName
        return ResponseEntity.ok(resourceLoader.getResource("ftp://" + username + ":" + password + "@" + host + work + "/" + fileName));
    }
}
