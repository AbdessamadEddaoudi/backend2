package ma.zs.carriere.bean.core.demande;

import java.util.Objects;





import ma.zs.carriere.bean.core.commun.Employe;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.carriere.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "demande_document")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="demande_document_seq",sequenceName="demande_document_seq",allocationSize=1, initialValue = 1)
public class DemandeDocument  extends BaseEntity     {

    private Long id;



    @Column(length = 500)
    private String ref;

    private Employe employe ;
    private TypeDocument typeDocument ;


    public DemandeDocument(){
        super();
    }

    public DemandeDocument(Long id,String ref){
        this.id = id;
        this.ref = ref ;
    }
    public DemandeDocument(String ref){
        this.ref = ref ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="demande_document_seq")
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
    @JoinColumn(name = "type_document")
    public TypeDocument getTypeDocument(){
        return this.typeDocument;
    }
    public void setTypeDocument(TypeDocument typeDocument){
        this.typeDocument = typeDocument;
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
        DemandeDocument demandeDocument = (DemandeDocument) o;
        return id != null && id.equals(demandeDocument.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

