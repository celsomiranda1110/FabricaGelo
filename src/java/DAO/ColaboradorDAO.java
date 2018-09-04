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
import Bean.VisitaColaborador;
import java.sql.Connection;
import java.util.*;

public class ColaboradorDAO extends DAO{

    public ColaboradorDAO(Connection conexao) {
        super(conexao);
    }
    
    public List<Colaborador> listaTodos()
    {
        List<Colaborador> lstTabela = new ArrayList();
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblColaborador`.`intColaboradorId`,";
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
        comSql += "     `tblColaborador`.`strEmail`";
        comSql += " FROM `bdGelo`.`tblColaborador`;";
        
        List tabela = super.listaTodos();
        
        for(int i = 0; i < tabela.size(); i++)
        {
            Colaborador colaborador = new Colaborador();
            List bkp = (ArrayList)tabela.get(i);
            
            colaborador.setIdColaborador(((Integer)bkp.get(0)).intValue());
            colaborador.setCnpj((String)bkp.get(1));
            colaborador.setInscricaoEstadual((String)bkp.get(2));
            colaborador.setInscricaoMunicipal((String)bkp.get(3));
            colaborador.setNumMei((String)bkp.get(4));
            colaborador.setNome((String)bkp.get(5));
            colaborador.setRazaoSocial((String)bkp.get(6));
            colaborador.setEndereco((String)bkp.get(7));
            colaborador.setComplemento((String)bkp.get(8));
            colaborador.setIdBairro(((Integer)bkp.get(9)).intValue());
            colaborador.setCpf((String)bkp.get(10));
            colaborador.setContato((String)bkp.get(11));
            colaborador.setFone((String)bkp.get(12));
            colaborador.setEmail((String)bkp.get(13));
            
            lstTabela.add(colaborador);
        }
        
        return lstTabela;
    }
    
    public Colaborador listaUm(Colaborador colaborador)
    {
        
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblColaborador`.`intColaboradorId`,";
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
        comSql += "     `tblColaborador`.`strEmail`";
        comSql += " FROM `bdGelo`.`tblColaborador`";
        comSql += " WHERE ";
        comSql += "     `tblColaborador`.`intColaboradorId` = " + colaborador.getIdColaborador() + ";";
        List tabela = super.listaUm();
        
        if(!tabela.isEmpty())
        {
            for(int i = 0; i < tabela.size(); i++)
            {
                
                List bkp = (ArrayList)tabela.get(i);

                colaborador.setIdColaborador(((Integer)bkp.get(0)).intValue());
                colaborador.setCnpj((String)bkp.get(1));
                colaborador.setInscricaoEstadual((String)bkp.get(2));
                colaborador.setInscricaoMunicipal((String)bkp.get(3));
                colaborador.setNumMei((String)bkp.get(4));
                colaborador.setNome((String)bkp.get(5));
                colaborador.setRazaoSocial((String)bkp.get(6));
                colaborador.setEndereco((String)bkp.get(7));
                colaborador.setComplemento((String)bkp.get(8));
                colaborador.setIdBairro(((Integer)bkp.get(9)).intValue());
                colaborador.setCpf((String)bkp.get(10));
                colaborador.setContato((String)bkp.get(11));
                colaborador.setFone((String)bkp.get(12));
                colaborador.setEmail((String)bkp.get(13));
                
                
                BairroDAO _bairroDAO = new BairroDAO(conexao);
                Bairro _bairro = new Bairro();
                _bairro.setIdBairro(colaborador.getIdBairro());
                _bairro = _bairroDAO.listaUm(_bairro);
                colaborador.setBairro(_bairro);
                
                List<ColaboradorProduto> lstColaboradorProduto = new ArrayList<ColaboradorProduto>();
                ColaboradorProdutoDAO _colaboradorProdutoDAO = new ColaboradorProdutoDAO(conexao);
                ColaboradorProduto _colaboradorProduto = new ColaboradorProduto();
                _colaboradorProduto.setIdColaborador(colaborador.getIdColaborador());
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
        
//        List<ColaboradorProduto> lstProduto = colaborador.getLstColaboradorProduto();
//        List<VisitaColaborador> lstVisita = colaborador.getLstVisitaColaborador();
        
        if (listaUm(colaborador) == null)
        {
            comSql = "";
            comSql += " INSERT INTO `bdGelo`.`tblColaborador`";
            comSql += "     (`strCNPJ`,";
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
            comSql += "     `strEmail`)";
            comSql += " VALUES";
            comSql += "     ('" + _colaborador.getCnpj() + "'";
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
            comSql += "     ,'" + _colaborador.getEmail() + "');";
            
            retorno = atualizar();
            if(retorno)
            {
                comSql = "";
                comSql += " Select max(intColaboradorId) as idColaborador from `tblColaborador`;";
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
            comSql += "     UPDATE `bdGelo`.`tblColaborador` SET ";
            comSql += "     	`strCNPJ` = '" + _colaborador.getCnpj() + "'";
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
            comSql += "     WHERE ";
            comSql += "     	`intColaboradorId` = " + _colaborador.getIdColaborador();
            retorno = atualizar();
        }
        
        if (retorno)
        {
            if (_colaborador.getLstVisitaColaborador() != null)
            {
                VisitaColaboradorDAO visitaColaboradorDAO = new VisitaColaboradorDAO(conexao);
                Iterator itVisitaColaborador = _colaborador.getLstVisitaColaborador().iterator();
                while (itVisitaColaborador.hasNext())
                {
                    VisitaColaborador visitaColaborador = (VisitaColaborador)itVisitaColaborador.next();
                    visitaColaborador.setIdColaborador(_colaborador.getIdColaborador());
                    visitaColaboradorDAO.atualizar(visitaColaborador);
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
        
//        if (retorno)
//        {
//            if(lstProduto != null)
//            {
//                ColaboradorProdutoDAO colaboradorProdutoDAO = new ColaboradorProdutoDAO(conexao);
//                int ultimoProduto = lstProduto.size();
//                ColaboradorProduto colaboradorProduto = (ColaboradorProduto)lstProduto.get(--ultimoProduto);
//                if(colaboradorProduto != null)
//                {
//                    colaboradorProduto.setIdColaborador(_colaborador.getIdColaborador());
//                    colaboradorProdutoDAO.atualizar(colaboradorProduto);
//                }
//            }
//            
//            if (lstVisita != null)
//            {
//                VisitaColaboradorDAO visitaColaboradorDAO = new VisitaColaboradorDAO(conexao);
//                int ultimoVisita = lstVisita.size();
//                VisitaColaborador visitaColaborador = (VisitaColaborador)lstVisita.get(--ultimoVisita);
//                if (visitaColaborador != null)
//                {
//                    visitaColaborador.setIdColaborador(_colaborador.getIdColaborador());
//                    visitaColaboradorDAO.atualizar(visitaColaborador);
//                }
//            }
//            
//        }        
        
        return retorno;
        
    }
    
}
