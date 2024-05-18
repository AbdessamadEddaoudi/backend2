package ma.zs.carriere.dao.specification.core.avancement;

import ma.zs.carriere.dao.criteria.core.avancement.EchelleCriteria;
import ma.zs.carriere.bean.core.avancement.Echelle;
import ma.zs.carriere.zynerator.specification.AbstractSpecification;


public class EchelleSpecification extends AbstractSpecification<EchelleCriteria, Echelle> {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("ref", criteria.getRef(), criteria.getRefLike());
        addPredicate("libelle", criteria.getLibelle(), criteria.getLibelleLike());
        addPredicateFk("echelon", "id", criteria.getEchelons());
    }

    public EchelleSpecification(EchelleCriteria criteria) {
        super(criteria);
    }

    public EchelleSpecification(EchelleCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
