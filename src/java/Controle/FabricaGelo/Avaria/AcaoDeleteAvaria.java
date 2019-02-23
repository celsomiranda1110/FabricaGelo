/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Avaria;

import Bean.Avaria;
import Bean.AvariaEntrega;
import Bean.AvariaProducao;
import Bean.Entrega;
import Bean.MaquinaProducao;
import Bean.Producao;
import Bean.ProdutoEntrega;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.AvariaDAO;
import DAO.EntregaDAO;
import DAO.MaquinaProducaoDAO;
import DAO.ProducaoDAO;
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
public class AcaoDeleteAvaria extends Acao
{
    
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String pagRetorno = "visao/avaria.jsp";
        
        Avaria avaria = (Avaria)sessao.getAttribute("avaria");
        if (avaria == null)
        {
            sessao.setAttribute("avisoErro", "Avaria não selecionado");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Avaria.AcaoAbreAvaria");
            pagRetorno = "visao/erro.jsp";              
        }
        else
        {
           
            boolean bolApaga = true;
            
            ProdutoEntregaDAO produtoEntregaDAO = new ProdutoEntregaDAO(conexao);
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
                    produtoEntrega = produtoEntregaDAO.listaUm(produtoEntrega);
                    Iterator itAvariaEntrega = produtoEntrega.getLstAvariaEntrega().iterator();
                    while (itAvariaEntrega.hasNext())
                    {
                        AvariaEntrega avariaEntrega = (AvariaEntrega)itAvariaEntrega.next();
                        if (avariaEntrega.getIdAvaria() == avaria.getIdAvaria())
                        {
                            bolApaga = false;
                            break;
                        }
                    }
                    if (!bolApaga)
                        break;
                }
                if (!bolApaga)
                    break;
            }
            
            if (bolApaga)
            {
            
                ProducaoDAO producaoDAO = new ProducaoDAO(conexao);
                MaquinaProducaoDAO maquinaProducaoDAO = new MaquinaProducaoDAO(conexao);
                Iterator itProducao = producaoDAO.listaTodos().iterator();
                while (itProducao.hasNext())
                {
                    Producao producao = (Producao)itProducao.next();
                    producao = producaoDAO.listaUm(producao);
                    Iterator itMaquinaProducao = producao.getLstMaquinaProducao().iterator();
                    while (itMaquinaProducao.hasNext())
                    {
                        MaquinaProducao maquinaProducao = (MaquinaProducao)itMaquinaProducao.next();
                        maquinaProducao = maquinaProducaoDAO.listaUm(maquinaProducao);
                        Iterator itAvariaProducao = maquinaProducao.getLstAvariaProducao().iterator();
                        while (itAvariaProducao.hasNext())
                        {
                            AvariaProducao avariaProducao = (AvariaProducao)itAvariaProducao.next();
                            if (avariaProducao.getIdAvaria() == avaria.getIdAvaria())
                            {
                                bolApaga = false;
                                break;
                            }
                        }
                        if (!bolApaga)
                            break;
                    }
                    if (!bolApaga)
                        break;
                }
            }
            
            if (!bolApaga)
            {
                sessao.setAttribute("avisoErro", "Avaria está relacionado com movimentos. Inative para preservação dos dados");
                sessao.setAttribute("tipoAviso","alert alert-danger");
                sessao.setAttribute("pagOrigemErro", "FabricaGelo.Avaria.AcaoAbreAvaria");
                pagRetorno = "visao/erro.jsp";              
            }
            else
            {
                AvariaDAO avariaDAO = new AvariaDAO(conexao);
                if (avariaDAO.delete(avaria))
                {
                    sessao.setAttribute("avisoErro", "Avaria deletado com sucesso");
                    sessao.setAttribute("tipoAviso","alert alert-success");
                    sessao.setAttribute("pagOrigemErro", "FabricaGelo.Avaria.AcaoListarAvaria");
                    pagRetorno = "visao/erro.jsp";              
                    
                }
            }
            
        }
        
        sessao.setAttribute("avaria", avaria);
        
        return pagRetorno;
    }
}
