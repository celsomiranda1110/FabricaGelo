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
public class MaquinaProducao implements Bean{
    
    private int idMaquinaProducao;
    private int idEquipamento;
    private int idProducao;
    private int idProduto;
    private double qtReposicao;
    private double qtSaldoAnterior;
    private double qtProducao;
    private double qtAvaria;
    private double rendimento;
    private String hrInicial;
    private String hrFinal;
    
    private Equipamento maquina;
    private Produto embalagem;
    
    private List<AvariaProducao> lstAvariaProducao;

    public int getIdMaquinaProducao() {
        return idMaquinaProducao;
    }

    public void setIdMaquinaProducao(int idMaquinaProducao) {
        this.idMaquinaProducao = idMaquinaProducao;
    }

    public int getIdEquipamento() {
        return idEquipamento;
    }

    public void setIdEquipamento(int idEquipamento) {
        this.idEquipamento = idEquipamento;
    }

    public int getIdProducao() {
        return idProducao;
    }

    public void setIdProducao(int idProducao) {
        this.idProducao = idProducao;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public double getQtReposicao() {
        return qtReposicao;
    }

    public double getQtSaldoAnterior() {
        return qtSaldoAnterior;
    }

    public void setQtSaldoAnterior(double qtSaldoAnterior) {
        this.qtSaldoAnterior = qtSaldoAnterior;
    }

    public void setQtReposicao(double qtReposicao) {
        this.qtReposicao = qtReposicao;
    }

    public double getQtProducao() {
        return qtProducao;
    }

    public void setQtProducao(double qtProducao) {
        this.qtProducao = qtProducao;
    }

    public double getQtAvaria() {
        return qtAvaria;
    }

    public void setQtAvaria(double qtAvaria) {
        this.qtAvaria = qtAvaria;
    }

    public double getRendimento() {
        return rendimento;
    }

    public void setRendimento(double rendimento) {
        this.rendimento = rendimento;
    }

    public String getHrInicial() {
        return hrInicial;
    }

    public void setHrInicial(String hrInicial) {
        this.hrInicial = hrInicial;
    }

    public String getHrFinal() {
        return hrFinal;
    }

    public void setHrFinal(String hrFinal) {
        this.hrFinal = hrFinal;
    }

    public Equipamento getMaquina() {
        return maquina;
    }

    public void setMaquina(Equipamento maquina) {
        this.maquina = maquina;
        this.idEquipamento = maquina.getIdEquipamento();
    }

    public Produto getEmbalagem() {
        return embalagem;
    }

    public void setEmbalagem(Produto embalagem) {
        this.embalagem = embalagem;
        this.idProduto = embalagem.getIdProduto();
    }

    public List<AvariaProducao> getLstAvariaProducao() {
        return lstAvariaProducao;
    }

    public void setLstAvariaProducao(List<AvariaProducao> lstAvariaProducao) {
        this.lstAvariaProducao = lstAvariaProducao;
    }
    
    
   
    public void replicar(MaquinaProducao _maquinaProducao)
    {
        _maquinaProducao.setIdMaquinaProducao(this.idMaquinaProducao);
        _maquinaProducao.setIdEquipamento(this.idEquipamento);
        _maquinaProducao.setIdProducao(this.idProducao);
        _maquinaProducao.setIdProduto(this.idProduto);
        _maquinaProducao.setQtReposicao(this.qtReposicao);
        _maquinaProducao.setQtSaldoAnterior(this.qtSaldoAnterior);
        _maquinaProducao.setQtProducao(this.qtProducao);
        _maquinaProducao.setQtAvaria(this.qtAvaria);
        _maquinaProducao.setRendimento(this.rendimento);
        _maquinaProducao.setHrInicial(this.hrInicial);
        _maquinaProducao.setHrFinal(this.hrFinal);
        
        _maquinaProducao.setEmbalagem(this.embalagem);
        _maquinaProducao.setMaquina(this.maquina);
        
        _maquinaProducao.setLstAvariaProducao(this.lstAvariaProducao);
        
    }
    
    
    
}
