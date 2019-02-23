/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Profissional;

import Bean.Entrega;
import Bean.ProdutoEntrega;
import Bean.Profissional;
import Bean.ProfissionalEntrega;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.EntregaDAO;
import DAO.MenuProfissionalDAO;
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
public class AcaoDeletaProfissional extends Acao
{
    
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String pagRetorno = "visao/maquina.jsp";
        
        Profissional profissional = (Profissional)sessao.getAttribute("profissional");
        if (profissional == null)
        {
            sessao.setAttribute("avisoErro", "Profissional não selecionado");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaeGelo.Profissional.AcaoAbreProfissional");
            pagRetorno = "visao/erro.jsp";            
        }
        else
        {
            boolean bolApaga = true;
            
            
            if (bolApaga)
            {
                EntregaDAO entregaDAO = new EntregaDAO(conexao);
                Iterator itEntrega = entregaDAO.listaTodos(null).iterator();
                while (itEntrega.hasNext())
                {
                    Entrega entrega = (Entrega)itEntrega.next();
                    entrega = entregaDAO.listaUm(entrega);
                    Iterator itProfissionalEntrega = entrega.getLstProfissionalEntrega().iterator();
                    while (itProfissionalEntrega.hasNext())
                    {
                        ProfissionalEntrega profissionalEntrega = (ProfissionalEntrega)itProfissionalEntrega.next();
                        if (profissionalEntrega.getIdProfissional()== profissional.getIdProfissional())
                        {
                            bolApaga = false;
                            break;
                        }
                    }
                    if (!bolApaga)
                        break;
                    
                }
                
            }
            
            if (!bolApaga)
            {
                sessao.setAttribute("avisoErro", "Profissional está relacionado com movimentos. Inative para preservação dos dados");
                sessao.setAttribute("tipoAviso","alert alert-danger");
                sessao.setAttribute("pagOrigemErro", "FabricaGelo.Profissional.AcaoAbreProfissional");
                pagRetorno = "visao/erro.jsp";                 
            }
            else
            {
                ProfissionalDAO profissionalDAO = new ProfissionalDAO(conexao);
                if (profissionalDAO.delete(profissional))
                {
                    sessao.setAttribute("avisoErro", "Profissional deletado com sucesso");
                    sessao.setAttribute("tipoAviso","alert alert-success");
                    sessao.setAttribute("pagOrigemErro", "FabricaGelo.Profissional.AcaoListarProfissional");
                    pagRetorno = "visao/erro.jsp";              
                    
                }
            }
        }
        
        return pagRetorno;
        
    }
}
