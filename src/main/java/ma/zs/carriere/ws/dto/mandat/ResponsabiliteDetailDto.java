package  ma.zs.carriere.ws.dto.mandat;

import ma.zs.carriere.zynerator.audit.Log;
import ma.zs.carriere.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;




@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponsabiliteDetailDto  extends AuditBaseDto {

    private String ref  ;
    private String dateApplicationMin ;
    private String dateApplicationMax ;
    private BigDecimal salaire  ;

    private ResponsabiliteDto responsabilite ;



    public ResponsabiliteDetailDto(){
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
    public String getDateApplicationMin(){
        return this.dateApplicationMin;
    }
    public void setDateApplicationMin(String dateApplicationMin){
        this.dateApplicationMin = dateApplicationMin;
    }

    @Log
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateApplicationMax(){
        return this.dateApplicationMax;
    }
    public void setDateApplicationMax(String dateApplicationMax){
        this.dateApplicationMax = dateApplicationMax;
    }

    @Log
    public BigDecimal getSalaire(){
        return this.salaire;
    }
    public void setSalaire(BigDecimal salaire){
        this.salaire = salaire;
    }


    public ResponsabiliteDto getResponsabilite(){
        return this.responsabilite;
    }

    public void setResponsabilite(ResponsabiliteDto responsabilite){
        this.responsabilite = responsabilite;
    }






}
