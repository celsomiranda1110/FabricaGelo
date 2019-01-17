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

public class Abastecimento  implements Bean{
    
    private int idAbastecimento;
    private int idEntrega;
    private int idColaborador;
    private Date data;
    private String dataFormatada;
    private double vlUnitario;
    private double quantidade;
    
    private Colaborador colaborador;

    public int getIdAbastecimento() {
        return idAbastecimento;
    }

    public void setIdAbastecimento(int idAbastecimento) {
        this.idAbastecimento = idAbastecimento;
    }

    public int getIdEntrega() {
        return idEntrega;
    }

    public void setIdEntrega(int idEntrega) {
        this.idEntrega = idEntrega;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        this.data = data;
        this.dataFormatada = formatter.format(this.data);
    }

    public String getDataFormatada() {
        return dataFormatada;
    }

    public void setDataFormatada(String dataFormatada) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        this.dataFormatada = dataFormatada;
        try{
            this.data = (Date)formatter.parse(dataFormatada);
        }catch(ParseException pe)
        {}         
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
    
    
    
    public void replicar(Abastecimento _abastecimento)
    {
        _abastecimento.setIdAbastecimento(this.idAbastecimento);
        _abastecimento.setIdEntrega(this.idEntrega);
        _abastecimento.setIdColaborador(this.idColaborador);
        _abastecimento.setData(this.data);
        _abastecimento.setVlUnitario(this.vlUnitario);
        _abastecimento.setQuantidade(this.quantidade);
        
        if (this.colaborador != null)
            _abastecimento.setColaborador(this.colaborador);
    }
    
}
