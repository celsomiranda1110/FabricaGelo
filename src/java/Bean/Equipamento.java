/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.util.List;

/**
 *
 * @author celso
 */
public class Equipamento implements Bean{
  
    private int idEquipamento;
    private String descricao;
    private String situacao;
    private double capacidade;
    private String tipo;
    private String placa;
    private String marca;
    private String modelo;
    private String ano;
    private String descSituacao;
    private double quilometragem;
    private String ativo;
    private String descAtivo;    
    
    List<Manutencao> lstManutencao;

    public Equipamento() {
        this.ativo = "A";
    }
    
    

    public int getIdEquipamento() {
        return idEquipamento;
    }

    public void setIdEquipamento(int idEquipamento) {
        this.idEquipamento = idEquipamento;
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
        this.situacao = situacao;
        if (situacao.equals("A"))
            this.descSituacao = "EM FUNCIONAMENTO";
        else if (situacao.equals("P"))
            this.descSituacao = "PARADO";
        else if (situacao.equals("M"))
            this.descSituacao = "EM MANUTENÇÃO";
    }

    public double getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(double capacidade) {
        this.capacidade = capacidade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
        if (!tipo.equals("VE"))
            this.quilometragem = 0;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public List<Manutencao> getLstManutencao() {
        return lstManutencao;
    }

    public void setLstManutencao(List<Manutencao> lstManutencao) {
        this.lstManutencao = lstManutencao;
    }

    public String getDescSituacao() {
        return descSituacao;
    }

    public void setDescSituacao(String descSituacao) {
        this.descSituacao = descSituacao;
    }
    
    public String toString()
    {
        return this.descricao;
    }

    public double getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(double quilometragem) {
        this.quilometragem = quilometragem;
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
 
    public void replicar(Equipamento _equipamento)
    {
        _equipamento.setIdEquipamento(this.idEquipamento);
        _equipamento.setDescricao(this.descricao);
        _equipamento.setSituacao(this.situacao);
        _equipamento.setCapacidade(this.capacidade);
        _equipamento.setTipo(this.tipo);
        _equipamento.setPlaca(this.placa);
        _equipamento.setMarca(this.marca);
        _equipamento.setModelo(this.modelo);
        _equipamento.setAno(this.ano);
        _equipamento.setQuilometragem(this.quilometragem);
        _equipamento.setAtivo(this.ativo);
        
        _equipamento.setLstManutencao(this.lstManutencao);
    }
}
