package com.webxel.webxel.repository;

import com.webxel.webxel.model.Row;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RowRepository extends JpaRepository<Row, Long> {
    List<Row> findByDataTableId(Long tableId);

}
