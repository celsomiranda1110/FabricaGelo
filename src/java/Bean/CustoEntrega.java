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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustoEntrega  implements Bean{
    
    private int idCustoEntrega;
    private int idEntrega;
    private int idColaborador;
    private String descricao;
    private String numero;
    private double vlUnitario;
    private double quantidade;
    private double vlTotal;
    
    private Colaborador colaborador;

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



    public double getVlUnitario() {
        return vlUnitario;
    }

    public void setVlUnitario(double vlUnitario) {
        this.vlUnitario = vlUnitario;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public int getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(int idColaborador) {
        this.idColaborador = idColaborador;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
        this.idColaborador = colaborador.getIdColaborador();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public double getVlTotal() {
        return vlTotal;
    }

    public void setVlTotal(double vlTotal) {
        this.vlTotal = vlTotal;
    }
    
    
    
    public void replicar(CustoEntrega _custoEntrega)
    {
        _custoEntrega.setIdCustoEntrega(this.idCustoEntrega);
        _custoEntrega.setIdEntrega(this.idEntrega);
        _custoEntrega.setIdColaborador(this.idColaborador);
        _custoEntrega.setDescricao(this.descricao);
        _custoEntrega.setNumero(this.numero);
        _custoEntrega.setVlUnitario(this.vlUnitario);
        _custoEntrega.setQuantidade(this.quantidade);
        _custoEntrega.setVlTotal(this.vlTotal);
        
        if (this.colaborador != null)
            _custoEntrega.setColaborador(this.colaborador);
    }
    
}
