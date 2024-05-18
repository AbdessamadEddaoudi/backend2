package ma.zs.carriere.bean.core.demande;

import java.util.Objects;







import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.carriere.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "etat_demande_document")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="etat_demande_document_seq",sequenceName="etat_demande_document_seq",allocationSize=1, initialValue = 1)
public class EtatDemandeDocument  extends BaseEntity     {

    private Long id;



    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;



    public EtatDemandeDocument(){
        super();
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="etat_demande_document_seq")
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EtatDemandeDocument etatDemandeDocument = (EtatDemandeDocument) o;
        return id != null && id.equals(etatDemandeDocument.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

