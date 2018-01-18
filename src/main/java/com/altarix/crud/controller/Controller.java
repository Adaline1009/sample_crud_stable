package com.altarix.crud.controller;

import com.altarix.crud.model.entity.Doc;
import com.altarix.crud.service.DataService;
import com.altarix.crud.service.ReportService;
import com.altarix.crud.service.ReportServiceDescribedViaXml;
import com.altarix.crud.service.RequestService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

/**
 * Created by Вячеслав on 23.10.2017.
 */
@Api(description = "Document operations")
@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private DataService dataService;

    @Autowired
    @Qualifier("requestService")
    private RequestService requestService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private ReportServiceDescribedViaXml reportServiceXml;

    @ApiOperation(value = "View a list of exists documents", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list of documents"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/docs")
    public Iterable<Doc> getDocs() {
        return dataService.getAll();
    }


    @ApiOperation(value = "Search a document with an ID", response = Doc.class)
    @RequestMapping(value = "/{card_id}", method = RequestMethod.GET)
    public Doc getDoc(@PathVariable("card_id") long id) {
        return dataService.getById(id);
    }

    @ApiOperation(value = "Delete a document")
    @RequestMapping(value = "/remove/{card_id}", method = RequestMethod.DELETE)
    public long remove(@PathVariable("card_id") long id) {
        dataService.remove(dataService.getById(id));
        return id;
    }

    @ApiOperation(value = "Add a document")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void insert(@RequestBody Doc doc) throws ParseException {
        dataService.save(doc);
        try {
            requestService.synchronizeData(doc);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation(value = "Update a document")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Doc update(@RequestBody Doc doc) throws ParseException {
        dataService.update(doc);
        return doc;
    }

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public ResponseEntity<Resource> getReport() {
        ByteArrayResource resource = new ByteArrayResource(reportService.createReport().toByteArray());
        return ResponseEntity.ok()
                .header("Content-Disposition", " attachment; filename=report.xls")
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(resource);
    }

    @RequestMapping(value = "/reportXml", method = RequestMethod.GET)
    public ResponseEntity<Resource> getReportXml() {
        ByteArrayResource resource = new ByteArrayResource(reportServiceXml.createReport().toByteArray());
        return ResponseEntity.ok()
                .header("Content-Disposition", " attachment; filename=reportV2.xls")
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(resource);
    }

    @RequestMapping(value = "/kindfox", method = RequestMethod.GET)
    public ResponseEntity.BodyBuilder fox() {
        return ResponseEntity.ok();
    }

}
