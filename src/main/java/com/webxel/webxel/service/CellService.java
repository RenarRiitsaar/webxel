package com.webxel.webxel.service;

import com.webxel.webxel.model.Cell;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CellService {

   Cell createCell(Long rowId, Cell cell);

   List<Cell> getCellsByRowId(Long rowId);

   Cell updateCell(Long id, Cell newCellData, Long rowId);

   Optional<Cell> getCellById(Long id);

   void deleteCell(Long id);
}
