package springweb.aiGenerator.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springweb.aiGenerator.dto.request.BatchOperationRequest;
import springweb.aiGenerator.entity.ApiResponse;
import springweb.aiGenerator.dto.response.QuestionDetailResponse;
import springweb.aiGenerator.entity.Question;
import springweb.aiGenerator.service.QuestionService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/batch-operations")
public class BatchOperationController {

    private final QuestionService questionService;

    // 构造函数注入
    public BatchOperationController(QuestionService questionService) {
        this.questionService = questionService;
    }

    /**
     * 批量获取题目详情
     */
    @PostMapping("/questions")
    public ResponseEntity<ApiResponse> getQuestionsByIds(
            @Valid @RequestBody BatchOperationRequest request
    ) {
        List<Question> questions = questionService.getQuestionsByIds(request.getIds());
        List<QuestionDetailResponse> responses = questions.stream()
                .map(QuestionDetailResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(responses));
    }

}