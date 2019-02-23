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
    private String ativo;
    private String descAtivo;    

    public Avaria() {
        this.ativo = "A";
    }

    
    
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
    
    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
        if (ativo.equals("I"))
            this.descAtivo = "checked";
        else
            this.descAtivo = "";
    }   

    public String getDescAtivo() {
        return descAtivo;
    }
    
    public void replicar(Avaria _avaria)
    {
        _avaria.setIdAvaria(this.idAvaria);
        _avaria.setDescricao(this.descricao);
        _avaria.setAtivo(this.ativo);
    }
    
    
}
