package  ma.zs.carriere.ws.converter.commun;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.carriere.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.carriere.ws.converter.avancement.EchelonConverter;



import ma.zs.carriere.zynerator.util.StringUtil;
import ma.zs.carriere.zynerator.converter.AbstractConverter;
import ma.zs.carriere.zynerator.util.DateUtil;
import ma.zs.carriere.bean.core.commun.Diplome;
import ma.zs.carriere.ws.dto.commun.DiplomeDto;

@Component
public class DiplomeConverter {

    @Autowired
    private EchelonConverter echelonConverter ;
    private boolean echelon;

    public  DiplomeConverter() {
        initObject(true);
    }


    public Diplome toItem(DiplomeDto dto) {
        if (dto == null) {
            return null;
        } else {
        Diplome item = new Diplome();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getRef()))
                item.setRef(dto.getRef());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(this.echelon && dto.getEchelon()!=null)
                item.setEchelon(echelonConverter.toItem(dto.getEchelon())) ;




        return item;
        }
    }


    public DiplomeDto toDto(Diplome item) {
        if (item == null) {
            return null;
        } else {
            DiplomeDto dto = new DiplomeDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getRef()))
                dto.setRef(item.getRef());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(this.echelon && item.getEchelon()!=null) {
                dto.setEchelon(echelonConverter.toDto(item.getEchelon())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.echelon = value;
    }
	
    public List<Diplome> toItem(List<DiplomeDto> dtos) {
        List<Diplome> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (DiplomeDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<DiplomeDto> toDto(List<Diplome> items) {
        List<DiplomeDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Diplome item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(DiplomeDto dto, Diplome t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getEchelon() != null)
        echelonConverter.copy(dto.getEchelon(), t.getEchelon());
    }

    public List<Diplome> copy(List<DiplomeDto> dtos) {
        List<Diplome> result = new ArrayList<>();
        if (dtos != null) {
            for (DiplomeDto dto : dtos) {
                Diplome instance = new Diplome();
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
    public boolean  isEchelon(){
        return this.echelon;
    }
    public void  setEchelon(boolean echelon){
        this.echelon = echelon;
    }
}
