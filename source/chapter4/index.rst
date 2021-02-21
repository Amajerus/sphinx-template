Querying SQL Server with JAVA
=============================

When querying SQL Sever with JAVA you will want to start with the this basic code.

    .. code-block:: Java
       :emphasize-lines: 3,4,8,9,10,11,13,20
       :linenos:

       package net.codejava.jdbc;

       //You can import individual elements of java.sql or as we did use java.sql.* to
       //do a mass import.

        import java.sql.*;

       //Example of some individual elements to use
       //import java.sql.Connection;
       //import java.sql.DriverManager;
       //import java.sql.SQLException;

       //Change the class name below to fit your needs
       public class Sample {
           public static void main(String[] args) {
                Connection conn = null;
                PreparedStatement stmt = null;
                ResultSet rs = null;
                try {
                    //This is where all your code goes to connect and query a SQL Server database.
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }


Simple Queries
--------------
    1.  This is a basic query only returns one column.

        .. code-block:: Java
           :emphasize-lines: 14
           :linenos:

            package net.codejava.jdbc;

            import java.sql.*;

            public class SimpleQuery {
                public static void main(String[] args) {
                    Connection conn = null;
                    PreparedStatement stmt = null;
                    ResultSet rs = null;
                    try {
                        String dbURL = "jdbc:sqlserver://localhost;databaseName=GAFA; integratedSecurity=true";
                        conn = DriverManager.getConnection(dbURL);
                        stmt = conn.createStatement();
                        String sql = "Select security_symbol from dbo.StockPrices "
                        rs = stmt.executeQuery(sql);
                        while(rs.next()){
                            String sym = rs.getString("security_symbol");
                            System.out.println(sym);
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }


    2.  To pull data from multiple columns update the try statement above:

        .. code-block:: Java
            :emphasize-lines: 5,8,9,10
            :linenos:

            try {
            String dbURL = "jdbc:sqlserver://localhost;databaseName=GAFA; integratedSecurity=true";
            conn = DriverManager.getConnection(dbURL);
            stmt = conn.createStatement();
            String sql = "Select security_symbol, Date, High  from dbo.StockPrices "
            rs = stmt.executeQuery(sql);
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

Operator Query
--------------
    Here we are showing a very basic example of how to use an operator in your query
        .. code-block:: Java
            :emphasize-lines: 14
            :linenos:

            package net.codejava.jdbc;

            import java.sql.*;

            public class OperatorQuery {
                public static void main(String[] args) {
                    Connection conn = null;
                    PreparedStatement stmt = null;
                    ResultSet rs = null;
                    try {
                        String dbURL = "jdbc:sqlserver://localhost;databaseName=GAFA; integratedSecurity=true";
                        conn = DriverManager.getConnection(dbURL);
                        stmt = conn.createStatement();
                        String sql = "Select security_symbol, Date, High  From dbo.stockprices Where High > 150 ";
                        rs = stmt.executeQuery(sql);
                        while(rs.next()) {
                            String sym = rs.getString("security_symbol");
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

Simple Join
-----------

    This will illustrate a very simple inner join.

         .. code-block:: Java
            :emphasize-lines: 12,13
            :linenos:

            package net.codejava.jdbc;

            import java.sql.*;

            public class QuerySQL {
                public static void main(String[] args) {
                    Connection conn = null;
                    try {
                        String dbURL = "jdbc:sqlserver://localhost;databaseName=GAFA; integratedSecurity=true";
                        conn = DriverManager.getConnection(dbURL);
                        Statement stmt = conn.createStatement();
                        String sql = "Select s.security_symbol, s.Date, s.High, n.Country, n.Volume From dbo.stockprices as s," +
                                " dbo.nasdaq as n Where s.security_symbol = n.symbol ";
                        ResultSet rs = stmt.executeQuery(sql);
                        while(rs.next()) {
                            String sym = rs.getString("security_symbol");
                            Date dt = rs.getDate("Date");
                            Float hgh = rs.getFloat("High");
                            String coun = rs.getString("Country");
                            Float vol = rs.getFloat("volume");
                            System.out.print(sym + " ");
                            System.out.print(dt + " ");
                            System.out.print(hgh + " ");
                            System.out.print(coun + " ");
                            System.out.println(vol);
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }

.. note::

    Notice we concatenated the ``sql`` statement.

    ``"Select s.security_symbol, s.Date, s.High, n.Country, n.Volume From dbo.stockprices as s," +``
    ``" dbo.nasdaq as n Where s.security_symbol = n.symbol ";``

    This is perfectly fine as the more complex the SQL statements get, the longer the ``sql`` statement
    will need to be.
