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
public class ProdutoCamara implements Bean{
    private int IdProdutoCamara;
    private int IdProduto;
    private int IdCamara;
    private String data;
    private double saldoAnterior;
    private double saldo;

    public int getIdProdutoCamara() {
        return IdProdutoCamara;
    }

    public void setIdProdutoCamara(int IdProdutoCamara) {
        this.IdProdutoCamara = IdProdutoCamara;
    }

    public int getIdProduto() {
        return IdProduto;
    }

    public void setIdProduto(int IdProduto) {
        this.IdProduto = IdProduto;
    }

    public int getIdCamara() {
        return IdCamara;
    }

    public void setIdCamara(int IdCamara) {
        this.IdCamara = IdCamara;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getSaldoAnterior() {
        return saldoAnterior;
    }

    public void setSaldoAnterior(double saldoAnterior) {
        this.saldoAnterior = saldoAnterior;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
    public void replicar(ProdutoCamara _produtoCamara)
    {
        _produtoCamara.setIdProdutoCamara(this.IdProdutoCamara);
        _produtoCamara.setIdProduto(this.IdProduto);
        _produtoCamara.setIdCamara(this.IdCamara);
        _produtoCamara.setData(this.data);
        _produtoCamara.setSaldo(this.saldo);
        _produtoCamara.setSaldoAnterior(this.saldoAnterior);
    }
}
