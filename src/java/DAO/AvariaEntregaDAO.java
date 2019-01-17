/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Bean.Avaria;
import Bean.AvariaEntrega;
import Bean.ProdutoMovimento;
import Bean.ProdutoEntrega;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author celso
 */
public class AvariaEntregaDAO extends DAO{

    public AvariaEntregaDAO(Connection conexao) {
        super(conexao);
    }
    
    public List<AvariaEntrega> listaTodos(ProdutoEntrega produtoEntrega)
    {
        List<AvariaEntrega> lstTabela = new ArrayList();
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += "     `tblAvariaEntrega`.`intAvariaEntregaId`,";
        comSql += "     `tblAvariaEntrega`.`intAvariaId`,";
        comSql += "     `tblAvariaEntrega`.`intProdutoEntregaId`,";
        comSql += "     `tblAvariaEntrega`.`dblQuantidade`";
        comSql += " FROM ";
        comSql += "     `smmdaa_bdGelo`.`tblAvariaEntrega`";
        comSql += " WHERE";
        comSql += "     `tblAvariaEntrega`.`intProdutoEntregaId` = " + produtoEntrega.getIdProdutoEntrega() + ";";
        List tabela = super.listaTodos();
        
        for(int i = 0; i < tabela.size(); i++)
        {
            AvariaEntrega avariaEntrega = new AvariaEntrega();
            List bkp = (ArrayList)tabela.get(i);
            
            avariaEntrega.setIdAvariaEntrega(((Integer)bkp.get(0)).intValue());
            avariaEntrega.setIdAvaria(((Integer)bkp.get(1)).intValue());
            avariaEntrega.setIdProdutoEntrega(((Integer)bkp.get(2)).intValue());
            avariaEntrega.setQuantidade((Double)bkp.get(3));
            
            // Avaria
            AvariaDAO avariaDAO = new AvariaDAO(conexao);
            Avaria avaria = new Avaria();
            avaria.setIdAvaria(avariaEntrega.getIdAvaria());
            avaria = avariaDAO.listaUm(avaria);
            avariaEntrega.setAvaria(avaria);

            
            lstTabela.add(avariaEntrega);
        }
        
        return lstTabela;
    }
    
    public AvariaEntrega listaUm(AvariaEntrega avariaEntrega)
    {
        
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += "     `tblAvariaEntrega`.`intAvariaEntregaId`,";
        comSql += "     `tblAvariaEntrega`.`intAvariaId`,";
        comSql += "     `tblAvariaEntrega`.`intProdutoEntregaId`,";
        comSql += "     `tblAvariaEntrega`.`dblQuantidade`";
        comSql += " FROM ";
        comSql += "     `smmdaa_bdGelo`.`tblAvariaEntrega`";
        comSql += " WHERE";
        comSql += "     `tblAvariaEntrega`.`intAvariaEntregaId` = " + avariaEntrega.getIdAvariaEntrega() + ";";        
        List tabela = super.listaUm();
        
        if(!tabela.isEmpty())
        {
            for(int i = 0; i < tabela.size(); i++)
            {
                
                List bkp = (ArrayList)tabela.get(i);

                avariaEntrega.setIdAvariaEntrega(((Integer)bkp.get(0)).intValue());
                avariaEntrega.setIdAvaria(((Integer)bkp.get(1)).intValue());
                avariaEntrega.setIdProdutoEntrega(((Integer)bkp.get(2)).intValue());
                avariaEntrega.setQuantidade((Double)bkp.get(3));
                
                // Avaria
                AvariaDAO avariaDAO = new AvariaDAO(conexao);
                Avaria avaria = new Avaria();
                avaria.setIdAvaria(avariaEntrega.getIdAvaria());
                avaria = avariaDAO.listaUm(avaria);
                avariaEntrega.setAvaria(avaria);                
                
            }  
            return avariaEntrega;
        }
        else
            return null;

    }
    
    public boolean atualizar(AvariaEntrega avariaProducao) 
    {
        boolean retorno;
        
        AvariaEntrega _avariaProducao = new AvariaEntrega();
        avariaProducao.replicar(_avariaProducao);
        
        if (listaUm(avariaProducao) == null)
        {
            comSql = "";
            comSql += " INSERT INTO `smmdaa_bdGelo`.`tblAvariaEntrega` ";
            comSql += " (`intAvariaId`";
            comSql += " ,`intProdutoEntregaId`";
            comSql += " ,`dblQuantidade`)";
            comSql += " VALUES";
            comSql += " (" + _avariaProducao.getIdAvaria();
            comSql += " ," + _avariaProducao.getIdProdutoEntrega();
            comSql += " ," + _avariaProducao.getQuantidade() + ");";
            
            retorno = atualizar();
            if(retorno)
            {
                comSql = "";
                comSql += " Select max(intAvariaEntregaId) as idAvariaEntrega from `tblAvariaEntrega`;";
                List lstConsulta = listaUm();
                for(int i = 0; i < lstConsulta.size(); i++)
                {
                    List reg = ((ArrayList)lstConsulta.get(0));
                    identity = ((Integer)reg.get(0)).intValue();
                    _avariaProducao.setIdAvariaEntrega( ((Integer)reg.get(0)).intValue() );
                }
                
            }            
        }
        else
        {
            comSql = "";
            comSql += " UPDATE `smmdaa_bdGelo`.`tblAvariaEntrega` SET";
            comSql += "     `intAvariaId`` = " + _avariaProducao.getIdAvaria();
            comSql += "     ,`intProdutoEntregaId` = " + _avariaProducao.getIdProdutoEntrega();
            comSql += "     ,`dblQuantidade` = " + _avariaProducao.getQuantidade();
            comSql += " WHERE ";
            comSql += "     `intAvariaEntregaId` = " + _avariaProducao.getIdAvariaEntrega()+ ";  ";   
            retorno = atualizar();
        }
        
        return retorno;
        
    }    
    
    public boolean delete(AvariaEntrega avariaEntrega)
    {
        boolean retorno = false;
        
        if(avariaEntrega != null)
        {
            comSql = "";
            comSql += " Delete from `smmdaa_bdGelo`.`tblAvariaEntrega`";
            comSql += " WHERE ";
            comSql += "     `intAvariaEntregaId` = " + avariaEntrega.getIdAvariaEntrega() + ";  ";
            retorno = atualizar();
        }
        return retorno;
    }
}
