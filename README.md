# IMDB-Database-Dumper
Creates a SQLite database given an IMDB.com search query.

This was used for a personal project, I figured someone could have some use for it.

First, create the DB : 

    CREATE TABLE movies (ID STRING PRIMARY KEY, Title TEXT NOT NULL, Actors TEXT, Rating TEXT, Year TEXT, Plot TEXT, Genre TEXT);

Given a URL from imdb such as : 

http://www.imdb.com/search/title?num_votes=30000,&release_date=1990-01-01,&user_rating=7.0,10`

it fills up a SQL with all the entries.

#TODO : 

 1. Automatic table creation
 2. Documentation
 3. All fields in the database?





