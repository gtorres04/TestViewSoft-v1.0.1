/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testviewsoft.dao.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author GerlinOTS
 */
public class Utileria {
    private static EntityManagerFactory emf=null;
    public static EntityManagerFactory getEntityManagerFactory(){
        return emf;
    }
    static{
        try{
            emf=Persistence.createEntityManagerFactory("TestViewSoft-v1.0.1PU");
        }catch(Throwable t){
            System.out.println("Error al iniciar Entity Manager Factory: "+t);
            t.printStackTrace();
        }
    }
}
