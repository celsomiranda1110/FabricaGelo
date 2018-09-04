package Controle.FabricaGelo.Gerais;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Acao
{

    public final int NUMPAGINAS = 10;

    public Acao()
    {
    }

    public String executa(HttpServletRequest req, HttpServletResponse res)
        throws Exception
    {
        return "";
    }

    protected String formataData(String data)
    {
        SimpleDateFormat formatterToDate = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatterToString = new SimpleDateFormat("yyyy-MM-dd");
        String retData = "";
        Date dDate = new Date();
        try
        {
            dDate = formatterToDate.parse(data);
            retData = formatterToString.format(dDate);
        }
        catch(Exception ex)
        {
            System.out.print(ex.getMessage());
        }
        return retData;
    }
    
    protected String dataAtual()
    {
        Date dtAtual = new Date(System.currentTimeMillis());
        SimpleDateFormat formatarDate = new SimpleDateFormat("yyyy-MM-dd");
        return formatarDate.format(dtAtual);
    }
}
