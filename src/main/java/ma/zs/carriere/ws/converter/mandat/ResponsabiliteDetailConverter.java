package  ma.zs.carriere.ws.converter.mandat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.carriere.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;


import ma.zs.carriere.zynerator.util.StringUtil;
import ma.zs.carriere.zynerator.util.DateUtil;
import ma.zs.carriere.bean.core.mandat.ResponsabiliteDetail;
import ma.zs.carriere.ws.dto.mandat.ResponsabiliteDetailDto;

@Component
public class ResponsabiliteDetailConverter {

    @Autowired
    private ResponsabiliteConverter responsabiliteConverter ;
    private boolean responsabilite;

    public  ResponsabiliteDetailConverter() {
        initObject(true);
    }


    public ResponsabiliteDetail toItem(ResponsabiliteDetailDto dto) {
        if (dto == null) {
            return null;
        } else {
        ResponsabiliteDetail item = new ResponsabiliteDetail();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getRef()))
                item.setRef(dto.getRef());
            if(StringUtil.isNotEmpty(dto.getDateApplicationMin()))
                item.setDateApplicationMin(DateUtil.stringEnToDate(dto.getDateApplicationMin()));
            if(StringUtil.isNotEmpty(dto.getDateApplicationMax()))
                item.setDateApplicationMax(DateUtil.stringEnToDate(dto.getDateApplicationMax()));
            if(StringUtil.isNotEmpty(dto.getSalaire()))
                item.setSalaire(dto.getSalaire());
            if(this.responsabilite && dto.getResponsabilite()!=null)
                item.setResponsabilite(responsabiliteConverter.toItem(dto.getResponsabilite())) ;




        return item;
        }
    }


    public ResponsabiliteDetailDto toDto(ResponsabiliteDetail item) {
        if (item == null) {
            return null;
        } else {
            ResponsabiliteDetailDto dto = new ResponsabiliteDetailDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getRef()))
                dto.setRef(item.getRef());
            if(item.getDateApplicationMin()!=null)
                dto.setDateApplicationMin(DateUtil.dateTimeToString(item.getDateApplicationMin()));
            if(item.getDateApplicationMax()!=null)
                dto.setDateApplicationMax(DateUtil.dateTimeToString(item.getDateApplicationMax()));
            if(StringUtil.isNotEmpty(item.getSalaire()))
                dto.setSalaire(item.getSalaire());
            if(this.responsabilite && item.getResponsabilite()!=null) {
                dto.setResponsabilite(responsabiliteConverter.toDto(item.getResponsabilite())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.responsabilite = value;
    }
	
    public List<ResponsabiliteDetail> toItem(List<ResponsabiliteDetailDto> dtos) {
        List<ResponsabiliteDetail> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ResponsabiliteDetailDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ResponsabiliteDetailDto> toDto(List<ResponsabiliteDetail> items) {
        List<ResponsabiliteDetailDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (ResponsabiliteDetail item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ResponsabiliteDetailDto dto, ResponsabiliteDetail t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getResponsabilite() != null)
        responsabiliteConverter.copy(dto.getResponsabilite(), t.getResponsabilite());
    }

    public List<ResponsabiliteDetail> copy(List<ResponsabiliteDetailDto> dtos) {
        List<ResponsabiliteDetail> result = new ArrayList<>();
        if (dtos != null) {
            for (ResponsabiliteDetailDto dto : dtos) {
                ResponsabiliteDetail instance = new ResponsabiliteDetail();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public ResponsabiliteConverter getResponsabiliteConverter(){
        return this.responsabiliteConverter;
    }
    public void setResponsabiliteConverter(ResponsabiliteConverter responsabiliteConverter ){
        this.responsabiliteConverter = responsabiliteConverter;
    }
    public boolean  isResponsabilite(){
        return this.responsabilite;
    }
    public void  setResponsabilite(boolean responsabilite){
        this.responsabilite = responsabilite;
    }
}
