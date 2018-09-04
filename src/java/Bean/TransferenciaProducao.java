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
public class TransferenciaProducao implements Bean{
    private int IdTransferenciaProducao;
    private int IdProducao;
    private int IdProdutoCamara;
    private double quantidade;

    public int getIdTransferenciaProducao() {
        return IdTransferenciaProducao;
    }

    public void setIdTransferenciaProducao(int IdTransferenciaProducao) {
        this.IdTransferenciaProducao = IdTransferenciaProducao;
    }

    public int getIdProducao() {
        return IdProducao;
    }

    public void setIdProducao(int IdProducao) {
        this.IdProducao = IdProducao;
    }

    public int getIdProdutoCamara() {
        return IdProdutoCamara;
    }

    public void setIdProdutoCamara(int IdProdutoCamara) {
        this.IdProdutoCamara = IdProdutoCamara;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public void replicar(TransferenciaProducao _transferenciaProducao)
    {
        _transferenciaProducao.setIdTransferenciaProducao(this.IdTransferenciaProducao);
        _transferenciaProducao.setIdProducao(this.IdProducao);
        _transferenciaProducao.setIdProdutoCamara(this.IdProdutoCamara);
        _transferenciaProducao.setQuantidade(this.quantidade);
    }
}
