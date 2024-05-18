package  ma.zs.carriere.ws.converter.mandat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.carriere.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.carriere.ws.converter.commun.EmployeConverter;



import ma.zs.carriere.zynerator.util.StringUtil;
import ma.zs.carriere.zynerator.util.DateUtil;
import ma.zs.carriere.bean.core.mandat.Mandat;
import ma.zs.carriere.ws.dto.mandat.MandatDto;

@Component
public class MandatConverter {

    @Autowired
    private ResponsabiliteConverter responsabiliteConverter ;
    @Autowired
    private EmployeConverter employeConverter ;
    private boolean employe;
    private boolean responsabilite;

    public  MandatConverter() {
        initObject(true);
    }


    public Mandat toItem(MandatDto dto) {
        if (dto == null) {
            return null;
        } else {
        Mandat item = new Mandat();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getRef()))
                item.setRef(dto.getRef());
            if(StringUtil.isNotEmpty(dto.getDateDebut()))
                item.setDateDebut(DateUtil.stringEnToDate(dto.getDateDebut()));
            if(StringUtil.isNotEmpty(dto.getDateFin()))
                item.setDateFin(DateUtil.stringEnToDate(dto.getDateFin()));
            if(StringUtil.isNotEmpty(dto.getSalaire()))
                item.setSalaire(dto.getSalaire());
            if(this.employe && dto.getEmploye()!=null)
                item.setEmploye(employeConverter.toItem(dto.getEmploye())) ;

            if(this.responsabilite && dto.getResponsabilite()!=null)
                item.setResponsabilite(responsabiliteConverter.toItem(dto.getResponsabilite())) ;




        return item;
        }
    }


    public MandatDto toDto(Mandat item) {
        if (item == null) {
            return null;
        } else {
            MandatDto dto = new MandatDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getRef()))
                dto.setRef(item.getRef());
            if(item.getDateDebut()!=null)
                dto.setDateDebut(DateUtil.dateTimeToString(item.getDateDebut()));
            if(item.getDateFin()!=null)
                dto.setDateFin(DateUtil.dateTimeToString(item.getDateFin()));
            if(StringUtil.isNotEmpty(item.getSalaire()))
                dto.setSalaire(item.getSalaire());
            if(this.employe && item.getEmploye()!=null) {
                dto.setEmploye(employeConverter.toDto(item.getEmploye())) ;

            }
            if(this.responsabilite && item.getResponsabilite()!=null) {
                dto.setResponsabilite(responsabiliteConverter.toDto(item.getResponsabilite())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.employe = value;
        this.responsabilite = value;
    }
	
    public List<Mandat> toItem(List<MandatDto> dtos) {
        List<Mandat> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (MandatDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<MandatDto> toDto(List<Mandat> items) {
        List<MandatDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Mandat item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(MandatDto dto, Mandat t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getEmploye() != null)
        employeConverter.copy(dto.getEmploye(), t.getEmploye());
        if (dto.getResponsabilite() != null)
        responsabiliteConverter.copy(dto.getResponsabilite(), t.getResponsabilite());
    }

    public List<Mandat> copy(List<MandatDto> dtos) {
        List<Mandat> result = new ArrayList<>();
        if (dtos != null) {
            for (MandatDto dto : dtos) {
                Mandat instance = new Mandat();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public ResponsabiliteConverter getResponsabiliteConverter(){
        return this.responsabiliteConverter;
    }
    public void setResponsabiliteConverter(ResponsabiliteConverter responsabiliteConverter ){
        this.responsabiliteConverter = responsabiliteConverter;
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
    public boolean  isResponsabilite(){
        return this.responsabilite;
    }
    public void  setResponsabilite(boolean responsabilite){
        this.responsabilite = responsabilite;
    }
}
