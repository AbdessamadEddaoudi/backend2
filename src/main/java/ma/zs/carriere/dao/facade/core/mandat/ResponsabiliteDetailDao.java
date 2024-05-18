package ma.zs.carriere.dao.facade.core.mandat;

import org.springframework.data.jpa.repository.Query;
import ma.zs.carriere.zynerator.repository.AbstractRepository;
import ma.zs.carriere.bean.core.mandat.ResponsabiliteDetail;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ResponsabiliteDetailDao extends AbstractRepository<ResponsabiliteDetail,Long>  {
    ResponsabiliteDetail findByRef(String ref);
    int deleteByRef(String ref);

    List<ResponsabiliteDetail> findByResponsabiliteId(Long id);
    int deleteByResponsabiliteId(Long id);
    long countByResponsabiliteRef(String ref);

    @Query("SELECT NEW ResponsabiliteDetail(item.id,item.ref) FROM ResponsabiliteDetail item")
    List<ResponsabiliteDetail> findAllOptimized();

}
