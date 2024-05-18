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
import ma.zs.carriere.bean.core.demande.TypeDocument;
import ma.zs.carriere.ws.dto.demande.TypeDocumentDto;

@Component
public class TypeDocumentConverter {


    public  TypeDocumentConverter() {
    }


    public TypeDocument toItem(TypeDocumentDto dto) {
        if (dto == null) {
            return null;
        } else {
        TypeDocument item = new TypeDocument();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());



        return item;
        }
    }


    public TypeDocumentDto toDto(TypeDocument item) {
        if (item == null) {
            return null;
        } else {
            TypeDocumentDto dto = new TypeDocumentDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());


        return dto;
        }
    }


	
    public List<TypeDocument> toItem(List<TypeDocumentDto> dtos) {
        List<TypeDocument> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (TypeDocumentDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<TypeDocumentDto> toDto(List<TypeDocument> items) {
        List<TypeDocumentDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (TypeDocument item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(TypeDocumentDto dto, TypeDocument t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<TypeDocument> copy(List<TypeDocumentDto> dtos) {
        List<TypeDocument> result = new ArrayList<>();
        if (dtos != null) {
            for (TypeDocumentDto dto : dtos) {
                TypeDocument instance = new TypeDocument();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
