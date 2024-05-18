package ma.zs.carriere.dao.facade.core.mandat;

import org.springframework.data.jpa.repository.Query;
import ma.zs.carriere.zynerator.repository.AbstractRepository;
import ma.zs.carriere.bean.core.mandat.Responsabilite;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ResponsabiliteDao extends AbstractRepository<Responsabilite,Long>  {
    Responsabilite findByRef(String ref);
    int deleteByRef(String ref);

    List<Responsabilite> findByEchelonMinId(Long id);
    int deleteByEchelonMinId(Long id);
    long countByEchelonMinId(Long id);

    @Query("SELECT NEW Responsabilite(item.id,item.nom) FROM Responsabilite item")
    List<Responsabilite> findAllOptimized();

}
