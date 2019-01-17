/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Movimento;

import Bean.ColaboradorEntrega;
import Bean.Movimento;
import Bean.MovimentoEntrega;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.ColaboradorEntregaDAO;
import DAO.MovimentoDAO;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoPesquisaMovimento extends Acao
{
    
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        
        String tipoMovimentoPesquisa = req.getParameter("cmbTipoPesquisa");
        String pesquisa = req.getParameter("txtPesquisa");
        
        MovimentoDAO movimentoDAO = new MovimentoDAO(conexao);
        
        ColaboradorEntrega colaboradorEntrega = (ColaboradorEntrega)sessao.getAttribute("colaboradorEntrega");
        ColaboradorEntregaDAO colaboradorEntregaDAO = new ColaboradorEntregaDAO(conexao);

        colaboradorEntrega = colaboradorEntregaDAO.listaUm(colaboradorEntrega);
        List<MovimentoEntrega> lstMovimentoEntrega = colaboradorEntrega.getLstMovimentoEntrega();
        List<MovimentoEntrega> lstMovimentoEntregaRetorno = new ArrayList<MovimentoEntrega>();



        Iterator itMovEntrega = lstMovimentoEntrega.iterator();
        while (itMovEntrega.hasNext())
        {
            MovimentoEntrega movEntrega = (MovimentoEntrega)itMovEntrega.next();
            Movimento movimento = new Movimento();
            movimento.setIdMovimento(movEntrega.getIdMovimento());
            movimento = movimentoDAO.listaUm(movimento);
            if (movimento != null)
            {
                if (movimento.getTipo().equals(tipoMovimentoPesquisa) || movimento.getDataLancamento().equals(pesquisa))
                    lstMovimentoEntregaRetorno.add(movEntrega);
            }
        }
 
        
        sessao.setAttribute("lstMovimentoEntrega",lstMovimentoEntregaRetorno); 
        
        return "visao/listarMovimento.jsp";              
 
        
    }
}
