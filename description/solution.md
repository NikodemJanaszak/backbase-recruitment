**Backbase recruitment challenge**

Main purpose of this application is to deliver API,
which allows user to get movies information nominated to Oscar Academy Awards since 1927 to 2010.

To resolve challenge I decided to use most popular and recommended technologies.
Combining them with multi-tier architecture was the most obvious, because application
is rather small in size and this approach is the best way to satisfy requirements.

Data is stored H2 database in file. It allows to quick verification of presented solution
without setting up more complex DBMS.

There is also added verification with API token that allows user to request API only with a valid token.

For testing business logic used most obvious testing libraries i.e. JUnit 5 and Mockito.