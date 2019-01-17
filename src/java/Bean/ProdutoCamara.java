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
public class ProdutoCamara implements Bean{
    private int IdProdutoCamara;
    private int IdProduto;
    private int idEquipamento;
    private Date data;
    private String dataFormatada;
    private double saldoAnterior;
    private double saldo;
    
    private Equipamento equipamento;
    private Produto produto;
    

    public int getIdProdutoCamara() {
        return IdProdutoCamara;
    }

    public void setIdProdutoCamara(int IdProdutoCamara) {
        this.IdProdutoCamara = IdProdutoCamara;
    }

    public int getIdProduto() {
        return IdProduto;
    }

    public void setIdProduto(int IdProduto) {
        this.IdProduto = IdProduto;
    }

    public int getIdEquipamento() {
        return idEquipamento;
    }

    public void setIdEquipamento(int idEquipamento) {
        this.idEquipamento = idEquipamento;
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
    
    public double getSaldoAnterior() {
        return saldoAnterior;
    }

    public void setSaldoAnterior(double saldoAnterior) {
        this.saldoAnterior = saldoAnterior;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
        this.idEquipamento = equipamento.getIdEquipamento();
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
        this.IdProduto = produto.getIdProduto();
    }
    
    public String toString()
    {
        return this.produto + " - " + this.equipamento;
    }
    
    public void replicar(ProdutoCamara _produtoCamara)
    {
        _produtoCamara.setIdProdutoCamara(this.IdProdutoCamara);
        _produtoCamara.setIdProduto(this.IdProduto);
        _produtoCamara.setIdEquipamento(this.idEquipamento);
        _produtoCamara.setData(this.data);
        _produtoCamara.setSaldo(this.saldo);
        _produtoCamara.setSaldoAnterior(this.saldoAnterior);
        
        if (this.equipamento != null)
            _produtoCamara.setEquipamento(this.equipamento);
        
        if (this.produto != null)
            _produtoCamara.setProduto(this.produto);
    }
}
