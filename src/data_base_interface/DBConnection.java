package data_base_interface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.StringTokenizer;
import java.util.Vector;


public class DBConnection {
    static String driver = "org.postgresql.Driver";
    static String url = "jdbc:postgresql://dblabs.it.teithe.gr:5432/it185288";
    static Connection dbcon = null;
    static Statement state = null;
    static ResultSet res = null;
    static String username = "";
    static String password = "";
    
    
    
    
    public static Statement getStatement()throws ClassNotFoundException, SQLException{
        try{
            Class.forName (driver);
            dbcon = DriverManager.getConnection (url, username, password);
            state = dbcon.createStatement();
            return state;
        }
        catch(ClassNotFoundException ex){
            System.out.println("Here is the problem. in connect method");
            throw ex;
        }
    }
    
    
    public static Vector<String[]> loadDBinVector(ResultSet res, String alias) throws SQLException{
        Vector<String[]> vec = new Vector<String[]>(25);
        StringTokenizer tok;
        int counter = 0;
        while(res.next()){
                tok = new StringTokenizer(res.getString(alias),"(,)");
                String[] array = new String[8];
                while(tok.hasMoreTokens())
                    array[counter++] = tok.nextToken();
                vec.addElement(array);
                counter = 0; 
            }
        return vec;
    }
    
    
    public static void closeConnection() throws SQLException{
        state.close();
        dbcon.close();
    }
    
}