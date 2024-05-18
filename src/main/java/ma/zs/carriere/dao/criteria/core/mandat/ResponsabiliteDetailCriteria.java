package  ma.zs.carriere.dao.criteria.core.mandat;



import ma.zs.carriere.zynerator.criteria.BaseCriteria;
import java.util.List;
import java.time.LocalDateTime;

public class ResponsabiliteDetailCriteria extends  BaseCriteria  {

    private String ref;
    private String refLike;
    private LocalDateTime dateApplicationMin;
    private LocalDateTime dateApplicationMinFrom;
    private LocalDateTime dateApplicationMinTo;
    private LocalDateTime dateApplicationMax;
    private LocalDateTime dateApplicationMaxFrom;
    private LocalDateTime dateApplicationMaxTo;
    private String salaire;
    private String salaireMin;
    private String salaireMax;

    private ResponsabiliteCriteria responsabilite ;
    private List<ResponsabiliteCriteria> responsabilites ;


    public ResponsabiliteDetailCriteria(){}

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

    public LocalDateTime getDateApplicationMin(){
        return this.dateApplicationMin;
    }
    public void setDateApplicationMin(LocalDateTime dateApplicationMin){
        this.dateApplicationMin = dateApplicationMin;
    }
    public LocalDateTime getDateApplicationMinFrom(){
        return this.dateApplicationMinFrom;
    }
    public void setDateApplicationMinFrom(LocalDateTime dateApplicationMinFrom){
        this.dateApplicationMinFrom = dateApplicationMinFrom;
    }
    public LocalDateTime getDateApplicationMinTo(){
        return this.dateApplicationMinTo;
    }
    public void setDateApplicationMinTo(LocalDateTime dateApplicationMinTo){
        this.dateApplicationMinTo = dateApplicationMinTo;
    }
    public LocalDateTime getDateApplicationMax(){
        return this.dateApplicationMax;
    }
    public void setDateApplicationMax(LocalDateTime dateApplicationMax){
        this.dateApplicationMax = dateApplicationMax;
    }
    public LocalDateTime getDateApplicationMaxFrom(){
        return this.dateApplicationMaxFrom;
    }
    public void setDateApplicationMaxFrom(LocalDateTime dateApplicationMaxFrom){
        this.dateApplicationMaxFrom = dateApplicationMaxFrom;
    }
    public LocalDateTime getDateApplicationMaxTo(){
        return this.dateApplicationMaxTo;
    }
    public void setDateApplicationMaxTo(LocalDateTime dateApplicationMaxTo){
        this.dateApplicationMaxTo = dateApplicationMaxTo;
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
