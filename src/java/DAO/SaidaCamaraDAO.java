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

import Bean.SaidaCamara;
import java.sql.Connection;
import java.util.*;

public class SaidaCamaraDAO extends DAO{

    public SaidaCamaraDAO(Connection conexao) {
        super(conexao);
    }
    
    public List<SaidaCamara> listaTodos()
    {
        List<SaidaCamara> lstTabela = new ArrayList();
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblSaidaCamara`.`intSaidaCamaraId`,";
        comSql += "     `tblSaidaCamara`.`intProdutoMovimentoId`,";
        comSql += "     `tblSaidaCamara`.`intProdutoCamaraId`,";
        comSql += "     `tblSaidaCamara`.`dblSaida`,";
        comSql += "     `tblSaidaCamara`.`dblDevolucao`";
        comSql += " FROM `bdGelo`.`tblSaidaCamara`;";
        
        List tabela = super.listaTodos();
        
        for(int i = 0; i < tabela.size(); i++)
        {
            SaidaCamara saidaCamara = new SaidaCamara();
            List bkp = (ArrayList)tabela.get(i);
            
            saidaCamara.setIdSaidaCamara(((Integer)bkp.get(0)).intValue());
            saidaCamara.setidProdutoMovimento(((Integer)bkp.get(1)).intValue());
            saidaCamara.setidProdutoCamara(((Integer)bkp.get(2)).intValue());
            saidaCamara.setSaida((Double)bkp.get(3));
            saidaCamara.setDevolucao((Double)bkp.get(4));
            
            lstTabela.add(saidaCamara);
        }
        
        return lstTabela;
    }
    
    public SaidaCamara listaUm(SaidaCamara saidaCamara)
    {
        
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblSaidaCamara`.`intSaidaCamaraId`,";
        comSql += "     `tblSaidaCamara`.`intProdutoMovimentoId`,";
        comSql += "     `tblSaidaCamara`.`intProdutoCamaraId`,";
        comSql += "     `tblSaidaCamara`.`dblSaida`,";
        comSql += "     `tblSaidaCamara`.`dblDevolucao`";
        comSql += " FROM `bdGelo`.`tblSaidaCamara`";
        comSql += " WHERE ";
        comSql += "     `tblSaidaCamara`.`intSaidaCamaraId` = " + saidaCamara.getIdSaidaCamara()+ ";";
        List tabela = super.listaUm();
        
        if(!tabela.isEmpty())
        {
            for(int i = 0; i < tabela.size(); i++)
            {
                
                List bkp = (ArrayList)tabela.get(i);

                saidaCamara.setIdSaidaCamara(((Integer)bkp.get(0)).intValue());
                saidaCamara.setidProdutoMovimento(((Integer)bkp.get(1)).intValue());
                saidaCamara.setidProdutoCamara(((Integer)bkp.get(2)).intValue());
                saidaCamara.setSaida((Double)bkp.get(3));
                saidaCamara.setDevolucao((Double)bkp.get(4));

            
                
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
            comSql += " INSERT INTO `bdGelo`.`tblSaidaCamara`";
            comSql += " 	(`intProdutoMovimentoId`,";
            comSql += " 	`intProdutoCamaraId`,";
            comSql += " 	`dblSaida`,";
            comSql += " 	`dblDevolucao`)";
            comSql += " VALUES";
            comSql += " 	(" + _saidaCamara.getidProdutoMovimento();
            comSql += " 	," + _saidaCamara.getidProdutoCamara();
            comSql += " 	," + _saidaCamara.getSaida();
            comSql += " 	," + _saidaCamara.getDevolucao() + ");";


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
            comSql += " UPDATE `bdGelo`.`tblSaidaCamara` SET";
            comSql += " 	`intProdutoMovimentoId` = " + _saidaCamara.getidProdutoMovimento();
            comSql += " 	,`intProdutoCamaraId` = " + _saidaCamara.getidProdutoCamara();
            comSql += " 	,`dblSaida` = " + _saidaCamara.getSaida();
            comSql += " 	,`dblDevolucao` = " + _saidaCamara.getDevolucao();
            comSql += " WHERE ";
            comSql += " 	`intSaidaCamaraId` = " + _saidaCamara.getIdSaidaCamara() + ";";
            retorno = atualizar();
        }
        
        return retorno;
        
    }      
    
}
