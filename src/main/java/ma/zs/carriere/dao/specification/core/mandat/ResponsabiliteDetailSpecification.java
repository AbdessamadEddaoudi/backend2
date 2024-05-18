package  ma.zs.carriere.dao.specification.core.mandat;

import ma.zs.carriere.dao.criteria.core.mandat.ResponsabiliteDetailCriteria;
import ma.zs.carriere.bean.core.mandat.ResponsabiliteDetail;
import ma.zs.carriere.zynerator.specification.AbstractSpecification;


public class ResponsabiliteDetailSpecification extends  AbstractSpecification<ResponsabiliteDetailCriteria, ResponsabiliteDetail>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("ref", criteria.getRef(),criteria.getRefLike());
        addPredicate("dateApplicationMin", criteria.getDateApplicationMin(), criteria.getDateApplicationMinFrom(), criteria.getDateApplicationMinTo());
        addPredicate("dateApplicationMax", criteria.getDateApplicationMax(), criteria.getDateApplicationMaxFrom(), criteria.getDateApplicationMaxTo());
        addPredicateBigDecimal("salaire", criteria.getSalaire(), criteria.getSalaireMin(), criteria.getSalaireMax());
        addPredicateFk("responsabilite","id", criteria.getResponsabilite()==null?null:criteria.getResponsabilite().getId());
        addPredicateFk("responsabilite","id", criteria.getResponsabilites());
        addPredicateFk("responsabilite","ref", criteria.getResponsabilite()==null?null:criteria.getResponsabilite().getRef());
    }

    public ResponsabiliteDetailSpecification(ResponsabiliteDetailCriteria criteria) {
        super(criteria);
    }

    public ResponsabiliteDetailSpecification(ResponsabiliteDetailCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
