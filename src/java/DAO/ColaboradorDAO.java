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
import Bean.Colaborador;
import Bean.ColaboradorProduto;
import Bean.Bairro;
import Bean.TipoColaborador;
import Bean.VisitaColaborador;
import java.sql.Connection;
import java.util.*;

public class ColaboradorDAO extends DAO{

    public ColaboradorDAO(Connection conexao) {
        super(conexao);
    }
    
    public List<Colaborador> listaTodos(Colaborador _colaborador)
    {
        List<Colaborador> lstTabela = new ArrayList();
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblColaborador`.`intColaboradorId`,";
        comSql += " 	`tblColaborador`.`intTipoColaboradorId`,";
        comSql += "     `tblColaborador`.`strCNPJ`,";
        comSql += "     `tblColaborador`.`strInscricaoEstadual`,";
        comSql += "     `tblColaborador`.`strInscricaoMunicipal`,";
        comSql += "     `tblColaborador`.`strNumMei`,";
        comSql += "     `tblColaborador`.`strNome`,";
        comSql += "     `tblColaborador`.`strRazaoSocial`,";
        comSql += "     `tblColaborador`.`strEndereco`,";
        comSql += "     `tblColaborador`.`strComplemento`,";
        comSql += "     `tblColaborador`.`intBairroId`,";
        comSql += "     `tblColaborador`.`strCpfContato`,";
        comSql += "     `tblColaborador`.`strContato`,";
        comSql += "     `tblColaborador`.`strFone`,";
        comSql += "     `tblColaborador`.`strEmail`,";
        comSql += "     `tblColaborador`.`strObservacao`,";
        comSql += "     `tblColaborador`.`bolAtivo`";
        comSql += " FROM `smmdaa_bdGelo`.`tblColaborador`";
        if (_colaborador != null)
        {
            comSql += " WHERE ";
            comSql += "     `tblColaborador`.`bolAtivo` = '" + _colaborador.getAtivo() + "'";
        }
        comSql += " ORDER BY ";
        comSql += "     `tblColaborador`.`strNome`;";

        
        List tabela = super.listaTodos();
        
        for(int i = 0; i < tabela.size(); i++)
        {
            Colaborador colaborador = new Colaborador();
            List bkp = (ArrayList)tabela.get(i);
            
            colaborador.setIdColaborador(((Integer)bkp.get(0)).intValue());
            colaborador.setIdTipoColaborador(((Integer)bkp.get(1)).intValue());
            colaborador.setCnpj((String)bkp.get(2));
            colaborador.setInscricaoEstadual((String)bkp.get(3));
            colaborador.setInscricaoMunicipal((String)bkp.get(4));
            colaborador.setNumMei((String)bkp.get(5));
            colaborador.setNome((String)bkp.get(6));
            colaborador.setRazaoSocial((String)bkp.get(7));
            colaborador.setEndereco((String)bkp.get(8));
            colaborador.setComplemento((String)bkp.get(9));
            colaborador.setIdBairro(((Integer)bkp.get(10)).intValue());
            colaborador.setCpf((String)bkp.get(11));
            colaborador.setContato((String)bkp.get(12));
            colaborador.setFone((String)bkp.get(13));
            colaborador.setEmail((String)bkp.get(14));
            colaborador.setObservacao((String)bkp.get(15));
            colaborador.setAtivo((String)bkp.get(16));
            
            BairroDAO _bairroDAO = new BairroDAO(conexao);
            Bairro _bairro = new Bairro();
            _bairro.setIdBairro(colaborador.getIdBairro());
            _bairro = _bairroDAO.listaUm(_bairro);
            colaborador.setBairro(_bairro); 
            
            TipoColaboradorDAO tipoColaboradorDAO = new TipoColaboradorDAO(conexao);
            TipoColaborador _tipoColaborador = new TipoColaborador();
            _tipoColaborador.setIdTipoColaborador(colaborador.getIdTipoColaborador());
            _tipoColaborador = tipoColaboradorDAO.listaUm(_tipoColaborador);
            colaborador.setTipoColaborador(_tipoColaborador);
            
//            List<VisitaColaborador> lstVisitaColaborador = new ArrayList<VisitaColaborador>();
//            VisitaColaboradorDAO _visitaColaboradorDAO = new VisitaColaboradorDAO(conexao);
//            lstVisitaColaborador = _visitaColaboradorDAO.listaTodos(colaborador);
//            colaborador.setLstVisitaColaborador(lstVisitaColaborador);            
            
            lstTabela.add(colaborador);
        }
        
        return lstTabela;
    }
    
    public Colaborador listaUm(Colaborador colaborador)
    {
        
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblColaborador`.`intColaboradorId`,";
        comSql += " 	`tblColaborador`.`intTipoColaboradorId`,";
        comSql += "     `tblColaborador`.`strCNPJ`,";
        comSql += "     `tblColaborador`.`strInscricaoEstadual`,";
        comSql += "     `tblColaborador`.`strInscricaoMunicipal`,";
        comSql += "     `tblColaborador`.`strNumMei`,";
        comSql += "     `tblColaborador`.`strNome`,";
        comSql += "     `tblColaborador`.`strRazaoSocial`,";
        comSql += "     `tblColaborador`.`strEndereco`,";
        comSql += "     `tblColaborador`.`strComplemento`,";
        comSql += "     `tblColaborador`.`intBairroId`,";
        comSql += "     `tblColaborador`.`strCpfContato`,";
        comSql += "     `tblColaborador`.`strContato`,";
        comSql += "     `tblColaborador`.`strFone`,";
        comSql += "     `tblColaborador`.`strEmail`,";
        comSql += "     `tblColaborador`.`strObservacao`,";
        comSql += "     `tblColaborador`.`bolAtivo`";
        comSql += " FROM `smmdaa_bdGelo`.`tblColaborador`";
        comSql += " WHERE ";
        comSql += "     `tblColaborador`.`intColaboradorId` = " + colaborador.getIdColaborador() + ";";
        List tabela = super.listaUm();
        
        if(!tabela.isEmpty())
        {
            for(int i = 0; i < tabela.size(); i++)
            {
                
                List bkp = (ArrayList)tabela.get(i);

                colaborador.setIdColaborador(((Integer)bkp.get(0)).intValue());
                colaborador.setIdTipoColaborador(((Integer)bkp.get(1)).intValue());
                colaborador.setCnpj((String)bkp.get(2));
                colaborador.setInscricaoEstadual((String)bkp.get(3));
                colaborador.setInscricaoMunicipal((String)bkp.get(4));
                colaborador.setNumMei((String)bkp.get(5));
                colaborador.setNome((String)bkp.get(6));
                colaborador.setRazaoSocial((String)bkp.get(7));
                colaborador.setEndereco((String)bkp.get(8));
                colaborador.setComplemento((String)bkp.get(9));
                colaborador.setIdBairro(((Integer)bkp.get(10)).intValue());
                colaborador.setCpf((String)bkp.get(11));
                colaborador.setContato((String)bkp.get(12));
                colaborador.setFone((String)bkp.get(13));
                colaborador.setEmail((String)bkp.get(14));
                colaborador.setObservacao((String)bkp.get(15));
                colaborador.setAtivo((String)bkp.get(16));
                
                
                BairroDAO _bairroDAO = new BairroDAO(conexao);
                Bairro _bairro = new Bairro();
                _bairro.setIdBairro(colaborador.getIdBairro());
                _bairro = _bairroDAO.listaUm(_bairro);
                colaborador.setBairro(_bairro);
                
                TipoColaboradorDAO tipoColaboradorDAO = new TipoColaboradorDAO(conexao);
                TipoColaborador _tipoColaborador = new TipoColaborador();
                _tipoColaborador.setIdTipoColaborador(colaborador.getIdTipoColaborador());
                _tipoColaborador = tipoColaboradorDAO.listaUm(_tipoColaborador);
                colaborador.setTipoColaborador(_tipoColaborador);                
                
                List<ColaboradorProduto> lstColaboradorProduto = new ArrayList<ColaboradorProduto>();
                ColaboradorProdutoDAO _colaboradorProdutoDAO = new ColaboradorProdutoDAO(conexao);
                lstColaboradorProduto = _colaboradorProdutoDAO.listaTodos(colaborador);
                colaborador.setLstColaboradorProduto(lstColaboradorProduto);
                
                List<VisitaColaborador> lstVisitaColaborador = new ArrayList<VisitaColaborador>();
                VisitaColaboradorDAO _visitaColaboradorDAO = new VisitaColaboradorDAO(conexao);
                lstVisitaColaborador = _visitaColaboradorDAO.listaTodos(colaborador);
                colaborador.setLstVisitaColaborador(lstVisitaColaborador);
                
            }  
            return colaborador;
        }
        else
            return null;

    }
    
    public boolean atualizar(Colaborador colaborador) 
    {
        boolean retorno;
        
        Colaborador _colaborador = new Colaborador();
        colaborador.replicar(_colaborador);
        
       
        if (listaUm(colaborador) == null)
        {
            comSql = "";
            comSql += " INSERT INTO `smmdaa_bdGelo`.`tblColaborador`";
            comSql += "     (`intTipoColaboradorId`,";
            comSql += "     `strCNPJ`,";
            comSql += "     `strInscricaoEstadual`,";
            comSql += "     `strInscricaoMunicipal`,";
            comSql += "     `strNumMei`,";
            comSql += "     `strNome`,";
            comSql += "     `strRazaoSocial`,";
            comSql += "     `strEndereco`,";
            comSql += "     `strComplemento`,";
            comSql += "     `intBairroId`,";
            comSql += "     `strCpfContato`,";
            comSql += "     `strContato`,";
            comSql += "     `strFone`,";
            comSql += "     `strObservacao`,";
            comSql += "     `bolAtivo`,";
            comSql += "     `strEmail`)";
            comSql += " VALUES";
            comSql += "     (" + _colaborador.getIdTipoColaborador();
            comSql += "     ,'" + _colaborador.getCnpj() + "'";
            comSql += "     ,'" + _colaborador.getInscricaoEstadual() + "'";
            comSql += "     ,'" + _colaborador.getInscricaoMunicipal() + "'";
            comSql += "     ,'" + _colaborador.getNumMei() + "'";
            comSql += "     ,'" + _colaborador.getNome() + "'";
            comSql += "     ,'" + _colaborador.getRazaoSocial() + "'";
            comSql += "     ,'" + _colaborador.getEndereco() + "'";
            comSql += "     ,'" + _colaborador.getComplemento() + "'";
            comSql += "     ," + _colaborador.getIdBairro();
            comSql += "     ,'" + _colaborador.getCpf() + "'";
            comSql += "     ,'" + _colaborador.getContato() + "'";
            comSql += "     ,'" + _colaborador.getFone() + "'";
            comSql += "     ,'" + _colaborador.getObservacao() + "'";
            comSql += "     ,'" + _colaborador.getAtivo() + "'";
            comSql += "     ,'" + _colaborador.getEmail() + "');";
            
            retorno = atualizar();
            if(retorno)
            {
                comSql = "";
                comSql += " Select max(intColaboradorId) as idColaborador from `smmdaa_bdGelo`.`tblColaborador`;";
                List lstConsulta = listaUm();
                for(int i = 0; i < lstConsulta.size(); i++)
                {
                    List reg = ((ArrayList)lstConsulta.get(0));
                    identity = ((Integer)reg.get(0)).intValue();
                    _colaborador.setIdColaborador(((Integer)reg.get(0)).intValue() );
                }
                
            }            
        }
        else
        {
            comSql = "";
            comSql += "     UPDATE `smmdaa_bdGelo`.`tblColaborador` SET ";
            comSql += "     	`intTipoColaboradorId` = " + _colaborador.getIdTipoColaborador();
            comSql += "     	,`strCNPJ` = '" + _colaborador.getCnpj() + "'";
            comSql += "     	,`strInscricaoEstadual` = '" + _colaborador.getInscricaoEstadual() + "'";
            comSql += "     	,`strInscricaoMunicipal` = '" + _colaborador.getInscricaoMunicipal() + "'";
            comSql += "     	,`strNumMei` = '" + _colaborador.getNumMei() + "'";
            comSql += "     	,`strNome` = '" + _colaborador.getNome() + "'";
            comSql += "     	,`strRazaoSocial` = '" + _colaborador.getRazaoSocial() + "'";
            comSql += "     	,`strEndereco` = '" + _colaborador.getEndereco() + "'";
            comSql += "     	,`strComplemento` = '" + _colaborador.getComplemento() + "'";
            comSql += "     	,`intBairroId` = " + _colaborador.getIdBairro();
            comSql += "     	,`strCpfContato` = '" + _colaborador.getCpf() + "'";
            comSql += "     	,`strContato` = '" + _colaborador.getContato() + "'";
            comSql += "     	,`strFone` = '" + _colaborador.getFone() + "'";
            comSql += "     	,`strEmail` = '" + _colaborador.getEmail() + "'";
            comSql += "     	,`strObservacao` = '" + _colaborador.getObservacao() + "'";
            comSql += "     	,`bolAtivo` = '" + _colaborador.getAtivo() + "'";
            comSql += "     WHERE ";
            comSql += "     	`intColaboradorId` = " + _colaborador.getIdColaborador();
            retorno = atualizar();
            if (retorno)
                identity = _colaborador.getIdColaborador();
            
        }
        
        if (retorno)
        {
            if (_colaborador.getLstVisitaColaborador() != null)
            {
                VisitaColaboradorDAO visitaColaboradorDAO = new VisitaColaboradorDAO(conexao);
                if (_colaborador.getLstVisitaColaborador().size() != 0)
                {
                    Iterator itVisitaColaborador = _colaborador.getLstVisitaColaborador().iterator();
                    while (itVisitaColaborador.hasNext())
                    {
                        VisitaColaborador visitaColaborador = (VisitaColaborador)itVisitaColaborador.next();
                        visitaColaborador.setIdColaborador(_colaborador.getIdColaborador());
                        visitaColaboradorDAO.atualizar(visitaColaborador);
                    }
                }
                else
                {
                    for(int d = 1; d < 8; d++)
                    {
                        VisitaColaborador visitaColaborador = new VisitaColaborador();
                        visitaColaborador.setIdColaborador(_colaborador.getIdColaborador());
                        visitaColaborador.setDia(d);
                        visitaColaborador.setAtivo("I"); 
                        visitaColaboradorDAO.atualizar(visitaColaborador);
                    }                    
                }
            }
            
            if (_colaborador.getLstColaboradorProduto() != null)
            {
                ColaboradorProdutoDAO colaboradorProdutoDAO = new ColaboradorProdutoDAO(conexao);
                Iterator itColaboradorProduto = _colaborador.getLstColaboradorProduto().iterator();
                while (itColaboradorProduto.hasNext())
                {
                    ColaboradorProduto colaboradorProduto = (ColaboradorProduto)itColaboradorProduto.next();
                    colaboradorProduto.setIdColaborador(_colaborador.getIdColaborador());
                    colaboradorProdutoDAO.atualizar(colaboradorProduto);
                }
            }
        }
        
        
        
        return retorno;
        
    }
    
    public boolean delete(Colaborador colaborador)
    {
        boolean retorno = false;
        
        // Deletando Visita colaborador
        VisitaColaboradorDAO visitaColaboradorDAO = new VisitaColaboradorDAO(conexao);
        visitaColaboradorDAO.deleta(colaborador);
      
        // deletando produtos do colabordor
        ColaboradorProdutoDAO colaboradorProdutoDAO = new ColaboradorProdutoDAO(conexao);
        colaboradorProdutoDAO.delete(colaborador);
       
        // deletando colaborador
        comSql = "";
        comSql += " Delete from tblColaborador ";
        comSql += " where ";
        comSql += "     intColaboradorId = " + colaborador.getIdColaborador() + ";";
        retorno = atualizar();
        
        return retorno;
    }
    
}
