package  ma.zs.carriere.ws.converter.commun;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.carriere.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;
import ma.zs.carriere.zynerator.util.ListUtil;

import ma.zs.carriere.ws.converter.commun.DiplomeConverter;
import ma.zs.carriere.ws.converter.commun.EmployeConverter;



import ma.zs.carriere.zynerator.util.StringUtil;
import ma.zs.carriere.zynerator.converter.AbstractConverter;
import ma.zs.carriere.zynerator.util.DateUtil;
import ma.zs.carriere.bean.core.commun.EntiteAdmin;
import ma.zs.carriere.ws.dto.commun.EntiteAdminDto;

@Component
public class EntiteAdminConverter {

    @Autowired
    private DiplomeConverter diplomeConverter ;
    @Autowired
    private EmployeConverter employeConverter ;
    private boolean chefDepart;
    private boolean employes;

    public  EntiteAdminConverter() {
        init(true);
    }


    public EntiteAdmin toItem(EntiteAdminDto dto) {
        if (dto == null) {
            return null;
        } else {
        EntiteAdmin item = new EntiteAdmin();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getRef()))
                item.setRef(dto.getRef());
            if(StringUtil.isNotEmpty(dto.getDepartement()))
                item.setDepartement(dto.getDepartement());
            if(this.chefDepart && dto.getChefDepart()!=null)
                item.setChefDepart(employeConverter.toItem(dto.getChefDepart())) ;


            if(this.employes && ListUtil.isNotEmpty(dto.getEmployes()))
                item.setEmployes(employeConverter.toItem(dto.getEmployes()));


        return item;
        }
    }


    public EntiteAdminDto toDto(EntiteAdmin item) {
        if (item == null) {
            return null;
        } else {
            EntiteAdminDto dto = new EntiteAdminDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getRef()))
                dto.setRef(item.getRef());
            if(StringUtil.isNotEmpty(item.getDepartement()))
                dto.setDepartement(item.getDepartement());
            if(this.chefDepart && item.getChefDepart()!=null) {
                employeConverter.setEntiteAdmin(false);
                dto.setChefDepart(employeConverter.toDto(item.getChefDepart())) ;
                employeConverter.setEntiteAdmin(true);

            }
        if(this.employes && ListUtil.isNotEmpty(item.getEmployes())){
            employeConverter.init(true);
            employeConverter.setEntiteAdmin(false);
            dto.setEmployes(employeConverter.toDto(item.getEmployes()));
            employeConverter.setEntiteAdmin(true);

        }


        return dto;
        }
    }

    public void init(boolean value) {
        initList(value);
    }

    public void initList(boolean value) {
        this.employes = value;
    }
    public void initObject(boolean value) {
        this.chefDepart = value;
    }
	
    public List<EntiteAdmin> toItem(List<EntiteAdminDto> dtos) {
        List<EntiteAdmin> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (EntiteAdminDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<EntiteAdminDto> toDto(List<EntiteAdmin> items) {
        List<EntiteAdminDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (EntiteAdmin item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(EntiteAdminDto dto, EntiteAdmin t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getChefDepart() != null)
        employeConverter.copy(dto.getChefDepart(), t.getChefDepart());
        if (dto.getEmployes() != null)
            t.setEmployes(employeConverter.copy(dto.getEmployes()));
    }

    public List<EntiteAdmin> copy(List<EntiteAdminDto> dtos) {
        List<EntiteAdmin> result = new ArrayList<>();
        if (dtos != null) {
            for (EntiteAdminDto dto : dtos) {
                EntiteAdmin instance = new EntiteAdmin();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public DiplomeConverter getDiplomeConverter(){
        return this.diplomeConverter;
    }
    public void setDiplomeConverter(DiplomeConverter diplomeConverter ){
        this.diplomeConverter = diplomeConverter;
    }
    public EmployeConverter getEmployeConverter(){
        return this.employeConverter;
    }
    public void setEmployeConverter(EmployeConverter employeConverter ){
        this.employeConverter = employeConverter;
    }
    public boolean  isChefDepart(){
        return this.chefDepart;
    }
    public void  setChefDepart(boolean chefDepart){
        this.chefDepart = chefDepart;
    }
    public boolean  isEmployes(){
        return this.employes ;
    }
    public void  setEmployes(boolean employes ){
        this.employes  = employes ;
    }
}
