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
public class AcaoGravaCarregamento extends Acao
{

    public String executa(HttpServletRequest req, HttpServletResponse res)
        throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String pagRetorno = "FabricaGelo.Carregamento.AcaoAbreCarregamento";
        
        SaidaCamara saidaCamara = (SaidaCamara)sessao.getAttribute("saidaCamara");
        ProdutoCamara produtoCamara = (ProdutoCamara)sessao.getAttribute("produtoCamara");
        String qtSaida = (req.getParameter("txtQtSaida").equals("") || req.getParameter("txtQtSaida") == null) ? "0" : req.getParameter("txtQtSaida");
       
        if (saidaCamara == null)
        {
            sessao.setAttribute("avisoErro", "Campos do carregamento não preenchidos.");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Carregamento.AcaoAbreCarregamento");
            pagRetorno = "visao/erro.jsp";             
        }
        else if(produtoCamara == null)
        {
            sessao.setAttribute("avisoErro", "Produto ou estoque não selecionados.");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Carregamento.AcaoAbreCarregamento");
            pagRetorno = "visao/erro.jsp";             
        } 
        else if (saidaCamara.getSituacao().equals("CA"))
        {
            sessao.setAttribute("avisoErro", "Carregamento finalizado");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Carregamento.AcaoAbreCarregamento");
            pagRetorno = "visao/erro.jsp";             
        }
        else if (saidaCamara.getSituacao().equals("CD"))
        {
            sessao.setAttribute("avisoErro", "Carregamento devolvido");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Carregamento.AcaoAbreCarregamento");
            pagRetorno = "visao/erro.jsp";             
        }
        else
        {
            // Verificando se quantidade de saída é maior que o registrado
            // na câmara
            
            ProdutoCamaraDAO produtoCamaraDAO = new ProdutoCamaraDAO(conexao);
            produtoCamara = produtoCamaraDAO.listaUm(produtoCamara);
            if (produtoCamara.getSaldo() < Double.parseDouble(qtSaida))
            {
                sessao.setAttribute("avisoErro", "Qt de saída maior que o estoque na câmara");
                sessao.setAttribute("tipoAviso","alert alert-danger");
                sessao.setAttribute("pagOrigemErro", "FabricaGelo.Carregamento.AcaoAbreCarregamento");
                pagRetorno = "visao/erro.jsp";                 
            }
            else
            {
                SaidaCamaraDAO saidaCamaraDAO = new SaidaCamaraDAO(conexao);
                saidaCamara.setIdProdutoCamara(produtoCamara.getIdProdutoCamara());
                saidaCamara.setSaida(Double.parseDouble(qtSaida));
                saidaCamara.setDevolucao(0);
                saidaCamara.setSituacao("CC");

                if (saidaCamaraDAO.atualizar(saidaCamara))
                {
                    saidaCamara = saidaCamaraDAO.listaUm(saidaCamara);

                    sessao.setAttribute("avisoErro", "Carregamento salvo com sucesso");
                    sessao.setAttribute("tipoAviso","alert alert-success");
                    sessao.setAttribute("pagOrigemErro", "FabricaGelo.Carregamento.AcaoListarCarregamento");
                    pagRetorno = "visao/erro.jsp";                 
                }
            }
        }
        
        return pagRetorno;
        
    }
}
