# IMDB-Database-Dumper
Creates a SQLite database given an IMDB.com search query.

This was used for a personal project, I figured someone could have some use for it.

Given a URL from imdb such as : 

http://www.imdb.com/search/title?num_votes=30000,&release_date=1990-01-01,&user_rating=7.0,10

it fills up a SQL with all the entries.

#TODO : 

 1. All fields in the database?

#STRUCTURE

<p align="center">
  <img src="https://raw.githubusercontent.com/arocketman/IMDB-Database-Dumper/master/Class%20Diagram.PNG"/>
</p>


#DEPENDENCIES

```
    <dependencies>
        <dependency>
            <!-- jsoup HTML parser library @ http://jsoup.org/ -->
            <groupId>org.jsoup</groupId>
            <artifactId>jsoup</artifactId>
            <version>1.8.2</version>
        </dependency>
        <dependency>
            <groupId>org.xerial</groupId>
            <artifactId>sqlite-jdbc</artifactId>
            <version>3.8.10.1</version>
        </dependency>
        <dependency>
            <groupId>org.json</groupId>
            <artifactId>json</artifactId>
            <version>20141113</version>
        </dependency>
        <dependency>
            <groupId>commons-lang</groupId>
            <artifactId>commons-lang</artifactId>
            <version>2.6</version>
        </dependency>
    </dependencies>
```




