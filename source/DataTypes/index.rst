Data Types
==========

There are numerous **Data Types** in SQL and Java.  Below are just some of the common ones used:

SQL Data Types
--------------

SQL Server String Data Types
^^^^^^^^^^^^^^^^^^^^^^^^^^^^

    +---------------+---------------------------------+----------------------------+
    |   Data Type   |         Description             |         Max Size           |
    |               |                                 |                            |
    +===============+=================================+============================+
    | char(n)       | Fixed width character string    | 8,000 characters           |
    +---------------+---------------------------------+----------------------------+
    | varchar(n)    | Variable width character string | 8,000 characters           |
    +---------------+---------------------------------+----------------------------+
    | nchar         | Fixed width Unicode string      | 4,000 characters           |
    +---------------+---------------------------------+----------------------------+
    | nvarchar      | Variable width Unicode string   | 4,000 characters           |
    +---------------+---------------------------------+----------------------------+
    | nvarchar(max) | Variable width Unicode string   | 536,870,912 characters     |
    +---------------+---------------------------------+----------------------------+

SQL Server Numeric Data Types
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

    +---------------+--------------------------------------------------------------+
    |   Data Type   |         Description                                          |
    |               |                                                              |
    +===============+=================================+============================+
    | int           | Whole numbers between -2,147,483,648 and 2,147,483,647       |
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


SQL Server Date and Time Data Types
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

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
    | date           | Store a date only. From January 1, 0001 to December 31, 9999  |
    +----------------+---------------------------------------------------------------+
    | time           | Store a time only to an accuracy of 100 nanoseconds           |
    +----------------+---------------------------------------------------------------+
    | datetimeoffset | The same as datetime2 with the addition of a time zone offset |
    +----------------+---------------------------------------------------------------+

The above tables as well as more information can be found at
`w3schools.com <https://www.w3schools.com/sql/sql_datatypes.asp>`_.

SQL Server Troublesome Data Type
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
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



Java Data Types
---------------

There are two main sets of data types in Java, ``Primitive`` and ``Non-Primitive``.
``Primitive`` includes ``byte``, ``short``, ``int``, ``long``, ``float``, ``double``, ``boolean``, and ``char``
``Non-Primitive`` are ``Strings``, ``Arrays``, and ``Classes``

Primitive
^^^^^^^^^
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

