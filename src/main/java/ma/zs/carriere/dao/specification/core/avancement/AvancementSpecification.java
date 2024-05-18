package  ma.zs.carriere.dao.specification.core.avancement;

import ma.zs.carriere.dao.criteria.core.avancement.AvancementCriteria;
import ma.zs.carriere.bean.core.avancement.Avancement;
import ma.zs.carriere.zynerator.specification.AbstractSpecification;


public class AvancementSpecification extends  AbstractSpecification<AvancementCriteria, Avancement>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("ref", criteria.getRef(),criteria.getRefLike());
        addPredicateBigDecimal("salaireAjoute", criteria.getSalaireAjoute(), criteria.getSalaireAjouteMin(), criteria.getSalaireAjouteMax());
        addPredicateFk("employe","id", criteria.getEmploye()==null?null:criteria.getEmploye().getId());
        addPredicateFk("employe","id", criteria.getEmployes());
        addPredicateFk("echelleAncien","id", criteria.getEchelleAncien()==null?null:criteria.getEchelleAncien().getId());
        addPredicateFk("echelleAncien","id", criteria.getEchelleAnciens());
        addPredicateFk("echelleNouveau","id", criteria.getEchelleNouveau()==null?null:criteria.getEchelleNouveau().getId());
        addPredicateFk("echelleNouveau","id", criteria.getEchelleNouveaus());
    }

    public AvancementSpecification(AvancementCriteria criteria) {
        super(criteria);
    }

    public AvancementSpecification(AvancementCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
