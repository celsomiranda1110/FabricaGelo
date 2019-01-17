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
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoSelecionaProdutoCamara extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String pagRetorno = "FabricaGelo.Carregamento.AcaoAbreCarregamento";
        
        SaidaCamara saidaCamara = (SaidaCamara)sessao.getAttribute("saidaCamara");
        String idProdutoCamara = req.getParameter("idProdutoCamara");
        
        ProdutoCamaraDAO produtoCamaraDAO = new ProdutoCamaraDAO(conexao);
        ProdutoCamara produtoCamara = new ProdutoCamara();
        produtoCamara.setIdProdutoCamara(Integer.parseInt(idProdutoCamara));
        produtoCamara = produtoCamaraDAO.listaUm(produtoCamara);
        
        sessao.setAttribute("produtoCamara",produtoCamara);
        sessao.setAttribute("saidaCamara",saidaCamara);
        
        return pagRetorno;
    }
}
