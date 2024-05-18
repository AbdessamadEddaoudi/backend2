package ma.zs.carriere.dao.criteria.core.avancement;


import ma.zs.carriere.zynerator.criteria.BaseCriteria;

import java.util.List;

public class EchelonCriteria extends BaseCriteria {

    private String ref;
    private String refLike;
    private String libelle;
    private String libelleLike;
    private String salaire;
    private String salaireMin;
    private String salaireMax;

    private EchelleCriteria echelle;


    public EchelonCriteria() {
    }

    public String getRef() {
        return this.ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getRefLike() {
        return this.refLike;
    }

    public void setRefLike(String refLike) {
        this.refLike = refLike;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelleLike() {
        return this.libelleLike;
    }

    public void setLibelleLike(String libelleLike) {
        this.libelleLike = libelleLike;
    }

    public String getSalaire() {
        return this.salaire;
    }

    public void setSalaire(String salaire) {
        this.salaire = salaire;
    }

    public String getSalaireMin() {
        return this.salaireMin;
    }

    public void setSalaireMin(String salaireMin) {
        this.salaireMin = salaireMin;
    }

    public String getSalaireMax() {
        return this.salaireMax;
    }

    public void setSalaireMax(String salaireMax) {
        this.salaireMax = salaireMax;
    }


    public EchelleCriteria getEchelle() {
        return this.echelle;
    }

    public void setEchelle(EchelleCriteria echelle) {
        this.echelle = echelle;
    }

}
