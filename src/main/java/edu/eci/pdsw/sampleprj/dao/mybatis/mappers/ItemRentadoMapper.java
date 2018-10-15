package edu.eci.pdsw.sampleprj.dao.mybatis.mappers;

import org.apache.ibatis.annotations.Param;

import edu.eci.pdsw.samples.entities.ItemRentado;

public interface ItemRentadoMapper {

	public ItemRentado consultarItemRentado(@Param("idi") int idi);
	
	//public void insertarItemRentado(@Param("itemRentado") ItemRentado ir, @Param("idcli") long idcli);

}
