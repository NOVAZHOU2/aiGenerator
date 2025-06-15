package springweb.aiGenerator.dto.request;

import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Data
public class BatchOperationRequest {
    // 添加缺失的 getter 方法
    @NotEmpty(message = "ID列表不能为空")
    private List<Long> ids;

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }
}