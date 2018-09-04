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
import Bean.Profissional;
import Bean.Veiculo;
import java.sql.Connection;
import java.util.*;

public class EntregaDAO extends DAO{

    public EntregaDAO(Connection conexao) {
        super(conexao);
    }
    
    public List<Entrega> listaTodos()
    {
        List<Entrega> lstTabela = new ArrayList();
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblEntrega`.`intEntregaId`,";
        comSql += "     `tblEntrega`.`intMovimentoId`,";
        comSql += "     `tblEntrega`.`intVeiculoId`,";
        comSql += "     `tblEntrega`.`intProfissionalId`,";
        comSql += "     `tblEntrega`.`datData`,";
        comSql += "     `tblEntrega`.`dblKmInicial`,";
        comSql += "     `tblEntrega`.`dblKmFinal`,";
        comSql += "     `tblEntrega`.`strHoraSaida`,";
        comSql += "     `tblEntrega`.`strHoraChegada`,";
        comSql += "     `tblEntrega`.`dblLitros`,";
        comSql += "     `tblEntrega`.`chrSituacao`";
        comSql += " FROM `bdGelo`.`tblEntrega`;";
        
        List tabela = super.listaTodos();
        
        for(int i = 0; i < tabela.size(); i++)
        {
            Entrega entrega = new Entrega();
            List bkp = (ArrayList)tabela.get(i);
            
            entrega.setIdEntrega(((Integer)bkp.get(0)).intValue());
            entrega.setIdMovimento(((Integer)bkp.get(1)).intValue());
            entrega.setIdVeiculo(((Integer)bkp.get(2)).intValue());
            entrega.setIdProfissional(((Integer)bkp.get(3)).intValue());
            entrega.setData((Date)bkp.get(4));
            entrega.setKmInicial((Double)bkp.get(5));
            entrega.setKmFinal((Double)bkp.get(6));
            entrega.setHrSaida((String)bkp.get(7));
            entrega.setHrChegada((String)bkp.get(8));
            entrega.setLitros((Double)bkp.get(9));
            entrega.setSituacao((String)bkp.get(10));

            lstTabela.add(entrega);
        }
        
        return lstTabela;
    }
    
    public Entrega listaUm(Entrega entrega)
    {
        
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblEntrega`.`intEntregaId`,";
        comSql += "     `tblEntrega`.`intMovimentoId`,";
        comSql += "     `tblEntrega`.`intVeiculoId`,";
        comSql += "     `tblEntrega`.`intProfissionalId`,";
        comSql += "     `tblEntrega`.`datData`,";
        comSql += "     `tblEntrega`.`dblKmInicial`,";
        comSql += "     `tblEntrega`.`dblKmFinal`,";
        comSql += "     `tblEntrega`.`strHoraSaida`,";
        comSql += "     `tblEntrega`.`strHoraChegada`,";
        comSql += "     `tblEntrega`.`dblLitros`,";
        comSql += "     `tblEntrega`.`chrSituacao`";
        comSql += " FROM `bdGelo`.`tblEntrega`";
        comSql += " WHERE ";
        if (entrega.getIdEntrega() != 0)
            comSql += "     `tblEntrega`.`intEntregaId` = " + entrega.getIdEntrega()+ ";";
        else if(entrega.getIdMovimento() != 0)
            comSql += "     `tblEntrega`.`intMovimentoId` = " + entrega.getIdMovimento() + ";";        
        List tabela = super.listaUm();
        
        if(!tabela.isEmpty())
        {
            for(int i = 0; i < tabela.size(); i++)
            {
                
                List bkp = (ArrayList)tabela.get(i);

                entrega.setIdEntrega(((Integer)bkp.get(0)).intValue());
                entrega.setIdMovimento(((Integer)bkp.get(1)).intValue());
                entrega.setIdVeiculo(((Integer)bkp.get(2)).intValue());
                entrega.setIdProfissional(((Integer)bkp.get(3)).intValue());
                entrega.setData((Date)bkp.get(4));
                entrega.setKmInicial((Double)bkp.get(5));
                entrega.setKmFinal((Double)bkp.get(6));
                entrega.setHrSaida((String)bkp.get(7));
                entrega.setHrChegada((String)bkp.get(8));
                entrega.setLitros((Double)bkp.get(9));
                entrega.setSituacao((String)bkp.get(10));
                
                ProfissionalDAO profissionalDAO = new ProfissionalDAO(conexao);
                Profissional profissional = new Profissional();
                profissional.setIdProfissional(entrega.getIdProfissional());
                profissional = profissionalDAO.listaUm(profissional);
                entrega.setProfissional(profissional);
                
                VeiculoDAO veiculoDAO = new VeiculoDAO(conexao);
                Veiculo veiculo = new Veiculo();
                veiculo.setIdVeiculo(entrega.getIdVeiculo());
                veiculo = veiculoDAO.listaUm(veiculo);
                entrega.setVeiculo(veiculo);
                
                List<CustoEntrega> lstCustoEntrega = new ArrayList<CustoEntrega>();
                CustoEntregaDAO custoEntregaDAO = new CustoEntregaDAO(conexao);
                lstCustoEntrega = custoEntregaDAO.listaTodos(entrega);
                entrega.setLstCustoEntrega(lstCustoEntrega);

                
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
            comSql += " INSERT INTO `bdGelo`.`tblEntrega`";
            comSql += " 	(`intMovimentoId`,";
            comSql += " 	`intVeiculoId`,";
            comSql += " 	`intProfissionalId`,";
            comSql += " 	`datData`,";
            comSql += " 	`dblKmInicial`,";
            comSql += " 	`dblKmFinal`,";
            comSql += " 	`strHoraSaida`,";
            comSql += " 	`strHoraChegada`,";
            comSql += " 	`dblLitros`,";
            comSql += " 	`chrSituacao`)";
            comSql += " VALUES";
            comSql += " 	(" + _entrega.getIdMovimento();
            comSql += " 	," + _entrega.getIdVeiculo();
            comSql += " 	," + _entrega.getIdProfissional();
            comSql += " 	,'" + _entrega.getDataFormatada() + "'";
            comSql += " 	," + _entrega.getKmInicial();
            comSql += " 	," + _entrega.getKmFinal();
            comSql += " 	,'" + _entrega.getHrSaida() + "'";
            comSql += " 	,'" + _entrega.getHrChegada() + "'";
            comSql += " 	," + _entrega.getLitros();
            comSql += " 	,'" + _entrega.getSituacao() + "');";

            
            retorno = atualizar();
            if(retorno)
            {
                comSql = "";
                comSql += " Select max(intEntregaId) as idEntrega from `tblEntrega`;";
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
            comSql += " UPDATE `bdGelo`.`tblEntrega` SET ";
            comSql += " 	`intMovimentoId` = " + _entrega.getIdMovimento();
            comSql += " 	,`intVeiculoId` = " + _entrega.getIdVeiculo();
            comSql += " 	,`intProfissionalId` = "  + _entrega.getIdProfissional();;
            comSql += " 	,`datData` = '" + _entrega.getDataFormatada() + "'";
            comSql += " 	,`dblKmInicial` = " + _entrega.getKmInicial();
            comSql += " 	,`dblKmFinal` = " + _entrega.getKmFinal();
            comSql += " 	,`strHoraSaida` = '" + _entrega.getHrSaida() + "'";
            comSql += " 	,`strHoraChegada` = '" + _entrega.getHrChegada() + "'";
            comSql += " 	,`dblLitros` = " + _entrega.getLitros();
            comSql += " 	,`chrSituacao` = '" + _entrega.getSituacao() + "'";
            comSql += " WHERE ";
            comSql += " 	`intEntregaId` = " + _entrega.getIdEntrega() + ";";
            retorno = atualizar();
        }
        
        if (retorno)
        {
            if (_entrega.getLstCustoEntrega() != null)
            {
                CustoEntregaDAO custoEntregaDAO = new CustoEntregaDAO(conexao);
                Iterator itCustoEntrega = _entrega.getLstCustoEntrega().iterator();
                while (itCustoEntrega.hasNext())
                {
                    CustoEntrega _custoEntrega = (CustoEntrega)itCustoEntrega.next();
                    _custoEntrega.setIdEntrega(_entrega.getIdEntrega());
                    
                    custoEntregaDAO.atualizar(_custoEntrega);
                }
            }
        }
        
        return retorno;
        
    }       
}
