package ma.zs.carriere.dao.facade.core.demande;

import ma.zs.carriere.zynerator.repository.AbstractRepository;
import ma.zs.carriere.bean.core.demande.EtatDemandeDocument;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface EtatDemandeDocumentDao extends AbstractRepository<EtatDemandeDocument,Long>  {



}
