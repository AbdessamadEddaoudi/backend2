package  ma.zs.carriere.ws.dto.commun;

import ma.zs.carriere.zynerator.audit.Log;
import ma.zs.carriere.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;




@JsonInclude(JsonInclude.Include.NON_NULL)
public class EntiteAdminDto  extends AuditBaseDto {

    private String ref  ;
    private String departement  ;

    private EmployeDto chefDepart ;

    private List<EmployeDto> employes ;


    public EntiteAdminDto(){
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
    public String getDepartement(){
        return this.departement;
    }
    public void setDepartement(String departement){
        this.departement = departement;
    }


    public EmployeDto getChefDepart(){
        return this.chefDepart;
    }

    public void setChefDepart(EmployeDto chefDepart){
        this.chefDepart = chefDepart;
    }



    public List<EmployeDto> getEmployes(){
        return this.employes;
    }

    public void setEmployes(List<EmployeDto> employes){
        this.employes = employes;
    }



}
