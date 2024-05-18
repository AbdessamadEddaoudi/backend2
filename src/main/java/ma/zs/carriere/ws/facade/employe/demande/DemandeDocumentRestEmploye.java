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

import ma.zs.carriere.bean.core.demande.DemandeDocument;
import ma.zs.carriere.dao.criteria.core.demande.DemandeDocumentCriteria;
import ma.zs.carriere.service.facade.employe.demande.DemandeDocumentEmployeService;
import ma.zs.carriere.ws.converter.demande.DemandeDocumentConverter;
import ma.zs.carriere.ws.dto.demande.DemandeDocumentDto;
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
@RequestMapping("/api/employe/demandeDocument/")
public class DemandeDocumentRestEmploye {




    @Operation(summary = "Finds a list of all demandeDocuments")
    @GetMapping("")
    public ResponseEntity<List<DemandeDocumentDto>> findAll() throws Exception {
        ResponseEntity<List<DemandeDocumentDto>> res = null;
        List<DemandeDocument> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<DemandeDocumentDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all demandeDocuments")
    @GetMapping("optimized")
    public ResponseEntity<List<DemandeDocumentDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<DemandeDocumentDto>> res = null;
        List<DemandeDocument> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<DemandeDocumentDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a demandeDocument by id")
    @GetMapping("id/{id}")
    public ResponseEntity<DemandeDocumentDto> findById(@PathVariable Long id) {
        DemandeDocument t = service.findById(id);
        if (t != null) {
            converter.init(true);
            DemandeDocumentDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a demandeDocument by ref")
    @GetMapping("ref/{ref}")
    public ResponseEntity<DemandeDocumentDto> findByRef(@PathVariable String ref) {
	    DemandeDocument t = service.findByReferenceEntity(new DemandeDocument(ref));
        if (t != null) {
            converter.init(true);
            DemandeDocumentDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  demandeDocument")
    @PostMapping("")
    public ResponseEntity<DemandeDocumentDto> save(@RequestBody DemandeDocumentDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            DemandeDocument myT = converter.toItem(dto);
            DemandeDocument t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                DemandeDocumentDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  demandeDocument")
    @PutMapping("")
    public ResponseEntity<DemandeDocumentDto> update(@RequestBody DemandeDocumentDto dto) throws Exception {
        ResponseEntity<DemandeDocumentDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            DemandeDocument t = service.findById(dto.getId());
            converter.copy(dto,t);
            DemandeDocument updated = service.update(t);
            DemandeDocumentDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of demandeDocument")
    @PostMapping("multiple")
    public ResponseEntity<List<DemandeDocumentDto>> delete(@RequestBody List<DemandeDocumentDto> dtos) throws Exception {
        ResponseEntity<List<DemandeDocumentDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<DemandeDocument> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified demandeDocument")
    @DeleteMapping("")
    public ResponseEntity<DemandeDocumentDto> delete(@RequestBody DemandeDocumentDto dto) throws Exception {
		ResponseEntity<DemandeDocumentDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            DemandeDocument t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified demandeDocument")
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
    @Operation(summary = "Delete multiple demandeDocuments by ids")
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



    @Operation(summary = "Finds a demandeDocument and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<DemandeDocumentDto> findWithAssociatedLists(@PathVariable Long id) {
        DemandeDocument loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        DemandeDocumentDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds demandeDocuments by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<DemandeDocumentDto>> findByCriteria(@RequestBody DemandeDocumentCriteria criteria) throws Exception {
        ResponseEntity<List<DemandeDocumentDto>> res = null;
        List<DemandeDocument> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<DemandeDocumentDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated demandeDocuments by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody DemandeDocumentCriteria criteria) throws Exception {
        List<DemandeDocument> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<DemandeDocumentDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets demandeDocument data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody DemandeDocumentCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<DemandeDocumentDto> findDtos(List<DemandeDocument> list){
        converter.initObject(true);
        List<DemandeDocumentDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<DemandeDocumentDto> getDtoResponseEntity(DemandeDocumentDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private DemandeDocumentEmployeService service;
    @Autowired private DemandeDocumentConverter converter;





}
