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
import Bean.CustoEntrega;
import Bean.Entrega;
import java.sql.Connection;
import java.util.*;

public class CustoEntregaDAO extends DAO{

    public CustoEntregaDAO(Connection conexao) {
        super(conexao);
    }
    
    public List<CustoEntrega> listaTodos(Entrega entrega)
    {
        List<CustoEntrega> lstTabela = new ArrayList();
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblCustoEntrega`.`intCustoEntregaId`,";
        comSql += "     `tblCustoEntrega`.`intEntregaId`,";
        comSql += "     `tblCustoEntrega`.`strDescricao`,";
        comSql += "     `tblCustoEntrega`.`dblValor`";
        comSql += " FROM `bdGelo`.`tblCustoEntrega`";
        comSql += " WHERE ";
        comSql += "     `tblCustoEntrega`.`intEntregaId` = " + entrega.getIdEntrega() + ";";
        
        List tabela = super.listaTodos();
        
        for(int i = 0; i < tabela.size(); i++)
        {
            CustoEntrega custoEntrega = new CustoEntrega();
            List bkp = (ArrayList)tabela.get(i);
            
            custoEntrega.setIdCustoEntrega(((Integer)bkp.get(0)).intValue());
            custoEntrega.setIdEntrega(((Integer)bkp.get(1)).intValue());
            custoEntrega.setDescricao((String)bkp.get(2));
            custoEntrega.setValor((Double)bkp.get(3));

            lstTabela.add(custoEntrega);
        }
        
        return lstTabela;
    }
    
    public CustoEntrega listaUm(CustoEntrega custoEntrega)
    {
        
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblCustoEntrega`.`intCustoEntregaId`,";
        comSql += "     `tblCustoEntrega`.`intEntregaId`,";
        comSql += "     `tblCustoEntrega`.`strDescricao`,";
        comSql += "     `tblCustoEntrega`.`dblValor`";
        comSql += " FROM `bdGelo`.`tblCustoEntrega`";
        comSql += " WHERE ";
        comSql += "     `tblCustoEntrega`.`intCustoEntregaId` = " + custoEntrega.getIdCustoEntrega()+ ";";
        List tabela = super.listaUm();
        
        if(!tabela.isEmpty())
        {
            for(int i = 0; i < tabela.size(); i++)
            {
                
                List bkp = (ArrayList)tabela.get(i);

            custoEntrega.setIdCustoEntrega(((Integer)bkp.get(0)).intValue());
            custoEntrega.setIdEntrega(((Integer)bkp.get(1)).intValue());
            custoEntrega.setDescricao((String)bkp.get(2));
            custoEntrega.setValor((Double)bkp.get(3));

                
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
            comSql += " INSERT INTO `bdGelo`.`tblCustoEntrega`";
            comSql += " 	(`intEntregaId`,";
            comSql += " 	`strDescricao`,";
            comSql += " 	`dblValor`)";
            comSql += " VALUES";
            comSql += " 	(" + _custoEntrega.getIdEntrega();
            comSql += " 	,'" + _custoEntrega.getDescricao() + "'";
            comSql += " 	," + _custoEntrega.getValor() + ");";

            
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
                    _custoEntrega.setIdCustoEntrega(((Integer)reg.get(0)).intValue() );
                }
                
            }            
        }
        else
        {
            comSql = "";
            comSql += " UPDATE `bdGelo`.`tblCustoEntrega` SET ";
            comSql += " 	`intEntregaId` = " + _custoEntrega.getIdEntrega();
            comSql += " 	,`strDescricao` = '" + _custoEntrega.getDescricao() + "'";
            comSql += " 	,`dblValor` = " + _custoEntrega.getValor();
            comSql += " WHERE ";
            comSql += " 	`intCustoEntregaId` = " + _custoEntrega.getIdCustoEntrega() + " ;";
            retorno = atualizar();
        }
        
        return retorno;
        
    } 
    
    public boolean deleta(CustoEntrega custoEntrega)
    {
        comSql = "";
        comSql += " Delete from";
        comSql += " `bdGelo`.`tblCustoEntrega` ";
        comSql += " where ";
        comSql += " `tblCustoEntrega`.`intCustoEntregaId` = " + custoEntrega.getIdCustoEntrega() + ";";
        
        return atualizar();
    }
}
