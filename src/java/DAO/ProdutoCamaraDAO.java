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

import Bean.Equipamento;
import Bean.Produto;
import Bean.ProdutoCamara;
import java.sql.Connection;
import java.util.*;


public class ProdutoCamaraDAO extends DAO{

    public ProdutoCamaraDAO(Connection conexao) {
        super(conexao);
    }
    
    public List<ProdutoCamara> listaTodos(ProdutoCamara _produtoCamara)
    {
        List<ProdutoCamara> lstTabela = new ArrayList();
        boolean concatena = false;
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblProdutoCamara`.`intProdutoCamaraId`,";
        comSql += "     `tblProdutoCamara`.`intProdutoId`,";
        comSql += "     `tblProdutoCamara`.`intEquipamentoId`,";
        comSql += "     `tblProdutoCamara`.`datData`,";
        comSql += "     `tblProdutoCamara`.`dlbSaldoAnterior`,";
        comSql += "     `tblProdutoCamara`.`dblSaldo`";
        comSql += " FROM `smmdaa_bdGelo`.`tblProdutoCamara`";
        if (_produtoCamara != null)
        {
            comSql += " WHERE ";
            if (_produtoCamara.getIdEquipamento() != 0)
            {
                comSql += "     `tblProdutoCamara`.`intEquipamentoId` = " + _produtoCamara.getIdEquipamento();
                concatena = true;
            }
            if (_produtoCamara.getIdProduto() != 0)
            {
                if (concatena)
                    comSql += " and ";
                comSql += "     `tblProdutoCamara`.`intProdutoId` = " + _produtoCamara.getIdProduto();
            }
        }
        comSql += ";";
        
        List tabela = super.listaTodos();
        
        for(int i = 0; i < tabela.size(); i++)
        {
            ProdutoCamara produtoCamara = new ProdutoCamara();
            List bkp = (ArrayList)tabela.get(i);
            
            produtoCamara.setIdProdutoCamara(((Integer)bkp.get(0)).intValue());
            produtoCamara.setIdProduto(((Integer)bkp.get(1)).intValue());
            produtoCamara.setIdEquipamento(((Integer)bkp.get(2)).intValue());
            produtoCamara.setData((Date)bkp.get(3));
            produtoCamara.setSaldoAnterior((Double)bkp.get(4));
            produtoCamara.setSaldo((Double)bkp.get(5));
            
            // identificando a câmera
            EquipamentoDAO camaraDAO = new EquipamentoDAO(conexao);
            Equipamento camara = new Equipamento();
            camara.setIdEquipamento(produtoCamara.getIdEquipamento());
            camara = camaraDAO.listaUm(camara);
            produtoCamara.setEquipamento(camara);
            
            // identificando o produto na câmera
            ProdutoDAO produtoDAO = new ProdutoDAO(conexao);
            Produto produto = new Produto();
            produto.setIdProduto(produtoCamara.getIdProduto());
            produto = produtoDAO.listaUm(produto);
            produtoCamara.setProduto(produto);

            lstTabela.add(produtoCamara);
        }
        
        return lstTabela;
    }
    
    public ProdutoCamara listaUm(ProdutoCamara produtoCamara)
    {
        
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblProdutoCamara`.`intProdutoCamaraId`,";
        comSql += "     `tblProdutoCamara`.`intProdutoId`,";
        comSql += "     `tblProdutoCamara`.`intEquipamentoId`,";
        comSql += "     `tblProdutoCamara`.`datData`,";
        comSql += "     `tblProdutoCamara`.`dlbSaldoAnterior`,";
        comSql += "     `tblProdutoCamara`.`dblSaldo`";
        comSql += " FROM `smmdaa_bdGelo`.`tblProdutoCamara`";
        comSql += " WHERE ";
        if ((produtoCamara.getIdEquipamento() != 0) && (produtoCamara.getIdProduto() != 0))
        {
            comSql += "     `tblProdutoCamara`.`intProdutoId` = " + produtoCamara.getIdProduto();
            comSql += "     and `tblProdutoCamara`.`intEquipamentoId` = " + produtoCamara.getIdEquipamento();
        }
        else
            comSql += "     `tblProdutoCamara`.`intProdutoCamaraId` = " + produtoCamara.getIdProdutoCamara()+ ";";
        List tabela = super.listaUm();
        
        if(!tabela.isEmpty())
        {
            for(int i = 0; i < tabela.size(); i++)
            {
                
                List bkp = (ArrayList)tabela.get(i);

                produtoCamara.setIdProdutoCamara(((Integer)bkp.get(0)).intValue());
                produtoCamara.setIdProduto(((Integer)bkp.get(1)).intValue());
                produtoCamara.setIdEquipamento(((Integer)bkp.get(2)).intValue());
                produtoCamara.setData((Date)bkp.get(3));
                produtoCamara.setSaldoAnterior((Double)bkp.get(4));
                produtoCamara.setSaldo((Double)bkp.get(5));

                // identificando a câmera
                EquipamentoDAO camaraDAO = new EquipamentoDAO(conexao);
                Equipamento camara = new Equipamento();
                camara.setIdEquipamento(produtoCamara.getIdEquipamento());
                camara = camaraDAO.listaUm(camara);
                produtoCamara.setEquipamento(camara);

                // identificando o produto na câmera
                ProdutoDAO produtoDAO = new ProdutoDAO(conexao);
                Produto produto = new Produto();
                produto.setIdProduto(produtoCamara.getIdProduto());
                produto = produtoDAO.listaUm(produto);
                produtoCamara.setProduto(produto);
            
                
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
            comSql += " INSERT INTO `smmdaa_bdGelo`.`tblProdutoCamara`";
            comSql += " 	(`intProdutoId`,";
            comSql += " 	`intEquipamentoId`,";
            comSql += " 	`datData`,";
            comSql += " 	`dlbSaldoAnterior`,";
            comSql += " 	`dblSaldo`)";
            comSql += " VALUES";
            comSql += " 	(" + _produtoCamara.getIdProduto();
            comSql += " 	," + _produtoCamara.getIdEquipamento();
            comSql += " 	,'" + _produtoCamara.getDataFormatada() + "'";
            comSql += " 	," + _produtoCamara.getSaldoAnterior();
            comSql += " 	," + _produtoCamara.getSaldo() + ");";
            
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
            comSql += " UPDATE `smmdaa_bdGelo`.`tblProdutoCamara` SET";
            comSql += " 	`intProdutoId` = " + _produtoCamara.getIdProduto();
            comSql += " 	,`intEquipamentoId` = " + _produtoCamara.getIdEquipamento();
            comSql += " 	,`datData` = '" + _produtoCamara.getDataFormatada() + "'";
            comSql += " 	,`dlbSaldoAnterior` = " + _produtoCamara.getSaldoAnterior();
            comSql += " 	,`dblSaldo` = " + _produtoCamara.getSaldo();
            comSql += " WHERE ";
            comSql += " 	`intProdutoCamaraId` = " + _produtoCamara.getIdProdutoCamara() + ";";

            retorno = atualizar();
        }
        
        return retorno;
        
    }     
    
}
