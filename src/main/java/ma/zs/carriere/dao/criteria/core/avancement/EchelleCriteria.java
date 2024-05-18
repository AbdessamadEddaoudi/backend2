package ma.zs.carriere.dao.criteria.core.avancement;


import ma.zs.carriere.zynerator.criteria.BaseCriteria;

import java.util.List;

public class EchelleCriteria extends BaseCriteria {

    private String ref;
    private String refLike;
    private String libelle;
    private String libelleLike;
    private List<EchelonCriteria> echelons;

    public List<EchelonCriteria> getEchelons() {
        return echelons;
    }

    public void setEchelons(List<EchelonCriteria> echelons) {
        this.echelons = echelons;
    }

    public EchelleCriteria() {
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


}
