package DAO;


import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

// Referenced classes of package DAO:
//            ComSql

public class DAO
{

    protected String descPesquisa;
    protected String dataInicial = "";
    protected String dataFinal = "";
    protected int campoInteger = 0;
    protected int idEmpresa = 0;
    protected int tipoPagamento = 0;
    protected String comSql = "";
    protected Connection conexao;
    protected String storeProcedure = "";
    protected List lstParametros;
    protected int identity = 0;

    public DAO(Connection conexao)
    {
        descPesquisa = "";
        dataInicial = "";
        dataFinal = "";
        campoInteger = Integer.valueOf(0);
        comSql = "";
        storeProcedure = "";
        lstParametros = new ArrayList();
        identity = 0;
        
        this.conexao = conexao;
    }

    public void setDescPesquisa(String descPesquisa)
    {
        this.descPesquisa = descPesquisa;
    }

    public void setDataFinal(String dataFinal)
    {
        this.dataFinal = dataFinal;
//        String formatoL = "dd/MM/yyyy";
//        String formatoE = "yyyy-MM-dd";
//        DateFormat formatL = new SimpleDateFormat(formatoL);
//        DateFormat formatE = new SimpleDateFormat(formatoE); 
//       
//        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
//       
//        if(dataFinal != null)
//        {
//            try {
//                this.dataFinal = formatE.format( (Date) formatL.parse( dataFinal ) );
//            } catch (ParseException ex) {
//                
//            }
//        } else
//        {
//            this.dataFinal = "";
//        }
    }

    public void setDataInicial(String dataInicial)
    {
        this.dataInicial = dataInicial;
//        String formatoL = "dd/MM/yyyy";
//        String formatoE = "yyyy-MM-dd";
//        DateFormat formatL = new SimpleDateFormat(formatoL);
//        DateFormat formatE = new SimpleDateFormat(formatoE); 
//       
//        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
//        
//        if(dataInicial != null)
//        {
//            try {
//                this.dataInicial = formatE.format( (Date) formatL.parse( dataInicial ) );
//            } catch (ParseException ex) {
//                
//            }
//        } else
//        {
//            this.dataInicial = "";
//        }
    }

    public void setCampoInteger(Integer campoInteger)
    {
        this.campoInteger = campoInteger;
    }

    public void setIdEmpresa(Integer idEmpresa)
    {
        this.idEmpresa = idEmpresa;
    }

    public void setTipoPagamento (int tipoPagamento)
    {
        this.tipoPagamento = tipoPagamento;
    }
    
    public boolean atualizar()
    {
        ComSql comandoSql = new ComSql(conexao);
        boolean retorno = comandoSql.atualizar(comSql);
        return retorno;
    }

    public List listaTodos()
    {
        ComSql comandoSql = new ComSql(conexao);
        List lstRetorno = comandoSql.listaTodos(comSql);
        return lstRetorno;
    }

    public List listaUm()
    {
        ComSql comandoSql = new ComSql(conexao);
        List lstRetorno = comandoSql.listaUm(comSql);
        return lstRetorno;
    }

    public boolean execAtualizar()
    {
        ComSql comandoSql = new ComSql(conexao);
        boolean retorno = comandoSql.execAtualizar(storeProcedure, lstParametros);
        identity = comandoSql.getIdentity();
        lstParametros = null;
        lstParametros = new ArrayList();
        return retorno;
    }

    public boolean execExcluir()
    {
        ComSql comandoSql = new ComSql(conexao);
        boolean retorno = comandoSql.execExcluir(storeProcedure, lstParametros);
        return retorno;
    }

    public List execLista()
    {
        ComSql comandoSql = new ComSql(conexao);
        List lstRetorno = comandoSql.lista(storeProcedure, lstParametros);
        lstParametros = null;
        lstParametros = new ArrayList();
        return lstRetorno;
    }

    public int getIdentity()
    {
        return identity;
    }
    
    public Date addData(Date dat, String param, int qt)
    {
//            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//            Calendar dataCal = Calendar.getInstance();
//            
//            String retorno = "1900-01-01";
//            
//            try {
//                
//                Date dataP = (Date)sdf.parse(dat);
//                dataCal.setTime(dataP);
//                
//                if (param.equals("DIA"))
//                    dataCal.add(Calendar.DAY_OF_MONTH, qt);
//                else if (param.equals("MÊS"))
//                    dataCal.add(Calendar.MONTH, qt);
//                else if (param.equals ("ANO"))
//                    dataCal.add(Calendar.YEAR, qt);
//                
//                retorno = sdf.format(dataCal.getTime());
//                
//            } catch (ParseException ex) {
//                Logger.getLogger(PagamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
//            }
        
            
            Calendar dataCal = Calendar.getInstance();
            Date retorno;
            //String retorno = "1900-01-01";
            
            //try {
                
                //Date dataP = (Date)sdf.parse(dat);
                dataCal.setTime(dat);
                
                if (param.equals("DIA"))
                    dataCal.add(Calendar.DAY_OF_MONTH, qt);
                else if (param.equals("MÊS"))
                    dataCal.add(Calendar.MONTH, qt);
                else if (param.equals ("ANO"))
                    dataCal.add(Calendar.YEAR, qt);
                
                retorno = dataCal.getTime();
                
            //} catch (ParseException ex) {
            //    Logger.getLogger(PagamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            //}
            
            return retorno;
    }
}
