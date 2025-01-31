package com.webxel.webxel.repository;

import com.webxel.webxel.model.DataTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends JpaRepository<DataTable, Long> {
}
