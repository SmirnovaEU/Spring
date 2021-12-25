package com.example.homework4.dao;

import com.example.homework4.domain.Answer;
import com.example.homework4.domain.Question;
import com.example.homework4.util.Parser;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuestionDaoSimple implements QuestionDao {
    private final Parser parser;

    public QuestionDaoSimple(Parser parser) {
        this.parser = parser;
    }

    @Override
    public List<Question> getQuestions() {
        return parser.parse();
    }

    @Override
    public Answer getAnswer(Question question, int answerNumber) {
        return question.getAnswers().stream()
                .filter(answer -> answer.getAnswerId() == answerNumber)
                .findFirst().orElse(null);
    }

    @Override
    public Question getQuestion(int questionNumber) {
        return getQuestions().stream().filter(question -> question.getId() == questionNumber).findFirst().orElse(null);
    }

    @Override
    public Answer getRightAnswer(Question question) {
        return question.getAnswers().stream().filter(Answer::isRightAnswer).findFirst().orElse(null);
    }

    @Override
    public boolean isAnswerRight(Question question, int answerNumber) {
        return getRightAnswer(question) == getAnswer(question, answerNumber);
    }
}