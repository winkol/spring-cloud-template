/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：JsonStrParse.java
 * 代码说明：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/2/26 14:27 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.dongl.utils;

import com.dongl.utils.util.ObjectMapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @Project: com.dongl.utils
 * @CreateDate: Created in 2020/2/26 14:27
 * @Author: Dong.L
 **/
@Slf4j
@RunWith(SpringRunner.class)
@DisplayName("测试类")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JsonStrParseTest {

    @Test
    public void test() {
        String qkl = "{'code':0,'message':'','data':{'header':{'number':'6','previous_hash':'a2aa6b960d416234f87b8df1f5e3a40f4c6a6423bcd5e5a912f8e258baf0da86','data_hash':'a2b4f472e6f186ad6435d810a9af47a423ce768555c6447642b0cc2e45632ed1'},'data':{'data':[{'signature':'3045022100b1d157ad770c5552921ee8d930800beac40b5c5d077d0aea09556595bc94a69e02201be70a9db3907879d43bbdfac799a2031bab27f28df8370bfe7d9793c1f13d4b','payload':{'header':{'channel_header':{'type':3,'version':1,'timestamp':'2020-02-25T08:58:45.932Z','channel_id':'mychannel','tx_id':'8566d996e2004781561045d8eba446abebf8d56436b0f85944f4a7a6080cfa1b','epoch':'0','extension':'120612046d796363','typeString':'ENDORSER_TRANSACTION'},'signature_header':{'creator':{'Mspid':'Org1MSP','IdBytes':'-----BEGIN CERTIFICATE-----\\nMIICKjCCAc+gAwIBAgIQKM+OK73tUd9i30nAK3KarTAKBggqgRzPVQGDdTBzMQsw\\nCQYDVQQGEwJVUzETMBEGA1UECBMKQ2FsaWZvcm5pYTEWMBQGA1UEBxMNU2FuIEZy\\nYW5jaXNjbzEZMBcGA1UEChMQb3JnMS5leGFtcGxlLmNvbTEcMBoGA1UEAxMTY2Eu\\nb3JnMS5leGFtcGxlLmNvbTAeFw0yMDAyMTcxMjExMDBaFw0zMDAyMTQxMjExMDBa\\nMGsxCzAJBgNVBAYTAlVTMRMwEQYDVQQIEwpDYWxpZm9ybmlhMRYwFAYDVQQHEw1T\\nYW4gRnJhbmNpc2NvMQ4wDAYDVQQLEwVhZG1pbjEfMB0GA1UEAwwWQWRtaW5Ab3Jn\\nMS5leGFtcGxlLmNvbTBZMBMGByqGSM49AgEGCCqBHM9VAYItA0IABM71UtsvDAx0\\nI0GzDRUAgZVQ7gLN4IdwZnerPr1sVhHEK62wvH6CjHDFJP4JaPCpW2PucjuN9kvr\\nDUp0189VoKyjTTBLMA4GA1UdDwEB/wQEAwIHgDAMBgNVHRMBAf8EAjAAMCsGA1Ud\\nIwQkMCKAII8o5hwSddHVV+lKBDFGVMaRswqaSvPR2RNy7A9epTj0MAoGCCqBHM9V\\nAYN1A0kAMEYCIQCJROGn+yvFay/nH/MOBOzAj2KL0g2GaclqiCigssImFQIhAO3+\\ndjN5jL/TZZkrK2QOCjNFvxweRtUm1ap6WS9PQMQk\\n-----END CERTIFICATE-----\\n'},'nonce':'471aa96fabb4f60424d55c635b3bfe3446d5547391164b62'}},'data':{'actions':[{'header':{'creator':{'Mspid':'Org1MSP','IdBytes':'-----BEGIN CERTIFICATE-----\\nMIICKjCCAc+gAwIBAgIQKM+OK73tUd9i30nAK3KarTAKBggqgRzPVQGDdTBzMQsw\\nCQYDVQQGEwJVUzETMBEGA1UECBMKQ2FsaWZvcm5pYTEWMBQGA1UEBxMNU2FuIEZy\\nYW5jaXNjbzEZMBcGA1UEChMQb3JnMS5leGFtcGxlLmNvbTEcMBoGA1UEAxMTY2Eu\\nb3JnMS5leGFtcGxlLmNvbTAeFw0yMDAyMTcxMjExMDBaFw0zMDAyMTQxMjExMDBa\\nMGsxCzAJBgNVBAYTAlVTMRMwEQYDVQQIEwpDYWxpZm9ybmlhMRYwFAYDVQQHEw1T\\nYW4gRnJhbmNpc2NvMQ4wDAYDVQQLEwVhZG1pbjEfMB0GA1UEAwwWQWRtaW5Ab3Jn\\nMS5leGFtcGxlLmNvbTBZMBMGByqGSM49AgEGCCqBHM9VAYItA0IABM71UtsvDAx0\\nI0GzDRUAgZVQ7gLN4IdwZnerPr1sVhHEK62wvH6CjHDFJP4JaPCpW2PucjuN9kvr\\nDUp0189VoKyjTTBLMA4GA1UdDwEB/wQEAwIHgDAMBgNVHRMBAf8EAjAAMCsGA1Ud\\nIwQkMCKAII8o5hwSddHVV+lKBDFGVMaRswqaSvPR2RNy7A9epTj0MAoGCCqBHM9V\\nAYN1A0kAMEYCIQCJROGn+yvFay/nH/MOBOzAj2KL0g2GaclqiCigssImFQIhAO3+\\ndjN5jL/TZZkrK2QOCjNFvxweRtUm1ap6WS9PQMQk\\n-----END CERTIFICATE-----\\n'},'nonce':'471aa96fabb4f60424d55c635b3bfe3446d5547391164b62'},'payload':{'chaincode_proposal_payload':{'input':{'chaincode_spec':{'type':1,'typeString':'GOLANG','input':{'args':['696e766f6b65','61','62','31'],'decorations':{}},'chaincode_id':{'path':'','name':'mycc','version':''},'timeout':0}}},'action':{'proposal_response_payload':{'proposal_hash':'74d02b3622d1c03d0b381d80bdc607146db59097b8319319467abc0b2cc491f3','extension':{'results':{'data_model':0,'ns_rwset':[{'namespace':'lscc','rwset':{'reads':[{'key':'mycc','version':{'block_num':'3','tx_num':'0'}}],'range_queries_info':[],'writes':[],'metadata_writes':[]},'collection_hashed_rwset':[]},{'namespace':'mycc','rwset':{'reads':[{'key':'a','version':{'block_num':'5','tx_num':'0'}},{'key':'b','version':{'block_num':'5','tx_num':'0'}}],'range_queries_info':[],'writes':[{'key':'a','is_delete':false,'value':'88'},{'key':'b','is_delete':false,'value':'212'}],'metadata_writes':[]},'collection_hashed_rwset':[]}]},'events':{'chaincode_id':'mycc','tx_id':'8566d996e2004781561045d8eba446abebf8d56436b0f85944f4a7a6080cfa1b','event_name':'invokeSuccess','payload':'323132'},'response':{'status':200,'message':'','payload':''},'chaincode_id':{'path':'','name':'mycc','version':'1.0'}}},'endorsements':[{'endorser':{'Mspid':'Org1MSP','IdBytes':'-----BEGIN CERTIFICATE-----\\nMIICKDCCAc+gAwIBAgIRANDpjExM3CCSGsarQO+dYSUwCgYIKoEcz1UBg3UwczEL\\nMAkGA1UEBhMCVVMxEzARBgNVBAgTCkNhbGlmb3JuaWExFjAUBgNVBAcTDVNhbiBG\\ncmFuY2lzY28xGTAXBgNVBAoTEG9yZzEuZXhhbXBsZS5jb20xHDAaBgNVBAMTE2Nh\\nLm9yZzEuZXhhbXBsZS5jb20wHhcNMjAwMjE3MTIxMTAwWhcNMzAwMjE0MTIxMTAw\\nWjBqMQswCQYDVQQGEwJVUzETMBEGA1UECBMKQ2FsaWZvcm5pYTEWMBQGA1UEBxMN\\nU2FuIEZyYW5jaXNjbzENMAsGA1UECxMEcGVlcjEfMB0GA1UEAxMWcGVlcjAub3Jn\\nMS5leGFtcGxlLmNvbTBZMBMGByqGSM49AgEGCCqBHM9VAYItA0IABE/np59Ttu6a\\nWMMAerKgynU73xOwNTgaWlaMdkHLcjLcDu7/VgZCg/pRYJ8VuufTO9/2vgTNDAVC\\nvOBXaO78XfOjTTBLMA4GA1UdDwEB/wQEAwIHgDAMBgNVHRMBAf8EAjAAMCsGA1Ud\\nIwQkMCKAII8o5hwSddHVV+lKBDFGVMaRswqaSvPR2RNy7A9epTj0MAoGCCqBHM9V\\nAYN1A0cAMEQCIH1oorrr7IVV1sN2YnamNiuT1o674evcvWv1pKkp2BNTAiABdyUZ\\nvYKqyxYoo0uejn/NNz+B3fAAO5e1RMVi7kcKeQ==\\n-----END CERTIFICATE-----\\n'},'signature':'304502203f6ee058ef14102f1bfda99a87c245f159d06250e46ec7c0181d615e92183c9e0221008443cd9e08a8b2f9875ed2d1416f4d0f3a820709c9b56143ac113c52841e7fdc'},{'endorser':{'Mspid':'Org2MSP','IdBytes':'-----BEGIN CERTIFICATE-----\\nMIICKjCCAc+gAwIBAgIRAM9cfUeHb4U/PSo5nWRUItowCgYIKoEcz1UBg3UwczEL\\nMAkGA1UEBhMCVVMxEzARBgNVBAgTCkNhbGlmb3JuaWExFjAUBgNVBAcTDVNhbiBG\\ncmFuY2lzY28xGTAXBgNVBAoTEG9yZzIuZXhhbXBsZS5jb20xHDAaBgNVBAMTE2Nh\\nLm9yZzIuZXhhbXBsZS5jb20wHhcNMjAwMjE3MTIxMTAwWhcNMzAwMjE0MTIxMTAw\\nWjBqMQswCQYDVQQGEwJVUzETMBEGA1UECBMKQ2FsaWZvcm5pYTEWMBQGA1UEBxMN\\nU2FuIEZyYW5jaXNjbzENMAsGA1UECxMEcGVlcjEfMB0GA1UEAxMWcGVlcjAub3Jn\\nMi5leGFtcGxlLmNvbTBZMBMGByqGSM49AgEGCCqBHM9VAYItA0IABMxHGDEp0S/y\\nbrGSkQTnvmB8VesiZAinR9rrvBLHdcylIOZWju3mH1YqmOBvp/Qt+GUNp7/fPfg8\\nXMlu6FECFAGjTTBLMA4GA1UdDwEB/wQEAwIHgDAMBgNVHRMBAf8EAjAAMCsGA1Ud\\nIwQkMCKAIMd2YOFlIi+IEwwVOG0yYJjzsLMpufmWjYkL984rXrUgMAoGCCqBHM9V\\nAYN1A0kAMEYCIQD8eU/5AQfZI+/dMu35x+D0m3b69wKILGOYVkiwZ9jybAIhAN8q\\n5m4oEkgPJMhciw8d41ccA0Iws/2sTY+V3XuDBMfg\\n-----END CERTIFICATE-----\\n'},'signature':'30460221008ba8e2caad96b343b5eedbd95db90ef1dce2db1dbcbd05ed9de675e1a7a6d0ac022100a4c925aa6bb8f6d8e0e5a4d27fbeb1a49b7547ac816833abd653c593af219299'}]}}}]}}}]},'metadata':{'metadata':[{'value':'\\n\\\\u0002\\b\\\\u0002','signatures':[{'signature_header':{'creator':{'Mspid':'OrdererMSP','IdBytes':'-----BEGIN CERTIFICATE-----\\nMIICHzCCAcSgAwIBAgIQIErBBzd/whM+59fR7B45HzAKBggqgRzPVQGDdTBpMQsw\\nCQYDVQQGEwJVUzETMBEGA1UECBMKQ2FsaWZvcm5pYTEWMBQGA1UEBxMNU2FuIEZy\\nYW5jaXNjbzEUMBIGA1UEChMLZXhhbXBsZS5jb20xFzAVBgNVBAMTDmNhLmV4YW1w\\nbGUuY29tMB4XDTIwMDIxNzEyMTEwMFoXDTMwMDIxNDEyMTEwMFowajELMAkGA1UE\\nBhMCVVMxEzARBgNVBAgTCkNhbGlmb3JuaWExFjAUBgNVBAcTDVNhbiBGcmFuY2lz\\nY28xEDAOBgNVBAsTB29yZGVyZXIxHDAaBgNVBAMTE29yZGVyZXIuZXhhbXBsZS5j\\nb20wWTATBgcqhkjOPQIBBggqgRzPVQGCLQNCAAQgVpMedK4jB9GjZPbU2D+eqQz9\\nhFw7NIeNMKd/cXbDDGTMMr61N31OVQrqcbNue2jpw2bQeh2CS2YYPeQBXKXzo00w\\nSzAOBgNVHQ8BAf8EBAMCB4AwDAYDVR0TAQH/BAIwADArBgNVHSMEJDAigCAibCHQ\\nEeqN1L/l3LPY07gJs9/7qRFKwhle36m3iiNUUjAKBggqgRzPVQGDdQNJADBGAiEA\\n1sqs2pt3CYzgCNahd7WYHDhGo+GnLPUb07NsraBYOMoCIQCUNWtrFAJ+sr6nScZR\\nyink0xSmX0in7b+INavbhMM6ag==\\n-----END CERTIFICATE-----\\n'},'nonce':'6a4aa1cffef5101f704676af8d1995d953fa2638f89d85db'},'signature':'30440220193bd508dbf34ba6f2d29a75108df23a7927087d144ad955ea4f2fd656bd9ec0022006955c800a4513f4866e675a4759807fa7dd13851d127681056938a552f41667'}]},{'value':{'index':'2'},'signatures':[]},[0]]}}}";
        log.info("->> qkl: {}", qkl);
        Map<String, Object> one = ObjectMapperUtils.jsonToMap(qkl);
        log.info("->> one: {}", one);
        Map<String, Object> dataOne = (Map<String, Object>) one.get("data");
        log.info("->> dataOne: {}", dataOne);
        Map<String, Object> headerOne = (Map<String, Object>) dataOne.get("header");
        log.info("->> headerOne: {}", headerOne);
        Map<String, Object> dataTwo = (Map<String, Object>) dataOne.get("data");
        List<Map<String, Object>> dataGroup = (List<Map<String, Object>>) dataTwo.get("data");
        log.info("->> dataGroup: {}", dataGroup);
        for (Map<String, Object> map : dataGroup) {
            Map<String, Object> payloadOne = (Map<String, Object>) map.get("payload");
            Map<String, Object> channelHeader = (Map<String, Object>) ((Map<String, Object>) payloadOne.get("header")).get("channel_header");
            log.info("tx_id: {}", channelHeader.get("tx_id"));
            List<Map<String, Object>> actionsOne = (List<Map<String, Object>>) ((Map<String, Object>) payloadOne.get("data")).get("actions");
            for (Map<String, Object> mapT : actionsOne) {
                Map<String, Object> payloadT = (Map<String, Object>) mapT.get("payload");
                Map<String, Object> actionT = (Map<String, Object>) payloadT.get("action");
                Map<String, Object> extension = (Map<String, Object>) ((Map<String, Object>) actionT.get("proposal_response_payload")).get("extension");
                List<Map<String, Object>> nsRwsets = (List<Map<String, Object>>) ((Map<String, Object>) extension.get("results")).get("ns_rwset");
                for (Map<String, Object> mapN : nsRwsets) {
                    List<Map<String, Object>> writes = (List<Map<String, Object>>) ((Map<String, Object>)mapN.get("rwset")).get("writes");
                    for (Map<String, Object> writ : writes) {
                        log.info("->> writ: {}", writ);
                    }
                }
            }
        }
    }

}
