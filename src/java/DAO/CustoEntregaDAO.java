/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Bean.CustoEntrega;
import Bean.Entrega;
import Bean.Colaborador;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author celso
 */
public class CustoEntregaDAO extends DAO{

    public CustoEntregaDAO(Connection conexao) {
        super(conexao);
    }
    
    public List<CustoEntrega> listaTodos(Entrega entrega)
    {
        List<CustoEntrega> lstTabela = new ArrayList();
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += "     `tblCustoEntrega`.`intCustoEntregaId`,";
        comSql += "     `tblCustoEntrega`.`intEntregaId`,";
        comSql += "     `tblCustoEntrega`.`intColaboradorId`,";
        comSql += "     `tblCustoEntrega`.`strNumero`,";
        comSql += "     `tblCustoEntrega`.`strDescricao`,";
        comSql += "     `tblCustoEntrega`.`dblValorUnitario`,";
        comSql += "     `tblCustoEntrega`.`dblQuantidade`,";
        comSql += "     `tblCustoEntrega`.`dblVlTotal`";
        comSql += " FROM ";
        comSql += "     `smmdaa_bdGelo`.`tblCustoEntrega`";
        comSql += " WHERE ";
        comSql += "     `tblCustoEntrega`.`intEntregaId` = " + entrega.getIdEntrega() + ";";
        
        List tabela = super.listaTodos();
        
        for(int i = 0; i < tabela.size(); i++)
        {
            CustoEntregaDAO custoEntregaDAO = new CustoEntregaDAO(conexao);
            List bkp = (ArrayList)tabela.get(i);
            
            CustoEntrega custoEntrega = new CustoEntrega();
            custoEntrega.setIdCustoEntrega(((Integer)bkp.get(0)).intValue());
            custoEntrega.setIdEntrega(((Integer)bkp.get(1)).intValue());
            custoEntrega.setIdColaborador(((Integer)bkp.get(2)).intValue());
            custoEntrega.setNumero((String)bkp.get(3));
            custoEntrega.setNumero((String)bkp.get(4));
            custoEntrega.setVlUnitario((Double)bkp.get(5));
            custoEntrega.setQuantidade((Double)bkp.get(6));
            custoEntrega.setVlTotal((Double)bkp.get(7));
            
            ColaboradorDAO colaboradorDAO = new ColaboradorDAO(conexao);
            Colaborador colaborador = new Colaborador();
            colaborador.setIdColaborador(custoEntrega.getIdColaborador());
            colaborador = colaboradorDAO.listaUm(colaborador);
            custoEntrega.setColaborador(colaborador);
            
            lstTabela.add(custoEntrega);
        }
        
        return lstTabela;
    }
    
    public CustoEntrega listaUm(CustoEntrega custoEntrega)
    {
        
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += "     `tblCustoEntrega`.`intCustoEntregaId`,";
        comSql += "     `tblCustoEntrega`.`intEntregaId`,";
        comSql += "     `tblCustoEntrega`.`intColaboradorId`,";
        comSql += "     `tblCustoEntrega`.`strNumero`,";
        comSql += "     `tblCustoEntrega`.`strDescricao`,";
        comSql += "     `tblCustoEntrega`.`dblValorUnitario`,";
        comSql += "     `tblCustoEntrega`.`dblQuantidade`,";
        comSql += "     `tblCustoEntrega`.`dblVlTotal`";
        comSql += " FROM ";
        comSql += "     `smmdaa_bdGelo`.`tblCustoEntrega`";
        comSql += " WHERE ";
        comSql += "     `tblCustoEntrega`.`intCustoEntregaId` = " + custoEntrega.getIdCustoEntrega() + ";";
        List tabela = super.listaUm();
        
        if(!tabela.isEmpty())
        {
            for(int i = 0; i < tabela.size(); i++)
            {
                
                List bkp = (ArrayList)tabela.get(i);

                custoEntrega.setIdCustoEntrega(((Integer)bkp.get(0)).intValue());
                custoEntrega.setIdEntrega(((Integer)bkp.get(1)).intValue());
                custoEntrega.setIdColaborador(((Integer)bkp.get(2)).intValue());
                custoEntrega.setNumero((String)bkp.get(3));
                custoEntrega.setNumero((String)bkp.get(4));
                custoEntrega.setVlUnitario((Double)bkp.get(5));
                custoEntrega.setQuantidade((Double)bkp.get(6));
                custoEntrega.setVlTotal((Double)bkp.get(7));
                
                ColaboradorDAO colaboradorDAO = new ColaboradorDAO(conexao);
                Colaborador colaborador = new Colaborador();
                colaborador.setIdColaborador(custoEntrega.getIdColaborador());
                colaborador = colaboradorDAO.listaUm(colaborador);
                custoEntrega.setColaborador(colaborador);                
                
            }  
            return custoEntrega;
        }
        else
            return null;

    }
    
    public boolean atualizar(CustoEntrega custoEntrega) 
    {
        boolean retorno;
        
        CustoEntrega _custoEntrega = new CustoEntrega();
        custoEntrega.replicar(_custoEntrega);
        
        if (listaUm(custoEntrega) == null)
        {
            comSql = "";
            comSql += " INSERT INTO `smmdaa_bdGelo`.`tblCustoEntrega` ";
            comSql += "     (";
            comSql += "     `tblCustoEntrega`.`intEntregaId`,";
            comSql += "     `tblCustoEntrega`.`intColaboradorId`,";
            comSql += "     `tblCustoEntrega`.`strNumero`,";
            comSql += "     `tblCustoEntrega`.`strDescricao`,";
            comSql += "     `tblCustoEntrega`.`dblValorUnitario`,";
            comSql += "     `tblCustoEntrega`.`dblQuantidade`,";
            comSql += "     `tblCustoEntrega`.`dblVlTotal`)";
            comSql += " VALUES";
            comSql += " (" + _custoEntrega.getIdEntrega();
            comSql += " ," + _custoEntrega.getIdColaborador();
            comSql += " ,'" + _custoEntrega.getNumero() + "'";
            comSql += " ,'" + _custoEntrega.getDescricao() + "'";
            comSql += " ," + _custoEntrega.getVlUnitario();
            comSql += " ," + _custoEntrega.getQuantidade() ;
            comSql += " ," + _custoEntrega.getVlTotal() + ")";
            
            retorno = atualizar();
            if(retorno)
            {
                comSql = "";
                comSql += " Select max(intCustoEntregaId) as idCustoEntrega from `tblCustoEntrega`;";
                List lstConsulta = listaUm();
                for(int i = 0; i < lstConsulta.size(); i++)
                {
                    List reg = ((ArrayList)lstConsulta.get(0));
                    identity = ((Integer)reg.get(0)).intValue();
                    _custoEntrega.setIdCustoEntrega( ((Integer)reg.get(0)).intValue() );
                }
                
            }            
        }
        else
        {
            comSql = "";
            comSql += " UPDATE `smmdaa_bdGelo`.`tblCustoEntrega` SET";
            comSql += "     `tblCustoEntrega`.`intEntregaId` = " + _custoEntrega.getIdEntrega();
            comSql += "     ,`tblCustoEntrega`.`intColaboradorId` = " + _custoEntrega.getIdColaborador();
            comSql += "     ,`tblCustoEntrega`.`strDescricao` = '" + _custoEntrega.getDescricao() + "'";
            comSql += "     ,`tblCustoEntrega`.`strNumero` = '" + _custoEntrega.getNumero() + "'";
            comSql += "     ,`tblCustoEntrega`.`dblValorUnitario` = " + _custoEntrega.getVlUnitario();
            comSql += "     ,`tblCustoEntrega`.`dblQuantidade` = " + _custoEntrega.getQuantidade();
            comSql += "     ,`tblCustoEntrega`.`dblVlTotal` = " + _custoEntrega.getVlTotal();
            comSql += " WHERE ";
            comSql += "	`intCustoEntregaId` = " + _custoEntrega.getIdCustoEntrega()+ ";  ";   
            retorno = atualizar();
        }
        if (retorno)
            identity = _custoEntrega.getIdCustoEntrega();
        
        return retorno;
        
    }
    
    public boolean delete(CustoEntrega custoEntrega)
    {
        boolean retorno = false;
        
        if (custoEntrega != null)
        {
            comSql = "";
            comSql += " Delete from `smmdaa_bdGelo`.`tblCustoEntrega` ";
            comSql += " WHERE ";
            comSql += "	`intCustoEntregaId` = " + custoEntrega.getIdCustoEntrega() + ";  ";
            retorno = atualizar();
        }
        
        return retorno;
    }
        
}
