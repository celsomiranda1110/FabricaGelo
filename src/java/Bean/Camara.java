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
public class Camara implements Bean{
    private int idCamara;
    private String descricao;
    private double capacidade;
    private String situacao;
    private String descSituacao;

    public int getIdCamara() {
        return idCamara;
    }

    public void setIdCamara(int idCamara) {
        this.idCamara = idCamara;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(double capacidade) {
        this.capacidade = capacidade;
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
 
    
    public void replicar(Camara _camara)
    {
        _camara.setIdCamara(this.idCamara);
        _camara.setDescricao(this.descricao);
        _camara.setCapacidade(this.capacidade);
        _camara.setSituacao(this.situacao);
    }
    
}
