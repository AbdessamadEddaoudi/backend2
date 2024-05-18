package  ma.zs.carriere.ws.converter.demande;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.carriere.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zs.carriere.zynerator.util.StringUtil;
import ma.zs.carriere.zynerator.converter.AbstractConverter;
import ma.zs.carriere.zynerator.util.DateUtil;
import ma.zs.carriere.bean.core.demande.EtatDemandeDocument;
import ma.zs.carriere.ws.dto.demande.EtatDemandeDocumentDto;

@Component
public class EtatDemandeDocumentConverter {


    public  EtatDemandeDocumentConverter() {
    }


    public EtatDemandeDocument toItem(EtatDemandeDocumentDto dto) {
        if (dto == null) {
            return null;
        } else {
        EtatDemandeDocument item = new EtatDemandeDocument();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());



        return item;
        }
    }


    public EtatDemandeDocumentDto toDto(EtatDemandeDocument item) {
        if (item == null) {
            return null;
        } else {
            EtatDemandeDocumentDto dto = new EtatDemandeDocumentDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());


        return dto;
        }
    }


	
    public List<EtatDemandeDocument> toItem(List<EtatDemandeDocumentDto> dtos) {
        List<EtatDemandeDocument> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (EtatDemandeDocumentDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<EtatDemandeDocumentDto> toDto(List<EtatDemandeDocument> items) {
        List<EtatDemandeDocumentDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (EtatDemandeDocument item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(EtatDemandeDocumentDto dto, EtatDemandeDocument t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<EtatDemandeDocument> copy(List<EtatDemandeDocumentDto> dtos) {
        List<EtatDemandeDocument> result = new ArrayList<>();
        if (dtos != null) {
            for (EtatDemandeDocumentDto dto : dtos) {
                EtatDemandeDocument instance = new EtatDemandeDocument();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
