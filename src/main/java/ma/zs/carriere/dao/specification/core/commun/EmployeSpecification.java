package  ma.zs.carriere.dao.specification.core.commun;

import ma.zs.carriere.dao.criteria.core.commun.EmployeCriteria;
import ma.zs.carriere.bean.core.commun.Employe;
import ma.zs.carriere.zynerator.specification.AbstractSpecification;


public class EmployeSpecification extends  AbstractSpecification<EmployeCriteria, Employe>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicateBigDecimal("salaire", criteria.getSalaire(), criteria.getSalaireMin(), criteria.getSalaireMax());
        addPredicateBool("credentialsNonExpired", criteria.getCredentialsNonExpired());
        addPredicateBool("enabled", criteria.getEnabled());
        addPredicateBool("accountNonExpired", criteria.getAccountNonExpired());
        addPredicateBool("accountNonLocked", criteria.getAccountNonLocked());
        addPredicateBool("passwordChanged", criteria.getPasswordChanged());
        addPredicate("username", criteria.getUsername(),criteria.getUsernameLike());
        addPredicate("password", criteria.getPassword(),criteria.getPasswordLike());
        addPredicateFk("diplome","id", criteria.getDiplome()==null?null:criteria.getDiplome().getId());
        addPredicateFk("diplome","id", criteria.getDiplomes());
        addPredicateFk("diplome","ref", criteria.getDiplome()==null?null:criteria.getDiplome().getRef());
        addPredicateFk("entiteAdmin","id", criteria.getEntiteAdmin()==null?null:criteria.getEntiteAdmin().getId());
        addPredicateFk("entiteAdmin","id", criteria.getEntiteAdmins());
        addPredicateFk("entiteAdmin","ref", criteria.getEntiteAdmin()==null?null:criteria.getEntiteAdmin().getRef());
    }

    public EmployeSpecification(EmployeCriteria criteria) {
        super(criteria);
    }

    public EmployeSpecification(EmployeCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
