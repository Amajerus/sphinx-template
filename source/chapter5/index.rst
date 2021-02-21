.. _manage-connections:
Prepared Statements
===================

    To make your SQL Queries more secure you can use **Prepared Statements**.

Setting Parameters
------------------
    First we will want to set parameters. An sample string is shown below

        ``String sql = "Select security_symbol, Date, High  From dbo.stockprices"+``
        ``"Where security_symbol = ? and Year(Date) >= ?"``

    Notice the use of the ``?`` in the where clause.

Defining Parameters
-------------------

    They are defined in the following codes

        ``stmt.setString(1, "FB");``
        ``stmt.setInt(2, 2019);``

    Notice the parameters ``1`` and ``2`` in the set statements, those represent the number and the position of the ``?``.

Code
----
    .. code-block:: Java
       :emphasize-lines: 13,14,16,17,18,19
       :linenos:

        package net.codejava.jdbc;

        import java.sql.*;

        public class SQLInjection {
            public static void main(String[] args) {
                Connection conn = null;
                PreparedStatement stmt = null;
                ResultSet rs = null;
                try {
                    String dbURL = "jdbc:sqlserver://localhost;databaseName=GAFA; integratedSecurity=true";
                    conn = DriverManager.getConnection(dbURL);
                    // We added a parameter here to help prevent SQL Injection
                    String sql = "Select security_symbol, Date, High  From dbo.stockprices Where security_symbol = ? and Year(Date) >= ?";
                    stmt = conn.prepareStatement(sql);
                    // If you had parameters, they would be set wit something like:
                    stmt.setString(1, "FB");
                    stmt.setInt(2,2019);
                    // Execute the SQL and get the results
                    rs = stmt.executeQuery();
                    while(rs.next()) {
                        String sym = rs.getString(1);
                        //String sym = rs.getString("security_symbol");
                        Date dt = rs.getDate("Date");
                        Float hgh = rs.getFloat("High");
                        System.out.print(sym + " ");
                        System.out.print(dt + " ");
                        System.out.println(hgh);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
