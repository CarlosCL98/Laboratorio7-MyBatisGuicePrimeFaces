package edu.eci.pdsw.sampleprj.dao.mybatis.mappers;

import edu.eci.pdsw.samples.entities.Item;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.mybatis.guice.transactional.Transactional;

public interface ItemMapper {

    public List<Item> consultarItems();

    public Item consultarItem(@Param("idit") int id);
    
    @Transactional
    public void insertarItem(@Param("item") Item it);

    public List<Item> consultarItemsDisponibles();
    
    @Transactional
    public void cambiarTarifaItem(@Param("idit") int id, @Param("tarifa") long tarifa);
    
    public List<Item> consultarItemsNoRegresados(@Param("documento") long documento);
}
