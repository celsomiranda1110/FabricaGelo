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

import Bean.ProdutoCamara;
import java.sql.Connection;
import java.util.*;


public class ProdutoCamaraDAO extends DAO{

    public ProdutoCamaraDAO(Connection conexao) {
        super(conexao);
    }


  
    
    
    public List<ProdutoCamara> listaTodos()
    {
        List<ProdutoCamara> lstTabela = new ArrayList();
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblProdutoCamara`.`intProdutoCamaraId`,";
        comSql += "     `tblProdutoCamara`.`intProdutoId`,";
        comSql += "     `tblProdutoCamara`.`intCamaraId`,";
        comSql += "     `tblProdutoCamara`.`datData`,";
        comSql += "     `tblProdutoCamara`.`dlbSaldoAnterior`,";
        comSql += "     `tblProdutoCamara`.`dblSaldo`";
        comSql += " FROM `bdGelo`.`tblProdutoCamara`;";
        
        List tabela = super.listaTodos();
        
        for(int i = 0; i < tabela.size(); i++)
        {
            ProdutoCamara produto = new ProdutoCamara();
            List bkp = (ArrayList)tabela.get(i);
            
            produto.setIdProdutoCamara(((Integer)bkp.get(0)).intValue());
            produto.setIdProduto(((Integer)bkp.get(1)).intValue());
            produto.setIdCamara(((Integer)bkp.get(2)).intValue());
            produto.setData((String)bkp.get(3));
            produto.setSaldoAnterior((Double)bkp.get(4));
            produto.setSaldo((Double)bkp.get(5));

            lstTabela.add(produto);
        }
        
        return lstTabela;
    }
    
    public ProdutoCamara listaUm(ProdutoCamara produtoCamara)
    {
        
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblProdutoCamara`.`intProdutoCamaraId`,";
        comSql += "     `tblProdutoCamara`.`intProdutoId`,";
        comSql += "     `tblProdutoCamara`.`intCamaraId`,";
        comSql += "     `tblProdutoCamara`.`datData`,";
        comSql += "     `tblProdutoCamara`.`dlbSaldoAnterior`,";
        comSql += "     `tblProdutoCamara`.`dblSaldo`";
        comSql += " FROM `bdGelo`.`tblProdutoCamara`";
        comSql += " WHERE ";
        comSql += "     `tblProdutoCamara`.`intProdutoCamaraId` = " + produtoCamara.getIdProdutoCamara()+ ";";
        List tabela = super.listaUm();
        
        if(!tabela.isEmpty())
        {
            for(int i = 0; i < tabela.size(); i++)
            {
                
                List bkp = (ArrayList)tabela.get(i);

                produtoCamara.setIdProdutoCamara(((Integer)bkp.get(0)).intValue());
                produtoCamara.setIdProduto(((Integer)bkp.get(1)).intValue());
                produtoCamara.setIdCamara(((Integer)bkp.get(2)).intValue());
                produtoCamara.setData((String)bkp.get(3));
                produtoCamara.setSaldoAnterior((Double)bkp.get(4));
                produtoCamara.setSaldo((Double)bkp.get(5));

            
                
            }  
            return produtoCamara;
        }
        else
            return null;

    }
    
    public boolean atualizar(ProdutoCamara produtoCamara) 
    {
        boolean retorno;
        
        ProdutoCamara _produtoCamara = new ProdutoCamara();
        produtoCamara.replicar(_produtoCamara);
       
        if (listaUm(produtoCamara) == null)
        {
            comSql = "";
            comSql += " INSERT INTO `bdGelo`.`tblProdutoCamara`";
            comSql += " 	(`intProdutoId`,";
            comSql += " 	`intCamaraId`,";
            comSql += " 	`datData`,";
            comSql += " 	`dlbSaldoAnterior`,";
            comSql += " 	`dblSaldo`)";
            comSql += " VALUES";
            comSql += " 	(" + _produtoCamara.getIdProduto();
            comSql += " 	," + _produtoCamara.getIdCamara();
            comSql += " 	,'" + _produtoCamara.getData() + "'";
            comSql += " 	'" + _produtoCamara.getSaldoAnterior();
            comSql += " 	'" + _produtoCamara.getSaldo() + ");";
            
            retorno = atualizar();
            if(retorno)
            {
                comSql = "";
                comSql += " Select max(intProdutoCamaraId) as idProdutoCamara from `tblProdutoCamara`;";
                List lstConsulta = listaUm();
                for(int i = 0; i < lstConsulta.size(); i++)
                {
                    List reg = ((ArrayList)lstConsulta.get(0));
                    identity = ((Integer)reg.get(0)).intValue();
                    _produtoCamara.setIdProdutoCamara(((Integer)reg.get(0)).intValue() );
                }
                
            }            
        }
        else
        {
            comSql = "";
            comSql += " UPDATE `bdGelo`.`tblProdutoCamara` SET";
            comSql += " 	`intProdutoId` = " + _produtoCamara.getIdProduto();
            comSql += " 	,`intCamaraId` = " + _produtoCamara.getIdCamara();
            comSql += " 	,`datData` = '" + _produtoCamara.getData() + "'";
            comSql += " 	,`dlbSaldoAnterior` = " + _produtoCamara.getSaldoAnterior();
            comSql += " 	,`dblSaldo` = " + _produtoCamara.getSaldo();
            comSql += " WHERE ";
            comSql += " 	`intProdutoCamaraId` = " + _produtoCamara.getIdProdutoCamara() + ";";

            retorno = atualizar();
        }
        
        return retorno;
        
    }     
    
}
