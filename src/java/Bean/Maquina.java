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
public class Maquina implements Bean{
    private int idMaquina;
    private String descricao;
    private String situacao;
    
    private String descSituacao;
    

    public int getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(int idMaquina) {
        this.idMaquina = idMaquina;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        if (situacao.equals("A"))
            this.descSituacao = "Ativo";
        else
            this.descSituacao = "Inativo";
        this.situacao = situacao;
    }

    public String getDescSituacao() {
        return descSituacao;
    }

    public String toString()
    {
        return this.descricao;
    }
    
    public void replicar(Maquina _maquina)
    {
        _maquina.setIdMaquina(this.idMaquina);
        _maquina.setDescricao(this.descricao);
        _maquina.setSituacao(this.situacao);
    }
}
