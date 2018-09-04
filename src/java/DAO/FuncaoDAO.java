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
    
    public List<Funcao> listaTodos()
    {
        List<Funcao> lstTabela = new ArrayList();
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblFuncao`.`intFuncaoId`,";
        comSql += "     `tblFuncao`.`strDescricao`";
        comSql += " FROM `bdGelo`.`tblFuncao`;";
        
        List tabela = super.listaTodos();
        
        for(int i = 0; i < tabela.size(); i++)
        {
            Funcao funcao = new Funcao();
            List bkp = (ArrayList)tabela.get(i);
            
            funcao.setIdFuncao(((Integer)bkp.get(0)).intValue());
            funcao.setDescricao((String)bkp.get(1));
            

            lstTabela.add(funcao);
        }
        
        return lstTabela;
    }
    
    public Funcao listaUm(Funcao funcao)
    {
        
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblFuncao`.`intFuncaoId`,";
        comSql += "     `tblFuncao`.`strDescricao`";
        comSql += " FROM `bdGelo`.`tblFuncao`";
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
            comSql += " INSERT INTO `bdGelo`.`tblFuncao`";
            comSql += " 	(`strDescricao`)";
            comSql += " VALUES";
            comSql += " 	('" + _funcao.getDescricao() + "');";

            
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
            comSql += " UPDATE `bdGelo`.`tblFuncao` SET";
            comSql += " 	`strDescricao` = '" + _funcao.getDescricao() + "'";
            comSql += " WHERE ";
            comSql += " 	`intFuncaoId` = " + _funcao.getIdFuncao() + ";";
            retorno = atualizar();
        }
        
        return retorno;
        
    }      
}
