/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import Bean.MenuProfissional;
import Bean.Profissional;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author celso
 */
public class MenuProfissionalDAO extends DAO{

    public MenuProfissionalDAO(Connection conexao) {
        super(conexao);
    }
    
    
    
    public List<MenuProfissional> listaTodos(Profissional profissional)
    {
        List<MenuProfissional> lstTabela = new ArrayList();
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblMenuProfissional`.`intMenuProfissionalId`,";
        comSql += "     `tblMenuProfissional`.`intMenuId`,";
        comSql += "     `tblMenuProfissional`.`intProfissionalId`,";
        comSql += "     `tblMenuProfissional`.`bolAtivo`";
        comSql += " FROM `smmdaa_bdGelo`.`tblMenuProfissional`";
        comSql += " WHERE ";
        comSql += "     `tblMenuProfissional`.`intProfissionalId` = " + profissional.getIdProfissional() + ";";

        
        List tabela = super.listaTodos();
        
        for(int i = 0; i < tabela.size(); i++)
        {
            MenuProfissional menuProfissional = new MenuProfissional();
            List bkp = (ArrayList)tabela.get(i);
            
            menuProfissional.setIdMenuProfissional(((Integer)bkp.get(0)).intValue());
            menuProfissional.setIdMenu(((Integer)bkp.get(1)).intValue());
            menuProfissional.setIdProfissional(((Integer)bkp.get(2)).intValue());
            menuProfissional.setAtivo((String)bkp.get(3));
            
            
            lstTabela.add(menuProfissional);
        }
        
        return lstTabela;
    }
    
    public MenuProfissional listaUm(MenuProfissional menuProfissional)
    {
        
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblMenuProfissional`.`intMenuProfissionalId`,";
        comSql += "     `tblMenuProfissional`.`intMenuId`,";
        comSql += "     `tblMenuProfissional`.`intProfissionalId`,";
        comSql += "     `tblMenuProfissional`.`bolAtivo`";
        comSql += " FROM `smmdaa_bdGelo`.`tblMenuProfissional`";
        comSql += " WHERE ";
        comSql += "     `tblMenuProfissional`.`intMenuProfissionalId` = " + menuProfissional.getIdMenuProfissional()+ ";";
        List tabela = super.listaUm();
        
        if(!tabela.isEmpty())
        {
            for(int i = 0; i < tabela.size(); i++)
            {
                
                List bkp = (ArrayList)tabela.get(i);

                menuProfissional.setIdMenuProfissional(((Integer)bkp.get(0)).intValue());
                menuProfissional.setIdMenu(((Integer)bkp.get(1)).intValue());
                menuProfissional.setIdProfissional(((Integer)bkp.get(2)).intValue());
                menuProfissional.setAtivo((String)bkp.get(3));
                
            }  
            return menuProfissional;
        }
        else
            return null;

    }
    
    public boolean atualizar(MenuProfissional menuProfissional) 
    {
        boolean retorno;
        
        MenuProfissional _menuProfissional = new MenuProfissional();
        menuProfissional.replicar(_menuProfissional);
       
        if (listaUm(menuProfissional) == null)
        {
            comSql = "";
            comSql += " INSERT INTO `smmdaa_bdGelo`.`tblMenuProfissional`";
            comSql += " 	(`intMenuId`,";
            comSql += " 	`intProfissionalId`,";
            comSql += " 	`bolAtivo`)";
            comSql += " VALUES";
            comSql += " 	(" + _menuProfissional.getIdMenu();
            comSql += " 	," + _menuProfissional.getIdProfissional();
            comSql += " 	,'" + _menuProfissional.getAtivo()  + "');";
            
            retorno = atualizar();
            if(retorno)
            {
                comSql = "";
                comSql += " Select max(intMenuProfissionalId) as idMenuProfissional from `tblMenuProfissional`;";
                List lstConsulta = listaUm();
                for(int i = 0; i < lstConsulta.size(); i++)
                {
                    List reg = ((ArrayList)lstConsulta.get(0));
                    identity = ((Integer)reg.get(0)).intValue();
                    _menuProfissional.setIdMenuProfissional(((Integer)reg.get(0)).intValue() );
                }
                
            }            
        }
        else
        {
            comSql = "";
            comSql += " UPDATE `smmdaa_bdGelo`.`tblMenuProfissional` SET";
            comSql += " 	`intMenuId` = " + _menuProfissional.getIdMenu();
            comSql += " 	,`intProfissionalId` = " + _menuProfissional.getIdProfissional();
            comSql += " 	,`bolAtivo` = '" + _menuProfissional.getAtivo() + "'";
            comSql += " WHERE ";
            comSql += " 	`intMenuProfissionalId` = " + _menuProfissional.getIdMenuProfissional() + ";";
            retorno = atualizar();
        }
        
        return retorno;
        
    }         
    
}
