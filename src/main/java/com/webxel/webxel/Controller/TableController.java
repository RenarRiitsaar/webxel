package com.webxel.webxel.Controller;

import com.webxel.webxel.model.DataTable;
import com.webxel.webxel.service.impl.TableServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/tables")
public class TableController {

    private final TableServiceImpl tableService;

    public TableController(TableServiceImpl tableService) {
        this.tableService = tableService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<DataTable> createTable(@RequestBody DataTable table) {
        tableService.createTable(table);


        return new ResponseEntity<>(table,HttpStatus.OK);
    }
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping
    public ResponseEntity<List<DataTable>> getTables() {

        List<DataTable> allTables = tableService.getAllTables();
        if(!allTables.isEmpty()) {

            return new ResponseEntity<>(allTables, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<DataTable>> getTable(@PathVariable Long id) {
        Optional<DataTable> table = tableService.getTableById(id);

        return new ResponseEntity<>(table, HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<DataTable> updateTable(@PathVariable Long id, @RequestBody DataTable newTableData) {
        try {
            DataTable updatedTable = tableService.updateTable(id, newTableData);
            return new ResponseEntity<>(updatedTable, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTable(@PathVariable Long id) {
        try {
            tableService.deleteTable(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
