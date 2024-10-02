package com.webxel.webxel.Controller;

import com.webxel.webxel.model.Row;
import com.webxel.webxel.service.RowService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tables/{tableId}/rows")
@CrossOrigin("*")
public class RowController {
    private final RowService rowService;

    public RowController(RowService rowService) {
        this.rowService = rowService;

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<Row> createRow(@PathVariable("tableId") Long tableId) {
        Row row = new Row();
        rowService.createRow(tableId, row);
        return new ResponseEntity<>(row, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Row>> getRows(@PathVariable("tableId") Long tableId){
        List<Row> rows = rowService.getRowsByTableId(tableId);
        return new ResponseEntity<>(rows, HttpStatus.OK);
       }

        @PreAuthorize("hasAuthority('ADMIN')")
       @DeleteMapping("/{rowId}")
    public ResponseEntity<?> deleteRow(@PathVariable("tableId") Long tableId, @PathVariable("rowId") Long rowId){
        rowService.deleteById(rowId);
        return new ResponseEntity<>(HttpStatus.OK);
       }

}
