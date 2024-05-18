package  ma.zs.carriere.ws.dto.demande;

import ma.zs.carriere.zynerator.audit.Log;
import ma.zs.carriere.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;



import ma.zs.carriere.ws.dto.commun.EmployeDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class DemandeDocumentDto  extends AuditBaseDto {

    private String ref  ;

    private EmployeDto employe ;
    private TypeDocumentDto typeDocument ;



    public DemandeDocumentDto(){
        super();
    }



    @Log
    public String getRef(){
        return this.ref;
    }
    public void setRef(String ref){
        this.ref = ref;
    }


    public EmployeDto getEmploye(){
        return this.employe;
    }

    public void setEmploye(EmployeDto employe){
        this.employe = employe;
    }
    public TypeDocumentDto getTypeDocument(){
        return this.typeDocument;
    }

    public void setTypeDocument(TypeDocumentDto typeDocument){
        this.typeDocument = typeDocument;
    }






}
