package edu.eci.pdsw.sampleprj.dao.mybatis.mappers;

import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.ItemRentado;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.mybatis.guice.transactional.Transactional;

public interface ClienteMapper {
    
    /**
     * 
     * @param id
     * @return 
     */
    public Cliente consultarCliente(@Param("idcli") int id);
    
    /**
     * 
     * @param cli 
     */
    @Transactional
    public void insertarCliente(@Param("cliente") Cliente cli);

    /**
     * Registrar un nuevo item rentado asociado al cliente identificado con
     * 'idc' y relacionado con el item identificado con 'idi'
     *
     * @param id
     * @param idit
     * @param fechainicio
     * @param fechafin
     */
    @Transactional
    public void agregarItemRentadoACliente(@Param("idcli") long id,
            @Param("iditem") int idit,
            @Param("fechai") Date fechainicio,
            @Param("fechaf") Date fechafin);

    /**
     * Consultar todos los clientes
     *
     * @return
     */
    public List<Cliente> consultarClientes();
    
    /**
     * 
     * @param idir
     * @param idcli
     * @return 
     */
    public ItemRentado consultarItemRentado(@Param("idir") int idir, @Param("idcli") int idcli);
    
    /**
     * 
     * @param id
     * @param estado 
     */
    @Transactional
    public void vetarCliente(@Param("idcli") long id, @Param("estado") boolean estado);

}
