package com.springboot.druid.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: Dong.L
 * @Create: 2019-11-21 10:17
 * @ClassName: FtpPool.class
 * @Description: 创建FtpPool连接池
 * FTP连接池
 * 1.可以获取池中空闲链接
 * 2.可以将链接归还到池中
 * 3.当池中空闲链接不足时，可以创建链接
 */
@Component
public class FtpPool {
    FtpClientFactory factory;
    private final GenericObjectPool<FTPClient> internalPool;

    /**
     * @param:
     * @return:
     * @Author:  Dong.L
     * @Date:    2019/11/21 10:19
     * @Description: 初始化连接池
     */
    public FtpPool(@Autowired FtpClientFactory factory) {
        this.factory = factory;
        FtpConfig config = factory.getConfig();
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxTotal(config.getMaxTotal());
        poolConfig.setMinIdle(config.getMinIdel());
        poolConfig.setMaxIdle(config.getMaxIdle());
        poolConfig.setMaxWaitMillis(config.getMaxWaitMillis());
        this.internalPool = new GenericObjectPool<FTPClient>(factory, poolConfig);
    }

    /**
     * @param:
     * @return:
     * @Author:  Dong.L
     * @Date:    2019/11/21 10:22
     * @Description: 从连接池中取连接
     */
    public FTPClient getFTPClient() {
        try {
            return internalPool.borrowObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param:
     * @return:
     * @Author:  Dong.L
     * @Date:    2019/11/21 10:19
     * @Description: 将链接归还到连接池
     */
    public void returnFTPClient(FTPClient ftpClient) {
        try {
            internalPool.returnObject(ftpClient);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 销毁池子
     */
    public void destroy() {
        try {
            internalPool.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
