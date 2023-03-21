/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author user
 */
public class Koneksi {
    public Connection getConnection(){
        try{
            return DriverManager.getConnection("jdbc:mysql://localhost/toko_alat_musik",
                    "root","");
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }
    
}
