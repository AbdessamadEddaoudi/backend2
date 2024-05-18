package  ma.zs.carriere.ws.converter.demande;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.carriere.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.carriere.ws.converter.demande.TypeDocumentConverter;



import ma.zs.carriere.zynerator.util.StringUtil;
import ma.zs.carriere.zynerator.converter.AbstractConverter;
import ma.zs.carriere.zynerator.util.DateUtil;
import ma.zs.carriere.bean.core.demande.TemplateDocument;
import ma.zs.carriere.ws.dto.demande.TemplateDocumentDto;

@Component
public class TemplateDocumentConverter {

    @Autowired
    private TypeDocumentConverter typeDocumentConverter ;
    private boolean typeDocument;

    public  TemplateDocumentConverter() {
        initObject(true);
    }


    public TemplateDocument toItem(TemplateDocumentDto dto) {
        if (dto == null) {
            return null;
        } else {
        TemplateDocument item = new TemplateDocument();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getRef()))
                item.setRef(dto.getRef());
            if(StringUtil.isNotEmpty(dto.getContenu()))
                item.setContenu(dto.getContenu());
            if(this.typeDocument && dto.getTypeDocument()!=null)
                item.setTypeDocument(typeDocumentConverter.toItem(dto.getTypeDocument())) ;




        return item;
        }
    }


    public TemplateDocumentDto toDto(TemplateDocument item) {
        if (item == null) {
            return null;
        } else {
            TemplateDocumentDto dto = new TemplateDocumentDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getRef()))
                dto.setRef(item.getRef());
            if(StringUtil.isNotEmpty(item.getContenu()))
                dto.setContenu(item.getContenu());
            if(this.typeDocument && item.getTypeDocument()!=null) {
                dto.setTypeDocument(typeDocumentConverter.toDto(item.getTypeDocument())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.typeDocument = value;
    }
	
    public List<TemplateDocument> toItem(List<TemplateDocumentDto> dtos) {
        List<TemplateDocument> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (TemplateDocumentDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<TemplateDocumentDto> toDto(List<TemplateDocument> items) {
        List<TemplateDocumentDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (TemplateDocument item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(TemplateDocumentDto dto, TemplateDocument t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getTypeDocument() != null)
        typeDocumentConverter.copy(dto.getTypeDocument(), t.getTypeDocument());
    }

    public List<TemplateDocument> copy(List<TemplateDocumentDto> dtos) {
        List<TemplateDocument> result = new ArrayList<>();
        if (dtos != null) {
            for (TemplateDocumentDto dto : dtos) {
                TemplateDocument instance = new TemplateDocument();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public TypeDocumentConverter getTypeDocumentConverter(){
        return this.typeDocumentConverter;
    }
    public void setTypeDocumentConverter(TypeDocumentConverter typeDocumentConverter ){
        this.typeDocumentConverter = typeDocumentConverter;
    }
    public boolean  isTypeDocument(){
        return this.typeDocument;
    }
    public void  setTypeDocument(boolean typeDocument){
        this.typeDocument = typeDocument;
    }
}
