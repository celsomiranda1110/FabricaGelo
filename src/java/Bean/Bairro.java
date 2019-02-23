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
public class Bairro implements Bean{
    private int idBairro;
    private String descricao;
    private String ativo;
    private String descAtivo;

    public Bairro() {
        this.ativo = "A";
    }

    
    
    public int getIdBairro() {
        return idBairro;
    }

    public void setIdBairro(int idBairro) {
        this.idBairro = idBairro;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
    
    
    
    public void replicar(Bairro _bairro)
    {
        _bairro.setIdBairro(this.idBairro);
        _bairro.setDescricao(this.descricao);
        _bairro.setAtivo(this.ativo);
    }
    
    public String toString()
    {
        return this.descricao;
    }
    
   
    
}
