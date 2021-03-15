
.. _manage-connections:

.. _Create Table Intro:

How To Create A Table and Insert Data
=====================================

First we should check to see if the table exists.
We want to make sure that the table does not exist, that way we can avoid errors and/or duplicate work.

Click :ref:`create table` to skip and go to creating a table.

Check If Table Exists
---------------------

This is a simple user input, in which you can input the table name to see if it exists
in the database.

.. code-block:: java
    :linenos:

    package net.codejava.jdbc;

    import java.sql.*;
    import java.util.Scanner;

    public class TableExists {
        public static void main(String[] args) {
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            DatabaseMetaData meta = null;
            try {
                Scanner console = new Scanner(System.in);
                System.out.print("Enter Table Name ");
                String tablename = console.next();
                String dbURL = "jdbc:sqlserver://localhost;databaseName=GAFA;" +
                               "integratedSecurity=true";
                conn = DriverManager.getConnection(dbURL);
                meta = conn.getMetaData();
                String [] types = {"TABLE"};
                rs = meta.getTables(null, null, "%", types);
                while (rs.next()){
                   if (rs.getString("TABLE_NAME").toLowerCase().equals(tablename.toLowerCase())){
                       System.out.println("You inputted:  " + tablename);
                       System.out.println("Table found:  "+ rs.getString("TABLE_NAME") );
                       break;
                   } else {
                       System.out.println("doesnt exist");
                   }
                }
                } catch (SQLException ex) {
                    ex.printStackTrace();
            }
        }
    }



Now lets create a new table in our data base:

.. _create table:

Create Table
------------

.. code-block:: Java
    :emphasize-lines: 15 - 19
    :linenos:

    package net.codejava.jdbc;

    import java.sql.*;

    public class CreateTable {
        public static void main(String[] args) {
            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;
            try {
                String dbURL = "jdbc:sqlserver://localhost;databaseName=GAFA; " +
                               "integratedSecurity=true";
                conn = DriverManager.getConnection(dbURL);
                stmt = conn.createStatement();
                //Here we are creating a String called sql and using the
                //Create Table Method to create an new table
                //called Company info with just some random
                //company information.  There is no data on this table yet,
                //just the column names.
                String sql = "Create Table CompanyInfo " +
                             "( symbol nvarchar(255) not NULL, " +
                             "companyname nvarchar(255) not Null, " +
                             "address nvarchar(255), " +
                             "city nvarchar(255), " +
                             "country nvarchar(255), " +
                             "state nvarchar(2), " +
                             "zip nvarchar(10), " +
                             "internationalcode nvarchar(4), " +
                             "areacode nvarchar(4), " +
                             "phonenumber nvarchar(15), " +
                             "PRIMARY KEY(symbol))";
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
-----------

Single Row Insert
~~~~~~~~~~~~~~~~~

The code below inserts data into the table we just created.  *This is just an example and the data being inserted
is not accurate*  We using :ref:`Prepared Statements` to insert the data into the table.  Notice the ``?`` in the code,
each ``?`` represents the data going into that that column.

.. code-block:: java
    :linenos:

    package net.codejava.jdbc;

    import java.sql.*;

    public class InsertData {
        public static void main(String[] args) {
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            try {
                String dbURL = "jdbc:sqlserver://localhost;databaseName=GAFA;" +
                               "integratedSecurity=true";
                conn = DriverManager.getConnection(dbURL);
                String sql = "INSERT INTO CompanyInfo ( symbol, " +
                             "companyname, " +
                             "address, " +
                             "city, " +
                             "country, " +
                             "state, " +
                             "zip, " +
                             "internationalcode, " +
                             "areacode, " +
                             "phonenumber) " +
                              "Values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1,"goog");
                stmt.setString(2, "Google");
                stmt.setString(3,"1234 Google Way");
                stmt.setString(4,"GoogleVille");
                stmt.setString(5,"USA");
                stmt.setString(6, "CA");
                stmt.setString(7,95124);
                stmt.setString(8,01);
                stmt.setString(9,912);
                stmt.setString(10,6945634);
                stmt.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }


.. note::

    Using java to insert could require multiple ``INSERT`` and ``Execute`` statements.


Bulk insert
~~~~~~~~~~~

Inserting one line at time can be useful, in certain circumstance, but what if you need to insert
a whole bunch of data. One line at a time is not very efficient.  We will want to use a **BULK INSERT**.

To import data from a data file, the file must meet the following basic requirements:
            * The data must be in row and column format.  **The structure of the data file does**
              **not need to be identical to the structure of the SQL Server table because columns**
              **can be skipped or reordered during the bulk-import process.**
            * The data in the data file must be in a supported format such as character or native format.
            * The data can be in character or native binary format including Unicode.
            * To import data by using a bcp command, ``BULK INSERT`` statement, or ``INSERT``
              ``... SELECT * FROM OPENROWSET(BULK...)`` statement, the destination table must already exist.
            * **Each field in the data file must be compatible with the corresponding column in the target table.**
              For example, an ``int`` field cannot be loaded into a ``datetime`` column.
            * To import data from data files with fixed-length or fixed-width fields, use a format file.

For our example we will be using an *Excel* with a ``.csv`` extension.

.. important::
   Starting with SQL Server 2017, a CSV file can be used as the data file for a bulk import of
   data into SQL Server. Note that the field terminator of a CSV file does not have to be a comma.
   To be usable as a data file for bulk import, a CSV file must comply with the following restrictions:
          * Data fields never contain the field terminator.
          * Either none or all of the values in a data field are enclosed in quotation marks ("").

Here is an example:

.. code-block::
    :emphasize-lines: 15-16,22-23,25-26,28-31,33-35,37-39
    :linenos:

    package net.codejava.jdbc;

    import java.sql.*;

    public class BulkInsert {
        public static void main(String[] args) {
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            try {
                String dbURL = "jdbc:sqlserver://localhost;databaseName=GAFA;" +
                               "integratedSecurity=true";
                conn = DriverManager.getConnection(dbURL);
                String sql = "DECLARE @filename as NVARCHAR(4000)" +
                             // When setting the file name you want to include the entire
                             // address to the file
                             " SET @filename = 'location/of/file/in/storage/ " +
                             "filename.csv'" +
                             " DECLARE @sql NVARCHAR(4000) = " +
                             " 'BULK INSERT (Table Name) FROM ''' + @filename + ''' " +
                             " WITH (" +
                                    // Using the DATAFILETYPE = ''char'', specifies that
                                    // the data fields be loaded as character data.
                                    "DATAFILETYPE = ''char'' " +
                                    // We are setting the FIRST copy row to 2 since we do
                                    // not need the header row as that is already in the table
                                    ", FIRSTROW=2 " +
                                    // FIELDQUOTE Specifies a character that will be used as
                                    // the quote character in the CSV file. If not specified,
                                    // the quote character (") will be used.
                                    ", FIELDQUOTE = ''\'' " +
                                    // FIELDTERMINATOR = Specifies the field terminator to be
                                    // used for character and Unicode character data files. We
                                    // are using a comma (,) as the field terminator.
                                    // Keep in mind that (;) can be used as well.
                                    ", FIELDTERMINATOR = '','' " +
                                    // ROWTERMINATOR Specifies the field terminator to be used
                                    // for character and Unicode character data files. We are
                                    // using the new line character to specify the row terminator.
                                    ", ROWTERMINATOR = ''\n'' )';" +
                             "EXEC(@sql)";
                stmt = conn.prepareStatement(sql);
                stmt.execute();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

