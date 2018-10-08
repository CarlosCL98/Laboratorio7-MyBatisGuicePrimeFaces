package edu.eci.pdsw.sampleprj.dao.mybatis.mappers;

import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.ItemRentado;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author 2106913
 */
public interface ClienteMapper {
    
    public Cliente consultarCliente(@Param("idcli") int id);
    
    public void insertarCliente(@Param("cliente") Cliente cli);
    
    /**
     * Registrar un nuevo item rentado asociado al cliente identificado
     * con 'idc' y relacionado con el item identificado con 'idi'
     * @param id
     * @param idit
     * @param fechainicio
     * @param fechafin 
     */
    public void agregarItemRentadoACliente(@Param("idcli") int id, 
    		@Param("iditem") int idit, 
    		@Param("fechai") Date fechainicio,
    		@Param("fechaf") Date fechafin);

    /**
     * Consultar todos los clientes
     * @return 
     */
    public List<Cliente> consultarClientes();
    
    public ItemRentado consultarItemRentado(@Param("idir") int idir, @Param("idcli") int idcli);
    
}