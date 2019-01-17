/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Movimento;


import Bean.Movimento;
import Bean.MovimentoEntrega;
import Controle.FabricaGelo.Gerais.Acao;
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
public class AcaoPesquisaMovimentoGeral extends Acao
{
    
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        
        String tipoMovimentoPesquisa = req.getParameter("cmbTipoPesquisa");
        String pesquisa = req.getParameter("txtPesquisa");
        
        MovimentoDAO movimentoDAO = new MovimentoDAO(conexao);
//        Movimento movimento = new Movimento();
//        movimento.setTipo(tipoMovimentoPesquisa);
//        movimento.setDataLancamento(pesquisa);
        
        List<Movimento> lstMovimento = movimentoDAO.listaTodos(null);
        List<Movimento> lstMovimentoRetorno = new ArrayList<Movimento>();

        Iterator itMovimento = lstMovimento.iterator();
        while (itMovimento.hasNext())
        {
            Movimento _movimento = (Movimento)itMovimento.next();
            _movimento = movimentoDAO.listaUm(_movimento);
            if (_movimento != null)
            {
                if (_movimento.getTipo().equals(tipoMovimentoPesquisa) || _movimento.getDataLancamento().equals(pesquisa))
                    lstMovimentoRetorno.add(_movimento);
            }
        }
 
        
        sessao.setAttribute("lstMovimento",lstMovimentoRetorno); 
        
        return "visao/listarMovimentoGeral.jsp";              
 
        
    }
}

