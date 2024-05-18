package  ma.zs.carriere.ws.dto.demande;

import ma.zs.carriere.zynerator.audit.Log;
import ma.zs.carriere.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;





@JsonInclude(JsonInclude.Include.NON_NULL)
public class TemplateDocumentDto  extends AuditBaseDto {

    private String ref  ;
    private String contenu  ;

    private TypeDocumentDto typeDocument ;



    public TemplateDocumentDto(){
        super();
    }



    @Log
    public String getRef(){
        return this.ref;
    }
    public void setRef(String ref){
        this.ref = ref;
    }

    @Log
    public String getContenu(){
        return this.contenu;
    }
    public void setContenu(String contenu){
        this.contenu = contenu;
    }


    public TypeDocumentDto getTypeDocument(){
        return this.typeDocument;
    }

    public void setTypeDocument(TypeDocumentDto typeDocument){
        this.typeDocument = typeDocument;
    }






}
