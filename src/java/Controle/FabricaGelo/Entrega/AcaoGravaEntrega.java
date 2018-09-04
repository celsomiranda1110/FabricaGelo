/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Entrega;

import Bean.CustoEntrega;
import Bean.Entrega;
import Bean.Movimento;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.EntregaDAO;
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
        EntregaDAO entregaDAO = new EntregaDAO(conexao);
        
        Movimento movimento = (Movimento)sessao.getAttribute("movimento");
        Entrega entrega = (Entrega)sessao.getAttribute("entrega");
        CustoEntrega custoEntrega = (CustoEntrega)sessao.getAttribute("custoEntrega");

        if (entrega == null)
            entrega = new Entrega();
        
        if (entrega.getSituacao().equals("EN"))
        {
            sessao.setAttribute("avisoErro", "Entrega realizada");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Entrega.AcaoAbreEntrega");
            pagRetorno = "visao/erro.jsp";             
        }
        else
        {
            List<CustoEntrega> lstCustoEntrega = entrega.getLstCustoEntrega();
            if (lstCustoEntrega == null)
                lstCustoEntrega = new ArrayList<CustoEntrega>();

            // campos da entrega
            String dtEntrega = (req.getParameter("txtDtEntrega").equals("") || req.getParameter("txtDtEntrega") == null) ? "1900-01-01" : req.getParameter("txtDtEntrega");
            String kmInicial = (req.getParameter("txtKmInicial").equals("") || req.getParameter("txtKmInicial") == null) ? "0" : req.getParameter("txtKmInicial");
            String kmFinal = (req.getParameter("txtKmFinal").equals("") || req.getParameter("txtKmFinal") == null) ? "0" : req.getParameter("txtKmFinal");
            String hrSaida = (req.getParameter("txtHrSaida").equals("") || req.getParameter("txtHrSaida") == null) ? "00:00" : req.getParameter("txtHrSaida");
            String hrChegada = (req.getParameter("txtHrChegada").equals("") || req.getParameter("txtHrChegada") == null) ? "00:00" : req.getParameter("txtHrChegada");
            String qtLitros = (req.getParameter("txtLitros").equals("") || req.getParameter("txtLitros") == null) ? "EN" : req.getParameter("txtLitros");
            String situacao = (req.getParameter("cmbSituacao").equals("") || req.getParameter("cmbSituacao") == null) ? "EN" : req.getParameter("cmbSituacao");

            // campos do custo de entrega
            String descCusto = (req.getParameter("txtDescCusto").equals("") || req.getParameter("txtDescCusto") == null) ? "" : req.getParameter("txtDescCusto");
            String valorCusto = (req.getParameter("txtValor").equals("") || req.getParameter("txtValor") == null) ? "0" : req.getParameter("txtValor");

            if (custoEntrega == null)
                custoEntrega = new CustoEntrega();
            custoEntrega.setDescricao(descCusto);
            custoEntrega.setValor(Double.parseDouble(valorCusto));
            lstCustoEntrega.add(custoEntrega);


            //montagem do objeto Entrega
            entrega.setIdMovimento(movimento.getIdMovimento());
            entrega.setDataFormatada(dtEntrega);
            entrega.setKmInicial(Double.parseDouble(kmInicial));
            entrega.setKmFinal((Double.parseDouble(kmFinal)));
            entrega.setHrSaida(hrSaida);
            entrega.setHrChegada(hrChegada);
            entrega.setLitros(Double.parseDouble(qtLitros));
            entrega.setSituacao(situacao);
            entrega.setLstCustoEntrega(lstCustoEntrega);

            if(entregaDAO.atualizar(entrega))
                entrega = entregaDAO.listaUm(entrega);

            sessao.setAttribute("entrega",entrega);
            sessao.setAttribute("lstCustoEntrega",entrega.getLstCustoEntrega());
            sessao.setAttribute("custoEntrega",null);
        }
        
        if (pagRetorno.equals(""))
            pagRetorno = "FabricaGelo.Entrega.AcaoAbreEntrega";
        
        return pagRetorno;
    }
}
