/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Bean.Avaria;
import Bean.AvariaProducao;
import Bean.Producao;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author celso
 */
public class AvariaProducaoDAO extends DAO{

    public AvariaProducaoDAO(Connection conexao) {
        super(conexao);
    }
    
    public List<AvariaProducao> listaTodos(Producao producao)
    {
        List<AvariaProducao> lstTabela = new ArrayList();
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += "     `tblAvariaProducao`.`intAvariaProducaoId`,";
        comSql += "     `tblAvariaProducao`.`intAvariaId`,";
        comSql += "     `tblAvariaProducao`.`intProducaoId`,";
        comSql += "     `tblAvariaProducao`.`dblQuantidade`";
        comSql += " FROM ";
        comSql += "     `bdGelo`.`tblAvariaProducao`";
        comSql += " WHERE";
        comSql += "     `tblAvariaProducao`.`intProducaoId` = " + producao.getIdProducao() + ";";
        List tabela = super.listaTodos();
        
        for(int i = 0; i < tabela.size(); i++)
        {
            AvariaProducao avariaProducao = new AvariaProducao();
            List bkp = (ArrayList)tabela.get(i);
            
            avariaProducao.setIdAvariaProducao(((Integer)bkp.get(0)).intValue());
            avariaProducao.setIdAvaria(((Integer)bkp.get(1)).intValue());
            avariaProducao.setIdProducao(((Integer)bkp.get(2)).intValue());
            avariaProducao.setQuantidade((Double)bkp.get(3));
            
            AvariaDAO avariaDAO = new AvariaDAO(conexao);
            Avaria avaria = new Avaria();
            avaria.setIdAvaria(avariaProducao.getIdAvaria());
            avaria = avariaDAO.listaUm(avaria);
            avariaProducao.setAvaria(avaria);
            
            lstTabela.add(avariaProducao);
        }
        
        return lstTabela;
    }
    
    public AvariaProducao listaUm(AvariaProducao avariaProducao)
    {
        
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += "     `tblAvariaProducao`.`intAvariaProducaoId`,";
        comSql += "     `tblAvariaProducao`.`intAvariaId`,";
        comSql += "     `tblAvariaProducao`.`intProducaoId`,";
        comSql += "     `tblAvariaProducao`.`dblQuantidade`";
        comSql += " FROM ";
        comSql += "     `bdGelo`.`tblAvariaProducao`";
        comSql += " WHERE";
        comSql += "     `tblAvariaProducao`.`intAvariaProducaoId` = " + avariaProducao.getIdAvariaProducao() + ";";        
        List tabela = super.listaUm();
        
        if(!tabela.isEmpty())
        {
            for(int i = 0; i < tabela.size(); i++)
            {
                
                List bkp = (ArrayList)tabela.get(i);

                avariaProducao.setIdAvariaProducao(((Integer)bkp.get(0)).intValue());
                avariaProducao.setIdAvaria(((Integer)bkp.get(1)).intValue());
                avariaProducao.setIdProducao(((Integer)bkp.get(2)).intValue());
                avariaProducao.setQuantidade((Double)bkp.get(3));

                AvariaDAO avariaDAO = new AvariaDAO(conexao);
                Avaria avaria = new Avaria();
                avaria.setIdAvaria(avariaProducao.getIdAvaria());
                avaria = avariaDAO.listaUm(avaria);
                avariaProducao.setAvaria(avaria);
                
            }  
            return avariaProducao;
        }
        else
            return null;

    }
    
    public boolean atualizar(AvariaProducao avariaProducao) 
    {
        boolean retorno;
        
        AvariaProducao _avariaProducao = new AvariaProducao();
        avariaProducao.replicar(_avariaProducao);
        
        if (listaUm(avariaProducao) == null)
        {
            comSql = "";
            comSql += " INSERT INTO `bdGelo`.`tblAvariaProducao` ";
            comSql += " (`intAvariaId`";
            comSql += " ,`intProducaoId`";
            comSql += " ,`dblQuantidade`)";
            comSql += " VALUES";
            comSql += " (" + _avariaProducao.getIdAvaria();
            comSql += " ," + _avariaProducao.getIdProducao();
            comSql += " ," + _avariaProducao.getQuantidade() + ");";
            
            retorno = atualizar();
            if(retorno)
            {
                comSql = "";
                comSql += " Select max(intAvariaProducaoId) as idAvariaProducao from `tblAvariaProducao`;";
                List lstConsulta = listaUm();
                for(int i = 0; i < lstConsulta.size(); i++)
                {
                    List reg = ((ArrayList)lstConsulta.get(0));
                    identity = ((Integer)reg.get(0)).intValue();
                    _avariaProducao.setIdAvariaProducao( ((Integer)reg.get(0)).intValue() );
                }
                
            }            
        }
        else
        {
            comSql = "";
            comSql += " UPDATE `bdGelo`.`tblAvariaProducao` SET";
            comSql += "     `intAvariaId`` = " + _avariaProducao.getIdAvaria();
            comSql += "     ,`intProducaoId` = " + _avariaProducao.getIdProducao();
            comSql += "     ,`dblQuantidade` = " + _avariaProducao.getQuantidade();
            comSql += " WHERE ";
            comSql += "     `intAvariaProducaoId` = " + _avariaProducao.getIdAvariaProducao()+ ";  ";   
            retorno = atualizar();
        }
        
        return retorno;
        
    }
    
}
