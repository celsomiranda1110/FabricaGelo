/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.TipoColaborador;

import Bean.TipoColaborador;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.TipoColaboradorDAO;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoGravaTipoColaborador extends Acao
{

    public String executa(HttpServletRequest req, HttpServletResponse res)
        throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String pagRetorno = "FabricaGelo.TipoColaborador.AcaoAbreTipoColaborador";
        TipoColaborador tipoColaborador = (TipoColaborador)sessao.getAttribute("tipoColaborador");
        String descricao = (req.getParameter("txtTipoColaborador") == "" || req.getParameter("txtTipoColaborador") == null) ? "" : req.getParameter("txtTipoColaborador").toUpperCase();
        String inativo = (req.getParameter("ck_Ativo") == null ? "A" : "I");
        
        if (tipoColaborador == null)
            tipoColaborador = new TipoColaborador();
        tipoColaborador.setDescricao(descricao);
        tipoColaborador.setAtivo(inativo);
        
        if (tipoColaborador.getDescricao().equals(""))
        {
            sessao.setAttribute("avisoErro", "Necessário descrição");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.TipoColaborador.AcaoAbreTipoColaborador");
            pagRetorno = "visao/erro.jsp";             
        }
        else
        {
            TipoColaboradorDAO tipoColaboradorDAO = new TipoColaboradorDAO(conexao);
            if (tipoColaboradorDAO.atualizar(tipoColaborador))
            {
                tipoColaborador.setIdTipoColaborador(tipoColaboradorDAO.getIdentity());
                
                sessao.setAttribute("tipoColaborador", tipoColaborador);
                sessao.setAttribute("avisoErro", "Tipo de colaborador atualizado com sucesso");
                sessao.setAttribute("tipoAviso","alert alert-success");
                sessao.setAttribute("pagOrigemErro", "FabricaGelo.TipoColaborador.AcaoAbreTipoColaborador");
                pagRetorno = "visao/erro.jsp";                  
            }
            else
            {
                sessao.setAttribute("avisoErro", "Problemas na atualização");
                sessao.setAttribute("tipoAviso","alert alert-danger");
                sessao.setAttribute("pagOrigemErro", "FabricaGelo.TipoColaborador.AcaoAbreTipoColaborador");
                pagRetorno = "visao/erro.jsp";             
                
            }
        }
        
        return pagRetorno;
        
    }
}
