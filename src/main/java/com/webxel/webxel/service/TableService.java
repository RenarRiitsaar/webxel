package com.webxel.webxel.service;

import com.webxel.webxel.model.DataTable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TableService {

    void createTable(DataTable table);

    List<DataTable> getAllTables();

    Optional<DataTable> getTableById(Long id);

    DataTable updateTable(Long id, DataTable newTableData);

    void deleteTable(Long id);
}
