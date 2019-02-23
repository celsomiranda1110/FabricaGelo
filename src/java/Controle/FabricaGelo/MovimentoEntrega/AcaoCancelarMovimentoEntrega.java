/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.MovimentoEntrega;

import Bean.ColaboradorEntrega;
import Bean.Entrega;
import Bean.Movimento;
import Bean.MovimentoEntrega;
import Bean.ProdutoEntrega;
import Bean.ProdutoMovimento;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.EntregaDAO;
import DAO.MovimentoDAO;
import DAO.MovimentoEntregaDAO;
import DAO.ProdutoEntregaDAO;
import java.sql.Connection;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoCancelarMovimentoEntrega extends Acao{ 
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String pagRetorno = "FabricaGelo.MovimentoEntrega.AcaoAbreMovimentoEntrega";
        
        Entrega entrega = (Entrega)sessao.getAttribute("entrega");
        ColaboradorEntrega colaboradorEntrega = (ColaboradorEntrega)sessao.getAttribute("colaboradorEntrega");        
        

        MovimentoEntregaDAO movimentoEntregaDAO = new MovimentoEntregaDAO(conexao);
        MovimentoEntrega movimentoEntrega = (MovimentoEntrega)sessao.getAttribute("movimentoEntrega");
        movimentoEntrega = movimentoEntregaDAO.listaUm(movimentoEntrega);
        
        if (entrega == null)
        {
            sessao.setAttribute("avisoErro", "Entrega não selecionada");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Entrega.AcaoEncerrarRota");
            pagRetorno = "visao/erro.jsp";              
        }
        else if (colaboradorEntrega == null)
        {
            sessao.setAttribute("avisoErro", "Cliente não selecionado");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Entrega.AcaoEncerrarRota");
            pagRetorno = "visao/erro.jsp";             
        }
        else if (movimentoEntrega == null)
        {
            sessao.setAttribute("avisoErro", "Movimento não selecionado");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Entrega.AcaoEncerrarRota");
            pagRetorno = "visao/erro.jsp";             
        }
        else if (movimentoEntrega.getSituacao().equals("CA"))
        {
            sessao.setAttribute("avisoErro", "Movimento cancelado");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Entrega.AcaoEncerrarRota");
            pagRetorno = "visao/erro.jsp";             
        }
        else
        {
            movimentoEntrega.setSituacao("CA");
            if (movimentoEntregaDAO.atualizar(movimentoEntrega))
            {
                EntregaDAO entregaDAO = new EntregaDAO(conexao);
                entrega = entregaDAO.listaUm(entrega);
                
                ProdutoEntregaDAO produtoEntregaDAO = new ProdutoEntregaDAO(conexao);
                
                // atualizando saldo dos produtos
                MovimentoDAO movimentoDAO = new MovimentoDAO(conexao);
                Movimento movimento = (Movimento)movimentoEntrega.getMovimento();
                movimento.setSituacao("CA");
                if (movimentoDAO.atualizar(movimento))
                {
                    movimento = movimentoDAO.listaUm(movimento);
                    Iterator itProdutoMovimento = movimento.getLstProdutoMovimento().iterator();
                    while (itProdutoMovimento.hasNext())
                    {
                        ProdutoMovimento produtoMovimento = (ProdutoMovimento)itProdutoMovimento.next();

                        Iterator itProdutoEntrega = entrega.getLstProdutoEntrega().iterator();
                        while (itProdutoEntrega.hasNext())
                        {
                            ProdutoEntrega produtoEntrega = (ProdutoEntrega)itProdutoEntrega.next();

                            if (produtoMovimento.getIdProduto() == produtoEntrega.getIdProduto())
                            {
                                if (movimento.getTipo().equals("BO"))
                                    produtoEntrega.setDblQuantidadeBonus(produtoEntrega.getDblQuantidadeBonus() - produtoMovimento.getQuantidade());
                                if (movimento.getTipo().equals("CO"))
                                    produtoEntrega.setDblQuantidadeCortesia(produtoEntrega.getDblQuantidadeCortesia() - produtoMovimento.getQuantidade());
                                if (movimento.getTipo().equals("VE"))
                                    produtoEntrega.setDblQuantidadeVenda(produtoEntrega.getDblQuantidadeVenda() - produtoMovimento.getQuantidade());
                                if (movimento.getTipo().equals("RE"))
                                    produtoEntrega.setDblQuantidadeReposicao(produtoEntrega.getDblQuantidadeReposicao() - produtoMovimento.getQuantidade());
                                produtoEntrega.setDblSaldo(produtoEntrega.getDblSaldo() + produtoMovimento.getQuantidade());
                                produtoEntregaDAO.atualizar(produtoEntrega);
                            }
                        }
                    }
                }
            }
            
            
        }
        
        sessao.setAttribute("movimentoEntrega", movimentoEntrega);
        sessao.setAttribute("colaboradorEntrega", colaboradorEntrega);
        
        return pagRetorno;
        
    }
}
