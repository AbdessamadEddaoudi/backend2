package  ma.zs.carriere.dao.criteria.core.demande;



import ma.zs.carriere.zynerator.criteria.BaseCriteria;
import java.util.List;

public class TemplateDocumentCriteria extends  BaseCriteria  {

    private String ref;
    private String refLike;
    private String contenu;
    private String contenuLike;

    private TypeDocumentCriteria typeDocument ;
    private List<TypeDocumentCriteria> typeDocuments ;


    public TemplateDocumentCriteria(){}

    public String getRef(){
        return this.ref;
    }
    public void setRef(String ref){
        this.ref = ref;
    }
    public String getRefLike(){
        return this.refLike;
    }
    public void setRefLike(String refLike){
        this.refLike = refLike;
    }

    public String getContenu(){
        return this.contenu;
    }
    public void setContenu(String contenu){
        this.contenu = contenu;
    }
    public String getContenuLike(){
        return this.contenuLike;
    }
    public void setContenuLike(String contenuLike){
        this.contenuLike = contenuLike;
    }


    public TypeDocumentCriteria getTypeDocument(){
        return this.typeDocument;
    }

    public void setTypeDocument(TypeDocumentCriteria typeDocument){
        this.typeDocument = typeDocument;
    }
    public List<TypeDocumentCriteria> getTypeDocuments(){
        return this.typeDocuments;
    }

    public void setTypeDocuments(List<TypeDocumentCriteria> typeDocuments){
        this.typeDocuments = typeDocuments;
    }
}
