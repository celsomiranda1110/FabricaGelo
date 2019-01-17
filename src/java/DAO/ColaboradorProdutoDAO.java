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
import Bean.Produto;
import Bean.Colaborador;
import Bean.ColaboradorProduto;
import java.sql.Connection;
import java.util.*;

public class ColaboradorProdutoDAO extends DAO{

    public ColaboradorProdutoDAO(Connection conexao) {
        super(conexao);
    }
    
    public List<ColaboradorProduto> listaTodos(Colaborador colaborador)
    {
        List<ColaboradorProduto> lstTabela = new ArrayList();
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblColaboradorProduto`.`intColaboradorProdutoId`,";
        comSql += "     `tblColaboradorProduto`.`intProdutoId`,";
        comSql += "     `tblColaboradorProduto`.`intColaboradorId`,";
        comSql += "     `tblColaboradorProduto`.`dblIcms`,";
        comSql += "     `tblColaboradorProduto`.`dblValor`,";
        comSql += "     `tblColaboradorProduto`.`dblDesconto`";
        comSql += " FROM `smmdaa_bdGelo`.`tblColaboradorProduto`";
        comSql += " WHERE ";
        comSql += "     `tblColaboradorProduto`.`intColaboradorId` = " + colaborador.getIdColaborador() + ";";
        
        List tabela = super.listaTodos();
        
        for(int i = 0; i < tabela.size(); i++)
        {
            ColaboradorProduto colaboradorProduto = new ColaboradorProduto();
            List bkp = (ArrayList)tabela.get(i);
            
            colaboradorProduto.setIdColaboradorProduto(((Integer)bkp.get(0)).intValue());
            colaboradorProduto.setIdProduto(((Integer)bkp.get(1)).intValue());
            colaboradorProduto.setIdColaborador(((Integer)bkp.get(2)).intValue());
            colaboradorProduto.setIcms((Double)bkp.get(3));
            colaboradorProduto.setValor((Double)bkp.get(4));
            colaboradorProduto.setDesconto((Double)bkp.get(5));
            
            ProdutoDAO _produtoDAO = new ProdutoDAO(conexao);
            Produto _produto = new Produto();
            _produto.setIdProduto(colaboradorProduto.getIdProduto());
            _produto = _produtoDAO.listaUm(_produto);
            colaboradorProduto.setProduto(_produto);

            lstTabela.add(colaboradorProduto);
        }
        
        return lstTabela;
    }
    
    public ColaboradorProduto listaUm(ColaboradorProduto colaboradorProduto)
    {
        
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblColaboradorProduto`.`intColaboradorProdutoId`,";
        comSql += "     `tblColaboradorProduto`.`intProdutoId`,";
        comSql += "     `tblColaboradorProduto`.`intColaboradorId`,";
        comSql += "     `tblColaboradorProduto`.`dblIcms`,";
        comSql += "     `tblColaboradorProduto`.`dblValor`,";
        comSql += "     `tblColaboradorProduto`.`dblDesconto`";
        comSql += " FROM `smmdaa_bdGelo`.`tblColaboradorProduto`";
        comSql += " WHERE ";
        comSql += "     `tblColaboradorProduto`.`intColaboradorProdutoId` = " + colaboradorProduto.getIdColaboradorProduto() + ";";
        List tabela = super.listaUm();
        
        if(!tabela.isEmpty())
        {
            for(int i = 0; i < tabela.size(); i++)
            {
                
                List bkp = (ArrayList)tabela.get(i);

                colaboradorProduto.setIdColaboradorProduto(((Integer)bkp.get(0)).intValue());
                colaboradorProduto.setIdProduto(((Integer)bkp.get(1)).intValue());
                colaboradorProduto.setIdColaborador(((Integer)bkp.get(2)).intValue());
                colaboradorProduto.setIcms((Double)bkp.get(3));
                colaboradorProduto.setValor((Double)bkp.get(4));
                colaboradorProduto.setDesconto((Double)bkp.get(5));
                
                ProdutoDAO _produtoDAO = new ProdutoDAO(conexao);
                Produto _produto = new Produto();
                _produto.setIdProduto(colaboradorProduto.getIdProduto());
                _produto = _produtoDAO.listaUm(_produto);
                colaboradorProduto.setProduto(_produto);                
                
                
            }  
            return colaboradorProduto;
        }
        else
            return null;

    }
    
    public boolean atualizar(ColaboradorProduto colaboradorProduto) 
    {
        boolean retorno;
        
        ColaboradorProduto _colaboradorProduto = new ColaboradorProduto();
        colaboradorProduto.replicar(_colaboradorProduto);
        
        if (listaUm(colaboradorProduto) == null)
        {
            comSql = "";
            comSql += " INSERT INTO `smmdaa_bdGelo`.`tblColaboradorProduto` ";
            comSql += " 	(`intProdutoId`,";
            comSql += " 	`intColaboradorId`,";
            comSql += " 	`dblIcms`,";
            comSql += " 	`dblValor`,";
            comSql += " 	`dblDesconto`)";
            comSql += " VALUES";
            comSql += " 	(" + _colaboradorProduto.getIdProduto();
            comSql += " 	," + _colaboradorProduto.getIdColaborador();
            comSql += " 	," + _colaboradorProduto.getIcms();
            comSql += " 	," + _colaboradorProduto.getValor();
            comSql += " 	," + _colaboradorProduto.getDesconto() + " );";

            
            retorno = atualizar();
            if(retorno)
            {
                comSql = "";
                comSql += " Select max(intColaboradorProdutoId) as idColaboradorProduto from `tblColaboradorProduto`;";
                List lstConsulta = listaUm();
                for(int i = 0; i < lstConsulta.size(); i++)
                {
                    List reg = ((ArrayList)lstConsulta.get(0));
                    identity = ((Integer)reg.get(0)).intValue();
                    _colaboradorProduto.setIdColaboradorProduto(((Integer)reg.get(0)).intValue() );
                }
                
            }            
        }
        else
        {
            comSql = "";
            comSql += " UPDATE `smmdaa_bdGelo`.`tblColaboradorProduto` SET ";
            comSql += "     `intProdutoId` = " + _colaboradorProduto.getIdProduto();
            comSql += "     ,`intColaboradorId` = " + _colaboradorProduto.getIdColaborador();
            comSql += "     ,`dblIcms` = " + _colaboradorProduto.getIcms();
            comSql += "     ,`dblValor` = " + _colaboradorProduto.getValor();
            comSql += "     ,`dblDesconto` = " + _colaboradorProduto.getDesconto();
            comSql += " WHERE ";
            comSql += "     `intColaboradorProdutoId` = " + _colaboradorProduto.getIdColaboradorProduto() + ";";
            retorno = atualizar();
        }
        
        return retorno;
        
    } 
    
    public boolean delete(ColaboradorProduto colaboradorProduto)
    {
        boolean retorno = false;
        
        if (listaUm(colaboradorProduto) != null)
        {
            comSql = "";
            comSql = " Delete from `smmdaa_bdGelo`.`tblColaboradorProduto`";
            comSql += " WHERE ";
            comSql += "     `intColaboradorProdutoId` = " + colaboradorProduto.getIdColaboradorProduto() + ";";
            retorno = atualizar();
        }
        
        return retorno;
    }
}
