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
public class SaidaCamara implements Bean{
    private int idSaidaCamara;
    private int idProdutoMovimento;
    private int idProdutoCamara;
    private double saida;
    private double devolucao;

    public int getIdSaidaCamara() {
        return idSaidaCamara;
    }

    public void setIdSaidaCamara(int idSaidaCamara) {
        this.idSaidaCamara = idSaidaCamara;
    }

    public int getidProdutoMovimento() {
        return idProdutoMovimento;
    }

    public void setidProdutoMovimento(int idProdutoMovimento) {
        this.idProdutoMovimento = idProdutoMovimento;
    }

    public int getidProdutoCamara() {
        return idProdutoCamara;
    }

    public void setidProdutoCamara(int idProdutoCamara) {
        this.idProdutoCamara = idProdutoCamara;
    }

    public double getSaida() {
        return saida;
    }

    public void setSaida(double saida) {
        this.saida = saida;
    }

    public double getDevolucao() {
        return devolucao;
    }

    public void setDevolucao(double devolucao) {
        this.devolucao = devolucao;
    }

    public void replicar(SaidaCamara _saidaCamara)
    {
        _saidaCamara.setIdSaidaCamara(this.idSaidaCamara);
        _saidaCamara.setidProdutoCamara(this.idProdutoCamara);
        _saidaCamara.setidProdutoMovimento(this.idProdutoMovimento);
        _saidaCamara.setSaida(this.saida);
        _saidaCamara.setDevolucao(this.devolucao);
    }
}
