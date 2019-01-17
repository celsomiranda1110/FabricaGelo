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
public class TransferenciaProducao implements Bean{
    private int IdTransferenciaProducao;
    private int IdProducao;
    private int IdProdutoCamara;
    private double quantidade;
    private Date data;
    private String dataFormatada;
    
    private ProdutoCamara produtoCamara;

    public int getIdTransferenciaProducao() {
        return IdTransferenciaProducao;
    }

    public void setIdTransferenciaProducao(int IdTransferenciaProducao) {
        this.IdTransferenciaProducao = IdTransferenciaProducao;
    }

    public int getIdProducao() {
        return IdProducao;
    }

    public void setIdProducao(int IdProducao) {
        this.IdProducao = IdProducao;
    }

    public int getIdProdutoCamara() {
        return IdProdutoCamara;
    }

    public void setIdProdutoCamara(int IdProdutoCamara) {
        this.IdProdutoCamara = IdProdutoCamara;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
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

    public ProdutoCamara getProdutoCamara() {
        return produtoCamara;
    }

    public void setProdutoCamara(ProdutoCamara produtoCamara) {
        this.produtoCamara = produtoCamara;
        this.IdProdutoCamara = produtoCamara.getIdProdutoCamara();
    }
    
    

    public void replicar(TransferenciaProducao _transferenciaProducao)
    {
        _transferenciaProducao.setIdTransferenciaProducao(this.IdTransferenciaProducao);
        _transferenciaProducao.setIdProducao(this.IdProducao);
        _transferenciaProducao.setIdProdutoCamara(this.IdProdutoCamara);
        _transferenciaProducao.setQuantidade(this.quantidade);
        _transferenciaProducao.setData(this.data);
        
        if (this.produtoCamara != null)
            _transferenciaProducao.setProdutoCamara(this.produtoCamara);
    }
}
