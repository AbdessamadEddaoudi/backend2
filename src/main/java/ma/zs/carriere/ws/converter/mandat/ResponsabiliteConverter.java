package  ma.zs.carriere.ws.converter.mandat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.carriere.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.carriere.ws.converter.avancement.EchelonConverter;



import ma.zs.carriere.zynerator.util.StringUtil;
import ma.zs.carriere.bean.core.mandat.Responsabilite;
import ma.zs.carriere.ws.dto.mandat.ResponsabiliteDto;

@Component
public class ResponsabiliteConverter {

    @Autowired
    private EchelonConverter echelonConverter ;
    private boolean echelonMin;

    public  ResponsabiliteConverter() {
        initObject(true);
    }


    public Responsabilite toItem(ResponsabiliteDto dto) {
        if (dto == null) {
            return null;
        } else {
        Responsabilite item = new Responsabilite();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getRef()))
                item.setRef(dto.getRef());
            if(StringUtil.isNotEmpty(dto.getNom()))
                item.setNom(dto.getNom());
            if(this.echelonMin && dto.getEchelonMin()!=null)
                item.setEchelonMin(echelonConverter.toItem(dto.getEchelonMin())) ;




        return item;
        }
    }


    public ResponsabiliteDto toDto(Responsabilite item) {
        if (item == null) {
            return null;
        } else {
            ResponsabiliteDto dto = new ResponsabiliteDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getRef()))
                dto.setRef(item.getRef());
            if(StringUtil.isNotEmpty(item.getNom()))
                dto.setNom(item.getNom());
            if(this.echelonMin && item.getEchelonMin()!=null) {
                dto.setEchelonMin(echelonConverter.toDto(item.getEchelonMin())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.echelonMin = value;
    }
	
    public List<Responsabilite> toItem(List<ResponsabiliteDto> dtos) {
        List<Responsabilite> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ResponsabiliteDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ResponsabiliteDto> toDto(List<Responsabilite> items) {
        List<ResponsabiliteDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Responsabilite item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ResponsabiliteDto dto, Responsabilite t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getEchelonMin() != null)
        echelonConverter.copy(dto.getEchelonMin(), t.getEchelonMin());
    }

    public List<Responsabilite> copy(List<ResponsabiliteDto> dtos) {
        List<Responsabilite> result = new ArrayList<>();
        if (dtos != null) {
            for (ResponsabiliteDto dto : dtos) {
                Responsabilite instance = new Responsabilite();
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
    public boolean  isEchelonMin(){
        return this.echelonMin;
    }
    public void  setEchelonMin(boolean echelonMin){
        this.echelonMin = echelonMin;
    }
}
