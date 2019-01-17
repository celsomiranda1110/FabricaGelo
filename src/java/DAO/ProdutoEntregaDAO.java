/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Bean.AvariaEntrega;
import Bean.Entrega;
import Bean.Produto;
import Bean.ProdutoEntrega;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author celso
 */
public class ProdutoEntregaDAO extends DAO{

    public ProdutoEntregaDAO(Connection conexao) {
        super(conexao);
    }
    
    public List<ProdutoEntrega> listaTodos(Entrega entrega)
    {
        List<ProdutoEntrega> lstTabela = new ArrayList();
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblProdutoEntrega`.`intProdutoEntregaId`,";
        comSql += "     `tblProdutoEntrega`.`intProdutoId`,";
        comSql += "     `tblProdutoEntrega`.`intEntregaId`,";
        comSql += "     `tblProdutoEntrega`.`dblQuantidade`,";
        comSql += "     `tblProdutoEntrega`.`dblQtAvaria`,";
        comSql += "     `tblProdutoEntrega`.`dblQtBonus`,";
        comSql += "     `tblProdutoEntrega`.`dblQtCortesia`,";
        comSql += "     `tblProdutoEntrega`.`dblQtReposicao`,";
        comSql += "     `tblProdutoEntrega`.`dblQtVenda`,";
        comSql += "     `tblProdutoEntrega`.`dblSaldo`,";
        comSql += "     `tblProdutoEntrega`.`chrSituacao`";
        comSql += " FROM `smmdaa_bdGelo`.`tblProdutoEntrega`";
        comSql += " WHERE ";
        comSql += "     `tblProdutoEntrega`.`intEntregaId` = " + entrega.getIdEntrega() + ";";
        
        List tabela = super.listaTodos();
        
        for(int i = 0; i < tabela.size(); i++)
        {
            ProdutoEntrega produtoEntrega = new ProdutoEntrega();
            List bkp = (ArrayList)tabela.get(i);
            
            produtoEntrega.setIdProdutoEntrega(((Integer)bkp.get(0)).intValue());
            produtoEntrega.setIdProduto(((Integer)bkp.get(1)).intValue());
            produtoEntrega.setIdEntrega(((Integer)bkp.get(2)).intValue());
            produtoEntrega.setDblQuantidade((Double)bkp.get(3));
            produtoEntrega.setDblQuantidadeAvaria((Double)bkp.get(4));
            produtoEntrega.setDblQuantidadeBonus((Double)bkp.get(5));
            produtoEntrega.setDblQuantidadeCortesia((Double)bkp.get(6));
            produtoEntrega.setDblQuantidadeReposicao((Double)bkp.get(7));
            produtoEntrega.setDblQuantidadeVenda((Double)bkp.get(8));
            produtoEntrega.setDblSaldo((Double)bkp.get(9));
            produtoEntrega.setSituacao((String)bkp.get(10));
            
            ProdutoDAO produtoDAO = new ProdutoDAO(conexao);
            Produto produto = new Produto();
            produto.setIdProduto(produtoEntrega.getIdProduto());
            produto = produtoDAO.listaUm(produto);
            produtoEntrega.setProduto(produto);

            lstTabela.add(produtoEntrega);
        }
        
        return lstTabela;
    }
    
    public ProdutoEntrega listaUm(ProdutoEntrega produtoEntrega)
    {
        
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblProdutoEntrega`.`intProdutoEntregaId`,";
        comSql += "     `tblProdutoEntrega`.`intProdutoId`,";
        comSql += "     `tblProdutoEntrega`.`intEntregaId`,";
        comSql += "     `tblProdutoEntrega`.`dblQuantidade`,";
        comSql += "     `tblProdutoEntrega`.`dblQtAvaria`,";
        comSql += "     `tblProdutoEntrega`.`dblQtBonus`,";
        comSql += "     `tblProdutoEntrega`.`dblQtCortesia`,";
        comSql += "     `tblProdutoEntrega`.`dblQtReposicao`,";
        comSql += "     `tblProdutoEntrega`.`dblQtVenda`,";
        comSql += "     `tblProdutoEntrega`.`dblSaldo`,";
        comSql += "     `tblProdutoEntrega`.`chrSituacao`";
        comSql += " FROM `smmdaa_bdGelo`.`tblProdutoEntrega`";
        comSql += " WHERE ";
        comSql += "     `tblProdutoEntrega`.`intProdutoEntregaId` = " + produtoEntrega.getIdProdutoEntrega()+ ";";
        List tabela = super.listaUm();
        
        if(!tabela.isEmpty())
        {
            for(int i = 0; i < tabela.size(); i++)
            {
                
                List bkp = (ArrayList)tabela.get(i);

                produtoEntrega.setIdProdutoEntrega(((Integer)bkp.get(0)).intValue());
                produtoEntrega.setIdProduto(((Integer)bkp.get(1)).intValue());
                produtoEntrega.setIdEntrega(((Integer)bkp.get(2)).intValue());
                produtoEntrega.setDblQuantidade((Double)bkp.get(3));
                produtoEntrega.setDblQuantidadeAvaria((Double)bkp.get(4));
                produtoEntrega.setDblQuantidadeBonus((Double)bkp.get(5));
                produtoEntrega.setDblQuantidadeCortesia((Double)bkp.get(6));
                produtoEntrega.setDblQuantidadeReposicao((Double)bkp.get(7));
                produtoEntrega.setDblQuantidadeVenda((Double)bkp.get(8));
                produtoEntrega.setDblSaldo((Double)bkp.get(9));
                produtoEntrega.setSituacao((String)bkp.get(10));

                ProdutoDAO produtoDAO = new ProdutoDAO(conexao);
                Produto produto = new Produto();
                produto.setIdProduto(produtoEntrega.getIdProduto());
                produto = produtoDAO.listaUm(produto);
                produtoEntrega.setProduto(produto);
                
                List<AvariaEntrega> lstAvariaEntrega = new ArrayList<AvariaEntrega>();
                AvariaEntregaDAO avariaEntregaDAO = new AvariaEntregaDAO(conexao);
                lstAvariaEntrega = avariaEntregaDAO.listaTodos(produtoEntrega);
                produtoEntrega.setLstAvariaEntrega(lstAvariaEntrega);
                
            }  
            return produtoEntrega;
        }
        else
            return null;

    }
    
    public boolean atualizar(ProdutoEntrega produtoEntrega) 
    {
        boolean retorno;
        
        ProdutoEntrega _produtoEntrega = new ProdutoEntrega();
        produtoEntrega.replicar(_produtoEntrega);
       
        if (listaUm(produtoEntrega) == null)
        {
            comSql = "";
            comSql += " INSERT INTO `smmdaa_bdGelo`.`tblProdutoEntrega`";
            comSql += " 	(`intProdutoId`,";
            comSql += " 	`intEntregaId`,";
            comSql += " 	`dblQuantidade`,";
            comSql += " 	`dblQtAvaria`,";
            comSql += " 	`dblQtBonus`,";
            comSql += " 	`dblQtCortesia`,";
            comSql += " 	`dblQtReposicao`,";
            comSql += " 	`dblQtVenda`,";
            comSql += " 	`dblSaldo`,";
            comSql += " 	`chrSituacao`)";
            comSql += " VALUES";
            comSql += " 	(" + _produtoEntrega.getIdProduto();
            comSql += " 	," + _produtoEntrega.getIdEntrega();
            comSql += " 	," + _produtoEntrega.getDblQuantidade();
            comSql += " 	," + _produtoEntrega.getDblQuantidadeAvaria();
            comSql += " 	," + _produtoEntrega.getDblQuantidadeBonus();
            comSql += " 	," + _produtoEntrega.getDblQuantidadeCortesia();
            comSql += " 	," + _produtoEntrega.getDblQuantidadeReposicao();
            comSql += " 	," + _produtoEntrega.getDblQuantidadeVenda();
            comSql += " 	," + _produtoEntrega.getDblSaldo();
            comSql += " 	,'" + _produtoEntrega.getSituacao() + "');";
            
            retorno = atualizar();
            if(retorno)
            {
                comSql = "";
                comSql += " Select max(intProdutoEntregaId) as idProdutoEntrega from `tblProdutoEntrega`;";
                List lstConsulta = listaUm();
                for(int i = 0; i < lstConsulta.size(); i++)
                {
                    List reg = ((ArrayList)lstConsulta.get(0));
                    identity = ((Integer)reg.get(0)).intValue();
                    _produtoEntrega.setIdProdutoEntrega(((Integer)reg.get(0)).intValue() );
                }
                
            }            
        }
        else
        {
            comSql = "";
            comSql += " UPDATE `smmdaa_bdGelo`.`tblProdutoEntrega` SET";
            comSql += " 	`intProdutoId` = " + _produtoEntrega.getIdProduto();
            comSql += " 	,`intEntregaId` = " + _produtoEntrega.getIdEntrega();
            comSql += " 	,`dblQuantidade` = " + _produtoEntrega.getDblQuantidade();
            comSql += " 	,`dblQtAvaria` = " + _produtoEntrega.getDblQuantidadeAvaria();
            comSql += " 	,`dblQtBonus` = " + _produtoEntrega.getDblQuantidadeBonus();
            comSql += " 	,`dblQtCortesia` = " + _produtoEntrega.getDblQuantidadeCortesia();
            comSql += " 	,`dblQtReposicao` = " + _produtoEntrega.getDblQuantidadeReposicao();
            comSql += " 	,`dblQtVenda` = " + _produtoEntrega.getDblQuantidadeVenda();
            comSql += "         ,`dblSaldo` = " + _produtoEntrega.getDblSaldo();
            comSql += " 	,`chrSituacao` = '" + _produtoEntrega.getSituacao() + "'";
            comSql += " WHERE ";
            comSql += " 	`intProdutoEntregaId` = " + _produtoEntrega.getIdProdutoEntrega() + ";";

            retorno = atualizar();
        }
        
        if (retorno)
        {
            if (_produtoEntrega.getLstAvariaEntrega() != null)
            {
                AvariaEntregaDAO avariaEntregaDAO = new AvariaEntregaDAO(conexao);
                Iterator itAvaria = _produtoEntrega.getLstAvariaEntrega().iterator();
                while (itAvaria.hasNext())
                {
                    AvariaEntrega _avariaEntrega = (AvariaEntrega)itAvaria.next();
                    _avariaEntrega.setIdProdutoEntrega(_produtoEntrega.getIdProdutoEntrega());
                    if (avariaEntregaDAO.atualizar(_avariaEntrega))
                    {
                        // atualizando quantidades
                        comSql = "";
                        comSql += " UPDATE `smmdaa_bdGelo`.`tblProdutoEntrega` SET";
                        comSql += " 	`dblQtAvaria` = dblQtAvaria + " + _avariaEntrega.getQuantidade();
                        comSql += "     ,`dblSaldo` = `dblSaldo` - " + _avariaEntrega.getQuantidade();
                        comSql += " WHERE ";
                        comSql += " 	`intProdutoEntregaId` = " + _produtoEntrega.getIdProdutoEntrega() + ";";                        
                        atualizar();
                    }
                }
            }
        }
        
        return retorno;
        
    }  
    
    public boolean delete(ProdutoEntrega produtoEntrega)
    {
        boolean retorno = false;

        
        if (produtoEntrega != null)
        {
            
            comSql = "";
            comSql += " Delete from `smmdaa_bdGelo`.`tblAvariaEntrega` ";
            comSql += " where ";
            comSql += " `tblAvariaEntrega`.`intProdutoEntregaId` = " + produtoEntrega.getIdProdutoEntrega() + ";";            
            atualizar();
            
            comSql = "";
            comSql += " Delete from `smmdaa_bdGelo`.`tblProdutoEntrega` ";
            comSql += " WHERE ";
            comSql += "     `tblProdutoEntrega`.`intProdutoEntregaId` = " + produtoEntrega.getIdProdutoEntrega() + ";";
            retorno = atualizar();
        }
        
        return retorno;
    }
    
}
