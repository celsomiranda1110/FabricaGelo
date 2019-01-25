/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

/**
 *
 * @author celso
 */
public class MenuProfissional implements Bean{
    
    private int idMenuProfissional;
    private int idMenu;
    private int idProfissional;
    private String ativo;

    public int getIdMenuProfissional() {
        return idMenuProfissional;
    }

    public void setIdMenuProfissional(int idMenuProfissional) {
        this.idMenuProfissional = idMenuProfissional;
    }

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public int getIdProfissional() {
        return idProfissional;
    }

    public void setIdProfissional(int idProfissional) {
        this.idProfissional = idProfissional;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }
    
    public void replicar(MenuProfissional _menuProfissional)
    {
        _menuProfissional.setIdMenuProfissional(this.idMenuProfissional);
        _menuProfissional.setIdMenu(this.idMenu);
        _menuProfissional.setIdProfissional(this.idProfissional);
        _menuProfissional.setAtivo(this.ativo);
    }
    
}
