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
import Bean.TransferenciaProducao;
import java.sql.Connection;
import java.util.*;

public class TransferenciaProducaoDAO extends DAO{

    public TransferenciaProducaoDAO(Connection conexao) {
        super(conexao);
    }
    
    public List<TransferenciaProducao> listaTodos()
    {
        List<TransferenciaProducao> lstTabela = new ArrayList();
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblTransferenciaProducao`.`intTransferenciaProducaoId`,";
        comSql += "     `tblTransferenciaProducao`.`intProducaoId`,";
        comSql += "     `tblTransferenciaProducao`.`intProdutoCamaraId`,";
        comSql += "     `tblTransferenciaProducao`.`dblQuantidade`";
        comSql += " FROM `bdGelo`.`tblTransferenciaProducao`;";
        
        List tabela = super.listaTodos();
        
        for(int i = 0; i < tabela.size(); i++)
        {
            TransferenciaProducao transferenciaProducao = new TransferenciaProducao();
            List bkp = (ArrayList)tabela.get(i);
            
            transferenciaProducao.setIdTransferenciaProducao(((Integer)bkp.get(0)).intValue());
            transferenciaProducao.setIdProducao(((Integer)bkp.get(1)).intValue());
            transferenciaProducao.setIdProdutoCamara(((Integer)bkp.get(2)).intValue());
            transferenciaProducao.setQuantidade((Double)bkp.get(3));
            
            
            lstTabela.add(transferenciaProducao);
        }
        
        return lstTabela;
    }
    
    public TransferenciaProducao listaUm(TransferenciaProducao transferenciaProducao)
    {
        
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblTransferenciaProducao`.`intTransferenciaProducaoId`,";
        comSql += "     `tblTransferenciaProducao`.`intProducaoId`,";
        comSql += "     `tblTransferenciaProducao`.`intProdutoCamaraId`,";
        comSql += "     `tblTransferenciaProducao`.`dblQuantidade`";
        comSql += " FROM `bdGelo`.`tblTransferenciaProducao`";
        comSql += " WHERE ";
        comSql += "     `tblTransferenciaProducao`.`intTransferenciaProducaoId` = " + transferenciaProducao.getIdTransferenciaProducao()+ ";";
        List tabela = super.listaUm();
        
        if(!tabela.isEmpty())
        {
            for(int i = 0; i < tabela.size(); i++)
            {
                
                List bkp = (ArrayList)tabela.get(i);

                transferenciaProducao.setIdTransferenciaProducao(((Integer)bkp.get(0)).intValue());
                transferenciaProducao.setIdProducao(((Integer)bkp.get(1)).intValue());
                transferenciaProducao.setIdProdutoCamara(((Integer)bkp.get(2)).intValue());
                transferenciaProducao.setQuantidade((Double)bkp.get(3));

            }  
            return transferenciaProducao;
        }
        else
            return null;

    }
    
    public boolean atualizar(TransferenciaProducao transferenciaProducao) 
    {
        boolean retorno;
        
        TransferenciaProducao _transferenciaProducao = new TransferenciaProducao();
        transferenciaProducao.replicar(_transferenciaProducao);
       
        if (listaUm(transferenciaProducao) == null)
        {
            comSql = "";
            comSql += " INSERT INTO `bdGelo`.`tblTransferenciaProducao`";
            comSql += " 	(`intProducaoId`,";
            comSql += " 	`intProdutoCamaraId`,";
            comSql += " 	`dblQuantidade`)";
            comSql += " VALUES";
            comSql += " 	(" + _transferenciaProducao.getIdProducao();
            comSql += " 	," + _transferenciaProducao.getIdProdutoCamara();
            comSql += " 	," + _transferenciaProducao.getQuantidade() + ");";

            retorno = atualizar();
            if(retorno)
            {
                comSql = "";
                comSql += " Select max(intTransferenciaProducaoId) as idTransferenciaProducao from `tblTransferenciaProducao`;";
                List lstConsulta = listaUm();
                for(int i = 0; i < lstConsulta.size(); i++)
                {
                    List reg = ((ArrayList)lstConsulta.get(0));
                    identity = ((Integer)reg.get(0)).intValue();
                    _transferenciaProducao.setIdTransferenciaProducao(((Integer)reg.get(0)).intValue() );
                }
                
            }            
        }
        else
        {
            comSql = "";
            comSql += " UPDATE `bdGelo`.`tblTransferenciaProducao` SET";
            comSql += " 	`intProducaoId` = " + _transferenciaProducao.getIdProducao();
            comSql += " 	,`intProdutoCamaraId` = " + _transferenciaProducao.getIdProdutoCamara();
            comSql += " 	,`dblQuantidade` = " + _transferenciaProducao.getQuantidade();
            comSql += " WHERE ";
            comSql += " 	`intTransferenciaProducaoId` = " + _transferenciaProducao.getIdTransferenciaProducao() + ";";

            retorno = atualizar();
        }
        
        return retorno;
        
    }       
        
    
}
