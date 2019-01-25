/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Bean;

/**
 *
 * @author Miranda
 */
public class Menu implements Bean{

    private int idMenu;
    private int idSubMenu;
    private String descricao;
    private String caminhoWeb;
    private int ordem;

    
    public Menu() {
    }

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public int getIdSubMenu() {
        return idSubMenu;
    }

    public void setIdSubMenu(int idSubMenu) {
        this.idSubMenu = idSubMenu;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }



    public String getCaminhoWeb() {
        return caminhoWeb;
    }

    public void setCaminhoWeb(String caminhoWeb) {
        this.caminhoWeb = caminhoWeb;
    }

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    
    public String toString()
    {
        return this.descricao;
    }
    
    
}
