/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Entrega;


import Bean.AvariaEntrega;
import Bean.Entrega;
import Bean.Movimento;
import Bean.CustoEntrega;
import Bean.Equipamento;
import Bean.Produto;
import Bean.ProdutoCamara;
import Bean.ProdutoEntrega;
import Bean.SaidaCamara;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.EntregaDAO;
import DAO.EquipamentoDAO;
import DAO.ProdutoDAO;
import DAO.SaidaCamaraDAO;
import com.sun.javafx.scene.control.skin.VirtualFlow;
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
public class AcaoGravaEntrega extends Acao
{

    public String executa(HttpServletRequest req, HttpServletResponse res)
        throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String pagRetorno = "";
        
        Entrega entrega = (Entrega)sessao.getAttribute("entrega");
        EntregaDAO entregaDAO = new EntregaDAO(conexao);
        entrega = entregaDAO.listaUm(entrega);
        
        if (entrega == null)
        {
            sessao.setAttribute("avisoErro", "Rota inexistente.");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Entrega.AcaoListarEntrega");
            pagRetorno = "visao/erro.jsp";             
        }
        else
        {
            sessao.setAttribute("avisoErro", "Rota atualizada");
            sessao.setAttribute("tipoAviso","alert alert-success");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Entrega.AcaoNovaRota");
            pagRetorno = "visao/erro.jsp";              
        }
              
 
        
        return pagRetorno;
        
//        List<ProdutoEntrega> lstProdutoEntrega = null;

//        List<CustoEntrega> lstCustoEntrega = null;
//        List<AvariaEntrega> lstAvariaEntrega = null;
//        List<Abastecimento> lstAbastecimento = null;
//        ProdutoEntrega produtoEntrega = (ProdutoEntrega)sessao.getAttribute("produtoEntrega");
//        AvariaEntrega avariaEntrega = (AvariaEntrega)sessao.getAttribute("avariaEntrega");
//        CustoEntrega abastecimento = (CustoEntrega)sessao.getAttribute("abastecimento");
//        CustoEntrega custoEntrega = (CustoEntrega)sessao.getAttribute("custoEntrega");
//        Entrega entrega = (Entrega)sessao.getAttribute("entrega");
//        
//
//        // campos da entrega
//        String profissional = (req.getParameter("cmbProfissional").equals("") || req.getParameter("cmbProfissional") == null) ? "0" : req.getParameter("cmbProfissional");
//        String idVeiculo = (req.getParameter("cmbVeiculo").equals("") || req.getParameter("cmbVeiculo") == null) ? "0" : req.getParameter("cmbVeiculo");
//        String dtEntrega = (req.getParameter("txtDtEntrega").equals("") || req.getParameter("txtDtEntrega") == null) ? "1900-01-01" : req.getParameter("txtDtEntrega");
//        String kmInicial = (req.getParameter("txtKmInicial").equals("") || req.getParameter("txtKmInicial") == null) ? "0" : req.getParameter("txtKmInicial");
//        String kmFinal = (req.getParameter("txtKmFinal").equals("") || req.getParameter("txtKmFinal") == null) ? "0" : req.getParameter("txtKmFinal");
//        String hrSaida = (req.getParameter("txtHrSaida").equals("") || req.getParameter("txtHrSaida") == null) ? "00:00" : req.getParameter("txtHrSaida");
//        String hrChegada = (req.getParameter("txtHrChegada").equals("") || req.getParameter("txtHrChegada") == null) ? "00:00" : req.getParameter("txtHrChegada");
//        
//        if (profissional.equals("0"))
//        {
//            sessao.setAttribute("avisoErro", "Profissional não selecionado");
//            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Entrega.AcaoAbreEntrega");
//            pagRetorno = "visao/erro.jsp";                
//        }
//        else if (idVeiculo.equals("0"))
//        {
//            sessao.setAttribute("avisoErro", "Rota não selecionado");
//            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Entrega.AcaoAbreEntrega");
//            pagRetorno = "visao/erro.jsp";                
//        }            
//        if ((entrega != null) && (entrega.getSituacao().equals("FI")))
//        {
//            sessao.setAttribute("avisoErro", "Rota finalizada");
//            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Entrega.AcaoAbreEntrega");
//            pagRetorno = "visao/erro.jsp";             
//        }
//        if ((entrega != null) && (entrega.getSituacao().equals("ET")))
//        {
//            sessao.setAttribute("avisoErro", "Rota em trânsito");
//            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Entrega.AcaoAbreEntrega");
//            pagRetorno = "visao/erro.jsp";             
//        }
//        if ((entrega != null) && (entrega.getSituacao().equals("CH")))
//        {
//            sessao.setAttribute("avisoErro", "Rota em retorno");
//            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Entrega.AcaoAbreEntrega");
//            pagRetorno = "visao/erro.jsp";             
//        }
//        else
//        {
//
//            if (entrega == null)
//                entrega = new Entrega();
//
//            
//            // campos do custo de entrega
//            String descCusto = (req.getParameter("txtDescCusto").equals("") || req.getParameter("txtDescCusto") == null) ? "" : req.getParameter("txtDescCusto");
//            String valorCusto = (req.getParameter("txtValor").equals("") || req.getParameter("txtValor") == null) ? "0" : req.getParameter("txtValor");            
//            if (!descCusto.equals(""))
//            {
//                lstCustoEntrega = new ArrayList<CustoEntrega>();
//                if (custoEntrega == null)
//                    custoEntrega = new CustoEntrega();
//                custoEntrega.setDescricao(descCusto);
//                custoEntrega.setValor(Double.parseDouble(valorCusto));
//                lstCustoEntrega.add(custoEntrega);
//            }
// 
//            // campos do abastecimento
//            if (abastecimento != null)
//            {
//                lstAbastecimento = new ArrayList<Abastecimento>();
//                String vlCombustivel = (req.getParameter("txtVlLitro").equals("") || req.getParameter("txtVlLitro") == null) ? "0" : req.getParameter("txtVlLitro");
//                String qtCombustivel = (req.getParameter("txtQtLitro").equals("") || req.getParameter("txtQtLitro") == null) ? "0" : req.getParameter("txtQtLitro");
//
//                abastecimento.setVlUnitario(Double.parseDouble(vlCombustivel));
//                abastecimento.setQuantidade(Double.parseDouble(qtCombustivel));
//                lstAbastecimento.add(abastecimento);
//            }
//     
//            
//            // descobrindo carga e km inicial do veículo
//            EquipamentoDAO veiculoDAO = new EquipamentoDAO(conexao);
//            Equipamento veiculo = new Equipamento();
//            veiculo.setIdEquipamento(Integer.parseInt(idVeiculo));
//            veiculo = veiculoDAO.listaUm(veiculo);
//            
//           
////            // produto da entrega
////            if (produtoEntrega != null)
////            {    
////                lstProdutoEntrega = new ArrayList<ProdutoEntrega>();
////                
////                String quantidadeProduto = (req.getParameter("txtQuantidade").equals("") || req.getParameter("txtQuantidade") == null) ? "0" : req.getParameter("txtQuantidade");
////                String quantidadeBonus = (req.getParameter("txtQtBonus").equals("") || req.getParameter("txtQtBonus") == null) ? "0" : req.getParameter("txtQtBonus");
////                String quantidadeCortesia = (req.getParameter("txtQtCortesia").equals("") || req.getParameter("txtQtCortesia") == null) ? "0" : req.getParameter("txtQtCortesia");
////                String quantidadeAvaria = (req.getParameter("txtQtAvaria").equals("") || req.getParameter("txtQtAvaria") == null) ? "0" : req.getParameter("txtQtAvaria");
////                String quantidadeReposicao = (req.getParameter("txtQtReposicao").equals("") || req.getParameter("txtQtReposicao") == null) ? "0" : req.getParameter("txtQtReposicao");
////
////                produtoEntrega.setSituacao("A");
////                produtoEntrega.setDblQuantidade(Double.parseDouble(quantidadeProduto));
////                produtoEntrega.setDblQuantidadeAvaria(Double.parseDouble(quantidadeAvaria));
////                produtoEntrega.setDblQuantidadeCortesia(Double.parseDouble(quantidadeCortesia));
////                produtoEntrega.setDblQuantidadeBonus(Double.parseDouble(quantidadeBonus));
////                produtoEntrega.setDblQuantidadeReposicao(Double.parseDouble(quantidadeReposicao));
////                // avaria entrega
////                if (avariaEntrega != null)
////                {
////                    lstAvariaEntrega = new ArrayList<AvariaEntrega>();
////                    String qtAvariaPE = (req.getParameter("txtQtAvariaPE").equals("") || req.getParameter("txtQtAvariaPE") == null) ? "0" : req.getParameter("txtQtAvariaPE");
////                    avariaEntrega.setQuantidade(Double.parseDouble(qtAvariaPE));
////                    lstAvariaEntrega.add(avariaEntrega);
////                    produtoEntrega.setLstAvariaEntrega(lstAvariaEntrega);
////
////                }
////                lstProdutoEntrega.add(produtoEntrega); 
////            }            
// 
//            entrega.setDataFormatada(dtEntrega);
//            entrega.setKmInicial(veiculo.getQuilometragem());
//            entrega.setKmFinal((Double.parseDouble(kmFinal)));
//            entrega.setHrSaida(hrSaida);
//            entrega.setHrChegada(hrChegada);
//            entrega.setSituacao("CD");
//            entrega.setLstCustoEntrega(lstCustoEntrega);
//            entrega.setLstProdutoEntrega(lstProdutoEntrega);
//            entrega.setLstAbastecimento(lstAbastecimento);
//
//            if(entregaDAO.atualizar(entrega))
//                entrega = entregaDAO.listaUm(entrega);
//
//            sessao.setAttribute("entrega",entrega);
//            sessao.setAttribute("lstProdutoEntrega",entrega.getLstProdutoEntrega());
//            sessao.setAttribute("lstCustoEntrega",entrega.getLstCustoEntrega());
//            sessao.setAttribute("lstAbastecimento",entrega.getLstAbastecimento());
//            sessao.setAttribute("custoEntrega",null);
//            sessao.setAttribute("produtoEntrega",produtoEntrega);
//            sessao.setAttribute("avariaEntrega",null);
//            sessao.setAttribute("abastecimento",null);
//        }
//        
//        if (pagRetorno.equals(""))
//            pagRetorno = "FabricaGelo.Entrega.AcaoAbreEntrega";
//        
//        return pagRetorno;
    }
}
