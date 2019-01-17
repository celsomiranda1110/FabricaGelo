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

import Bean.Funcao;
import Bean.Profissional;
import java.sql.Connection;
import java.util.*;

public class ProfissionalDAO extends DAO{

    public ProfissionalDAO(Connection conexao) {
        super(conexao);
    }
    
    public List<Profissional> listaTodos()
    {
        List<Profissional> lstTabela = new ArrayList();
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblProfissional`.`intProfissionalId`,";
        comSql += "     `tblProfissional`.`intFuncaoId`,";
        comSql += "     `tblProfissional`.`strCTPS`,";
        comSql += "     `tblProfissional`.`strCpf`,";
        comSql += "     `tblProfissional`.`strNome`,";
        comSql += "     `tblProfissional`.`strCelular`,";
        comSql += "     `tblProfissional`.`strSenha`,";
        comSql += "     `tblProfissional`.`strUsuario`";
        comSql += " FROM `smmdaa_bdGelo`.`tblProfissional`";
        comSql += " ORDER BY ";
        comSql += " `tblProfissional`.`strNome`;";
        
        List tabela = super.listaTodos();
        
        for(int i = 0; i < tabela.size(); i++)
        {
            Profissional profissional = new Profissional();
            List bkp = (ArrayList)tabela.get(i);
            
            profissional.setIdProfissional(((Integer)bkp.get(0)).intValue());
            profissional.setIdFuncao(((Integer)bkp.get(1)).intValue());
            profissional.setCtps((String)bkp.get(2));
            profissional.setCpf((String)bkp.get(3));
            profissional.setNome((String)bkp.get(4));
            profissional.setCelular((String)bkp.get(5));
            profissional.setSenha((String)bkp.get(6));
            profissional.setUsuario((String)bkp.get(7));
            
            FuncaoDAO funcaoDAO = new FuncaoDAO(conexao);
            Funcao funcao = new Funcao();
            funcao.setIdFuncao(profissional.getIdFuncao());
            funcao = funcaoDAO.listaUm(funcao);
            profissional.setFuncao(funcao);

            lstTabela.add(profissional);
        }
        
        return lstTabela;
    }
    
    public Profissional listaUm(Profissional profissional)
    {
        
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblProfissional`.`intProfissionalId`,";
        comSql += "     `tblProfissional`.`intFuncaoId`,";
        comSql += "     `tblProfissional`.`strCTPS`,";
        comSql += "     `tblProfissional`.`strCpf`,";
        comSql += "     `tblProfissional`.`strNome`,";
        comSql += "     `tblProfissional`.`strCelular`,";
        comSql += "     `tblProfissional`.`strSenha`,";
        comSql += "     `tblProfissional`.`strUsuario`";
        comSql += " FROM `smmdaa_bdGelo`.`tblProfissional`";
        comSql += " WHERE ";
        comSql += "     `tblProfissional`.`intProfissionalId` = " + profissional.getIdProfissional()+ ";";
        List tabela = super.listaUm();
        
        if(!tabela.isEmpty())
        {
            for(int i = 0; i < tabela.size(); i++)
            {
                
                List bkp = (ArrayList)tabela.get(i);

                profissional.setIdProfissional(((Integer)bkp.get(0)).intValue());
                profissional.setIdFuncao(((Integer)bkp.get(1)).intValue());
                profissional.setCtps((String)bkp.get(2));
                profissional.setCpf((String)bkp.get(3));
                profissional.setNome((String)bkp.get(4));
                profissional.setCelular((String)bkp.get(5));
                profissional.setSenha((String)bkp.get(6));
                profissional.setUsuario((String)bkp.get(7));

                FuncaoDAO funcaoDAO = new FuncaoDAO(conexao);
                Funcao funcao = new Funcao();
                funcao.setIdFuncao(profissional.getIdFuncao());
                funcao = funcaoDAO.listaUm(funcao);
                profissional.setFuncao(funcao);
                
            }  
            return profissional;
        }
        else
            return null;

    }
    
    public boolean atualizar(Profissional profissional) 
    {
        boolean retorno;
        
        Profissional _profissional = new Profissional();
        profissional.replicar(_profissional);
       
        if (listaUm(profissional) == null)
        {
            comSql = "";
            comSql += " INSERT INTO `smmdaa_bdGelo`.`tblProfissional`";
            comSql += " 	(`intFuncaoId`,";
            comSql += " 	`strCTPS`,";
            comSql += " 	`strCpf`,";
            comSql += " 	`strNome`,";
            comSql += " 	`strCelular`,";
            comSql += " 	`strSenha`,";
            comSql += " 	`strUsuario`)";
            comSql += " VALUES";
            comSql += " 	(" + _profissional.getIdFuncao();
            comSql += " 	,'" + _profissional.getCtps() + "'";
            comSql += " 	,'" + _profissional.getCpf() + "'";
            comSql += " 	,'" + _profissional.getNome() + "'";
            comSql += " 	,'" + _profissional.getCelular() + "'";
            comSql += " 	,'" + _profissional.getSenha() + "'";
            comSql += " 	,'" + _profissional.getUsuario() + "');";
            
            retorno = atualizar();
            if(retorno)
            {
                comSql = "";
                comSql += " Select max(intProfissionalId) as idProfissional from `tblProfissional`;";
                List lstConsulta = listaUm();
                for(int i = 0; i < lstConsulta.size(); i++)
                {
                    List reg = ((ArrayList)lstConsulta.get(0));
                    identity = ((Integer)reg.get(0)).intValue();
                    _profissional.setIdProfissional(((Integer)reg.get(0)).intValue() );
                }
                
            }            
        }
        else
        {
            comSql = "";
            comSql += " UPDATE `smmdaa_bdGelo`.`tblProfissional` SET";
            comSql += " 	`intFuncaoId` = " + _profissional.getIdFuncao();
            comSql += " 	,`strCTPS` = <'" + _profissional.getCtps() + "'";
            comSql += " 	,`strCpf` = '" + _profissional.getCpf() + "'";
            comSql += " 	,`strNome` = '" + _profissional.getNome() + "'";
            comSql += " 	,`strCelular` = '" + _profissional.getCelular() + "'";
            comSql += " 	,`strUsuario` = '" + _profissional.getUsuario() + "'";
            comSql += " 	,`strSenha` = '" + _profissional.getSenha() + "'";           
            comSql += " WHERE ";
            comSql += " 	`intProfissionalId` = " + _profissional.getIdProfissional() + ";";
            retorno = atualizar();
        }
        
        return retorno;
        
    }      
}
