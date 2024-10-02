package com.webxel.webxel.Controller;

import com.webxel.webxel.model.Cell;
import com.webxel.webxel.service.impl.CellServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tables/{rowId}/cells")
@CrossOrigin("*")
public class CellController {

    private final CellServiceImpl cellService;

    public CellController(CellServiceImpl cellService) {
        this.cellService = cellService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<Cell> createCell(@PathVariable("rowId") Long rowId, @RequestBody Cell cell) {

        try{
            cellService.createCell(rowId, cell);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{cellId}")
    public ResponseEntity<Cell> getCell(@PathVariable("rowId") Long rowId, @PathVariable("cellId") Long cellId){
        Optional<Cell> cell = cellService.getCellById(cellId);
            return new ResponseEntity<>(cell.get(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping
    public ResponseEntity<List<Cell>> getCells(@PathVariable("rowId") Long rowId) {
       List<Cell> cellByRowId = cellService.getCellsByRowId(rowId);
       return new ResponseEntity<>(cellByRowId, HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{cellId}")
    public ResponseEntity<Cell> updateCell(@PathVariable Long cellId, @RequestBody Cell newCellData, @PathVariable("rowId") Long rowId) {
        try {
            cellService.updateCell(cellId, newCellData, rowId);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{cellId}")
    public ResponseEntity<?> deleteCell(@PathVariable Long cellId, @PathVariable String rowId) {

            cellService.deleteCell(cellId);
            return ResponseEntity.ok().build();

        }
    }

