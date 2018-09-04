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
import Bean.Colaborador;
import Bean.VisitaColaborador;
import java.sql.Connection;
import java.util.*;

public class VisitaColaboradorDAO extends DAO{

    public VisitaColaboradorDAO(Connection conexao) {
        super(conexao);
    }
    
    public List<VisitaColaborador> listaTodos(Colaborador colaborador)
    {
        List<VisitaColaborador> lstTabela = new ArrayList();
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblVisitaColaborador`.`intVisitaColaboradorId`,";
        comSql += "     `tblVisitaColaborador`.`intColaboradorId`,";
        comSql += "     `tblVisitaColaborador`.`intDia`,";
        comSql += "     `tblVisitaColaborador`.`strAtivo`";
        comSql += " FROM `bdGelo`.`tblVisitaColaborador`";
        comSql += " WHERE ";
        comSql += "     `tblVisitaColaborador`.`intColaboradorId` = " + colaborador.getIdColaborador() + ";";
        
        List tabela = super.listaTodos();
        
        for(int i = 0; i < tabela.size(); i++)
        {
            VisitaColaborador visitaColaborador = new VisitaColaborador();
            List bkp = (ArrayList)tabela.get(i);
            
            visitaColaborador.setIdVisitaColaborador(((Integer)bkp.get(0)).intValue());
            visitaColaborador.setIdColaborador(((Integer)bkp.get(1)).intValue());
            visitaColaborador.setDia(((Integer)bkp.get(2)).intValue());
            visitaColaborador.setAtivo((String)bkp.get(3));
                        
            lstTabela.add(visitaColaborador);
        }
        
        return lstTabela;
    }
    
    public VisitaColaborador listaUm(VisitaColaborador visitaColaborador)
    {
        
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblVisitaColaborador`.`intVisitaColaboradorId`,";
        comSql += "     `tblVisitaColaborador`.`intColaboradorId`,";
        comSql += "     `tblVisitaColaborador`.`intDia`,";
        comSql += "     `tblVisitaColaborador`.`strAtivo`";
        comSql += " FROM `bdGelo`.`tblVisitaColaborador`";
        comSql += " WHERE ";
        comSql += "     `tblVisitaColaborador`.`intVisitaColaboradorId` = " + visitaColaborador.getIdVisitaColaborador()+ ";";
        List tabela = super.listaUm();
        
        if(!tabela.isEmpty())
        {
            for(int i = 0; i < tabela.size(); i++)
            {
                
                List bkp = (ArrayList)tabela.get(i);

                visitaColaborador.setIdVisitaColaborador(((Integer)bkp.get(0)).intValue());
                visitaColaborador.setIdColaborador(((Integer)bkp.get(1)).intValue());
                visitaColaborador.setDia(((Integer)bkp.get(2)).intValue());
                visitaColaborador.setAtivo((String)bkp.get(3));

            }  
            return visitaColaborador;
        }
        else
            return null;

    }
    
    public boolean atualizar(VisitaColaborador visitaColaborador) 
    {
        boolean retorno;
        
        VisitaColaborador _visitaColaborador = new VisitaColaborador();
        visitaColaborador.replicar(_visitaColaborador);
       
        if (listaUm(visitaColaborador) == null)
        {
            comSql = "";
            comSql += " INSERT INTO `bdGelo`.`tblVisitaColaborador`";
            comSql += " 	(`intColaboradorId`,";
            comSql += " 	`intDia`,";
            comSql += " 	`strAtivo`)";
            comSql += " VALUES";
            comSql += " 	(" + _visitaColaborador.getIdColaborador();
            comSql += " 	," + _visitaColaborador.getDia();
            comSql += " 	,'" + _visitaColaborador.getAtivo() + "');";

            retorno = atualizar();
            if(retorno)
            {
                comSql = "";
                comSql += " Select max(intVisitaColaboradorId) as idVisitaColaborador from `tblVisitaColaborador`;";
                List lstConsulta = listaUm();
                for(int i = 0; i < lstConsulta.size(); i++)
                {
                    List reg = ((ArrayList)lstConsulta.get(0));
                    identity = ((Integer)reg.get(0)).intValue();
                    _visitaColaborador.setIdVisitaColaborador(((Integer)reg.get(0)).intValue() );
                }
                
            }            
        }
        else
        {
            comSql = "";
            comSql += " UPDATE `bdGelo`.`tblVisitaColaborador` SET";
            comSql += " 	`intColaboradorId` = " + _visitaColaborador.getIdColaborador();
            comSql += " 	,`intDia` = " + _visitaColaborador.getDia() ;
            comSql += " 	,`strAtivo` = '" + _visitaColaborador.getAtivo() + "'";
            comSql += " WHERE ";
            comSql += " 	`intVisitaColaboradorId` = " + _visitaColaborador.getIdVisitaColaborador() + ";";

            retorno = atualizar();
        }
        
        return retorno;
        
    }      
    
}
