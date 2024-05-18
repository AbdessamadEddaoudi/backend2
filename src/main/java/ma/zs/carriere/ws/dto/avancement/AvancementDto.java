package  ma.zs.carriere.ws.dto.avancement;

import ma.zs.carriere.zynerator.audit.Log;
import ma.zs.carriere.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;


import ma.zs.carriere.ws.dto.commun.EmployeDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class AvancementDto  extends AuditBaseDto {

    private String ref  ;
    private BigDecimal salaireAjoute  ;

    private EmployeDto employe ;
    private EchelonDto echelleAncien ;
    private EchelonDto echelleNouveau ;



    public AvancementDto(){
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
    public BigDecimal getSalaireAjoute(){
        return this.salaireAjoute;
    }
    public void setSalaireAjoute(BigDecimal salaireAjoute){
        this.salaireAjoute = salaireAjoute;
    }


    public EmployeDto getEmploye(){
        return this.employe;
    }

    public void setEmploye(EmployeDto employe){
        this.employe = employe;
    }
    public EchelonDto getEchelleAncien(){
        return this.echelleAncien;
    }

    public void setEchelleAncien(EchelonDto echelleAncien){
        this.echelleAncien = echelleAncien;
    }
    public EchelonDto getEchelleNouveau(){
        return this.echelleNouveau;
    }

    public void setEchelleNouveau(EchelonDto echelleNouveau){
        this.echelleNouveau = echelleNouveau;
    }






}
