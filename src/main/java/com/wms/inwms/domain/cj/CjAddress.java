package com.wms.inwms.domain.cj;

import com.github.underscore.U;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;

@Component
public class CjAddress {
    public static JSONObject getAddress(String orderNo, String address) {
        StringBuffer content = new StringBuffer();
        JSONObject result = new JSONObject();
        OutputStreamWriter wr = null;
        InputStreamReader in = null;
        HttpURLConnection conn = null;
        try {
            URL url = new URL(null, "https://address.doortodoor.co.kr/address/address_webservice.korex");
            TrustManager[] tm = { (TrustManager)new Object() };
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, tm, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier((HostnameVerifier)new Object());
            String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webservice.address.nplus.doortodoor.co.kr/\">\t<soapenv:Header/>\t<soapenv:Body>\t\t<web:getAddressInformationByValue>\t\t\t<arg0>\t\t\t\t<!--2: 3:4:-->\t\t\t\t<boxTyp>1</boxTyp>\t\t\t\t<clntMgmCustCd>30327633</clntMgmCustCd>\t\t\t\t<clntNum>30327633</clntNum>\t\t\t\t<cntrLarcCd>01</cntrLarcCd>\t\t\t\t<fareDiv>03</fareDiv>\t\t\t\t<orderNo><![CDATA[" + orderNo + "]]></orderNo>\t\t\t\t<prngDivCd>01</prngDivCd>\t\t\t\t<rcvrAddr><![CDATA[" + address + "]]></rcvrAddr>\t\t\t\t<sndprsnAddr>197</sndprsnAddr>\t\t\t</arg0>\t\t</web:getAddressInformationByValue>\t</soapenv:Body></soapenv:Envelope>";
            conn = (HttpURLConnection)url.openConnection();
            conn.setConnectTimeout(3000);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.addRequestProperty("Content-Type", "text/xml");
            wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(xml);
            wr.flush();
            in = new InputStreamReader(conn.getInputStream(), "utf-8");
            BufferedReader br = new BufferedReader(in);
            String line;
            while ((line = br.readLine()) != null)
                content.append(line);
            String test1 = U.xmlToJson(content.toString());
            if (test1.length() != 0) {
                JSONParser parser = new JSONParser();
                JSONObject test2 = (JSONObject)parser.parse(test1);
                if (!test2.isEmpty()) {
                    JSONObject test3 = (JSONObject)test2.get("S:Envelope");
                    if (!test3.isEmpty()) {
                        JSONObject test4 = (JSONObject)test3.get("S:Body");
                        if (!test4.isEmpty()) {
                            JSONObject test5 = (JSONObject)test4.get("ns2:getAddressInformationByValueResponse");
                            if (!test5.isEmpty()) {
                                JSONObject paramJson = (JSONObject)test5.get("return");
                                if (!paramJson.isEmpty() &&
                                        paramJson.get("dlvClsfCd") != null && String.valueOf(paramJson.get("dlvClsfCd")).length() > 0) {
                                    result.put("PRINT_LABEL1", paramJson.get("dlvClsfCd"));
                                    result.put("PRINT_LABEL2", paramJson.get("dlvSubClsfCd"));
                                    result.put("PRINT_LABEL3", paramJson.get("rcvrClsfAddr"));
                                    result.put("PRINT_LABEL4", paramJson.get("dlvPreArrBranNm"));
                                    result.put("PRINT_LABEL5", paramJson.get("dlvPreArrEmpNm"));
                                    result.put("PRINT_LABEL6", paramJson.get("dlvPreArrEmpNickNm"));
                                    result.put("PRINT_LABEL7", paramJson.get("rcvrZipnum"));
                                }
                            }
                        }
                    }
                }
            }
            if (result.isEmpty())
                result = null;
        } catch (Exception e) {
            result = null;
            System.out.println(content.toString());
            System.out.println(address);
            System.out.println("KOREX Error");
            e.printStackTrace();
        } finally {
            if (in != null)
                try {
                    in.close();
                } catch (Exception exception) {}
            if (wr != null)
                try {
                    wr.close();
                } catch (Exception exception) {}
            if (conn != null)
                try {
                    conn.disconnect();
                } catch (Exception exception) {}
        }
        return result;
    }
}