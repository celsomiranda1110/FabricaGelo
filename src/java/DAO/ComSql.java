package DAO;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

// Referenced classes of package DAO:
//            Parametro

public class ComSql
{

    private Connection con;
    private Statement st;
    private PreparedStatement prSt;
    private ResultSet rs;
    private int identity;

    public ComSql(Connection conexao)
    {
        con = null;
        st = null;
        prSt = null;
        rs = null;
        identity = 0;
        con = conexao;
    }
 
    private void abreConexao()    throws SQLException
    {
        st = con.createStatement();
    }

    public boolean atualizar(String sql)
    {
        boolean retorno = false;
        
        try
        {
            abreConexao();
            st.getConnection().setAutoCommit(false);
            st.executeUpdate(sql);
            st.getConnection().commit();
            retorno = true;
        }
        catch(SQLException sqlExc)
        {
            
            try { 
                st.getConnection().rollback();
                st.getConnection().setAutoCommit(true);
                st.close();                
            } catch (SQLException ex) {
                Logger.getLogger(ComSql.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        finally
        {
            if(rs != null)
            {
                try
                {
                    rs.close();
                    st.close();
                }
                catch(Exception e) { }
            }

        }        


        return retorno;
    }

    public List listaTodos(String sql)
    {
        List tabela;
        {
            tabela = new ArrayList();
            String formato = "dd/MM/yyyy";
            try
            {
                abreConexao();
                rs = st.executeQuery(sql);
                ResultSetMetaData rsmd = rs.getMetaData();
                List registro;
                for(; rs.next(); tabela.add(registro))
                {
                    registro = new ArrayList();
                    for(int i = 1; i <= rsmd.getColumnCount(); i++)
                    {
                        switch(rsmd.getColumnType(i))
                        {
                        case Types.VARCHAR: // '\f'
                            registro.add(rs.getString(i));
                            break;

                        case Types.INTEGER: // '\004'
                            registro.add(Integer.valueOf(rs.getInt(i)));
                            break;

                        case Types.DATE: // '['
                            registro.add(rs.getDate(i));
                            break;

                        case Types.DOUBLE: // '\b'
                            registro.add(Double.valueOf(rs.getDouble(i)));
                            break;
                            
                        case Types.DECIMAL: // '\b'
                            registro.add(Double.valueOf(rs.getDouble(i)));
                            break;
                            
                        case Types.TIME: // '\b'
//                            registro.add(rs.getTime(i));
                            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                            String horaFormatada = sdf.format(rs.getTime(i));                            
                            registro.add(horaFormatada);                            
                            break;                            

                        default:
                            registro.add(null);
                            break;
                        }
                    }

                }

            }
            catch(SQLException sqlex)
            {
                if(rs != null)
                {
                    try
                    {
                        rs.close();
                        st.close();
                    }
                    catch(Exception e) { }
                }

            }
            finally
            {
                if(rs != null)
                {
                    try
                    {
                        rs.close();
                        st.close();
                    }
                    catch(Exception e) { }
                }

            }

        }
        return tabela;
    }

    public List listaUm(String sql)
    {
        List tabela;
        {
            tabela = new ArrayList();
            String formato = "dd/MM/yyyy";
            SimpleDateFormat formatter = new SimpleDateFormat(formato);
            try
            {
                abreConexao();
                rs = st.executeQuery(sql);
                ResultSetMetaData rsmd = rs.getMetaData();
                if(rs.next())
                {
                    List registro = new ArrayList();
                    for(int i = 1; i <= rsmd.getColumnCount(); i++)
                    {
                        switch(rsmd.getColumnType(i))
                        {
                        case Types.VARCHAR: // '\f'
                            registro.add(rs.getString(i));
                            break;

                        case Types.INTEGER: // '\004'
                            registro.add(Integer.valueOf(rs.getInt(i)));
                            break;

                        case Types.TIMESTAMP: // ']'
                            registro.add(formatter.format(rs.getDate(i)));
                            break;
                            
                        case Types.DATE: // ']'
                            registro.add(rs.getDate(i));
                            //registro.add(formatter.format(rs.getDate(i)));
                            break;

                        case Types.DOUBLE: // '\b'
                            registro.add(Double.valueOf(rs.getDouble(i)));
                            break;
                            
                        case Types.DECIMAL: // '\b'
                            registro.add(Double.valueOf(rs.getDouble(i)));
                            break;  
                            
                        case Types.TIME: // '\b'
                            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                            String horaFormatada = sdf.format(rs.getTime(i));                            
                            registro.add(horaFormatada);
                            break;                            

                        default:
                            registro.add(null);
                            break;
                        }
                    }

                    tabela.add(registro);
                }
            }
            catch(SQLException sqlex)
            {
                if(rs != null)
                {
                    try
                    {
                        rs.close();
                        st.close();
                    }
                    catch(Exception e) { }
                }

            }
            finally
            {
                if(rs != null)
                {
                    try
                    {
                        rs.close();
                        st.close();
                    }
                    catch(Exception e) { }
                }
 
            }

        }
        return tabela;
    }

    public List lista(String sql, List lstParam)
    {
        List tabela;
        SimpleDateFormat formatter;
        SimpleDateFormat formatter2;
        tabela = new ArrayList();
        String formato = "dd/MM/yyyy";
        String formato2 = "yyyy-MM-dd";
        formatter = new SimpleDateFormat(formato);
        formatter2 = new SimpleDateFormat(formato2);
        try
        {
            abreConexao();
            CallableStatement cal = st.getConnection().prepareCall(sql);
            for(int i = 0; i < lstParam.size(); i++)
            {
                Parametro param = (Parametro)lstParam.get(i);
                if(param.getTipo().equals("INTEGER"))
                {
                    cal.setInt(i + 1, param.getValor().equals("") ? 0 : Integer.parseInt(param.getValor()));
                    continue;
                }
                if(param.getTipo().equals("VARCHAR"))
                {
                    cal.setString(i + 1, param.getValor().equals("") ? null : param.getValor());
                    continue;
                }
                if(param.getTipo().equals("TIMESTAMP"))
                {
                    if(!param.getValor().equals("null") && !param.getValor().equals(""))
                    {
                        try
                        {
                            String dataFormatada = formatter2.format(formatter.parse(param.getValor()));
                            cal.setDate(i + 1, Date.valueOf(dataFormatada));
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                    } else
                    {
                        cal.setDate(i + 1, null);
                    }
                    continue;
                }
                if(param.getTipo().equals("DOUBLE"))
                {
                    cal.setDouble(i + 1, param.getValor().equals("") ? 0.0D : Double.parseDouble(param.getValor()));
                }
            }

            rs = cal.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            List registro;
            for(; rs.next(); tabela.add(registro))
            {
                registro = new ArrayList();
                for(int i = 1; i <= rsmd.getColumnCount(); i++)
                {
                    switch(rsmd.getColumnType(i))
                    {
                    case 12: // '\f'
                        registro.add(rs.getString(i));
                        break;

                    case 4: // '\004'
                        registro.add(Integer.valueOf(rs.getInt(i)));
                        break;

                    case 91: // '['
                        registro.add(formatter.format(rs.getDate(i)));
                        break;

                    case 93: // ']'
                        if(rs.getDate(i) == null)
                        {
                            registro.add("");
                        } else
                        {
                            registro.add(formatter.format(rs.getDate(i)));
                        }
                        break;

                    case 8: // '\b'
                        registro.add(Double.valueOf(rs.getDouble(i)));
                        break;

                    default:
                        registro.add(null);
                        break;
                    }
                }

            }
        }
        catch(SQLException sqlExc)
        {
            try {
                rs.close();
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(ComSql.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        finally
        {
            if(rs != null)
            {
                try
                {
                    rs.close();
                    st.close();
                }
                catch(Exception e) { }
            }

        }
                
        
        return tabela;
    }

    public boolean execAtualizar(String sql, List lstParam)
    {
        boolean retorno;
        SimpleDateFormat formato1;
        SimpleDateFormat formato2;
        int idRetorno;
        retorno = false;
        formato1 = new SimpleDateFormat("dd/MM/yyyy");
        formato2 = new SimpleDateFormat("yyyy-MM-dd");
        idRetorno = 0;
        
        try
        {
            abreConexao();
            st.getConnection().setAutoCommit(false);
            prSt = st.getConnection().prepareStatement(sql, 1);
            for(int i = 0; i < lstParam.size(); i++)
            {
                Parametro param = (Parametro)lstParam.get(i);
                if(param.getTipo().equals("INTEGER"))
                {
                    idRetorno = idRetorno != 0 ? idRetorno : i + 1;
                    prSt.setInt(i + 1, param.getValor().equals("") ? 0 : Integer.parseInt(param.getValor()));
                    continue;
                }
                if(param.getTipo().equals("VARCHAR"))
                {
                    prSt.setString(i + 1, param.getValor().equals("") ? null : param.getValor());
                    continue;
                }
                if(param.getTipo().equals("TIMESTAMP"))
                {
                    if(!param.getValor().equals("null") && !param.getValor().equals(""))
                    {
                        try
                        {
                            String dataFormatada = formato2.format(formato1.parse(param.getValor()));
                            prSt.setDate(i + 1, Date.valueOf(dataFormatada));
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                    } else
                    {
                        prSt.setDate(i + 1, null);
                    }
                    continue;
                }
                if(param.getTipo().equals("DOUBLE"))
                {
                    prSt.setDouble(i + 1, param.getValor().equals("") ? 0.0D : Double.parseDouble(param.getValor()));
                }
            }

            rs = prSt.executeQuery();
            st.getConnection().commit();
            if(rs.next())
            {
                identity = rs.getInt(idRetorno);
            }
            retorno = true;
        }
        catch(SQLException sqlExc)
        {
            
            try { 
                st.getConnection().rollback();
                st.getConnection().setAutoCommit(true);
                st.close();                
            } catch (SQLException ex) {
                Logger.getLogger(ComSql.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        finally
        {
            if(rs != null)
            {
                try
                {
                    rs.close();
                    st.close();
                }
                catch(Exception e) { }
            }

        }        
                
 
        return retorno;
    }

    public boolean execExcluir(String sql, List lstParam)
    {
        boolean retorno;
        SimpleDateFormat formatter;
        retorno = false;
        String formato = "dd/MM/yyyy";
        formatter = new SimpleDateFormat(formato);
        try
        {
            abreConexao();
            st.getConnection().setAutoCommit(false);
            prSt = st.getConnection().prepareStatement(sql, 1);
            for(int i = 0; i < lstParam.size(); i++)
            {
                Parametro param = (Parametro)lstParam.get(i);
                if(param.getTipo().equals("INTEGER"))
                {
                    prSt.setInt(i + 1, param.getValor().equals("") ? 0 : Integer.parseInt(param.getValor()));
                    continue;
                }
                if(param.getTipo().equals("VARCHAR"))
                {
                    prSt.setString(i + 1, param.getValor().equals("") ? null : param.getValor());
                    continue;
                }
                if(param.getTipo().equals("TIMESTAMP"))
                {
                    prSt.setDate(i + 1, param.getValor().equals("") ? null : Date.valueOf(formatter.format(param.getValor())));
                    continue;
                }
                if(param.getTipo().equals("DOUBLE"))
                {
                    prSt.setDouble(i + 1, param.getValor().equals("") ? 0.0D : Double.parseDouble(param.getValor()));
                }
            }

            prSt.executeQuery();
            st.getConnection().commit();
            retorno = true;
        }
        catch(SQLException sqlExc)
        {
            
            try { 
                st.getConnection().rollback();
                st.getConnection().setAutoCommit(true);
                st.close();                
            } catch (SQLException ex) {
                Logger.getLogger(ComSql.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        finally
        {
            if(rs != null)
            {
                try
                {
                    rs.close();
                    st.close();
                }
                catch(Exception e) { }
            }

        }        
        return retorno;
    }

    public int getIdentity()
    {
        return identity;
    }
}
