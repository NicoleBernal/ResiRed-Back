package com.project.resiRed.dto.SurveyDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class getAllUnassignedSurveysResponse {
    private Long surveyId;
    private String topic;
}
