package com.risk.api.dto;
import java.util.List;
import lombok.Data;

@Data
public class ReconciliationResult {
    private String tableName;
    private long totalMysqlCount;
    private long totalOracleCount;
    private long missingInOracle;
    private long missingInMysql;
    private List<String> missingIds;
    private long startTime;
    private long endTime;
    private String status;
}
