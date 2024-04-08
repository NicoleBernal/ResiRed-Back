package com.project.resiRed.dto.QuestionDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.project.resiRed.dto.ChoiceDto.choiceInfoResponse;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class updateQuestionRequest {
    private String description;
    private List<choiceInfoResponse> choices;
}
