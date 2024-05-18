package  ma.zs.carriere.dao.specification.core.demande;

import ma.zs.carriere.dao.criteria.core.demande.TypeDocumentCriteria;
import ma.zs.carriere.bean.core.demande.TypeDocument;
import ma.zs.carriere.zynerator.specification.AbstractSpecification;


public class TypeDocumentSpecification extends  AbstractSpecification<TypeDocumentCriteria, TypeDocument>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public TypeDocumentSpecification(TypeDocumentCriteria criteria) {
        super(criteria);
    }

    public TypeDocumentSpecification(TypeDocumentCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
