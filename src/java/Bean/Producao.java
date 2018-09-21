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
    private String turno;
    private Date data;
    private String dataFormatada;
    private double quantidade;
    
    private Produto produto;
    
    List<MaquinaProducao> lstMaquinaProducao = new ArrayList<MaquinaProducao>();
   
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
    

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
        this.IdProduto = produto.getIdProduto();
    }

    public List<MaquinaProducao> getLstMaquinaProducao() {
        return lstMaquinaProducao;
    }

    public void setLstMaquinaProducao(List<MaquinaProducao> lstMaquinaProducao) {
        this.lstMaquinaProducao = lstMaquinaProducao;
    }


    public void replicar(Producao _producao)
    {
        _producao.setIdProducao(this.IdProducao);
        _producao.setIdProduto(this.IdProduto);
        _producao.setTurno(this.turno);
        _producao.setData(this.data);
        _producao.setProduto(this.produto);
        _producao.setQuantidade(this.quantidade);

        
        _producao.setLstMaquinaProducao(this.lstMaquinaProducao);
        
    }
}
