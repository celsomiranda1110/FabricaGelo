/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Carregamento;

import Bean.SaidaCamara;
import Bean.Colaborador;
import Bean.ProdutoCamara;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.SaidaCamaraDAO;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoSelecionaCarregamento extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);

        String pagRetorno = "FabricaGelo.Carregamento.AcaoAbreCarregamento";
        
        SaidaCamaraDAO saidaCamaraDAO = new SaidaCamaraDAO(conexao);
        SaidaCamara saidaCamara = new SaidaCamara();
        
        String idSaidaCamara = req.getParameter("idSaidaCamara");
        saidaCamara.setIdSaidaCamara(Integer.parseInt(idSaidaCamara));
        saidaCamara = saidaCamaraDAO.listaUm(saidaCamara);
        
        ProdutoCamara produtoCamara = (ProdutoCamara)saidaCamara.getProdutoCamara();
        
        
        sessao.setAttribute("saidaCamara",saidaCamara);
        sessao.setAttribute("produtoCamara",produtoCamara);
        
        
        return pagRetorno;
    }
}
