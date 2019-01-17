/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Bean.Colaborador;
import Bean.ColaboradorEntrega;
import Bean.MovimentoEntrega;
import Bean.Entrega;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author celso
 */
public class ColaboradorEntregaDAO  extends DAO{

    public ColaboradorEntregaDAO(Connection conexao) {
        super(conexao);
    }
    
    public List<ColaboradorEntrega> listaTodos(Entrega _entrega)
    {
        List<ColaboradorEntrega> lstTabela = new ArrayList();
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblColaboradorEntrega`.`intEntregaColaboradorId`,";
        comSql += "     `tblColaboradorEntrega`.`intColaboradorId`,";
        comSql += "     `tblColaboradorEntrega`.`intEntregaId`,";
        comSql += "     `tblColaboradorEntrega`.`chrMotivo`,";
        comSql += "     `tblColaboradorEntrega`.`chrSituacao`";
        comSql += " FROM `smmdaa_bdGelo`.`tblColaboradorEntrega`";
        comSql += " WHERE ";
        comSql += "     `tblColaboradorEntrega`.`intEntregaId` = " + _entrega.getIdEntrega() ;
        comSql += ";";
        
        List tabela = super.listaTodos();
        
        for(int i = 0; i < tabela.size(); i++)
        {
            ColaboradorEntrega colaboradorEntrega = new ColaboradorEntrega();
            List bkp = (ArrayList)tabela.get(i);
            
            colaboradorEntrega.setIdColaboradorEntrega(((Integer)bkp.get(0)).intValue());
            colaboradorEntrega.setIdColaborador(((Integer)bkp.get(1)).intValue());
            colaboradorEntrega.setIdEntrega(((Integer)bkp.get(2)).intValue());
            colaboradorEntrega.setMotivo((String)bkp.get(3));
            colaboradorEntrega.setSituacao((String)bkp.get(4));
            
            ColaboradorDAO clienteDAO = new ColaboradorDAO(conexao);
            Colaborador cliente = new Colaborador();
            cliente.setIdColaborador(colaboradorEntrega.getIdColaborador());
            cliente = clienteDAO.listaUm(cliente);
            colaboradorEntrega.setCliente(cliente);

            lstTabela.add(colaboradorEntrega);
        }
        
        return lstTabela;
    }
    
    public ColaboradorEntrega listaUm(ColaboradorEntrega colaboradorEntrega)
    {
        
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblColaboradorEntrega`.`intEntregaColaboradorId`,";
        comSql += "     `tblColaboradorEntrega`.`intColaboradorId`,";
        comSql += "     `tblColaboradorEntrega`.`intEntregaId`,";
        comSql += "     `tblColaboradorEntrega`.`chrMotivo`,";
        comSql += "     `tblColaboradorEntrega`.`chrSituacao`";
        comSql += " FROM `smmdaa_bdGelo`.`tblColaboradorEntrega`";
        comSql += " WHERE ";
        if ((colaboradorEntrega.getIdColaborador() != 0) && (colaboradorEntrega.getIdEntrega() != 0))
        {
            comSql += " `tblColaboradorEntrega`.`intColaboradorId` = " + colaboradorEntrega.getIdColaborador();
            comSql += " and `tblColaboradorEntrega`.`intEntregaId` = " + colaboradorEntrega.getIdEntrega();
        }
        else
            comSql += "     `tblColaboradorEntrega`.`intEntregaColaboradorId` = " + colaboradorEntrega.getIdColaboradorEntrega();
        comSql += ";";
        
        List tabela = super.listaUm();
        
        if(!tabela.isEmpty())
        {
            for(int i = 0; i < tabela.size(); i++)
            {
                
                List bkp = (ArrayList)tabela.get(i);

                colaboradorEntrega.setIdColaboradorEntrega(((Integer)bkp.get(0)).intValue());
                colaboradorEntrega.setIdColaborador(((Integer)bkp.get(1)).intValue());
                colaboradorEntrega.setIdEntrega(((Integer)bkp.get(2)).intValue());
                colaboradorEntrega.setMotivo((String)bkp.get(3));
                colaboradorEntrega.setSituacao((String)bkp.get(4));
                
                ColaboradorDAO clienteDAO = new ColaboradorDAO(conexao);
                Colaborador cliente = new Colaborador();
                cliente.setIdColaborador(colaboradorEntrega.getIdColaborador());
                cliente = clienteDAO.listaUm(cliente);
                colaboradorEntrega.setCliente(cliente);   
                
                List<MovimentoEntrega> lstMovimentoEntrega = new ArrayList<MovimentoEntrega>();
                MovimentoEntregaDAO movimentoEntregaDAO = new MovimentoEntregaDAO(conexao);
                lstMovimentoEntrega = movimentoEntregaDAO.listaTodos(colaboradorEntrega);
                colaboradorEntrega.setLstMovimentoEntrega(lstMovimentoEntrega);
                
            }  
            return colaboradorEntrega;
        }
        else
            return null;

    }
    
    public boolean atualizar(ColaboradorEntrega colaboradorEntrega) 
    {
        boolean retorno;
        double vlProduto = 0;
        double vlDesconto = 0;
       
        ColaboradorEntrega _colaboradorEntrega = new ColaboradorEntrega();
        colaboradorEntrega.replicar(_colaboradorEntrega);
       
        if (listaUm(colaboradorEntrega) == null)
        {
            comSql = "";
            comSql += " INSERT INTO `smmdaa_bdGelo`.`tblColaboradorEntrega`";
            comSql += " 	(`intColaboradorId`,";
            comSql += " 	`intEntregaId`,";
            comSql += " 	`chrMotivo`,";
            comSql += " 	`chrSituacao`)";
            comSql += " VALUES";
            comSql += " 	(" + _colaboradorEntrega.getIdColaborador();
            comSql += " 	," + _colaboradorEntrega.getIdEntrega();
            comSql += " 	,'" + _colaboradorEntrega.getMotivo() + "'";
            comSql += " 	,'" + _colaboradorEntrega.getSituacao() + "');";
            

            
            retorno = atualizar();
            if(retorno)
            {
                comSql = "";
                comSql += " Select max(intEntregaColaboradorId) as idColaboradorEntrega from `smmdaa_bdGelo`.`tblColaboradorEntrega`;";
                List lstConsulta = listaUm();
                for(int i = 0; i < lstConsulta.size(); i++)
                {
                    List reg = ((ArrayList)lstConsulta.get(0));
                    identity = ((Integer)reg.get(0)).intValue();
                    _colaboradorEntrega.setIdColaboradorEntrega(((Integer)reg.get(0)).intValue() );
                }
                
            }            
        }
        else
        {
            comSql = "";
            comSql += " UPDATE `smmdaa_bdGelo`.`tblColaboradorEntrega` SET ";
            comSql += " 	`intColaboradorId` = " + _colaboradorEntrega.getIdColaborador();
            comSql += " 	,`intEntregaId` = " + _colaboradorEntrega.getIdEntrega();
            comSql += " 	,`chrMotivo` = '" + _colaboradorEntrega.getMotivo() + "'";
            comSql += " 	,`chrSituacao` = '" + _colaboradorEntrega.getSituacao() + "'";
            comSql += " WHERE ";
            comSql += " 	`intEntregaColaboradorId` = " + _colaboradorEntrega.getIdColaboradorEntrega() + ";";

            retorno = atualizar();
        }
        
        if (retorno)
        {
            if (_colaboradorEntrega.getLstMovimentoEntrega() != null)
            {
                MovimentoEntregaDAO movimentoEntregaDAO = new MovimentoEntregaDAO(conexao);
                Iterator itMovEnt = _colaboradorEntrega.getLstMovimentoEntrega().iterator();
                while (itMovEnt.hasNext())
                {
                    MovimentoEntrega movimentoEntrega = (MovimentoEntrega)itMovEnt.next();
                    movimentoEntrega.setIdColaboradorEntrega(_colaboradorEntrega.getIdColaboradorEntrega());
                    movimentoEntregaDAO.atualizar(movimentoEntrega);
                }
                
            }
        }
        
       
        return retorno;
        
    }  
    
    public boolean delete(ColaboradorEntrega colaboradorEntrega)
    {
        boolean retorno = false;
        
        if (colaboradorEntrega != null)
        {
            comSql = "";
            comSql += " Delete from ";
            comSql += " `smmdaa_bdGelo`.`tblColaboradorEntrega`";
            comSql += " WHERE ";
            comSql += " 	`intEntregaColaboradorId` = " + colaboradorEntrega.getIdColaboradorEntrega() + ";";
            retorno = atualizar();
        }
        
        return retorno;
        
    }
    
}
