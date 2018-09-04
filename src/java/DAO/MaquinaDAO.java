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
import Bean.Maquina;
import java.sql.Connection;
import java.util.*;

public class MaquinaDAO extends DAO{

    public MaquinaDAO(Connection conexao) {
        super(conexao);
    }
    
    public List<Maquina> listaTodos()
    {
        List<Maquina> lstTabela = new ArrayList();
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblMaquina`.`intMaquinaId`,";
        comSql += "     `tblMaquina`.`strDescricao`,";
        comSql += "     `tblMaquina`.`chrSituacao`";
        comSql += " FROM `bdGelo`.`tblMaquina`;";
        
        List tabela = super.listaTodos();
        
        for(int i = 0; i < tabela.size(); i++)
        {
            Maquina maquina = new Maquina();
            List bkp = (ArrayList)tabela.get(i);
            
            maquina.setIdMaquina(((Integer)bkp.get(0)).intValue());
            maquina.setDescricao((String)bkp.get(1));
            maquina.setSituacao((String)bkp.get(2));
            

            lstTabela.add(maquina);
        }
        
        return lstTabela;
    }
    
    public Maquina listaUm(Maquina maquina)
    {
        
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblMaquina`.`intMaquinaId`,";
        comSql += "     `tblMaquina`.`strDescricao`,";
        comSql += "     `tblMaquina`.`chrSituacao`";
        comSql += " FROM `bdGelo`.`tblMaquina`";
        comSql += " WHERE ";
        comSql += "     `tblMaquina`.`intMaquinaId` = " + maquina.getIdMaquina()+ ";";
        List tabela = super.listaUm();
        
        if(!tabela.isEmpty())
        {
            for(int i = 0; i < tabela.size(); i++)
            {
                
                List bkp = (ArrayList)tabela.get(i);

            maquina.setIdMaquina(((Integer)bkp.get(0)).intValue());
            maquina.setDescricao((String)bkp.get(1));
            maquina.setSituacao((String)bkp.get(2));
            
            
                
            }  
            return maquina;
        }
        else
            return null;

    }
    
    public boolean atualizar(Maquina maquina) 
    {
        boolean retorno;
        
        Maquina _maquina = new Maquina();
        maquina.replicar(_maquina);
       
        if (listaUm(maquina) == null)
        {
            comSql = "";
            comSql += " INSERT INTO `bdGelo`.`tblMaquina`";
            comSql += " 	(`strDescricao`,";
            comSql += " 	`chrSituacao`)";
            comSql += " VALUES";
            comSql += " 	('" + _maquina.getDescricao() + "'";
            comSql += " 	,'" + _maquina.getSituacao() + "');";

            
            retorno = atualizar();
            if(retorno)
            {
                comSql = "";
                comSql += " Select max(intMaquinaId) as idMaquina from `tblMaquina`;";
                List lstConsulta = listaUm();
                for(int i = 0; i < lstConsulta.size(); i++)
                {
                    List reg = ((ArrayList)lstConsulta.get(0));
                    identity = ((Integer)reg.get(0)).intValue();
                    _maquina.setIdMaquina(((Integer)reg.get(0)).intValue() );
                }
                
            }            
        }
        else
        {
            comSql = "";
            comSql += " UPDATE `bdGelo`.`tblMaquina` SET";
            comSql += " 	`strDescricao` = '" + _maquina.getDescricao() + "',";
            comSql += " 	`chrSituacao` = '" + _maquina.getSituacao() + "'";
            comSql += " WHERE ";
            comSql += " 	`intMaquinaId` = " + _maquina.getIdMaquina() + ";";
            retorno = atualizar();
        }
        
        return retorno;
        
    }      
}
