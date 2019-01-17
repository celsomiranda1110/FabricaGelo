/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Bean.Avaria;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author celso
 */
public class AvariaDAO extends DAO{

    public AvariaDAO(Connection conexao) {
        super(conexao);
    }
    
    
    
    public List<Avaria> listaTodos()
    {
        List<Avaria> lstTabela = new ArrayList();
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += "     `tblAvaria`.`intAvariaId`,";
        comSql += "     `tblAvaria`.`strDescricao`";
        comSql += " FROM ";
        comSql += "     `smmdaa_bdGelo`.`tblAvaria`;";
        List tabela = super.listaTodos();
        
        for(int i = 0; i < tabela.size(); i++)
        {
            Avaria avaria = new Avaria();
            List bkp = (ArrayList)tabela.get(i);
            
            avaria.setIdAvaria(((Integer)bkp.get(0)).intValue());
            avaria.setDescricao((String)bkp.get(1));
            
            lstTabela.add(avaria);
        }
        
        return lstTabela;
    }
    
    public Avaria listaUm(Avaria avaria)
    {
        
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += "     `tblAvaria`.`intAvariaId`,";
        comSql += "     `tblAvaria`.`strDescricao`";
        comSql += " FROM ";
        comSql += "     `smmdaa_bdGelo`.`tblAvaria`";
        comSql += " WHERE ";
        comSql += "     `tblAvaria`.`intAvariaId` = " + avaria.getIdAvaria() + ";";
        List tabela = super.listaUm();
        
        if(!tabela.isEmpty())
        {
            for(int i = 0; i < tabela.size(); i++)
            {
                
                List bkp = (ArrayList)tabela.get(i);

                avaria.setIdAvaria(((Integer)bkp.get(0)).intValue());
                avaria.setDescricao((String)bkp.get(1));
                
            }  
            return avaria;
        }
        else
            return null;

    }
    
    public boolean atualizar(Avaria avaria) 
    {
        boolean retorno;
        
        Avaria _avaria = new Avaria();
        avaria.replicar(_avaria);
        
        if (listaUm(avaria) == null)
        {
            comSql = "";
            comSql += " INSERT INTO `smmdaa_bdGelo`.`tblAvaria` ";
            comSql += " (`strDescricao`)";
            comSql += " VALUES";
            comSql += " ('" + _avaria.getDescricao() + "');";
            
            retorno = atualizar();
            if(retorno)
            {
                comSql = "";
                comSql += " Select max(intAvariaId) as idAvaria from `tblAvaria`;";
                List lstConsulta = listaUm();
                for(int i = 0; i < lstConsulta.size(); i++)
                {
                    List reg = ((ArrayList)lstConsulta.get(0));
                    identity = ((Integer)reg.get(0)).intValue();
                    _avaria.setIdAvaria( ((Integer)reg.get(0)).intValue() );
                }
                
            }            
        }
        else
        {
            comSql = "";
            comSql += " UPDATE `smmdaa_bdGelo`.`tblAvaria` SET";
            comSql += "	`strDescricao` = '" + _avaria.getDescricao() + "'";
            comSql += " WHERE ";
            comSql += "	`intAvariaId` = " + _avaria.getIdAvaria()+ ";  ";   
            retorno = atualizar();
        }
        
        return retorno;
        
    }
    
}
