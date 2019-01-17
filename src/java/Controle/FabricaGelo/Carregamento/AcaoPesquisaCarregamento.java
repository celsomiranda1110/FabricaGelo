/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Carregamento;

import Bean.SaidaCamara;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.SaidaCamaraDAO;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoPesquisaCarregamento extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        
        List<SaidaCamara> lstSaidaCamara = new ArrayList<SaidaCamara>();
        List<SaidaCamara> lstRetorno = new ArrayList<SaidaCamara>();
        
        SaidaCamaraDAO saidaCamaraDAO = new SaidaCamaraDAO(conexao);
        lstSaidaCamara = saidaCamaraDAO.listaTodos();
        
        String pesquisaIn = req.getParameter("txtPesquisaIn");
        String pesquisaFi = req.getParameter("txtPesquisaFi");
        
        if ((!pesquisaIn.equals("")) && (!pesquisaFi.equals("")))
        {
            Date dtIn = new Date();
            Date dtFi = new Date();
            
            try{
                dtIn = (Date)formatter.parse(pesquisaIn);
                dtFi = (Date)formatter.parse(pesquisaFi);
            }catch(ParseException pe)
            {}            
            
            Iterator it = lstSaidaCamara.iterator();
            while (it.hasNext())
            {
                SaidaCamara _saidaCamara = (SaidaCamara)it.next();
                
                if (_saidaCamara.getData().after(dtIn) && _saidaCamara.getData().before(dtFi))
                    lstRetorno.add(_saidaCamara);
            }
        }
        else
            lstRetorno = lstSaidaCamara; 
        
        sessao.setAttribute("lstSaidaCamara",lstRetorno); 
        
        return "visao/listarSaidaCamara.jsp";        
    }
}
