/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
/**
 *
 * @author celso
 */
public class Entrega implements Bean{

    
    
    private  int idEntrega;
    private int idEquipamento;
    private Date data;
    private String dataFormatada;
    private String dataApresentacao;
    private double kmInicial;
    private double kmFinal;
    private String hrSaida;
    private String hrChegada;
    private String situacao; // AB - ABERTA | FE - FECHADA | PE - PENDENTE
    private String descSituacao;

    private Equipamento veiculo;
    
    private List<CustoEntrega> lstCustoEntrega;
    private List<ProdutoEntrega> lstProdutoEntrega;
    private List<Abastecimento> lstAbastecimento;
    private List<ColaboradorEntrega> lstColaboradorEntrega;
    private List<ProfissionalEntrega> lstProfissionalEntrega;
    
    public Entrega() {
        this.situacao = "CD"; // Iniciando objeto como Pendente
        this.idEquipamento = 0;
        this.hrSaida = "";
        this.hrChegada = "";
        
        
        Calendar cal = new GregorianCalendar();
        this.data = cal.getTime();
           
    }

    public int getIdEquipamento() {
        return idEquipamento;
    }

    public void setIdEquipamento(int idEquipamento) {
        this.idEquipamento = idEquipamento;
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

    public String getDataApresentacao() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        this.data = data;
        this.dataApresentacao = formatter.format(this.data);
        
        return dataApresentacao;
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

    public Equipamento getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Equipamento veiculo) {
        this.veiculo = veiculo;
        this.idEquipamento = veiculo.getIdEquipamento();
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
        if (this.situacao.equals("ET"))
            this.descSituacao = "EM TRANSITO";
        else if (this.situacao.equals("CH"))
            this.descSituacao = "CHEGADA";
        else if (this.situacao.equals("CD"))
            this.descSituacao = "CADASTRADA";
        else if (this.situacao.equals("FI"))
            this.descSituacao = "FINALIZADA";
    }

    public String getDescSituacao() {
        return descSituacao;
    }

    
    
    
    public List<ProdutoEntrega> getLstProdutoEntrega() {
        return lstProdutoEntrega;
    }

    public void setLstProdutoEntrega(List<ProdutoEntrega> lstProdutoEntrega) {
        this.lstProdutoEntrega = lstProdutoEntrega;
    }

    public List<Abastecimento> getLstAbastecimento() {
        return lstAbastecimento;
    }

    public void setLstAbastecimento(List<Abastecimento> lstAbastecimento) {
        this.lstAbastecimento = lstAbastecimento;
    }

    public List<ColaboradorEntrega> getLstColaboradorEntrega() {
        return lstColaboradorEntrega;
    }

    public void setLstColaboradorEntrega(List<ColaboradorEntrega> lstColaboradorEntrega) {
        this.lstColaboradorEntrega = lstColaboradorEntrega;
    }

    public List<ProfissionalEntrega> getLstProfissionalEntrega() {
        return lstProfissionalEntrega;
    }

    public void setLstProfissionalEntrega(List<ProfissionalEntrega> lstProfissionalEntrega) {
        this.lstProfissionalEntrega = lstProfissionalEntrega;
    }
    
    

    public void replicar(Entrega _entrega)
    {
        _entrega.setIdEntrega(this.idEntrega);
        _entrega.setIdEquipamento(this.idEquipamento);
        _entrega.setData(this.data);
        _entrega.setKmInicial(this.kmInicial);
        _entrega.setKmFinal(this.kmFinal);
        _entrega.setHrSaida(this.hrSaida);
        _entrega.setHrChegada(this.hrChegada);
        _entrega.setSituacao(this.situacao);
        
        _entrega.setVeiculo(this.veiculo);
        
        _entrega.setLstCustoEntrega(this.lstCustoEntrega);
        _entrega.setLstProdutoEntrega(lstProdutoEntrega);
        _entrega.setLstAbastecimento(this.lstAbastecimento);
        _entrega.setLstColaboradorEntrega(this.lstColaboradorEntrega);
        _entrega.setLstProfissionalEntrega(this.lstProfissionalEntrega);
        
    }
}
