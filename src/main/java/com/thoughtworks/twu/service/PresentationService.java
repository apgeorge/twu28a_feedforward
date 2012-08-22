package com.thoughtworks.twu.service;


import com.thoughtworks.twu.domain.Presentation;
import com.thoughtworks.twu.persistence.PresentationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PresentationService {
    private PresentationMapper presentationMapper;

    @Autowired
    public PresentationService(PresentationMapper presentationMapper) {
        this.presentationMapper = presentationMapper;
    }

    public Presentation getPresentation(String title){
        return presentationMapper.getPresentationByTitle(title);
    }


}
