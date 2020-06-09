package dao;
import java.util.*;

import models.Livre;

import java.sql.*; 
public class Dao {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
    

  //Get All Modules
    private static final String SELECT_ALL_Livres ="SELECT * FROM  livres";
    
  //Get Livre by id
    private static final String Select_Livre_byID = "SELECT * FROM livres where code=?";

    public Dao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    } 
    
    // DB Connection
    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver"); 
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(
                                        jdbcURL, jdbcUsername, jdbcPassword);
        }
    }
    
    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }
    
    // selectioner tous les livres
    public List<Livre> getAllLivres() {
    	try {
    	//System.out.println("in the getClasse Function");
    	
    	 List<Livre> Livres_list=new ArrayList<Livre>();
    	 connect();
    	 PreparedStatement statement = jdbcConnection.prepareStatement(SELECT_ALL_Livres);
    	 ResultSet resultSet = statement.executeQuery();
    	 while(resultSet.next()){  
    		 Livre livre = new Livre();
    		 livre.setCodeLivre(resultSet.getInt(1));
    		// System.out.println(resultSet.getInt(1));
    		 livre.setTitreLivre(resultSet.getString(2));
    		 livre.setPrixLivre(resultSet.getDouble(3));
    		 Livres_list.add(livre);  
            }  
    	    resultSet.close();
            statement.close();
            disconnect(); 
    	 return  Livres_list;
    	
    }
    catch(SQLException e) {
    	e.printStackTrace();
    	return null;
    }

    }
    
    // selectioner livre avec codeLivre
    public Livre getLivrebyID(String Code) {
    	try {
    	 connect();
    	 PreparedStatement statement = jdbcConnection.prepareStatement(Select_Livre_byID);
    	 statement.setString(1 ,Code);
    	 ResultSet resultSet = statement.executeQuery();
    	 Livre livre =null;
    	if(resultSet.next()){  
    		  livre = new Livre();
    		 livre.setCodeLivre(resultSet.getInt(1));
    		 livre.setTitreLivre(resultSet.getString(2));
    		 livre.setPrixLivre(resultSet.getDouble(3)); 
            }  
    	    resultSet.close();
            statement.close();
            disconnect(); 
    	 return  livre;
    	
    }
    catch(SQLException e) {
    	e.printStackTrace();
    	return null;
    }

    }
  
}
