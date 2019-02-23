/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.TipoColaborador;

import Bean.Colaborador;
import Bean.TipoColaborador;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.ColaboradorDAO;
import DAO.TipoColaboradorDAO;
import java.sql.Connection;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoDeleteTipoColaborador extends Acao
{
    
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);

        String pagRetorno = "FabricaGelo.TipoColaborador.AcaoAbreTipoColaborador";
        
        TipoColaborador tipoColaborador = (TipoColaborador)sessao.getAttribute("tipoColaborador");
        
        if (tipoColaborador == null)
        {
            sessao.setAttribute("avisoErro", "Tipo de empresa não selecionado");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.TipoColaborador.AcaoAbreTipoColaborador");
            pagRetorno = "visao/erro.jsp";             
        }
        else
        {
            boolean bolApaga = true;
            
            ColaboradorDAO colaboradorDAO = new ColaboradorDAO(conexao);
            Iterator itColaborador = colaboradorDAO.listaTodos(new Colaborador()).iterator();
            while (itColaborador.hasNext())
            {
                Colaborador colaborador = (Colaborador)itColaborador.next();
                if (colaborador.getIdTipoColaborador() == tipoColaborador.getIdTipoColaborador())
                {
                    bolApaga = false;
                    break;
                }
            }
            
            if (!bolApaga)
            {
                sessao.setAttribute("avisoErro", "Tipo de empresa está relacionado com movimentos. Inative para preservação dos dados");
                sessao.setAttribute("tipoAviso","alert alert-danger");
                sessao.setAttribute("pagOrigemErro", "FabricaGelo.TipoColaborador.AcaoAbreTipoColaborador");
                pagRetorno = "visao/erro.jsp";                 
            }
            else
            {
                TipoColaboradorDAO tipoColaboradorDAO = new TipoColaboradorDAO(conexao);
                if (tipoColaboradorDAO.delete(tipoColaborador))
                {
                    sessao.setAttribute("avisoErro", "Tipo de empresa deletado com sucesso");
                    sessao.setAttribute("tipoAviso","alert alert-success");
                    sessao.setAttribute("pagOrigemErro", "FabricaGelo.TipoColaborador.AcaoListarTipoColaborador");
                    pagRetorno = "visao/erro.jsp";              
                    
                }
            }
        }
        
        sessao.setAttribute("tipoColaborador", tipoColaborador);
        
        return pagRetorno;
        
    }
}
