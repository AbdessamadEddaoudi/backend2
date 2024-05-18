package ma.zs.carriere.bean.core.mandat;

import java.util.Objects;

import java.time.LocalDateTime;


import ma.zs.carriere.bean.core.commun.Employe;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.carriere.zynerator.bean.BaseEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "mandat")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="mandat_seq",sequenceName="mandat_seq",allocationSize=1, initialValue = 1)
public class Mandat  extends BaseEntity     {

    private Long id;



    @Column(length = 500)
    private String ref;

    private LocalDateTime dateDebut ;

    private LocalDateTime dateFin ;

    private BigDecimal salaire = BigDecimal.ZERO;

    private Employe employe ;
    private Responsabilite responsabilite ;


    public Mandat(){
        super();
    }

    public Mandat(Long id,Employe employe){
        this.id = id;
        this.employe = employe ;
    }
    public Mandat(Employe employe){
        this.employe = employe ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="mandat_seq")
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getRef(){
        return this.ref;
    }
    public void setRef(String ref){
        this.ref = ref;
    }
    public LocalDateTime getDateDebut(){
        return this.dateDebut;
    }
    public void setDateDebut(LocalDateTime dateDebut){
        this.dateDebut = dateDebut;
    }
    public LocalDateTime getDateFin(){
        return this.dateFin;
    }
    public void setDateFin(LocalDateTime dateFin){
        this.dateFin = dateFin;
    }
    public BigDecimal getSalaire(){
        return this.salaire;
    }
    public void setSalaire(BigDecimal salaire){
        this.salaire = salaire;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employe")
    public Employe getEmploye(){
        return this.employe;
    }
    public void setEmploye(Employe employe){
        this.employe = employe;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "responsabilite")
    public Responsabilite getResponsabilite(){
        return this.responsabilite;
    }
    public void setResponsabilite(Responsabilite responsabilite){
        this.responsabilite = responsabilite;
    }

    @Transient
    public String getLabel() {
        label = ref;
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mandat mandat = (Mandat) o;
        return id != null && id.equals(mandat.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

