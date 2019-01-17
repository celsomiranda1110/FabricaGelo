/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author celso
 */
public class Pagamento implements Bean{
    private int idPagamento;
    private int idMovimento;
    private double valor;
    private double desconto;
    private double valorTotal;
    private Date data;
    private String dtDataFormatada;
    private int numParcela;
    private String situacao;
    private String cobranca;
    private boolean processar;
    
    String formato = "dd/MM/yyyy";
    private String descSituacao = "";

    private List<Parcela> lstParcela;

    public int getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(int idPagamento) {
        this.idPagamento = idPagamento;
    }

    public int getIdMovimento() {
        return idMovimento;
    }

    public void setIdMovimento(int idMovimento) {
        this.idMovimento = idMovimento;
    }

    
    
    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        this.data = data;
        this.dtDataFormatada = formatter.format(this.data);
    }

    public String getDtDataFormatada() {
        return dtDataFormatada;
    }

    public void setDtDataFormatada(String dtDataFormatada) {
        this.dtDataFormatada = dtDataFormatada;
    }



    public int getNumParcela() {
        return numParcela;
    }

    public void setNumParcela(int numParcela) {
        this.numParcela = numParcela;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
        if (this.situacao.equals("PG"))
            this.descSituacao = "PAGAMENTO LIQUIDADO";
        else if (this.situacao.equals("PP"))
            this.descSituacao = "EM PAGAMENTO";
        else if (this.situacao.equals("PC"))
            this.descSituacao = "PAGAMENTO CADASTRADO";
    }

    public String getDescSituacao() {
        return descSituacao;
    }

    public void setDescSituacao(String descSituacao) {
        this.descSituacao = descSituacao;
    }

    public String getCobranca() {
        return cobranca;
    }

    public void setCobranca(String cobranca) {
        this.cobranca = cobranca;
    }

    public List<Parcela> getLstParcela() {
        return lstParcela;
    }

    public void setLstParcela(List<Parcela> lstParcela) {
        this.lstParcela = lstParcela;
    }

    public boolean isProcessar() {
        return processar;
    }

    public void setProcessar(boolean processar) {
        this.processar = processar;
    }
    
    

    public void replicar(Pagamento _pagamento)
    {
        
        
        _pagamento.setIdPagamento(this.idPagamento);
        _pagamento.setIdMovimento(this.idMovimento);
        _pagamento.setValor(this.valor);
        _pagamento.setDesconto(this.desconto);
        _pagamento.setValorTotal(this.valorTotal);
        _pagamento.setData(this.data);
        _pagamento.setNumParcela(this.numParcela);
        _pagamento.setSituacao(this.situacao);
        _pagamento.setCobranca(this.cobranca);
        _pagamento.setProcessar(this.processar);
        
        _pagamento.setLstParcela(this.lstParcela);
        
    }
}
