/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Bean.Menu;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Miranda
 */
public class MenuDAO extends DAO{

    public MenuDAO(Connection conexao) {
        super(conexao);
    }
    
    
    public Menu listaUm(Menu menu) 
    {
        comSql = "";        
        comSql += " SELECT `tblMenu`.`intMenuId`,"; 
        comSql += "     `tblMenu`.`intSubMenuId`,"; 
        comSql += "     `tblMenu`.`strDescricao`,"; 
        comSql += "     `tblMenu`.`strCaminhoWeb`"; 
        comSql += " FROM `bdGelo`.`tblMenu` ; "; 
        
        
        List tabela = super.listaUm();
        if(!tabela.isEmpty())
        {
            for(int i = 0; i < tabela.size(); i++)
            {
                List registro = (ArrayList)tabela.get(i);
                menu.setIdMenu(((Integer)registro.get(0)).intValue());
                menu.setIdSubMenu(((Integer)registro.get(1)).intValue());
                menu.setDescricao(((String)registro.get(2)));
                menu.setCaminhoWeb(((String)registro.get(3)));

            }

            return menu;
        } else
        {
            return null;
        }        
    }   
    
    public List<Menu> listaTodos()
    {
        List lstTabela;
        lstTabela = new ArrayList();
        
        comSql = "";        
        comSql += " SELECT `tblMenu`.`intMenuId`,"; 
        comSql += "     `tblMenu`.`intSubMenuId`,"; 
        comSql += "     `tblMenu`.`strDescricao`,"; 
        comSql += "     `tblMenu`.`strCaminhoWeb`"; 
        comSql += " FROM `bdGelo`.`tblMenu`  "; 
        comSql += " ORDER BY ";
        comSql += "     `tblMenu`.`intMenuId`;"; 
        
        List tabela = super.listaTodos();
        
        for(int i = 0; i < tabela.size(); i++)
        {
            Menu _menu = new Menu();
            
            List bkp = (ArrayList)tabela.get(i);
            _menu.setIdMenu(((Integer)bkp.get(0)).intValue());
            _menu.setIdSubMenu(((Integer)bkp.get(1)).intValue());
            _menu.setDescricao(((String)bkp.get(2)));
            _menu.setCaminhoWeb(((String)bkp.get(3)));

            
            lstTabela.add(_menu);
        }
        return lstTabela;
    }
}
