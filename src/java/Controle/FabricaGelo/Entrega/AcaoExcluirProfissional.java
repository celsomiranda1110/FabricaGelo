/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Entrega;

import Bean.Entrega;
import Bean.ProfissionalEntrega;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.EntregaDAO;
import DAO.ProfissionalEntregaDAO;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoExcluirProfissional extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String pagRetorno = "FabricaGelo.Entrega.AcaoNovaRota";
        
        EntregaDAO entregaDAO = new EntregaDAO(conexao);
        Entrega entrega = (Entrega)sessao.getAttribute("entrega");
        
        String idProfissionalEntrega = req.getParameter("idProfissionalEntrega");
        ProfissionalEntregaDAO profissionalEntregaDAO = new ProfissionalEntregaDAO(conexao);
        ProfissionalEntrega profissionalEntrega = new ProfissionalEntrega();
        profissionalEntrega.setIdProfissionalEntrega(Integer.parseInt(idProfissionalEntrega));
        
        if (profissionalEntregaDAO.deletar(profissionalEntrega))
            entrega = entregaDAO.listaUm(entrega);
        
        return pagRetorno;
    }
}
