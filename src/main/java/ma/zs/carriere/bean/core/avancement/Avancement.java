package ma.zs.carriere.bean.core.avancement;

import java.util.Objects;





import ma.zs.carriere.bean.core.commun.Employe;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.carriere.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "avancement")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="avancement_seq",sequenceName="avancement_seq",allocationSize=1, initialValue = 1)
public class Avancement  extends BaseEntity     {

    private Long id;



    @Column(length = 500)
    private String ref;

    private BigDecimal salaireAjoute = BigDecimal.ZERO;

    private Employe employe ;
    private Echelon echelleAncien ;
    private Echelon echelleNouveau ;


    public Avancement(){
        super();
    }

    public Avancement(Long id,Employe employe){
        this.id = id;
        this.employe = employe ;
    }
    public Avancement(Employe employe){
        this.employe = employe ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="avancement_seq")
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employe")
    public Employe getEmploye(){
        return this.employe;
    }
    public void setEmploye(Employe employe){
        this.employe = employe;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "echelle_ancien")
    public Echelon getEchelleAncien(){
        return this.echelleAncien;
    }
    public void setEchelleAncien(Echelon echelleAncien){
        this.echelleAncien = echelleAncien;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "echelle_nouveau")
    public Echelon getEchelleNouveau(){
        return this.echelleNouveau;
    }
    public void setEchelleNouveau(Echelon echelleNouveau){
        this.echelleNouveau = echelleNouveau;
    }
    public BigDecimal getSalaireAjoute(){
        return this.salaireAjoute;
    }
    public void setSalaireAjoute(BigDecimal salaireAjoute){
        this.salaireAjoute = salaireAjoute;
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
        Avancement avancement = (Avancement) o;
        return id != null && id.equals(avancement.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

