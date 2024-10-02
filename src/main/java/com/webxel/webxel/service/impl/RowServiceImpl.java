package com.webxel.webxel.service.impl;

import com.webxel.webxel.model.DataTable;
import com.webxel.webxel.model.Row;
import com.webxel.webxel.repository.RowRepository;
import com.webxel.webxel.repository.TableRepository;
import com.webxel.webxel.service.RowService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RowServiceImpl implements RowService {
    private final RowRepository rowRepository;
    private final TableRepository tableRepository;

    public RowServiceImpl(RowRepository rowRepository, TableRepository tableRepository) {
        this.rowRepository = rowRepository;
        this.tableRepository = tableRepository;
    }

    @Override
    public List<Row> getRowsByTableId(Long tableId) {
        return rowRepository.findByDataTableId(tableId);
    }

    @Override
    public Row createRow(Long tableId, Row row) {
       Optional<DataTable> table = tableRepository.findById(tableId);
        if (table.isPresent()) {
          row.setDataTable(table.get());
            return rowRepository.save(row);
        } else {
            throw new RuntimeException("Row not found");
        }
    }

    @Override
    public void deleteById(Long rowId) {
        Optional<Row> row = rowRepository.findById(rowId);

        row.ifPresent(rowRepository::delete);
    }
}

