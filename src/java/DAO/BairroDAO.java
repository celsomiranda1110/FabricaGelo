/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Bean.Bairro;
import java.sql.Connection;
import java.util.*;

/**
 *
 * @author celso
 */
public class BairroDAO extends DAO{
    
    public BairroDAO(Connection conexao)
    {
        super(conexao);
    }  
    
    public List<Bairro> listaTodos()
    {
        List<Bairro> lstTabela = new ArrayList();
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += "     `tblBairro`.`intBairroId`,";
        comSql += "     `tblBairro`.`strDescricao`";
        comSql += " FROM ";
        comSql += "     `bdGelo`.`tblBairro`;";
        List tabela = super.listaTodos();
        
        for(int i = 0; i < tabela.size(); i++)
        {
            Bairro bairro = new Bairro();
            List bkp = (ArrayList)tabela.get(i);
            
            bairro.setIdBairro(((Integer)bkp.get(0)).intValue());
            bairro.setDescricao((String)bkp.get(1));
            
            lstTabela.add(bairro);
        }
        
        return lstTabela;
    }
    
    public Bairro listaUm(Bairro bairro)
    {
        
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += "     `tblBairro`.`intBairroId`,";
        comSql += "     `tblBairro`.`strDescricao`";
        comSql += " FROM ";
        comSql += "     `bdGelo`.`tblBairro`";
        comSql += " WHERE ";
        comSql += "     `tblBairro`.`intBairroId` = " + bairro.getIdBairro() + ";";
        List tabela = super.listaUm();
        
        if(!tabela.isEmpty())
        {
            for(int i = 0; i < tabela.size(); i++)
            {
                
                List bkp = (ArrayList)tabela.get(i);

                bairro.setIdBairro(((Integer)bkp.get(0)).intValue());
                bairro.setDescricao((String)bkp.get(1));
                
            }  
            return bairro;
        }
        else
            return null;

    }
    
    public boolean atualizar(Bairro bairro) 
    {
        boolean retorno;
        
        Bairro _bairro = new Bairro();
        bairro.replicar(_bairro);
        
        if (listaUm(bairro) == null)
        {
            comSql = "";
            comSql += " INSERT INTO `bdGelo`.`tblBairro` ";
            comSql += " (`strDescricao`)";
            comSql += " VALUES";
            comSql += " ('" + _bairro.getDescricao() + "');";
            
            retorno = atualizar();
            if(retorno)
            {
                comSql = "";
                comSql += " Select max(intBairroId) as idBairro from `tblBairro`;";
                List lstConsulta = listaUm();
                for(int i = 0; i < lstConsulta.size(); i++)
                {
                    List reg = ((ArrayList)lstConsulta.get(0));
                    identity = ((Integer)reg.get(0)).intValue();
                    _bairro.setIdBairro( ((Integer)reg.get(0)).intValue() );
                }
                
            }            
        }
        else
        {
            comSql = "";
            comSql += " UPDATE `bdGelo`.`tblBairro` SET";
            comSql += "	`strDescricao` = '" + _bairro.getDescricao() + "'";
            comSql += " WHERE ";
            comSql += "	`intBairroId` = " + _bairro.getIdBairro()+ ";  ";   
            retorno = atualizar();
        }
        
        return retorno;
        
    }
}
