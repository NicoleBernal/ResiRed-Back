package com.project.resiRed.service.admin;
import com.project.resiRed.dto.ChoiceDto.choiceResponse;
import com.project.resiRed.dto.ChoiceDto.createChoiceRequest;
import com.project.resiRed.dto.ChoiceDto.newChoiceResponse;
import com.project.resiRed.dto.MessageDto;
import com.project.resiRed.dto.QuestionDto.updateQuestionRequest;
import com.project.resiRed.entity.Choice;
import com.project.resiRed.entity.Question;
import com.project.resiRed.repository.QuestionRepository;
import com.project.resiRed.repository.ChoiceRepository;



import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.NoSuchElementException;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService{

    private final QuestionRepository questionRepository;
    private final ChoiceRepository choiceRepository;

    @Override
    public MessageDto updateSurveyQuestion(Long questionId, updateQuestionRequest request){
        Question question = questionRepository.findById(questionId).get();
        if(Objects.nonNull(request.getDescription()) && !"".equalsIgnoreCase(request.getDescription())){
            question.setDescription(request.getDescription());
        }
        if(Objects.nonNull(request.getChoices())) {
            for (choiceResponse choiceDto : request.getChoices()) {
                if (Objects.nonNull(choiceDto.getDescription()) && !"".equalsIgnoreCase(choiceDto.getDescription())) {
                    Choice choice = choiceRepository.findById(choiceDto.getChoiceId()).get();
                    choice.setDescription(choiceDto.getDescription());
                }
            }
        }

        questionRepository.save(question);

        return MessageDto.builder().detail("Question updated").build();
    }

    @Override
    public newChoiceResponse addChoiceToQuestion(Long questionId, createChoiceRequest request){
        Question question = questionRepository.findById(questionId).orElseThrow(() -> new NoSuchElementException("Question not found"));
        Choice choice = new Choice();
        choice.setDescription(request.getDescription());
        choice.setVotes(0);

        choiceRepository.saveAndFlush(choice); // Guardar y flushear antes de añadir a la colección

        choice.setQuestion(question);
        question.getChoices().add(choice);
        questionRepository.save(question); // Guardar la pregunta actualizada (preventivo)

        return new newChoiceResponse(choice.getChoiceId(), "Choice added to question");
    }

    @Override
    public MessageDto deleteQuestion(Long questionId){
        questionRepository.deleteById(questionId);
        return MessageDto.builder().detail("Question Deleted").build();
    }

    @Override
    public MessageDto deleteChoice(Long choiceId){
        choiceRepository.deleteById(choiceId);
        return MessageDto.builder().detail("Choice Deleted").build();
    }
}
