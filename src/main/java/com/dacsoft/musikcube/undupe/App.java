package com.dacsoft.musikcube.undupe;

import org.sqlite.SQLiteJDBCLoader;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {

        final String DBLOC = "of C:/Users/David/.musikproject/musik_u.db" ;
        // load the sqlite-JDBC driver using the current class loader
        Class.forName("org.sqlite.JDBC");

        Map<Integer, DupeDetail> dupes = new HashMap<Integer, DupeDetail>()     ;

        Connection connection = null;
        try
        {
            System.out.println(String.format("running in %s mode", SQLiteJDBCLoader.isNativeMode() ? "native" : "pure-java"));
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:///C:/Users/David/.musikproject/musik_u.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

//            statement.executeUpdate("drop table if exists person");
//            statement.executeUpdate("create table person (id integer, name string)");
//            statement.executeUpdate("insert into person values(1, 'leo')");
//            statement.executeUpdate("insert into person values(2, 'yui')");
            ResultSet rs = statement.executeQuery("select songid, title,album,artist, count(*) as rowct, filename\n" +
                    "                               from songs\n" +
                    "                            --    where artist='Lunadyas'\n" +
                    "                          -- where title is not null and title <> ''  and artist is not null and artist <> '' and album is not null\n" +
                    "                              group by title,album,artist                        \n" +
                    "                              having count(*) > 1");
            while(rs.next())
            {
                // read the result set
                DupeDetail det = new DupeDetail(rs);

                System.out.println(det);
                Map<Integer, DupeDetail> expanded = expandDupeDetails(connection, det);
                Map<Integer, DupeDetail> theseDie = decideWhoDies(expanded);
                killTheseDupes(connection, theseDie);

//                dupes.put(det.songid, det);
            }
        }
        catch(SQLException e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
        finally
        {
            try
            {
                if(connection != null)
                    connection.close();
            }
            catch(SQLException e)
            {
                // connection close failed.
                System.err.println(e);
            }
        }
    }

    private static Map<Integer, DupeDetail> decideWhoDies(Map<Integer, DupeDetail> expanded) {
        return expanded ; // new HashMap<Integer,DupeDetail>();  //To change body of created methods use File | Settings | File Templates.
    }

    private static void killTheseDupes(Connection conn,
                                       Map<Integer,DupeDetail> theseDie) {
        for( Integer songid : theseDie.keySet()) {
            DupeDetail killIt = theseDie.get(songid);
            System.out.println("delete " + killIt.toString());
        }
    }

    static Map<Integer, DupeDetail> expandDupeDetails(Connection connection,DupeDetail dupe) throws SQLException {
        Map<Integer, DupeDetail> expandedDupes = new HashMap<Integer, DupeDetail>();

        ResultSet rs = null;
        Statement statement = null;

        try
        {
            statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

//            for( Integer theDetId : dupes.keySet()) {
                DupeDetail findDet = dupe; // dupes.get(theDetId);
            String query = "select songid, title,album,artist,0 as rowct, filename\n" +
                    "                               from songs\n" +
                    "                              where title='" + findDet.title + "'\n" +
                    "                              and album='" + findDet.album + "'\n" +
                    "                            and artist='" + findDet.artist + "'\n";

                rs = statement.executeQuery(query);

                while(rs.next())
                {
                    // read the result set
                    DupeDetail aDetail = new DupeDetail(rs);
                    expandedDupes.put(aDetail.songid, aDetail);

                }
//            }

        }
        catch(SQLException e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
        finally
        {
            closeNoThrow(rs);
            closeNoThrow(statement);
        }
        return expandedDupes;
    }

    static void closeNoThrow(Statement rs) {
        try
        {
            if(rs != null)
                rs.close();
        }
        catch(SQLException e)
        {
            // connection close failed.
            System.err.println(e);
        }
    }
    static void closeNoThrow(ResultSet rs) {
        try
        {
            if(rs != null)
                rs.close();
        }
        catch(SQLException e)
        {
            // connection close failed.
            System.err.println(e);
        }
    }
}
