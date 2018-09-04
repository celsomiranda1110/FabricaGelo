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
public class Avaria implements Bean{
    
    private int idAvaria;
    private String descricao;

    public int getIdAvaria() {
        return idAvaria;
    }

    public void setIdAvaria(int idAvaria) {
        this.idAvaria = idAvaria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public String toString()
    {
        return this.descricao;
    }
    
    public void replicar(Avaria _avaria)
    {
        _avaria.setIdAvaria(this.idAvaria);
        _avaria.setDescricao(this.descricao);
    }
    
    
}
