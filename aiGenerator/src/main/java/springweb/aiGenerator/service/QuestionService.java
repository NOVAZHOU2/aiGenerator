package springweb.aiGenerator.service;

import org.springframework.data.domain.Pageable;
import springweb.aiGenerator.dto.request.QuestionQueryParams;
import springweb.aiGenerator.dto.response.QuestionDetailResponse;
import springweb.aiGenerator.dto.response.QuestionListResponse;
import springweb.aiGenerator.entity.Question;
import springweb.aiGenerator.entity.DifficultyLevel;
import springweb.aiGenerator.entity.QuestionType;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface QuestionService {

    // 创建题目
    Question createQuestion(Question question);

    // 更新题目
    Question updateQuestion(Long id, Question question);

    // 获取题目详情
    Question getQuestionById(Long id);

    // 分页查询题目
    Page<QuestionListResponse> searchQuestions(QuestionQueryParams params);


    // 批量删除题目
    void batchDeleteQuestions(List<Long> ids);


    // 获取题目统计信息
    Map<String, Long> getQuestionStatistics();

    // 根据ID列表批量获取题目详情
    List<Question> getQuestionsByIds(List<Long> ids);

}