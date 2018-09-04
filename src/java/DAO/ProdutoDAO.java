package DAO;

import Bean.Produto;
import java.sql.Connection;
import java.util.*;


public class ProdutoDAO extends DAO
{

    public ProdutoDAO(Connection conexao)
    {
        super(conexao);
    }

    public List<Produto> listaTodos()
    {
        List<Produto> lstTabela = new ArrayList();
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblProduto`.`intProdutoId`,";
        comSql += "     `tblProduto`.`strDescricao`,";
        comSql += "     `tblProduto`.`dblSaldo`,";
        comSql += "     `tblProduto`.`dblVlUnitario`";
        comSql += " FROM `bdGelo`.`tblProduto`;";
        
        List tabela = super.listaTodos();
        
        for(int i = 0; i < tabela.size(); i++)
        {
            Produto produto = new Produto();
            List bkp = (ArrayList)tabela.get(i);
            
            produto.setIdProduto(((Integer)bkp.get(0)).intValue());
            produto.setDescricao((String)bkp.get(1));
            produto.setSaldo((Double)bkp.get(2));
            produto.setVlUnitario((Double)bkp.get(3));

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
        comSql += "     `tblProduto`.`dblVlUnitario`";
        comSql += " FROM `bdGelo`.`tblProduto`";
        comSql += " WHERE ";
        comSql += "     `tblProduto`.`intProdutoId` = " + produto.getIdProduto()+ ";";
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
            comSql += " INSERT INTO `bdGelo`.`tblProduto`";
            comSql += " 	(`strDescricao`,";
            comSql += " 	`dblSaldo`,";
            comSql += "         `dblVlUnitario`)";
            comSql += " VALUES";
            comSql += " 	('" + _produto.getDescricao() + "'";
            comSql += " 	," + _produto.getSaldo() ;
            comSql += " 	," + _produto.getVlUnitario() + ");";
            
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
            comSql += " UPDATE `bdGelo`.`tblProduto` SET";
            comSql += " 	`strDescricao` = '" + _produto.getDescricao() + "',";
            comSql += " 	`dblSaldo` = " + _produto.getSaldo() + ",";
            comSql += " 	`dblVlUnitario` = " + _produto.getVlUnitario();
            comSql += " WHERE ";
            comSql += " 	`intProdutoId` = " + _produto.getIdProduto() + ";";

            retorno = atualizar();
        }
        
        return retorno;
        
    }   
}
