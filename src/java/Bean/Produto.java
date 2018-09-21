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
public class Produto implements Bean{
    private int IdProduto;
    private String descricao;
    private double saldo;
    private double vlUnitario;
    private String tipo;

    public Produto() {
        this.saldo = 0;
        this.vlUnitario = 0;
    }
    
    

    public int getIdProduto() {
        return IdProduto;
    }

    public void setIdProduto(int IdProduto) {
        this.IdProduto = IdProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    


    public double getVlUnitario() {
        return vlUnitario;
    }

    public void setVlUnitario(double vlUnitario) {
        this.vlUnitario = vlUnitario;
    }
    
    
    public String toString()
    {
        return this.descricao;
    }
    
    public void replicar(Produto _produto)
    {
        _produto.setIdProduto(this.IdProduto);
        _produto.setDescricao(this.descricao);
        _produto.setSaldo(this.saldo);
        _produto.setVlUnitario(this.vlUnitario);
    }
}
