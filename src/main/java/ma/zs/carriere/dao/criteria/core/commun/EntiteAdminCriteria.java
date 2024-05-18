package  ma.zs.carriere.dao.criteria.core.commun;



import ma.zs.carriere.zynerator.criteria.BaseCriteria;
import java.util.List;

public class EntiteAdminCriteria extends  BaseCriteria  {

    private String ref;
    private String refLike;
    private String departement;
    private String departementLike;

    private EmployeCriteria chefDepart ;
    private List<EmployeCriteria> chefDeparts ;


    public EntiteAdminCriteria(){}

    public String getRef(){
        return this.ref;
    }
    public void setRef(String ref){
        this.ref = ref;
    }
    public String getRefLike(){
        return this.refLike;
    }
    public void setRefLike(String refLike){
        this.refLike = refLike;
    }

    public String getDepartement(){
        return this.departement;
    }
    public void setDepartement(String departement){
        this.departement = departement;
    }
    public String getDepartementLike(){
        return this.departementLike;
    }
    public void setDepartementLike(String departementLike){
        this.departementLike = departementLike;
    }


    public EmployeCriteria getChefDepart(){
        return this.chefDepart;
    }

    public void setChefDepart(EmployeCriteria chefDepart){
        this.chefDepart = chefDepart;
    }
    public List<EmployeCriteria> getChefDeparts(){
        return this.chefDeparts;
    }

    public void setChefDeparts(List<EmployeCriteria> chefDeparts){
        this.chefDeparts = chefDeparts;
    }
}
