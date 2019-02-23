/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Bean.TipoColaborador;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author celso
 */
public class TipoColaboradorDAO extends DAO{

    public TipoColaboradorDAO(Connection conexao) {
        super(conexao);
    }
    
    public List<TipoColaborador> listaTodos(TipoColaborador _tipoColaborador)
    {
        List<TipoColaborador> lstTabela = new ArrayList();
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += "     `tblTipoColaborador`.`intTipoColaboradorId`,";
        comSql += "     `tblTipoColaborador`.`strDescricao`,";
        comSql += "     `tblTipoColaborador`.`bolAtivo`";
        comSql += " FROM ";
        comSql += "     `smmdaa_bdGelo`.`tblTipoColaborador`";
        if (_tipoColaborador != null)
        {
            comSql += " WHERE ";
            comSql += "     `tblTipoColaborador`.`bolAtivo` = '" + _tipoColaborador.getAtivo() + "'";
        }
        comSql += ";";
        
        List tabela = super.listaTodos();
        
        for(int i = 0; i < tabela.size(); i++)
        {
            TipoColaborador tipoColaborador = new TipoColaborador();
            List bkp = (ArrayList)tabela.get(i);
            
            tipoColaborador.setIdTipoColaborador(((Integer)bkp.get(0)).intValue());
            tipoColaborador.setDescricao((String)bkp.get(1));
            tipoColaborador.setAtivo((String)bkp.get(2));
            
            lstTabela.add(tipoColaborador);
        }
        
        return lstTabela;
    }
    
    public TipoColaborador listaUm(TipoColaborador tipoColaborador)
    {
        
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += "     `tblTipoColaborador`.`intTipoColaboradorId`,";
        comSql += "     `tblTipoColaborador`.`strDescricao`,";
        comSql += "     `tblTipoColaborador`.`bolAtivo`";
        comSql += " FROM ";
        comSql += "     `smmdaa_bdGelo`.`tblTipoColaborador`";
        comSql += " WHERE ";
        comSql += "     `tblTipoColaborador`.`intTipoColaboradorId` = " + tipoColaborador.getIdTipoColaborador() + ";";
        List tabela = super.listaUm();
        
        if(!tabela.isEmpty())
        {
            for(int i = 0; i < tabela.size(); i++)
            {
                
                List bkp = (ArrayList)tabela.get(i);

                tipoColaborador.setIdTipoColaborador(((Integer)bkp.get(0)).intValue());
                tipoColaborador.setDescricao((String)bkp.get(1));
                tipoColaborador.setAtivo((String)bkp.get(2));
            }  
            return tipoColaborador;
        }
        else
            return null;

    }
    
    public boolean atualizar(TipoColaborador tipoColaborador) 
    {
        boolean retorno;
        
        TipoColaborador _tipoColaborador = new TipoColaborador();
        tipoColaborador.replicar(_tipoColaborador);
        
        if (listaUm(tipoColaborador) == null)
        {
            comSql = "";
            comSql += " INSERT INTO `smmdaa_bdGelo`.`tblTipoColaborador` ";
            comSql += " (`strDescricao`,`bolAtivo`)";
            comSql += " VALUES";
            comSql += " ('" + _tipoColaborador.getDescricao() + "',";
            comSql += "  '" + _tipoColaborador.getAtivo() + "');";
            
            retorno = atualizar();
            if(retorno)
            {
                comSql = "";
                comSql += " Select max(intTipoColaboradorId) as idTipoColaborador from `tblTipoColaborador`;";
                List lstConsulta = listaUm();
                for(int i = 0; i < lstConsulta.size(); i++)
                {
                    List reg = ((ArrayList)lstConsulta.get(0));
                    identity = ((Integer)reg.get(0)).intValue();
                    _tipoColaborador.setIdTipoColaborador( ((Integer)reg.get(0)).intValue() );
                }
                
            }            
        }
        else
        {
            comSql = "";
            comSql += " UPDATE `smmdaa_bdGelo`.`tblTipoColaborador` SET";
            comSql += "	`strDescricao` = '" + _tipoColaborador.getDescricao() + "'";
            comSql += "	,`bolAtivo` = '" + _tipoColaborador.getAtivo() + "'";
            comSql += " WHERE ";
            comSql += "	`intTipoColaboradorId` = " + _tipoColaborador.getIdTipoColaborador()+ ";  ";   
            retorno = atualizar();
        }
        
        return retorno;
        
    }   
    
    public boolean delete(TipoColaborador tipoColaborador)
    {
        boolean retorno;
        
        comSql = "";
        comSql += " Delete from `smmdaa_bdGelo`.`tblTipoColaborador` ";
        comSql += " where ";
        comSql += "	`intTipoColaboradorId` = " + tipoColaborador.getIdTipoColaborador()+ ";  ";   
        retorno = atualizar();
        
        return retorno;
    }
}
