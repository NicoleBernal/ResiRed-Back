package com.project.resiRed.controller;


import com.project.resiRed.entity.Survey;
import com.project.resiRed.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.project.resiRed.dto.MessageDto;
import com.project.resiRed.dto.SurveyDto.createSurveyRequest;
import com.project.resiRed.dto.SurveyDto.updateTopicRequest;
import com.project.resiRed.dto.QuestionDto.updateQuestionRequest;



import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.http.HttpStatus;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


@RestController
@RequestMapping("/survey")
@RequiredArgsConstructor
public class surveyController {
    private final SurveyService surveyService;

    @PostMapping(value = "create")
    public  ResponseEntity<MessageDto> createSurvey(@RequestBody createSurveyRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        if (authorities.contains(new SimpleGrantedAuthority("ADMIN"))) {
            return ResponseEntity.ok(
                    surveyService.createSurvey(request)
            );
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                    MessageDto.builder().detail("Insufficient permissions").build());
        }

    }


    @GetMapping(value = "list/unassigned")
    public  ResponseEntity<?> getAllUnassignedSurveys(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        if (authorities.contains(new SimpleGrantedAuthority("ADMIN"))) {
            return ResponseEntity.ok(
                    surveyService.getAllUnassignedSurveys()
            );
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                    MessageDto.builder().detail("Insufficient permissions").build());
        }

    }

    @GetMapping(value = "list/unassigned/{id}")
    public  ResponseEntity<?> getSurveyQuestions(@PathVariable Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        if (authorities.contains(new SimpleGrantedAuthority("ADMIN"))) {
            return ResponseEntity.ok(
                    surveyService.getSurveyQuestions(id)
            );
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                    MessageDto.builder().detail("Insufficient permissions").build());
        }

    }

    @DeleteMapping(value = "update/unassigned/{id}")
    public  ResponseEntity<MessageDto> updateSurveyTopic(@PathVariable Long id, @RequestBody  updateTopicRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        if (authorities.contains(new SimpleGrantedAuthority("ADMIN"))) {
            return ResponseEntity.ok(
                    surveyService.updateSurveyTopic(id, request)
            );
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                    MessageDto.builder().detail("Insufficient permissions").build());
        }

    }

    @DeleteMapping(value = "list/unassigned/{id}")
    public  ResponseEntity<MessageDto> deleteSurvey(@PathVariable Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        if (authorities.contains(new SimpleGrantedAuthority("ADMIN"))) {
            return ResponseEntity.ok(
                    surveyService.deleteSurvey(id)
            );
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                    MessageDto.builder().detail("Insufficient permissions").build());
        }

    }

    @PutMapping(value = "question/update/{id}")
    public  ResponseEntity<MessageDto> updateSurveyQuestion(@PathVariable Long id, @RequestBody updateQuestionRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        if (authorities.contains(new SimpleGrantedAuthority("ADMIN"))) {
            return ResponseEntity.ok(
                    surveyService.updateSurveyQuestion(id, request)
            );
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                    MessageDto.builder().detail("Insufficient permissions").build());
        }

    }


}
