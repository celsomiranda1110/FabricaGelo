package Controle.Filtro;

import Bean.Profissional;
import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.*;
import javax.servlet.http.*;

public class FiltroSeguranca
    implements Filter
{

    HttpSession sessao;
    FilterConfig config;

    public FiltroSeguranca()
    {
        sessao = null;
        config = null;
    }

    public void init(FilterConfig filterConfig)
        throws ServletException
    {
        config = filterConfig;
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
        throws IOException, ServletException
    {
        if(!((HttpServletRequest)req).getRequestURI().equals(""))
        {
            if(((HttpServletRequest)req).getRequestURI().endsWith("/FabricaGelo.Gerais.AcaoLogin"))
            {
                chain.doFilter(req, res);
            } else
            {
                sessao = ((HttpServletRequest)req).getSession(false);
                if(sessao == null || !sessao.getAttributeNames().hasMoreElements())
                {
                    //((HttpServletResponse)res).sendRedirect(config.getServletContext().getContextPath());
                    ((HttpServletResponse)res).sendRedirect(config.getServletContext().toString());
                } else
                {
                    Profissional usuario = (Profissional)sessao.getAttribute("usuario");
                    if(usuario == null)
                    {
                        ((HttpServletResponse)res).sendRedirect(config.getServletContext().toString());
                    } else
                    {
                        chain.doFilter(req, res);
                    }
                }
            }
        } else
        {
            sessao = ((HttpServletRequest)req).getSession(false);
            if(sessao == null || !sessao.getAttributeNames().hasMoreElements())
            {
                ((HttpServletResponse)res).sendRedirect(config.getServletContext().toString());
                
            } else
            {
                chain.doFilter(req, res);
            }
        }
    }

    public void destroy()
    {
        config = null;
    }
}
