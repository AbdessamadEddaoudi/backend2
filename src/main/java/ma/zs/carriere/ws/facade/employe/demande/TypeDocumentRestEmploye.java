package  ma.zs.carriere.ws.facade.employe.demande;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.carriere.bean.core.demande.TypeDocument;
import ma.zs.carriere.dao.criteria.core.demande.TypeDocumentCriteria;
import ma.zs.carriere.service.facade.employe.demande.TypeDocumentEmployeService;
import ma.zs.carriere.ws.converter.demande.TypeDocumentConverter;
import ma.zs.carriere.ws.dto.demande.TypeDocumentDto;
import ma.zs.carriere.zynerator.controller.AbstractController;
import ma.zs.carriere.zynerator.dto.AuditEntityDto;
import ma.zs.carriere.zynerator.util.PaginatedList;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zs.carriere.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zs.carriere.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/employe/typeDocument/")
public class TypeDocumentRestEmploye {




    @Operation(summary = "Finds a list of all typeDocuments")
    @GetMapping("")
    public ResponseEntity<List<TypeDocumentDto>> findAll() throws Exception {
        ResponseEntity<List<TypeDocumentDto>> res = null;
        List<TypeDocument> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeDocumentDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a typeDocument by id")
    @GetMapping("id/{id}")
    public ResponseEntity<TypeDocumentDto> findById(@PathVariable Long id) {
        TypeDocument t = service.findById(id);
        if (t != null) {
            TypeDocumentDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  typeDocument")
    @PostMapping("")
    public ResponseEntity<TypeDocumentDto> save(@RequestBody TypeDocumentDto dto) throws Exception {
        if(dto!=null){
            TypeDocument myT = converter.toItem(dto);
            TypeDocument t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                TypeDocumentDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  typeDocument")
    @PutMapping("")
    public ResponseEntity<TypeDocumentDto> update(@RequestBody TypeDocumentDto dto) throws Exception {
        ResponseEntity<TypeDocumentDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            TypeDocument t = service.findById(dto.getId());
            converter.copy(dto,t);
            TypeDocument updated = service.update(t);
            TypeDocumentDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of typeDocument")
    @PostMapping("multiple")
    public ResponseEntity<List<TypeDocumentDto>> delete(@RequestBody List<TypeDocumentDto> dtos) throws Exception {
        ResponseEntity<List<TypeDocumentDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<TypeDocument> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified typeDocument")
    @DeleteMapping("")
    public ResponseEntity<TypeDocumentDto> delete(@RequestBody TypeDocumentDto dto) throws Exception {
		ResponseEntity<TypeDocumentDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            TypeDocument t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified typeDocument")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) throws Exception {
        ResponseEntity<Long> res;
        HttpStatus status = HttpStatus.PRECONDITION_FAILED;
        if (id != null) {
            boolean resultDelete = service.deleteById(id);
            if (resultDelete) {
                status = HttpStatus.OK;
            }
        }
        res = new ResponseEntity<>(id, status);
        return res;
    }
    @Operation(summary = "Delete multiple typeDocuments by ids")
    @DeleteMapping("multiple/id")
    public ResponseEntity<List<Long>> deleteByIdIn(@RequestBody List<Long> ids) throws Exception {
        ResponseEntity<List<Long>> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (ids != null) {
            service.deleteByIdIn(ids);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(ids, status);
        return res;
     }



    @Operation(summary = "Finds a typeDocument and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<TypeDocumentDto> findWithAssociatedLists(@PathVariable Long id) {
        TypeDocument loaded =  service.findWithAssociatedLists(id);
        TypeDocumentDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds typeDocuments by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<TypeDocumentDto>> findByCriteria(@RequestBody TypeDocumentCriteria criteria) throws Exception {
        ResponseEntity<List<TypeDocumentDto>> res = null;
        List<TypeDocument> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeDocumentDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated typeDocuments by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody TypeDocumentCriteria criteria) throws Exception {
        List<TypeDocument> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<TypeDocumentDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets typeDocument data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody TypeDocumentCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<TypeDocumentDto> findDtos(List<TypeDocument> list){
        List<TypeDocumentDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<TypeDocumentDto> getDtoResponseEntity(TypeDocumentDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private TypeDocumentEmployeService service;
    @Autowired private TypeDocumentConverter converter;





}
