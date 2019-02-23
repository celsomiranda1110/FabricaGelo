/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.util.List;

/**
 *
 * @author celso
 */
public class Profissional implements Bean{
    private int IdProfissional;
    private int IdFuncao;
    private String ctps;
    private String cpf;
    private String nome;
    private String celular;
    private String senha;
    private String usuario;
    private String ativo;
    private String descAtivo;
    
    private Funcao funcao;
    private List<MenuProfissional> lstMenuProfissional;

    public Profissional() {
        this.ativo = "A";
    }

    
    
    public int getIdProfissional() {
        return IdProfissional;
    }

    public void setIdProfissional(int IdProfissional) {
        this.IdProfissional = IdProfissional;
    }

    public int getIdFuncao() {
        return IdFuncao;
    }

    public void setIdFuncao(int IdFuncao) {
        this.IdFuncao = IdFuncao;
    }



    
    
    public String getCtps() {
        return ctps;
    }

    public void setCtps(String ctps) {
        this.ctps = ctps;
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

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Funcao getFuncao() {
        return funcao;
    }

    public void setFuncao(Funcao funcao) {
        this.funcao = funcao;
        this.IdFuncao = funcao.getIdFuncao();
    }

    public List<MenuProfissional> getLstMenuProfissional() {
        return lstMenuProfissional;
    }

    public void setLstMenuProfissional(List<MenuProfissional> lstMenuProfissional) {
        this.lstMenuProfissional = lstMenuProfissional;
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

    public String getDescAtivo() {
        return descAtivo;
    }    
    
    public String toString()
    {
        return this.nome + " - " + this.funcao;
    }
    
    public void replicar(Profissional _profissional)
    {
        _profissional.setIdProfissional(this.IdProfissional);
        _profissional.setIdFuncao(this.IdFuncao);
        _profissional.setAtivo(this.ativo);
        _profissional.setCtps(this.ctps);
        _profissional.setCpf(this.cpf);
        _profissional.setNome(this.nome);
        _profissional.setCelular(this.celular);
        _profissional.setSenha(this.senha);
        _profissional.setUsuario(this.usuario);
        
        if (this.lstMenuProfissional != null)
            _profissional.setLstMenuProfissional(this.lstMenuProfissional);
    
    }
}
