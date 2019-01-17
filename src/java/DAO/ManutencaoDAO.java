/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Bean.Colaborador;
import Bean.Equipamento;
import Bean.Manutencao;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author celso
 */
public class ManutencaoDAO extends DAO{

    public ManutencaoDAO(Connection conexao) {
        super(conexao);
    }
    
    public List<Manutencao> listaTodos(Equipamento equipamento)
    {
        List<Manutencao> lstTabela = new ArrayList();
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblManutencao`.`intManutencaoId`,";
        comSql += "     `tblManutencao`.`intEquipamentoId`,";
        comSql += "     `tblManutencao`.`intColaboradorId`,";
        comSql += "     `tblManutencao`.`strMotivo`,";
        comSql += "     `tblManutencao`.`datDataInicio`,";
        comSql += "     `tblManutencao`.`datDataFim`,";
        comSql += "     `tblManutencao`.`datGarantia`,";
        comSql += "     `tblManutencao`.`strMotivo`,";
        comSql += "     `tblManutencao`.`dblValor`";
        comSql += " FROM `smmdaa_bdGelo`.`tblManutencao`";
        comSql += " WHERE ";
        comSql += "     `tblManutencao`.`intEquipamentoId` = " + equipamento.getIdEquipamento();
        
        List tabela = super.listaTodos();
        
        for(int i = 0; i < tabela.size(); i++)
        {
            Manutencao manutencao = new Manutencao();
            List bkp = (ArrayList)tabela.get(i);
            
            manutencao.setIdManutencao(((Integer)bkp.get(0)).intValue());
            manutencao.setIdEquipamento(((Integer)bkp.get(1)).intValue());
            manutencao.setIdColaborador(((Integer)bkp.get(2)).intValue());
            manutencao.setMotivo((String)bkp.get(3));
            manutencao.setDataInicio((Date)bkp.get(4));
            manutencao.setDataFim((Date)bkp.get(5));
            manutencao.setDataGarantia((Date)bkp.get(6));
            manutencao.setMotivo((String)bkp.get(7));
            manutencao.setValor((Double)bkp.get(8));

            Colaborador colaborador = new Colaborador();
            ColaboradorDAO colaboradorDAO = new ColaboradorDAO(conexao);
            colaborador.setIdColaborador(manutencao.getIdColaborador());
            colaborador = colaboradorDAO.listaUm(colaborador);
            manutencao.setColaborador(colaborador);
            
            lstTabela.add(manutencao);
        }
        
        return lstTabela;
    }
    
    public Manutencao listaUm(Manutencao manutencao)
    {
        
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblManutencao`.`intManutencaoId`,";
        comSql += "     `tblManutencao`.`intEquipamentoId`,";
        comSql += "     `tblManutencao`.`intColaboradorId`,";
        comSql += "     `tblManutencao`.`strMotivo`,";
        comSql += "     `tblManutencao`.`datDataInicio`,";
        comSql += "     `tblManutencao`.`datDataFim`,";
        comSql += "     `tblManutencao`.`datGarantia`,";
        comSql += "     `tblManutencao`.`strMotivo`,";
        comSql += "     `tblManutencao`.`dblValor`";
        comSql += " FROM `smmdaa_bdGelo`.`tblManutencao`";
        comSql += " WHERE ";
        comSql += "     `tblManutencao`.`intManutencaoId` = " + manutencao.getIdManutencao()+ ";";
        List tabela = super.listaUm();
        
        if(!tabela.isEmpty())
        {
            for(int i = 0; i < tabela.size(); i++)
            {
                
                List bkp = (ArrayList)tabela.get(i);

                manutencao.setIdManutencao(((Integer)bkp.get(0)).intValue());
                manutencao.setIdEquipamento(((Integer)bkp.get(1)).intValue());
                manutencao.setIdColaborador(((Integer)bkp.get(2)).intValue());
                manutencao.setMotivo((String)bkp.get(3));
                manutencao.setDataInicio((Date)bkp.get(4));
                manutencao.setDataFim((Date)bkp.get(5));
                manutencao.setDataGarantia((Date)bkp.get(6));
                manutencao.setMotivo((String)bkp.get(7));
                manutencao.setValor((Double)bkp.get(8));
                
                Colaborador colaborador = new Colaborador();
                ColaboradorDAO colaboradorDAO = new ColaboradorDAO(conexao);
                colaborador.setIdColaborador(manutencao.getIdColaborador());
                colaborador = colaboradorDAO.listaUm(colaborador);
                manutencao.setColaborador(colaborador);            
                
            }  
            return manutencao;
        }
        else
            return null;

    }
    
    public boolean atualizar(Manutencao manutencao) 
    {
        boolean retorno;
        
        Manutencao _manutencao = new Manutencao();
        manutencao.replicar(_manutencao);
       
        if (listaUm(manutencao) == null)
        {
            comSql = "";
            comSql += " INSERT INTO `smmdaa_bdGelo`.`tblManutencao`";
            comSql += " 	(`intEquipamentoId`,";
            comSql += " 	`intColaboradorId`,";
            comSql += " 	`strMotivo`,";
            comSql += " 	`datDataInicio`,";
            comSql += " 	`datDataFim`,";
            comSql += " 	`datGarantia`,";
            comSql += " 	`dblValor`)";
            comSql += " VALUES";
            comSql += " 	(" + _manutencao.getIdEquipamento();
            comSql += " 	," + _manutencao.getIdColaborador();
            comSql += " 	,'" + _manutencao.getMotivo() + "'";
            comSql += " 	,'" + _manutencao.getDataInicioFormatada() + "'";
            comSql += " 	,'" + _manutencao.getDataFimFormatada() + "'";
            comSql += " 	,'" + _manutencao.getDataGarantiaFormatada() + "'";
            comSql += " 	," + _manutencao.getValor() + ");";
            
            

            
            retorno = atualizar();
            if(retorno)
            {
                comSql = "";
                comSql += " Select max(intManutencaoId) as idManutencao from `tblManutencao`;";
                List lstConsulta = listaUm();
                for(int i = 0; i < lstConsulta.size(); i++)
                {
                    List reg = ((ArrayList)lstConsulta.get(0));
                    identity = ((Integer)reg.get(0)).intValue();
                    _manutencao.setIdManutencao(((Integer)reg.get(0)).intValue() );
                }
                
            }            
        }
        else
        {
            comSql = "";
            comSql += " UPDATE `smmdaa_bdGelo`.`tblManutencao` SET";
            comSql += " 	`intEquipamentoId` = " + _manutencao.getIdEquipamento();
            comSql += " 	,`intColaboradorId` = " + _manutencao.getIdColaborador();
            comSql += " 	,`strMotivo` = '" + _manutencao.getMotivo() + "'";
            comSql += " 	,`datDataInicio` = '" + _manutencao.getDataInicioFormatada() + "'";
            comSql += " 	,`datDataFim` = '" + _manutencao.getDataFimFormatada() + "'";
            comSql += " 	,`datGarantia` = " + _manutencao.getDataGarantiaFormatada() + "'";
            comSql += " 	,`dblValor` = " + _manutencao.getValor();
            comSql += " WHERE ";
            comSql += " 	`intManutencaoId` = " + _manutencao.getIdManutencao() + ";";
            retorno = atualizar();
        }
        
        return retorno;
        
    }    
}
