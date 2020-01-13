/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：SignatureDataUtils.java
 * 代码说明：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/1/13 15:46 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.dongl.utils.util;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;

/**
 * @Description: 编写发送者的功能：首先通过私钥加密待输出数据Data，并输出Data和签名后的Data
 * @Project: com.dongl.utils.util
 * @CreateDate: Created in 2020/1/13 15:46
 * @Author: Dong.L
 **/
public class SignatureDataUtils {
    public static String run(String prikeyvalue) {
        try {
            // 这是GenerateKeyPair输出的私钥编码
            // String prikeyvalue =
            // "30820278020100300d06092a864886f70d0101010500048202623082025e020100028181009471a0dd33eda4d5ab55966c99ec49d782508de7178ecd72e2f680fc1d9c14015f842489009a9f5ec158d228e881a793bdd429696b7344f286a64e1645857d5c8df2c6fb6e99325d9c8ac2d58cd21685806cb2adf4adc446169ca426d5bc290fc77681804b7e5b5d334bca44a85ea2fc46919c8d6cd11c5c059b7967fe03d3a1020301000102818100898754d05c01fa4b73b791ec001768ba5fa39e34d2209dbba90754ad400990874d25326c33c10d924e73aa11f887d6e80c617a148f5676347407b424f23820d1a8b467b0e47ef678c0b4c7a4f656913c3450996670e0430b5ae056abeb4cb775062b3400a0d3da94b5680919b0e8e46ffc4d9dbc4164a6bd58bf5152828f1899024100df2520bcd7bbf8f5af5314066f74a2a0f9c8502808036284ea7e7a31ae3028df4c11090a9293cb89ec83d97c26aefd61caa8a29c1db74033ff5d6f596a4b2a7b024100aa4cd580b509364dd4ecf85743c4af2b5c3717e89dcd73e0a2510140d3c2766be82a4ed465e9f2e0edbb418cd6b532bbb68d031bf4978b7319b5c33784579d93024100cfd713211187c1a184c5cad71ba4f57d1e6a574e91f825214c10b5dbe434733d58ea5d137de72f23ae2a38be0c81dcfbe2f9234c69d92f71bf1ed601e0a1565502410095b995a0620a265f498bca4f56ba8ed38d70b6a9824bc6cc4188fc9415598c2a14e5558731cefd05ba9d7ee5274409c5b59ac6980674525b30c87848d02703d702400c54e66a101041e6e56e8afbae7769645aa1445dd1565cb2e470f420e59bfad42e124a9f3d2bdafb668ce7f13c5d590ee13aeb56f9dcaa1f62b394b5801d2832";
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(hexStrToBytes(prikeyvalue));
            KeyFactory keyf = KeyFactory.getInstance("RSA");
            PrivateKey myprikey = keyf.generatePrivate(priPKCS8);

            // 要签名的信息
            String myinfo = "orderId=10dkfadsfksdkssdkd&amount=80&orderTime=20060509";
            // 用私钥对信息生成数字签名
            Signature signet = Signature.getInstance("MD5withRSA");
            signet.initSign(myprikey);
            signet.update(myinfo.getBytes("ISO-8859-1"));
            // 对信息的数字签名
            byte[] signed = signet.sign();

            String sign = bytesToHexStr(signed);
            System.out.println("signed(签名内容)原值=" + sign);
            System.out.println("info（原值）=" + myinfo);

            System.out.println("签名并生成文件成功");
            return sign;
        } catch (java.lang.Exception e) {
            e.printStackTrace();
            System.out.println("签名并生成文件失败");
        }
        return null;
    }

    /**
     * Transform the specified byte into a Hex String form.
     */
    public static final String bytesToHexStr(byte[] bcd) {
        StringBuffer s = new StringBuffer(bcd.length * 2);

        for (int i = 0; i < bcd.length; i++) {
            s.append(bcdLookup[(bcd[i] >>> 4) & 0x0f]);
            s.append(bcdLookup[bcd[i] & 0x0f]);
        }

        return s.toString();
    }

    /**
     * Transform the specified Hex String into a byte array.
     */
    public static final byte[] hexStrToBytes(String s) {
        byte[] bytes;

        bytes = new byte[s.length() / 2];

        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(s.substring(2 * i, 2 * i + 2), 16);
        }

        return bytes;
    }

    private static final char[] bcdLookup = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
            'f'};
}
