package  ma.zs.carriere.ws.facade.admin.demande;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.carriere.bean.core.demande.TemplateDocument;
import ma.zs.carriere.dao.criteria.core.demande.TemplateDocumentCriteria;
import ma.zs.carriere.service.facade.admin.demande.TemplateDocumentAdminService;
import ma.zs.carriere.ws.converter.demande.TemplateDocumentConverter;
import ma.zs.carriere.ws.dto.demande.TemplateDocumentDto;
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
@RequestMapping("/api/admin/templateDocument/")
public class TemplateDocumentRestAdmin {




    @Operation(summary = "Finds a list of all templateDocuments")
    @GetMapping("")
    public ResponseEntity<List<TemplateDocumentDto>> findAll() throws Exception {
        ResponseEntity<List<TemplateDocumentDto>> res = null;
        List<TemplateDocument> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<TemplateDocumentDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all templateDocuments")
    @GetMapping("optimized")
    public ResponseEntity<List<TemplateDocumentDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<TemplateDocumentDto>> res = null;
        List<TemplateDocument> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<TemplateDocumentDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a templateDocument by id")
    @GetMapping("id/{id}")
    public ResponseEntity<TemplateDocumentDto> findById(@PathVariable Long id) {
        TemplateDocument t = service.findById(id);
        if (t != null) {
            converter.init(true);
            TemplateDocumentDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a templateDocument by ref")
    @GetMapping("ref/{ref}")
    public ResponseEntity<TemplateDocumentDto> findByRef(@PathVariable String ref) {
	    TemplateDocument t = service.findByReferenceEntity(new TemplateDocument(ref));
        if (t != null) {
            converter.init(true);
            TemplateDocumentDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  templateDocument")
    @PostMapping("")
    public ResponseEntity<TemplateDocumentDto> save(@RequestBody TemplateDocumentDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            TemplateDocument myT = converter.toItem(dto);
            TemplateDocument t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                TemplateDocumentDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  templateDocument")
    @PutMapping("")
    public ResponseEntity<TemplateDocumentDto> update(@RequestBody TemplateDocumentDto dto) throws Exception {
        ResponseEntity<TemplateDocumentDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            TemplateDocument t = service.findById(dto.getId());
            converter.copy(dto,t);
            TemplateDocument updated = service.update(t);
            TemplateDocumentDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of templateDocument")
    @PostMapping("multiple")
    public ResponseEntity<List<TemplateDocumentDto>> delete(@RequestBody List<TemplateDocumentDto> dtos) throws Exception {
        ResponseEntity<List<TemplateDocumentDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<TemplateDocument> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified templateDocument")
    @DeleteMapping("")
    public ResponseEntity<TemplateDocumentDto> delete(@RequestBody TemplateDocumentDto dto) throws Exception {
		ResponseEntity<TemplateDocumentDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            TemplateDocument t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified templateDocument")
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
    @Operation(summary = "Delete multiple templateDocuments by ids")
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



    @Operation(summary = "Finds a templateDocument and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<TemplateDocumentDto> findWithAssociatedLists(@PathVariable Long id) {
        TemplateDocument loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        TemplateDocumentDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds templateDocuments by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<TemplateDocumentDto>> findByCriteria(@RequestBody TemplateDocumentCriteria criteria) throws Exception {
        ResponseEntity<List<TemplateDocumentDto>> res = null;
        List<TemplateDocument> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<TemplateDocumentDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated templateDocuments by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody TemplateDocumentCriteria criteria) throws Exception {
        List<TemplateDocument> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<TemplateDocumentDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets templateDocument data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody TemplateDocumentCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<TemplateDocumentDto> findDtos(List<TemplateDocument> list){
        converter.initObject(true);
        List<TemplateDocumentDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<TemplateDocumentDto> getDtoResponseEntity(TemplateDocumentDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private TemplateDocumentAdminService service;
    @Autowired private TemplateDocumentConverter converter;





}
