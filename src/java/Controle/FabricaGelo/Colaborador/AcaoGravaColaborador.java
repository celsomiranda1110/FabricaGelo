/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Colaborador;

import Bean.Colaborador;
import Bean.ColaboradorProduto;
import Bean.VisitaColaborador;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.ColaboradorDAO;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
//import Bean.Empresa;
//import Bean.Funcionario;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author MIRANDA
 */
public class AcaoGravaColaborador extends Acao
{

    public String executa(HttpServletRequest req, HttpServletResponse res)
        throws Exception
    {
        
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        List<ColaboradorProduto> lstColaboradorProduto = new ArrayList<ColaboradorProduto>();
        List<VisitaColaborador> lstVisitaColaborador = new ArrayList<VisitaColaborador>();
        
        ColaboradorDAO colaboradorDAO = new ColaboradorDAO(conexao);
        Colaborador colaborador = (Colaborador)sessao.getAttribute("colaborador");
        if (colaborador == null)
        {
            colaborador = new Colaborador();

        }
        else
        {
            if (colaborador.getLstColaboradorProduto() != null)
                lstColaboradorProduto = colaborador.getLstColaboradorProduto();
            
            if ((colaborador.getLstVisitaColaborador() == null) || (colaborador.getLstVisitaColaborador().size() == 0))
                            {
                for(int d = 1; d < 8; d++)
                {
                    VisitaColaborador visitaColaborador = new VisitaColaborador();
                    visitaColaborador.setDia(d);
                    visitaColaborador.setAtivo("I"); 
                    lstVisitaColaborador.add(visitaColaborador);
                }                
            }
            else
                lstVisitaColaborador = colaborador.getLstVisitaColaborador();

        }
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
        

        String icms = (req.getParameter("txtIcms").equals("") || req.getParameter("txtIcms") == null) ? "" : req.getParameter("txtIcms"); 
        String desconto = (req.getParameter("txtDesconto").equals("") || req.getParameter("txtDesconto") == null) ? "" : req.getParameter("txtDesconto");        
        String valor = (req.getParameter("txtValor").equals("") || req.getParameter("txtValor") == null) ? "" : req.getParameter("txtValor");        
        if (!icms.equals(""))
        {
            ColaboradorProduto colaboradorProduto = (ColaboradorProduto)sessao.getAttribute("colaboradorProduto");
            if (colaboradorProduto == null)
                colaboradorProduto = new ColaboradorProduto();
            colaboradorProduto.setIcms(Double.parseDouble(icms));
            colaboradorProduto.setDesconto(Double.parseDouble(desconto));
            colaboradorProduto.setValor(Double.parseDouble(valor));
            lstColaboradorProduto.add(colaboradorProduto);
        }
        
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
        colaborador.setLstColaboradorProduto(lstColaboradorProduto);
        colaborador.setLstVisitaColaborador(lstVisitaColaborador);
       
        if (colaboradorDAO.atualizar(colaborador))
        {
            sessao.setAttribute("colaborador", colaborador);
            sessao.setAttribute("lstColaboradorProduto",colaborador.getLstColaboradorProduto());
        }
        
        return "FabricaGelo.Colaborador.AcaoListarColaborador";
        
    }

    public AcaoGravaColaborador() {
    }
}
