package ma.zs.carriere.bean.core.demande;

import java.util.Objects;







import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.carriere.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "type_document")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="type_document_seq",sequenceName="type_document_seq",allocationSize=1, initialValue = 1)
public class TypeDocument  extends BaseEntity     {

    private Long id;



    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;



    public TypeDocument(){
        super();
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="type_document_seq")
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
        TypeDocument typeDocument = (TypeDocument) o;
        return id != null && id.equals(typeDocument.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

