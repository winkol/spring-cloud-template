/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：WalletController.java
 * 代码说明：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/3/17 17:38 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.springboot.test.web3j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;

/**
 * @Description: TODO
 * @Project: com.springboot.test.web3j
 * @CreateDate: Created in 2020/3/17 17:38
 * @Author: Dong.L
 **/
@RestController
@RequestMapping("/eth")
public class WalletController {
    @Autowired
    private Web3j web3j;


    @GetMapping("/height")
    public long getHeight() {
        System.out.println("/height..................");
        try {
            EthBlockNumber blockNumber = web3j.ethBlockNumber().send();
            long blockHeight = blockNumber.getBlockNumber().longValue();
            System.out.println("blockHeight:" + blockHeight);

            String addr = "0x5724df305f4a6709696ac3cd614fc29ee18e970b";
            EthGetTransactionCount getNonce = web3j.ethGetTransactionCount(addr, DefaultBlockParameterName.PENDING).send();
            if (getNonce == null){
                System.out.println("net error");
            }
            System.out.println("TransactionCount: " + getNonce.getTransactionCount());

            return blockHeight;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

}
