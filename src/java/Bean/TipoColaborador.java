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
public class TipoColaborador implements Bean{
    
    private int idTipoColaborador;
    private String descricao;
    private String ativo;
    private String descAtivo;

    public TipoColaborador() {
        this.ativo = "A";
    }
    
    

    public int getIdTipoColaborador() {
        return idTipoColaborador;
    }

    public void setIdTipoColaborador(int idTipoColaborador) {
        this.idTipoColaborador = idTipoColaborador;
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
    
    public String toString()
    {
        return this.descricao;
    }
    
    public void replicar(TipoColaborador _tipoColaborador)
    {
        _tipoColaborador.setIdTipoColaborador(this.idTipoColaborador);
        _tipoColaborador.setDescricao(this.descricao);
        _tipoColaborador.setAtivo(this.ativo);
    }
    
}
