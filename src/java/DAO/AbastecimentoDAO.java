/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Bean.Abastecimento;
import Bean.Entrega;
import Bean.Colaborador;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author celso
 */
public class AbastecimentoDAO extends DAO{

    public AbastecimentoDAO(Connection conexao) {
        super(conexao);
    }
    
    public List<Abastecimento> listaTodos(Entrega entrega)
    {
        List<Abastecimento> lstTabela = new ArrayList();
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += "     `tblAbastecimento`.`intAbastecimentoId`,";
        comSql += "     `tblAbastecimento`.`intEntregaId`,";
        comSql += "     `tblAbastecimento`.`intColaboradorId`,";
        comSql += "     `tblAbastecimento`.`datData`,";
        comSql += "     `tblAbastecimento`.`dblValorUnitario`,";
        comSql += "     `tblAbastecimento`.`dblQuantidade`";
        comSql += " FROM ";
        comSql += "     `smmdaa_bdGelo`.`tblAbastecimento`";
        comSql += " WHERE ";
        comSql += "     `tblAbastecimento`.`intEntregaId` = " + entrega.getIdEntrega() + ";";
        
        List tabela = super.listaTodos();
        
        for(int i = 0; i < tabela.size(); i++)
        {
            Abastecimento abastecimento = new Abastecimento();
            List bkp = (ArrayList)tabela.get(i);
            
            abastecimento.setIdAbastecimento(((Integer)bkp.get(0)).intValue());
            abastecimento.setIdEntrega(((Integer)bkp.get(1)).intValue());
            abastecimento.setIdColaborador(((Integer)bkp.get(2)).intValue());
            abastecimento.setData((Date)bkp.get(3));
            abastecimento.setVlUnitario((Double)bkp.get(4));
            abastecimento.setQuantidade((Double)bkp.get(5));
            
            ColaboradorDAO colaboradorDAO = new ColaboradorDAO(conexao);
            Colaborador colaborador = new Colaborador();
            colaborador.setIdColaborador(abastecimento.getIdColaborador());
            colaborador = colaboradorDAO.listaUm(colaborador);
            abastecimento.setColaborador(colaborador);
            
            lstTabela.add(abastecimento);
        }
        
        return lstTabela;
    }
    
    public Abastecimento listaUm(Abastecimento abastecimento)
    {
        
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += "     `tblAbastecimento`.`intAbastecimentoId`,";
        comSql += "     `tblAbastecimento`.`intEntregaId`,";
        comSql += "     `tblAbastecimento`.`intColaboradorId`,";
        comSql += "     `tblAbastecimento`.`datData`,";
        comSql += "     `tblAbastecimento`.`dblValorUnitario`,";
        comSql += "     `tblAbastecimento`.`dblQuantidade`";
        comSql += " FROM ";
        comSql += "     `smmdaa_bdGelo`.`tblAbastecimento`";
        comSql += " WHERE ";
        comSql += "     `tblAbastecimento`.`intAbastecimentoId` = " + abastecimento.getIdAbastecimento() + ";";
        List tabela = super.listaUm();
        
        if(!tabela.isEmpty())
        {
            for(int i = 0; i < tabela.size(); i++)
            {
                
                List bkp = (ArrayList)tabela.get(i);

                abastecimento.setIdAbastecimento(((Integer)bkp.get(0)).intValue());
                abastecimento.setIdEntrega(((Integer)bkp.get(1)).intValue());
                abastecimento.setIdColaborador(((Integer)bkp.get(2)).intValue());
                abastecimento.setData((Date)bkp.get(3));
                abastecimento.setVlUnitario((Double)bkp.get(4));
                abastecimento.setQuantidade((Double)bkp.get(5));
                
                ColaboradorDAO colaboradorDAO = new ColaboradorDAO(conexao);
                Colaborador colaborador = new Colaborador();
                colaborador.setIdColaborador(abastecimento.getIdColaborador());
                colaborador = colaboradorDAO.listaUm(colaborador);
                abastecimento.setColaborador(colaborador);                
                
            }  
            return abastecimento;
        }
        else
            return null;

    }
    
    public boolean atualizar(Abastecimento abastecimento) 
    {
        boolean retorno;
        
        Abastecimento _abastecimento = new Abastecimento();
        abastecimento.replicar(_abastecimento);
        
        if (listaUm(abastecimento) == null)
        {
            comSql = "";
            comSql += " INSERT INTO `smmdaa_bdGelo`.`tblAbastecimento` ";
            comSql += "     (";
            comSql += "     `tblAbastecimento`.`intEntregaId`,";
            comSql += "     `tblAbastecimento`.`intColaboradorId`,";
            comSql += "     `tblAbastecimento`.`datData`,";
            comSql += "     `tblAbastecimento`.`dblValorUnitario`,";
            comSql += "     `tblAbastecimento`.`dblQuantidade`)";
            comSql += " VALUES";
            comSql += " (" + _abastecimento.getIdEntrega();
            comSql += " ," + _abastecimento.getIdColaborador();
            comSql += " ,'" + _abastecimento.getDataFormatada() + "'";
            comSql += " ," + _abastecimento.getVlUnitario();
            comSql += " ," + _abastecimento.getQuantidade()  + ")";
            
            retorno = atualizar();
            if(retorno)
            {
                comSql = "";
                comSql += " Select max(intAbastecimentoId) as idAbastecimento from `tblAbastecimento`;";
                List lstConsulta = listaUm();
                for(int i = 0; i < lstConsulta.size(); i++)
                {
                    List reg = ((ArrayList)lstConsulta.get(0));
                    identity = ((Integer)reg.get(0)).intValue();
                    _abastecimento.setIdAbastecimento( ((Integer)reg.get(0)).intValue() );
                }
                
            }            
        }
        else
        {
            comSql = "";
            comSql += " UPDATE `smmdaa_bdGelo`.`tblAbastecimento` SET";
            comSql += "     `tblAbastecimento`.`intEntregaId` = " + _abastecimento.getIdEntrega();
            comSql += "     ,`tblAbastecimento`.`intColaboradorId` = " + _abastecimento.getIdColaborador();
            comSql += "     ,`tblAbastecimento`.`datData` = '" + _abastecimento.getDataFormatada() + "'";
            comSql += "     ,`tblAbastecimento`.`dblValorUnitario` = " + _abastecimento.getVlUnitario();
            comSql += "     ,`tblAbastecimento`.`dblQuantidade` = " + _abastecimento.getQuantidade();
            comSql += " WHERE ";
            comSql += "	`intAbastecimentoId` = " + _abastecimento.getIdAbastecimento()+ ";  ";   
            retorno = atualizar();
        }
        
        return retorno;
        
    }
    
    public boolean delete(Abastecimento abastecimento)
    {
        boolean retorno = false;
        
        if (abastecimento != null)
        {
            comSql = "";
            comSql += " Delete from `smmdaa_bdGelo`.`tblAbastecimento` ";
            comSql += " WHERE ";
            comSql += "	`intAbastecimentoId` = " + abastecimento.getIdAbastecimento() + ";  ";
            retorno = atualizar();
        }
        
        return retorno;
    }
        
}
