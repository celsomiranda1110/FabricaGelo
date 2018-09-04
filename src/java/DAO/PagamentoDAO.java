package DAO;

import Bean.Pagamento;
import Bean.Parcela;
import java.sql.Connection;
import java.util.*;


// Referenced classes of package DAO:
//            DAO, ParcelaPagamentoDAO, DocumentoDAO

public class PagamentoDAO extends DAO
{

    public PagamentoDAO(Connection conexao)
    {
        super(conexao);
    }

    public List<Pagamento> listaTodos()
    {
        List<Pagamento> lstTabela = new ArrayList();
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblPagamento`.`intPagamentoId`,";
        comSql += " 	`tblPagamento`.`intMovimentoId`,";
        comSql += "     `tblPagamento`.`dblValor`,";
        comSql += "     `tblPagamento`.`dblDesconto`,";
        comSql += "     `tblPagamento`.`dblValorTotal`,";
        comSql += "     `tblPagamento`.`datData`,";
        comSql += "     `tblPagamento`.`intNumParcela`,";
        comSql += "     `tblPagamento`.`chrSituacao`,";
        comSql += "     `tblPagamento`.`strCobranca`";
        comSql += " FROM `bdGelo`.`tblPagamento`;";
        
        List tabela = super.listaTodos();
        
        for(int i = 0; i < tabela.size(); i++)
        {
            Pagamento pagamento = new Pagamento();
            List bkp = (ArrayList)tabela.get(i);
            
            pagamento.setIdPagamento(((Integer)bkp.get(0)).intValue());
            pagamento.setIdMovimento(((Integer)bkp.get(1)).intValue());
            pagamento.setValor((Double)bkp.get(2));
            pagamento.setDesconto((Double)bkp.get(3));
            pagamento.setValorTotal((Double)bkp.get(4));
            pagamento.setData((Date)bkp.get(5));
            pagamento.setNumParcela(((Integer)bkp.get(6)).intValue());
            pagamento.setSituacao((String)bkp.get(7));
            pagamento.setCobranca((String)bkp.get(8));

            lstTabela.add(pagamento);
        }
        
        return lstTabela;
    }
    
    public Pagamento listaUm(Pagamento pagamento)
    {
        
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblPagamento`.`intPagamentoId`,";
        comSql += " 	`tblPagamento`.`intMovimentoId`,";
        comSql += "     `tblPagamento`.`dblValor`,";
        comSql += "     `tblPagamento`.`dblDesconto`,";
        comSql += "     `tblPagamento`.`dblValorTotal`,";
        comSql += "     `tblPagamento`.`datData`,";
        comSql += "     `tblPagamento`.`intNumParcela`,";
        comSql += "     `tblPagamento`.`chrSituacao`,";
        comSql += "     `tblPagamento`.`strCobranca`";
        comSql += " FROM `bdGelo`.`tblPagamento`";
        comSql += " WHERE ";
        if (pagamento.getIdPagamento() != 0)
            comSql += "     `tblPagamento`.`intPagamentoId` = " + pagamento.getIdPagamento() + ";";
        else if(pagamento.getIdMovimento() != 0)
            comSql += "     `tblPagamento`.`intMovimentoId` = " + pagamento.getIdMovimento() + ";";
        
        List tabela = super.listaUm();
        
        if(!tabela.isEmpty())
        {
            for(int i = 0; i < tabela.size(); i++)
            {
                
                List bkp = (ArrayList)tabela.get(i);

                pagamento.setIdPagamento(((Integer)bkp.get(0)).intValue());
                pagamento.setIdMovimento(((Integer)bkp.get(1)).intValue());
                pagamento.setValor((Double)bkp.get(2));
                pagamento.setDesconto((Double)bkp.get(3));
                pagamento.setValorTotal((Double)bkp.get(4));
                pagamento.setData((Date)bkp.get(5));
                pagamento.setNumParcela(((Integer)bkp.get(6)).intValue());
                pagamento.setSituacao((String)bkp.get(7));
                pagamento.setCobranca((String)bkp.get(8));
                
                List<Parcela> lstParcela = new ArrayList<Parcela>();
                ParcelaDAO parcelaDAO = new ParcelaDAO(conexao);
                lstParcela = parcelaDAO.listaTodos(pagamento);
                pagamento.setLstParcela(lstParcela);
            
                
            }  
            return pagamento;
        }
        else
            return null;

    }
    
    public boolean atualizar(Pagamento pagamento) 
    {
        boolean retorno;
        
        Pagamento _pagamento = new Pagamento();
        pagamento.replicar(_pagamento);
       
        if (listaUm(pagamento) == null)
        {
            comSql = "";
            comSql += " INSERT INTO `bdGelo`.`tblPagamento`";
            comSql += " 	(`intMovimentoId`,";
            comSql += " 	`dblValor`,";
            comSql += " 	`dblDesconto`,";
            comSql += " 	`dblValorTotal`,";
            comSql += " 	`datData`,";
            comSql += " 	`intNumParcela`,";
            comSql += " 	`chrSituacao`,";
            comSql += " 	`strCobranca`)";
            comSql += " VALUES";
            comSql += " 	(" + _pagamento.getIdMovimento();
            comSql += " 	," + _pagamento.getValor();
            comSql += " 	," + _pagamento.getDesconto();
            comSql += " 	," + _pagamento.getValorTotal();
            comSql += " 	,'" + _pagamento.getDtDataFormatada() + "'";
            comSql += " 	," + _pagamento.getNumParcela();
            comSql += " 	,'" + _pagamento.getSituacao() + "'";
            comSql += " 	,'" + _pagamento.getCobranca() + "');";

            
            retorno = atualizar();
            if(retorno)
            {
                comSql = "";
                comSql += " Select max(intPagamentoId) as idPagamento from `tblPagamento`;";
                List lstConsulta = listaUm();
                for(int i = 0; i < lstConsulta.size(); i++)
                {
                    List reg = ((ArrayList)lstConsulta.get(0));
                    identity = ((Integer)reg.get(0)).intValue();
                    _pagamento.setIdPagamento(((Integer)reg.get(0)).intValue() );
                }
                
            }            
        }
        else
        {
            comSql = "";
            comSql += " UPDATE `bdGelo`.`tblPagamento` SET";
            comSql += " 	`intMovimentoId` = " + _pagamento.getIdMovimento();
            comSql += " 	,`dblValor` = " + _pagamento.getValor();
            comSql += " 	,`dblDesconto` = " + _pagamento.getDesconto();
            comSql += " 	,`dblValorTotal` = " + _pagamento.getValorTotal();
            comSql += " 	,`datData` = '" + _pagamento.getDtDataFormatada() + "'";
            comSql += " 	,`intNumParcela` = " + _pagamento.getNumParcela();
            comSql += " 	,`chrSituacao` = '" + _pagamento.getSituacao() + "'";
            comSql += " 	,`strCobranca` = '" + _pagamento.getCobranca() + "'";
            comSql += " WHERE ";
            comSql += " 	`intPagamentoId` = " + _pagamento.getIdPagamento() + ";";

            retorno = atualizar();
        }
        
        if (retorno)
        {
            ParcelaDAO parcelaDAO = new ParcelaDAO(conexao);
            if (_pagamento.getLstParcela() == null)
            {
               Parcela parcela = new Parcela();
               parcela.setIdPagamento(_pagamento.getIdPagamento());
               parcela.setValor(pagamento.getValorTotal());
               parcela.setNumParcela(1);
               parcela.setValorPago(0);
               parcela.setDtPagamento(_pagamento.getData());
               parcela.setDtVencimento(_pagamento.getData());
               parcela.setFormaPagamento("0");
               parcela.setSituacao("P");
               parcelaDAO.atualizar(parcela);                
            }
            else
            {
                Iterator itParcela = _pagamento.getLstParcela().iterator();
                while (itParcela.hasNext())
                {
                    Parcela parcela = (Parcela)itParcela.next();
                    parcela.setIdPagamento(_pagamento.getIdPagamento());
                    parcelaDAO.atualizar(parcela);
                }
            }
                    

        }
        
        return retorno;
        
    }  
    
    public boolean deletaParcelas(Pagamento pagamento)
    {
        comSql = "";
        comSql += " Delete from bdGelo.tblParcela " ;
        comSql += " where ";
        comSql += " intPagamentoId = " + pagamento.getIdPagamento();
        
        return atualizar();
    }
}
