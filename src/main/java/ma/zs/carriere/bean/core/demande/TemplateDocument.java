package ma.zs.carriere.bean.core.demande;

import java.util.Objects;







import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.carriere.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "template_document")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="template_document_seq",sequenceName="template_document_seq",allocationSize=1, initialValue = 1)
public class TemplateDocument  extends BaseEntity     {

    private Long id;



    @Column(length = 500)
    private String ref;

    @Column(length = 500)
    private String contenu;

    private TypeDocument typeDocument ;


    public TemplateDocument(){
        super();
    }

    public TemplateDocument(Long id,String ref){
        this.id = id;
        this.ref = ref ;
    }
    public TemplateDocument(String ref){
        this.ref = ref ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="template_document_seq")
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
    public String getContenu(){
        return this.contenu;
    }
    public void setContenu(String contenu){
        this.contenu = contenu;
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
        TemplateDocument templateDocument = (TemplateDocument) o;
        return id != null && id.equals(templateDocument.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

