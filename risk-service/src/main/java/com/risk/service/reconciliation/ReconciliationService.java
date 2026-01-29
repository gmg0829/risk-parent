package com.risk.service.reconciliation;

import com.risk.api.dto.ReconciliationResult;
import com.risk.dao.repository.MysqlRepository;
import com.risk.dao.repository.OracleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReconciliationService {
    
    private final MysqlRepository mysqlRepository;
    private final OracleRepository oracleRepository;
    private static final int PAGE_SIZE = 1000;
    
    public ReconciliationResult compare(String tableName, String idColumn) {
        log.info("开始对账: table={}, idColumn={}", tableName, idColumn);
        ReconciliationResult result = new ReconciliationResult();
        result.setTableName(tableName);
        result.setStartTime(System.currentTimeMillis());
        
        Set<String> mysqlIds = collectIds(mysqlRepository);
        Set<String> oracleIds = collectIds(oracleRepository);
        
        Set<String> missingInOracle = mysqlIds.stream()
            .filter(id -> !oracleIds.contains(id))
            .collect(Collectors.toSet());
        
        Set<String> missingInMysql = oracleIds.stream()
            .filter(id -> !mysqlIds.contains(id))
            .collect(Collectors.toSet());
        
        result.setTotalMysqlCount(mysqlIds.size());
        result.setTotalOracleCount(oracleIds.size());
        result.setMissingInOracle(missingInOracle.size());
        result.setMissingInMysql(missingInMysql.size());
        result.setMissingIds(new ArrayList<>(missingInOracle));
        result.setStatus("COMPLETED");
        result.setEndTime(System.currentTimeMillis());
        
        log.info("对账完成: mysql={}, oracle={}, 差异={}", 
            mysqlIds.size(), oracleIds.size(), missingInOracle.size());
        
        return result;
    }
    
    private Set<String> collectIds(Object repository) {
        Set<String> ids = new HashSet<>();
        String lastId = "";
        while (true) {
            List<String> batch;
            if (repository instanceof MysqlRepository) {
                batch = ((MysqlRepository) repository).findIdsByPage(lastId, PAGE_SIZE);
            } else {
                batch = ((OracleRepository) repository).findIdsByPage(lastId, PAGE_SIZE);
            }
            if (batch.isEmpty()) break;
            ids.addAll(batch);
            lastId = batch.get(batch.size() - 1);
        }
        return ids;
    }
}
