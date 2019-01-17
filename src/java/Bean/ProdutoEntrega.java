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
public class ProdutoEntrega implements Bean{
    
    private int idProdutoEntrega;
    private int idProduto;
    private int idEntrega;
    private double dblQuantidade;
    private double dblQuantidadeAvaria;
    private double dblQuantidadeCortesia;
    private double dblQuantidadeBonus;
    private double dblQuantidadeReposicao;
    private double dblQuantidadeVenda;
    private double dblSaldo;
    private String situacao;

    private Produto produto;
    
    private List<AvariaEntrega> lstAvariaEntrega;

    public ProdutoEntrega() {
        this.dblQuantidade = 0;
        this.dblQuantidadeAvaria = 0;
        this.dblQuantidadeCortesia = 0;
        this.dblQuantidadeBonus = 0;
        this.dblQuantidadeReposicao = 0;
        this.dblQuantidadeVenda = 0;
        this.dblSaldo = 0;
    }
    
    
    public int getIdProdutoEntrega() {
        return idProdutoEntrega;
    }

    public void setIdProdutoEntrega(int idProdutoEntrega) {
        this.idProdutoEntrega = idProdutoEntrega;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getIdEntrega() {
        return idEntrega;
    }

    public void setIdEntrega(int idEntrega) {
        this.idEntrega = idEntrega;
    }

    public double getDblQuantidade() {
        return dblQuantidade;
    }

    public void setDblQuantidade(double dblQuantidade) {
        this.dblQuantidade = dblQuantidade;
    }

    public double getDblQuantidadeAvaria() {
        return dblQuantidadeAvaria;
    }

    public void setDblQuantidadeAvaria(double dblQuantidadeAvaria) {
        this.dblQuantidadeAvaria = dblQuantidadeAvaria;
    }

    public double getDblQuantidadeCortesia() {
        return dblQuantidadeCortesia;
    }

    public void setDblQuantidadeCortesia(double dblQuantidadeCortesia) {
        this.dblQuantidadeCortesia = dblQuantidadeCortesia;
    }

    public double getDblQuantidadeBonus() {
        return dblQuantidadeBonus;
    }

    public void setDblQuantidadeBonus(double dblQuantidadeBonus) {
        this.dblQuantidadeBonus = dblQuantidadeBonus;
    }

    public double getDblQuantidadeReposicao() {
        return dblQuantidadeReposicao;
    }

    public void setDblQuantidadeReposicao(double dblQuantidadeReposicao) {
        this.dblQuantidadeReposicao = dblQuantidadeReposicao;
    }

    public double getDblQuantidadeVenda() {
        return dblQuantidadeVenda;
    }

    public void setDblQuantidadeVenda(double dblQuantidadeVenda) {
        this.dblQuantidadeVenda = dblQuantidadeVenda;
    }
    
    

    public double getDblSaldo() {
        return dblSaldo;
    }

    public void setDblSaldo(double dblSaldo) {
        this.dblSaldo = dblSaldo;
    }
    
    

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
        this.idProduto = produto.getIdProduto();
    }

    public List<AvariaEntrega> getLstAvariaEntrega() {
        return lstAvariaEntrega;
    }

    public void setLstAvariaEntrega(List<AvariaEntrega> lstAvariaEntrega) {
        this.lstAvariaEntrega = lstAvariaEntrega;
    }
    
    
    
    public void replicar(ProdutoEntrega _produtoEntrega)
    {
        _produtoEntrega.setIdProdutoEntrega(this.idProdutoEntrega);
        _produtoEntrega.setIdProduto(this.idProduto);
        _produtoEntrega.setIdEntrega(this.idEntrega);
        _produtoEntrega.setDblQuantidade(this.dblQuantidade);
        _produtoEntrega.setDblQuantidadeAvaria(this.dblQuantidadeAvaria);
        _produtoEntrega.setDblQuantidadeBonus(this.dblQuantidadeBonus);
        _produtoEntrega.setDblQuantidadeCortesia(this.dblQuantidadeCortesia);
        _produtoEntrega.setDblQuantidadeReposicao(this.dblQuantidadeReposicao);
        _produtoEntrega.setDblQuantidadeVenda(this.dblQuantidadeVenda);
        _produtoEntrega.setDblSaldo(this.dblSaldo);
        _produtoEntrega.setSituacao(this.situacao);
        
        if (this.produto != null)
            _produtoEntrega.setProduto(this.produto);
        
        _produtoEntrega.setLstAvariaEntrega(this.lstAvariaEntrega);
    }
    
}
