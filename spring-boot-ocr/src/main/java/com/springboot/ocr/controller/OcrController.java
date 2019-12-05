package com.springboot.ocr.controller;

import com.baidu.aip.ocr.AipOcr;
import com.springboot.ocr.util.JsonChange;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Package: com.wzy.controller
 * @Author: Clarence1
 * @Date: 2019/9/14 23:41
 */
@RestController
public class OcrController {
    AipOcr client = new AipOcr("17247027", "1OBVubcETaoDCUxRCfeQeDjN", "G4hsARotv6iweCuDvozOeHaoNqXGOTRG");
    /**
     * 传入可选参数调用接口
     */
    static HashMap<String, String> options = new HashMap<String, String>(4);
    static {
        options.put("language_type", "CHN_ENG");
        options.put("detect_direction", "true");
        options.put("detect_language", "true");
        options.put("probability", "true");
    }

    @PostMapping(value = "/ocr")
    public Map<Object, Object> ocr(MultipartFile file) throws Exception {
        // 参数为二进制数组
        byte[] buf = file.getBytes();
        JSONObject res = client.basicGeneral(buf, options);

        Map map = JsonChange.json2map(res.toString());
        return map;
    }

    @PostMapping(value = "/ocrStr")
    public String ocr1(MultipartFile file) throws Exception {
        String str = "";
        String existsStr = "";
        // 参数为二进制数组
        byte[] buf = file.getBytes();
        JSONObject res = client.basicGeneral(buf, options);

        Map map = JsonChange.json2map(res.toString());
        //  提取并打印出识别的文字
        List list = (List) map.get("words_result");
        int len = ((List) map.get("words_result")).size();
        for(int i=0; i<len; i++) {
            str = str + ((Map) list.get(i)).get("words") + "\n";
            existsStr = existsStr + ((Map) list.get(i)).get("words");
        }
        System.out.println(existsStr);
        if (existsStr.indexOf("孤立无援") > 0) {
            System.out.println("ocr exists 孤立无援");
        }

        return str;
    }

}
