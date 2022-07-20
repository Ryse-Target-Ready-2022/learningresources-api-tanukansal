package com.tgt.rysetii.learningresourcesapitanukansal.controllers;

import com.tgt.rysetii.learningresourcesapitanukansal.entity.LearningResources;
import com.tgt.rysetii.learningresourcesapitanukansal.service.LearningResourceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resources")
public class LearningResourceController {
    private final LearningResourceService learningResourceService;

    LearningResourceController(LearningResourceService learningResourceService){
        this.learningResourceService=learningResourceService;
    }

    @GetMapping("/")
    public List<LearningResources> getLearningResources(){
        return learningResourceService.getLearningResources();
    }

    @PostMapping(value="/",consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveLearningResources(@RequestBody List<LearningResources> newResource){
          learningResourceService.saveLearningResources(newResource);
    }

    @DeleteMapping("/{id}")
    public void deleteResource(@PathVariable Integer id){
        learningResourceService.deleteById(id);
    }
}
