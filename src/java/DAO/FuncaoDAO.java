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

import Bean.Funcao;
import java.sql.Connection;
import java.util.*;

public class FuncaoDAO extends DAO{

    public FuncaoDAO(Connection conexao) {
        super(conexao);
    }
    
    public List<Funcao> listaTodos(Funcao _funcao)
    {
        List<Funcao> lstTabela = new ArrayList();
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblFuncao`.`intFuncaoId`,";
        comSql += "     `tblFuncao`.`strDescricao`,";
        comSql += "     `tblFuncao`.`bolAtivo`";
        comSql += " FROM `smmdaa_bdGelo`.`tblFuncao`";
        if (_funcao != null)
        {
            comSql += " WHERE ";
            comSql += "     `tblFuncao`.`bolAtivo` = '" + _funcao.getAtivo() + "'";
        }
        
        List tabela = super.listaTodos();
        
        for(int i = 0; i < tabela.size(); i++)
        {
            Funcao funcao = new Funcao();
            List bkp = (ArrayList)tabela.get(i);
            
            funcao.setIdFuncao(((Integer)bkp.get(0)).intValue());
            funcao.setDescricao((String)bkp.get(1));
            funcao.setAtivo((String)bkp.get(2));

            lstTabela.add(funcao);
        }
        
        return lstTabela;
    }
    
    public Funcao listaUm(Funcao funcao)
    {
        
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblFuncao`.`intFuncaoId`,";
        comSql += "     `tblFuncao`.`strDescricao`,";
        comSql += "     `tblFuncao`.`bolAtivo`";
        comSql += " FROM `smmdaa_bdGelo`.`tblFuncao`";
        comSql += " WHERE ";
        comSql += "     `tblFuncao`.`intFuncaoId` = " + funcao.getIdFuncao()+ ";";
        List tabela = super.listaUm();
        
        if(!tabela.isEmpty())
        {
            for(int i = 0; i < tabela.size(); i++)
            {
                
                List bkp = (ArrayList)tabela.get(i);

                funcao.setIdFuncao(((Integer)bkp.get(0)).intValue());
                funcao.setDescricao((String)bkp.get(1));
                funcao.setAtivo((String)bkp.get(2));
                
            }  
            return funcao;
        }
        else
            return null;

    }
    
    public boolean atualizar(Funcao funcao) 
    {
        boolean retorno;
        
        Funcao _funcao = new Funcao();
        funcao.replicar(_funcao);
       
        if (listaUm(funcao) == null)
        {
            comSql = "";
            comSql += " INSERT INTO `smmdaa_bdGelo`.`tblFuncao`";
            comSql += " 	(`strDescricao`, `bolAtivo`)";
            comSql += " VALUES";
            comSql += " 	('" + _funcao.getDescricao() + "'";
            comSql += " 	,'" + _funcao.getAtivo() + "');";

            
            retorno = atualizar();
            if(retorno)
            {
                comSql = "";
                comSql += " Select max(intFuncaoId) as idFuncao from `tblFuncao`;";
                List lstConsulta = listaUm();
                for(int i = 0; i < lstConsulta.size(); i++)
                {
                    List reg = ((ArrayList)lstConsulta.get(0));
                    identity = ((Integer)reg.get(0)).intValue();
                    _funcao.setIdFuncao(((Integer)reg.get(0)).intValue() );
                }
                
            }            
        }
        else
        {
            comSql = "";
            comSql += " UPDATE `smmdaa_bdGelo`.`tblFuncao` SET";
            comSql += " 	`strDescricao` = '" + _funcao.getDescricao() + "'";
            comSql += " 	,`bolAtivo` = '" + _funcao.getAtivo() + "'";
            comSql += " WHERE ";
            comSql += " 	`intFuncaoId` = " + _funcao.getIdFuncao() + ";";
            retorno = atualizar();
            identity = _funcao.getIdFuncao();
        }
        
        return retorno;
        
    }  

    public boolean delete(Funcao funcao)
    {
        boolean retorno;
        
        comSql = "";
        comSql += " Delete from tblFuncao ";
        comSql += " where ";
        comSql += " 	`intFuncaoId` = " + funcao.getIdFuncao() + ";";
        retorno = atualizar();
        
        return retorno;
    }
}
