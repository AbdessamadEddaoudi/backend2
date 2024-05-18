package  ma.zs.carriere.dao.specification.core.mandat;

import ma.zs.carriere.dao.criteria.core.mandat.ResponsabiliteCriteria;
import ma.zs.carriere.bean.core.mandat.Responsabilite;
import ma.zs.carriere.zynerator.specification.AbstractSpecification;


public class ResponsabiliteSpecification extends  AbstractSpecification<ResponsabiliteCriteria, Responsabilite>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("ref", criteria.getRef(),criteria.getRefLike());
        addPredicate("nom", criteria.getNom(),criteria.getNomLike());
        addPredicateFk("echelonMin","id", criteria.getEchelonMin()==null?null:criteria.getEchelonMin().getId());
        addPredicateFk("echelonMin","id", criteria.getEchelonMins());
    }

    public ResponsabiliteSpecification(ResponsabiliteCriteria criteria) {
        super(criteria);
    }

    public ResponsabiliteSpecification(ResponsabiliteCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
