package edu.eci.pdsw.sampleprj.dao;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;

import edu.eci.pdsw.samples.entities.Item;

public interface ItemDAO {

   public void save(Item it) throws PersistenceException;

   public Item load(int id) throws PersistenceException;
   
   public List<Item> load() throws PersistenceException;

   public List<Item> loadItemsDisponibles() throws PersistenceException;
   
   public void saveTarifaItem(int id, long tarifa) throws PersistenceException;
   
   public List<Item> loadItemsNoRegresados(long documento) throws PersistenceException;
}
