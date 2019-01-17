/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Colaborador;

import Bean.Colaborador;
import Bean.Produto;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.ProdutoDAO;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoBuscarProduto extends Acao
{
    
public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);


        
        // Dados do colaborador
        String cnpj = (req.getParameter("txtCnpj").equals("") || req.getParameter("txtCnpj") == null) ? "" : req.getParameter("txtCnpj");
        String inscricaoEstadual = (req.getParameter("txtInscricaoEstadual").equals("") || req.getParameter("txtInscricaoEstadual") == null) ? "" : req.getParameter("txtInscricaoEstadual");        
        String inscricaoMunicipal = (req.getParameter("txtInscricaoMunicipal").equals("") || req.getParameter("txtInscricaoMunicipal") == null) ? "" : req.getParameter("txtInscricaoMunicipal");        
        String numMei = (req.getParameter("txtNumMei").equals("") || req.getParameter("txtNumMei") == null) ? "" : req.getParameter("txtNumMei");        
        String nomeFantasia = (req.getParameter("txtNomeFantasia").equals("") || req.getParameter("txtNomeFantasia") == null) ? "" : req.getParameter("txtNomeFantasia").toUpperCase();        
        String razaoSocial = (req.getParameter("txtRazaoSocial").equals("") || req.getParameter("txtRazaoSocial") == null) ? "" : req.getParameter("txtRazaoSocial").toUpperCase();        
        String endereco = (req.getParameter("txtEndereco").equals("") || req.getParameter("txtEndereco") == null) ? "" : req.getParameter("txtEndereco").toUpperCase();        
        String cpf = (req.getParameter("txtCpf").equals("") || req.getParameter("txtCpf") == null) ? "" : req.getParameter("txtCpf");        
        String contato = (req.getParameter("txtContato").equals("") || req.getParameter("txtContato") == null) ? "" : req.getParameter("txtContato").toUpperCase();        
        String fone = (req.getParameter("txtFone").equals("") || req.getParameter("txtFone") == null) ? "" : req.getParameter("txtFone").toUpperCase();        
        String email = (req.getParameter("txtEmail").equals("") || req.getParameter("txtEmail") == null) ? "" : req.getParameter("txtEmail");        

        Colaborador colaborador = (Colaborador)sessao.getAttribute("colaborador");
        if (colaborador == null)
            colaborador = new Colaborador();
        
        colaborador.setCnpj(cnpj);
        colaborador.setInscricaoEstadual(inscricaoEstadual);
        colaborador.setInscricaoMunicipal(inscricaoMunicipal);
        colaborador.setNumMei(numMei);
        colaborador.setRazaoSocial(razaoSocial);
        colaborador.setNome(nomeFantasia);
        colaborador.setEndereco(endereco);
        colaborador.setCpf(cpf);
        colaborador.setContato(contato);
        colaborador.setFone(fone);
        colaborador.setEmail(email); 
        
        List<Produto> lstProduto = new ArrayList<Produto>();
        ProdutoDAO produtoDAO = new ProdutoDAO(conexao); 
        Produto produto = new Produto();
        produto.setTipo("PR");
        lstProduto = produtoDAO.listaTodos(produto);
        
        sessao.setAttribute("colaborador",colaborador);
        sessao.setAttribute("lstProduto",lstProduto);
        sessao.setAttribute("pagRetorno","FabricaGelo.Colaborador.AcaoAbreColaborador");
        
        return "visao/listarProduto.jsp"; 
    }
}
