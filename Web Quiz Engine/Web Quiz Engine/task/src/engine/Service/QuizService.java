package engine.Service;

import engine.Model.User;
import engine.Repository.QuestionRepository;
import engine.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class QuizService {

    QuestionRepository questionRepository;
    UserRepository  userRepository;

    @Autowired
    public QuizService(QuestionRepository questionRepository, UserRepository userRepository) {
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
    }

    public void deleteQuestionById(int id) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (currentUser.getId() == (questionRepository.findById(id).get().getUser().getId()))
            questionRepository.deleteById(id);;
    }
}
