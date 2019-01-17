/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Carregamento;

import Bean.ProdutoCamara;
import Bean.SaidaCamara;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.ProdutoCamaraDAO;
import DAO.SaidaCamaraDAO;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoFecharCarregamento extends Acao
{

    public String executa(HttpServletRequest req, HttpServletResponse res)
        throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String pagRetorno = "FabricaGelo.Carregamento.AcaoListarCarregamento";
        
        SaidaCamaraDAO saidaCamaraDAO = new SaidaCamaraDAO(conexao);
        ProdutoCamaraDAO produtoCamaraDAO = new ProdutoCamaraDAO(conexao);
        SaidaCamara saidaCamara = (SaidaCamara)sessao.getAttribute("saidaCamara");
        ProdutoCamara produtoCamara = (ProdutoCamara)sessao.getAttribute("produtoCamara");
        //String qtSaida = (req.getParameter("txtQtSaida").equals("") || req.getParameter("txtQtSaida") == null) ? "0" : req.getParameter("txtQtSaida");
        
        saidaCamara = saidaCamaraDAO.listaUm(saidaCamara);
        
        if (saidaCamara.getSituacao().equals("CA"))
        {
            sessao.setAttribute("avisoErro", "Carregamento finalizada");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Carregamento.AcaoAbreCarregamento");
            pagRetorno = "visao/erro.jsp";             
        } 
        else if (saidaCamara.getSituacao().equals("CD"))
        {
            sessao.setAttribute("avisoErro", "Carregamento devolvido");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Carregamento.AcaoAbreCarregamento");
            pagRetorno = "visao/erro.jsp";             
        }
        else
        {
            produtoCamara.setDataFormatada(saidaCamara.getDataFormatada());
            produtoCamara.setSaldoAnterior(produtoCamara.getSaldo());
            produtoCamara.setSaldo(produtoCamara.getSaldo() - saidaCamara.getSaida());

            if (produtoCamaraDAO.atualizar(produtoCamara))
            {
                saidaCamara.setSituacao("CA");
                saidaCamaraDAO.atualizar(saidaCamara);

                sessao.setAttribute("avisoErro", "Carregamento finalizado");
                sessao.setAttribute("pagOrigemErro", "FabricaGelo.Carregamento.AcaoAbreCarregamento");
                pagRetorno = "visao/erro.jsp";             
            }
        }
        return pagRetorno;
    }
}
