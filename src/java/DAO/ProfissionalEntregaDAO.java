/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Bean.Entrega;
import Bean.Funcao;
import Bean.Profissional;
import Bean.ProfissionalEntrega;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author celso
 */
public class ProfissionalEntregaDAO extends DAO{

    public ProfissionalEntregaDAO(Connection conexao) {
        super(conexao);
    }
    
    public List<ProfissionalEntrega> listaTodos(Entrega _entrega)
    {
        List<ProfissionalEntrega> lstTabela = new ArrayList();
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblProfissionalEntrega`.`intProfissionalEntregaId`,";
        comSql += "     `tblProfissionalEntrega`.`intProfissionalId`,";
        comSql += "     `tblProfissionalEntrega`.`intEntregaId`,";
        comSql += "     `tblProfissionalEntrega`.`bolResponsavel`";
        comSql += " FROM `smmdaa_bdGelo`.`tblProfissionalEntrega`";
        comSql += " WHERE ";
        comSql += "     `tblProfissionalEntrega`.`intEntregaId` = " + _entrega.getIdEntrega() + ";";
        
        
        List tabela = super.listaTodos();
        
        for(int i = 0; i < tabela.size(); i++)
        {
            ProfissionalEntrega profissionalEntrega = new ProfissionalEntrega();
            List bkp = (ArrayList)tabela.get(i);
            
            profissionalEntrega.setIdProfissionalEntrega(((Integer)bkp.get(0)).intValue());
            profissionalEntrega.setIdProfissional(((Integer)bkp.get(1)).intValue());
            profissionalEntrega.setIdEntrega(((Integer)bkp.get(2)).intValue());
            profissionalEntrega.setResponsavel((String)bkp.get(3));
            
            ProfissionalDAO profissionalDAO = new ProfissionalDAO(conexao);
            Profissional profissional = new Profissional();
            profissional.setIdProfissional(profissionalEntrega.getIdProfissional());
            profissional = profissionalDAO.listaUm(profissional);
            profissionalEntrega.setProfissional(profissional);
            
//            EntregaDAO entregaDAO = new EntregaDAO(conexao);
//            Entrega entrega = new Entrega();
//            entrega.setIdEntrega(profissionalEntrega.getIdEntrega());
//            entrega = entregaDAO.listaUm(entrega);
//            profissionalEntrega.setEntrega(entrega);
              
            

            lstTabela.add(profissionalEntrega);
        }
        
        return lstTabela;
    }
    
    public ProfissionalEntrega listaUm(ProfissionalEntrega profissionalEntrega)
    {
        
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblProfissionalEntrega`.`intProfissionalEntregaId`,";
        comSql += "     `tblProfissionalEntrega`.`intProfissionalId`,";
        comSql += "     `tblProfissionalEntrega`.`intEntregaId`,";
        comSql += "     `tblProfissionalEntrega`.`bolResponsavel`";
        comSql += " FROM `smmdaa_bdGelo`.`tblProfissionalEntrega`";
        comSql += " WHERE ";
        comSql += "     `tblProfissionalEntrega`.`intProfissionalEntregaId` = " + profissionalEntrega.getIdProfissionalEntrega()+ ";";
        List tabela = super.listaUm();
        
        if(!tabela.isEmpty())
        {
            for(int i = 0; i < tabela.size(); i++)
            {
                
                List bkp = (ArrayList)tabela.get(i);

                profissionalEntrega.setIdProfissionalEntrega(((Integer)bkp.get(0)).intValue());
                profissionalEntrega.setIdProfissional(((Integer)bkp.get(1)).intValue());
                profissionalEntrega.setIdEntrega(((Integer)bkp.get(2)).intValue());
                profissionalEntrega.setResponsavel((String)bkp.get(3));
                
                ProfissionalDAO profissionalDAO = new ProfissionalDAO(conexao);
                Profissional profissional = new Profissional();
                profissional.setIdProfissional(profissionalEntrega.getIdProfissional());
                profissional = profissionalDAO.listaUm(profissional);
                profissionalEntrega.setProfissional(profissional);

//                EntregaDAO entregaDAO = new EntregaDAO(conexao);
//                Entrega entrega = new Entrega();
//                entrega.setIdEntrega(profissionalEntrega.getIdEntrega());
//                entrega = entregaDAO.listaUm(entrega);
//                profissionalEntrega.setEntrega(entrega);                
                
            }  
            return profissionalEntrega;
        }
        else
            return null;

    }
    
    public boolean atualizar(ProfissionalEntrega profissionalEntrega) 
    {
        boolean retorno;
        
        ProfissionalEntrega _profissionalEntrega = new ProfissionalEntrega();
        profissionalEntrega.replicar(_profissionalEntrega);
       
        if (listaUm(profissionalEntrega) == null)
        {
            comSql = "";
            comSql += " INSERT INTO `smmdaa_bdGelo`.`tblProfissionalEntrega`";
            comSql += " 	(`intProfissionalId`,";
            comSql += " 	`intEntregaId`,";
            comSql += " 	`bolResponsavel`)";
            comSql += " VALUES";
            comSql += " 	(" + _profissionalEntrega.getIdProfissional();
            comSql += " 	," + _profissionalEntrega.getIdEntrega();
            comSql += " 	,'" + _profissionalEntrega.getResponsavel() + "')";
            
            
            retorno = atualizar();
            if(retorno)
            {
                comSql = "";
                comSql += " Select max(intProfissionalEntregaId) as idProfissionalEntrega from `tblProfissionalEntrega`;";
                List lstConsulta = listaUm();
                for(int i = 0; i < lstConsulta.size(); i++)
                {
                    List reg = ((ArrayList)lstConsulta.get(0));
                    identity = ((Integer)reg.get(0)).intValue();
                    _profissionalEntrega.setIdProfissionalEntrega(((Integer)reg.get(0)).intValue() );
                }
                
            }            
        }
        else
        {
            comSql = "";
            comSql += " UPDATE `smmdaa_bdGelo`.`tblProfissionalEntrega` SET";
            comSql += " 	`intProfissionalId` = " + _profissionalEntrega.getIdProfissional();
            comSql += " 	,`intEntregaId` = " + _profissionalEntrega.getIdEntrega();
            comSql += " 	,`bolResponsavel` = '" + _profissionalEntrega.getResponsavel() + "'";
            comSql += " WHERE ";
            comSql += " 	`intProfissionalEntregaId` = " + _profissionalEntrega.getIdProfissionalEntrega() + ";";
            retorno = atualizar();
        }
        
        return retorno;
        
    }      
    
    public boolean deletar(ProfissionalEntrega profissionalEntrega)
    {
        boolean retorno = true;
        if (profissionalEntrega != null)
        {
            comSql = "";
            comSql += " Delete from `smmdaa_bdGelo`.`tblProfissionalEntrega`";
            comSql += " where ";
            comSql += " 	`intProfissionalEntregaId` = " + profissionalEntrega.getIdProfissionalEntrega() + ";";
            retorno = atualizar();
        }
        return retorno;
    }
    
}
