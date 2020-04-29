/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：Web3jDemo2.java
 * 代码说明：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/3/5 17:31 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.springboot.test.web3j;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

/**
 * @Description: TODO
 * @Project: com.springboot.test.web3j
 * @CreateDate: Created in 2020/3/5 17:31
 * @Author: Dong.L
 **/
public class Web3jDemo2 {

    private static final String RPC_URL = "http://192.168.1.199:9001";
    private static final Web3j web3j = Web3j.build(new HttpService(RPC_URL));

    public static void main(String[] args) throws Exception {

        getAge();

    }

    public static void getAge() throws Exception {
        // 这里要填写真实的钱包地址
        EthGetBalance ethGetBalance = web3j
                .ethGetBalance("0xbcbbd4e9bbf41a149652da55c42a4b28b6e39599", DefaultBlockParameterName.LATEST).send();

        if (ethGetBalance != null) {
            System.out.println("余额:" + Convert.fromWei(ethGetBalance.getBalance().toString(), Convert.Unit.ETHER));
        }

    }
}
