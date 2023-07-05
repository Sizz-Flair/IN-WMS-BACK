package com.wms.inwms.domain.jasper;

import com.wms.inwms.domain.returnOrder.ReturnEntity;
import com.wms.inwms.domain.returnOrder.dto.ReturnOrderDtoM;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRMapArrayDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * packageName    : com.wms.inwms.domain.jasper
 * fileName       : JasperService
 * author         : akfur
 * date           : 2023-06-29
 */
@Service
public class JasperService {
    private static Map<String, JasperReport> jasperReportMap = new HashMap<>();

    @Value("${jasper.file.path}")
    private String jasperPath;


    @PostConstruct
    private void putJasperReport() {
        try{
            jasperReportMap.put("PickingList", (JasperReport) JRLoader.loadObject(
                    new SimpleJasperReportsContext(),
                    new FileInputStream("C:\\Users\\akfur\\JaspersoftWorkspace\\MyReports\\"+"OrderReport"+".jasper")
            ));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void makePickingReport(HttpServletResponse response, List<Map> params) throws JRException, IOException {
        response.setContentType("application/pdf");
        JasperExportManager jem = JasperExportManager.getInstance(new SimpleJasperReportsContext());

        JasperPrint jp = null;

        jp = JasperFillManager.fillReport(jasperReportMap.get("PickingList"), new HashMap<>(),new JRMapArrayDataSource(params.toArray()));

        jem.exportToPdfStream(jp,response.getOutputStream());
        response.getOutputStream().flush();
    }

    public void makeCJReport() {

    }
}
