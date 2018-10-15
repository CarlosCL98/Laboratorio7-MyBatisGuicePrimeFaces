package edu.eci.pdsw.sampleprj.dao.mybatis.mappers;

import edu.eci.pdsw.samples.entities.TipoItem;
import java.util.List;
import org.mybatis.guice.transactional.Transactional;

/**
 *
 * @author 2106913
 */
public interface TipoItemMapper {

    public List<TipoItem> getTiposItems();

    public TipoItem getTipoItem(int id);
    
    @Transactional
    public void addTipoItem(String des);

}
