package com.springboot.druid.ftp;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import sun.misc.BASE64Encoder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @Author: Dong.L
 * @Create: 2019-11-21 10:54
 * @ClassName: FtpOperationT.class
 * @Description: java类描述
 */
@Slf4j
public class FtpOperationT {

    // ftpip
    private String ip = "127.0.0.1";
    // ftp端口
    private String post = "22";
    // ftp url
    private String url = "127.0.0.1:22";
    // ftp用户
    private String userName = "admin";
    // ftp密码
    private String password = "admin";

    // 下载本地路径
    private String localDir = "d:/test/";
    // 远程路径
    private String remoteDir = "/files";

    /**
     * ftp登录初始化
     *
     * @return
     */
    private FTPClient connectFtpServer() {
        FTPClient ftpClient = new FTPClient();
        //设置连接超时时间
        ftpClient.setConnectTimeout(1000 * 30);
        //设置ftp字符集
        ftpClient.setControlEncoding("utf-8");
        //设置被动模式，文件传输端口设置
        ftpClient.enterLocalPassiveMode();
        try {
            //设置文件传输模式为二进制，可以保证传输的内容不会被改变
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.connect(url);
            ftpClient.login(userName, password);
            int replyCode = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                log.error("connect ftp {} failed", url);
                ftpClient.disconnect();
                return null;
            }
            log.info("replyCode==========={}", replyCode);
        } catch (IOException e) {
            log.error("connect fail ------->>>{}", e.getCause());
            return null;
        }
        return ftpClient;
    }

    /**
     * @param inputStream 待上传文件的输入流
     * @param originName  文件保存时的名字
     */
    public void uploadFile(InputStream inputStream, String originName) {
        FTPClient ftpClient = connectFtpServer();
        if (ftpClient == null) {
            return;
        }

        try {
            //进入到文件保存的目录
            ftpClient.changeWorkingDirectory(remoteDir);
            //保存文件
            Boolean isSuccess = ftpClient.storeFile(originName, inputStream);
            if (!isSuccess) {
                //throw new BusinessException(ResponseCode.UPLOAD_FILE_FAIL_CODE,originName+"---》上传失败！");
            }
            log.info("{}---》上传成功！", originName);
            ftpClient.logout();
        } catch (IOException e) {
            log.error("{}---》上传失败！", originName);
            //throw new BusinessException(ResponseCode.UPLOAD_FILE_FAIL_CODE,originName+"上传失败！");
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    log.error("disconnect fail ------->>>{}", e.getCause());
                }
            }
        }
    }

    /**
     * 读ftp上的文件，并将其转换成base64
     *
     * @param remoteFileName ftp服务器上的文件名
     * @return
     */
    public String readFileToBase64(String remoteFileName) {
        FTPClient ftpClient = connectFtpServer();
        if (ftpClient == null) {
            return null;
        }
        String base64 = "";
        InputStream inputStream = null;

        try {
            ftpClient.changeWorkingDirectory(remoteDir);
            FTPFile[] ftpFiles = ftpClient.listFiles(remoteDir);
            Boolean flag = false;
            //遍历当前目录下的文件，判断要读取的文件是否在当前目录下
            for (FTPFile ftpFile : ftpFiles) {
                if (ftpFile.getName().equals(remoteFileName)) {
                    flag = true;
                }
            }

            if (!flag) {
                log.error("directory：{}下没有 {}", remoteDir, remoteFileName);
                return null;
            }
            //获取待读文件输入流
            inputStream = ftpClient.retrieveFileStream(remoteDir + remoteFileName);

            //inputStream.available() 获取返回在不阻塞的情况下能读取的字节数，正常情况是文件的大小
            byte[] bytes = new byte[inputStream.available()];

            //将文件数据读到字节数组中
            inputStream.read(bytes);
            BASE64Encoder base64Encoder = new BASE64Encoder();
            //将字节数组转成base64字符串
            base64 = base64Encoder.encode(bytes);
            log.info("read file {} success", remoteFileName);
            ftpClient.logout();
        } catch (IOException e) {
            log.error("read file fail ----->>>{}", e.getCause());
            return null;
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    log.error("disconnect fail ------->>>{}", e.getCause());
                }
            }

            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.error("inputStream close fail -------- {}", e.getCause());
                }
            }
        }
        return base64;
    }

    /**
     * 文件下载
     *
     * @param remoteFileName ftp上的文件名
     * @param localFileName  本地文件名
     */
    public void download(String remoteFileName, String localFileName) {
        FTPClient ftpClient = connectFtpServer();
        if (ftpClient == null) {
            return;
        }

        OutputStream outputStream = null;

        try {
            ftpClient.changeWorkingDirectory(remoteDir);
            FTPFile[] ftpFiles = ftpClient.listFiles(remoteDir);
            Boolean flag = false;
            //遍历当前目录下的文件，判断是否存在待下载的文件
            for (FTPFile ftpFile : ftpFiles) {
                if (ftpFile.getName().equals(remoteFileName)) {
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                log.error("directory：{}下没有 {}", remoteDir, remoteFileName);
                return;
            }

            //创建文件输出流
            outputStream = new FileOutputStream(localDir + localFileName);

            //下载文件
            Boolean isSuccess = ftpClient.retrieveFile(remoteFileName, outputStream);
            if (!isSuccess) {
                log.error("download file 【{}】 fail", remoteFileName);
            }

            log.info("download file success");
            ftpClient.logout();
        } catch (IOException e) {
            log.error("download file 【{}】 fail ------->>>{}", remoteFileName, e.getCause());
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    log.error("disconnect fail ------->>>{}", e.getCause());
                }
            }

            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    log.error("outputStream close fail ------->>>{}", e.getCause());
                }
            }
        }
    }
}
