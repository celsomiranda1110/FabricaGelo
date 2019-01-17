/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author celso
 */
public class Parcela implements Bean{
    private int idParcela;
    private int idPagamento;
    
    private int numParcela;
    private Date dtPagamento;
    private String dtPagamentoFormatado;
    private Date dtVencimento;
    private String dtVencimentoFormatado;
    private double valor;
    private double valorPago;
    private String situacao;
    private String descSituacao;
    private String formaPagamento;
    
    private String descFormaPagamento;

    String formato = "dd/MM/yyyy";
    
    
    public int getIdParcela() {
        return idParcela;
    }

    public void setIdParcela(int idParcela) {
        this.idParcela = idParcela;
    }
   
    public int getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(int idPagamento) {
        this.idPagamento = idPagamento;
    }

    public int getNumParcela() {
        return numParcela;
    }

    public void setNumParcela(int numParcela) {
        this.numParcela = numParcela;
    }

    public Date getDtPagamento() {
        return dtPagamento;
    }

    public void setDtPagamento(Date dtPagamento) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        this.dtPagamento = dtPagamento;
        this.dtPagamentoFormatado = formatter.format(this.dtPagamento);
    }

    public Date getDtVencimento() {
        return dtVencimento;
    }

    public void setDtVencimento(Date dtVencimento) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        this.dtVencimento = dtVencimento;
        this.dtVencimentoFormatado = formatter.format(this.dtVencimento);
    }

    public String getDtPagamentoFormatado() {
        return dtPagamentoFormatado;
    }

    public void setDtPagamentoFormatado(String dtPagamentoFormatado) {
        this.dtPagamentoFormatado = dtPagamentoFormatado;
    }

    public String getDtVencimentoFormatado() {
        return dtVencimentoFormatado;
    }

    public void setDtVencimentoFormatado(String dtVencimentoFormatado) {
        this.dtVencimentoFormatado = dtVencimentoFormatado;
    }



    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
        
        if (situacao.equals("P"))
            this.descSituacao = "PARCELA PENDENTE";
        else if(situacao.equals("G"))
            this.descSituacao = "PARCELA PAGA";
        else if(situacao.equals("C"))
            this.descSituacao = "PARCELA CADASTRADA";
    }

    public String getDescSituacao() {
        return descSituacao;
    }
    


    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
        if (this.formaPagamento.equals("DI"))
            this.descFormaPagamento = "DINHEIRO";
        else if (this.formaPagamento.equals("DB"))
            this.descFormaPagamento = "DÉBITO";
        else if (this.formaPagamento.equals("CR"))
            this.descFormaPagamento = "CRÉDITO";
        else if (this.formaPagamento.equals("CT"))
            this.descFormaPagamento = "CARTEIRA"; 
        else if (this.formaPagamento.equals("TR"))
            this.descFormaPagamento = "TRANSFERÊNCIA";         
    }

    public String getDescFormaPagamento() {
        return descFormaPagamento;
    }

    public void setDescFormaPagamento(String descFormaPagamento) {
        this.descFormaPagamento = descFormaPagamento;
    }

    
    
    
    
    public void replicar(Parcela _parcela)
    {
        _parcela.setIdParcela(this.idParcela);
        _parcela.setIdPagamento(this.idPagamento);
        _parcela.setNumParcela(this.numParcela);
        _parcela.setDtPagamento(this.dtPagamento);
        _parcela.setDtVencimento(this.dtVencimento);
        _parcela.setValor(this.valor);
        _parcela.setValorPago(this.valorPago);
        _parcela.setSituacao(this.situacao);
        _parcela.setFormaPagamento(this.formaPagamento);
    }
    
}
