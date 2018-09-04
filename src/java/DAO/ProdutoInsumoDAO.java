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

import Bean.ProdutoInsumo;
import java.sql.Connection;
import java.util.*;

public class ProdutoInsumoDAO extends DAO{

    public ProdutoInsumoDAO(Connection conexao) {
        super(conexao);
    }
    
    public List<ProdutoInsumo> listaTodos()
    {
        List<ProdutoInsumo> lstTabela = new ArrayList();
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblProdutoInsumo`.`intProdutoInsumoId`,";
        comSql += "     `tblProdutoInsumo`.`intProdutoId`,";
        comSql += "     `tblProdutoInsumo`.`intInsumoId`,";
        comSql += "     `tblProdutoInsumo`.`dblQuantidade`";
        comSql += " FROM `bdGelo`.`tblProdutoInsumo`;";
        
        List tabela = super.listaTodos();
        
        for(int i = 0; i < tabela.size(); i++)
        {
            ProdutoInsumo produtoInsumo = new ProdutoInsumo();
            List bkp = (ArrayList)tabela.get(i);
            
            produtoInsumo.setIdProdutoInsumo(((Integer)bkp.get(0)).intValue());
            produtoInsumo.setIdProduto(((Integer)bkp.get(1)).intValue());
            produtoInsumo.setIdInsumo(((Integer)bkp.get(2)).intValue());
            produtoInsumo.setQuantidade((Double)bkp.get(3));
            

            lstTabela.add(produtoInsumo);
        }
        
        return lstTabela;
    }
    
    public ProdutoInsumo listaUm(ProdutoInsumo produtoInsumo)
    {
        
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblProdutoInsumo`.`intProdutoInsumoId`,";
        comSql += "     `tblProdutoInsumo`.`intProdutoId`,";
        comSql += "     `tblProdutoInsumo`.`intInsumoId`,";
        comSql += "     `tblProdutoInsumo`.`dblQuantidade`";
        comSql += " FROM `bdGelo`.`tblProdutoInsumo`";
        comSql += " WHERE ";
        comSql += "     `tblProdutoInsumo`.`intProdutoInsumoId` = " + produtoInsumo.getIdProdutoInsumo()+ ";";
        List tabela = super.listaUm();
        
        if(!tabela.isEmpty())
        {
            for(int i = 0; i < tabela.size(); i++)
            {
                
                List bkp = (ArrayList)tabela.get(i);

                produtoInsumo.setIdProdutoInsumo(((Integer)bkp.get(0)).intValue());
                produtoInsumo.setIdProduto(((Integer)bkp.get(1)).intValue());
                produtoInsumo.setIdInsumo(((Integer)bkp.get(2)).intValue());
                produtoInsumo.setQuantidade((Double)bkp.get(3));

            
                
            }  
            return produtoInsumo;
        }
        else
            return null;

    }
    
    public boolean atualizar(ProdutoInsumo produtoInsumo) 
    {
        boolean retorno;
        
        ProdutoInsumo _produtoInsumo = new ProdutoInsumo();
        produtoInsumo.replicar(_produtoInsumo);
       
        if (listaUm(produtoInsumo) == null)
        {
            comSql = "";
            comSql += " INSERT INTO `bdGelo`.`tblProdutoInsumo`";
            comSql += " 	(`intProdutoId`,";
            comSql += " 	`intInsumoId`,";
            comSql += " 	`dblQuantidade`)";
            comSql += " VALUES";
            comSql += " 	(" + _produtoInsumo.getIdProduto();
            comSql += " 	," + _produtoInsumo.getIdInsumo();
            comSql += " 	," + _produtoInsumo.getQuantidade() + ");";
            
            retorno = atualizar();
            if(retorno)
            {
                comSql = "";
                comSql += " Select max(intProdutoInsumoId) as idProdutoInsumo from `tblProdutoInsumo`;";
                List lstConsulta = listaUm();
                for(int i = 0; i < lstConsulta.size(); i++)
                {
                    List reg = ((ArrayList)lstConsulta.get(0));
                    identity = ((Integer)reg.get(0)).intValue();
                    _produtoInsumo.setIdProdutoInsumo(((Integer)reg.get(0)).intValue() );
                }
                
            }            
        }
        else
        {
            comSql = "";
            comSql += " UPDATE `bdGelo`.`tblProdutoInsumo` SET";
            comSql += " 	`intProdutoId` = " + _produtoInsumo.getIdProduto();
            comSql += " 	,`intInsumoId` " + _produtoInsumo.getIdInsumo();
            comSql += " 	,`dblQuantidade` = " + _produtoInsumo.getQuantidade();
            comSql += " WHERE ";
            comSql += " 	`intProdutoInsumoId` = " + _produtoInsumo.getIdProdutoInsumo() + ";";

            retorno = atualizar();
        }
        
        return retorno;
        
    }     

    
}
