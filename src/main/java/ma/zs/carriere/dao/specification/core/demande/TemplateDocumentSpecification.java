package  ma.zs.carriere.dao.specification.core.demande;

import ma.zs.carriere.dao.criteria.core.demande.TemplateDocumentCriteria;
import ma.zs.carriere.bean.core.demande.TemplateDocument;
import ma.zs.carriere.zynerator.specification.AbstractSpecification;


public class TemplateDocumentSpecification extends  AbstractSpecification<TemplateDocumentCriteria, TemplateDocument>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("ref", criteria.getRef(),criteria.getRefLike());
        addPredicate("contenu", criteria.getContenu(),criteria.getContenuLike());
        addPredicateFk("typeDocument","id", criteria.getTypeDocument()==null?null:criteria.getTypeDocument().getId());
        addPredicateFk("typeDocument","id", criteria.getTypeDocuments());
    }

    public TemplateDocumentSpecification(TemplateDocumentCriteria criteria) {
        super(criteria);
    }

    public TemplateDocumentSpecification(TemplateDocumentCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
