package ma.zs.carriere.dao.specification.core.avancement;

import ma.zs.carriere.dao.criteria.core.avancement.EchelonCriteria;
import ma.zs.carriere.bean.core.avancement.Echelon;
import ma.zs.carriere.zynerator.specification.AbstractSpecification;


public class EchelonSpecification extends AbstractSpecification<EchelonCriteria, Echelon> {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("ref", criteria.getRef(), criteria.getRefLike());
        addPredicate("libelle", criteria.getLibelle(), criteria.getLibelleLike());
        addPredicateBigDecimal("salaire", criteria.getSalaire(), criteria.getSalaireMin(), criteria.getSalaireMax());
        addPredicateFk("echelle", "id", criteria.getEchelle() == null ? null : criteria.getEchelle().getId());
        addPredicateFk("echelle", "ref", criteria.getEchelle() == null ? null : criteria.getEchelle().getRef());
    }

    public EchelonSpecification(EchelonCriteria criteria) {
        super(criteria);
    }

    public EchelonSpecification(EchelonCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
