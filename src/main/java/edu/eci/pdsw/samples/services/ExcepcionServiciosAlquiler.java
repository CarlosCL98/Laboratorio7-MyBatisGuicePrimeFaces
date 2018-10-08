package edu.eci.pdsw.samples.services;

import org.apache.ibatis.exceptions.PersistenceException;

public class ExcepcionServiciosAlquiler extends Exception {

	private static final long serialVersionUID = 1L;
	
	public static final String  EXCEPCIONES = "";
    
    public ExcepcionServiciosAlquiler(String message){
        super(message);
    }
    
    public ExcepcionServiciosAlquiler(String message, PersistenceException ex){
        super(message);
    } 
	
}