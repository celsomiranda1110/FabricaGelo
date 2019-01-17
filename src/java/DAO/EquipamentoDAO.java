/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Bean.Equipamento;
import Bean.Manutencao;
import Bean.Produto;
import Bean.ProdutoCamara;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author celso
 */
public class EquipamentoDAO extends DAO{

    public EquipamentoDAO(Connection conexao) {
        super(conexao);
    }
    
    public List<Equipamento> listaTodos(Equipamento _equipamento)
    {
        List<Equipamento> lstTabela = new ArrayList();
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += "     `tblEquipamento`.`intEquipamentoId`,";
        comSql += "     `tblEquipamento`.`strDescricao`,";
        comSql += "     `tblEquipamento`.`chrSituacao`,";
        comSql += "     `tblEquipamento`.`dblCapacidade`,";
        comSql += "     `tblEquipamento`.`chrTipo`,";
        comSql += "     `tblEquipamento`.`strPlaca`,";
        comSql += "     `tblEquipamento`.`strMarca`,";
        comSql += "     `tblEquipamento`.`strModelo`,";
        comSql += "     `tblEquipamento`.`strAno`,";
        comSql += "     `tblEquipamento`.`dblKm`";
        comSql += " FROM ";
        comSql += "     `smmdaa_bdGelo`.`tblEquipamento`";
        comSql += " WHERE ";
        comSql += "     `tblEquipamento`.`chrTipo` = '" + _equipamento.getTipo() + "'";
        comSql += " ORDER BY ";
        comSql += "     `tblEquipamento`.`strDescricao`;";
        List tabela = super.listaTodos();
        
        for(int i = 0; i < tabela.size(); i++)
        {
            Equipamento equipamento = new Equipamento();
            List bkp = (ArrayList)tabela.get(i);
            
            equipamento.setIdEquipamento(((Integer)bkp.get(0)).intValue());
            equipamento.setDescricao((String)bkp.get(1));
            equipamento.setSituacao((String)bkp.get(2));
            equipamento.setCapacidade((Double)bkp.get(3));
            equipamento.setTipo((String)bkp.get(4));
            equipamento.setPlaca((String)bkp.get(5));
            equipamento.setMarca((String)bkp.get(6));
            equipamento.setModelo((String)bkp.get(7));
            equipamento.setAno((String)bkp.get(8));
            equipamento.setQuilometragem((Double)bkp.get(9));
            
            lstTabela.add(equipamento);
        }
        
        return lstTabela;
    }
    
    public Equipamento listaUm(Equipamento equipamento)
    {
        
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += "     `tblEquipamento`.`intEquipamentoId`,";
        comSql += "     `tblEquipamento`.`strDescricao`,";
        comSql += "     `tblEquipamento`.`chrSituacao`,";
        comSql += "     `tblEquipamento`.`dblCapacidade`,";
        comSql += "     `tblEquipamento`.`chrTipo`,";
        comSql += "     `tblEquipamento`.`strPlaca`,";
        comSql += "     `tblEquipamento`.`strMarca`,";
        comSql += "     `tblEquipamento`.`strModelo`,";
        comSql += "     `tblEquipamento`.`strAno`,"; 
        comSql += "     `tblEquipamento`.`dblKm`";
        comSql += " FROM ";
        comSql += "     `smmdaa_bdGelo`.`tblEquipamento`";
        comSql += " WHERE ";
        comSql += "     `tblEquipamento`.`intEquipamentoId` = " + equipamento.getIdEquipamento() + ";";
        List tabela = super.listaUm();
        
        if(!tabela.isEmpty())
        {
            for(int i = 0; i < tabela.size(); i++)
            {
                
                List bkp = (ArrayList)tabela.get(i);

                equipamento.setIdEquipamento(((Integer)bkp.get(0)).intValue());
                equipamento.setDescricao((String)bkp.get(1));
                equipamento.setSituacao((String)bkp.get(2));
                equipamento.setCapacidade((Double)bkp.get(3));
                equipamento.setTipo((String)bkp.get(4));
                equipamento.setPlaca((String)bkp.get(5));
                equipamento.setMarca((String)bkp.get(6));
                equipamento.setModelo((String)bkp.get(7));
                equipamento.setAno((String)bkp.get(8));
                equipamento.setQuilometragem((Double)bkp.get(9));
                
                List<Manutencao> lstManutencao = new ArrayList<Manutencao>();
                ManutencaoDAO manutencaoDAO = new ManutencaoDAO(conexao);
                lstManutencao = manutencaoDAO.listaTodos(equipamento);
                equipamento.setLstManutencao(lstManutencao);
                
            }  
            return equipamento;
        }
        else
            return null;

    }
    
    public boolean atualizar(Equipamento equipamento) 
    {
        boolean retorno;
        
        Equipamento _equipamento = new Equipamento();
        equipamento.replicar(_equipamento);
        
        if (listaUm(equipamento) == null)
        {
            comSql = "";
            comSql += " INSERT INTO `smmdaa_bdGelo`.`tblEquipamento` ";
            comSql += " (`strDescricao`";
            comSql += " ,`chrSituacao`";
            comSql += " ,`dblCapacidade`";
            comSql += " ,`chrTipo`";
            comSql += " ,`strPlaca`";
            comSql += " ,`strMarca`";
            comSql += " ,`strModelo`";
            comSql += " ,`strAno`";
            comSql += " ,`dblKm`)";
            comSql += " VALUES";
            comSql += " ('" + _equipamento.getDescricao() + "'";
            comSql += " ,'" + _equipamento.getSituacao() + "'";
            comSql += " ," + _equipamento.getCapacidade();
            comSql += " ,'" + _equipamento.getTipo() + "'";
            comSql += " ,'" + _equipamento.getPlaca() + "'";
            comSql += " ,'" + _equipamento.getMarca() + "'";
            comSql += " ,'" + _equipamento.getModelo() + "'";
            comSql += " ,'" + _equipamento.getAno() + "'";
            comSql += " ," + _equipamento.getQuilometragem() + ");";
            
            retorno = atualizar();
            if(retorno)
            {
                comSql = "";
                comSql += " Select max(intEquipamentoId) as idEquipamento from `tblEquipamento`;";
                List lstConsulta = listaUm();
                for(int i = 0; i < lstConsulta.size(); i++)
                {
                    List reg = ((ArrayList)lstConsulta.get(0));
                    identity = ((Integer)reg.get(0)).intValue();
                    _equipamento.setIdEquipamento( ((Integer)reg.get(0)).intValue() );
                }
                
            }            
        }
        else
        {
            comSql = "";
            comSql += " UPDATE `smmdaa_bdGelo`.`tblEquipamento` SET";
            comSql += "	`strDescricao` = '" + _equipamento.getDescricao() + "'";
            comSql += "	,`chrSituacao` = '" + _equipamento.getSituacao() + "'";
            comSql += "	,`dblCapacidade` = " + _equipamento.getCapacidade() ;
            comSql += "	,`chrTipo` = '" + _equipamento.getTipo() + "'";
            comSql += "	,`strPlaca` = '" + _equipamento.getPlaca() + "'";
            comSql += "	,`strMarca` = '" + _equipamento.getMarca() + "'";
            comSql += "	,`strModelo` = '" + _equipamento.getModelo() + "'";
            comSql += "	,`strAno` = '" + _equipamento.getAno() + "'";
            comSql += "	,`dblKm` = '" + _equipamento.getQuilometragem();
            comSql += " WHERE ";
            comSql += "	`intEquipamentoId` = " + _equipamento.getIdEquipamento()+ ";  ";   
            retorno = atualizar();
        }
        
        if (retorno)
        {
            if (_equipamento.getLstManutencao() != null)
            {
                ManutencaoDAO manutencaoDAO = new ManutencaoDAO(conexao);
                Iterator it = _equipamento.getLstManutencao().iterator();
                while (it.hasNext())
                {
                    Manutencao _manutencao = (Manutencao)it.next();
                    _manutencao.setIdEquipamento(_equipamento.getIdEquipamento());
                    manutencaoDAO.atualizar(_manutencao);
                }
                
            }
            
            // vínculo com produto para formar objetos ProdutoCamara
            if (_equipamento.getTipo().equals("CA"))
            {
                ProdutoCamaraDAO produtoCamaraDAO  = new ProdutoCamaraDAO(conexao);
                ProdutoDAO produtoDAO = new ProdutoDAO(conexao);
                List<Produto> lstProduto = new ArrayList<Produto>();
                lstProduto = produtoDAO.listaTodos(null);
                Iterator itProduto = lstProduto.iterator();
                while (itProduto.hasNext())
                {
                    
                    ProdutoCamara produtoCamara = new ProdutoCamara();
                    Produto produto = (Produto)itProduto.next();
                    produtoCamara.setIdProduto(produto.getIdProduto());
                    produtoCamara.setIdEquipamento(_equipamento.getIdEquipamento());
                    produtoCamara = produtoCamaraDAO.listaUm(produtoCamara);
                    if (produtoCamara == null)
                    {
                        produtoCamara = new ProdutoCamara();
                        produtoCamara.setIdProduto(produto.getIdProduto());
                        produtoCamara.setIdEquipamento(_equipamento.getIdEquipamento());
                        produtoCamara.setDataFormatada("1900-01-01");
                        produtoCamara.setSaldo(0);
                        produtoCamara.setSaldoAnterior(0);
                        produtoCamaraDAO.atualizar(produtoCamara);
                    }
                    
                    
                }
            }
        }
        
        return retorno;
        
    }   
    
}
