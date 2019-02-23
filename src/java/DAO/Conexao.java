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
            return DriverManager.getConnection("jdbc:mysql://localhost/smmdaa_bdGelo", "root", "cloim10");
            //return DriverManager.getConnection("jdbc:mysql://localhost/smmdaa_bdGelo", "smmdaa_dorli", "!#cepapeed#!");
            
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
