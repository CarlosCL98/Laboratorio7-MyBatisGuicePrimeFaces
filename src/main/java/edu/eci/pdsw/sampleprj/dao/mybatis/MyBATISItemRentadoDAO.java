package edu.eci.pdsw.sampleprj.dao.mybatis;

import org.apache.ibatis.exceptions.PersistenceException;

import com.google.inject.Inject;

import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ItemRentadoMapper;
import edu.eci.pdsw.samples.entities.ItemRentado;
import edu.eci.pdsw.sampleprj.dao.ItemRentadoDAO;

public class MyBATISItemRentadoDAO implements ItemRentadoDAO {

	@Inject
	private ItemRentadoMapper itemRentadoMapper;    

	@Override
	public void save(ItemRentado ir) throws PersistenceException{
		try{
			itemRentadoMapper.consultarItemRentado(ir.getItem().getId());
		}
		catch(org.apache.ibatis.exceptions.PersistenceException e){
			throw new PersistenceException("Error al registrar el item "+ir.toString(),e);
		}        
	}

	@Override
	public ItemRentado load(int idi) throws PersistenceException {
		try{
			return itemRentadoMapper.consultarItemRentado(idi);
		}
		catch(org.apache.ibatis.exceptions.PersistenceException e){
			throw new PersistenceException("Error al consultar el item "+idi,e);
		}
	}
}
