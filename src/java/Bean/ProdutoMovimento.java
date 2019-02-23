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
public class ProdutoMovimento implements Bean{
    private int IdProdutoMovimento;
    private int IdMovimento;
    private int IdProduto;
    private double icms;
    private double valor;
    private double quantidade;
    private double valorTotal;
    private double desconto;
    private String operacao;
    

    private Produto produto;

    public ProdutoMovimento() {
        icms = 0;
        valor = 0;
        quantidade = 0;
        valorTotal = 0;
        desconto = 0;
        this.operacao = "SO";
    }
    
    
    
    public int getIdProdutoMovimento() {
        return IdProdutoMovimento;
    }

    public void setIdProdutoMovimento(int IdProdutoMovimento) {
        this.IdProdutoMovimento = IdProdutoMovimento;
    }

    public int getIdMovimento() {
        return IdMovimento;
    }

    public void setIdMovimento(int IdMovimento) {
        this.IdMovimento = IdMovimento;
    }

    public int getIdProduto() {
        return IdProduto;
    }

    public void setIdProduto(int IdProduto) {
        this.IdProduto = IdProduto;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
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

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
        this.IdProduto = produto.getIdProduto();
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public String toString()
    {
        return this.produto.toString();
    }

    
    public void replicar(ProdutoMovimento _produtoMovimento)
    {
        _produtoMovimento.setIdProdutoMovimento(this.IdProdutoMovimento);
        _produtoMovimento.setIdMovimento(this.IdMovimento);
        _produtoMovimento.setIdProduto(this.IdProduto);
        _produtoMovimento.setIcms(this.icms);
        _produtoMovimento.setValor(this.valor);
        _produtoMovimento.setQuantidade(this.quantidade);
        _produtoMovimento.setValorTotal(this.valorTotal);
        _produtoMovimento.setDesconto(this.desconto);
        _produtoMovimento.setOperacao(this.operacao);
        
        if (this.produto != null)
            _produtoMovimento.setProduto(this.produto);
    }
}
