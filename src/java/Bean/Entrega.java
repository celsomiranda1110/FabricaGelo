/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
/**
 *
 * @author celso
 */
public class Entrega implements Bean{
    private  int idEntrega;
    private int idMovimento;
    private int idVeiculo;
    private int idProfissional;
    private Date data;
    private String dataFormatada;
    private double kmInicial;
    private double kmFinal;
    private String hrSaida;
    private String hrChegada;
    private double litros;
    private String situacao; // EN - Entregue | PE - Pendente

    private Profissional profissional;
    private Veiculo veiculo;
    
    private List<CustoEntrega> lstCustoEntrega;

    public Entrega() {
        this.situacao = "PE"; // Iniciando objeto como Pendente
        // PE - pendente | EN - Entregue
    }
    
    
    
    public int getIdEntrega() {
        return idEntrega;
    }

    public void setIdEntrega(int idEntrega) {
        this.idEntrega = idEntrega;
    }

    public int getIdMovimento() {
        return idMovimento;
    }

    public void setIdMovimento(int idMovimento) {
        this.idMovimento = idMovimento;
    }

    public int getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public int getIdProfissional() {
        return idProfissional;
    }

    public void setIdProfissional(int idProfissional) {
        this.idProfissional = idProfissional;
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

    public double getKmInicial() {
        return kmInicial;
    }

    public void setKmInicial(double kmInicial) {
        this.kmInicial = kmInicial;
    }

    public double getKmFinal() {
        return kmFinal;
    }

    public void setKmFinal(double kmFinal) {
        this.kmFinal = kmFinal;
    }

    public String getHrSaida() {
        return hrSaida;
    }

    public void setHrSaida(String hrSaida) {
        this.hrSaida = hrSaida;
    }

    public String getHrChegada() {
        return hrChegada;
    }

    public void setHrChegada(String hrChegada) {
        this.hrChegada = hrChegada;
    }

    public double getLitros() {
        return litros;
    }

    public void setLitros(double litros) {
        this.litros = litros;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
        this.idProfissional = profissional.getIdProfissional();
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
        this.idVeiculo = veiculo.getIdVeiculo();
    }

    public List<CustoEntrega> getLstCustoEntrega() {
        return lstCustoEntrega;
    }

    public void setLstCustoEntrega(List<CustoEntrega> lstCustoEntrega) {
        this.lstCustoEntrega = lstCustoEntrega;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
    
    

    public void replicar(Entrega _entrega)
    {
        _entrega.setIdEntrega(this.idEntrega);
        _entrega.setIdMovimento(this.idMovimento);
        _entrega.setIdVeiculo(this.idVeiculo);
        _entrega.setIdProfissional(this.idProfissional);
        _entrega.setData(this.data);
        _entrega.setKmInicial(this.kmInicial);
        _entrega.setKmFinal(this.kmFinal);
        _entrega.setHrSaida(this.hrSaida);
        _entrega.setHrChegada(this.hrChegada);
        _entrega.setLitros(this.litros);
        _entrega.setSituacao(this.situacao);
        
        _entrega.setProfissional(this.profissional);
        _entrega.setVeiculo(this.veiculo);
        
        _entrega.setLstCustoEntrega(this.lstCustoEntrega);
        
    }
}
