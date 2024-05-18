package  ma.zs.carriere.dao.specification.core.demande;

import ma.zs.carriere.dao.criteria.core.demande.DemandeDocumentCriteria;
import ma.zs.carriere.bean.core.demande.DemandeDocument;
import ma.zs.carriere.zynerator.specification.AbstractSpecification;


public class DemandeDocumentSpecification extends  AbstractSpecification<DemandeDocumentCriteria, DemandeDocument>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("ref", criteria.getRef(),criteria.getRefLike());
        addPredicateFk("employe","id", criteria.getEmploye()==null?null:criteria.getEmploye().getId());
        addPredicateFk("employe","id", criteria.getEmployes());
        addPredicateFk("typeDocument","id", criteria.getTypeDocument()==null?null:criteria.getTypeDocument().getId());
        addPredicateFk("typeDocument","id", criteria.getTypeDocuments());
    }

    public DemandeDocumentSpecification(DemandeDocumentCriteria criteria) {
        super(criteria);
    }

    public DemandeDocumentSpecification(DemandeDocumentCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
