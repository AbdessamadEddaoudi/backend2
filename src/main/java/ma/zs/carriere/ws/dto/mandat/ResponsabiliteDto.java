package  ma.zs.carriere.ws.dto.mandat;

import ma.zs.carriere.zynerator.audit.Log;
import ma.zs.carriere.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;



import ma.zs.carriere.ws.dto.avancement.EchelonDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponsabiliteDto  extends AuditBaseDto {

    private String ref  ;
    private String nom  ;

    private EchelonDto echelonMin ;



    public ResponsabiliteDto(){
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
    public String getNom(){
        return this.nom;
    }
    public void setNom(String nom){
        this.nom = nom;
    }


    public EchelonDto getEchelonMin(){
        return this.echelonMin;
    }

    public void setEchelonMin(EchelonDto echelonMin){
        this.echelonMin = echelonMin;
    }






}
