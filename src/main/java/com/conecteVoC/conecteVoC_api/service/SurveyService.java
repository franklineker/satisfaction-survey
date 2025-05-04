package com.conecteVoC.conecteVoC_api.service;

import com.conecteVoC.conecteVoC_api.dto.question.CreateQuestionDTO;
import com.conecteVoC.conecteVoC_api.dto.survey.CreateSurveyDTO;
import com.conecteVoC.conecteVoC_api.dto.survey.SurveyResponseDTO;
import com.conecteVoC.conecteVoC_api.dto.survey.UpdateSurveyDTO;
import com.conecteVoC.conecteVoC_api.entity.Question;
import com.conecteVoC.conecteVoC_api.entity.Survey;
import com.conecteVoC.conecteVoC_api.mapper.QuestionMapper;
import com.conecteVoC.conecteVoC_api.mapper.SurveyMapper;
import com.conecteVoC.conecteVoC_api.repository.QuestionRepository;
import com.conecteVoC.conecteVoC_api.repository.SurveyRepository;
import com.conecteVoC.conecteVoC_api.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class SurveyService {

    private final SurveyRepository surveyRepository;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    @Transactional
    public SurveyResponseDTO create(CreateSurveyDTO dto) {
        Survey survey = SurveyMapper.fromCreateSurveyDTO(dto, userRepository);
        survey = surveyRepository.save(survey);

        List<Question> questions = new ArrayList<>();

        for(CreateQuestionDTO questionDTO : dto.getQuestions()) {
            Question question = QuestionMapper.toQuestion(questionDTO, survey);
            questions.add(question);
        }

        survey.setQuestions(questions);
        questionRepository.saveAll(questions);

        return SurveyMapper.toSurveyResponseDTO(survey);
    }

    public SurveyResponseDTO getById(String id) {
        Survey survey = surveyRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException(String.format("Survey with id: <%s> not found.",id)));

        SurveyResponseDTO responseDTO = SurveyMapper.toSurveyResponseDTO(survey);

        return responseDTO;
    }

    public Page<SurveyResponseDTO> getSurveys(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.desc("createdAt")));
        Page<Survey> surveysPage = surveyRepository.findAll(pageable);

        if (page >= surveysPage.getTotalPages() && surveysPage.getTotalPages() > 0) {
            return Page.empty(pageable);
        }

        Page<SurveyResponseDTO> surveysPageDTO = surveysPage.map(SurveyMapper::toSurveyResponseDTO);

        return surveysPageDTO;
    }

    @Transactional
    public SurveyResponseDTO updateSurvey(UUID id, UpdateSurveyDTO dto) {
        Survey survey = surveyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Survey with id <%s> not found.", id)));
        survey = SurveyMapper.fromUpdateSurveyDTO(dto, survey, questionRepository);
        System.out.println(survey);

        questionRepository.saveAll(survey.getQuestions());

        SurveyResponseDTO responseDTO = SurveyMapper.toSurveyResponseDTO(surveyRepository.save(survey));

        return responseDTO;
    }


}
