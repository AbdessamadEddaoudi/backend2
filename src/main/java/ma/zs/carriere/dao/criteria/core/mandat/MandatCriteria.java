package  ma.zs.carriere.dao.criteria.core.mandat;




import ma.zs.carriere.dao.criteria.core.commun.EmployeCriteria;

import ma.zs.carriere.zynerator.criteria.BaseCriteria;
import java.util.List;
import java.time.LocalDateTime;

public class MandatCriteria extends  BaseCriteria  {

    private String ref;
    private String refLike;
    private LocalDateTime dateDebut;
    private LocalDateTime dateDebutFrom;
    private LocalDateTime dateDebutTo;
    private LocalDateTime dateFin;
    private LocalDateTime dateFinFrom;
    private LocalDateTime dateFinTo;
    private String salaire;
    private String salaireMin;
    private String salaireMax;

    private EmployeCriteria employe ;
    private List<EmployeCriteria> employes ;
    private ResponsabiliteCriteria responsabilite ;
    private List<ResponsabiliteCriteria> responsabilites ;


    public MandatCriteria(){}

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

    public LocalDateTime getDateDebut(){
        return this.dateDebut;
    }
    public void setDateDebut(LocalDateTime dateDebut){
        this.dateDebut = dateDebut;
    }
    public LocalDateTime getDateDebutFrom(){
        return this.dateDebutFrom;
    }
    public void setDateDebutFrom(LocalDateTime dateDebutFrom){
        this.dateDebutFrom = dateDebutFrom;
    }
    public LocalDateTime getDateDebutTo(){
        return this.dateDebutTo;
    }
    public void setDateDebutTo(LocalDateTime dateDebutTo){
        this.dateDebutTo = dateDebutTo;
    }
    public LocalDateTime getDateFin(){
        return this.dateFin;
    }
    public void setDateFin(LocalDateTime dateFin){
        this.dateFin = dateFin;
    }
    public LocalDateTime getDateFinFrom(){
        return this.dateFinFrom;
    }
    public void setDateFinFrom(LocalDateTime dateFinFrom){
        this.dateFinFrom = dateFinFrom;
    }
    public LocalDateTime getDateFinTo(){
        return this.dateFinTo;
    }
    public void setDateFinTo(LocalDateTime dateFinTo){
        this.dateFinTo = dateFinTo;
    }
    public String getSalaire(){
        return this.salaire;
    }
    public void setSalaire(String salaire){
        this.salaire = salaire;
    }   
    public String getSalaireMin(){
        return this.salaireMin;
    }
    public void setSalaireMin(String salaireMin){
        this.salaireMin = salaireMin;
    }
    public String getSalaireMax(){
        return this.salaireMax;
    }
    public void setSalaireMax(String salaireMax){
        this.salaireMax = salaireMax;
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
    public ResponsabiliteCriteria getResponsabilite(){
        return this.responsabilite;
    }

    public void setResponsabilite(ResponsabiliteCriteria responsabilite){
        this.responsabilite = responsabilite;
    }
    public List<ResponsabiliteCriteria> getResponsabilites(){
        return this.responsabilites;
    }

    public void setResponsabilites(List<ResponsabiliteCriteria> responsabilites){
        this.responsabilites = responsabilites;
    }
}
