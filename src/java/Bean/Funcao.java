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
public class Funcao implements Bean{
    private int idFuncao;
    private String descricao; 
    private String ativo;
    private String descAtivo;    

    public Funcao() {
        this.ativo = "A";
    }

    
    
    public int getIdFuncao() {
        return idFuncao;
    }

    public void setIdFuncao(int idFuncao) {
        this.idFuncao = idFuncao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public void replicar(Funcao _funcao)
    {
        _funcao.setIdFuncao(this.idFuncao);
        _funcao.setDescricao(this.descricao);
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

    public String toString()
    {
        return this.descricao;
    }
}
