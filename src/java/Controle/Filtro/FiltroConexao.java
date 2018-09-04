package Controle.Filtro;

import DAO.Conexao;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.*;

public class FiltroConexao
    implements Filter
{

    public FiltroConexao()
    {
    }

    public void init(FilterConfig filterconfig)
        throws ServletException
    {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException
    {
        try
        {
            Connection connection = (new Conexao()).getConexao();
            request.setAttribute("connection", connection);
            chain.doFilter(request, response);
            connection.close();
        }
        catch(SQLException e)
        {
            throw new ServletException(e);
        }
    }

    public void destroy()
    {
    }
}
