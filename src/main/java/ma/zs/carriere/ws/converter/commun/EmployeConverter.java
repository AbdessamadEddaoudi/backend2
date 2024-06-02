package  ma.zs.carriere.ws.converter.commun;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.carriere.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.carriere.ws.converter.commun.EntiteAdminConverter;
import ma.zs.carriere.ws.converter.commun.DiplomeConverter;

import ma.zs.carriere.bean.core.commun.EntiteAdmin;


import ma.zs.carriere.zynerator.util.StringUtil;
import ma.zs.carriere.zynerator.converter.AbstractConverter;
import ma.zs.carriere.zynerator.util.DateUtil;
import ma.zs.carriere.bean.core.commun.Employe;
import ma.zs.carriere.ws.dto.commun.EmployeDto;

@Component
public class EmployeConverter {

    @Autowired
    private EntiteAdminConverter entiteAdminConverter ;
    @Autowired
    private DiplomeConverter diplomeConverter ;
    private boolean diplome;
    private boolean entiteAdmin;

    public  EmployeConverter() {
        initObject(true);
    }


    public Employe toItem(EmployeDto dto) {
        if (dto == null) {
            return null;
        } else {
        Employe item = new Employe();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());

            if(StringUtil.isNotEmpty(dto.getRef()))
                item.setRef(dto.getRef());

            if(StringUtil.isNotEmpty(dto.getSalaire()))
                item.setSalaire(dto.getSalaire());
            item.setCredentialsNonExpired(dto.getCredentialsNonExpired());
            item.setEnabled(dto.getEnabled());
            item.setAccountNonExpired(dto.getAccountNonExpired());
            item.setAccountNonLocked(dto.getAccountNonLocked());
            item.setPasswordChanged(dto.getPasswordChanged());
            if(StringUtil.isNotEmpty(dto.getUsername()))
                item.setUsername(dto.getUsername());
            if(StringUtil.isNotEmpty(dto.getPassword()))
                item.setPassword(dto.getPassword());
            if(this.diplome && dto.getDiplome()!=null)
                item.setDiplome(diplomeConverter.toItem(dto.getDiplome())) ;

            if(dto.getEntiteAdmin() != null && dto.getEntiteAdmin().getId() != null){
                item.setEntiteAdmin(new EntiteAdmin());
                item.getEntiteAdmin().setId(dto.getEntiteAdmin().getId());
                item.getEntiteAdmin().setDepartement(dto.getEntiteAdmin().getDepartement());
            }



            //item.setRoles(dto.getRoles());

        return item;
        }
    }


    public EmployeDto toDto(Employe item) {
        if (item == null) {
            return null;
        } else {
            EmployeDto dto = new EmployeDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());

            if(StringUtil.isNotEmpty(item.getRef()))
                dto.setRef(item.getRef());

            if(StringUtil.isNotEmpty(item.getSalaire()))
                dto.setSalaire(item.getSalaire());
            if(StringUtil.isNotEmpty(item.getCredentialsNonExpired()))
                dto.setCredentialsNonExpired(item.getCredentialsNonExpired());
            if(StringUtil.isNotEmpty(item.getEnabled()))
                dto.setEnabled(item.getEnabled());
            if(StringUtil.isNotEmpty(item.getAccountNonExpired()))
                dto.setAccountNonExpired(item.getAccountNonExpired());
            if(StringUtil.isNotEmpty(item.getAccountNonLocked()))
                dto.setAccountNonLocked(item.getAccountNonLocked());
            if(StringUtil.isNotEmpty(item.getPasswordChanged()))
                dto.setPasswordChanged(item.getPasswordChanged());
            if(StringUtil.isNotEmpty(item.getUsername()))
                dto.setUsername(item.getUsername());
            if(this.diplome && item.getDiplome()!=null) {
                dto.setDiplome(diplomeConverter.toDto(item.getDiplome())) ;

            }
            if(this.entiteAdmin && item.getEntiteAdmin()!=null) {
                entiteAdminConverter.setChefDepart(false);
                dto.setEntiteAdmin(entiteAdminConverter.toDto(item.getEntiteAdmin())) ;
                entiteAdminConverter.setChefDepart(true);

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.diplome = value;
        this.entiteAdmin = value;
    }
	
    public List<Employe> toItem(List<EmployeDto> dtos) {
        List<Employe> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (EmployeDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<EmployeDto> toDto(List<Employe> items) {
        List<EmployeDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Employe item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(EmployeDto dto, Employe t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getDiplome() != null)
        diplomeConverter.copy(dto.getDiplome(), t.getDiplome());
        if (dto.getEntiteAdmin() != null)
        entiteAdminConverter.copy(dto.getEntiteAdmin(), t.getEntiteAdmin());
    }

    public List<Employe> copy(List<EmployeDto> dtos) {
        List<Employe> result = new ArrayList<>();
        if (dtos != null) {
            for (EmployeDto dto : dtos) {
                Employe instance = new Employe();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public EntiteAdminConverter getEntiteAdminConverter(){
        return this.entiteAdminConverter;
    }
    public void setEntiteAdminConverter(EntiteAdminConverter entiteAdminConverter ){
        this.entiteAdminConverter = entiteAdminConverter;
    }
    public DiplomeConverter getDiplomeConverter(){
        return this.diplomeConverter;
    }
    public void setDiplomeConverter(DiplomeConverter diplomeConverter ){
        this.diplomeConverter = diplomeConverter;
    }
    public boolean  isDiplome(){
        return this.diplome;
    }
    public void  setDiplome(boolean diplome){
        this.diplome = diplome;
    }
    public boolean  isEntiteAdmin(){
        return this.entiteAdmin;
    }
    public void  setEntiteAdmin(boolean entiteAdmin){
        this.entiteAdmin = entiteAdmin;
    }
}
