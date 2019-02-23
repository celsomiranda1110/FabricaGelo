/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Produto;

import Bean.Colaborador;
import Bean.ColaboradorProduto;
import Bean.Entrega;
import Bean.MaquinaProducao;
import Bean.Movimento;
import Bean.Produto;
import Bean.ProdutoCamara;
import Bean.ProdutoEntrega;
import Bean.ProdutoMovimento;
import Bean.Producao;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.ColaboradorDAO;
import DAO.EntregaDAO;
import DAO.MovimentoDAO;
import DAO.ProducaoDAO;
import DAO.ProdutoCamaraDAO;
import DAO.ProdutoDAO;
import java.sql.Connection;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoDeletaProduto extends Acao
{
    
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);

        String pagRetorno = "FabricaGelo.Produto.AcaoAbreProduto";
        
        Produto produto = (Produto)sessao.getAttribute("produto");
        
        if (produto == null)
        {
            sessao.setAttribute("avisoErro", "Produto não selecionado");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Produto.AcaoAbreProduto");
            pagRetorno = "visao/erro.jsp";              
        }
        else
        {
        
            boolean bolApaga = true;
            
            // verificando relacionamentos do produto com outras tabelas
            ColaboradorDAO colaboradorDAO = new ColaboradorDAO(conexao);
            Iterator itColaborador = colaboradorDAO.listaTodos(null).iterator();
            while (itColaborador.hasNext())
            {
                Colaborador colaborador = (Colaborador)itColaborador.next();
                colaborador = colaboradorDAO.listaUm(colaborador);
                Iterator itColaboradorProduto = colaborador.getLstColaboradorProduto().iterator();
                while (itColaboradorProduto.hasNext())
                {
                    ColaboradorProduto colaboradorProduto = (ColaboradorProduto)itColaboradorProduto.next();
                    if (colaboradorProduto.getIdProduto() == produto.getIdProduto())
                    {
                        bolApaga = false;
                        break;
                    }
                        
                }
                if (!bolApaga)
                    break;
            }
            
            if (bolApaga)
            {
                EntregaDAO entregaDAO = new EntregaDAO(conexao);
                Iterator itEntrega = entregaDAO.listaTodos(null).iterator();
                while (itEntrega.hasNext())
                {
                    Entrega entrega = (Entrega)itEntrega.next();
                    entrega = entregaDAO.listaUm(entrega);
                    Iterator itProdutoEntrega = entrega.getLstProdutoEntrega().iterator();
                    while (itProdutoEntrega.hasNext())
                    {
                        ProdutoEntrega produtoEntrega = (ProdutoEntrega)itProdutoEntrega.next();
                        if (produtoEntrega.getIdProduto() == produto.getIdProduto())
                        {
                            bolApaga = false;
                            break;
                        }
                    }
                    if (!bolApaga)
                        break;
                    
                }
                
            }
            
            if (bolApaga)
            {
                MovimentoDAO movimentoDAO = new MovimentoDAO(conexao);
                Iterator itMovimento = movimentoDAO.listaTodos(null).iterator();
                while (itMovimento.hasNext())
                {
                    Movimento movimento = (Movimento)itMovimento.next();
                    movimento = movimentoDAO.listaUm(movimento);
                    Iterator itProdutoMovimento = movimento.getLstProdutoMovimento().iterator();
                    while (itProdutoMovimento.hasNext())
                    {
                        ProdutoMovimento produtoMovimento = (ProdutoMovimento)itProdutoMovimento.next();
                        if (produtoMovimento.getIdProduto() == produto.getIdProduto())
                        {
                            bolApaga = false;
                            break;
                        }
                        
                    }
                    if (!bolApaga)
                        break;
                }
            }
            
            if (bolApaga)
            {
                ProdutoCamaraDAO produtoCamaraDAO = new ProdutoCamaraDAO(conexao);
                ProdutoCamara produtoCamara = new ProdutoCamara();
                produtoCamara.setIdProduto(produto.getIdProduto());
                if (produtoCamaraDAO.listaTodos(produtoCamara).size() > 0)
                    bolApaga = false;
            }
            
            if (bolApaga)
            {
                ProducaoDAO producaoDAO = new ProducaoDAO(conexao);
                Iterator itProducao = producaoDAO.listaTodos().iterator();
                while (itProducao.hasNext())
                {
                    Producao producao = (Producao)itProducao.next();
                    producao = producaoDAO.listaUm(producao);
                    
                    if (producao.getIdProduto() == produto.getIdProduto())
                    {
                        bolApaga = false;
                        break;
                    }
                    else
                    {
                        Iterator itMaquinaProducao = producao.getLstMaquinaProducao().iterator();
                        while (itMaquinaProducao.hasNext())
                        {
                            MaquinaProducao maquinaProducao = (MaquinaProducao)itMaquinaProducao.next();
                            if (maquinaProducao.getIdProduto() == produto.getIdProduto())
                            {
                                bolApaga = false;
                                break;
                            }
                        }
                    }
                    if (!bolApaga)
                        break;
                }
            } 
            
            if (!bolApaga)
            {
                sessao.setAttribute("avisoErro", "Produto está relacionado com movimentos. Inative para preservação dos dados");
                sessao.setAttribute("tipoAviso","alert alert-danger");
                sessao.setAttribute("pagOrigemErro", "FabricaGelo.Produto.AcaoAbreProduto");
                pagRetorno = "visao/erro.jsp";              
            }
            else
            {
                ProdutoDAO produtoDAO = new ProdutoDAO(conexao);
                if (produtoDAO.delete(produto))
                {
                    sessao.setAttribute("avisoErro", "Produto deletado com sucesso");
                    sessao.setAttribute("tipoAviso","alert alert-success");
                    sessao.setAttribute("pagOrigemErro", "FabricaGelo.Produto.AcaoListarProduto");
                    pagRetorno = "visao/erro.jsp";              

                }
            }
        }
        
        sessao.setAttribute("produto", produto);
        
        return pagRetorno;
    }
    
}