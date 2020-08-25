package engine.Controller;

import engine.Answer;
import engine.Model.Completed;
import engine.Model.Question;
import engine.Model.User;
import engine.Repository.CompletedRepository;
import engine.Repository.QuestionRepository;
import engine.Model.QuizResponse;
import engine.dto.CompletionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;


import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
public class QuizController {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private CompletedRepository completedRepository;

    @PostMapping(path = "/api/quizzes/{id}/solve", consumes = "application/json", produces = "application/json")
    public QuizResponse solve(@PathVariable int id, @RequestBody Answer answer) {
        if(!questionRepository.existsById(id))
            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        if(questionRepository.findById(id).get().getAnswer().equals(answer.getAnswer())){
            User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Completed completed = new Completed();
            completed.setUser(currentUser);
            completed.setQuestion(questionRepository.findById(id).get());
            completed.setCompletedAt(LocalDateTime.now());
            completedRepository.save(completed);
            return new QuizResponse(true, "Congratulations, you're right!");
        } else {
            return new QuizResponse(false, "Wrong answer! Please, try again.");
        }
    }

    @PostMapping(path = "/api/quizzes", consumes = "application/json", produces = "application/json")
    public Question submitQuestion(@Valid @RequestBody Question q) {
        q.setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        questionRepository.save(q);
        return q;
    }

    @GetMapping(path = "/api/quizzes")
    public Page<Question> getAllQuestions(Pageable paging) {
        Page<Question> pagedResult = questionRepository.findAll(paging);
            return pagedResult;
    }

    @GetMapping(path = "/api/quizzes/{id}")
    public Question getQuestionById(@PathVariable int id) {
        if (!questionRepository.existsById(id)) {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        }
        return questionRepository.findById(id).get();
    }

    @GetMapping(path = "/api/quizzes/completed")
    public Page<CompletionDto> getCompletedQuestions(Pageable paging) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Page<CompletionDto> pagedResult = completedRepository.findAllByUserId(currentUser.getId(),paging)
                .map(completion -> new CompletionDto(completion.getQuestion().getId(), completion.getCompletedAt()));
        return pagedResult;
    }

    @DeleteMapping(path = "/api/quizzes/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteQuestion(@PathVariable int id) {
        if (questionRepository.existsById(id)) {
            User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (currentUser.getId() == (questionRepository.findById(id).get().getUser().getId())) {
                questionRepository.deleteById(id);
            } else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }
        } else {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        }
    }
}
