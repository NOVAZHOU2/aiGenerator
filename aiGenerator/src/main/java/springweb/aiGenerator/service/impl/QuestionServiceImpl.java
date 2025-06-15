package springweb.aiGenerator.service.impl;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springweb.aiGenerator.dto.request.QuestionQueryParams;
import springweb.aiGenerator.dto.response.QuestionListResponse;
import springweb.aiGenerator.entity.Question;
import springweb.aiGenerator.entity.DifficultyLevel;
import springweb.aiGenerator.entity.QuestionType;

import springweb.aiGenerator.repository.QuestionRepository;
import springweb.aiGenerator.service.QuestionService;
import springweb.aiGenerator.exception.NotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    @Transactional
    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    @Transactional
    public Question updateQuestion(Long id, Question questionDetails) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("题目不存在，ID: " + id));

        // 更新字段
        if (questionDetails.getType() != null) {
            question.setType(questionDetails.getType());
        }
        if (questionDetails.getContent() != null) {
            question.setContent(questionDetails.getContent());
        }
        if (questionDetails.getOptions() != null) {
            question.setOptions(questionDetails.getOptions());
        }
        if (questionDetails.getAnswer() != null) {
            question.setAnswer(questionDetails.getAnswer());
        }
        if (questionDetails.getDifficulty() != null) {
            question.setDifficulty(questionDetails.getDifficulty());
        }
        if (questionDetails.getLanguage() != null) {
            question.setLanguage(questionDetails.getLanguage());
        }

        return questionRepository.save(question);
    }

    @Override
    public Question getQuestionById(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("题目不存在，ID: " + id));
    }


    @Override
    public Page<QuestionListResponse> searchQuestions(QuestionQueryParams params) {
        // 构建分页和排序
        PageRequest pageable = PageRequest.of(
                params.getPage(),
                params.getSize(),
                Sort.by(params.getDirection(), params.getSortBy())
        );

        // 构建查询条件
        Specification<Question> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (params.getType() != null) {
                predicates.add(cb.equal(root.get("type"), params.getType()));
            }
            if (params.getDifficulty() != null) {
                predicates.add(cb.equal(root.get("difficulty"), params.getDifficulty()));
            }
            if (params.getLanguage() != null && !params.getLanguage().isEmpty()) {
                predicates.add(cb.equal(root.get("language"), params.getLanguage()));
            }
            if (params.getKeyword() != null && !params.getKeyword().isEmpty()) {
                predicates.add(cb.like(
                        cb.lower(root.get("content")),
                        "%" + params.getKeyword().toLowerCase() + "%"
                ));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        // 执行查询并转换
        return questionRepository.findAll(spec, pageable)
                .map(QuestionListResponse::new);
    }

    @Override
    @Transactional
    public void batchDeleteQuestions(List<Long> ids) {
        questionRepository.deleteByIds(ids);
    }


    @Override
    public Map<String, Long> getQuestionStatistics() {
        Map<String, Long> stats = new HashMap<>();

        // 1. 统计所有题目总数（不再区分是否删除）
        stats.put("totalQuestions", questionRepository.count());

        // 2. 按题目类型统计
        List<Object[]> typeStats = questionRepository.countGroupedByType();
        for (Object[] result : typeStats) {
            QuestionType type = (QuestionType) result[0];
            Long count = (Long) result[1];
            stats.put(type.name() + "Count", count);
        }

        // 3. 按题目难度统计
        List<Object[]> difficultyStats = questionRepository.countGroupedByDifficulty();
        for (Object[] result : difficultyStats) {
            DifficultyLevel difficulty = (DifficultyLevel) result[0];
            Long count = (Long) result[1];
            stats.put(difficulty.name() + "Count", count);
        }

        // 4. 按编程语言统计
        List<Object[]> languageStats = questionRepository.countGroupedByLanguage();
        for (Object[] result : languageStats) {
            String language = (String) result[0];
            Long count = (Long) result[1];
            stats.put(language + "Count", count);
        }

        return stats;
    }

    // 修改获取题目方法，移除软删除检查
    @Override
    public List<Question> getQuestionsByIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            throw new IllegalArgumentException("ID列表不能为空");
        }

        // 限制最大查询数量
        if (ids.size() > 100) {
            throw new IllegalArgumentException("一次最多查询100条记录");
        }

        // 使用 findAllById 替代 findByIdInAndDeletedFalse
        List<Question> questions = questionRepository.findAllById(ids);

        if (questions.size() != ids.size()) {
            // 记录缺失的ID
            List<Long> foundIds = questions.stream()
                    .map(Question::getId)
                    .collect(Collectors.toList());

            List<Long> missingIds = ids.stream()
                    .filter(id -> !foundIds.contains(id))
                    .collect(Collectors.toList());

            if (!missingIds.isEmpty()) {
                throw new NotFoundException("找不到ID为 " + missingIds + " 的题目");
            }
        }

        return questions;
    }
}