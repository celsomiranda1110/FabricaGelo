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
public class MovimentoEntrega implements Bean{
    
    private int idMovimentoEntrega;
    private int idColaboradorEntrega;
    private int idMovimento;
    
    
    private Movimento movimento;
    

    public int getIdMovimentoEntrega() {
        return idMovimentoEntrega;
    }

    public void setIdMovimentoEntrega(int idMovimentoEntrega) {
        this.idMovimentoEntrega = idMovimentoEntrega;
    }

    public int getIdColaboradorEntrega() {
        return idColaboradorEntrega;
    }

    public void setIdColaboradorEntrega(int idColaboradorEntrega) {
        this.idColaboradorEntrega = idColaboradorEntrega;
    }

    public int getIdMovimento() {
        return idMovimento;
    }

    public void setIdMovimento(int idMovimento) {
        this.idMovimento = idMovimento;
    }

    public Movimento getMovimento() {
        return movimento;
    }

    public void setMovimento(Movimento movimento) {
        this.movimento = movimento;
        this.idMovimento = movimento.getIdMovimento();
    }
    
    
    public void replicar(MovimentoEntrega _movimentoEntrega)
    {
        _movimentoEntrega.setIdMovimentoEntrega(this.idMovimentoEntrega);
        _movimentoEntrega.setIdColaboradorEntrega(this.idColaboradorEntrega);
        _movimentoEntrega.setIdMovimento(this.idMovimento);
        
        if (this.movimento != null)
            _movimentoEntrega.setMovimento(movimento);
    }
    
}
