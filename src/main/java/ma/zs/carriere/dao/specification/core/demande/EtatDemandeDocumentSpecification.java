package  ma.zs.carriere.dao.specification.core.demande;

import ma.zs.carriere.dao.criteria.core.demande.EtatDemandeDocumentCriteria;
import ma.zs.carriere.bean.core.demande.EtatDemandeDocument;
import ma.zs.carriere.zynerator.specification.AbstractSpecification;


public class EtatDemandeDocumentSpecification extends  AbstractSpecification<EtatDemandeDocumentCriteria, EtatDemandeDocument>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public EtatDemandeDocumentSpecification(EtatDemandeDocumentCriteria criteria) {
        super(criteria);
    }

    public EtatDemandeDocumentSpecification(EtatDemandeDocumentCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
