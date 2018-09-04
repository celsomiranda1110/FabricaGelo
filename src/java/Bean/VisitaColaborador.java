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
public class VisitaColaborador implements Bean{
    private int IdVisitaColaborador;
    private int IdColaborador;
    private int dia;
    private String ativo;
    private String imagemAtiva;

    public VisitaColaborador() {
        IdColaborador = 0;
    }

    
    
    public int getIdVisitaColaborador() {
        return IdVisitaColaborador;
    }

    public void setIdVisitaColaborador(int IdVisitaColaborador) {
        this.IdVisitaColaborador = IdVisitaColaborador;
    }

    public int getIdColaborador() {
        return IdColaborador;
    }

    public void setIdColaborador(int IdColaborador) {
        this.IdColaborador = IdColaborador;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        if (ativo.equals("A"))
            this.imagemAtiva = "visao/css/bootstrap/img/Yes.png";
        else
            this.imagemAtiva = "visao/css/bootstrap/img/Red_mark.png";
        this.ativo = ativo;
    }

    public String getImagemAtiva() {
        return imagemAtiva;
    }
    
    
    
    public void replicar(VisitaColaborador _visitaColaborador)
    {
        _visitaColaborador.setIdVisitaColaborador(this.IdVisitaColaborador);
        _visitaColaborador.setIdColaborador(this.IdColaborador);
        _visitaColaborador.setDia(this.dia);
        _visitaColaborador.setAtivo(this.ativo);
    }
}
