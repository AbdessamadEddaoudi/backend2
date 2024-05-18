package ma.zs.carriere.bean.core.commun;

import java.util.Objects;





import ma.zs.carriere.bean.core.avancement.Echelon;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.carriere.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "diplome")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="diplome_seq",sequenceName="diplome_seq",allocationSize=1, initialValue = 1)
public class Diplome  extends BaseEntity     {

    private Long id;



    @Column(length = 500)
    private String ref;

    @Column(length = 500)
    private String libelle;

    private Echelon echelon ;


    public Diplome(){
        super();
    }

    public Diplome(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public Diplome(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="diplome_seq")
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
    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "echelon")
    public Echelon getEchelon(){
        return this.echelon;
    }
    public void setEchelon(Echelon echelon){
        this.echelon = echelon;
    }

    @Transient
    public String getLabel() {
        label = libelle;
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Diplome diplome = (Diplome) o;
        return id != null && id.equals(diplome.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

