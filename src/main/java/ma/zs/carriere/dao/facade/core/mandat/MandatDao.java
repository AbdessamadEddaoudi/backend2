package ma.zs.carriere.dao.facade.core.mandat;


import ma.zs.carriere.bean.core.mandat.Mandat;
import ma.zs.carriere.zynerator.repository.AbstractRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MandatDao extends AbstractRepository<Mandat,Long> {

    List<Mandat> findByEmployeId(Long id);
    int deleteByEmployeId(Long id);
    long countByEmployeId(Long id);
    List<Mandat> findByResponsabiliteId(Long id);
    int deleteByResponsabiliteId(Long id);
    long countByResponsabiliteRef(String ref);

    @Query("SELECT NEW Mandat(item.id,item.employe) FROM Mandat item")
    List<Mandat> findAllOptimized();

}
