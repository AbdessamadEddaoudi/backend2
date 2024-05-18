package  ma.zs.carriere.ws.converter.demande;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.carriere.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.carriere.ws.converter.commun.EmployeConverter;
import ma.zs.carriere.ws.converter.demande.TypeDocumentConverter;



import ma.zs.carriere.zynerator.util.StringUtil;
import ma.zs.carriere.zynerator.converter.AbstractConverter;
import ma.zs.carriere.zynerator.util.DateUtil;
import ma.zs.carriere.bean.core.demande.DemandeDocument;
import ma.zs.carriere.ws.dto.demande.DemandeDocumentDto;

@Component
public class DemandeDocumentConverter {

    @Autowired
    private EmployeConverter employeConverter ;
    @Autowired
    private TypeDocumentConverter typeDocumentConverter ;
    private boolean employe;
    private boolean typeDocument;

    public  DemandeDocumentConverter() {
        initObject(true);
    }


    public DemandeDocument toItem(DemandeDocumentDto dto) {
        if (dto == null) {
            return null;
        } else {
        DemandeDocument item = new DemandeDocument();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getRef()))
                item.setRef(dto.getRef());
            if(this.employe && dto.getEmploye()!=null)
                item.setEmploye(employeConverter.toItem(dto.getEmploye())) ;

            if(this.typeDocument && dto.getTypeDocument()!=null)
                item.setTypeDocument(typeDocumentConverter.toItem(dto.getTypeDocument())) ;




        return item;
        }
    }


    public DemandeDocumentDto toDto(DemandeDocument item) {
        if (item == null) {
            return null;
        } else {
            DemandeDocumentDto dto = new DemandeDocumentDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getRef()))
                dto.setRef(item.getRef());
            if(this.employe && item.getEmploye()!=null) {
                dto.setEmploye(employeConverter.toDto(item.getEmploye())) ;

            }
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
        this.employe = value;
        this.typeDocument = value;
    }
	
    public List<DemandeDocument> toItem(List<DemandeDocumentDto> dtos) {
        List<DemandeDocument> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (DemandeDocumentDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<DemandeDocumentDto> toDto(List<DemandeDocument> items) {
        List<DemandeDocumentDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (DemandeDocument item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(DemandeDocumentDto dto, DemandeDocument t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getEmploye() != null)
        employeConverter.copy(dto.getEmploye(), t.getEmploye());
        if (dto.getTypeDocument() != null)
        typeDocumentConverter.copy(dto.getTypeDocument(), t.getTypeDocument());
    }

    public List<DemandeDocument> copy(List<DemandeDocumentDto> dtos) {
        List<DemandeDocument> result = new ArrayList<>();
        if (dtos != null) {
            for (DemandeDocumentDto dto : dtos) {
                DemandeDocument instance = new DemandeDocument();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public EmployeConverter getEmployeConverter(){
        return this.employeConverter;
    }
    public void setEmployeConverter(EmployeConverter employeConverter ){
        this.employeConverter = employeConverter;
    }
    public TypeDocumentConverter getTypeDocumentConverter(){
        return this.typeDocumentConverter;
    }
    public void setTypeDocumentConverter(TypeDocumentConverter typeDocumentConverter ){
        this.typeDocumentConverter = typeDocumentConverter;
    }
    public boolean  isEmploye(){
        return this.employe;
    }
    public void  setEmploye(boolean employe){
        this.employe = employe;
    }
    public boolean  isTypeDocument(){
        return this.typeDocument;
    }
    public void  setTypeDocument(boolean typeDocument){
        this.typeDocument = typeDocument;
    }
}
