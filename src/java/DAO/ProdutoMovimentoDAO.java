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

import Bean.Movimento;
import Bean.Produto;
import Bean.ProdutoMovimento;
import java.sql.Connection;
import java.util.*;

public class ProdutoMovimentoDAO extends DAO{

    public ProdutoMovimentoDAO(Connection conexao) {
        super(conexao);
    }
    
    public List<ProdutoMovimento> listaTodos(Movimento movimento)
    {
        List<ProdutoMovimento> lstTabela = new ArrayList();
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblProdutoMovimento`.`intProdutoMovimentoId`,";
        comSql += "     `tblProdutoMovimento`.`intMovimentoId`,";
        comSql += "     `tblProdutoMovimento`.`intProdutoId`,";
        comSql += "     `tblProdutoMovimento`.`dblIcms`,";
        comSql += "     `tblProdutoMovimento`.`dblValor`,";
        comSql += "     `tblProdutoMovimento`.`dblQuantidade`,";
        comSql += "     `tblProdutoMovimento`.`dblValorTotal`,";
        comSql += "     `tblProdutoMovimento`.`dblDesconto`,";
        comSql += "     `tblProdutoMovimento`.`dblQtAvaria`,";
        comSql += "     `tblProdutoMovimento`.`dblQtBonus`,";
        comSql += "     `tblProdutoMovimento`.`dblQtCortesia`,";
        comSql += "     `tblProdutoMovimento`.`dblQtReposicao`,";
        comSql += "     `tblProdutoMovimento`.`chrOperacao`";
        comSql += " FROM `smmdaa_bdGelo`.`tblProdutoMovimento`";
        comSql += " WHERE ";
        comSql += "     `tblProdutoMovimento`.`intMovimentoId` = " + movimento.getIdMovimento() + ";";
        
        List tabela = super.listaTodos();
        
        for(int i = 0; i < tabela.size(); i++)
        {
            ProdutoMovimento produtoMovimento = new ProdutoMovimento();
            List bkp = (ArrayList)tabela.get(i);
            
            produtoMovimento.setIdProdutoMovimento(((Integer)bkp.get(0)).intValue());
            produtoMovimento.setIdMovimento(((Integer)bkp.get(1)).intValue());
            produtoMovimento.setIdProduto(((Integer)bkp.get(2)).intValue());
            produtoMovimento.setIcms((Double)bkp.get(3));
            produtoMovimento.setValor((Double)bkp.get(4));
            produtoMovimento.setQuantidade((Double)bkp.get(5));
            produtoMovimento.setValorTotal((Double)bkp.get(6));
            produtoMovimento.setDesconto((Double)bkp.get(7));
            produtoMovimento.setQtAvaria((Double)bkp.get(8));
            produtoMovimento.setQtBonus((Double)bkp.get(9));
            produtoMovimento.setQtCortesia((Double)bkp.get(10));
            produtoMovimento.setQtReposicao((Double)bkp.get(11));
            produtoMovimento.setOperacao((String)bkp.get(12));
 
            ProdutoDAO produtoDAO = new ProdutoDAO(conexao);
            Produto produto = new Produto();
            produto.setIdProduto(produtoMovimento.getIdProduto());
            produto = produtoDAO.listaUm(produto);
            produtoMovimento.setProduto(produto);
            
            lstTabela.add(produtoMovimento);
        }
        
        return lstTabela;
    }
    
    public ProdutoMovimento listaUm(ProdutoMovimento produtoMovimento)
    {
        
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblProdutoMovimento`.`intProdutoMovimentoId`,";
        comSql += "     `tblProdutoMovimento`.`intMovimentoId`,";
        comSql += "     `tblProdutoMovimento`.`intProdutoId`,";
        comSql += "     `tblProdutoMovimento`.`dblIcms`,";
        comSql += "     `tblProdutoMovimento`.`dblValor`,";
        comSql += "     `tblProdutoMovimento`.`dblQuantidade`,";
        comSql += "     `tblProdutoMovimento`.`dblValorTotal`,";
        comSql += "     `tblProdutoMovimento`.`dblDesconto`,";
        comSql += "     `tblProdutoMovimento`.`dblQtAvaria`,";
        comSql += "     `tblProdutoMovimento`.`dblQtBonus`,";
        comSql += "     `tblProdutoMovimento`.`dblQtCortesia`,";
        comSql += "     `tblProdutoMovimento`.`dblQtReposicao`,";        
        comSql += "     `tblProdutoMovimento`.`chrOperacao`";
        comSql += " FROM `smmdaa_bdGelo`.`tblProdutoMovimento`";
        comSql += " WHERE ";
        comSql += "     `tblProdutoMovimento`.`intProdutoMovimentoId` = " + produtoMovimento.getIdProdutoMovimento()+ ";";
        List tabela = super.listaUm();
        
        if(!tabela.isEmpty())
        {
            for(int i = 0; i < tabela.size(); i++)
            {
                
                List bkp = (ArrayList)tabela.get(i);

                produtoMovimento.setIdProdutoMovimento(((Integer)bkp.get(0)).intValue());
                produtoMovimento.setIdMovimento(((Integer)bkp.get(1)).intValue());
                produtoMovimento.setIdProduto(((Integer)bkp.get(2)).intValue());
                produtoMovimento.setIcms((Double)bkp.get(3));
                produtoMovimento.setValor((Double)bkp.get(4));
                produtoMovimento.setQuantidade((Double)bkp.get(5));
                produtoMovimento.setValorTotal((Double)bkp.get(6));
                produtoMovimento.setDesconto((Double)bkp.get(7));
                produtoMovimento.setQtAvaria((Double)bkp.get(8));
                produtoMovimento.setQtBonus((Double)bkp.get(9));
                produtoMovimento.setQtCortesia((Double)bkp.get(10));
                produtoMovimento.setQtReposicao((Double)bkp.get(11));                
                produtoMovimento.setOperacao((String)bkp.get(12));

                ProdutoDAO produtoDAO = new ProdutoDAO(conexao);
                Produto produto = new Produto();
                produto.setIdProduto(produtoMovimento.getIdProduto());
                produto = produtoDAO.listaUm(produto);
                produtoMovimento.setProduto(produto);
                
            }  
            return produtoMovimento;
        }
        else
            return null;

    }
    
    public boolean atualizar(ProdutoMovimento produtoMovimento) 
    {
        boolean retorno;
        
        ProdutoMovimento _produtoMovimento = new ProdutoMovimento();
        produtoMovimento.replicar(_produtoMovimento);
       
        if (listaUm(produtoMovimento) == null)
        {
            comSql = "";
            comSql += " INSERT INTO `smmdaa_bdGelo`.`tblProdutoMovimento`";
            comSql += " 	(`intMovimentoId`,";
            comSql += " 	`intProdutoId`,";
            comSql += " 	`dblIcms`,";
            comSql += " 	`dblValor`,";
            comSql += " 	`dblQuantidade`,";
            comSql += " 	`dblValorTotal`,";
            comSql += " 	`dblDesconto`,";
            comSql += " 	`dblQtAvaria`,";
            comSql += " 	`dblQtBonus`,";
            comSql += " 	`dblQtCortesia`,";
            comSql += " 	`dblQtReposicao`,";
            comSql += " 	`chrOperacao`)";
            comSql += " VALUES";
            comSql += " 	(" + _produtoMovimento.getIdMovimento();
            comSql += " 	," + _produtoMovimento.getIdProduto();
            comSql += " 	," + _produtoMovimento.getIcms();
            comSql += " 	," + _produtoMovimento.getValor();
            comSql += " 	," + _produtoMovimento.getQuantidade();
            comSql += " 	," + _produtoMovimento.getValorTotal();
            comSql += " 	," + _produtoMovimento.getDesconto() ;
            comSql += " 	," + _produtoMovimento.getQtAvaria();
            comSql += " 	," + _produtoMovimento.getQtBonus();
            comSql += " 	," + _produtoMovimento.getQtCortesia();
            comSql += " 	," + _produtoMovimento.getQtReposicao();
            comSql += " 	,'" + _produtoMovimento.getOperacao() + "');";
            
            retorno = atualizar();
            if(retorno)
            {
                comSql = "";
                comSql += " Select max(intProdutoMovimentoId) as idProdutoMovimento from `smmdaa_bdGelo`.`tblProdutoMovimento`;";
                List lstConsulta = listaUm();
                for(int i = 0; i < lstConsulta.size(); i++)
                {
                    List reg = ((ArrayList)lstConsulta.get(0));
                    identity = ((Integer)reg.get(0)).intValue();
                    _produtoMovimento.setIdProdutoMovimento(((Integer)reg.get(0)).intValue() );
                }
                
            }            
        }
        else
        {
            comSql = "";
            comSql += " UPDATE `smmdaa_bdGelo`.`tblProdutoMovimento` SET";
            comSql += " 	`intMovimentoId` = " + _produtoMovimento.getIdMovimento();
            comSql += " 	,`intProdutoId` = " + _produtoMovimento.getIdProduto();
            comSql += " 	,`dblIcms` = "  + _produtoMovimento.getIcms();
            comSql += " 	,`dblValor` = "  + _produtoMovimento.getValor();
            comSql += " 	,`dblQuantidade` = "  + _produtoMovimento.getQuantidade();
            comSql += " 	,`dblValorTotal` = "  + _produtoMovimento.getValorTotal();
            comSql += " 	,`dblDesconto` = "  + _produtoMovimento.getDesconto();
            comSql += " 	,`dblQtAvaria` = "  + _produtoMovimento.getQtAvaria();
            comSql += " 	,`dblQtBonus` = "  + _produtoMovimento.getQtBonus();
            comSql += " 	,`dblQtCortesia` = "  + _produtoMovimento.getQtCortesia();
            comSql += " 	,`dblQtReposicao` = "  + _produtoMovimento.getQtReposicao();
            comSql += " 	,`chrOperacao` = '"  + _produtoMovimento.getOperacao() + "'";
            comSql += " WHERE ";
            comSql += " 	`intProdutoMovimentoId` = " + _produtoMovimento.getIdProdutoMovimento() + ";";
            retorno = atualizar();
        }
        
        if (retorno)
        {
            boolean atualizaSaldo = false;
            
            // atualização de saldo
            ProdutoDAO produtoDAO = new ProdutoDAO(conexao);
            Produto produto = (Produto)_produtoMovimento.getProduto();
            if (produto == null)
            {
                produto = new Produto();
                produto.setIdProduto(_produtoMovimento.getIdProduto());
                produto = produtoDAO.listaUm(produto);
            }
            double saldoAtual = 0;
            
            if (_produtoMovimento.getOperacao().equals("SU"))
            {
                atualizaSaldo = true;
                saldoAtual = produto.getSaldo() - _produtoMovimento.getQuantidade();
                produto.setSaldo(saldoAtual);
            }
            else if(_produtoMovimento.getOperacao().equals("AD"))
            {
                atualizaSaldo = true;
                saldoAtual = produto.getSaldo() + _produtoMovimento.getQuantidade();
                produto.setSaldo(saldoAtual);
            }

            if (atualizaSaldo)
                produtoDAO.atualizar(produto);
        }
        
        return retorno;
        
    }        
    
    public boolean delete(ProdutoMovimento produtoMovimento)
    {
        boolean retorno = false;
        
        if (produtoMovimento != null)
        {
            comSql = "";
            comSql += " Delete from `smmdaa_bdGelo`.`tblProdutoMovimento` ";
            comSql += " where ";
            comSql += "     `tblProdutoMovimento`.`intProdutoMovimentoId` = " + produtoMovimento.getIdProdutoMovimento()+ ";";
            retorno = atualizar();
        }
        
        return retorno;
    }
}
