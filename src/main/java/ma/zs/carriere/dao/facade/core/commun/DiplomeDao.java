package ma.zs.carriere.dao.facade.core.commun;

import org.springframework.data.jpa.repository.Query;
import ma.zs.carriere.zynerator.repository.AbstractRepository;
import ma.zs.carriere.bean.core.commun.Diplome;
import org.springframework.stereotype.Repository;
import ma.zs.carriere.bean.core.commun.Diplome;
import java.util.List;


@Repository
public interface DiplomeDao extends AbstractRepository<Diplome,Long>  {
    Diplome findByRef(String ref);
    int deleteByRef(String ref);

    List<Diplome> findByEchelonId(Long id);
    int deleteByEchelonId(Long id);
    long countByEchelonId(Long id);

    @Query("SELECT NEW Diplome(item.id,item.libelle) FROM Diplome item")
    List<Diplome> findAllOptimized();

}
