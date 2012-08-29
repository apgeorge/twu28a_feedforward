package com.thoughtworks.twu.service;


import com.thoughtworks.twu.domain.Presentation;
import com.thoughtworks.twu.persistence.PresentationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PresentationService {
    private PresentationMapper presentationMapper;

    @Autowired
    public PresentationService(PresentationMapper presentationMapper) {
        this.presentationMapper = presentationMapper;
    }

    public Presentation getPresentation(String title){
        return presentationMapper.getPresentation(title, null);
    }


    public List<Presentation> getPresentationByOwner(String owner) {
        return presentationMapper.getPresentationsByOwner(owner);
    }
}
