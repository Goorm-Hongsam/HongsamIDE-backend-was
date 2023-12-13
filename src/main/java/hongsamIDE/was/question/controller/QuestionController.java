package hongsamIDE.was.question.controller;

import hongsamIDE.was.question.domain.QuestionBasic;
import hongsamIDE.was.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping("/question")
    public QuestionBasic addQuestion(@RequestBody QuestionBasic newQuestion) {
        return questionService.addQuestion(newQuestion);
    }

    @GetMapping("/question")
    public List<QuestionBasic> getAllQuestion() {
        return questionService.getAllQuestion();
    }
}
