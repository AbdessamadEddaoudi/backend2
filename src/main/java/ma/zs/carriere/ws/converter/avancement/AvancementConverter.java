package  ma.zs.carriere.ws.converter.avancement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.carriere.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.carriere.ws.converter.avancement.EchelonConverter;
import ma.zs.carriere.ws.converter.commun.EmployeConverter;



import ma.zs.carriere.zynerator.util.StringUtil;
import ma.zs.carriere.zynerator.converter.AbstractConverter;
import ma.zs.carriere.zynerator.util.DateUtil;
import ma.zs.carriere.bean.core.avancement.Avancement;
import ma.zs.carriere.ws.dto.avancement.AvancementDto;

@Component
public class AvancementConverter {

    @Autowired
    private EchelonConverter echelonConverter ;
    @Autowired
    private EmployeConverter employeConverter ;
    private boolean employe;
    private boolean echelleAncien;
    private boolean echelleNouveau;

    public  AvancementConverter() {
        initObject(true);
    }


    public Avancement toItem(AvancementDto dto) {
        if (dto == null) {
            return null;
        } else {
        Avancement item = new Avancement();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getRef()))
                item.setRef(dto.getRef());
            if(StringUtil.isNotEmpty(dto.getSalaireAjoute()))
                item.setSalaireAjoute(dto.getSalaireAjoute());
            if(this.employe && dto.getEmploye()!=null)
                item.setEmploye(employeConverter.toItem(dto.getEmploye())) ;

            if(this.echelleAncien && dto.getEchelleAncien()!=null)
                item.setEchelleAncien(echelonConverter.toItem(dto.getEchelleAncien())) ;

            if(this.echelleNouveau && dto.getEchelleNouveau()!=null)
                item.setEchelleNouveau(echelonConverter.toItem(dto.getEchelleNouveau())) ;




        return item;
        }
    }


    public AvancementDto toDto(Avancement item) {
        if (item == null) {
            return null;
        } else {
            AvancementDto dto = new AvancementDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getRef()))
                dto.setRef(item.getRef());
            if(StringUtil.isNotEmpty(item.getSalaireAjoute()))
                dto.setSalaireAjoute(item.getSalaireAjoute());
            if(this.employe && item.getEmploye()!=null) {
                dto.setEmploye(employeConverter.toDto(item.getEmploye())) ;

            }
            if(this.echelleAncien && item.getEchelleAncien()!=null) {
                dto.setEchelleAncien(echelonConverter.toDto(item.getEchelleAncien())) ;

            }
            if(this.echelleNouveau && item.getEchelleNouveau()!=null) {
                dto.setEchelleNouveau(echelonConverter.toDto(item.getEchelleNouveau())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.employe = value;
        this.echelleAncien = value;
        this.echelleNouveau = value;
    }
	
    public List<Avancement> toItem(List<AvancementDto> dtos) {
        List<Avancement> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (AvancementDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<AvancementDto> toDto(List<Avancement> items) {
        List<AvancementDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Avancement item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(AvancementDto dto, Avancement t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getEmploye() != null)
        employeConverter.copy(dto.getEmploye(), t.getEmploye());
        if (dto.getEchelleAncien() != null)
        echelonConverter.copy(dto.getEchelleAncien(), t.getEchelleAncien());
        if (dto.getEchelleNouveau() != null)
        echelonConverter.copy(dto.getEchelleNouveau(), t.getEchelleNouveau());
    }

    public List<Avancement> copy(List<AvancementDto> dtos) {
        List<Avancement> result = new ArrayList<>();
        if (dtos != null) {
            for (AvancementDto dto : dtos) {
                Avancement instance = new Avancement();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public EchelonConverter getEchelonConverter(){
        return this.echelonConverter;
    }
    public void setEchelonConverter(EchelonConverter echelonConverter ){
        this.echelonConverter = echelonConverter;
    }
    public EmployeConverter getEmployeConverter(){
        return this.employeConverter;
    }
    public void setEmployeConverter(EmployeConverter employeConverter ){
        this.employeConverter = employeConverter;
    }
    public boolean  isEmploye(){
        return this.employe;
    }
    public void  setEmploye(boolean employe){
        this.employe = employe;
    }
    public boolean  isEchelleAncien(){
        return this.echelleAncien;
    }
    public void  setEchelleAncien(boolean echelleAncien){
        this.echelleAncien = echelleAncien;
    }
    public boolean  isEchelleNouveau(){
        return this.echelleNouveau;
    }
    public void  setEchelleNouveau(boolean echelleNouveau){
        this.echelleNouveau = echelleNouveau;
    }
}
