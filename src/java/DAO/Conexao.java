package DAO;

import java.sql.*;

public class Conexao
{

    public Conexao()
    {
    }

    public Connection getConexao()
    {
        
        try
        {
            Class.forName("org.gjt.mm.mysql.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost/bdGelo", "root", "cloim10");
            //return DriverManager.getConnection("jdbc:mysql://mysql.gerirloja.kinghost.net/gerirloja", "gerirloja", "csm1110");
            //return DriverManager.getConnection("jdbc:mysql://mysql05-farm68.kinghost.net/gerirloja", "gerirloja", "csm1110");
        }
        catch(SQLException e)
        {
            throw new RuntimeException(e);
        }
        catch(ClassNotFoundException ce)
        {
            throw new RuntimeException(ce);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
        return null;
        
    }
}
