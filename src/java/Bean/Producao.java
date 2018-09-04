/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author celso
 */
public class Producao implements Bean{
    private int IdProducao;
    private int IdProduto;
    private int idMaquina;
    private String turno;
    private Date data;
    private String dataFormatada;
    private double quantidade;
    private double quantidadeAnterior;
    private double qtAvaria;
    private double sobra;
    private String horaInicial;
    private String horaFinal;
    private double rendimento; 
    
    private Produto produto;
    private Maquina maquina;
    List<AvariaProducao> lstAvariaProducao = new ArrayList<AvariaProducao>();

    
    
   
    
    String formato = "dd/MM/yyyy";
    
    public int getIdProducao() {
        return IdProducao;
    }

    public void setIdProducao(int IdProducao) {
        this.IdProducao = IdProducao;
    }

    public int getIdProduto() {
        return IdProduto;
    }

    public void setIdProduto(int IdProduto) {
        this.IdProduto = IdProduto;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
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

    

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public double getQuantidadeAnterior() {
        return quantidadeAnterior;
    }

    public void setQuantidadeAnterior(double quantidadeAnterior) {
        this.quantidadeAnterior = quantidadeAnterior;
    }

    public Produto getProduto() {
        return produto;
    }

    public double getQtAvaria() {
        return qtAvaria;
    }

    public void setQtAvaria(double qtAvaria) {
        this.qtAvaria = qtAvaria;
    }

    public double getSobra() {
        return sobra;
    }

    public void setSobra(double sobra) {
        this.sobra = sobra;
    }

    
    
    public void setProduto(Produto produto) {
        this.produto = produto;
        this.IdProduto = produto.getIdProduto();
    }

    public String getHoraInicial() {
        return horaInicial;
    }

    public void setHoraInicial(String horaInicial) {
        this.horaInicial = horaInicial;
    }

    public String getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;
    }

    public double getRendimento() {
        return rendimento;
    }

    public void setRendimento(double rendimento) {
        this.rendimento = rendimento;
    }

    public Maquina getMaquina() {
        return maquina;
    }

    public void setMaquina(Maquina maquina) {
        this.maquina = maquina;
        this.idMaquina = maquina.getIdMaquina();
    }

    public List<AvariaProducao> getLstAvariaProducao() {
        return lstAvariaProducao;
    }

    public void setLstAvariaProducao(List<AvariaProducao> lstAvariaProducao) {
        this.lstAvariaProducao = lstAvariaProducao;
    }

    public int getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(int idMaquina) {
        this.idMaquina = idMaquina;
    }

    
    
    
    
    public void replicar(Producao _producao)
    {
        _producao.setIdProducao(this.IdProducao);
        _producao.setIdProduto(this.IdProduto);
        _producao.setTurno(this.turno);
        _producao.setData(this.data);
        _producao.setQuantidade(this.quantidade);
        _producao.setQuantidadeAnterior(this.quantidadeAnterior);
        _producao.setQtAvaria(qtAvaria);
        _producao.setSobra(sobra);
        _producao.setHoraInicial(this.horaInicial);
        _producao.setHoraFinal(this.horaFinal);
        _producao.setRendimento(this.rendimento);
        
        _producao.setProduto(produto);
        _producao.setMaquina(this.maquina);
        
        _producao.setLstAvariaProducao(this.lstAvariaProducao);
        
    }
}
