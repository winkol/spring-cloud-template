package com.springboot.druid.ftp;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author: Dong.L
 * @Create: 2019-11-21 10:10
 * @ClassName: FtpClientFactory.class
 * @Description: 创建FtpClientFactory工厂类
 */
@Component
public class FtpClientFactory implements PooledObjectFactory<FTPClient> {
    @Autowired
    FtpConfig config;

    /**
     * @param: []
     * @return: org.apache.commons.pool2.PooledObject<org.apache.commons.net.ftp.FTPClient>
     * @Author: Dong.L
     * @Date: 2019/11/21 10:16
     * @Description: 创建连接到池中
     */
    @Override
    public PooledObject<FTPClient> makeObject() {
        //创建客户端实例
        FTPClient ftpClient = new FTPClient();
        return new DefaultPooledObject<>(ftpClient);
    }

    /**
     * @param: [pooledObject]
     * @return: void
     * @Author: Dong.L
     * @Date: 2019/11/21 10:14
     * @Description: 销毁连接，当连接池空闲数量达到上限时，调用此方法销毁连接
     */
    @Override
    public void destroyObject(PooledObject<FTPClient> pooledObject) {
        FTPClient ftpClient = pooledObject.getObject();
        try {
            ftpClient.logout();
            if (ftpClient.isConnected()) {
                ftpClient.disconnect();
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not disconnect from server.", e);
        }
    }

    /**
     * @param: [pooledObject]
     * @return: boolean
     * @Author: Dong.L
     * @Date: 2019/11/21 10:14
     * @Description: 链接状态检查
     */
    @Override
    public boolean validateObject(PooledObject<FTPClient> pooledObject) {
        FTPClient ftpClient = pooledObject.getObject();
        try {
            return ftpClient.sendNoOp();
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * @param: [pooledObject]
     * @return: void
     * @Author: Dong.L
     * @Date: 2019/11/21 10:15
     * @Description: 初始化连接
     */
    @Override
    public void activateObject(PooledObject<FTPClient> pooledObject) throws Exception {
        FTPClient ftpClient = pooledObject.getObject();
        ftpClient.connect(config.getHost(), config.getPort());
        ftpClient.login(config.getUserName(), config.getPassWord());
        ftpClient.setControlEncoding(config.getEncoding());
        ftpClient.changeWorkingDirectory(config.getWorkDir());
        //设置上传文件类型为二进制，否则将无法打开文件
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
    }

    /**
     * @param: [pooledObject]
     * @return: void
     * @Author: Dong.L
     * @Date: 2019/11/21 10:15
     * @Description: 钝化连接，使链接变为可用状态
     */
    @Override
    public void passivateObject(PooledObject<FTPClient> pooledObject) throws Exception {
        FTPClient ftpClient = pooledObject.getObject();
        try {
            ftpClient.changeWorkingDirectory(config.getRoot());
            ftpClient.logout();
            if (ftpClient.isConnected()) {
                ftpClient.disconnect();
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not disconnect from server.", e);
        }
    }

    /**
     * @Author: Dong.L
     * @Date: 2019/11/21 10:15
     * @Description: 用于连接池中获取pool属性
     */
    public FtpConfig getConfig() {
        return config;
    }
}
