/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
/**
 *
 * @author celso
 */

public class SaidaCamara implements Bean{
    private int idSaidaCamara;
    private int idEquipamento;
    private int idProdutoCamara;
    private double saida;
    private double devolucao;
    private Date data;
    private String dataFormatada;
    private String situacao;
    private String descSituacao;
    private Equipamento equipamento;
    private ProdutoCamara produtoCamara;

    public int getIdSaidaCamara() {
        return idSaidaCamara;
    }

    public void setIdSaidaCamara(int idSaidaCamara) {
        this.idSaidaCamara = idSaidaCamara;
    }

    public int getIdEquipamento() {
        return idEquipamento;
    }

    public void setIdEquipamento(int idEquipamento) {
        this.idEquipamento = idEquipamento;
    }

    public int getIdProdutoCamara() {
        return idProdutoCamara;
    }

    public void setIdProdutoCamara(int idProdutoCamara) {
        this.idProdutoCamara = idProdutoCamara;
    }

    public double getSaida() {
        return saida;
    }

    public void setSaida(double saida) {
        this.saida = saida;
    }

    public double getDevolucao() {
        return devolucao;
    }

    public void setDevolucao(double devolucao) {
        this.devolucao = devolucao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        this.data = data;
        this.dataFormatada = formatter.format(this.data);
    }

    public String getDataFormatada() {
        return dataFormatada;
    }

    public void setDataFormatada(String dataFormatada) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        this.dataFormatada = dataFormatada;
        try{
            this.data = (Date)formatter.parse(dataFormatada);
        }catch(ParseException pe)
        {}
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getDescSituacao() {
        String retorno = "";
        
        if (this.situacao.equals("CC"))
            retorno = "CADASTRADO";
        else if(this.situacao.equals("CA"))
            retorno = "CARREGADO";
        else if(this.situacao.equals("CD"))
            retorno = "DEVOLVIDO";
        
        return retorno;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
        this.idEquipamento = equipamento.getIdEquipamento();
    }

    public ProdutoCamara getProdutoCamara() {
        return produtoCamara;
    }

    public void setProdutoCamara(ProdutoCamara produtoCamara) {
        this.produtoCamara = produtoCamara;
    }

    
    public void replicar(SaidaCamara _saidaCamara)
    {
        _saidaCamara.setIdSaidaCamara(this.idSaidaCamara);
        _saidaCamara.setIdProdutoCamara(this.idProdutoCamara);
        _saidaCamara.setIdEquipamento(this.idEquipamento);
        _saidaCamara.setSaida(this.saida);
        _saidaCamara.setDevolucao(this.devolucao);
        _saidaCamara.setData(this.data);
        _saidaCamara.setDataFormatada(this.dataFormatada);
        _saidaCamara.setSituacao(this.situacao);
        
        if (this.equipamento != null)
            _saidaCamara.setEquipamento(this.equipamento);
        
        if (this.produtoCamara != null)
            _saidaCamara.setProdutoCamara(this.produtoCamara);
    }
}
