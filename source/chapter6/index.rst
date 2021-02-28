Data Types
==========

There are numerous **Data Types** in SQL and Java:

SQL Server Data Types
_____________________

String Data Types
~~~~~~~~~~~~~~~~~

    +---------------+---------------------------------+----------------------------+
    |   Data Type   |         Description             |         Max Size           |
    |               |                                 |                            |
    +===============+=================================+============================+
    | char(n)       | Fixed width character string    | 8,000 characters           |
    +---------------+---------------------------------+----------------------------+
    | varchar(n)    | Variable width character string | 8,000 characters           |
    +---------------+---------------------------------+----------------------------+
    | varchar(max)  | Variable width character string | 1,073,741,824 characters   |
    +---------------+---------------------------------+----------------------------+
    | text          | Variable width character string | 2GB of text data           |
    +---------------+---------------------------------+----------------------------+
    | nchar         | Fixed width Unicode string      | 4,000 characters           |
    +---------------+---------------------------------+----------------------------+
    | nvarchar      | Variable width Unicode string   | 4,000 characters           |
    +---------------+---------------------------------+----------------------------+
    | nvarchar(max) | Variable width Unicode string   | 536,870,912 characters     |
    +---------------+---------------------------------+----------------------------+
    | ntext         | Variable width Unicode string   | 2GB of text data           |
    +---------------+---------------------------------+----------------------------+
    | binary(n)     | Fixed width binary string       | 8,000 bytes                |
    +---------------+---------------------------------+----------------------------+
    | varbinary     | Variable width binary string    | 8,000 bytes                |
    +---------------+---------------------------------+----------------------------+
    | Varbinary(max)| Fixed width binary string       | 2GB                        |
    +---------------+---------------------------------+----------------------------+
    | image         | Variable width binary string    | 2GB                        |
    +---------------+---------------------------------+----------------------------+


Numeric Data Types
~~~~~~~~~~~~~~~~~~

    +---------------+--------------------------------------------------------------+
    |   Data Type   |         Description                                          |
    |               |                                                              |
    +===============+=================================+============================+
    | bit           | Integer that can be 0, 1, or NULL                            |
    +---------------+--------------------------------------------------------------+
    | tinyint       | Whole numbers from 0 to 255                                  |
    +---------------+--------------------------------------------------------------+
    | smallint      | Whole numbers between -32,768 and 32,767                     |
    +---------------+--------------------------------------------------------------+
    | int           | Whole numbers between -2,147,483,648 and 2,147,483,647       |
    +---------------+--------------------------------------------------------------+
    | bigint        | Whole numbers between -9,223,372,036,854,775,808 and         |
    |               |                                                              |
    |               | 9,223,372,036,854,775,807                                    |
    +---------------+--------------------------------------------------------------+
    | decimal(p,s)  | Fixed precision and scale numbers.                           |
    |               +--------------------------------------------------------------+
    |               |                                                              |
    |               | Allows numbers from -10^38 +1 to 10^38 –1.                   |
    |               +--------------------------------------------------------------+
    |               |                                                              |
    |               | The p parameter indicates the maximum total number of digits |
    |               |                                                              |
    |               | that can be stored (both to the left and to the right of the |
    |               |                                                              |
    |               | decimal point). p must be a value from 1 to 38.              |
    |               |                                                              |
    |               | The default is 18.                                           |
    |               +--------------------------------------------------------------+
    |               | The s parameter indicates the maximum number of digits       |
    |               |                                                              |
    |               | stored to the right of the decimal point. s must be a        |
    |               |                                                              |
    |               | value from 0 to p. Default value is 0                        |
    +---------------+--------------------------------------------------------------+
    | numeric(p,s)  | Fixed precision and scale numbers.                           |
    |               +--------------------------------------------------------------+
    |               | Allows numbers from -10^38 +1 to 10^38 –1.                   |
    |               +--------------------------------------------------------------+
    |               | The p parameter indicates the maximum total number of digits |
    |               |                                                              |
    |               | that can be stored (both to the left and to the right of the |
    |               |                                                              |
    |               | decimal point). p must be a value from 1 to 38.              |
    |               |                                                              |
    |               | The default is 18.                                           |
    |               +--------------------------------------------------------------+
    |               | The s parameter indicates the maximum number of digits       |
    |               |                                                              |
    |               | stored to the right of the decimal point. s must be a        |
    |               |                                                              |
    |               | value from 0 to p. Default value is 0                        |
    +---------------+--------------------------------------------------------------+
    | smallmoney    | Monetary data from -214,748.3648 to 214,748.3647             |
    +---------------+--------------------------------------------------------------+
    | money         | Monetary data from -922,337,203,685,477.5808 to              |
    |               |                                                              |
    |               | 922,337,203,685,477.5807                                     |
    +---------------+--------------------------------------------------------------+
    | float(n)      | Floating precision number data from                          |
    |               |                                                              |
    |               | -1.79E + 308 to 1.79E + 308.                                 |
    |               +--------------------------------------------------------------+
    |               | The n parameter indicates whether the field should hold 4    |
    |               |                                                              |
    |               | or 8 bytes. float(24) holds a 4-byte field and float(53)     |
    |               |                                                              |
    |               | holds an 8-byte field. Default value of n is 53.             |
    +---------------+---------------------------------+----------------------------+
    | real          | Floating precision number data from -3.40E + 38              |
    |               |                                                              |
    |               | to 3.40E + 38                                                |
    +---------------+---------------------------------+----------------------------+


Date and Time Data Types
~~~~~~~~~~~~~~~~~~~~~~~~

    +----------------+---------------------------------------------------------------+
    |   Data Type    |         Description                                           |
    |                |                                                               |
    +================+=================================+=============================+
    | datetime       | From January 1, 1753 to December 31, 9999 with                |
    |                |                                                               |
    |                | an accuracy of 3.33 milliseconds                              |
    +----------------+---------------------------------------------------------------+
    | datetime2      | From January 1, 0001 to December 31, 9999 with                |
    |                |                                                               |
    |                | an accuracy of 100 nanoseconds                                |
    +----------------+---------------------------------------------------------------+
    | smalldatetime  | From January 1, 1900 to June 6, 2079 with                     |
    |                |                                                               |
    |                | an accuracy of 1 minute                                       |
    +----------------+---------------------------------------------------------------+
    | date           | Store a date only. From January 1, 0001 to December 31, 9999  |
    +----------------+---------------------------------------------------------------+
    | time           | Store a time only to an accuracy of 100 nanoseconds           |
    +----------------+---------------------------------------------------------------+
    | datetimeoffset | The same as datetime2 with the addition of a time zone offset |
    +----------------+---------------------------------------------------------------+
    | timestamp      | Stores a unique number that gets updated every time a row     |
    |                |                                                               |
    |                | gets created or modified. The timestamp value is based upon   |
    |                |                                                               |
    |                | an internal clock and does not correspond to real time. Each  |
    |                |                                                               |
    |                | table may have only one timestamp variable                    |
    +----------------+---------------------------------------------------------------+


Other Data Types
~~~~~~~~~~~~~~~~

    +------------------+---------------------------------------------------------------+
    |   Data Type      |         Description                                           |
    |                  |                                                               |
    +==================+=================================+=============================+
    | sql_variant      | Stores up to 8,000 bytes of data of various data types,       |
    |                  |                                                               |
    |                  | except text, ntext, and timestamp                             |
    +------------------+---------------------------------------------------------------+
    | uniqueidentifier | Stores a globally unique identifier (GUID)                    |
    +------------------+---------------------------------------------------------------+
    | xml              | Stores XML formatted data. Maximum 2GB                        |
    +------------------+---------------------------------------------------------------+
    | cursor           | Stores a reference to a cursor used for database operations   |
    +------------------+---------------------------------------------------------------+
    | table            | Stores a result-set for later processing                      |
    +------------------+---------------------------------------------------------------+

The above tables as well as more information can be found at
`w3schools.com <https://www.w3schools.com/sql/sql_datatypes.asp>`_.

Java Data Types
---------------

There are two main sets of data types in Java, ``Primitive`` and ``Non-Primitive``.

``Primitive`` includes ``byte``, ``short``, ``int``, ``long``, ``float``, ``double``, ``boolean``, and ``char``

``Non-Primitive`` are ``Strings``, ``Arrays``, and ``Classes``

Primitive
~~~~~~~~~
    +------------------+---------------------------------------------------------------+
    |   Data Type      |         Description                                           |
    |                  |                                                               |
    +==================+=================================+=============================+
    | byte             | Stores whole numbers from -128 to 127                         |
    +------------------+---------------------------------------------------------------+
    | short            | Stores whole numbers from -32,768 to 32,767                   |
    +------------------+---------------------------------------------------------------+
    | int              | Stores whole numbers from -2,147,483,648 to 2,147,483,647     |
    +------------------+---------------------------------------------------------------+
    | long             | Stores whole numbers from -9,223,372,036,854,775,808 to       |
    |                  |                                                               |
    |                  | 9,223,372,036,854,775,807                                     |
    +------------------+---------------------------------------------------------------+
    | float            | Stores fractional numbers. Sufficient for storing             |
    |                  |                                                               |
    |                  | 6 to 7 decimal digits                                         |
    +------------------+---------------------------------------------------------------+
    | double           | Stores fractional numbers. Sufficient for storing             |
    |                  |                                                               |
    |                  | 15 decimal digits                                             |
    +------------------+---------------------------------------------------------------+
    | boolean          | Stores true or false values                                   |
    +------------------+---------------------------------------------------------------+
    | char             | Stores a single character/letter or ASCII values              |
    +------------------+---------------------------------------------------------------+

The above tables as well as more information can be found at
`w3schools.com <https://www.w3schools.com/java/java_data_types.asp>`_.

Troublesome Data Type
---------------------
``DATETIME`` seems to be one of the most troublesome of the data types in SQL SERVER.  ``DATETIME`` uses 8 bytes with
an accuracy of ``3 milliseconds``, as  long as the milliseconds end in 0, 3, or 7.  This can cause inaccurate
values from rounding errors.  Data corruption can be an issue if using ``DATETIMEFROMPART()``.

SQL Server's database engine stores a ``DATETIME`` value as two integers.  The first integer is the date and the second
is the time.  For example 12/15/2001 12:05:15.135.

Lets look at this in code:

    .. code-block:: SQL

       DECLARE @a DATETIME = '12/15/2001 12:05:15.135'
       SELECT @a;

Output:   ``2001-12-15 12:05:15.137``

Notice the date has been rearranged, which is trivial, the bigger issue is the milliseconds.  The time we used ending in
a 5 but the output ends in a 7.  To the average person the milliseconds my not matter but there still is the issue.
The question is why does this happen, unfortunately its not a simple answer.  Lets look at a couple more examples:


 .. code-block:: SQL

       DECLARE @a DATETIME = '12/15/2001 12:05:15.137'
       SELECT @a;

Output:   ``2001-12-15 12:05:15.137``


 .. code-block:: SQL

       DECLARE @a DATETIME = '12/15/2001 12:05:15.139'
       SELECT @a;

Output:   ``2001-12-15 12:05:15.140``

This boils down to the way SQL Server stores the ``DATETIME``.  SQL Server stores the ``DATETIME`` with
the precision of **3.33 milliseconds (0.00333 seconds)**.   The suggested workaround is if needing a more precise
date and time would be to use ``DATETIME2``.

 .. code-block:: SQL

       DECLARE @a DATETIME = '12/15/2001 12:05:15.139'
       SELECT @a;

Output:   ``2001-12-15 12:05:15.1390000``