package  ma.zs.carriere.ws.dto.mandat;

import ma.zs.carriere.zynerator.audit.Log;
import ma.zs.carriere.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;


import ma.zs.carriere.ws.dto.commun.EmployeDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class MandatDto  extends AuditBaseDto {

    private String ref  ;
    private String dateDebut ;
    private String dateFin ;
    private BigDecimal salaire  ;

    private EmployeDto employe ;
    private ResponsabiliteDto responsabilite ;



    public MandatDto(){
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateDebut(){
        return this.dateDebut;
    }
    public void setDateDebut(String dateDebut){
        this.dateDebut = dateDebut;
    }

    @Log
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateFin(){
        return this.dateFin;
    }
    public void setDateFin(String dateFin){
        this.dateFin = dateFin;
    }

    @Log
    public BigDecimal getSalaire(){
        return this.salaire;
    }
    public void setSalaire(BigDecimal salaire){
        this.salaire = salaire;
    }


    public EmployeDto getEmploye(){
        return this.employe;
    }

    public void setEmploye(EmployeDto employe){
        this.employe = employe;
    }
    public ResponsabiliteDto getResponsabilite(){
        return this.responsabilite;
    }

    public void setResponsabilite(ResponsabiliteDto responsabilite){
        this.responsabilite = responsabilite;
    }






}
