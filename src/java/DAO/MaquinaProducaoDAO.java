/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Bean.AvariaProducao;
import Bean.Equipamento;
import Bean.MaquinaProducao;
import Bean.Producao;
import Bean.Produto;
import Bean.TransferenciaProducao;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author celso
 */
public class MaquinaProducaoDAO extends DAO{

    public MaquinaProducaoDAO(Connection conexao) {
        super(conexao);
    }
    
    public List<MaquinaProducao> listaTodos(Producao producao)
    {
        List<MaquinaProducao> lstTabela = new ArrayList();
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblMaquinaProducao`.`intMaquinaProducaoId`,";
        comSql += "     `tblMaquinaProducao`.`intProducaoId`,";
        comSql += "     `tblMaquinaProducao`.`intEquipamentoId`,";
        comSql += "     `tblMaquinaProducao`.`intProdutoId`,";
        comSql += "     `tblMaquinaProducao`.`dblQtReposicao`,";
        comSql += "     `tblMaquinaProducao`.`dblSaldoAnterior`,";
        comSql += "     `tblMaquinaProducao`.`dblQtProducao`,";
        comSql += "     `tblMaquinaProducao`.`dblQtAvaria`,";
        comSql += "     `tblMaquinaProducao`.`dblSaldo`,";
        comSql += "     `tblMaquinaProducao`.`strHoraInicial`,";
        comSql += "     `tblMaquinaProducao`.`strHoraFinal`,";
        comSql += "     `tblMaquinaProducao`.`dblRendimento`";
        comSql += " FROM `smmdaa_bdGelo`.`tblMaquinaProducao`";
        comSql += " WHERE ";
        comSql += "     `tblMaquinaProducao`.`intProducaoId` = " + producao.getIdProducao() + ";";
         
        
        List tabela = super.listaTodos();
        
        for(int i = 0; i < tabela.size(); i++)
        {
            MaquinaProducao maquinaProducao = new MaquinaProducao();
            List bkp = (ArrayList)tabela.get(i);
            
            maquinaProducao.setIdMaquinaProducao(((Integer)bkp.get(0)).intValue());
            maquinaProducao.setIdProducao(((Integer)bkp.get(1)).intValue());
            maquinaProducao.setIdEquipamento(((Integer)bkp.get(2)).intValue());
            maquinaProducao.setIdProduto(((Integer)bkp.get(3)).intValue());
            maquinaProducao.setQtReposicao((Double)bkp.get(4));
            maquinaProducao.setQtSaldoAnterior((Double)bkp.get(5));
            maquinaProducao.setQtProducao((Double)bkp.get(6));
            maquinaProducao.setQtAvaria((Double)bkp.get(7));
            maquinaProducao.setQtSaldo((Double)bkp.get(8));
            maquinaProducao.setHrInicial((String)bkp.get(9));
            maquinaProducao.setHrFinal((String)bkp.get(10));
            maquinaProducao.setRendimento((Double)bkp.get(11));

            // Embalagem
            ProdutoDAO produtoDAO = new ProdutoDAO(conexao);
            Produto produto = new Produto();
            produto.setIdProduto(maquinaProducao.getIdProduto());
            produto = produtoDAO.listaUm(produto);
            maquinaProducao.setEmbalagem(produto);

            // Maquina
            EquipamentoDAO maquinaDAO = new EquipamentoDAO(conexao);
            Equipamento maquina = new Equipamento();
            maquina.setIdEquipamento(maquinaProducao.getIdEquipamento());
            maquina = maquinaDAO.listaUm(maquina);
            maquinaProducao.setMaquina(maquina);
                
            // descobrindo as transferências
            List<TransferenciaProducao> lstTransferenciaProducao = new ArrayList<TransferenciaProducao>();
            TransferenciaProducaoDAO transferenciaProducaoDAO = new TransferenciaProducaoDAO(conexao);
            lstTransferenciaProducao = transferenciaProducaoDAO.listaTodos(maquinaProducao);
            maquinaProducao.setLstTransferenciaProducao(lstTransferenciaProducao);
                
            lstTabela.add(maquinaProducao);
        }
        
        return lstTabela;
    }
    
    public MaquinaProducao listaUm(MaquinaProducao maquinaProducao)
    {
        
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblMaquinaProducao`.`intMaquinaProducaoId`,";
        comSql += "     `tblMaquinaProducao`.`intProducaoId`,";
        comSql += "     `tblMaquinaProducao`.`intEquipamentoId`,";
        comSql += "     `tblMaquinaProducao`.`intProdutoId`,";
        comSql += "     `tblMaquinaProducao`.`dblQtReposicao`,";
        comSql += "     `tblMaquinaProducao`.`dblSaldoAnterior`,";
        comSql += "     `tblMaquinaProducao`.`dblQtProducao`,";
        comSql += "     `tblMaquinaProducao`.`dblQtAvaria`,";
        comSql += "     `tblMaquinaProducao`.`dblSaldo`,";
        comSql += "     `tblMaquinaProducao`.`strHoraInicial`,";
        comSql += "     `tblMaquinaProducao`.`strHoraFinal`,";
        comSql += "     `tblMaquinaProducao`.`dblRendimento`";
        comSql += " FROM `smmdaa_bdGelo`.`tblMaquinaProducao`";
        comSql += " WHERE ";
        if ((maquinaProducao.getIdProducao() != 0) && (maquinaProducao.getIdEquipamento() != 0))
        {
            comSql += "     `tblMaquinaProducao`.`intProducaoId` = " + maquinaProducao.getIdProducao();
            comSql += "     and `tblMaquinaProducao`.`intEquipamentoId` = " + maquinaProducao.getIdEquipamento() + ";";
            
        } 
        else
            comSql += "     `tblMaquinaProducao`.`intMaquinaProducaoId` = " + maquinaProducao.getIdMaquinaProducao() + ";";
        List tabela = super.listaUm();
        
        if(!tabela.isEmpty())
        {
            for(int i = 0; i < tabela.size(); i++)
            {
                
                List bkp = (ArrayList)tabela.get(i);

                maquinaProducao.setIdMaquinaProducao(((Integer)bkp.get(0)).intValue());
                maquinaProducao.setIdProducao(((Integer)bkp.get(1)).intValue());
                maquinaProducao.setIdEquipamento(((Integer)bkp.get(2)).intValue());
                maquinaProducao.setIdProduto(((Integer)bkp.get(3)).intValue());
                maquinaProducao.setQtReposicao((Double)bkp.get(4));
                maquinaProducao.setQtSaldoAnterior((Double)bkp.get(5));
                maquinaProducao.setQtProducao((Double)bkp.get(6));
                maquinaProducao.setQtAvaria((Double)bkp.get(7));
                maquinaProducao.setQtSaldo((Double)bkp.get(8));
                maquinaProducao.setHrInicial((String)bkp.get(9));
                maquinaProducao.setHrFinal((String)bkp.get(10));
                maquinaProducao.setRendimento((Double)bkp.get(11));
                
                // Embalagem
                ProdutoDAO produtoDAO = new ProdutoDAO(conexao);
                Produto produto = new Produto();
                produto.setIdProduto(maquinaProducao.getIdProduto());
                produto = produtoDAO.listaUm(produto);
                maquinaProducao.setEmbalagem(produto);
                
                // Maquina
                EquipamentoDAO maquinaDAO = new EquipamentoDAO(conexao);
                Equipamento maquina = new Equipamento();
                maquina.setIdEquipamento(maquinaProducao.getIdEquipamento());
                maquina = maquinaDAO.listaUm(maquina);
                maquinaProducao.setMaquina(maquina);
                
                List<AvariaProducao> lstAvariaProducao = new ArrayList<AvariaProducao>();
                AvariaProducaoDAO avariaProducaoDAO = new AvariaProducaoDAO(conexao);
                lstAvariaProducao = avariaProducaoDAO.listaTodos(maquinaProducao);
                maquinaProducao.setLstAvariaProducao(lstAvariaProducao);
                
                // descobrindo as transferências
                List<TransferenciaProducao> lstTransferenciaProducao = new ArrayList<TransferenciaProducao>();
                TransferenciaProducaoDAO transferenciaProducaoDAO = new TransferenciaProducaoDAO(conexao);
                lstTransferenciaProducao = transferenciaProducaoDAO.listaTodos(maquinaProducao);
                maquinaProducao.setLstTransferenciaProducao(lstTransferenciaProducao);
                
                
                
            }  
            return maquinaProducao;
        }
        else
            return null;

    }
    
    public boolean atualizar(MaquinaProducao maquinaProducao) 
    {
        boolean retorno;
        
        
        MaquinaProducao _maquinaProducao = new MaquinaProducao();
        maquinaProducao.replicar(_maquinaProducao);
       
        if (listaUm(maquinaProducao) == null)
        {
            comSql = "";
            comSql += " INSERT INTO `smmdaa_bdGelo`.`tblMaquinaProducao`";
            comSql += " 	(`intProducaoId`,";
            comSql += " 	`intEquipamentoId`,";
            comSql += " 	`intProdutoId`,";
            comSql += " 	`dblQtReposicao`,";
            comSql += " 	`dblSaldoAnterior`,";
            comSql += " 	`dblQtProducao`,";
            comSql += " 	`dblQtAvaria`,";
            comSql += " 	`dblSaldo`,";
            comSql += " 	`strHoraInicial`,";
            comSql += " 	`strHoraFinal`,";
            comSql += " 	`dblRendimento`)";
            comSql += " VALUES";
            comSql += " 	(" + _maquinaProducao.getIdProducao();
            comSql += " 	," + _maquinaProducao.getIdEquipamento();
            comSql += " 	," + _maquinaProducao.getIdProduto();
            comSql += " 	," + _maquinaProducao.getQtReposicao();
            comSql += " 	," + _maquinaProducao.getQtSaldoAnterior();
            comSql += " 	," + _maquinaProducao.getQtProducao();
            comSql += " 	," + _maquinaProducao.getQtAvaria();
            comSql += " 	," + _maquinaProducao.getQtSaldo();
            comSql += " 	,'" + _maquinaProducao.getHrInicial() + "'";
            comSql += " 	,'" + _maquinaProducao.getHrFinal() + "'";
            comSql += " 	," + _maquinaProducao.getRendimento() + ");";

            
            retorno = atualizar();
            if(retorno)
            {
                comSql = "";
                comSql += " Select max(intMaquinaProducaoId) as idMaquinaProducao from `tblMaquinaProducao`;";
                List lstConsulta = listaUm();
                for(int i = 0; i < lstConsulta.size(); i++)
                {
                    List reg = ((ArrayList)lstConsulta.get(0));
                    identity = ((Integer)reg.get(0)).intValue();
                    _maquinaProducao.setIdMaquinaProducao(((Integer)reg.get(0)).intValue() );
                }
                
            }            
        }
        else
        {
            comSql = "";
            comSql += " UPDATE `smmdaa_bdGelo`.`tblMaquinaProducao` SET";
            comSql += " 	`intProducaoId` = " + _maquinaProducao.getIdProducao();
            comSql += " 	,`intEquipamentoId` = " + _maquinaProducao.getIdEquipamento();
            comSql += " 	,`intProdutoId` = " + _maquinaProducao.getIdProduto();
            comSql += " 	,`dblQtReposicao` = " + _maquinaProducao.getQtReposicao();
            comSql += " 	,`dblSaldoAnterior` = " + _maquinaProducao.getQtSaldoAnterior();
            comSql += " 	,`dblQtProducao` = " + _maquinaProducao.getQtProducao();
            comSql += " 	,`dblQtAvaria` = " + _maquinaProducao.getQtAvaria();
            comSql += " 	,`dblSaldo` = " + _maquinaProducao.getQtSaldo();
            comSql += " 	,`strHoraInicial` = '" + _maquinaProducao.getHrInicial() + "'";
            comSql += " 	,`strHoraFinal` = '" + _maquinaProducao.getHrFinal() + "'";
            comSql += " 	,`dblRendimento` = " + _maquinaProducao.getRendimento();
            comSql += " WHERE ";
            comSql += " 	`intMaquinaProducaoId` = " + _maquinaProducao.getIdMaquinaProducao() + ";";
            retorno = atualizar();
        }
        if (retorno)
        {
            double qtAvaria = 0;
            double qtSaldoAnterior = 0;
            double qtReposicao = 0;
            double qtProducao = 0;
            double qtSaldo = 0;
            double qtTransferencia = 0;
            
            _maquinaProducao = listaUm(_maquinaProducao);
            
            if (_maquinaProducao.getLstAvariaProducao() != null)
            {
                AvariaProducaoDAO avariaProducaoDAO = new AvariaProducaoDAO(conexao);
                Iterator itAvariaProducao = _maquinaProducao.getLstAvariaProducao().iterator();
                while (itAvariaProducao.hasNext())
                {
                    AvariaProducao avariaProducao = (AvariaProducao)itAvariaProducao.next();
                    qtAvaria += avariaProducao.getQuantidade();
                }
                

            }
        
            
             // salvando transferẽncias, se houver
            if (_maquinaProducao.getLstTransferenciaProducao() != null)
            {
                TransferenciaProducaoDAO transfDAO = new TransferenciaProducaoDAO(conexao);
                Iterator itTransf = _maquinaProducao.getLstTransferenciaProducao().iterator();
                while (itTransf.hasNext())
                {
                    TransferenciaProducao transf = (TransferenciaProducao)itTransf.next();
                    qtTransferencia += transf.getQuantidade();

                }
            }
            
            // atualizando quantidades
            qtSaldoAnterior = _maquinaProducao.getQtSaldoAnterior();
            qtReposicao = _maquinaProducao.getQtReposicao();
            qtProducao = (qtSaldoAnterior + qtReposicao) - qtAvaria;
            qtSaldo = qtProducao - qtTransferencia;
            
            comSql = "";
            comSql += " UPDATE `smmdaa_bdGelo`.`tblMaquinaProducao` SET";
            comSql += " 	`dblQtAvaria` = " + qtAvaria;
            comSql += " 	,`dblQtProducao` = " + qtProducao;
            comSql += " 	,`dblSaldo` = " + qtSaldo;
            comSql += " WHERE ";
            comSql += " 	`intMaquinaProducaoId` = " + _maquinaProducao.getIdMaquinaProducao() + ";";                
            atualizar();                
            
        }
        
        return retorno;
        
    }          
    
}
