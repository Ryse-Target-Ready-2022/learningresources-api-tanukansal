package com.tgt.rysetii.learningresourcesapitanukansal.service;

import com.tgt.rysetii.learningresourcesapitanukansal.entity.LearningResources;
import com.tgt.rysetii.learningresourcesapitanukansal.repository.LearningResourceRepository;
import org.springframework.stereotype.Service;


import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class LearningResourceService {
    private final LearningResourceRepository learningResourceRepository;
    public LearningResourceService(LearningResourceRepository learningResourceRepository){
        this.learningResourceRepository=learningResourceRepository;
    }
    public void saveLearningResources(List<LearningResources> resources){
        for(LearningResources lr:resources){
            learningResourceRepository.save(lr);
        }
    }

    public  List<LearningResources> getLearningResources(){
        return learningResourceRepository.findAll();
    }

    public List<Double> calculateProfitMargin(){
        List<LearningResources> resources=getLearningResources();
        List<Double> profitMargin = resources.stream()
                .map(r -> ((r.getSellingPrice() - r.getCostPrice())/r.getSellingPrice()))
                .collect(toList());

        return profitMargin;
    }
    public List<LearningResources> sortByProfitMargin(){
        List<LearningResources> resources = getLearningResources();

        resources.sort((r1, r2) -> {
            Double profitMargin1 = (r1.getSellingPrice() - r1.getCostPrice())/r1.getSellingPrice();
            Double profitMargin2 = (r2.getSellingPrice() - r2.getCostPrice())/r2.getSellingPrice();

            return profitMargin2.compareTo(profitMargin1) ;
        });

        return resources;
    }
    public void deleteById(int id) {
        learningResourceRepository.deleteById(id);
    }
}
