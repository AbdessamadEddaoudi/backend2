package  ma.zs.carriere.ws.dto.commun;

import ma.zs.carriere.zynerator.audit.Log;
import ma.zs.carriere.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;



import ma.zs.carriere.ws.dto.avancement.EchelonDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class DiplomeDto  extends AuditBaseDto {

    private String ref  ;
    private String libelle  ;

    private EchelonDto echelon ;



    public DiplomeDto(){
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
    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }


    public EchelonDto getEchelon(){
        return this.echelon;
    }

    public void setEchelon(EchelonDto echelon){
        this.echelon = echelon;
    }






}
