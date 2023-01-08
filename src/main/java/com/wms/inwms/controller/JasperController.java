package com.wms.inwms.controller;

import com.wms.inwms.ready.Jasper;
import net.sf.jasperreports.engine.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class JasperController {

    @PostMapping(value = "/jasper/cj")
    public void getCJJasperPrint(@RequestBody Map<String ,Object> data, HttpServletResponse response) throws IOException, JRException {
        response.setContentType("application/pdf");
        JasperExportManager jem = JasperExportManager.getInstance(new SimpleJasperReportsContext());
        JasperPrint jp = null;

        jp = JasperFillManager.fillReport(Jasper.reportMap.get("CJ"), data, new JREmptyDataSource());
        jem.exportToPdfStream(jp, response.getOutputStream());
        response.getOutputStream().flush();
    }
}
