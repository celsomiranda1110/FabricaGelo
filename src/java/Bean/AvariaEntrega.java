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
public class AvariaEntrega implements Bean{
    private int idAvariaEntrega;
    private int idAvaria;
    private int idProdutoEntrega;
    private double quantidade;
    
    private Avaria avaria;

    public int getIdAvariaEntrega() {
        return idAvariaEntrega;
    }

    public void setIdAvariaEntrega(int idAvariaEntrega) {
        this.idAvariaEntrega = idAvariaEntrega;
    }

    public int getIdAvaria() {
        return idAvaria;
    }

    public void setIdAvaria(int idAvaria) {
        this.idAvaria = idAvaria;
    }

    public int getIdProdutoEntrega() {
        return idProdutoEntrega;
    }

    public void setIdProdutoEntrega(int idProdutoEntrega) {
        this.idProdutoEntrega = idProdutoEntrega;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public Avaria getAvaria() {
        return avaria;
    }

    public void setAvaria(Avaria avaria) {
        this.avaria = avaria;
        this.idAvaria = avaria.getIdAvaria();
    }
    
    
    
    public void replicar(AvariaEntrega _avariaEntrega)
    {
        _avariaEntrega.setIdAvariaEntrega(this.idAvariaEntrega);
        _avariaEntrega.setIdAvaria(this.idAvaria);
        _avariaEntrega.setIdProdutoEntrega(this.idProdutoEntrega);
        _avariaEntrega.setQuantidade(this.quantidade);
        
        if (this.avaria != null)
            _avariaEntrega.setAvaria(this.avaria);
    }
    
}
