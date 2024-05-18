package ma.zs.carriere.dao.facade.core.commun;

import ma.zs.carriere.zynerator.repository.AbstractRepository;
import ma.zs.carriere.bean.core.commun.Employe;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface EmployeDao extends AbstractRepository<Employe,Long>  {

    List<Employe> findByDiplomeId(Long id);
    int deleteByDiplomeId(Long id);
    long countByDiplomeRef(String ref);
    List<Employe> findByEntiteAdminId(Long id);
    int deleteByEntiteAdminId(Long id);
    long countByEntiteAdminRef(String ref);
    Employe findByUsername(String username);


}
