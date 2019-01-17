/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author Miranda
 */

import Bean.MaquinaProducao;
import Bean.Producao;
import Bean.Produto;
import Bean.TransferenciaProducao;
import java.sql.Connection;
import java.util.*;

public class ProducaoDAO extends DAO{

    public ProducaoDAO(Connection conexao) {
        super(conexao);
    }
    
    public List<Producao> listaTodos()
    {
        List<Producao> lstTabela = new ArrayList();
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblProducao`.`intProducaoId`,";
        comSql += "     `tblProducao`.`intProdutoId`,";
        comSql += "     `tblProducao`.`strTurno`,";
        comSql += "     `tblProducao`.`datData`,";
        comSql += "     `tblProducao`.`dblQuantidade`,";
        comSql += "     `tblProducao`.`chrSituacao`";
        comSql += " FROM `smmdaa_bdGelo`.`tblProducao`;";
        
        List tabela = super.listaTodos();
        
        for(int i = 0; i < tabela.size(); i++)
        {
            Producao producao = new Producao();
            List bkp = (ArrayList)tabela.get(i);
            
            producao.setIdProducao(((Integer)bkp.get(0)).intValue());
            producao.setIdProduto(((Integer)bkp.get(1)).intValue());
            producao.setTurno((String)bkp.get(2));
            producao.setData((Date)bkp.get(3));
            producao.setQuantidade((Double)bkp.get(4));
            producao.setSituacao((String)bkp.get(5));
           
            ProdutoDAO produtoDAO = new ProdutoDAO(conexao);
            Produto produto = new Produto();
            produto.setIdProduto(producao.getIdProduto());
            produto = produtoDAO.listaUm(produto);
            producao.setProduto(produto);
        
            lstTabela.add(producao);
        }
        
        return lstTabela;
    }
    
    public Producao listaUm(Producao producao)
    {
        
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblProducao`.`intProducaoId`,";
        comSql += "     `tblProducao`.`intProdutoId`,";
        comSql += "     `tblProducao`.`strTurno`,";
        comSql += "     `tblProducao`.`datData`,";
        comSql += "     `tblProducao`.`dblQuantidade`,"; 
        comSql += "     `tblProducao`.`chrSituacao`";
        comSql += " FROM `smmdaa_bdGelo`.`tblProducao`";
        comSql += " WHERE ";
        comSql += "     `tblProducao`.`intProducaoId` = " + producao.getIdProducao() + ";";
        List tabela = super.listaUm();
        
        if(!tabela.isEmpty())
        {
            for(int i = 0; i < tabela.size(); i++)
            {
                
                List bkp = (ArrayList)tabela.get(i);

                producao.setIdProducao(((Integer)bkp.get(0)).intValue());
                producao.setIdProduto(((Integer)bkp.get(1)).intValue());
                producao.setTurno((String)bkp.get(2));
                producao.setData((Date)bkp.get(3));
                producao.setQuantidade((Double)bkp.get(4));
                producao.setSituacao((String)bkp.get(5));

                ProdutoDAO produtoDAO = new ProdutoDAO(conexao);
                Produto produto = new Produto();
                produto.setIdProduto(producao.getIdProduto());
                produto = produtoDAO.listaUm(produto);
                producao.setProduto(produto);

                List<MaquinaProducao> lstMaquinaProducao = new ArrayList<MaquinaProducao>();
                MaquinaProducaoDAO maquinaProducaoDAO = new MaquinaProducaoDAO(conexao);
                lstMaquinaProducao = maquinaProducaoDAO.listaTodos(producao);
                producao.setLstMaquinaProducao(lstMaquinaProducao);
                
                // descobrindo a quantidade de avarias
                Iterator itAvaria = lstMaquinaProducao.iterator();
                double qtAvaria = 0;
                double qtProduzida = 0;
                while (itAvaria.hasNext())
                {
                    MaquinaProducao _maquinaProducao = (MaquinaProducao)itAvaria.next();
                    qtAvaria += _maquinaProducao.getQtAvaria();
                    qtProduzida += _maquinaProducao.getQtProducao();
                }
                producao.setQuantidade(qtProduzida - qtAvaria);
                
                // descobrindo as transferências
                List<TransferenciaProducao> lstTransferenciaProducao = new ArrayList<TransferenciaProducao>();
                TransferenciaProducaoDAO transferenciaProducaoDAO = new TransferenciaProducaoDAO(conexao);
                lstTransferenciaProducao = transferenciaProducaoDAO.listaTodos(producao);
                producao.setLstTransferenciaProducao(lstTransferenciaProducao);
            }  
            return producao;
        }
        else
            return null;

    }
    
    public boolean atualizar(Producao producao) 
    {
        boolean retorno;
        double qtProducao = 0;
        
        Producao _producao = new Producao();
        producao.replicar(_producao);
       
        if (listaUm(producao) == null)
        {
            comSql = "";
            comSql += " INSERT INTO `smmdaa_bdGelo`.`tblProducao`";
            comSql += " 	(`intProdutoId`,";
            comSql += " 	`strTurno`,";
            comSql += " 	`datData`,";
            comSql += " 	`dblQuantidade`,";
            comSql += " 	`chrSituacao`)";
            comSql += " VALUES";
            comSql += " 	(" + _producao.getIdProduto();
            comSql += " 	,'" + _producao.getTurno() + "'";
            comSql += " 	,'" + _producao.getDataFormatada() + "'";
            comSql += " 	," + _producao.getQuantidade();
            comSql += " 	,'" + _producao.getSituacao() + "');";
            
            retorno = atualizar();
            if(retorno)
            {
                comSql = "";
                comSql += " Select max(intProducaoId) as idProducao from `tblProducao`;";
                List lstConsulta = listaUm();
                for(int i = 0; i < lstConsulta.size(); i++)
                {
                    List reg = ((ArrayList)lstConsulta.get(0));
                    identity = ((Integer)reg.get(0)).intValue();
                    _producao.setIdProducao(((Integer)reg.get(0)).intValue() );
                }
                
            }            
        }
        else
        {
            comSql = "";
            comSql += " UPDATE `smmdaa_bdGelo`.`tblProducao` SET ";
            comSql += " 	`intProdutoId` = " + _producao.getIdProduto();
            comSql += " 	,`strTurno` = '" + _producao.getTurno() + "'";
            comSql += " 	,`datData` = '" + _producao.getDataFormatada() + "'";
            comSql += " 	,`dblQuantidade` = " + _producao.getQuantidade();
            comSql += " 	,`chrSituacao` = '" + _producao.getSituacao() + "'";
            comSql += " WHERE ";
            comSql += " 	`intProducaoId` =  " + _producao.getIdProducao() + ";";

            retorno = atualizar();
        }
        
        if (retorno)
        {
            if (_producao.getLstMaquinaProducao() != null)
            {
                MaquinaProducaoDAO maquinaProducaoDAO = new MaquinaProducaoDAO(conexao);
                Iterator itMaquinaProducao = _producao.getLstMaquinaProducao().iterator();
                while (itMaquinaProducao.hasNext())
                {
                    
                    MaquinaProducao _maquinaProducao = (MaquinaProducao)itMaquinaProducao.next();
                    _maquinaProducao.setIdProducao(_producao.getIdProducao());
                    if(maquinaProducaoDAO.atualizar(_maquinaProducao))
                        qtProducao += _maquinaProducao.getQtProducao();
                }
                

            }
            
            // salvando transferẽncias, se houver
            if (_producao.getLstTransferenciaProducao() != null)
            {
                TransferenciaProducaoDAO transfDAO = new TransferenciaProducaoDAO(conexao);
                Iterator itTransf = _producao.getLstTransferenciaProducao().iterator();
                while (itTransf.hasNext())
                {
                    TransferenciaProducao transf = (TransferenciaProducao)itTransf.next();
                    transf.setIdProducao(_producao.getIdProducao());
                    transf.setDataFormatada("1900-01-01");
                    transfDAO.atualizar(transf);
                }
            }
        }

        
        return retorno;
        
    }    
}
