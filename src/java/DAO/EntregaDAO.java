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
import Bean.AvariaEntrega;
import Bean.ColaboradorEntrega;
import Bean.Entrega;
import Bean.Profissional;
import Bean.Equipamento;
import Bean.ProdutoEntrega;
import Bean.ProfissionalEntrega;
import java.sql.Connection;
import java.util.*;

public class EntregaDAO extends DAO{

    public EntregaDAO(Connection conexao) {
        super(conexao);
    }
    
    public List<Entrega> listaTodos(Entrega _entrega)
    {
        List<Entrega> lstTabela = new ArrayList();
        boolean temOr = false;
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblEntrega`.`intEntregaId`,";
        comSql += "     `tblEntrega`.`intEquipamentoId`,";
        comSql += "     `tblEntrega`.`datData`,";
        comSql += "     `tblEntrega`.`dblKmInicial`,";
        comSql += "     `tblEntrega`.`dblKmFinal`,";
        comSql += "     `tblEntrega`.`strHoraSaida`,";
        comSql += "     `tblEntrega`.`strHoraChegada`,";
        comSql += "     `tblEntrega`.`chrSituacao`";
        comSql += " FROM `smmdaa_bdGelo`.`tblEntrega`";
        if (_entrega != null)
        {
            comSql += " WHERE ";
            if (!_entrega.getSituacao().equals(""))
            {
                comSql += "     `tblEntrega`.`chrSituacao` = '" + _entrega.getSituacao() + "'";
                temOr = true;
            }
            if (!_entrega.getDataFormatada().equals("1900-01-01"))
            {
                if (temOr)
                    comSql += " OR ";
                comSql += "     `tblEntrega`.`datData` = '" + _entrega.getDataFormatada() + "'";
                temOr = true;
            }
            if (_entrega.getIdEquipamento() != 0)
            {
                if (temOr)
                    comSql += " OR ";
                comSql += "     `tblEntrega`.`intEquipamentoId` = " + _entrega.getIdEquipamento();
            }
            
        }
        comSql += ";";
        //comSql += "ORDER BY `tblEntrega`.`datData`;";
        
        List tabela = super.listaTodos();
        
        for(int i = 0; i < tabela.size(); i++)
        {
            Entrega entrega = new Entrega();
            List bkp = (ArrayList)tabela.get(i);
            
            entrega.setIdEntrega(((Integer)bkp.get(0)).intValue());
            entrega.setIdEquipamento(((Integer)bkp.get(1)).intValue());
            entrega.setData((Date)bkp.get(2));
            entrega.setKmInicial((Double)bkp.get(3));
            entrega.setKmFinal((Double)bkp.get(4));
            entrega.setHrSaida((String)bkp.get(5));
            entrega.setHrChegada((String)bkp.get(6));
            entrega.setSituacao((String)bkp.get(7));
            
            
            EquipamentoDAO veiculoDAO = new EquipamentoDAO(conexao);
            Equipamento veiculo = new Equipamento();
            veiculo.setIdEquipamento(entrega.getIdEquipamento());
            veiculo = veiculoDAO.listaUm(veiculo);
            entrega.setVeiculo(veiculo);  
            
            List<ProfissionalEntrega> lstProfissionalEntrega = new ArrayList<ProfissionalEntrega>();
            ProfissionalEntregaDAO profissionalEntregaDAO = new ProfissionalEntregaDAO(conexao);
            lstProfissionalEntrega = profissionalEntregaDAO.listaTodos(entrega);
            entrega.setLstProfissionalEntrega(lstProfissionalEntrega); 
            
            List<ColaboradorEntrega> lstColaboradorEntrega = new ArrayList<ColaboradorEntrega>();
            ColaboradorEntregaDAO colaboradorEntregaDAO = new ColaboradorEntregaDAO(conexao);
            lstColaboradorEntrega = colaboradorEntregaDAO.listaTodos(entrega);
            entrega.setLstColaboradorEntrega(lstColaboradorEntrega);
            
            lstTabela.add(entrega);
        }
        
        return lstTabela;
    }
    
    public Entrega listaUm(Entrega entrega)
    {
        
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblEntrega`.`intEntregaId`,";
        comSql += "     `tblEntrega`.`intEquipamentoId`,";
        comSql += "     `tblEntrega`.`datData`,";
        comSql += "     `tblEntrega`.`dblKmInicial`,";
        comSql += "     `tblEntrega`.`dblKmFinal`,";
        comSql += "     `tblEntrega`.`strHoraSaida`,";
        comSql += "     `tblEntrega`.`strHoraChegada`,";
        comSql += "     `tblEntrega`.`chrSituacao`";
        comSql += " FROM `smmdaa_bdGelo`.`tblEntrega`";
        comSql += " WHERE ";
        comSql += "     `tblEntrega`.`intEntregaId` = " + entrega.getIdEntrega() + ";";
     
        List tabela = super.listaUm();
        
        if(!tabela.isEmpty())
        {
            for(int i = 0; i < tabela.size(); i++)
            {
                
                List bkp = (ArrayList)tabela.get(i);

                entrega.setIdEntrega(((Integer)bkp.get(0)).intValue());
                entrega.setIdEquipamento(((Integer)bkp.get(1)).intValue());
                entrega.setData((Date)bkp.get(2));
                entrega.setKmInicial((Double)bkp.get(3));
                entrega.setKmFinal((Double)bkp.get(4));
                entrega.setHrSaida((String)bkp.get(5));
                entrega.setHrChegada((String)bkp.get(6));
                entrega.setSituacao((String)bkp.get(7));
                
                EquipamentoDAO veiculoDAO = new EquipamentoDAO(conexao);
                Equipamento veiculo = new Equipamento();
                veiculo.setIdEquipamento(entrega.getIdEquipamento());
                veiculo = veiculoDAO.listaUm(veiculo);
                entrega.setVeiculo(veiculo);
                
              
                List<ProdutoEntrega> lstProdutoEntrega = new ArrayList<ProdutoEntrega>();
                ProdutoEntregaDAO produtoEntregaDAO = new ProdutoEntregaDAO(conexao);
                lstProdutoEntrega = produtoEntregaDAO.listaTodos(entrega);
                entrega.setLstProdutoEntrega(lstProdutoEntrega);
                
                List<CustoEntrega> lstCustoEntrega = new ArrayList<CustoEntrega>();
                CustoEntregaDAO custoEntregaDAO = new CustoEntregaDAO(conexao);
                lstCustoEntrega = custoEntregaDAO.listaTodos(entrega);
                entrega.setLstCustoEntrega(lstCustoEntrega);
                
                List<ColaboradorEntrega> lstColaboradorEntrega = new ArrayList<ColaboradorEntrega>();
                ColaboradorEntregaDAO colaboradorEntregaDAO = new ColaboradorEntregaDAO(conexao);
                lstColaboradorEntrega = colaboradorEntregaDAO.listaTodos(entrega);
                entrega.setLstColaboradorEntrega(lstColaboradorEntrega);
                
                List<ProfissionalEntrega> lstProfissionalEntrega = new ArrayList<ProfissionalEntrega>();
                ProfissionalEntregaDAO profissionalEntregaDAO = new ProfissionalEntregaDAO(conexao);
                lstProfissionalEntrega = profissionalEntregaDAO.listaTodos(entrega);
                entrega.setLstProfissionalEntrega(lstProfissionalEntrega);

                
            }  
            return entrega;
        }
        else
            return null;

    }
    
    public boolean atualizar(Entrega entrega) 
    {
        boolean retorno;
        
        Entrega _entrega = new Entrega();
        entrega.replicar(_entrega);
       
        if (listaUm(entrega) == null)
        {
            comSql = "";
            comSql += " INSERT INTO `smmdaa_bdGelo`.`tblEntrega`";
            comSql += " 	(";
            comSql += " 	`intEquipamentoId`,";
            comSql += " 	`datData`,";
            comSql += " 	`dblKmInicial`,";
            comSql += " 	`dblKmFinal`,";
            comSql += " 	`strHoraSaida`,";
            comSql += " 	`strHoraChegada`,";
            comSql += " 	`chrSituacao`)";
            comSql += " VALUES";
            comSql += " 	(";
            comSql += " 	" + _entrega.getIdEquipamento();
            comSql += " 	,'" + _entrega.getDataFormatada() + "'";
            comSql += " 	," + _entrega.getKmInicial();
            comSql += " 	," + _entrega.getKmFinal();
            comSql += " 	,'" + _entrega.getHrSaida() + "'";
            comSql += " 	,'" + _entrega.getHrChegada() + "'";
            comSql += " 	,'" + _entrega.getSituacao() + "');";

            
            retorno = atualizar();
            if(retorno)
            {
                comSql = "";
                comSql += " Select max(intEntregaId) as idEntrega from `smmdaa_bdGelo`.`tblEntrega`;";
                List lstConsulta = listaUm();
                for(int i = 0; i < lstConsulta.size(); i++)
                {
                    List reg = ((ArrayList)lstConsulta.get(0));
                    identity = ((Integer)reg.get(0)).intValue();
                    _entrega.setIdEntrega(((Integer)reg.get(0)).intValue() );
                }
                
            }            
        }
        else
        {
            comSql = "";
            comSql += " UPDATE `smmdaa_bdGelo`.`tblEntrega` SET ";
            comSql += " 	`intEquipamentoId` = " + _entrega.getIdEquipamento();
            comSql += " 	,`datData` = '" + _entrega.getDataFormatada() + "'";
            comSql += " 	,`dblKmInicial` = " + _entrega.getKmInicial();
            comSql += " 	,`dblKmFinal` = " + _entrega.getKmFinal();
            comSql += " 	,`strHoraSaida` = '" + _entrega.getHrSaida() + "'";
            comSql += " 	,`strHoraChegada` = '" + _entrega.getHrChegada() + "'";
            comSql += " 	,`chrSituacao` = '" + _entrega.getSituacao() + "'";
            comSql += " WHERE ";
            comSql += " 	`intEntregaId` = " + _entrega.getIdEntrega() + ";";
            retorno = atualizar();
        }
        
        if (retorno)
        {

            if (_entrega.getLstProdutoEntrega() != null)
            {
                ProdutoEntregaDAO produtoEntregaDAO = new ProdutoEntregaDAO(conexao);
                Iterator itProdutoEntrega = _entrega.getLstProdutoEntrega().iterator();
                while (itProdutoEntrega.hasNext())
                {
                    ProdutoEntrega _produtoEntrega = (ProdutoEntrega)itProdutoEntrega.next();
                    _produtoEntrega.setIdEntrega(_entrega.getIdEntrega());
                    
                    produtoEntregaDAO.atualizar(_produtoEntrega);
                }
            }
            
            if (_entrega.getLstCustoEntrega() != null)
            {
                CustoEntregaDAO custoEntregaDAO = new CustoEntregaDAO(conexao);
                Iterator itCustoEntrega = _entrega.getLstCustoEntrega().iterator();
                while (itCustoEntrega.hasNext())
                {
                    CustoEntrega _custoEntrega = (CustoEntrega)itCustoEntrega.next();
                    _custoEntrega.setIdEntrega((_entrega.getIdEntrega()));
                    custoEntregaDAO.atualizar(_custoEntrega);
                }
            }
            
            if (_entrega.getLstProfissionalEntrega() != null)
            {
                ProfissionalEntregaDAO profissionalEntregaDAO = new ProfissionalEntregaDAO(conexao);
                Iterator itProfissionalEntrega = _entrega.getLstProfissionalEntrega().iterator();
                while (itProfissionalEntrega.hasNext())
                {
                    ProfissionalEntrega profissionalEntrega = (ProfissionalEntrega)itProfissionalEntrega.next();
                    profissionalEntrega.setIdEntrega(_entrega.getIdEntrega());
                    profissionalEntregaDAO.atualizar(profissionalEntrega);
                }
                        
            }
            
            if (_entrega.getLstColaboradorEntrega() != null)
            {
                ColaboradorEntregaDAO colaboradorEntregaDAO = new ColaboradorEntregaDAO(conexao);
                Iterator itColaboradorEntrega = _entrega.getLstColaboradorEntrega().iterator();
                while (itColaboradorEntrega.hasNext())
                {
                    ColaboradorEntrega colaboradorEntrega = (ColaboradorEntrega)itColaboradorEntrega.next();
                    colaboradorEntrega.setIdEntrega(_entrega.getIdEntrega());
                    colaboradorEntregaDAO.atualizar(colaboradorEntrega);
                }
            }
        }
        
        return retorno;
        
    }       
}
