package com.webxel.webxel.repository;

import com.webxel.webxel.model.Cell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CellRepository extends JpaRepository<Cell, Long> {

    List<Cell> findByRowId(Long rowId);
}
