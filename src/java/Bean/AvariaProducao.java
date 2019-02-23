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
public class AvariaProducao implements Bean{
    
    private int idAvariaProducao;
    private int idAvaria;
    private int idMaquinaProducao;
    private double quantidade;

    private Avaria avaria;
    
    public int getIdAvariaProducao() {
        return idAvariaProducao;
    }

    public void setIdAvariaProducao(int idAvariaProducao) {
        this.idAvariaProducao = idAvariaProducao;
    }

    public int getIdAvaria() {
        return idAvaria;
    }

    public void setIdAvaria(int idAvaria) {
        this.idAvaria = idAvaria;
    }

    public int getIdMaquinaProducao() {
        return idMaquinaProducao;
    }

    public void setIdMaquinaProducao(int idMaquinaProducao) {
        this.idMaquinaProducao = idMaquinaProducao;
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
    
    public String toString()
    {
        return this.avaria.toString();
    }
    
    public void replicar(AvariaProducao _avariaProducao)
    {
        _avariaProducao.setIdAvariaProducao(this.idAvariaProducao);
        _avariaProducao.setIdAvaria(this.idAvaria);
        _avariaProducao.setIdMaquinaProducao(this.idMaquinaProducao);
        _avariaProducao.setQuantidade(this.quantidade);
        
        if (this.avaria != null)
            _avariaProducao.setAvaria(this.avaria);
    }
    
}
