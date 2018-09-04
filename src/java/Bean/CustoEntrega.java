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
public class CustoEntrega implements Bean{
    private int idCustoEntrega;
    private int idEntrega;
    private String descricao;
    private double valor;

    public int getIdCustoEntrega() {
        return idCustoEntrega;
    }

    public void setIdCustoEntrega(int idCustoEntrega) {
        this.idCustoEntrega = idCustoEntrega;
    }

    public int getIdEntrega() {
        return idEntrega;
    }

    public void setIdEntrega(int idEntrega) {
        this.idEntrega = idEntrega;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void replicar(CustoEntrega _custoEntrega)
    {
        _custoEntrega.setIdCustoEntrega(this.idCustoEntrega);
        _custoEntrega.setIdEntrega(this.idEntrega);
        _custoEntrega.setDescricao(this.descricao);
        _custoEntrega.setValor(this.valor);
    }
}
