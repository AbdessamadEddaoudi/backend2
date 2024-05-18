package ma.zs.carriere.bean.core.commun;

import java.util.Objects;
import java.util.List;







import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.carriere.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "entite_admin")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="entite_admin_seq",sequenceName="entite_admin_seq",allocationSize=1, initialValue = 1)
public class EntiteAdmin  extends BaseEntity     {

    private Long id;



    @Column(length = 500)
    private String ref;

    @Column(length = 500)
    private String departement;

    private Employe chefDepart ;

    private List<Employe> employes ;

    public EntiteAdmin(){
        super();
    }

    public EntiteAdmin(Long id,String departement){
        this.id = id;
        this.departement = departement ;
    }
    public EntiteAdmin(String departement){
        this.departement = departement ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="entite_admin_seq")
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
    public String getDepartement(){
        return this.departement;
    }
    public void setDepartement(String departement){
        this.departement = departement;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chef_depart")
    public Employe getChefDepart(){
        return this.chefDepart;
    }
    public void setChefDepart(Employe chefDepart){
        this.chefDepart = chefDepart;
    }
    @OneToMany(mappedBy = "entiteAdmin")
    public List<Employe> getEmployes(){
        return this.employes;
    }

    public void setEmployes(List<Employe> employes){
        this.employes = employes;
    }

    @Transient
    public String getLabel() {
        label = departement;
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntiteAdmin entiteAdmin = (EntiteAdmin) o;
        return id != null && id.equals(entiteAdmin.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

