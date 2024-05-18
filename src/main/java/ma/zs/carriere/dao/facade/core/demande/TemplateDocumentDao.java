package ma.zs.carriere.dao.facade.core.demande;

import org.springframework.data.jpa.repository.Query;
import ma.zs.carriere.zynerator.repository.AbstractRepository;
import ma.zs.carriere.bean.core.demande.TemplateDocument;
import org.springframework.stereotype.Repository;
import ma.zs.carriere.bean.core.demande.TemplateDocument;
import java.util.List;


@Repository
public interface TemplateDocumentDao extends AbstractRepository<TemplateDocument,Long>  {
    TemplateDocument findByRef(String ref);
    int deleteByRef(String ref);

    List<TemplateDocument> findByTypeDocumentId(Long id);
    int deleteByTypeDocumentId(Long id);
    long countByTypeDocumentId(Long id);

    @Query("SELECT NEW TemplateDocument(item.id,item.ref) FROM TemplateDocument item")
    List<TemplateDocument> findAllOptimized();

}
