package hongsamIDE.was.question.service;

import hongsamIDE.was.question.domain.QuestionBasic;
import hongsamIDE.was.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionBasic addQuestion(QuestionBasic newQuestion) {
        return questionRepository.save(newQuestion);
    }

    public List<QuestionBasic> getAllQuestion() {
        return questionRepository.findAll();
    }
}
