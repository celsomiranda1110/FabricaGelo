/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Entrega;


import Bean.Entrega;
import Bean.Movimento;
import Bean.Profissional;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.EntregaDAO;
import DAO.ProfissionalDAO;
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
public class AcaoBuscarProfissional extends Acao{ 
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        

        String pagRetorno = "visao/rotaProfissionais.jsp";
        EntregaDAO entregaDAO = new EntregaDAO(conexao);
        Entrega entrega = (Entrega)sessao.getAttribute("entrega");
        
        //salvando entrega para identificar os profissionaias da entrega
        if (!entregaDAO.atualizar(entrega))
        {
            sessao.setAttribute("avisoErro", "Problemas ao adicionar rota");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Entrega.AcaoListarEntrega");
            pagRetorno = "visao/erro.jsp";            
        }
        else
        {
        
            ProfissionalDAO profissionalDAO = new ProfissionalDAO(conexao);
            List<Profissional> lstProfissional = new ArrayList<Profissional>();
            lstProfissional = profissionalDAO.listaTodos();        

            sessao.setAttribute("entrega",entrega);
            sessao.setAttribute("lstProfissional",lstProfissional);

        }
        return pagRetorno;
    }
}
