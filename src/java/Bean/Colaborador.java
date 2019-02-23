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

import java.util.List;
import java.util.ArrayList;

public class Colaborador implements Bean{
    private int idColaborador;
    private int idTipoColaborador;
    private String cnpj;
    private String  inscricaoEstadual;
    private String  inscricaoMunicipal;
    private String  numMei;
    private String nome;
    private String razaoSocial;
    private String endereco;
    private String complemento;
    private int idBairro;
    private String cpf;
    private String contato;
    private String fone;
    private String email;
    private String ativo;
    private String descAtivo;
    private String observacao;
    
    private TipoColaborador tipoColaborador;
    
    private Bairro bairro;
    private List<ColaboradorProduto> lstColaboradorProduto = new ArrayList<ColaboradorProduto>();
    private List<VisitaColaborador> lstVisitaColaborador = new ArrayList<VisitaColaborador>();

    public Colaborador() {
        this.ativo = "A";
    }
    
    

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
        this.idBairro = bairro.getIdBairro();
    }

    public int getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(int idColaborador) {
        this.idColaborador = idColaborador;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getInscricaoMunicipal() {
        return inscricaoMunicipal;
    }

    public void setInscricaoMunicipal(String inscricaoMunicipal) {
        this.inscricaoMunicipal = inscricaoMunicipal;
    }

    public String getNumMei() {
        return numMei;
    }

    public void setNumMei(String numMei) {
        this.numMei = numMei;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public int getIdBairro() {
        return idBairro;
    }

    public void setIdBairro(int idBairro) {
        this.idBairro = idBairro;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ColaboradorProduto> getLstColaboradorProduto() {
        return lstColaboradorProduto;
    }

    public void setLstColaboradorProduto(List<ColaboradorProduto> lstColaboradorProduto) {
        this.lstColaboradorProduto = lstColaboradorProduto;
    }

    public List<VisitaColaborador> getLstVisitaColaborador() {
        return lstVisitaColaborador;
    }

    public void setLstVisitaColaborador(List<VisitaColaborador> lstVisitaColaborador) {
        this.lstVisitaColaborador = lstVisitaColaborador;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
        if (ativo.equals("I"))
            this.descAtivo = "checked";
        else
            this.descAtivo = "";
    }

    public int getIdTipoColaborador() {
        return idTipoColaborador;
    }

    public void setIdTipoColaborador(int idTipoColaborador) {
        this.idTipoColaborador = idTipoColaborador;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getDescAtivo() {
        return descAtivo;
    }

    public TipoColaborador getTipoColaborador() {
        return tipoColaborador;
    }

    public void setTipoColaborador(TipoColaborador tipoColaborador) {
        this.tipoColaborador = tipoColaborador;
        this.idTipoColaborador = tipoColaborador.getIdTipoColaborador();
    }
    
    

    public String toString()
    {
        return this.razaoSocial;
    }
    
    public void replicar(Colaborador _colaborador)
    {
    
        _colaborador.setIdColaborador(this.idColaborador);
        _colaborador.setIdTipoColaborador(this.idTipoColaborador);
        _colaborador.setCnpj(this.cnpj);
        _colaborador.setInscricaoEstadual(this.inscricaoEstadual);
        _colaborador.setInscricaoMunicipal(this.inscricaoMunicipal);
        _colaborador.setNumMei(this.numMei);
        _colaborador.setNome(this.nome);
        _colaborador.setRazaoSocial(this.razaoSocial);
        _colaborador.setEndereco(this.endereco);
        _colaborador.setComplemento(this.complemento);
        _colaborador.setIdBairro(this.idBairro);
        _colaborador.setCpf(this.cpf);
        _colaborador.setContato(this.contato);
        _colaborador.setFone(this.fone);
        _colaborador.setEmail(this.email);
        _colaborador.setAtivo(this.ativo);
        _colaborador.setObservacao(this.observacao);
        
        if (this.bairro != null)
            _colaborador.setBairro(this.bairro);
        
        if (this.tipoColaborador != null)
            _colaborador.setTipoColaborador(this.tipoColaborador);
        
        _colaborador.setLstVisitaColaborador(this.lstVisitaColaborador);
        _colaborador.setLstColaboradorProduto(this.lstColaboradorProduto);
        
    }
}
