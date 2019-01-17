/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.util.List;

/**
 *
 * @author celso
 */
public class ColaboradorEntrega implements Bean{
    
    private int idColaboradorEntrega;
    private int idColaborador;
    private int idEntrega;
    private String motivo;
    private String descMotivo;
    private String situacao;
    private String imagemAtiva;
    
    private Colaborador cliente;
    private List<MovimentoEntrega> lstMovimentoEntrega;

    public ColaboradorEntrega() {
        // SITUAÇÕES: PD = PENDENCIA DESMARCADA | PM = PENDENCIA MARCADA
        this.situacao = "PD";
        this.imagemAtiva = "visao/css/bootstrap/img/Red_mark.png";
    }
    
    

    public int getIdColaboradorEntrega() {
        return idColaboradorEntrega;
    }

    public void setIdColaboradorEntrega(int idColaboradorEntrega) {
        this.idColaboradorEntrega = idColaboradorEntrega;
    }

    public int getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(int idColaborador) {
        this.idColaborador = idColaborador;
    }

    public int getIdEntrega() {
        return idEntrega;
    }

    public void setIdEntrega(int idEntrega) {
        this.idEntrega = idEntrega;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
        if (this.motivo.equals("CI"))
            this.descMotivo = "CLIENTE INADIMPLENTE";
        else if (this.motivo.equals("CH"))
            this.descMotivo = "CLIENTE COM VISITA HOJE";
        else if (this.motivo.equals("CP"))
            this.descMotivo = "CLIENTE COM PAGAMENTO PARA HOJE";        
    }

    public String getDescMotivo() {
        return descMotivo;
    }
    
    

    public Colaborador getCliente() {
        return cliente;
    }

    public void setCliente(Colaborador cliente) {
        this.cliente = cliente;
        this.idColaborador = cliente.getIdColaborador();
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
        if (this.situacao.equals("PM")) // marcado
            this.imagemAtiva = "visao/css/bootstrap/img/Yes.png";
        else
            this.imagemAtiva = "visao/css/bootstrap/img/Red_mark.png";
    }

    public List<MovimentoEntrega> getLstMovimentoEntrega() {
        return lstMovimentoEntrega;
    }

    public void setLstMovimentoEntrega(List<MovimentoEntrega> lstMovimentoEntrega) {
        this.lstMovimentoEntrega = lstMovimentoEntrega;
    }

   
    
    
    
    
    
    public String getImagemAtiva() {
        return imagemAtiva;
    }    
    
    public void replicar(ColaboradorEntrega _movimentoEntrega)
    {
        _movimentoEntrega.setIdColaboradorEntrega(this.idColaboradorEntrega);
        _movimentoEntrega.setIdColaborador(this.idColaborador);
        _movimentoEntrega.setIdEntrega(this.idEntrega);
        _movimentoEntrega.setMotivo(this.motivo);
        _movimentoEntrega.setSituacao(this.situacao);
        
        _movimentoEntrega.setLstMovimentoEntrega(this.lstMovimentoEntrega);
        
        if (this.cliente != null)
            _movimentoEntrega.setCliente(this.cliente);
    }
    
}
