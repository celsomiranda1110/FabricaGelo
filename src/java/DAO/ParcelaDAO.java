/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author celso
 */
import Bean.Pagamento;
import Bean.Parcela;
import java.sql.Connection;
import java.util.*;

public class ParcelaDAO extends DAO{

    public ParcelaDAO(Connection conexao) {
        super(conexao);
    }
    
    public List<Parcela> listaTodos(Pagamento pagamento)
    {
        List<Parcela> lstTabela = new ArrayList();
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblParcela`.`intParcelaId`,";
        comSql += "     `tblParcela`.`intPagamentoId`,";
        comSql += "     `tblParcela`.`intNumParcela`,";
        comSql += "     `tblParcela`.`chrFormaPagamento`,";
        comSql += "     `tblParcela`.`datDtPagamento`,";
        comSql += "     `tblParcela`.`datDtVencimento`,";
        comSql += "     `tblParcela`.`dblValor`,";
        comSql += "     `tblParcela`.`dblValorPago`,";
        comSql += "     `tblParcela`.`chrSituacao`";
        comSql += " FROM `smmdaa_bdGelo`.`tblParcela`";
        comSql += " WHERE ";
        comSql += "     `tblParcela`.`intPagamentoId` = " + pagamento.getIdPagamento() + ";";
        
        List tabela = super.listaTodos();
        
        for(int i = 0; i < tabela.size(); i++)
        {
            Parcela parcela = new Parcela();
            List bkp = (ArrayList)tabela.get(i);
            
            parcela.setIdParcela(((Integer)bkp.get(0)).intValue());
            parcela.setIdPagamento(((Integer)bkp.get(1)).intValue());
            parcela.setNumParcela(((Integer)bkp.get(2)).intValue());
            parcela.setFormaPagamento((String)bkp.get(3));
            parcela.setDtPagamento((Date)bkp.get(4));
            parcela.setDtVencimento((Date)bkp.get(5));
            parcela.setValor((Double)bkp.get(6));
            parcela.setValorPago((Double)bkp.get(7));
            parcela.setSituacao((String)bkp.get(8));
            

            lstTabela.add(parcela);
        }
        
        return lstTabela;
    }
    
    public Parcela listaUm(Parcela parcela)
    {
        
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblParcela`.`intParcelaId`,";
        comSql += "     `tblParcela`.`intPagamentoId`,";
        comSql += "     `tblParcela`.`intNumParcela`,";
        comSql += "     `tblParcela`.`chrFormaPagamento`,";
        comSql += "     `tblParcela`.`datDtPagamento`,";
        comSql += "     `tblParcela`.`datDtVencimento`,";
        comSql += "     `tblParcela`.`dblValor`,";
        comSql += "     `tblParcela`.`dblValorPago`,";
        comSql += "     `tblParcela`.`chrSituacao`";
        comSql += " FROM `smmdaa_bdGelo`.`tblParcela`";
        comSql += " WHERE ";
        comSql += "     `tblParcela`.`intParcelaId` = " + parcela.getIdParcela() + ";";
        List tabela = super.listaUm();
        
        if(!tabela.isEmpty())
        {
            for(int i = 0; i < tabela.size(); i++)
            {
                
                List bkp = (ArrayList)tabela.get(i);

            parcela.setIdParcela(((Integer)bkp.get(0)).intValue());
            parcela.setIdPagamento(((Integer)bkp.get(1)).intValue());
            parcela.setNumParcela(((Integer)bkp.get(2)).intValue());
            parcela.setFormaPagamento((String)bkp.get(3));
            parcela.setDtPagamento((Date)bkp.get(4));
            parcela.setDtVencimento((Date)bkp.get(5));
            parcela.setValor((Double)bkp.get(6));
            parcela.setValorPago((Double)bkp.get(7));
            parcela.setSituacao((String)bkp.get(8));
            
                
            }  
            return parcela;
        }
        else
            return null;

    }
    
    public boolean atualizar(Parcela parcela) 
    {
        boolean retorno;
        
        Parcela _parcela = new Parcela();
        parcela.replicar(_parcela);
       
        if (listaUm(parcela) == null)
        {
            comSql = "";
            comSql += " INSERT INTO `smmdaa_bdGelo`.`tblParcela`";
            comSql += " 	(`intPagamentoId`,";
            comSql += " 	`intNumParcela`,";
            comSql += " 	`chrFormaPagamento`,";
            comSql += " 	`datDtPagamento`,";
            comSql += " 	`datDtVencimento`,";
            comSql += " 	`dblValor`,";
            comSql += " 	`dblValorPago`,";
            comSql += " 	`chrSituacao`)";
            comSql += " VALUES";
            comSql += " 	(" + _parcela.getIdPagamento();
            comSql += " 	," + _parcela.getNumParcela();
            comSql += " 	,'" + _parcela.getFormaPagamento() + "'";
            comSql += " 	,'" + _parcela.getDtPagamentoFormatado() + "'";
            comSql += " 	,'" + _parcela.getDtVencimentoFormatado() + "'";
            comSql += " 	," + _parcela.getValor();
            comSql += " 	," + _parcela.getValorPago();
            comSql += " 	,'" + _parcela.getSituacao()+ "');";

            
            retorno = atualizar();
            if(retorno)
            {
                comSql = "";
                comSql += " Select max(intParcelaId) as idParcela from `tblParcela`;";
                List lstConsulta = listaUm();
                for(int i = 0; i < lstConsulta.size(); i++)
                {
                    List reg = ((ArrayList)lstConsulta.get(0));
                    identity = ((Integer)reg.get(0)).intValue();
                    _parcela.setIdParcela(((Integer)reg.get(0)).intValue() );
                }
                
            }            
        }
        else
        {
            comSql = "";
            comSql += " UPDATE `smmdaa_bdGelo`.`tblParcela` SET";
            comSql += " 	`intPagamentoId` = " + _parcela.getIdPagamento();
            comSql += " 	,`intNumParcela` = " + _parcela.getNumParcela();
            comSql += " 	,`chrFormaPagamento` = '" + _parcela.getFormaPagamento() + "'";
            comSql += " 	,`datDtPagamento` = '" + _parcela.getDtPagamentoFormatado() + "'";
            comSql += " 	,`datDtVencimento` = '" + _parcela.getDtVencimentoFormatado() + "'";
            comSql += " 	,`dblValor` = " + _parcela.getValor();
            comSql += " 	,`dblValorPago` = " + _parcela.getValorPago();
            comSql += " 	,`chrSituacao` = '" + _parcela.getSituacao()+ "'";
            comSql += " WHERE ";
            comSql += " 	`intParcelaId` = " + _parcela.getIdParcela() + ";";

            retorno = atualizar();
        }
        
        // verifica se pagamento foi pago
        if (retorno)
            setEncerraPagamento(_parcela);
        
        return retorno;
        
    }  
    
    private void setEncerraPagamento(Parcela parcela)
    {
        
        boolean encerraPagamento = true;
        
       
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblParcela`.`intParcelaId`,";
        comSql += "     `tblParcela`.`intPagamentoId`,";
        comSql += "     `tblParcela`.`intNumParcela`,";
        comSql += "     `tblParcela`.`chrFormaPagamento`,";
        comSql += "     `tblParcela`.`datDtPagamento`,";
        comSql += "     `tblParcela`.`datDtVencimento`,";
        comSql += "     `tblParcela`.`dblValor`,";
        comSql += "     `tblParcela`.`dblValorPago`,";
        comSql += "     `tblParcela`.`chrSituacao`";
        comSql += " FROM `smmdaa_bdGelo`.`tblParcela`";
        comSql += " WHERE ";
        comSql += "     `tblParcela`.`intPagamentoId` = " + parcela.getIdPagamento() + ";";
        
        List tabela = super.listaTodos();
        
        for(int i = 0; i < tabela.size(); i++)
        {
            Parcela _parcela = new Parcela();
            List bkp = (ArrayList)tabela.get(i);
            
            _parcela.setIdParcela(((Integer)bkp.get(0)).intValue());
            _parcela.setIdPagamento(((Integer)bkp.get(1)).intValue());
            _parcela.setNumParcela(((Integer)bkp.get(2)).intValue());
            _parcela.setFormaPagamento((String)bkp.get(3));
            _parcela.setDtPagamento((Date)bkp.get(4));
            _parcela.setDtVencimento((Date)bkp.get(5));
            _parcela.setValor((Double)bkp.get(6));
            _parcela.setValorPago((Double)bkp.get(7));
            _parcela.setSituacao((String)bkp.get(8));
            
            if (!_parcela.getSituacao().equals("G"))
                encerraPagamento = false;
            
        }        
        
       
        if(encerraPagamento)
        {
            PagamentoDAO pagamentoDAO = new PagamentoDAO(conexao);
            Pagamento pagamento = new Pagamento();
            pagamento.setIdPagamento(tipoPagamento);
            pagamento = pagamentoDAO.listaUm(pagamento);
            pagamento.setSituacao("PG");
            pagamentoDAO.atualizar(pagamento);
        }
        
    }
    
}
