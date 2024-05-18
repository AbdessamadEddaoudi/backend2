package ma.zs.carriere.bean.core.commun;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import java.math.BigDecimal;
import ma.zs.carriere.zynerator.security.bean.User;

@Entity
@Table(name = "employe")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="employe_seq",sequenceName="employe_seq",allocationSize=1, initialValue = 1)
public class Employe  extends User    {


    public Employe(String username) {

        super(username);
    }


    private BigDecimal salaire = BigDecimal.ZERO;



    private Diplome diplome ;
    private EntiteAdmin entiteAdmin ;


    public Employe(){
        super();
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="employe_seq")
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public BigDecimal getSalaire(){
        return this.salaire;
    }
    public void setSalaire(BigDecimal salaire){
        this.salaire = salaire;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diplome")
    public Diplome getDiplome(){
        return this.diplome;
    }
    public void setDiplome(Diplome diplome){
        this.diplome = diplome;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entite_admin")
    public EntiteAdmin getEntiteAdmin(){
        return this.entiteAdmin;
    }
    public void setEntiteAdmin(EntiteAdmin entiteAdmin){
        this.entiteAdmin = entiteAdmin;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employe employe = (Employe) o;
        return id != null && id.equals(employe.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

