package com.provident.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.jasypt.util.text.BasicTextEncryptor;

/**
 *
 * @author Christian
 */
public class DBConnection {
    
    private static DBConnection instances;
    private Connection connection;
    
    private String driver;
    private String url;
    private String user;
    private String password;    
    
    private static Logger log = Logger.getLogger(DBConnection.class);    
       
    public static DBConnection getInstances()
    {
        if(instances == null){
            instances = new DBConnection();
        }
        
        return instances;
    }

    public DBConnection()
    {        }
    
    public void configureConnection(String driver, String url, String user, String pass){
        this.driver = driver;
        this.url = url;
        this.user = user;
        this.password = pass;
        
        connectDB();
    }    
    
    public void configureConnection(String file){
        Properties config = loadProperties(file);
        String passwordDecrypt = decrypt(config.getProperty("key"), config.getProperty("password"));        
        this.configureConnection(config.getProperty("driver"), config.getProperty("url"), config.getProperty("user"), passwordDecrypt);
    }

    public Connection getConnection() {
        return connection;
    }
    
    public boolean isDBConnected(){
        return (connection != null);
    }
    
    private void connectDB(){
        try{
            log.info("Buat Koneksi Ke Database");
            Class.forName(this.driver);
            connection = DriverManager.getConnection(this.url, this.user, this.password);   
            
        }catch(ClassNotFoundException ex){
            log.error(ex.getMessage(), ex);
        }catch(SQLException ex){
            log.error(ex.getMessage(), ex);
        }finally{
            log.info("Connection to DB succesfull = " + isDBConnected());
        }
    }
    
    
    
    public String decrypt(String key, String passwordEnc){
        String passwordDecrypt = null;
        
        BasicTextEncryptor decrypt = new BasicTextEncryptor();
        decrypt.setPassword(key);
        passwordDecrypt = decrypt.decrypt(passwordEnc);
        
        return passwordDecrypt;
    }
    
    public static Properties loadProperties(String file)
    {
            Properties p = new Properties();
            try
            {
                    log.info("Load Properties from file Konfigurasi : " + file);			
                    FileInputStream in = new FileInputStream(file);
                    p.load(in);
                    in.close();

            }
            catch(IOException ex)
            {
                    System.err.println(ex.getMessage());
                    log.error(ex.getMessage(),ex);
            }

            return p;
    }
    
    public static void main (String [] args){
        
        DBConnection db = DBConnection.getInstances();
//        db.configureConnection("org.postgresql.Driver", "jdbc:postgresql://localhost:5432/adempiere", "adempiere1", "adempiere");
        db.configureConnection("config.properties");
    }
    
    
}
