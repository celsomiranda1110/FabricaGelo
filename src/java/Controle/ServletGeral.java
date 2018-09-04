package Controle;

import Controle.FabricaGelo.Gerais.Acao;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class ServletGeral extends HttpServlet
{

    protected HttpSession sessao;

    public ServletGeral()
    {
        sessao = null;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
        processRequest(request, response);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response)
    {
        String nomeClasse = "";
        String jsp = null;
        sessao = request.getSession(true);
        try
        {
            nomeClasse = request.getRequestURI();
            nomeClasse = (new StringBuilder()).append("Controle.").append(nomeClasse.substring(7)).toString();
            Class cl = Class.forName(nomeClasse);
            Acao acao = (Acao)cl.newInstance();
            jsp = acao.executa(request, response);
            if(jsp != null)
            {
                if (!response.isCommitted())
                {
                    RequestDispatcher  dispatcher = request.getRequestDispatcher(jsp);
                    dispatcher.forward(request, response);
                }
            }            
//            if(jsp != null)
//            {
//                request.getRequestDispatcher(jsp).forward(request, response);
//            }
        }
        catch(SQLException sqle)
        {
            throw new RuntimeException(sqle);
        }
        catch(ClassNotFoundException ce)
        {
            throw new RuntimeException(ce);
        }
        catch(ServletException se)
        {
            throw new RuntimeException(se);
        }
        catch(IOException ioe)
        {
            throw new RuntimeException(ioe);
        }
        catch(InstantiationException ie)
        {
            throw new RuntimeException(ie);
        }
        catch(IllegalAccessException iae)
        {
            throw new RuntimeException(iae);
        }
        catch(Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
