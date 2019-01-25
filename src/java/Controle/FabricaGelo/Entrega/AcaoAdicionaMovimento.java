/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Entrega;

import Bean.Colaborador;
import Bean.ColaboradorEntrega;
import Bean.ColaboradorProduto;
import Bean.Entrega;
import Bean.Movimento;
import Bean.MovimentoEntrega;
import Bean.Produto;
import Bean.ProdutoEntrega;
import Bean.ProdutoMovimento;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.ColaboradorDAO;
import DAO.ColaboradorEntregaDAO;
import DAO.EntregaDAO;
import DAO.MovimentoDAO;
import DAO.MovimentoEntregaDAO;
import DAO.ProdutoDAO;
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
public class AcaoAdicionaMovimento extends Acao{ 
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String pagRetorno = "FabricaGelo.MovimentoEntrega.AcaoAbreMovimentoEntrega";
        
        EntregaDAO entregaDAO = new EntregaDAO(conexao);
        Entrega entrega = (Entrega)sessao.getAttribute("entrega");
        ColaboradorEntrega colaboradorEntrega = (ColaboradorEntrega)sessao.getAttribute("colaboradorEntrega");
        String tipoMovimento = (req.getParameter("cmbTipoMovimento").equals("") || req.getParameter("cmbTipoMovimento") == null) ? "" : req.getParameter("cmbTipoMovimento");
        
        if (entrega == null)
        {
            sessao.setAttribute("avisoErro", "Entrega não selecionada");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Entrega.AcaoEncerrarRota");
            pagRetorno = "visao/erro.jsp";              
        }
        else if (colaboradorEntrega == null)
        {
            sessao.setAttribute("avisoErro", "Cliente não selecionado");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Entrega.AcaoEncerrarRota");
            pagRetorno = "visao/erro.jsp";             
        }
        else if (tipoMovimento.equals(""))
        {
            sessao.setAttribute("avisoErro", "Tipo de movimento não selecionado");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Entrega.AcaoEncerrarRota");
            pagRetorno = "visao/erro.jsp";             
        }
        else
        {
            double vlIcms = 0;
            double vlUnit = 0;
            double vlDesc = 0;
            boolean prodColaborador = false;
            
            // ColaboradorEntrega vai para a sessão
            // pois visa a criação de objeto MovimentoEntrega
            // após guarda de Movimento
            ColaboradorEntregaDAO colaboradorEntregaDAO = new ColaboradorEntregaDAO(conexao);
            colaboradorEntrega = colaboradorEntregaDAO.listaUm(colaboradorEntrega);
            
            // para identificar os produtos relacionados ao colaborador
            ColaboradorDAO colaboradorDAO = new ColaboradorDAO(conexao);
            Colaborador colaborador = (Colaborador)colaboradorEntrega.getCliente();
            colaborador = colaboradorDAO.listaUm(colaborador);
            

            
            // carga dos produtos da entrega
            // para o movimento
            List<ProdutoMovimento> lstProdutoMovimento = new ArrayList<ProdutoMovimento>();
            ProdutoDAO produtoDAO = new ProdutoDAO(conexao);
            List<ProdutoEntrega> lstProdutoEntrega = entrega.getLstProdutoEntrega();
            Iterator itProdutoEntrega = lstProdutoEntrega.iterator();
            while (itProdutoEntrega.hasNext())
            {
                prodColaborador = false;
                
                ProdutoEntrega produtoEntrega = (ProdutoEntrega)itProdutoEntrega.next();
                Produto produto = new Produto();
                produto.setIdProduto(produtoEntrega.getIdProduto());
                produto = produtoDAO.listaUm(produto);
                
                // verificando se produto está na lista de produtos
                // do colaborador
                Iterator itProdutoColaborador = colaborador.getLstColaboradorProduto().iterator();
                while (itProdutoColaborador.hasNext())
                {
                    ColaboradorProduto colaboradorProduto = (ColaboradorProduto)itProdutoColaborador.next();
                    if (produto.getIdProduto() == colaboradorProduto.getIdProduto())
                    {
                        prodColaborador = true;
                        vlIcms = colaboradorProduto.getIcms();
                        vlUnit = colaboradorProduto.getValor();
                        vlDesc = colaboradorProduto.getDesconto();
                    }
                }
                
                ProdutoMovimento produtoMovimento = new ProdutoMovimento();
                produtoMovimento.setProduto(produto);
                if (!prodColaborador)
                    produtoMovimento.setValor(produto.getVlUnitario());
                else
                {
                    produtoMovimento.setIcms(vlIcms);
                    produtoMovimento.setValor(vlUnit);
                    produtoMovimento.setDesconto(vlDesc);
                }
                
                lstProdutoMovimento.add(produtoMovimento);

            }
            
            // criação do objeto movimento para ficar na sessão e
            // posterior guarda de dados
            MovimentoEntregaDAO movimentoEntregaDAO = new MovimentoEntregaDAO(conexao);
            MovimentoEntrega movimentoEntrega = new MovimentoEntrega();
            
            MovimentoDAO movimentoDAO = new MovimentoDAO(conexao);
            Movimento movimento = new Movimento();
            movimento.setColaborador(colaborador);
            movimento.setDtLancamento(entrega.getData());
            movimento.setDtEntrega(entrega.getData());
            movimento.setTipo(tipoMovimento);  
            movimento.setSituacao("AB");
            movimento.setLstProdutoMovimento(lstProdutoMovimento);
            if (movimentoDAO.atualizar(movimento))
            {
                movimento.setIdMovimento(movimentoDAO.getIdentity());
                movimentoEntrega.setIdColaboradorEntrega(colaboradorEntrega.getIdColaboradorEntrega());
                movimentoEntrega.setIdMovimento(movimento.getIdMovimento());
                movimentoEntregaDAO.atualizar(movimentoEntrega);
                if (movimentoEntregaDAO.atualizar(movimentoEntrega))
                    movimentoEntrega.setIdMovimentoEntrega(movimentoEntregaDAO.getIdentity());
            }
            
            sessao.setAttribute("movimentoEntrega", movimentoEntrega);
            sessao.setAttribute("colaboradorEntrega", colaboradorEntrega);
            sessao.setAttribute("movimentoEntrega", movimento);
            
        }
        
        return pagRetorno;
    }
}
