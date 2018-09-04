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

import Bean.Veiculo;
import java.sql.Connection;
import java.util.*;

public class VeiculoDAO extends DAO{

    public VeiculoDAO(Connection conexao) {
        super(conexao);
    }
    
    public List<Veiculo> listaTodos()
    {
        List<Veiculo> lstTabela = new ArrayList();
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblVeiculo`.`intVeiculoId`,";
        comSql += "     `tblVeiculo`.`strPlaca`,";
        comSql += "     `tblVeiculo`.`strMarca`,";
        comSql += "     `tblVeiculo`.`strModelo`,";
        comSql += "     `tblVeiculo`.`strAno`";
        comSql += " FROM `bdGelo`.`tblVeiculo`;";
        
        List tabela = super.listaTodos();
        
        for(int i = 0; i < tabela.size(); i++)
        {
            Veiculo veiculo = new Veiculo();
            List bkp = (ArrayList)tabela.get(i);
            
            veiculo.setIdVeiculo(((Integer)bkp.get(0)).intValue());
            veiculo.setPlaca((String)bkp.get(1));
            veiculo.setMarca((String)bkp.get(2));
            veiculo.setModelo((String)bkp.get(3));
            veiculo.setAno((String)bkp.get(4));
            
            lstTabela.add(veiculo);
        }
        
        return lstTabela;
    }
    
    public Veiculo listaUm(Veiculo veiculo)
    {
        
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblVeiculo`.`intVeiculoId`,";
        comSql += "     `tblVeiculo`.`strPlaca`,";
        comSql += "     `tblVeiculo`.`strMarca`,";
        comSql += "     `tblVeiculo`.`strModelo`,";
        comSql += "     `tblVeiculo`.`strAno`";
        comSql += " FROM `bdGelo`.`tblVeiculo`";
        comSql += " WHERE ";
        comSql += "     `tblVeiculo`.`intVeiculoId` = " + veiculo.getIdVeiculo()+ ";";
        List tabela = super.listaUm();
        
        if(!tabela.isEmpty())
        {
            for(int i = 0; i < tabela.size(); i++)
            {
                
                List bkp = (ArrayList)tabela.get(i);

                veiculo.setIdVeiculo(((Integer)bkp.get(0)).intValue());
                veiculo.setPlaca((String)bkp.get(1));
                veiculo.setMarca((String)bkp.get(2));
                veiculo.setModelo((String)bkp.get(3));
                veiculo.setAno((String)bkp.get(4));

            }  
            return veiculo;
        }
        else
            return null;

    }
    
    public boolean atualizar(Veiculo veiculo) 
    {
        boolean retorno;
        
        Veiculo _veiculo = new Veiculo();
        veiculo.replicar(_veiculo);
       
        if (listaUm(veiculo) == null)
        {
            comSql = "";
            comSql += " INSERT INTO `bdGelo`.`tblVeiculo`";
            comSql += " 	(`strPlaca`,";
            comSql += " 	`strMarca`,";
            comSql += " 	`strModelo`,";
            comSql += " 	`strAno`)";
            comSql += " VALUES";
            comSql += " 	('" + _veiculo.getPlaca() + "'";
            comSql += " 	,'" + _veiculo.getMarca() + "'";
            comSql += " 	,'" + _veiculo.getModelo() + "'";
            comSql += " 	,'" + _veiculo.getAno() + "');";

            retorno = atualizar();
            if(retorno)
            {
                comSql = "";
                comSql += " Select max(intVeiculoId) as idVeiculo from `tblVeiculo`;";
                List lstConsulta = listaUm();
                for(int i = 0; i < lstConsulta.size(); i++)
                {
                    List reg = ((ArrayList)lstConsulta.get(0));
                    identity = ((Integer)reg.get(0)).intValue();
                    _veiculo.setIdVeiculo(((Integer)reg.get(0)).intValue() );
                }
                
            }            
        }
        else
        {
            comSql = "";
            comSql += " UPDATE `bdGelo`.`tblVeiculo` SET";
            comSql += " 	`strPlaca` = '" + _veiculo.getPlaca() + "'";
            comSql += " 	,`strMarca` = '" + _veiculo.getMarca() + "'";
            comSql += " 	,`strModelo` = '" + _veiculo.getModelo() + "'";
            comSql += " 	,`strAno` = '" + _veiculo.getAno() + "'";
            comSql += " WHERE ";
            comSql += " 	`intVeiculoId` = " + _veiculo.getIdVeiculo() + ";";

            retorno = atualizar();
        }
        
        return retorno;
        
    }        
    
}
