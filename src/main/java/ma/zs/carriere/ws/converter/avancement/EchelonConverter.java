package  ma.zs.carriere.ws.converter.avancement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.carriere.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.carriere.ws.converter.avancement.EchelleConverter;



import ma.zs.carriere.zynerator.util.StringUtil;
import ma.zs.carriere.zynerator.converter.AbstractConverter;
import ma.zs.carriere.zynerator.util.DateUtil;
import ma.zs.carriere.bean.core.avancement.Echelon;
import ma.zs.carriere.ws.dto.avancement.EchelonDto;

@Component
public class EchelonConverter {

    @Autowired
    private EchelleConverter echelleConverter ;
    private boolean echelle;

    public  EchelonConverter() {
        initObject(true);
    }


    public Echelon toItem(EchelonDto dto) {
        if (dto == null) {
            return null;
        } else {
        Echelon item = new Echelon();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getRef()))
                item.setRef(dto.getRef());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getSalaire()))
                item.setSalaire(dto.getSalaire());
            if(this.echelle && dto.getEchelle()!=null)
                item.setEchelle(echelleConverter.toItem(dto.getEchelle())) ;




        return item;
        }
    }


    public EchelonDto toDto(Echelon item) {
        if (item == null) {
            return null;
        } else {
            EchelonDto dto = new EchelonDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getRef()))
                dto.setRef(item.getRef());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getSalaire()))
                dto.setSalaire(item.getSalaire());
            if(this.echelle && item.getEchelle()!=null) {
                dto.setEchelle(echelleConverter.toDto(item.getEchelle())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.echelle = value;
    }
	
    public List<Echelon> toItem(List<EchelonDto> dtos) {
        List<Echelon> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (EchelonDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<EchelonDto> toDto(List<Echelon> items) {
        List<EchelonDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Echelon item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(EchelonDto dto, Echelon t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getEchelle() != null)
        echelleConverter.copy(dto.getEchelle(), t.getEchelle());
    }

    public List<Echelon> copy(List<EchelonDto> dtos) {
        List<Echelon> result = new ArrayList<>();
        if (dtos != null) {
            for (EchelonDto dto : dtos) {
                Echelon instance = new Echelon();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public EchelleConverter getEchelleConverter(){
        return this.echelleConverter;
    }
    public void setEchelleConverter(EchelleConverter echelleConverter ){
        this.echelleConverter = echelleConverter;
    }
    public boolean  isEchelle(){
        return this.echelle;
    }
    public void  setEchelle(boolean echelle){
        this.echelle = echelle;
    }
}
