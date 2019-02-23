/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Colaborador;

import Bean.Colaborador;
import Bean.ColaboradorEntrega;
import Bean.CustoEntrega;
import Bean.Entrega;
import Bean.Movimento;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.ColaboradorDAO;
import DAO.ColaboradorEntregaDAO;
import DAO.EntregaDAO;
import DAO.MovimentoDAO;
import java.sql.Connection;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoDeletaColaborador extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String pagRetorno = "FabricaGelo.Colaborador.AcaoAbreColaborador";
        
        Colaborador colaborador = (Colaborador)sessao.getAttribute("colaborador");
        if (colaborador == null)
        {
            sessao.setAttribute("avisoErro", "Empresa não selecionada");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Colaborador.AcaoAbreColaborador");
            pagRetorno = "visao/erro.jsp";             
        }
        else
        {
            
            
            
            
            boolean bolApaga = true;
            
            
            EntregaDAO entregaDAO = new EntregaDAO(conexao);
            ColaboradorEntregaDAO colaboradorEntregaDAO = new ColaboradorEntregaDAO(conexao);
            
            Iterator itEntrega = entregaDAO.listaTodos(null).iterator();
            while (itEntrega.hasNext())
            {
                Entrega entrega = (Entrega)itEntrega.next();
                entrega = entregaDAO.listaUm(entrega);
                
                // Identificando participação de empresas em rotas
                Iterator itColaboradorEntrega = entrega.getLstColaboradorEntrega().iterator();
                while (itColaboradorEntrega.hasNext())
                {
                    ColaboradorEntrega colaboradorEntrega = (ColaboradorEntrega)itColaboradorEntrega.next();
                    if (colaboradorEntrega.getIdColaborador() == colaborador.getIdColaborador())
                    {
                        bolApaga = false;
                        break;
                    }
                }
                
                // Identificando participação de empresas em custo de rotas
                Iterator itCustoEntrega = entrega.getLstCustoEntrega().iterator();
                while (itCustoEntrega.hasNext())
                {
                    CustoEntrega custoEntrega = (CustoEntrega)itCustoEntrega.next();
                    if (custoEntrega.getIdColaborador() == colaborador.getIdColaborador())
                    {
                        bolApaga = false;
                        break;
                    }
                }
                
                if (!bolApaga)
                    break;
            }
            
            // segundo teste: participação de empresa em movimentos
            if (bolApaga)
            {
                MovimentoDAO movimentoDAO = new MovimentoDAO(conexao);
                Iterator itMovimento = movimentoDAO.listaTodos(null).iterator();
                while (itMovimento.hasNext())
                {
                    Movimento movimento = (Movimento)itMovimento.next();
                    if (movimento.getIdColaborador() == colaborador.getIdColaborador())
                    {
                        bolApaga = false;
                        break;
                    }
                }
            }
            
            
            if (!bolApaga)
            {
                sessao.setAttribute("avisoErro", "Empresa possui relacionamentos em rotas ou movimentos. Inative para preservação dos dados");
                sessao.setAttribute("tipoAviso","alert alert-danger");
                sessao.setAttribute("pagOrigemErro", "FabricaGelo.Colaborador.AcaoAbreColaborador");
                pagRetorno = "visao/erro.jsp";              
                
            }
            else
            {
                ColaboradorDAO colaboradorDAO = new ColaboradorDAO(conexao);
                if (colaboradorDAO.delete(colaborador))
                {
                    sessao.setAttribute("avisoErro", "Empresa apagada com sucesso");
                    sessao.setAttribute("tipoAviso","alert alert-success");
                    sessao.setAttribute("pagOrigemErro", "FabricaGelo.Colaborador.AcaoAbreColaborador");
                    pagRetorno = "visao/erro.jsp";                      
                }
            }
            
        }
        
        return pagRetorno;
        
    }
}
