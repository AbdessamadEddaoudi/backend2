package  ma.zs.carriere.dao.specification.core.commun;

import ma.zs.carriere.dao.criteria.core.commun.DiplomeCriteria;
import ma.zs.carriere.bean.core.commun.Diplome;
import ma.zs.carriere.zynerator.specification.AbstractSpecification;


public class DiplomeSpecification extends  AbstractSpecification<DiplomeCriteria, Diplome>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("ref", criteria.getRef(),criteria.getRefLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicateFk("echelon","id", criteria.getEchelon()==null?null:criteria.getEchelon().getId());
        addPredicateFk("echelon","id", criteria.getEchelons());
    }

    public DiplomeSpecification(DiplomeCriteria criteria) {
        super(criteria);
    }

    public DiplomeSpecification(DiplomeCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
