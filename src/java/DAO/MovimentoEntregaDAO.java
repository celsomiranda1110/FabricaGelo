/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Bean.ColaboradorEntrega;
import Bean.Movimento;
import Bean.MovimentoEntrega;
import Bean.Pagamento;
import Bean.ProdutoMovimento;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author celso
 */
public class MovimentoEntregaDAO extends DAO{

    public MovimentoEntregaDAO(Connection conexao) {
        super(conexao);
    }
    
    public List<MovimentoEntrega> listaTodos(ColaboradorEntrega _colaboradorEntrega)
    {
        List<MovimentoEntrega> lstTabela = new ArrayList();
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblMovimentoEntrega`.`intMovimentoEntregaId`,";
        comSql += "     `tblMovimentoEntrega`.`intEntregaColaboradorId`,";
        comSql += "     `tblMovimentoEntrega`.`intMovimentoId`,";
        comSql += "     `tblMovimentoEntrega`.`chrSituacao`";
        comSql += " FROM `smmdaa_bdGelo`.`tblMovimentoEntrega`";
        comSql += " WHERE ";
        comSql += "     `tblMovimentoEntrega`.`intEntregaColaboradorId` = " + _colaboradorEntrega.getIdColaboradorEntrega();
        comSql += ";";
        
        List tabela = super.listaTodos();
        
        for(int i = 0; i < tabela.size(); i++)
        {
            MovimentoEntrega movimentoEntrega = new MovimentoEntrega();
            List bkp = (ArrayList)tabela.get(i);
            
            movimentoEntrega.setIdMovimentoEntrega(((Integer)bkp.get(0)).intValue());
            movimentoEntrega.setIdColaboradorEntrega(((Integer)bkp.get(1)).intValue());
            movimentoEntrega.setIdMovimento(((Integer)bkp.get(2)).intValue());
            movimentoEntrega.setSituacao((String)bkp.get(3));
            
            MovimentoDAO movimentoDAO = new MovimentoDAO(conexao);
            Movimento movimento = new Movimento();
            movimento.setIdMovimento(movimentoEntrega.getIdMovimento());
            movimento = movimentoDAO.listaUm(movimento);
            movimentoEntrega.setMovimento(movimento);

            lstTabela.add(movimentoEntrega);
        }
        
        return lstTabela;
    }
    
    public MovimentoEntrega listaUm(MovimentoEntrega movimentoEntrega)
    {
        
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblMovimentoEntrega`.`intMovimentoEntregaId`,";
        comSql += "     `tblMovimentoEntrega`.`intEntregaColaboradorId`,";
        comSql += "     `tblMovimentoEntrega`.`intMovimentoId`,";
        comSql += "     `tblMovimentoEntrega`.`chrSituacao`";
        comSql += " FROM `smmdaa_bdGelo`.`tblMovimentoEntrega`";
        comSql += " WHERE ";
        comSql += "     `tblMovimentoEntrega`.`intMovimentoEntregaId` = " + movimentoEntrega.getIdMovimentoEntrega();
        comSql += ";";
        
        List tabela = super.listaUm();
        
        if(!tabela.isEmpty())
        {
            for(int i = 0; i < tabela.size(); i++)
            {
                
                List bkp = (ArrayList)tabela.get(i);

                movimentoEntrega.setIdMovimentoEntrega(((Integer)bkp.get(0)).intValue());
                movimentoEntrega.setIdColaboradorEntrega(((Integer)bkp.get(1)).intValue());
                movimentoEntrega.setIdMovimento(((Integer)bkp.get(2)).intValue());
                movimentoEntrega.setSituacao((String)bkp.get(3));
                
                MovimentoDAO movimentoDAO = new MovimentoDAO(conexao);
                Movimento movimento = new Movimento();
                movimento.setIdMovimento(movimentoEntrega.getIdMovimento());
                movimento = movimentoDAO.listaUm(movimento);
                movimentoEntrega.setMovimento(movimento);                
                
            }  
            return movimentoEntrega;
        }
        else
            return null;

    }
    
    public boolean atualizar(MovimentoEntrega movimentoEntrega) 
    {
        boolean retorno;
        double vlProduto = 0;
        double vlDesconto = 0;
        
        
        MovimentoEntrega _movimentoEntrega = new MovimentoEntrega();
        movimentoEntrega.replicar(_movimentoEntrega);
        
        
        //List<ProdutoMovimento> lstProdutoMovimento = movimento.getLstProdutoMovimento();
       
        if (listaUm(movimentoEntrega) == null)
        {
            comSql = "";
            comSql += " INSERT INTO `smmdaa_bdGelo`.`tblMovimentoEntrega`";
            comSql += " 	(";
            comSql += " 	`intEntregaColaboradorId`,";
            comSql += " 	`intMovimentoId`,";
            comSql += " 	`chrSituacao`)";
            comSql += " VALUES";
            comSql += " 	(" ;
            comSql += " 	" + _movimentoEntrega.getIdColaboradorEntrega();
            comSql += " 	," + _movimentoEntrega.getIdMovimento() ;
            comSql += " 	,'" + _movimentoEntrega.getSituacao() + "');";
           
            retorno = atualizar();
            if(retorno)
            {
                comSql = "";
                comSql += " Select max(intMovimentoEntregaId) as idMovimentoEntrega from `tblMovimentoEntrega`;";
                List lstConsulta = listaUm();
                for(int i = 0; i < lstConsulta.size(); i++)
                {
                    List reg = ((ArrayList)lstConsulta.get(0));
                    identity = ((Integer)reg.get(0)).intValue();
                    _movimentoEntrega.setIdMovimento(((Integer)reg.get(0)).intValue() );
                }
                
            }            
        }
        else
        {
            comSql = "";
            comSql += " UPDATE `smmdaa_bdGelo`.`tblMovimentoEntrega` SET ";
            comSql += " 	`intEntregaColaboradorId` = " + _movimentoEntrega.getIdColaboradorEntrega();
            comSql += " 	,`intMovimentoId` = " + _movimentoEntrega.getIdMovimento();
            comSql += " 	,`chrSituacao` = '" + _movimentoEntrega.getSituacao() + "'";
            comSql += " WHERE ";
            comSql += " 	`intMovimentoEntregaId` = " + _movimentoEntrega.getIdMovimentoEntrega() + ";";

            retorno = atualizar();
        }
        
        return retorno;
        
    }   
    
    public boolean deletar(MovimentoEntrega movimentoEntrega)
    {
        boolean retorno = false;
        
        if (movimentoEntrega != null)
        {
            comSql = "";
            comSql += " Delete from `smmdaa_bdGelo`.`tblMovimentoEntrega` ";
            comSql += " where ";
            comSql += " `intMovimentoEntregaId` = " + movimentoEntrega.getIdMovimentoEntrega();
            retorno = atualizar();
        }
        
        return retorno;
    }
        
    
}
