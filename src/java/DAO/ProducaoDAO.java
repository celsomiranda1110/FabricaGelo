/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author Miranda
 */
import Bean.AvariaProducao;
import Bean.Maquina;
import Bean.Producao;
import Bean.Produto;
import java.sql.Connection;
import java.util.*;

public class ProducaoDAO extends DAO{

    public ProducaoDAO(Connection conexao) {
        super(conexao);
    }
    
    public List<Producao> listaTodos()
    {
        List<Producao> lstTabela = new ArrayList();
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblProducao`.`intProducaoId`,";
        comSql += "     `tblProducao`.`intProdutoId`,";
        comSql += "     `tblProducao`.`intMaquinaId`,";
        comSql += "     `tblProducao`.`strTurno`,";
        comSql += "     `tblProducao`.`datData`,";
        comSql += "     `tblProducao`.`dblQuantidade`,";
        comSql += "     `tblProducao`.`dblQuantidadeAnterior`,";
        comSql += "     `tblProducao`.`dblSobra`,";
        comSql += "     `tblProducao`.`dblAvaria`,";
        comSql += "     `tblProducao`.`strHoraInicial`,";
        comSql += "     `tblProducao`.`strHoraFinal`,";
        comSql += "     `tblProducao`.`dblRendimento`";
        comSql += " FROM `bdGelo`.`tblProducao`;";
        
        List tabela = super.listaTodos();
        
        for(int i = 0; i < tabela.size(); i++)
        {
            Producao producao = new Producao();
            List bkp = (ArrayList)tabela.get(i);
            
            producao.setIdProducao(((Integer)bkp.get(0)).intValue());
            producao.setIdProduto(((Integer)bkp.get(1)).intValue());
            producao.setIdMaquina(((Integer)bkp.get(2)).intValue());
            producao.setTurno((String)bkp.get(3));
            producao.setData((Date)bkp.get(4));
            producao.setQuantidade((Double)bkp.get(5));
            producao.setQuantidadeAnterior((Double)bkp.get(6));
            producao.setSobra((Double)bkp.get(7));
            producao.setQtAvaria((Double)bkp.get(8));
            producao.setHoraInicial((String)bkp.get(9));
            producao.setHoraFinal((String)bkp.get(10));
            producao.setRendimento((Double)bkp.get(11));
            
            ProdutoDAO produtoDAO = new ProdutoDAO(conexao);
            Produto produto = new Produto();
            produto.setIdProduto(producao.getIdProduto());
            produto = produtoDAO.listaUm(produto);
            producao.setProduto(produto);
            
            MaquinaDAO maquinaDAO = new MaquinaDAO(conexao);
            Maquina maquina = new Maquina();
            maquina.setIdMaquina(producao.getIdMaquina());
            maquina = maquinaDAO.listaUm(maquina);
            producao.setMaquina(maquina);

            lstTabela.add(producao);
        }
        
        return lstTabela;
    }
    
    public Producao listaUm(Producao producao)
    {
        
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblProducao`.`intProducaoId`,";
        comSql += "     `tblProducao`.`intProdutoId`,";
        comSql += "     `tblProducao`.`intMaquinaId`,";
        comSql += "     `tblProducao`.`strTurno`,";
        comSql += "     `tblProducao`.`datData`,";
        comSql += "     `tblProducao`.`dblQuantidade`,";
        comSql += "     `tblProducao`.`dblQuantidadeAnterior`,";
        comSql += "     `tblProducao`.`dblSobra`,";
        comSql += "     `tblProducao`.`dblAvaria`,";
        comSql += "     `tblProducao`.`strHoraInicial`,";
        comSql += "     `tblProducao`.`strHoraFinal`,";
        comSql += "     `tblProducao`.`dblRendimento`";        
        comSql += " FROM `bdGelo`.`tblProducao`";
        comSql += " WHERE ";
        comSql += "     `tblProducao`.`intProducaoId` = " + producao.getIdProducao()+ ";";
        List tabela = super.listaUm();
        
        if(!tabela.isEmpty())
        {
            for(int i = 0; i < tabela.size(); i++)
            {
                
                List bkp = (ArrayList)tabela.get(i);

                producao.setIdProducao(((Integer)bkp.get(0)).intValue());
                producao.setIdProduto(((Integer)bkp.get(1)).intValue());
                producao.setIdMaquina(((Integer)bkp.get(2)).intValue());
                producao.setTurno((String)bkp.get(3));
                producao.setData((Date)bkp.get(4));
                producao.setQuantidade((Double)bkp.get(5));
                producao.setQuantidadeAnterior((Double)bkp.get(6));
                producao.setSobra((Double)bkp.get(7));
                producao.setQtAvaria((Double)bkp.get(8));
                producao.setHoraInicial((String)bkp.get(9));
                producao.setHoraFinal((String)bkp.get(10));
                producao.setRendimento((Double)bkp.get(11));               

                ProdutoDAO produtoDAO = new ProdutoDAO(conexao);
                Produto produto = new Produto();
                produto.setIdProduto(producao.getIdProduto());
                produto = produtoDAO.listaUm(produto);
                producao.setProduto(produto);            
                
                MaquinaDAO maquinaDAO = new MaquinaDAO(conexao);
                Maquina maquina = new Maquina();
                maquina.setIdMaquina(producao.getIdMaquina());
                maquina = maquinaDAO.listaUm(maquina);
                producao.setMaquina(maquina); 
                
                AvariaProducaoDAO avariaProducaoDAO= new AvariaProducaoDAO(conexao);
                List<AvariaProducao> lstAvariaProducao = new ArrayList<AvariaProducao>();
                lstAvariaProducao = avariaProducaoDAO.listaTodos(producao);
                producao.setLstAvariaProducao(lstAvariaProducao);
            }  
            return producao;
        }
        else
            return null;

    }
    
    public boolean atualizar(Producao producao) 
    {
        boolean retorno;
        
        Producao _producao = new Producao();
        producao.replicar(_producao);
       
        if (listaUm(producao) == null)
        {
            comSql = "";
            comSql += " INSERT INTO `bdGelo`.`tblProducao`";
            comSql += " 	(`intProdutoId`,";
            comSql += " 	`intMaquinaId`,";
            comSql += " 	`strTurno`,";
            comSql += " 	`datData`,";
            comSql += " 	`dblQuantidade`,";
            comSql += " 	`dblQuantidadeAnterior`,";
            comSql += "         `dblSobra`,";
            comSql += "         `dblAvaria`,";
            comSql += "         `strHoraInicial`,";
            comSql += "         `strHoraFinal`,";
            comSql += "         `dblRendimento`)";
            comSql += " VALUES";
            comSql += " 	(" + _producao.getIdProduto();
            comSql += " 	," + _producao.getIdMaquina();
            comSql += " 	,'" + _producao.getTurno() + "'";
            comSql += " 	,'" + _producao.getDataFormatada() + "'";
            comSql += " 	," + _producao.getQuantidade();
            comSql += " 	," + _producao.getQuantidadeAnterior() ;
            comSql += " 	," + _producao.getSobra();
            comSql += " 	," + _producao.getQtAvaria();
            comSql += " 	,'" + _producao.getHoraInicial() + "'";
            comSql += " 	,'" + _producao.getHoraFinal() + "'";
            comSql += " 	," + _producao.getRendimento() + ");";
            
            
            retorno = atualizar();
            if(retorno)
            {
                comSql = "";
                comSql += " Select max(intProducaoId) as idProducao from `tblProducao`;";
                List lstConsulta = listaUm();
                for(int i = 0; i < lstConsulta.size(); i++)
                {
                    List reg = ((ArrayList)lstConsulta.get(0));
                    identity = ((Integer)reg.get(0)).intValue();
                    _producao.setIdProducao(((Integer)reg.get(0)).intValue() );
                }
                
            }            
        }
        else
        {
            comSql = "";
            comSql += " UPDATE `bdGelo`.`tblProducao` SET ";
            comSql += " 	`intProdutoId` = " + _producao.getIdProduto();
            comSql += " 	,`intMaquinaId` = " + _producao.getIdMaquina();
            comSql += " 	,`strTurno` = '" + _producao.getTurno() + "'";
            comSql += " 	,`datData` = '" + _producao.getDataFormatada() + "'";
            comSql += " 	,`dblQuantidade` = " + _producao.getQuantidade();
            comSql += " 	,`dblQuantidadeAnterior` = " + _producao.getQuantidadeAnterior();
            comSql += " 	,`dblSobra` = " + _producao.getSobra();
            comSql += " 	,`dblAvaria` = " + _producao.getQtAvaria();
            comSql += " 	,`strHoraInicial` = '" + _producao.getHoraInicial() + "'";
            comSql += " 	,`strHoraFinal` = '" + _producao.getHoraFinal() + "'";
            comSql += " 	,`dblRendimento` = " + _producao.getRendimento();
            comSql += " WHERE ";
            comSql += " 	`intProducaoId` =  " + _producao.getIdProducao() + ";";

            retorno = atualizar();
        }
        if (retorno)
        {
            if (_producao.getLstAvariaProducao() != null)
            {
                AvariaProducaoDAO avariaProducaoDAO = new AvariaProducaoDAO(conexao);
                Iterator itAvariaProducao = _producao.getLstAvariaProducao().iterator();
                while (itAvariaProducao.hasNext())
                {
                    
                    AvariaProducao _avariaProducao = (AvariaProducao)itAvariaProducao.next();
                    _avariaProducao.setIdProducao(_producao.getIdProducao());
                    avariaProducaoDAO.atualizar(_avariaProducao);
                }
            }
        }

        
        return retorno;
        
    }    
}
