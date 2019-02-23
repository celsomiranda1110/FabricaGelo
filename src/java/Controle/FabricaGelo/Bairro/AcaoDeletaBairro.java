/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Bairro;

import Bean.Bairro;
import Bean.Colaborador;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.BairroDAO;
import DAO.ColaboradorDAO;
import java.sql.Connection;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoDeletaBairro extends Acao
{
    
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String pagRetorno = "visao/bairro.jsp";
        
        Bairro bairro = (Bairro)sessao.getAttribute("bairro");
        if (bairro == null)
        {
            sessao.setAttribute("avisoErro", "Bairro não selecionado");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Bairro.AcaoAbreBairro");
            pagRetorno = "visao/erro.jsp";             
        }
        else
        {
            boolean bolApaga = true;
            
            // procurando possíveis relacionamentos
            ColaboradorDAO colaboradorDAO = new ColaboradorDAO(conexao);
            Iterator itColaborador = colaboradorDAO.listaTodos(null).iterator();
            while (itColaborador.hasNext())
            {
                Colaborador colaborador = (Colaborador)itColaborador.next();
                if (colaborador.getIdBairro() == bairro.getIdBairro())
                {
                    bolApaga = false;
                    break;
                }
            }
            
            if (!bolApaga)
            {
                sessao.setAttribute("avisoErro", "Bairro está relacionado a cadastro de empresas. Inative para preservação dos dados");
                sessao.setAttribute("tipoAviso","alert alert-danger");
                sessao.setAttribute("pagOrigemErro", "FabricaGelo.Bairro.AcaoAbreBairro");
                pagRetorno = "visao/erro.jsp";              
                
            }
            else
            {
                BairroDAO bairroDAO = new BairroDAO(conexao);
                if (bairroDAO.delete(bairro))
                {
                    sessao.setAttribute("avisoErro", "Bairro deletado com sucesso");
                    sessao.setAttribute("tipoAviso","alert alert-success");
                    sessao.setAttribute("pagOrigemErro", "FabricaGelo.Bairro.AcaoListarBairro");
                    pagRetorno = "visao/erro.jsp";                      
                }
            }            
        }
        return pagRetorno;
    }
}