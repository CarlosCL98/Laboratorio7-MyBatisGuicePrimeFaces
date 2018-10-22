package edu.eci.pdsw.sampleprj.dao;

import edu.eci.pdsw.samples.entities.Item;

import org.apache.ibatis.exceptions.PersistenceException;

import edu.eci.pdsw.samples.entities.ItemRentado;

public interface ItemRentadoDAO {

    public ItemRentado load(int id) throws PersistenceException;
    
    public void eliminarAlquiler(Item item) throws PersistenceException;

}
