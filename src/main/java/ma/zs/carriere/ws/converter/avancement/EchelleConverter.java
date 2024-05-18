package  ma.zs.carriere.ws.converter.avancement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.carriere.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zs.carriere.zynerator.util.StringUtil;
import ma.zs.carriere.zynerator.converter.AbstractConverter;
import ma.zs.carriere.zynerator.util.DateUtil;
import ma.zs.carriere.bean.core.avancement.Echelle;
import ma.zs.carriere.ws.dto.avancement.EchelleDto;

@Component
public class EchelleConverter {


    public  EchelleConverter() {
    }


    public Echelle toItem(EchelleDto dto) {
        if (dto == null) {
            return null;
        } else {
        Echelle item = new Echelle();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getRef()))
                item.setRef(dto.getRef());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());



        return item;
        }
    }


    public EchelleDto toDto(Echelle item) {
        if (item == null) {
            return null;
        } else {
            EchelleDto dto = new EchelleDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getRef()))
                dto.setRef(item.getRef());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());


        return dto;
        }
    }


	
    public List<Echelle> toItem(List<EchelleDto> dtos) {
        List<Echelle> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (EchelleDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<EchelleDto> toDto(List<Echelle> items) {
        List<EchelleDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Echelle item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(EchelleDto dto, Echelle t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<Echelle> copy(List<EchelleDto> dtos) {
        List<Echelle> result = new ArrayList<>();
        if (dtos != null) {
            for (EchelleDto dto : dtos) {
                Echelle instance = new Echelle();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
