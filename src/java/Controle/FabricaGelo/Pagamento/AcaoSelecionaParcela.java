/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Pagamento;

import Bean.Parcela;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.ParcelaDAO;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoSelecionaParcela extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        ParcelaDAO parcelaDAO = new ParcelaDAO(conexao);
        Parcela parcela = new Parcela();
        String idParcela = req.getParameter("idParcela");
        parcela.setIdParcela(Integer.parseInt(idParcela));
        parcela = parcelaDAO.listaUm(parcela);
        
        sessao.setAttribute("parcela", parcela);
        
        return "visao/pagamento.jsp";
        
        
    }
}
