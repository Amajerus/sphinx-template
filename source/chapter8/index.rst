Error Handling
==============

Simple Error
------------

When querying ``SQL Server`` with ``java`` there is a very good chance that an error will occur somewhere.
What does an error look like when querying ``SQL Server`` with ``java``?

Here is a simple example:

 .. code-block:: Java
    :linenos:

    package net.codejava.jdbc;

    import java.sql.*;

    public class SimpleQuery {
        public static void main(String[] args) {
            Connection conn = null;
            try {
                String dbURL = "jdbc:sqlserver://localhost;databaseName=GAFA;" +
                               "integratedSecurity=true";
                conn = DriverManager.getConnection(dbURL);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("Select securty_symbol from StockPrices ");
                while(rs.next()){
                    String sym = rs.getString("security_symbol");
                    System.out.println(sym);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

Output:

.. image::  Error1.JPG

This is a simple example where the column name was mistyped in the select statement.  As you can see on the second line
of the output it calls out ``Invalid column name 'securty_symbol'``.


Error With Rollback
-------------------

We are doing a simple insert using *Prepared Statements*, *Try Catch*, and *Rollback*

.. code-block::
    :emphasize-lines: 14-15, 31-33
    :linenos:

    package net.codejava.jdbc;

    import java.sql.*;

    public class InsertData2 {
        public static void main(String[] args) {
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            try {
                String dbURL = "jdbc:sqlserver://localhost;databaseName=GAFA;" +
                               "integratedSecurity=true";
                conn = DriverManager.getConnection(dbURL);
                //In this example we are inserting a row into the table we created earlier.
                //The rollback is used to reverse the insert if an error occurs.
                String sql = "Begin Try " +
                             "Begin Transaction " +
                             "INSERT INTO CompanyInfo "+
                             "Values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                             "Commit Transaction " +
                             "End Try " +
                             "Begin Catch "+
                             "Rollback " +
                             "DECLARE @ErrorMessage NVARCHAR(4000); \n" +
                             "DECLARE @ErrorSeverity INT; \n" +
                             "DECLARE @ErrorState INT; \n" +
                             "SELECT \n" +
                             "@ErrorMessage = ERROR_MESSAGE(), \n" +
                             "@ErrorSeverity = ERROR_SEVERITY(), \n" +
                             "@ErrorState = ERROR_STATE();  \n" +
                        // Use RAISERROR inside the CATCH block to return error
                        // information about the original error that caused
                        // execution to jump to the CATCH block.
                             "RAISERROR (@ErrorMessage, -- Message text. \n" +
                             "@ErrorSeverity, -- Severity. \n" +
                             "@ErrorState -- State0. \n" +
                             ");  \n" +
                             "END Catch";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1,"goog");
                stmt.setString(2, "Google");
                stmt.setString(3,"1234 Google Way");
                stmt.setString(4,"GoogleVille");
                stmt.setString(5,"USA");
                stmt.setString(6, "CA");
                stmt.setInt(7,95124);
                stmt.setInt(8,01);
                stmt.setInt(9,912);
                stmt.setInt(10,6945634);
                stmt.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("Error Code:" + ex.getErrorCode());
                System.out.println("Error Message:  " + ex.getMessage());
            }
        }
    }

.. note::
    We have changed the ``catch (SQLException ex)`` it will now print the error code using
    ``System.out.println(ex.getErrorCode())`` and will also print the error message from the
    ``System.out.println(ex.getMessage());``.

    The above code returns:
     * Error Code: 50000
     * Error Message: Violation of PRIMARY KEY constraint 'PK__CompanyI__DF7EEB80C4E2D864'. Cannot insert duplicate key
       in object 'dbo.CompanyInfo'. The duplicate key value is (goog).

