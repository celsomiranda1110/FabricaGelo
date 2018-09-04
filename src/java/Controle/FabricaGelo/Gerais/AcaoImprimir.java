package Controle.FabricaGelo.Gerais;

import java.io.InputStream;

import java.util.*;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


import Bean.Colaborador;

// Referenced classes of package Controle.Estoque.Gerais:
//            Acao

public class AcaoImprimir extends Acao
{



    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {

        HttpSession sessao = req.getSession(false);
        InputStream inputStream = null;
        InputStream inputStreamS = null;
        JasperReport report = null;
        JasperReport subReport = null;
        Map parametros = null;
        String caminhoRelatorio = "";
        String caminhoSubRelatorio = "";
        
        try
        {
            Colaborador empresa = (Colaborador)sessao.getAttribute("empresa");
            caminhoRelatorio = (String)sessao.getAttribute("nomeRelatorio");
            caminhoSubRelatorio = (String)sessao.getAttribute("nomeSubRelatorio");
            
            List lstImpressao = (ArrayList)sessao.getAttribute("lstImpressao");
            
            res.setContentType("application/pdf");
            ServletOutputStream ouputStream = res.getOutputStream();
            
            inputStream = getClass().getResourceAsStream(caminhoRelatorio);
            report = JasperCompileManager.compileReport(inputStream);

            
            
            parametros = new HashMap();
            if (caminhoSubRelatorio != "")
            {
                inputStreamS = getClass().getResourceAsStream(caminhoSubRelatorio);
                subReport = JasperCompileManager.compileReport(inputStreamS);
                parametros.put("subRelatorio", subReport);
                //parametros.put("subRelatorio", inputStreamS);
            }
            parametros.put("empresa", empresa);
            
            JasperPrint print = JasperFillManager.fillReport(report, parametros, new JRBeanCollectionDataSource(lstImpressao));
            //JasperPrint print = JasperFillManager.fillReport(inputStream, parametros, new JRBeanCollectionDataSource(lstImpressao));
            
// código anterior
//            JRExporter exporter = new JRPdfExporter();
//            //JasperExportManager.exportReportToPdf(print);
//            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
//            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
//            exporter.exportReport();
            
// código novo            
            byte[] bytes = JasperExportManager.exportReportToPdf(print);
            if (bytes != null && bytes.length > 0) {  
//                response.setContentType("application/pdf");  
//                response.setContentLength(bytes.length);  
//                ServletOutputStream ouputStream = response.getOutputStream();  
                ouputStream.write(bytes, 0, bytes.length);  
                ouputStream.flush();  
                ouputStream.close();  
            }             
        }
        catch (JRException exc)
        {
            exc.printStackTrace();
        }  
        catch (Exception e) 
        {                  
            e.printStackTrace();  
        }          
        return "";
    }
}
