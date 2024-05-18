package ma.zs.carriere.dao.facade.core.demande;

import org.springframework.data.jpa.repository.Query;
import ma.zs.carriere.zynerator.repository.AbstractRepository;
import ma.zs.carriere.bean.core.demande.DemandeDocument;
import org.springframework.stereotype.Repository;
import ma.zs.carriere.bean.core.demande.DemandeDocument;
import java.util.List;


@Repository
public interface DemandeDocumentDao extends AbstractRepository<DemandeDocument,Long>  {
    DemandeDocument findByRef(String ref);
    int deleteByRef(String ref);

    List<DemandeDocument> findByEmployeId(Long id);
    int deleteByEmployeId(Long id);
    long countByEmployeId(Long id);
    List<DemandeDocument> findByTypeDocumentId(Long id);
    int deleteByTypeDocumentId(Long id);
    long countByTypeDocumentId(Long id);

    @Query("SELECT NEW DemandeDocument(item.id,item.ref) FROM DemandeDocument item")
    List<DemandeDocument> findAllOptimized();

}
