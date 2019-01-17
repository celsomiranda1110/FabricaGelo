package DAO;

import Bean.Equipamento;
import Bean.Produto;
import Bean.ProdutoCamara;
import java.sql.Connection;
import java.util.*;


public class ProdutoDAO extends DAO
{

    public ProdutoDAO(Connection conexao)
    {
        super(conexao);
    }

    public List<Produto> listaTodos(Produto _produto)
    {
        List<Produto> lstTabela = new ArrayList();
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblProduto`.`intProdutoId`,";
        comSql += "     `tblProduto`.`strDescricao`,";
        comSql += "     `tblProduto`.`dblSaldo`,";
        comSql += "     `tblProduto`.`dblVlUnitario`,";
        comSql += "     `tblProduto`.`chrTipo`";
        comSql += " FROM `smmdaa_bdGelo`.`tblProduto`";
        if (_produto != null)
        {
            comSql += " WHERE";
            comSql += "     `tblProduto`.`chrTipo` = '" + _produto.getTipo() + "'";
        }
        comSql += ";";
        
        List tabela = super.listaTodos();
        
        for(int i = 0; i < tabela.size(); i++)
        {
            Produto produto = new Produto();
            List bkp = (ArrayList)tabela.get(i);
            
            produto.setIdProduto(((Integer)bkp.get(0)).intValue());
            produto.setDescricao((String)bkp.get(1));
            produto.setSaldo((Double)bkp.get(2));
            produto.setVlUnitario((Double)bkp.get(3));
            produto.setTipo((String)bkp.get(4));

            lstTabela.add(produto);
        }
        
        return lstTabela;
    }
    
    public Produto listaUm(Produto produto)
    {
        
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblProduto`.`intProdutoId`,";
        comSql += "     `tblProduto`.`strDescricao`,";
        comSql += "     `tblProduto`.`dblSaldo`,";
        comSql += "     `tblProduto`.`dblVlUnitario`,";
        comSql += "     `tblProduto`.`chrTipo`";
        comSql += " FROM `smmdaa_bdGelo`.`tblProduto`";
        comSql += " WHERE ";
        comSql += "     `tblProduto`.`intProdutoId` = " + produto.getIdProduto() + ";";
        List tabela = super.listaUm();
        
        if(!tabela.isEmpty())
        {
            for(int i = 0; i < tabela.size(); i++)
            {
                
                List bkp = (ArrayList)tabela.get(i);

                produto.setIdProduto(((Integer)bkp.get(0)).intValue());
                produto.setDescricao((String)bkp.get(1));
                produto.setSaldo((Double)bkp.get(2));
                produto.setVlUnitario((Double)bkp.get(3));
                produto.setTipo((String)bkp.get(4));
                

                
            }  
            return produto;
        }
        else
            return null;

    }
    
    public boolean atualizar(Produto produto) 
    {
        boolean retorno;
        
        Produto _produto = new Produto();
        produto.replicar(_produto);
       
        if (listaUm(produto) == null)
        {
            comSql = "";
            comSql += " INSERT INTO `smmdaa_bdGelo`.`tblProduto`";
            comSql += " 	(`strDescricao`,";
            comSql += " 	`dblSaldo`,";
            comSql += "         `dblVlUnitario`,";
            comSql += "         `chrTipo`)";
            comSql += " VALUES";
            comSql += " 	('" + _produto.getDescricao() + "'";
            comSql += " 	," + _produto.getSaldo() ;
            comSql += " 	," + _produto.getVlUnitario();
            comSql += " 	,'" + _produto.getTipo() + "');";
            
            retorno = atualizar();
            if(retorno)
            {
                comSql = "";
                comSql += " Select max(intProdutoId) as idProduto from `tblProduto`;";
                List lstConsulta = listaUm();
                for(int i = 0; i < lstConsulta.size(); i++)
                {
                    List reg = ((ArrayList)lstConsulta.get(0));
                    identity = ((Integer)reg.get(0)).intValue();
                    _produto.setIdProduto(((Integer)reg.get(0)).intValue() );
                }
                
            }            
        }
        else
        {
            comSql = "";
            comSql += " UPDATE `smmdaa_bdGelo`.`tblProduto` SET";
            comSql += " 	`strDescricao` = '" + _produto.getDescricao() + "',";
            comSql += " 	`dblSaldo` = " + _produto.getSaldo() + ",";
            comSql += " 	`dblVlUnitario` = " + _produto.getVlUnitario() + ",";
            comSql += " 	`chrTipo` = '" + _produto.getTipo() + "'";
            comSql += " WHERE ";
            comSql += " 	`intProdutoId` = " + _produto.getIdProduto() + ";";

            retorno = atualizar();
        }
        
        if (retorno)
        {
            
            
            // vinculando produtos a câmara
            ProdutoCamaraDAO produtoCamaraDAO = new ProdutoCamaraDAO(conexao);
            EquipamentoDAO equipamentoDAO = new EquipamentoDAO(conexao);
            Equipamento equipamento = new Equipamento();
            equipamento.setTipo("CA");
            List<Equipamento> lstCamara = new ArrayList<Equipamento>();
            lstCamara = equipamentoDAO.listaTodos(equipamento);
            Iterator itCamara = lstCamara.iterator();
            while (itCamara.hasNext())
            {
                Equipamento camara = (Equipamento)itCamara.next();
                ProdutoCamara produtoCamara = new ProdutoCamara();
                produtoCamara.setIdEquipamento(camara.getIdEquipamento());
                produtoCamara.setIdProduto(_produto.getIdProduto());
                produtoCamara = produtoCamaraDAO.listaUm(produtoCamara);
                if (produtoCamara == null)
                {
                    produtoCamara = new ProdutoCamara();
                    produtoCamara.setIdEquipamento(camara.getIdEquipamento());
                    produtoCamara.setIdProduto(_produto.getIdProduto());
                    produtoCamara.setDataFormatada("1900-01-01");
                    produtoCamara.setSaldo(0);
                    produtoCamara.setSaldoAnterior(0);
                    produtoCamaraDAO.atualizar(produtoCamara);
                }
            }
        }
        return retorno;
    }   
}
