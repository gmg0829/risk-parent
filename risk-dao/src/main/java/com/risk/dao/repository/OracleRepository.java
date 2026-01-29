package com.risk.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OracleRepository {
    @Query("SELECT e.id FROM #{#entityName} e WHERE e.id > :lastId ORDER BY e.id")
    List<String> findIdsByPage(@Param("lastId") String lastId, @Param("limit") int limit);
    long count();
}
