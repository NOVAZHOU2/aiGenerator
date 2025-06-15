package springweb.aiGenerator.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springweb.aiGenerator.dto.request.QuestionQueryParams;
import springweb.aiGenerator.dto.request.BatchOperationRequest;
import springweb.aiGenerator.dto.request.QuestionCreateUpdateDTO;
import springweb.aiGenerator.dto.response.PagedResponse;
import springweb.aiGenerator.entity.ApiResponse;
import springweb.aiGenerator.dto.response.QuestionDetailResponse;
import springweb.aiGenerator.dto.response.QuestionListResponse;
import springweb.aiGenerator.entity.ApiResponse;
import springweb.aiGenerator.entity.DifficultyLevel;
import springweb.aiGenerator.entity.Question;
import springweb.aiGenerator.entity.QuestionType;
import springweb.aiGenerator.service.QuestionService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private final QuestionService questionService;

    // 显式构造函数代替 Lombok
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    private static final int MAX_PAGE_SIZE = 100;
    private static final List<String> ALLOWED_SORT_FIELDS = List.of(
            "id", "type", "difficulty", "language", "createdAt"
    );

    /**
     * 创建题目
     */
    @PostMapping
    public ResponseEntity<ApiResponse> createQuestion(
            @Valid @RequestBody QuestionCreateUpdateDTO dto
    ) {
        Question question = convertToEntity(dto);
        Question created = questionService.createQuestion(question);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(new QuestionDetailResponse(created)));
    }

    /**
     * 更新题目
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateQuestion(
            @PathVariable Long id,
            @Valid @RequestBody QuestionCreateUpdateDTO dto
    ) {
        Question question = convertToEntity(dto);
        question.setId(id);
        Question updated = questionService.updateQuestion(id, question);
        return ResponseEntity.ok(ApiResponse.success(new QuestionDetailResponse(updated)));
    }

    /**
     * 获取题目详情
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getQuestionById(@PathVariable Long id) {
        Question question = questionService.getQuestionById(id);

        QuestionDetailResponse response = new QuestionDetailResponse(question);

        // 调试输出
        System.out.println("原始对象: " + question);
        System.out.println("响应对象: " + response);

        try {
            String json = new ObjectMapper().writeValueAsString(response);
            System.out.println("序列化JSON: " + json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok(ApiResponse.success(response));
    }

    /**
     * 分页查询题目
     */
    @GetMapping
    public ResponseEntity<Page<QuestionListResponse>> searchQuestions(
            @RequestParam(required = false) QuestionType type,
            @RequestParam(required = false) DifficultyLevel difficulty,
            @RequestParam(required = false) String language,
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "DESC") String direction) {

        QuestionQueryParams params = new QuestionQueryParams();
        params.setType(type);
        params.setDifficulty(difficulty);
        params.setLanguage(language);
        params.setKeyword(keyword);
        params.setPage(page);
        params.setSize(size);
        params.setSortBy(sortBy);
        params.setDirection(Sort.Direction.fromString(direction));

        return ResponseEntity.ok(questionService.searchQuestions(params));
    }


    /**
     * 批量删除题目
     */
    @PostMapping("/batch-delete")
    public ResponseEntity<ApiResponse> batchDeleteQuestions(
            @Valid @RequestBody BatchOperationRequest request
    ) {
        questionService.batchDeleteQuestions(request.getIds());
        return ResponseEntity.ok(ApiResponse.success("批量删除成功"));
    }


    /**
     * 将DTO转换为实体
     */
    private Question convertToEntity(QuestionCreateUpdateDTO dto) {
        Question question = new Question();
        question.setType(dto.getType());
        question.setContent(dto.getContent());
        question.setOptions(dto.getOptions());
        question.setAnswer(dto.getAnswer());
        question.setDifficulty(dto.getDifficulty());
        question.setLanguage(dto.getLanguage());
        return question;
    }
}