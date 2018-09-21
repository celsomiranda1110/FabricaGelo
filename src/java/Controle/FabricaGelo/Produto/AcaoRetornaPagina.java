/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Produto;

import Bean.Colaborador;
import Bean.Producao;
import Bean.Produto;
import Bean.ProdutoMovimento;
import Controle.FabricaGelo.Gerais.Acao;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoRetornaPagina extends Acao
{
    
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String pagRetorno = (String)sessao.getAttribute("pagRetorno");
        Produto produto = (Produto)sessao.getAttribute("produto");
        Colaborador colaborador = (Colaborador)sessao.getAttribute("colaborador");
        
        if (pagRetorno != null)
        {
            if (pagRetorno.equals("FabricaGelo.Colaborador.AcaoAbreColaborador"))
            {
                sessao.setAttribute("produto", produto);
                sessao.setAttribute("colaborador", colaborador);
            }
            else if (pagRetorno.equals("FabricaGelo.Venda.AcaoAbreVenda"))
            {
                ProdutoMovimento produtoMovimento = new ProdutoMovimento();
                produtoMovimento.setProduto(produto);
                produtoMovimento.setValor(produto.getVlUnitario());
                sessao.setAttribute("vendaProduto", produtoMovimento);
            }
            else if (pagRetorno.equals("FabricaGelo.Compra.AcaoAbreCompra"))
            {
                ProdutoMovimento produtoMovimento = new ProdutoMovimento();
                produtoMovimento.setProduto(produto);
                produtoMovimento.setValor(produto.getVlUnitario());
                sessao.setAttribute("compraProduto", produtoMovimento);
            } 
            else if (pagRetorno.equals("FabricaGelo.Cortesia.AcaoAbreCortesia"))
            {
                ProdutoMovimento produtoMovimento = new ProdutoMovimento();
                produtoMovimento.setProduto(produto);
                produtoMovimento.setValor(produto.getVlUnitario());
                sessao.setAttribute("cortesiaProduto", produtoMovimento);
            } 
            else if (pagRetorno.equals("FabricaGelo.Aluguel.AcaoAbreAluguel"))
            {
                ProdutoMovimento produtoMovimento = new ProdutoMovimento();
                produtoMovimento.setProduto(produto);
                produtoMovimento.setValor(produto.getVlUnitario());
                sessao.setAttribute("aluguelProduto", produtoMovimento);
            }
            else if (pagRetorno.equals("FabricaGelo.Bonificacao.AcaoAbreBonificacao"))
            {
                ProdutoMovimento produtoMovimento = new ProdutoMovimento();
                produtoMovimento.setProduto(produto);
                produtoMovimento.setValor(produto.getVlUnitario());
                sessao.setAttribute("bonificacaoProduto", produtoMovimento);
            }  
            else if (pagRetorno.equals("FabricaGelo.Producao.AcaoAbreProducao"))
            {
                Producao producao = (Producao)sessao.getAttribute("producao");
                producao.setProduto(produto);
                sessao.setAttribute("producao", producao);
                
            }
            
        }
        else
        {
            sessao.setAttribute("produto", null);
            sessao.setAttribute("colaborador", null);
            pagRetorno = "FabricaGelo.Produto.AcaoListarProduto";
        }
        return pagRetorno;
    }
}
