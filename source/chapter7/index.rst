Creating A Table and Inserting Data
===================================

First lets create a new table in our data base:

Create Table
------------

.. code-block:: Java
    :emphasize-lines: 14-20
    :linenos:

    package net.codejava.jdbc;

    import java.sql.*;

    public class CreateTable {
        public static void main(String[] args) {
            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;
            try {
                String dbURL = "jdbc:sqlserver://localhost;databaseName=GAFA; integratedSecurity=true";
                conn = DriverManager.getConnection(dbURL);
                stmt = conn.createStatement();
                //Here we are creating a String called sql and using the Create Table Method to create an new table
                //called Company info with just some random company information.  There is no data on this table yet,
                //just the column names.
                String sql = "Create Table CompanyInfo ( symbol nvarchar(255) not NULL, companyname nvarchar(255)," +
                             "address nvarchar(255), city nvarchar(255), country nvarchar(255), state nvarchar(2)," +
                             "zip int, internationalcode int, areacode int, phonenumber int, PRIMARY KEY(symbol))";
                stmt.executeUpdate(sql);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

.. note::

    Notice the *execution* statement ``stmt.executeUpdate(sql);`` and lack of print statements.  In this case we are
    creating a new table in the current database.  You can use print statements for status updates but they are not
    needed.


Insert Data
~~~~~~~~~~~

The code below inserts data into the table we just created.  *This is just an example and the data being inserted
is not accurate*

.. code-block:: java
    :emphasize-lines: 14 - 21
    :linenos:

    package net.codejava.jdbc;

    import java.sql.*;

    public class InsertData {
        public static void main(String[] args) {
            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;
            try {
                String dbURL = "jdbc:sqlserver://localhost;databaseName=GAFA; integratedSecurity=true";
                conn = DriverManager.getConnection(dbURL);
                stmt = conn.createStatement();
                String sql = "INSERT INTO CompanyInfo ( symbol, companyname, address, city, country, state, zip," +
                             "internationalcode, areacode, phonenumber)" + "Values ('goog', 'Google', '1234 Google Way',"+ "" +
                             "'Googleville', 'USA', 'CA',95124, 01, 912, 6945634)";
                stmt.execute(sql);
                sql = "INSERT INTO CompanyInfo ( symbol, companyname, address, city, country, state, zip," +
                      "internationalcode, areacode, phonenumber)" + "Values ('amzn', 'Amazon', '48951 Bezos Blvd'," +
                      "'Amazonville', 'USA', 'CA', 90210, 01, 414, 4521381)";
                stmt.execute(sql);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

.. note::

    Using java to insert requires multiple ``INSERT`` and ``Execute`` statements as shown above.