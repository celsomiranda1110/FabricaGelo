/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Entrega;

import Bean.CustoEntrega;
import Bean.Entrega;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.CustoEntregaDAO;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoAdicionaCusto extends Acao
{
    
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String pagRetorno = "FabricaGelo.Entrega.AcaoEncerrarRota";
        
        Entrega entrega = (Entrega)sessao.getAttribute("entrega");
        if (entrega == null)
            entrega = new Entrega();
        
        String idColaborador = (req.getParameter("cmbColaborador").equals("") || req.getParameter("cmbColaborador") == null) ? "0" : req.getParameter("cmbColaborador");
        String numNota = (req.getParameter("txtNota").equals("") || req.getParameter("txtNota") == null) ? "" : req.getParameter("txtNota");
        String descCusto = (req.getParameter("txtDescricao").equals("") || req.getParameter("txtDescricao") == null) ? "" : req.getParameter("txtDescricao").toUpperCase();
        String qtCusto = (req.getParameter("txtQtCusto").equals("") || req.getParameter("txtQtCusto") == null) ? "0" : req.getParameter("txtQtCusto");
        String vlCusto = (req.getParameter("txtVlUnitCusto").equals("") || req.getParameter("txtVlUnitCusto") == null) ? "0" : req.getParameter("txtVlUnitCusto");
        
        double vQtCusto = Double.parseDouble(qtCusto);
        double vVlCusto = Double.parseDouble(vlCusto);
        double vVTCusto = vQtCusto * vVlCusto;
        
        CustoEntrega custo = new CustoEntrega();
        custo.setIdEntrega(entrega.getIdEntrega());
        custo.setIdColaborador(Integer.parseInt(idColaborador));
        custo.setDescricao(descCusto);
        custo.setQuantidade(vQtCusto);
        custo.setVlUnitario(vVlCusto);
        custo.setVlTotal(vVTCusto);
        
        if (custo.getIdEntrega() == 0)
        {
            sessao.setAttribute("avisoErro", "Rota não selecionada");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Entrega.AcaoEncerrarRota");
            pagRetorno = "visao/erro.jsp";             
        }
        else if (custo.getIdColaborador()== 0)
        {
            sessao.setAttribute("avisoErro", "Fornecedor não selecionado");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Entrega.AcaoEncerrarRota");
            pagRetorno = "visao/erro.jsp";             
        }
        else if (custo.getDescricao().equals(""))
        {
            sessao.setAttribute("avisoErro", "Produto ou serviço não informado");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Entrega.AcaoEncerrarRota");
            pagRetorno = "visao/erro.jsp";             
        }
        else
        {
            CustoEntregaDAO custoDAO = new CustoEntregaDAO(conexao);
            if (custoDAO.atualizar(custo))
            {
                //custo.setIdCustoEntrega(custoDAO.getIdentity());
                
                sessao.setAttribute("custoEntrega", null);
                sessao.setAttribute("paramPesquisa", null);
                sessao.setAttribute("avisoErro", "Custo informado");
                sessao.setAttribute("pagOrigemErro", "FabricaGelo.Entrega.AcaoEncerrarRota");
                pagRetorno = "visao/erro.jsp";             
                
            }
        }
        
        return pagRetorno;
        
    }
}
