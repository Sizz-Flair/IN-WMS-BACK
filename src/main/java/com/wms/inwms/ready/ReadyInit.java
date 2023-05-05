package com.wms.inwms.ready;

import com.wms.Prop;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.SimpleJasperReportsContext;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.sql.Timestamp;

@Component
public class ReadyInit implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("┌───────────────────────────	Ready Init Start	───────────────────────────");
        System.out.println("├ Country init start at "+new Timestamp(System.currentTimeMillis()));
        System.out.println("├ Country init finished at "+new Timestamp(System.currentTimeMillis()));
        System.out.println("├──────────────────────────────────────────────────────────────────────────────────");
        System.out.println("├ Jasper init start at "+new Timestamp(System.currentTimeMillis()));
        initJasper();
        System.out.println("├ Jasper init finished at "+new Timestamp(System.currentTimeMillis()));
        System.out.println("└───────────────────────────	Ready Init Finished	───────────────────────────");
    }

    public static void initJasper() throws Exception {
        try {
            JasperReportsContext jrc = new SimpleJasperReportsContext();
            String basePath = Prop.getProp("jasper.input.label");

            //Jasper.reportMap.put("CJ", (JasperReport) JRLoader.loadObject(jrc, new FileInputStream("D:\\\\dev\\\\ggate_spring\\\\src\\\\main\\\\resources\\\\jasper\\\\" + "CJ.jasper")));
            Jasper.reportMap.entrySet().stream().forEach(e -> {
                System.out.println("├ Jasper ->" + e.getKey());
            });
            System.out.println("├ Jasper template written on memory.");

        } catch(Exception e) {
            System.err.println("├ Error : "+e.getMessage());
        }
    }
}
