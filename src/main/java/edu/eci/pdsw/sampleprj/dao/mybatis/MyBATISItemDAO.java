package edu.eci.pdsw.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.pdsw.sampleprj.dao.ItemDAO;
import org.apache.ibatis.exceptions.PersistenceException;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.eci.pdsw.samples.entities.Item;
import java.util.List;

public class MyBATISItemDAO implements ItemDAO {

    @Inject
    private ItemMapper itemMapper;

    @Override
    public void save(Item it) throws PersistenceException {
        try {
            itemMapper.insertarItem(it);
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al registrar el item " + it.toString(), e);
        }
    }

    @Override
    public Item load(int id) throws PersistenceException {
        try {
            return itemMapper.consultarItem(id);
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al consultar el item " + id, e);
        }
    }

    @Override
    public List<Item> load() throws PersistenceException {
        try {
            return itemMapper.consultarItems();
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al consultar el items", e);
        }
    }

    @Override
    public List<Item> loadItemsDisponibles() {
        try {
            return itemMapper.consultarItemsDisponibles();
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al consultar los items", e);
        }
    }

    @Override
    public void saveTarifaItem(int id, long tarifa) throws PersistenceException {
        try {
            itemMapper.cambiarTarifaItem(id, tarifa);
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al cambiar la tarifa del item" + id, e);
        }
    }
    
    @Override
    public List<Item> loadItemsNoRegresados(long documento) throws PersistenceException {
        try {
            return itemMapper.consultarItemsNoRegresados(documento);
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al consultar los items no regresados", e);
        }
    }

    @Override
    public void eliminarItem(Item item) throws PersistenceException {
        try {
            itemMapper.eliminarItem(item);
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al eliminar el item " + item.toString(), e);
        }
    }
}
