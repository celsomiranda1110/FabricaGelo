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

import Bean.Camara;
import java.sql.Connection;
import java.util.*;

public class CamaraDAO extends DAO{

    public CamaraDAO(Connection conexao) {
        super(conexao);
    }
    
    public List<Camara> listaTodos()
    {
        List<Camara> lstTabela = new ArrayList();
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblCamara`.`intCamaraId`,";
        comSql += "     `tblCamara`.`strDescricao`,";
        comSql += "     `tblCamara`.`dblCapacidade`,";
        comSql += "     `tblCamara`.`chrSituacao`";
        comSql += " FROM ";
        comSql += " 	`bdGelo`.`tblCamara`;";
        
        List tabela = super.listaTodos();
        
        for(int i = 0; i < tabela.size(); i++)
        {
            Camara camara = new Camara();
            List bkp = (ArrayList)tabela.get(i);
            
            camara.setIdCamara(((Integer)bkp.get(0)).intValue());
            camara.setDescricao((String)bkp.get(1));
            camara.setCapacidade((Double)bkp.get(2));
            camara.setSituacao((String)bkp.get(3));
            
            lstTabela.add(camara);
        }
        
        return lstTabela;
    }    
    
    public Camara listaUm(Camara camara)
    {
        
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblCamara`.`intCamaraId`,";
        comSql += "     `tblCamara`.`strDescricao`,";
        comSql += "     `tblCamara`.`dblCapacidade`,";
        comSql += "     `tblCamara`.`chrSituacao`";
        comSql += " FROM ";
        comSql += " 	`bdGelo`.`tblCamara`";
        comSql += " WHERE ";
        comSql += "     `tblCamara`.`intCamaraId` = " + camara.getIdCamara() + ";";
        List tabela = super.listaUm();
        
        if(!tabela.isEmpty())
        {
            for(int i = 0; i < tabela.size(); i++)
            {
                
                List bkp = (ArrayList)tabela.get(i);

                camara.setIdCamara(((Integer)bkp.get(0)).intValue());
                camara.setDescricao((String)bkp.get(1));
                camara.setCapacidade((Double)bkp.get(2));
                camara.setSituacao((String)bkp.get(3));                
            }  
            return camara;
        }
        else
            return null;

    } 
    
    public boolean atualizar(Camara camara) 
    {
        boolean retorno;
        
        Camara _camara = new Camara();
        camara.replicar(_camara);
        
        if (listaUm(camara) == null)
        {
            comSql = "";
            comSql += " INSERT INTO `bdGelo`.`tblCamara`";
            comSql += " (`strDescricao`,";
            comSql += " `dblCapacidade`,";
            comSql += " `chrSituacao`)";
            comSql += " VALUES";
            comSql += " ('" + _camara.getDescricao() + "'";
            comSql += " ," + _camara.getCapacidade();
            comSql += " ,'" + _camara.getSituacao() + "');";

            
            retorno = atualizar();
            
            if(retorno)
            {
                comSql = "";
                comSql += " Select max(intCamaraId) as idCamara from `tblCamara`;";
                List lstConsulta = listaUm();
                for(int i = 0; i < lstConsulta.size(); i++)
                {
                    List reg = ((ArrayList)lstConsulta.get(0));
                    identity = ((Integer)reg.get(0)).intValue();
                    _camara.setIdCamara( ((Integer)reg.get(0)).intValue() );
                }
                
            }            
        }
        else
        {
            comSql = "";
            comSql += " UPDATE `bdGelo`.`tblCamara` SET ";
            comSql += " 	`strDescricao` = '" + _camara.getDescricao() + "',";
            comSql += " 	`dblCapacidade` = " + _camara.getCapacidade() + ",";
            comSql += " 	`chrSituacao` = '" + _camara.getSituacao() + "'";
            comSql += " WHERE ";
            comSql += " 	`intCamaraId` = " + _camara.getIdCamara() + ";";
            retorno = atualizar();
        }
        
        return retorno;
        
    }
}
