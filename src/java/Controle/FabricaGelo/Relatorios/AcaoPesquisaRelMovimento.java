/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controle.FabricaGelo.Relatorios;


import Bean.Colaborador;
import Bean.Produto;
import Controle.FabricaGelo.Gerais.Acao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Miranda
 */
public class AcaoPesquisaRelMovimento extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res)
        throws Exception
    {
//        List lstMovimento = new ArrayList();
//        List lstMovimentoApre = new ArrayList();
//        Connection conexao = (Connection)req.getAttribute("connection");
//        HttpSession sessao = req.getSession(false);
//
//        Empresa empresa = (Empresa)sessao.getAttribute("empresa");
//        DocumentoDAO movimentoDAO = new DocumentoDAO(conexao);
//        
//        // Identificando os tipos de movimentos a serem pesquisados
//        String formaMovimento = "";
//        String ckAluguel = "";
//        String ckVenda = "";
//        String ckCompra = "";
//        if (req.getParameter("ck_ALUGUEL") != null)
//        {
//            if (formaMovimento != "")
//                formaMovimento += ",";
//            formaMovimento += "'ALUGUEL'";
//            ckAluguel = "checked";
//        }
//        if (req.getParameter("ck_VENDA") != null)
//        {
//            if (formaMovimento != "")
//                formaMovimento += ",";
//            formaMovimento += "'VENDA'";   
//            ckVenda = "checked";
//        }
//        if (req.getParameter("ck_COMPRA") != null)
//        {
//            if (formaMovimento != "")
//                formaMovimento += ",";
//            formaMovimento += "'COMPRA'"; 
//            ckCompra = "checked";
//        }       
//        
//
//
//        int opSelecaoSituacao = Integer.parseInt(req.getParameter("cmbSituacaoMovimento"));
//        String selOp1 = "";
//        String selOp2 = "";
//        String selOp3 = "";
//        if (opSelecaoSituacao == 1) selOp1 = "selected";
//        if (opSelecaoSituacao == 2) selOp2 = "selected";
//        if (opSelecaoSituacao == 3) selOp3 = "selected";
//        
//        Documento documento = new Documento();
//        documento.setIdEmpresa(empresa.getIdEmpresa());
//        documento.setForma(formaMovimento);
//        documento.setEstado(opSelecaoSituacao);
//        
//        movimentoDAO.setDataInicial(req.getParameter("txtPer1"));
//        movimentoDAO.setDataFinal(req.getParameter("txtPer2"));
////        movimentoDAO.setCampoInteger(opSelecaoSituacao);
////        movimentoDAO.setIdEmpresa(Integer.valueOf(empresa.getIdEmpresa()));
//        lstMovimento = movimentoDAO.listaTodos(documento);
//        
//        // calculando movimentos dos itens dos movimentos
//        Iterator it = lstMovimento.iterator();
//        List<Produto> lstItensGerais = new ArrayList();
//        List<Produto> lstListaFinal = new ArrayList();
//        
//        while (it.hasNext())
//        {
//            Documento movimento = (Documento)it.next();
//            movimento = movimentoDAO.listaUm(movimento);
//            List<ItensDocumento> lstItens = movimento.getLstItensDocumento();
//            
//            if (lstItens != null)
//            {
//                
//                if (!movimento.getDescEstado().equals("CANCELADO"))
//                {
//                
//                    lstMovimentoApre.add(movimento);
//                    
//                    Iterator itItens = lstItens.iterator();
//
//                    while (itItens.hasNext())
//                    {
//                        ItensDocumento itemDocumento = (ItensDocumento)itItens.next();
//                        Produto _produtoItem = (Produto)itemDocumento.getProduto();
//                        if (movimento.getForma().equals("COMPRA"))
//                            _produtoItem.setQtEntrada(itemDocumento.getQtArtigo());
//                        else
//                            _produtoItem.setQtSaida(itemDocumento.getQtArtigo());
//
//                        lstItensGerais.add(_produtoItem);
//                    }
//                    
//                }
//            }
//        }
//
//        
//        if (lstItensGerais != null)
//        {
//        
//            Iterator itProduto = lstItensGerais.iterator();
//
//
//            while (itProduto.hasNext())
//            {
//                Produto _prodComparar = (Produto)itProduto.next();
//                boolean existeFinal = false;
//
//                // procura do produto a ser comparado dentro da lista final
//                // simula o contains
//                Iterator itFinal = lstListaFinal.iterator();
//                while (itFinal.hasNext())
//                {
//                    Produto _produtoFinal = (Produto)itFinal.next();
//                    if (_produtoFinal.getIdProduto() == _prodComparar.getIdProduto())
//                        existeFinal = true;
//                }
//
//                if (!existeFinal)
//                {
//                    double dblQtSaida = 0;
//                    double dblQtEntrada = 0;                
//                    Iterator itProdutoComp = lstItensGerais.iterator();
//
//                    while (itProdutoComp.hasNext())
//                    {
//                        Produto _prodComparado = (Produto)itProdutoComp.next();
//                        if (_prodComparar.getIdProduto() == _prodComparado.getIdProduto())
//                        {
//                            dblQtSaida += _prodComparado.getQtSaida();
//                            dblQtEntrada += _prodComparado.getQtEntrada();
//                         }
//                    }
//
//                    _prodComparar.setQtSaida(dblQtSaida);
//                    _prodComparar.setQtEntrada(dblQtEntrada);
//                    lstListaFinal.add(_prodComparar);                  
//                }
//
//            }
//        }
//        // fim do cálculo de quantidade de ítens
//        
//        sessao.setAttribute("avisoGerMovimento", null);
//        sessao.setAttribute("lstMovimentos", lstMovimentoApre);
//        sessao.setAttribute("lstItensMovimentos", lstListaFinal);
//        // campos de pesquisa
//        sessao.setAttribute("ckVlAluguel", ckAluguel);
//        sessao.setAttribute("ckVlVenda", ckVenda);
//        sessao.setAttribute("ckVlCompra", ckCompra);
//        sessao.setAttribute("selOp1", selOp1);
//        sessao.setAttribute("selOp2", selOp2);
//        sessao.setAttribute("selOp3", selOp3); 
//        sessao.setAttribute("DtPer1", req.getParameter("txtPer1"));
//        sessao.setAttribute("DtPer2", req.getParameter("txtPer2"));         
//        
//        
//        return "visao/gerMovimento.jsp";
        return "";
    }    
}
