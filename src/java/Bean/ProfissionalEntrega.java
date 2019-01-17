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
public class ProfissionalEntrega  implements Bean{
    
    private int idProfissionalEntrega;
    private int idProfissional;
    private int idEntrega;
    private String responsavel;
    
    private Profissional profissional;
    private Entrega entrega;

    public int getIdProfissionalEntrega() {
        return idProfissionalEntrega;
    }

    public void setIdProfissionalEntrega(int idProfissionalEntrega) {
        this.idProfissionalEntrega = idProfissionalEntrega;
    }

    public int getIdProfissional() {
        return idProfissional;
    }

    public void setIdProfissional(int idProfissional) {
        this.idProfissional = idProfissional;
    }

    public int getIdEntrega() {
        return idEntrega;
    }

    public void setIdEntrega(int idEntrega) {
        this.idEntrega = idEntrega;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
        
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
        this.idProfissional = profissional.getIdProfissional();
    }

    public Entrega getEntrega() {
        return entrega;
    }

    public void setEntrega(Entrega entrega) {
        this.entrega = entrega;
        this.idEntrega = entrega.getIdEntrega();
    }
    
    public void replicar(ProfissionalEntrega _profissionalEntrega)
    {
        _profissionalEntrega.setIdProfissionalEntrega(this.idProfissionalEntrega);
        _profissionalEntrega.setIdEntrega(this.idEntrega);
        _profissionalEntrega.setIdProfissional(this.idProfissional);
        _profissionalEntrega.setResponsavel(this.responsavel);
        
        if (this.profissional != null)
            _profissionalEntrega.setProfissional(this.profissional);
        
        if (this.entrega != null)
            _profissionalEntrega.setEntrega(this.entrega);
    }
    
}
