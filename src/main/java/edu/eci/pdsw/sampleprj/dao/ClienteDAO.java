package edu.eci.pdsw.sampleprj.dao;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;

import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.ItemRentado;

public interface ClienteDAO {
	
	public void save(Cliente cli) throws PersistenceException;
	
	public void saveItemRentado(ItemRentado ir, long idcli) throws PersistenceException;
	
	public Cliente load(int id) throws PersistenceException;
	
	public List<Cliente> load() throws PersistenceException;
	
	public void vetarCliente(long idcli, boolean estado) throws PersistenceException;
        
        public void eliminarCliente(Cliente cliente) throws PersistenceException;
}