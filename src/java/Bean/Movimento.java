/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author celsocolaborador
 */
public class Movimento implements Bean{
    private int idMovimento;
    private int idColaborador;
    private int idProfissional;
    private String notaFiscal;
    private Date dtEntrega;
    private String dataEntrega;
    private String numero;
    private String dataLancamento;
    private Date dtLancamento;
    private String tipo;
    private String situacao;
    
    String formato = "dd/MM/yyyy";
    
    private Colaborador colaborador;
    private Profissional profissional;
    
    private Pagamento pagamento;
    private Entrega entrega;

    private List<ProdutoMovimento> lstProdutoMovimento;

    public String getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(String notaFiscal) {
        this.notaFiscal = notaFiscal;
    }
   
    public int getIdMovimento() {
        return idMovimento;
    }

    public void setIdMovimento(int idMovimento) {
        this.idMovimento = idMovimento;
    }

    public int getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(int idColaborador) {
        this.idColaborador = idColaborador;
    }

    public String getDataEntrega() {
        return dataEntrega;
    }
    
    public void setDataEntrega(String dataEntrega) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        this.dataEntrega = dataEntrega;
        try{
            this.dtEntrega = (Date)formatter.parse(dataEntrega);
        }catch(ParseException pe)
        {}        
    }

    public Date getDtEntrega() {
        return dtEntrega;
    }

    public void setDtEntrega(Date dtEntrega) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        this.dtEntrega = dtEntrega;
        this.dataEntrega = formatter.format(this.dtEntrega);
    }
    
    

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDataLancamento() {
        return dataLancamento;
    }

    public Date getDtLancamento() {
        return dtLancamento;
    }

    public void setDtLancamento(Date dtLancamento) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        this.dtLancamento = dtLancamento;
        this.dataLancamento = formatter.format(this.dtLancamento);
    }
    
 
    public void setDataLancamento(String dataLancamento) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        this.dataLancamento = dataLancamento;
        try{
            this.dtLancamento = (Date)formatter.parse(dataLancamento);
        }catch(ParseException pe)
        {}
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    
    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
        this.idColaborador = colaborador.getIdColaborador();
    }

    public int getIdProfissional() {
        return idProfissional;
    }

    public void setIdProfissionalId(int idProfissional) {
        this.idProfissional = idProfissional;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
        this.idProfissional = profissional.getIdProfissional();
    }

    public List<ProdutoMovimento> getLstProdutoMovimento() {
        return lstProdutoMovimento;
    }

    public void setLstProdutoMovimento(List<ProdutoMovimento> lstProdutoMovimento) {
        this.lstProdutoMovimento = lstProdutoMovimento;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public Entrega getEntrega() {
        return entrega;
    }

    public void setEntrega(Entrega entrega) {
        this.entrega = entrega;
    }
    
    
    
    public void replicar(Movimento _movimento)
    {
        _movimento.setIdMovimento(this.idMovimento);
        _movimento.setIdColaborador(this.idColaborador);
        _movimento.setIdProfissionalId(this.idProfissional);
        _movimento.setDataEntrega(this.dataEntrega);
        _movimento.setDtEntrega(this.dtEntrega);
        _movimento.setDataLancamento(this.dataLancamento);
        _movimento.setDtLancamento(this.dtLancamento);
        _movimento.setNumero(this.numero);
        _movimento.setTipo(this.tipo);
        _movimento.setSituacao(this.situacao);
        _movimento.setNotaFiscal(this.notaFiscal);
        
        _movimento.setColaborador(this.colaborador);
        _movimento.setProfissional(this.profissional);
        
        _movimento.setPagamento(this.pagamento);
        _movimento.setEntrega(this.entrega);
        
        _movimento.setLstProdutoMovimento(this.lstProdutoMovimento);
    }
}
