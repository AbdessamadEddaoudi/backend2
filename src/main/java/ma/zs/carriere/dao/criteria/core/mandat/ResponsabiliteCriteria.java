package  ma.zs.carriere.dao.criteria.core.mandat;


import ma.zs.carriere.dao.criteria.core.avancement.EchelonCriteria;

import ma.zs.carriere.zynerator.criteria.BaseCriteria;
import java.util.List;

public class ResponsabiliteCriteria extends  BaseCriteria  {

    private String ref;
    private String refLike;
    private String nom;
    private String nomLike;

    private EchelonCriteria echelonMin ;
    private List<EchelonCriteria> echelonMins ;


    public ResponsabiliteCriteria(){}

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

    public String getNom(){
        return this.nom;
    }
    public void setNom(String nom){
        this.nom = nom;
    }
    public String getNomLike(){
        return this.nomLike;
    }
    public void setNomLike(String nomLike){
        this.nomLike = nomLike;
    }


    public EchelonCriteria getEchelonMin(){
        return this.echelonMin;
    }

    public void setEchelonMin(EchelonCriteria echelonMin){
        this.echelonMin = echelonMin;
    }
    public List<EchelonCriteria> getEchelonMins(){
        return this.echelonMins;
    }

    public void setEchelonMins(List<EchelonCriteria> echelonMins){
        this.echelonMins = echelonMins;
    }
}
