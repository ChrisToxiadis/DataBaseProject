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
    static String url = "jdbc:postgresql://dblabs.it.teithe.gr:5432/";
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
    
    
    public static Vector<Vector<String>> loadDBinVector(ResultSet res, String alias) throws SQLException{
        Vector<Vector<String>> vec = new Vector<Vector<String>>(25);
        StringTokenizer tok;
        while(res.next()){
                tok = new StringTokenizer(res.getString(alias),"(,)\"");
                Vector<String> vector = new Vector<String>(10);
                while(tok.hasMoreTokens())
                    vector.addElement(tok.nextToken());
                vec.addElement(vector);
        }
        return vec;
    }
    
    
    public static void closeConnection() throws SQLException{
        state.close();
        dbcon.close();
    }
    
}