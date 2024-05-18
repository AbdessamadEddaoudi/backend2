package ma.zs.carriere.bean.core.mandat;

import java.util.Objects;





import ma.zs.carriere.bean.core.avancement.Echelon;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.carriere.zynerator.bean.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "responsabilite")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="responsabilite_seq",sequenceName="responsabilite_seq",allocationSize=1, initialValue = 1)
public class Responsabilite  extends BaseEntity     {

    private Long id;



    @Column(length = 500)
    private String ref;

    @Column(length = 500)
    private String nom;

    private Echelon echelonMin ;


    public Responsabilite(){
        super();
    }

    public Responsabilite(Long id,String nom){
        this.id = id;
        this.nom = nom ;
    }
    public Responsabilite(String nom){
        this.nom = nom ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="responsabilite_seq")
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
    public String getNom(){
        return this.nom;
    }
    public void setNom(String nom){
        this.nom = nom;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "echelon_min")
    public Echelon getEchelonMin(){
        return this.echelonMin;
    }
    public void setEchelonMin(Echelon echelonMin){
        this.echelonMin = echelonMin;
    }

    @Transient
    public String getLabel() {
        label = nom;
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Responsabilite responsabilite = (Responsabilite) o;
        return id != null && id.equals(responsabilite.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

