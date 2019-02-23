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
import DAO.ColaboradorEntregaDAO;
import DAO.EntregaDAO;
import DAO.MovimentoDAO;
import DAO.MovimentoEntregaDAO;
import DAO.ProdutoEntregaDAO;
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
public class AcaoGravaMovimentoEntrega extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String pagRetorno = "FabricaGelo.MovimentoEntrega.AcaoAbreMovimentoEntrega";

        MovimentoDAO movimentoDAO = new MovimentoDAO(conexao);
        ColaboradorEntrega colaboradorEntrega = (ColaboradorEntrega)sessao.getAttribute("colaboradorEntrega");        
        MovimentoEntrega movimentoEntrega = (MovimentoEntrega)sessao.getAttribute("movimentoEntrega");
        Movimento movimento = (Movimento)sessao.getAttribute("movimento");
        movimento.setNumero(movimento.getNumero().equals("") ? ("" + movimento.getIdMovimento()) : "");
        
        ProdutoMovimento produtoMovimento = (ProdutoMovimento)sessao.getAttribute(("produtoMovimento"));
        
        if (movimentoEntrega.getSituacao().equals("CA"))
        {
            sessao.setAttribute("avisoErro", "Movimento cancelado");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Entrega.AcaoEncerrarRota");
            pagRetorno = "visao/erro.jsp";             
        }
        else
        {
            
            if (produtoMovimento != null)
            {
                List<ProdutoMovimento> lstProdutoMovimento = new ArrayList<ProdutoMovimento>();
                String vlUnitario = (req.getParameter("txtVlUnitario").equals("") || req.getParameter("txtVlUnitario") == null) ? "0" : req.getParameter("txtVlUnitario");
                String quantidade = (req.getParameter("txtQuantidade").equals("") || req.getParameter("txtQuantidade") == null) ? "0" : req.getParameter("txtQuantidade");
                String icms = (req.getParameter("txtIcms").equals("") || req.getParameter("txtIcms") == null) ? "0" : req.getParameter("txtIcms");
                String desconto = (req.getParameter("txtVlDesconto").equals("") || req.getParameter("txtVlDesconto") == null) ? "0" : req.getParameter("txtVlDesconto");

                double d_vlUnitario = Double.parseDouble(vlUnitario);
                double d_quantidade = Double.parseDouble(quantidade);
                double d_icms = Double.parseDouble(icms);
                double d_desconto = Double.parseDouble(desconto);
                double d_total = (((d_vlUnitario * d_quantidade) - d_icms) - d_desconto);

                produtoMovimento.setDesconto(d_desconto);
                produtoMovimento.setIcms(d_icms);
                produtoMovimento.setValor(d_vlUnitario);
                produtoMovimento.setQuantidade(d_quantidade);
                produtoMovimento.setValorTotal(d_total);
                lstProdutoMovimento.add(produtoMovimento);
                movimento.setLstProdutoMovimento(lstProdutoMovimento);

            }

            if (movimentoDAO.atualizar(movimento))
            {
                // Atualizar quantidade em produtoEntrega
                movimento = movimentoDAO.listaUm(movimento);

                EntregaDAO entregaDAO = new EntregaDAO(conexao);
                ProdutoEntregaDAO produtoEntregaDAO = new ProdutoEntregaDAO(conexao);
                ColaboradorEntregaDAO colaboradorEntregaDAO = new ColaboradorEntregaDAO(conexao);
                MovimentoEntregaDAO movimentoEntregaDAO = new MovimentoEntregaDAO(conexao);

                Entrega entrega = new Entrega();
                entrega.setIdEntrega(colaboradorEntrega.getIdEntrega());
                entrega = entregaDAO.listaUm(entrega);

                
                ProdutoEntrega produtoEntregaNovo = new ProdutoEntrega();

                Iterator itProdutoEntrega = entrega.getLstProdutoEntrega().iterator();
                while (itProdutoEntrega.hasNext())
                {
                    ProdutoEntrega _produtoEntrega = (ProdutoEntrega)itProdutoEntrega.next();
                    // objeto que atualizará quantidades
                    
                    produtoEntregaNovo.setIdProdutoEntrega(_produtoEntrega.getIdProdutoEntrega());
                    produtoEntregaNovo.setIdProduto(_produtoEntrega.getIdProduto());
                    produtoEntregaNovo.setIdEntrega(_produtoEntrega.getIdEntrega());
                    produtoEntregaNovo.setDblQuantidade(_produtoEntrega.getDblQuantidade());

                        
                    // Listando colaboradores de entrega
                    Iterator itColaboradorEntrega = entrega.getLstColaboradorEntrega().iterator();
                    while (itColaboradorEntrega.hasNext())
                    {
                        ColaboradorEntrega _colaboradorEntrega = (ColaboradorEntrega)itColaboradorEntrega.next();
                        _colaboradorEntrega = colaboradorEntregaDAO.listaUm(_colaboradorEntrega);


                        Iterator itMovimentoEntrega = _colaboradorEntrega.getLstMovimentoEntrega().iterator();
                        while (itMovimentoEntrega.hasNext())
                        {
                            MovimentoEntrega _movimentoEntrega = (MovimentoEntrega)itMovimentoEntrega.next();
                            _movimentoEntrega = movimentoEntregaDAO.listaUm(_movimentoEntrega);

                            if (!_movimentoEntrega.getSituacao().equals("CA")) // cancelados não entram na conta
                            {
                                Movimento _movimento = (Movimento)_movimentoEntrega.getMovimento();
                                _movimento = movimentoDAO.listaUm(_movimento);

                                Iterator itProdutoMovimento = _movimento.getLstProdutoMovimento().iterator();
                                while (itProdutoMovimento.hasNext())
                                {
                                    ProdutoMovimento _produtoMovimento = (ProdutoMovimento)itProdutoMovimento.next();

                                    if(_produtoEntrega.getIdProduto() == _produtoMovimento.getIdProduto())
                                    {

                                        if (_movimento.getTipo().equals("BO"))
                                            produtoEntregaNovo.setDblQuantidadeBonus(produtoEntregaNovo.getDblQuantidadeBonus() + _produtoMovimento.getQuantidade());
                                        else if (_movimento.getTipo().equals("CO"))
                                            produtoEntregaNovo.setDblQuantidadeCortesia(produtoEntregaNovo.getDblQuantidadeCortesia() + _produtoMovimento.getQuantidade());
                                        else if (_movimento.getTipo().equals("VE"))
                                            produtoEntregaNovo.setDblQuantidadeVenda(produtoEntregaNovo.getDblQuantidadeVenda() + _produtoMovimento.getQuantidade());
                                        else if (_movimento.getTipo().equals("RE"))
                                            produtoEntregaNovo.setDblQuantidadeReposicao(produtoEntregaNovo.getDblQuantidadeReposicao() + _produtoMovimento.getQuantidade());
//                                        if (_movimento.getTipo().equals("BO"))
//                                            _produtoEntrega.setDblQuantidadeBonus(_produtoEntrega.getDblQuantidadeBonus() + _produtoMovimento.getQuantidade());
//                                        else if (_movimento.getTipo().equals("CO"))
//                                            _produtoEntrega.setDblQuantidadeCortesia(_produtoEntrega.getDblQuantidadeCortesia() + _produtoMovimento.getQuantidade());
//                                        else if (_movimento.getTipo().equals("VE"))
//                                            _produtoEntrega.setDblQuantidadeVenda(_produtoEntrega.getDblQuantidadeVenda() + _produtoMovimento.getQuantidade());
//                                        else if (_movimento.getTipo().equals("RE"))
//                                            _produtoEntrega.setDblQuantidadeReposicao(_produtoEntrega.getDblQuantidadeReposicao() + _produtoMovimento.getQuantidade());                                        


                                    }

                                }
                            }

                        }


//                        // atualizando produto da entrega
//                        double qtSaida = 0;
//                        qtSaida = _produtoEntrega.getDblQuantidadeAvaria() + _produtoEntrega.getDblQuantidadeBonus() + _produtoEntrega.getDblQuantidadeCortesia() + _produtoEntrega.getDblQuantidadeReposicao() + _produtoEntrega.getDblQuantidadeVenda();
//                        _produtoEntrega.setDblSaldo(_produtoEntrega.getDblQuantidade() - qtSaida);
//                        if(produtoEntregaDAO.atualizar(_produtoEntrega))
//                            _produtoEntrega = produtoEntregaDAO.listaUm(_produtoEntrega);

                    }

                }

                // atualizando produto da entrega
                double qtSaida = 0;
                qtSaida = produtoEntregaNovo.getDblQuantidadeAvaria() + produtoEntregaNovo.getDblQuantidadeBonus() + produtoEntregaNovo.getDblQuantidadeCortesia() + produtoEntregaNovo.getDblQuantidadeReposicao() + produtoEntregaNovo.getDblQuantidadeVenda();
                produtoEntregaNovo.setDblSaldo(produtoEntregaNovo.getDblQuantidade() - qtSaida);
                produtoEntregaDAO.atualizar(produtoEntregaNovo);
                                        
                sessao.setAttribute("avisoErro", "Movimento atualizado");
                sessao.setAttribute("tipoAviso","alert alert-success");
                sessao.setAttribute("pagOrigemErro", "FabricaGelo.MovimentoEntrega.AcaoAbreMovimentoEntrega");
                pagRetorno = "visao/erro.jsp";  

            }            
            
            
        }
        
        sessao.setAttribute("movimentoEntrega", movimentoEntrega);
        sessao.setAttribute("colaboradorEntrega", colaboradorEntrega);        
        sessao.setAttribute("produtoMovimento", null);  
        
        return pagRetorno;
    }
}
