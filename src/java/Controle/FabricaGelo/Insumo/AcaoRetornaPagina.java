/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Insumo;

import Bean.Produto;
import Bean.MaquinaProducao;
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
        Produto insumo = (Produto)sessao.getAttribute("insumo");
        
        if (pagRetorno != null)
        {
            if (pagRetorno.equals("FabricaGelo.Producao.AcaoAbreProducao"))
            {
                MaquinaProducao maquinaProducao = (MaquinaProducao)sessao.getAttribute("maquinaProducao");
                maquinaProducao.setEmbalagem(insumo);
                sessao.setAttribute("maquinaProducao", maquinaProducao);
                
            }
        }
        else
        {
            sessao.setAttribute("produto", null);
            pagRetorno = "FabricaGelo.Insumo.AcaoListarInsumo";
        }        
        
        return pagRetorno;
    }
}
