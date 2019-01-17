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

import Bean.Equipamento;
import Bean.ProdutoCamara;
import Bean.SaidaCamara;
import java.sql.Connection;
import java.util.*;

public class SaidaCamaraDAO extends DAO{

    public SaidaCamaraDAO(Connection conexao) {
        super(conexao);
    }
    
    public List<SaidaCamara> listaTodos(Equipamento veiculo)
    {
        List<SaidaCamara> lstTabela = new ArrayList();
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblSaidaCamara`.`intSaidaCamaraId`,";
        comSql += "     `tblSaidaCamara`.`intEquipamentoId`,";
        comSql += "     `tblSaidaCamara`.`intProdutoCamaraId`,";
        comSql += "     `tblSaidaCamara`.`dblSaida`,";
        comSql += "     `tblSaidaCamara`.`dblDevolucao`,";
        comSql += "     `tblSaidaCamara`.`datData`,";
        comSql += "     `tblSaidaCamara`.`chrSituacao`";
        comSql += " FROM `smmdaa_bdGelo`.`tblSaidaCamara`";
        if (veiculo != null)
        {
            comSql += " WHERE ";
            comSql += "     `tblSaidaCamara`.`intEquipamentoId` = " + veiculo.getIdEquipamento();
        }
        comSql += " ORDER BY ";
        comSql += "     `tblSaidaCamara`.`intSaidaCamaraId` DESC ";
        comSql += " LIMIT 100;";
        
        
        List tabela = super.listaTodos();
        
        for(int i = 0; i < tabela.size(); i++)
        {
            SaidaCamara saidaCamara = new SaidaCamara();
            List bkp = (ArrayList)tabela.get(i);
            
            saidaCamara.setIdSaidaCamara(((Integer)bkp.get(0)).intValue());
            saidaCamara.setIdEquipamento(((Integer)bkp.get(1)).intValue());
            saidaCamara.setIdProdutoCamara(((Integer)bkp.get(2)).intValue());
            saidaCamara.setSaida((Double)bkp.get(3));
            saidaCamara.setDevolucao((Double)bkp.get(4));
            saidaCamara.setData((Date)bkp.get(5));
            saidaCamara.setSituacao((String)bkp.get(6));
            
            EquipamentoDAO equipamentoDAO = new EquipamentoDAO(conexao);
            Equipamento equipamento = new Equipamento();
            equipamento.setIdEquipamento(saidaCamara.getIdEquipamento());
            equipamento = equipamentoDAO.listaUm(equipamento);
            saidaCamara.setEquipamento(equipamento);
            
            ProdutoCamaraDAO produtoCamaraDAO = new ProdutoCamaraDAO(conexao);
            ProdutoCamara produtoCamara = new ProdutoCamara();
            produtoCamara.setIdProdutoCamara(saidaCamara.getIdProdutoCamara());
            produtoCamara = produtoCamaraDAO.listaUm(produtoCamara);
            saidaCamara.setProdutoCamara(produtoCamara);
            
            lstTabela.add(saidaCamara);
        }
        
        return lstTabela;
    }
    
    public SaidaCamara listaUm(SaidaCamara saidaCamara)
    {
        
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblSaidaCamara`.`intSaidaCamaraId`,";
        comSql += "     `tblSaidaCamara`.`intEquipamentoId`,";
        comSql += "     `tblSaidaCamara`.`intProdutoCamaraId`,";
        comSql += "     `tblSaidaCamara`.`dblSaida`,";
        comSql += "     `tblSaidaCamara`.`dblDevolucao`,";
        comSql += "     `tblSaidaCamara`.`datData`,";
        comSql += "     `tblSaidaCamara`.`chrSituacao`";
        comSql += " FROM `smmdaa_bdGelo`.`tblSaidaCamara`";
        comSql += " WHERE ";
        comSql += "     `tblSaidaCamara`.`intSaidaCamaraId` = " + saidaCamara.getIdSaidaCamara()+ ";";
        List tabela = super.listaUm();
        
        if(!tabela.isEmpty())
        {
            for(int i = 0; i < tabela.size(); i++)
            {
                
                List bkp = (ArrayList)tabela.get(i);

                saidaCamara.setIdSaidaCamara(((Integer)bkp.get(0)).intValue());
                saidaCamara.setIdEquipamento(((Integer)bkp.get(1)).intValue());
                saidaCamara.setIdProdutoCamara(((Integer)bkp.get(2)).intValue());
                saidaCamara.setSaida((Double)bkp.get(3));
                saidaCamara.setDevolucao((Double)bkp.get(4));
                saidaCamara.setData((Date)bkp.get(5));
                saidaCamara.setSituacao((String)bkp.get(6));

                EquipamentoDAO equipamentoDAO = new EquipamentoDAO(conexao);
                Equipamento equipamento = new Equipamento();
                equipamento.setIdEquipamento(saidaCamara.getIdEquipamento());
                equipamento = equipamentoDAO.listaUm(equipamento);
                saidaCamara.setEquipamento(equipamento);

                ProdutoCamaraDAO produtoCamaraDAO = new ProdutoCamaraDAO(conexao);
                ProdutoCamara produtoCamara = new ProdutoCamara();
                produtoCamara.setIdProdutoCamara(saidaCamara.getIdProdutoCamara());
                produtoCamara = produtoCamaraDAO.listaUm(produtoCamara);
                saidaCamara.setProdutoCamara(produtoCamara);
                
            }  
            return saidaCamara;
        }
        else
            return null;

    }
    
    public boolean atualizar(SaidaCamara saidaCamara) 
    {
        boolean retorno;
        
        SaidaCamara _saidaCamara = new SaidaCamara();
        saidaCamara.replicar(_saidaCamara);
       
        if (listaUm(saidaCamara) == null)
        {
            comSql = "";
            comSql += " INSERT INTO `smmdaa_bdGelo`.`tblSaidaCamara`";
            comSql += " 	(`intEquipamentoId`,";
            comSql += " 	`intProdutoCamaraId`,";
            comSql += " 	`dblSaida`,";
            comSql += "         `dblDevolucao`,";
            comSql += "         `datData`,";
            comSql += "         `chrSituacao`)";
            comSql += " VALUES";
            comSql += " 	(" + _saidaCamara.getIdEquipamento();
            comSql += " 	," + _saidaCamara.getIdProdutoCamara();
            comSql += " 	," + _saidaCamara.getSaida();
            comSql += " 	," + _saidaCamara.getDevolucao() ;
            comSql += " 	,'" + _saidaCamara.getDataFormatada() + "'";
            comSql += " 	,'" + _saidaCamara.getSituacao() + "');";


            retorno = atualizar();
            if(retorno)
            {
                comSql = "";
                comSql += " Select max(intSaidaCamaraId) as idSaidaCamara from `tblSaidaCamara`;";
                List lstConsulta = listaUm();
                for(int i = 0; i < lstConsulta.size(); i++)
                {
                    List reg = ((ArrayList)lstConsulta.get(0));
                    identity = ((Integer)reg.get(0)).intValue();
                    _saidaCamara.setIdSaidaCamara(((Integer)reg.get(0)).intValue() );
                }
                
            }            
        }
        else
        {
            comSql = "";
            comSql += " UPDATE `smmdaa_bdGelo`.`tblSaidaCamara` SET";
            comSql += " 	`intEquipamentoId` = " + _saidaCamara.getIdEquipamento();
            comSql += " 	,`intProdutoCamaraId` = " + _saidaCamara.getIdProdutoCamara();
            comSql += " 	,`dblSaida` = " + _saidaCamara.getSaida();
            comSql += " 	,`datData` = '" + _saidaCamara.getDataFormatada() + "'";
            comSql += " 	,`chrSituacao` = '" + _saidaCamara.getSituacao() + "'";
            comSql += " 	,`dblDevolucao` = " + _saidaCamara.getDevolucao();
            comSql += " WHERE ";
            comSql += " 	`intSaidaCamaraId` = " + _saidaCamara.getIdSaidaCamara() + ";";
            retorno = atualizar();
        }
        
        return retorno;
        
    }      
    
}
