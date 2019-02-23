/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author celso
 */
public class Produto implements Bean{
    private int idProduto;
    private String descricao;
    private double saldo;
    private double vlUnitario;
    private String tipo;
    private String ativo;
    private String descAtivo;    



    
    public Produto() {
        this.saldo = 0;
        this.vlUnitario = 0;
        this.ativo = "A";
    }
    
    

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
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

 
    
    
    
    public void replicar(Produto _produto)
    {
        _produto.setIdProduto(this.idProduto);
        _produto.setDescricao(this.descricao);
        _produto.setSaldo(this.saldo);
        _produto.setVlUnitario(this.vlUnitario);
        _produto.setTipo(this.tipo);
        _produto.setAtivo(this.ativo);
        
        
    
    }
}
