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
public class ColaboradorProduto implements Bean{
    private int idColaboradorProduto;
    private int idProduto;
    private int idColaborador;
    private double icms;
    private double desconto;
    private double valor;

    
    private Produto produto;

    public ColaboradorProduto() {
        valor = 0;
    }

    public int getIdColaboradorProduto() {
        return idColaboradorProduto;
    }

    public void setIdColaboradorProduto(int idColaboradorProduto) {
        this.idColaboradorProduto = idColaboradorProduto;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(int idColaborador) {
        this.idColaborador = idColaborador;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
        this.idProduto = produto.getIdProduto();
    }

    public double getIcms() {
        return icms;
    }

    public void setIcms(double icms) {
        this.icms = icms;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }


    
    public void replicar(ColaboradorProduto _colaboradorProduto)
    {
        _colaboradorProduto.setIdColaboradorProduto(this.idColaboradorProduto);
        _colaboradorProduto.setIdProduto(this.idProduto);
        _colaboradorProduto.setIdColaborador(this.idColaborador);
        _colaboradorProduto.setIcms(this.icms);
        _colaboradorProduto.setValor(this.valor);
        _colaboradorProduto.setDesconto(this.desconto);
    }

}
