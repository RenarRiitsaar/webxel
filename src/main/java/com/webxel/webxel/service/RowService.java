package com.webxel.webxel.service;

import com.webxel.webxel.model.Row;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RowService {

    List<Row> getRowsByTableId(Long tableId);

    Row createRow(Long tableId, Row row);

    void deleteById(Long rowId);
}
