package ma.zs.carriere.dao.facade.core.commun;

import org.springframework.data.jpa.repository.Query;
import ma.zs.carriere.zynerator.repository.AbstractRepository;
import ma.zs.carriere.bean.core.commun.EntiteAdmin;
import org.springframework.stereotype.Repository;
import ma.zs.carriere.bean.core.commun.EntiteAdmin;
import java.util.List;


@Repository
public interface EntiteAdminDao extends AbstractRepository<EntiteAdmin,Long>  {
    EntiteAdmin findByRef(String ref);
    int deleteByRef(String ref);

    List<EntiteAdmin> findByChefDepartId(Long id);
    int deleteByChefDepartId(Long id);
    long countByChefDepartId(Long id);

    @Query("SELECT NEW EntiteAdmin(item.id,item.departement) FROM EntiteAdmin item")
    List<EntiteAdmin> findAllOptimized();

}
