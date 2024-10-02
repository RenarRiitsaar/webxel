package com.webxel.webxel.service.impl;

import com.webxel.webxel.model.DataTable;
import com.webxel.webxel.repository.TableRepository;
import com.webxel.webxel.service.TableService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TableServiceImpl implements TableService {


    private final TableRepository tableRepository;

    public TableServiceImpl(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    @Override
    public void createTable(DataTable table) {
        tableRepository.save(table);
    }

    @Override
    public List<DataTable> getAllTables() {

        return tableRepository.findAll();
    }

    @Override
    public Optional<DataTable> getTableById(Long id) {
        return tableRepository.findById(id);
    }

    @Override
    public DataTable updateTable(Long id, DataTable newTableData) {
        return tableRepository.findById(id).map(table -> {
            table.setName(newTableData.getName());
            return tableRepository.save(table);
        }).orElseThrow(() -> new RuntimeException("Table not found"));
    }

    @Override
    public void deleteTable(Long id) {
        tableRepository.deleteById(id);
    }
}
