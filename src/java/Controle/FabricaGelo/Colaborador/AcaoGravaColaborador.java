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
        
        String pagRetorno = "FabricaGelo.Colaborador.AcaoAbreColaborador";

//        List<ColaboradorProduto> lstColaboradorProduto = new ArrayList<ColaboradorProduto>();
//        List<VisitaColaborador> lstVisitaColaborador = new ArrayList<VisitaColaborador>();            
       
        // Dados do colaborador
        String idTipoColaborador = (req.getParameter("cmbTipoColaborador").equals("") || req.getParameter("cmbTipoColaborador") == null) ? "0" : req.getParameter("cmbTipoColaborador");
        String cnpj = (req.getParameter("txtCnpj").equals("") || req.getParameter("txtCnpj") == null) ? "" : req.getParameter("txtCnpj");
        String inscricaoEstadual = (req.getParameter("txtInscricaoEstadual").equals("") || req.getParameter("txtInscricaoEstadual") == null) ? "" : req.getParameter("txtInscricaoEstadual");        
        String inscricaoMunicipal = (req.getParameter("txtInscricaoMunicipal").equals("") || req.getParameter("txtInscricaoMunicipal") == null) ? "" : req.getParameter("txtInscricaoMunicipal");        
        String numMei = (req.getParameter("txtNumMei").equals("") || req.getParameter("txtNumMei") == null) ? "" : req.getParameter("txtNumMei");        
        String nomeFantasia = (req.getParameter("txtNomeFantasia").equals("") || req.getParameter("txtNomeFantasia") == null) ? "" : req.getParameter("txtNomeFantasia").toUpperCase();        
        String razaoSocial = (req.getParameter("txtRazaoSocial").equals("") || req.getParameter("txtRazaoSocial") == null) ? "" : req.getParameter("txtRazaoSocial").toUpperCase();        
        String endereco = (req.getParameter("txtEndereco").equals("") || req.getParameter("txtEndereco") == null) ? "" : req.getParameter("txtEndereco").toUpperCase();        
        String bairro = (req.getParameter("cmbBairro").equals("") || req.getParameter("cmbBairro") == null) ? "" : req.getParameter("cmbBairro");        
        String cpf = (req.getParameter("txtCpf").equals("") || req.getParameter("txtCpf") == null) ? "" : req.getParameter("txtCpf");        
        String contato = (req.getParameter("txtContato").equals("") || req.getParameter("txtContato") == null) ? "" : req.getParameter("txtContato").toUpperCase();        
        String fone = (req.getParameter("txtFone").equals("") || req.getParameter("txtFone") == null) ? "" : req.getParameter("txtFone").toUpperCase();        
        String email = (req.getParameter("txtEmail").equals("") || req.getParameter("txtEmail") == null) ? "" : req.getParameter("txtEmail");        
        String observacao = (req.getParameter("txtObservacao").equals("") || req.getParameter("txtObservacao") == null) ? "" : req.getParameter("txtObservacao");        
        String inativo = (req.getParameter("ck_Ativo") == null ? "A" : "I");
        
        ColaboradorDAO colaboradorDAO = new ColaboradorDAO(conexao);
        Colaborador colaborador = (Colaborador)sessao.getAttribute("colaborador");
        
        if (colaborador == null)
            colaborador = new Colaborador();
        
        
//        // Definindo a definição de visitas
//        if (colaborador == null)
//        {
//            colaborador = new Colaborador();
//            for(int d = 1; d < 8; d++)
//            {
//                VisitaColaborador visitaColaborador = new VisitaColaborador();
//                visitaColaborador.setDia(d);
//                visitaColaborador.setAtivo("I"); 
//                lstVisitaColaborador.add(visitaColaborador);
//            }               
//        }
//        else
//        {
//            colaborador = colaboradorDAO.listaUm(colaborador);
//            if ((colaborador.getLstVisitaColaborador() == null) || (colaborador.getLstVisitaColaborador().size() == 0))
//            {
//                for(int d = 1; d < 8; d++)
//                {
//                    VisitaColaborador visitaColaborador = new VisitaColaborador();
//                    visitaColaborador.setDia(d);
//                    visitaColaborador.setAtivo("I"); 
//                    lstVisitaColaborador.add(visitaColaborador);
//                }                
//            }
//            else
//              lstVisitaColaborador = colaborador.getLstVisitaColaborador();
//        }   
        
        colaborador.setIdTipoColaborador(Integer.parseInt(idTipoColaborador));
        colaborador.setCnpj(cnpj);
        colaborador.setInscricaoEstadual(inscricaoEstadual);
        colaborador.setInscricaoMunicipal(inscricaoMunicipal);
        colaborador.setNumMei(numMei);
        colaborador.setRazaoSocial(razaoSocial);
        colaborador.setNome(nomeFantasia);
        colaborador.setEndereco(endereco);
        colaborador.setIdBairro(Integer.parseInt(bairro));
        colaborador.setCpf(cpf);
        colaborador.setContato(contato);
        colaborador.setFone(fone);
        colaborador.setEmail(email); 
        colaborador.setObservacao(observacao);
        colaborador.setAtivo(inativo);
//        colaborador.setLstColaboradorProduto(lstColaboradorProduto);
//        colaborador.setLstVisitaColaborador(lstVisitaColaborador);        
        
        if (colaborador.getIdBairro() == 0)
        {
            sessao.setAttribute("avisoErro", "Necessário informar bairro");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Colaborador.AcaoNovoColaborador");
            pagRetorno = "visao/erro.jsp";              
        }
        if (colaborador.getIdTipoColaborador() == 0)
        {
            sessao.setAttribute("avisoErro", "Necessário informar tipo de empreendimento");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Colaborador.AcaoNovoColaborador");
            pagRetorno = "visao/erro.jsp";              
        }        
        else if(colaborador.getRazaoSocial().equals(""))
        {
            sessao.setAttribute("avisoErro", "Necessário informar Razão Social");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Colaborador.AcaoNovoColaborador");
            pagRetorno = "visao/erro.jsp";             
        }
        else if(colaborador.getInscricaoEstadual().equals("") || colaborador.getInscricaoMunicipal().equals("") )
        {
            sessao.setAttribute("avisoErro", "Necessário informar Inscrição pública");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Colaborador.AcaoNovoColaborador");
            pagRetorno = "visao/erro.jsp";             
        }
        else if(colaborador.getCnpj().equals("") || colaborador.getCnpj().equals("") )
        {
            sessao.setAttribute("avisoErro", "Necessário informar CNPJ");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Colaborador.AcaoNovoColaborador");
            pagRetorno = "visao/erro.jsp";             
        }
        {
            
//             // Dados do produto
//            String idProduto = (req.getParameter("cmbProduto").equals("") || req.getParameter("cmbProduto") == null) ? "0" : req.getParameter("cmbProduto"); 
//            String icms = (req.getParameter("txtIcms").equals("") || req.getParameter("txtIcms") == null) ? "" : req.getParameter("txtIcms"); 
//            String desconto = (req.getParameter("txtDesconto").equals("") || req.getParameter("txtDesconto") == null) ? "" : req.getParameter("txtDesconto");        
//            String valor = (req.getParameter("txtValor").equals("") || req.getParameter("txtValor") == null) ? "" : req.getParameter("txtValor");        
//            
//            ColaboradorProduto colaboradorProduto = (ColaboradorProduto)sessao.getAttribute("colaboradorProduto");
//            if (colaboradorProduto == null)
//                colaboradorProduto = new ColaboradorProduto();
//            
//            if (!idProduto.equals("0"))
//            {
//                colaboradorProduto.setIdProduto(Integer.parseInt(idProduto));
//                colaboradorProduto.setIcms(Double.parseDouble(icms));
//                colaboradorProduto.setDesconto(Double.parseDouble(desconto));
//                colaboradorProduto.setValor(Double.parseDouble(valor));
//                lstColaboradorProduto.add(colaboradorProduto);
//            }
 
            if (colaboradorDAO.atualizar(colaborador))
            {

                colaborador.setIdColaborador(colaboradorDAO.getIdentity());
                colaborador = colaboradorDAO.listaUm(colaborador);

                sessao.setAttribute("colaborador", colaborador);  
                sessao.setAttribute("colaboradorProduto", null);

                sessao.setAttribute("avisoErro", "Empresa atualizada");
                sessao.setAttribute("tipoAviso","alert alert-success");
                sessao.setAttribute("pagOrigemErro", "FabricaGelo.Colaborador.AcaoAbreColaborador");
                pagRetorno = "visao/erro.jsp";             
            }
            
        }
        
        sessao.setAttribute("colaborador", colaborador);
        
        return pagRetorno;        
        
        
//        List<ColaboradorProduto> lstColaboradorProduto = null;
//        List<VisitaColaborador> lstVisitaColaborador = new ArrayList<VisitaColaborador>();
//        
//        ColaboradorDAO colaboradorDAO = new ColaboradorDAO(conexao);
//        Colaborador colaborador = (Colaborador)sessao.getAttribute("colaborador");
//        ColaboradorProduto colaboradorProduto = (ColaboradorProduto)sessao.getAttribute("colaboradorProduto");
//        
//        
//        if (colaborador == null)
//        {
//            colaborador = new Colaborador();
//            for(int d = 1; d < 8; d++)
//            {
//                VisitaColaborador visitaColaborador = new VisitaColaborador();
//                visitaColaborador.setDia(d);
//                visitaColaborador.setAtivo("I"); 
//                lstVisitaColaborador.add(visitaColaborador);
//            }               
//        }
//        else
//        {
//            
//            if ((colaborador.getLstVisitaColaborador() == null) || (colaborador.getLstVisitaColaborador().size() == 0))
//            {
//                for(int d = 1; d < 8; d++)
//                {
//                    VisitaColaborador visitaColaborador = new VisitaColaborador();
//                    visitaColaborador.setDia(d);
//                    visitaColaborador.setAtivo("I"); 
//                    lstVisitaColaborador.add(visitaColaborador);
//                }                
//            }
//            else
//              lstVisitaColaborador = colaborador.getLstVisitaColaborador();
//
//        }
//        
//        // Dados do colaborador
//        String cnpj = (req.getParameter("txtCnpj").equals("") || req.getParameter("txtCnpj") == null) ? "" : req.getParameter("txtCnpj");
//        String inscricaoEstadual = (req.getParameter("txtInscricaoEstadual").equals("") || req.getParameter("txtInscricaoEstadual") == null) ? "" : req.getParameter("txtInscricaoEstadual");        
//        String inscricaoMunicipal = (req.getParameter("txtInscricaoMunicipal").equals("") || req.getParameter("txtInscricaoMunicipal") == null) ? "" : req.getParameter("txtInscricaoMunicipal");        
//        String numMei = (req.getParameter("txtNumMei").equals("") || req.getParameter("txtNumMei") == null) ? "" : req.getParameter("txtNumMei");        
//        String nomeFantasia = (req.getParameter("txtNomeFantasia").equals("") || req.getParameter("txtNomeFantasia") == null) ? "" : req.getParameter("txtNomeFantasia").toUpperCase();        
//        String razaoSocial = (req.getParameter("txtRazaoSocial").equals("") || req.getParameter("txtRazaoSocial") == null) ? "" : req.getParameter("txtRazaoSocial").toUpperCase();        
//        String endereco = (req.getParameter("txtEndereco").equals("") || req.getParameter("txtEndereco") == null) ? "" : req.getParameter("txtEndereco").toUpperCase();        
//        String bairro = (req.getParameter("cmbBairro").equals("") || req.getParameter("cmbBairro") == null) ? "" : req.getParameter("cmbBairro");        
//        String cpf = (req.getParameter("txtCpf").equals("") || req.getParameter("txtCpf") == null) ? "" : req.getParameter("txtCpf");        
//        String contato = (req.getParameter("txtContato").equals("") || req.getParameter("txtContato") == null) ? "" : req.getParameter("txtContato").toUpperCase();        
//        String fone = (req.getParameter("txtFone").equals("") || req.getParameter("txtFone") == null) ? "" : req.getParameter("txtFone").toUpperCase();        
//        String email = (req.getParameter("txtEmail").equals("") || req.getParameter("txtEmail") == null) ? "" : req.getParameter("txtEmail");        
//        
//        // Dados do produto
//        String icms = (req.getParameter("txtIcms").equals("") || req.getParameter("txtIcms") == null) ? "" : req.getParameter("txtIcms"); 
//        String desconto = (req.getParameter("txtDesconto").equals("") || req.getParameter("txtDesconto") == null) ? "" : req.getParameter("txtDesconto");        
//        String valor = (req.getParameter("txtValor").equals("") || req.getParameter("txtValor") == null) ? "" : req.getParameter("txtValor");        
//        if (colaboradorProduto != null)
//        {
//            colaboradorProduto.setIcms(Double.parseDouble(icms));
//            colaboradorProduto.setDesconto(Double.parseDouble(desconto));
//            colaboradorProduto.setValor(Double.parseDouble(valor));
//            lstColaboradorProduto = new ArrayList<ColaboradorProduto>();
//            lstColaboradorProduto.add(colaboradorProduto);
//        }
//        
//        colaborador.setCnpj(cnpj);
//        colaborador.setInscricaoEstadual(inscricaoEstadual);
//        colaborador.setInscricaoMunicipal(inscricaoMunicipal);
//        colaborador.setNumMei(numMei);
//        colaborador.setRazaoSocial(razaoSocial);
//        colaborador.setNome(nomeFantasia);
//        colaborador.setEndereco(endereco);
//        colaborador.setCpf(cpf);
//        colaborador.setContato(contato);
//        colaborador.setFone(fone);
//        colaborador.setEmail(email);  
//        colaborador.setLstColaboradorProduto(lstColaboradorProduto);
//        colaborador.setLstVisitaColaborador(lstVisitaColaborador);
//       
//
//
//        if (colaboradorDAO.atualizar(colaborador))
//        {
//            
//            colaborador.setIdColaborador(colaboradorDAO.getIdentity());
//            colaborador = colaboradorDAO.listaUm(colaborador);
// 
//            sessao.setAttribute("colaborador", colaborador);  
//            sessao.setAttribute("colaboradorProduto", null);
//            
//            sessao.setAttribute("avisoErro", "Empresa atualizada");
//            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Colaborador.AcaoAbreColaborador");
//            pagRetorno = "visao/erro.jsp";             
//        }
//        
//
//        return pagRetorno;
        
    }

    public AcaoGravaColaborador() {
    }
}
  