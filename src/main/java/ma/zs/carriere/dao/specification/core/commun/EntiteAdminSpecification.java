package  ma.zs.carriere.dao.specification.core.commun;

import ma.zs.carriere.dao.criteria.core.commun.EntiteAdminCriteria;
import ma.zs.carriere.bean.core.commun.EntiteAdmin;
import ma.zs.carriere.zynerator.specification.AbstractSpecification;


public class EntiteAdminSpecification extends  AbstractSpecification<EntiteAdminCriteria, EntiteAdmin>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("ref", criteria.getRef(),criteria.getRefLike());
        addPredicate("departement", criteria.getDepartement(),criteria.getDepartementLike());
        addPredicateFk("chefDepart","id", criteria.getChefDepart()==null?null:criteria.getChefDepart().getId());
        addPredicateFk("chefDepart","id", criteria.getChefDeparts());
    }

    public EntiteAdminSpecification(EntiteAdminCriteria criteria) {
        super(criteria);
    }

    public EntiteAdminSpecification(EntiteAdminCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
