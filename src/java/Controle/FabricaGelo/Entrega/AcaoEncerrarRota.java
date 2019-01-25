/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Entrega;

import Bean.Avaria;
import Bean.AvariaEntrega;
import Bean.Colaborador;
import Bean.ColaboradorEntrega;
import Bean.Entrega;
import Bean.ProdutoEntrega;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.AvariaDAO;
import DAO.ColaboradorDAO;
import DAO.EntregaDAO;
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
public class AcaoEncerrarRota extends Acao
{
    
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String pagRetorno = "visao/rotaFinalizar.jsp";
        
        List<Avaria> lstAvaria = new ArrayList<Avaria>();
        Entrega entrega = (Entrega)sessao.getAttribute("entrega");
        ProdutoEntrega produtoEntrega = (ProdutoEntrega)sessao.getAttribute("produtoEntrega");
        ColaboradorEntrega colaboradorEntrega = (ColaboradorEntrega)sessao.getAttribute("colaboradorEntrega");        
        
        if (entrega == null)
        {
            sessao.setAttribute("avisoErro", "Rota não selecionada");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Entrega.AcaoEncerrarRota");
            pagRetorno = "visao/erro.jsp";             
        }
        else
        {
            EntregaDAO entregaDAO = new EntregaDAO(conexao);
            entrega = entregaDAO.listaUm(entrega);

            AvariaDAO avariaDAO = new AvariaDAO(conexao);
            lstAvaria = avariaDAO.listaTodos();            
            
            if (produtoEntrega != null)
            {
                ProdutoEntregaDAO produtoEntregaDAO = new ProdutoEntregaDAO(conexao);
                if (produtoEntrega == null)
                    produtoEntrega = new ProdutoEntrega();
                else
                {
                    produtoEntrega = produtoEntregaDAO.listaUm(produtoEntrega);

                    List<Avaria> lstRetorno = lstAvaria;
                    lstAvaria = new ArrayList<Avaria>();

                    if (produtoEntrega.getLstAvariaEntrega() != null)
                    {
                        boolean incluiAvaria = true;
                        Iterator itAvaria = lstRetorno.iterator();
                        while (itAvaria.hasNext())
                        {
                            incluiAvaria = true;
                            Avaria avaria = (Avaria)itAvaria.next();
                            Iterator itAvariaEntrega = produtoEntrega.getLstAvariaEntrega().iterator();
                            while (itAvariaEntrega.hasNext())
                            {
                                AvariaEntrega avariaEntrega = (AvariaEntrega)itAvariaEntrega.next();
                                if (avaria.getIdAvaria() == avariaEntrega.getIdAvaria())
                                    incluiAvaria = false;
                            }
                            if (incluiAvaria)
                                lstAvaria.add(avaria);
                        }
                    }  

                }
                
            }
            
            
        }
        
       
        sessao.setAttribute("entrega", entrega);
        sessao.setAttribute("lstColaboradorEntrega", entrega.getLstColaboradorEntrega());
        sessao.setAttribute("lstProdutoEntrega", entrega.getLstProdutoEntrega());
        sessao.setAttribute("lstAbastecimento", entrega.getLstAbastecimento());
        sessao.setAttribute("lstCustoEntrega", entrega.getLstCustoEntrega());
        sessao.setAttribute("produtoEntrega", produtoEntrega);
        sessao.setAttribute("colaboradorEntrega", colaboradorEntrega);
        sessao.setAttribute("lstAvaria", lstAvaria);
        sessao.setAttribute("lstAvariaEntrega", (produtoEntrega != null ? produtoEntrega.getLstAvariaEntrega() : null));

        
        return pagRetorno;
        
    }
}
