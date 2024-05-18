package  ma.zs.carriere.dao.criteria.core.demande;


import ma.zs.carriere.dao.criteria.core.commun.EmployeCriteria;

import ma.zs.carriere.zynerator.criteria.BaseCriteria;
import java.util.List;

public class DemandeDocumentCriteria extends  BaseCriteria  {

    private String ref;
    private String refLike;

    private EmployeCriteria employe ;
    private List<EmployeCriteria> employes ;
    private TypeDocumentCriteria typeDocument ;
    private List<TypeDocumentCriteria> typeDocuments ;


    public DemandeDocumentCriteria(){}

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


    public EmployeCriteria getEmploye(){
        return this.employe;
    }

    public void setEmploye(EmployeCriteria employe){
        this.employe = employe;
    }
    public List<EmployeCriteria> getEmployes(){
        return this.employes;
    }

    public void setEmployes(List<EmployeCriteria> employes){
        this.employes = employes;
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
