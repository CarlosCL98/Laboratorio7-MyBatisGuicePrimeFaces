package edu.eci.pdsw.sampleprj.dao.mybatis.mappers;

import edu.eci.pdsw.samples.entities.Item;
import org.apache.ibatis.annotations.Param;

import edu.eci.pdsw.samples.entities.ItemRentado;

import org.mybatis.guice.transactional.Transactional;

public interface ItemRentadoMapper {

    public ItemRentado consultarItemRentado(@Param("idi") int idi);
    
    @Transactional
    public void eliminarAlquiler(@Param("item") Item item);   

}
