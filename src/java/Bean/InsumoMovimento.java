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
public class InsumoMovimento implements Bean{
    private int IdInsumoMovimento;
    private int idMovimento;
    private int idInsumo;
    private double quantidade;

    public int getIdInsumoMovimento() {
        return IdInsumoMovimento;
    }

    public void setIdInsumoMovimento(int IdInsumoMovimento) {
        this.IdInsumoMovimento = IdInsumoMovimento;
    }

    public int getIdMovimento() {
        return idMovimento;
    }

    public void setIdMovimento(int idMovimento) {
        this.idMovimento = idMovimento;
    }

    public int getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(int idInsumo) {
        this.idInsumo = idInsumo;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public void replicar(InsumoMovimento _insumoMovimento)
    {
        _insumoMovimento.setIdInsumoMovimento(this.IdInsumoMovimento);
        _insumoMovimento.setIdInsumo(this.idInsumo);
        _insumoMovimento.setIdMovimento(this.idMovimento);
        _insumoMovimento.setQuantidade(this.quantidade);
    }
}
