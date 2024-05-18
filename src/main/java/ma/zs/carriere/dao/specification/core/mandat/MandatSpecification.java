package  ma.zs.carriere.dao.specification.core.mandat;

import ma.zs.carriere.bean.core.mandat.Mandat;
import ma.zs.carriere.dao.criteria.core.mandat.MandatCriteria;

import ma.zs.carriere.zynerator.specification.AbstractSpecification;


public class MandatSpecification extends AbstractSpecification<MandatCriteria, Mandat> {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("ref", criteria.getRef(),criteria.getRefLike());
        addPredicate("dateDebut", criteria.getDateDebut(), criteria.getDateDebutFrom(), criteria.getDateDebutTo());
        addPredicate("dateFin", criteria.getDateFin(), criteria.getDateFinFrom(), criteria.getDateFinTo());
        addPredicateBigDecimal("salaire", criteria.getSalaire(), criteria.getSalaireMin(), criteria.getSalaireMax());
        addPredicateFk("employe","id", criteria.getEmploye()==null?null:criteria.getEmploye().getId());
        addPredicateFk("employe","id", criteria.getEmployes());
        addPredicateFk("responsabilite","id", criteria.getResponsabilite()==null?null:criteria.getResponsabilite().getId());
        addPredicateFk("responsabilite","id", criteria.getResponsabilites());
        addPredicateFk("responsabilite","ref", criteria.getResponsabilite()==null?null:criteria.getResponsabilite().getRef());
    }

    public MandatSpecification(MandatCriteria criteria) {
        super(criteria);
    }

    public MandatSpecification(MandatCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
