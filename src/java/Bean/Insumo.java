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
public class Insumo implements Bean{
    private int idInsumo;
    private String descricao;
    private double saldo;

    public int getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(int idInsumo) {
        this.idInsumo = idInsumo;
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

    public void replicar(Insumo _insumo)
    {
        _insumo.setIdInsumo(this.idInsumo);
        _insumo.setDescricao(this.descricao);
        _insumo.setSaldo(this.saldo);
    }
}
