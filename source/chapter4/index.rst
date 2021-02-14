Querying SQL Server with JAVA
=============================

Some Sample Queries:

    1.  This is a basic query only returns one column.

        .. code-block:: Java
            :linenos:

            package net.codejava.jdbc;
            import java.sql.*
            public class SimpleQuery {
                public static void main(String[] args) {
                    Connection conn = null;
                    try {
                        String dbURL = "jdbc:sqlserver://localhost;databaseName=GAFA; integratedSecurity=true";
                        conn = DriverManager.getConnection(dbURL);
                        Statement stmt = conn.createStatement();
                        String sql = "Select security_symbol from dbo.StockPrices "
                        ResultSet rs = stmt.executeQuery(sql);
                        while(rs.next()){
                            String sym = rs.getString("security_symbol");
                            System.out.println(sym);
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }


    2.  To pull data from multiple columns up date the try statement above:

        .. code-block:: Java
            :linenos:

            try {
            String dbURL = "jdbc:sqlserver://localhost;databaseName=GAFA; integratedSecurity=true";
            conn = DriverManager.getConnection(dbURL);
            Statement stmt = conn.createStatement();
            String sql = "Select security_symbol, Date, High  from dbo.StockPrices "
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String sym = rs.getString("security_symbol");
                String dt = rs.getString("Date");
                String hgh = rs.getString("High");
                System.out.print(sym + " ");
                System.out.print(dt + " ");
                System.out.println(hgh);
            }



  .. note::

    Note  ``String sql = "Select security_symbol, Date, High  from dbo.StockPrices "``
    has 3 columns listed which will require a separate print statement.
    Make sure to add a " " in the print statement to separate the data in the rows.

