package  ma.zs.carriere.dao.criteria.core.commun;


import ma.zs.carriere.dao.criteria.core.avancement.EchelonCriteria;

import ma.zs.carriere.zynerator.criteria.BaseCriteria;
import java.util.List;

public class DiplomeCriteria extends  BaseCriteria  {

    private String ref;
    private String refLike;
    private String libelle;
    private String libelleLike;

    private EchelonCriteria echelon ;
    private List<EchelonCriteria> echelons ;


    public DiplomeCriteria(){}

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

    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }
    public String getLibelleLike(){
        return this.libelleLike;
    }
    public void setLibelleLike(String libelleLike){
        this.libelleLike = libelleLike;
    }


    public EchelonCriteria getEchelon(){
        return this.echelon;
    }

    public void setEchelon(EchelonCriteria echelon){
        this.echelon = echelon;
    }
    public List<EchelonCriteria> getEchelons(){
        return this.echelons;
    }

    public void setEchelons(List<EchelonCriteria> echelons){
        this.echelons = echelons;
    }
}
