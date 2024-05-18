package  ma.zs.carriere.ws.dto.avancement;

import ma.zs.carriere.zynerator.audit.Log;
import ma.zs.carriere.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;




@JsonInclude(JsonInclude.Include.NON_NULL)
public class EchelonDto  extends AuditBaseDto {

    private String ref  ;
    private String libelle  ;
    private BigDecimal salaire  ;

    private EchelleDto echelle ;



    public EchelonDto(){
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

    @Log
    public BigDecimal getSalaire(){
        return this.salaire;
    }
    public void setSalaire(BigDecimal salaire){
        this.salaire = salaire;
    }


    public EchelleDto getEchelle(){
        return this.echelle;
    }

    public void setEchelle(EchelleDto echelle){
        this.echelle = echelle;
    }






}
