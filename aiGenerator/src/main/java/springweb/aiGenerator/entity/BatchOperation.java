package springweb.aiGenerator.entity;

import lombok.Data;

import java.util.List;

@Data
public class BatchOperation {
    private List<Long> ids;
}