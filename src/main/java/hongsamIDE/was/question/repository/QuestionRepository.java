package hongsamIDE.was.question.repository;

import hongsamIDE.was.question.domain.QuestionBasic;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class QuestionRepository {
    
    private final EntityManager em;

    public QuestionBasic save(QuestionBasic questionBasic) {
        em.persist(questionBasic);
        return questionBasic;
    }
    
    public List<QuestionBasic> findAll() {
        String jpql = "select q from QuestionBasic q";

        List<QuestionBasic> resultList = em.createQuery(jpql, QuestionBasic.class)
                .getResultList();
        return resultList;
    }
    
}
