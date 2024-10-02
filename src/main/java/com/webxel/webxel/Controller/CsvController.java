package com.webxel.webxel.Controller;


import com.webxel.webxel.model.Cell;
import com.webxel.webxel.model.DataTable;
import com.webxel.webxel.model.Row;
import com.webxel.webxel.service.impl.RowServiceImpl;
import com.webxel.webxel.service.impl.TableServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/csv")
@CrossOrigin("*")
public class CsvController {

    private final TableServiceImpl tableService;
    private final RowServiceImpl   rowService;

    public CsvController(TableServiceImpl tableService, RowServiceImpl rowService) {
        this.tableService = tableService;
        this.rowService = rowService;
    }

    @GetMapping("{id}/export")
    public ResponseEntity<?> exportToCsv(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Optional<DataTable> table = tableService.getTableById(id);
        List<Row> rows = null;


        if (table.isPresent()) {
            rows = rowService.getRowsByTableId(table.get().getId());
        }

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=table.csv");

        CSVPrinter printer = null;
        try {
            printer = new CSVPrinter(response.getWriter(), CSVFormat.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (printer != null && rows != null) {
            for (Row row : rows) {
                boolean containsData = false;

                for (Cell cell : row.getCells()) {
                    if(cell.getValue()!= null && !cell.getValue().toString().isEmpty()){
                        containsData = true;
                    }
                    try {
                        if(containsData) {
                         printer.print(cell.getValue());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                try {
                    printer.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

