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
public class ProdutoInsumo implements Bean{
    private int IdProdutoInsumo;
    private int IdProduto;
    private int IdInsumo;
    private double quantidade;

    public int getIdProdutoInsumo() {
        return IdProdutoInsumo;
    }

    public void setIdProdutoInsumo(int IdProdutoInsumo) {
        this.IdProdutoInsumo = IdProdutoInsumo;
    }

    public int getIdProduto() {
        return IdProduto;
    }

    public void setIdProduto(int IdProduto) {
        this.IdProduto = IdProduto;
    }

    public int getIdInsumo() {
        return IdInsumo;
    }

    public void setIdInsumo(int IdInsumo) {
        this.IdInsumo = IdInsumo;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }
    
    public void replicar(ProdutoInsumo _produtoInsumo)
    {
        _produtoInsumo.setIdProdutoInsumo(this.IdProdutoInsumo);
        _produtoInsumo.setIdProduto(this.IdProduto);
        _produtoInsumo.setIdInsumo(this.IdInsumo);
        _produtoInsumo.setQuantidade(this.quantidade);
    }
    
}
