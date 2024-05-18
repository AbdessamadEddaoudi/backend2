package  ma.zs.carriere.dao.criteria.core.avancement;


import ma.zs.carriere.dao.criteria.core.commun.EmployeCriteria;

import ma.zs.carriere.zynerator.criteria.BaseCriteria;
import java.util.List;

public class AvancementCriteria extends  BaseCriteria  {

    private String ref;
    private String refLike;
    private String salaireAjoute;
    private String salaireAjouteMin;
    private String salaireAjouteMax;

    private EmployeCriteria employe ;
    private List<EmployeCriteria> employes ;
    private EchelonCriteria echelleAncien ;
    private List<EchelonCriteria> echelleAnciens ;
    private EchelonCriteria echelleNouveau ;
    private List<EchelonCriteria> echelleNouveaus ;


    public AvancementCriteria(){}

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

    public String getSalaireAjoute(){
        return this.salaireAjoute;
    }
    public void setSalaireAjoute(String salaireAjoute){
        this.salaireAjoute = salaireAjoute;
    }   
    public String getSalaireAjouteMin(){
        return this.salaireAjouteMin;
    }
    public void setSalaireAjouteMin(String salaireAjouteMin){
        this.salaireAjouteMin = salaireAjouteMin;
    }
    public String getSalaireAjouteMax(){
        return this.salaireAjouteMax;
    }
    public void setSalaireAjouteMax(String salaireAjouteMax){
        this.salaireAjouteMax = salaireAjouteMax;
    }
      

    public EmployeCriteria getEmploye(){
        return this.employe;
    }

    public void setEmploye(EmployeCriteria employe){
        this.employe = employe;
    }
    public List<EmployeCriteria> getEmployes(){
        return this.employes;
    }

    public void setEmployes(List<EmployeCriteria> employes){
        this.employes = employes;
    }
    public EchelonCriteria getEchelleAncien(){
        return this.echelleAncien;
    }

    public void setEchelleAncien(EchelonCriteria echelleAncien){
        this.echelleAncien = echelleAncien;
    }
    public List<EchelonCriteria> getEchelleAnciens(){
        return this.echelleAnciens;
    }

    public void setEchelleAnciens(List<EchelonCriteria> echelleAnciens){
        this.echelleAnciens = echelleAnciens;
    }
    public EchelonCriteria getEchelleNouveau(){
        return this.echelleNouveau;
    }

    public void setEchelleNouveau(EchelonCriteria echelleNouveau){
        this.echelleNouveau = echelleNouveau;
    }
    public List<EchelonCriteria> getEchelleNouveaus(){
        return this.echelleNouveaus;
    }

    public void setEchelleNouveaus(List<EchelonCriteria> echelleNouveaus){
        this.echelleNouveaus = echelleNouveaus;
    }
}
