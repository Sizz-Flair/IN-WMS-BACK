package com.wms.inwms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wms.inwms.ready.Jasper;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.SimpleJasperReportsContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JasperController {
    @PostMapping({"/jasper/cj"})
    public void getCJJasperPrint(@RequestBody Map<String, Object> data, HttpServletResponse response) throws IOException, JRException {
        response.setContentType("application/pdf");
        data.put("orderDt", data.get("createdCj"));
        data.put("shipperNameEng", "");
        data.put("goodsNames", data.get("gngNumber"));
        data.put("labelRemark", "");
        data.put("pageInfo", "");
        data.put("shipperAddress", "(주) 자이언트네트워크그룹\n 인천광역시 중구 운서동 3167-7 자이언트네트워크 인천국제물류센터");
                data.put("consigneeTel", String.valueOf(data.get("consigneeTel")).replaceAll("[0-9]{4}$", "-****"));
        data.put("consigneeNameImp", String.valueOf(data.get("consigneeNameImp")).replaceAll("(?<=.{2}).", "*"));
        data.put("weight", "");
        ObjectMapper mapper = new ObjectMapper();
        Map printData = (Map)mapper.readValue(String.valueOf(data.get("printData")), Map.class);
        printData.forEach((key, value) -> data.put(String.valueOf(key), value));
        JasperExportManager jem = JasperExportManager.getInstance((JasperReportsContext)new SimpleJasperReportsContext());
        JasperPrint jp = null;
        jp = JasperFillManager.fillReport((JasperReport)Jasper.reportMap.get("CJ"), data, (JRDataSource)new JREmptyDataSource());
        jem.exportToPdfStream(jp, (OutputStream)response.getOutputStream());
        response.getOutputStream().flush();
    }
}
