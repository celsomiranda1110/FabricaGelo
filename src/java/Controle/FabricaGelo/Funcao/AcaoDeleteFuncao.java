/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Funcao;

import Bean.Funcao;
import Bean.Profissional;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.FuncaoDAO;
import DAO.ProfissionalDAO;
import java.sql.Connection;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoDeleteFuncao extends Acao
{
    
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String pagRetorno = "visao/funcao.jsp";
        
        Funcao funcao = (Funcao)sessao.getAttribute("funcao");
        if (funcao == null)
        {
            sessao.setAttribute("avisoErro", "Função não selecionada");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Funcao.AcaoAbreFuncao");
            pagRetorno = "visao/erro.jsp";            
        }
        else
        {
            boolean bolApaga = true;
            
            ProfissionalDAO profissionalDAO = new ProfissionalDAO(conexao);
            Iterator itProfissional = profissionalDAO.listaTodos().iterator();
            while (itProfissional.hasNext())
            {
                Profissional profissional = (Profissional)itProfissional.next();
                if (profissional.getIdFuncao() == funcao.getIdFuncao())
                {
                    bolApaga = false;
                    break;
                }
            }
            if (!bolApaga)
            {
                sessao.setAttribute("avisoErro", "Função está relacionado a cadastro de profissional. Inative para preservação dos dados");
                sessao.setAttribute("tipoAviso","alert alert-danger");
                sessao.setAttribute("pagOrigemErro", "FabricaGelo.Funcao.AcaoAbreFuncao");
                pagRetorno = "visao/erro.jsp";                        
            }
            else
            {
                FuncaoDAO funcaoDAO = new FuncaoDAO(conexao);
                if (funcaoDAO.delete(funcao))
                {
                    sessao.setAttribute("avisoErro", "Função deletado com sucesso");
                    sessao.setAttribute("tipoAviso","alert alert-success");
                    sessao.setAttribute("pagOrigemErro", "FabricaGelo.Funcao.AcaoListarFuncao");
                    pagRetorno = "visao/erro.jsp";                    
                }
            }
        }
         
        return pagRetorno;
        
    }
}
