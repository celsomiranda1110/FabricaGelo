/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author celso
 */
public class Manutencao implements Bean{
    private int idManutencao;
    private int idEquipamento;
    private int idColaborador;
    private Date dataInicio;
    private String dataInicioFormatada;
    private Date dataFim;
    private String dataFimFormatada;
    private Date dataGarantia;
    private String dataGarantiaFormatada;
    private String motivo;
    private double valor;
    
    private Colaborador colaborador;
    
    

    public int getIdManutencao() {
        return idManutencao;
    }

    public void setIdManutencao(int idManutencao) {
        this.idManutencao = idManutencao;
    }

    public int getIdEquipamento() {
        return idEquipamento;
    }

    public void setIdEquipamento(int idEquipamento) {
        this.idEquipamento = idEquipamento;
    }

    public int getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(int idColaborador) {
        this.idColaborador = idColaborador;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        this.dataInicio = dataInicio;
        this.dataInicioFormatada = formatter.format(this.dataInicio);
        
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        this.dataFim = dataFim;
        this.dataFimFormatada = formatter.format(this.dataFim);
    }

    public Date getDataGarantia() {
        return dataGarantia;
    }

    public void setDataGarantia(Date dataGarantia) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        this.dataGarantia = dataGarantia;
        this.dataGarantiaFormatada = formatter.format(this.dataGarantia);
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDataInicioFormatada() {
        return dataInicioFormatada;
    }

    public void setDataInicioFormatada(String dataInicioFormatada) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        this.dataInicioFormatada = dataInicioFormatada;
        try{
            this.dataInicio = (Date)formatter.parse(dataInicioFormatada);
        }catch(ParseException pe)
        {}
    }

    public String getDataFimFormatada() {
        return dataFimFormatada;
    }

    public void setDataFimFormatada(String dataFimFormatada) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        this.dataFimFormatada = dataFimFormatada;
        try{
            this.dataFim = (Date)formatter.parse(dataFimFormatada);
        }catch(ParseException pe)
        {}
    }

    public String getDataGarantiaFormatada() {
        return dataGarantiaFormatada;
    }

    public void setDataGarantiaFormatada(String dataGarantiaFormatada) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        this.dataGarantiaFormatada = dataGarantiaFormatada;
        try{
            this.dataGarantia = (Date)formatter.parse(dataGarantiaFormatada);
        }catch(ParseException pe)
        {}
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
        this.idColaborador = colaborador.getIdColaborador();
    }
    
    
    
    public void replicar(Manutencao _manutencao)
    {
        _manutencao.setIdManutencao(this.idManutencao);
        _manutencao.setIdEquipamento(this.idEquipamento);
        _manutencao.setIdColaborador(this.idColaborador);
        _manutencao.setDataInicio(this.dataInicio);
        _manutencao.setDataFim(this.dataFim);
        _manutencao.setDataGarantia(this.dataGarantia);
        _manutencao.setMotivo(this.motivo);
        _manutencao.setValor(this.valor);
        
        _manutencao.setColaborador(this.colaborador);
    }
    
}
