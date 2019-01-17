/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Bean.Avaria;
import Bean.AvariaProducao;
import Bean.MaquinaProducao;
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
    
    public List<AvariaProducao> listaTodos(MaquinaProducao maquinaproducao)
    {
        List<AvariaProducao> lstTabela = new ArrayList();
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += "     `tblAvariaProducao`.`intAvariaProducaoId`,";
        comSql += "     `tblAvariaProducao`.`intAvariaId`,";
        comSql += "     `tblAvariaProducao`.`intMaquinaProducaoId`,";
        comSql += "     `tblAvariaProducao`.`dblQuantidade`";
        comSql += " FROM ";
        comSql += "     `smmdaa_bdGelo`.`tblAvariaProducao`";
        comSql += " WHERE";
        comSql += "     `tblAvariaProducao`.`intMaquinaProducaoId` = " + maquinaproducao.getIdMaquinaProducao() + ";";
        List tabela = super.listaTodos();
        
        for(int i = 0; i < tabela.size(); i++)
        {
            AvariaProducao avariaProducao = new AvariaProducao();
            List bkp = (ArrayList)tabela.get(i);
            
            avariaProducao.setIdAvariaProducao(((Integer)bkp.get(0)).intValue());
            avariaProducao.setIdAvaria(((Integer)bkp.get(1)).intValue());
            avariaProducao.setIdMaquinaProducao(((Integer)bkp.get(2)).intValue());
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
        comSql += "     `tblAvariaProducao`.`intMaquinaProducaoId`,";
        comSql += "     `tblAvariaProducao`.`dblQuantidade`";
        comSql += " FROM ";
        comSql += "     `smmdaa_bdGelo`.`tblAvariaProducao`";
        comSql += " WHERE";
        if ((avariaProducao.getIdMaquinaProducao() != 0) && (avariaProducao.getIdAvaria() != 0))
        {
            comSql += "     `tblAvariaProducao`.`intMaquinaProducaoId` = " + avariaProducao.getIdMaquinaProducao();        
            comSql += "     and `tblAvariaProducao`.`intAvariaId` = " + avariaProducao.getIdAvaria() + ";";        
        }
        else
            comSql += "     `tblAvariaProducao`.`intAvariaProducaoId` = " + avariaProducao.getIdAvariaProducao() + ";";        
        List tabela = super.listaUm();
        
        if(!tabela.isEmpty())
        {
            for(int i = 0; i < tabela.size(); i++)
            {
                
                List bkp = (ArrayList)tabela.get(i);

                avariaProducao.setIdAvariaProducao(((Integer)bkp.get(0)).intValue());
                avariaProducao.setIdAvaria(((Integer)bkp.get(1)).intValue());
                avariaProducao.setIdMaquinaProducao(((Integer)bkp.get(2)).intValue());
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
            comSql += " INSERT INTO `smmdaa_bdGelo`.`tblAvariaProducao` ";
            comSql += " (`intAvariaId`";
            comSql += " ,`intMaquinaProducaoId`";
            comSql += " ,`dblQuantidade`)";
            comSql += " VALUES";
            comSql += " (" + _avariaProducao.getIdAvaria();
            comSql += " ," + _avariaProducao.getIdMaquinaProducao();
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
            comSql += " UPDATE `smmdaa_bdGelo`.`tblAvariaProducao` SET";
            comSql += "     `intAvariaId` = " + _avariaProducao.getIdAvaria();
            comSql += "     ,`intMaquinaProducaoId` = " + _avariaProducao.getIdMaquinaProducao();
            comSql += "     ,`dblQuantidade` = " + _avariaProducao.getQuantidade();
            comSql += " WHERE ";
            comSql += "     `intAvariaProducaoId` = " + _avariaProducao.getIdAvariaProducao()+ ";  ";   
            retorno = atualizar();
        }
        
        return retorno;
        
    }
    
}
