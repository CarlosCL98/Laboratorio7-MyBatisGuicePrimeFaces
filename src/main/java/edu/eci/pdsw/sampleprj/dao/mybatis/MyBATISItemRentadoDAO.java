package edu.eci.pdsw.sampleprj.dao.mybatis;

import org.apache.ibatis.exceptions.PersistenceException;

import com.google.inject.Inject;

import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ItemRentadoMapper;
import edu.eci.pdsw.samples.entities.ItemRentado;
import edu.eci.pdsw.sampleprj.dao.ItemRentadoDAO;
import edu.eci.pdsw.samples.entities.Item;

public class MyBATISItemRentadoDAO implements ItemRentadoDAO {

    @Inject
    private ItemRentadoMapper itemRentadoMapper;

    @Override
    public ItemRentado load(int idi) throws PersistenceException {
        try {
            return itemRentadoMapper.consultarItemRentado(idi);
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al consultar el item rentado " + idi, e);
        }
    }

    @Override
    public void eliminarAlquiler(Item item) throws PersistenceException {
        try {
            itemRentadoMapper.eliminarAlquiler(item);
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al eliminar el alquiler del item " + item.toString(), e);
        }
    }
    
}
