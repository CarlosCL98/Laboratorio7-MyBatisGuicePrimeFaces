package edu.eci.pdsw.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.eci.pdsw.sampleprj.dao.ClienteDAO;
import org.apache.ibatis.exceptions.PersistenceException;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;
import edu.eci.pdsw.samples.entities.TipoItem;
import java.sql.SQLException;
import java.util.List;

public class MyBATISClienteDAO implements ClienteDAO{

	@Inject
	private ClienteMapper clienteMapper;    

	@Override
	public void save(Cliente cli) throws PersistenceException{
		try{
			clienteMapper.insertarCliente(cli);
		}
		catch(org.apache.ibatis.exceptions.PersistenceException e){
			throw new PersistenceException("Error al registrar el item "+cli.toString(),e);
		}        
	}

	@Override
	public Cliente load(int id) throws PersistenceException {
		try{
			return clienteMapper.consultarCliente(id);
		}
		catch(org.apache.ibatis.exceptions.PersistenceException e){
			throw new PersistenceException("Error al consultar el item "+id,e);
		}
	}
	
	@Override
	public List<Cliente> load() throws PersistenceException {
		try{
			return clienteMapper.consultarClientes();
		}
		catch(org.apache.ibatis.exceptions.PersistenceException e){
			throw new PersistenceException("Error al consultar los clientes item", e);
		}
	}

	@Override
	public void saveItemRentado(ItemRentado ir, long idcli) throws PersistenceException {
		try{
			clienteMapper.agregarItemRentadoACliente(idcli, ir.getItem().getId(), ir.getFechainiciorenta(), ir.getFechafinrenta());
		}
		catch(org.apache.ibatis.exceptions.PersistenceException e){
			throw new PersistenceException("Error al agregar el item rentado al cliente", e);
		}
	}

	@Override
	public void vetarCliente(long idcli, boolean estado) throws PersistenceException {
		try{
			clienteMapper.vetarCliente(idcli, estado);
		}
		catch(org.apache.ibatis.exceptions.PersistenceException e){
			throw new PersistenceException("Error al vetar al cliente "+idcli, e);
		}
	}
}