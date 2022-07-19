package com.tgt.rysetii.learningresourcesapitanukansal.service;

import com.tgt.rysetii.learningresourcesapitanukansal.entity.LearningResources;
import com.tgt.rysetii.learningresourcesapitanukansal.entity.LearningResourceStatus;
import com.tgt.rysetii.learningresourcesapitanukansal.repository.LearningResourceRepository;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class LearningResourceServiceTests {

    @Mock
    LearningResourceRepository learningResourceRepository;

    @InjectMocks
    LearningResourceService learningResourceService;

    @Test
    public void getProfitMarginsOfALlResources(){
        List<LearningResources> learningResources = new ArrayList<>();
        LearningResources lr1 = new LearningResources(110, "Test 1", 115.0, 134.0, LearningResourceStatus.Planning, LocalDate.now(), LocalDate.now().plusMonths(5), LocalDate.now().plusYears(2));
        LearningResources lr2 = new LearningResources(111, "Test 2", 122.0, 174.0, LearningResourceStatus.Planning, LocalDate.now(), LocalDate.now().plusMonths(6), LocalDate.now().plusYears(3));
        learningResources.add(lr1);
        learningResources.add(lr2);

        List<Double> expectedProfitMargins = learningResources.stream()
                .map(lr -> ((lr.getSellingPrice() - lr.getCostPrice())/lr.getSellingPrice()))
                .collect(toList());

        when(learningResourceRepository.findAll()).thenReturn(learningResources);

        List<Double> actualProfitMargins = learningResourceService.calculateProfitMargin();
        assertEquals(expectedProfitMargins, actualProfitMargins, "Wrong profit margins");
    }

    @Test
    public void sortBasedOnProfitMargin(){
        List<LearningResources> learningResources = new ArrayList<>();
        LearningResources lr1 = new LearningResources(110, "Test 1", 115.0, 134.0, LearningResourceStatus.Planning, LocalDate.now(), LocalDate.now().plusMonths(5), LocalDate.now().plusYears(2));
        LearningResources lr2 = new LearningResources(111, "Test 2", 122.0, 174.0, LearningResourceStatus.Planning, LocalDate.now(), LocalDate.now().plusMonths(6), LocalDate.now().plusYears(3));
        learningResources.add(lr1);
        learningResources.add(lr2);

        learningResources.sort((r1, r2) -> {
            Double profitMargin1 = (r1.getSellingPrice() - r1.getCostPrice())/r1.getSellingPrice();
            Double profitMargin2 = (r2.getSellingPrice() - r2.getCostPrice())/r2.getSellingPrice();

            return profitMargin2.compareTo(profitMargin1) ;
        });

        when(learningResourceRepository.findAll()).thenReturn(learningResources);

        List<LearningResources> learningResourcesSorted = learningResourceService.sortByProfitMargin();

        assertEquals(learningResources, learningResourcesSorted, "The learning resources are not sorted by profit margin");
    }

    @Test
    public void saveResources(){
        List<LearningResources> learningResources = new ArrayList<>();
        LearningResources lr1 = new LearningResources(110, "Test 1", 115.0, 134.0, LearningResourceStatus.Planning, LocalDate.now(), LocalDate.now().plusMonths(5), LocalDate.now().plusYears(2));
        LearningResources lr2 = new LearningResources(111, "Test 2", 122.0, 174.0, LearningResourceStatus.Planning, LocalDate.now(), LocalDate.now().plusMonths(6), LocalDate.now().plusYears(3));
        LearningResources lr3 = new LearningResources(112, "Test 3", 131.0, 158.0, LearningResourceStatus.Live, LocalDate.now(), LocalDate.now().plusMonths(6), LocalDate.now().plusYears(3));
        LearningResources lr4 = new LearningResources(113, "Test 4", 107.0, 160.0, LearningResourceStatus.Published, LocalDate.now(), LocalDate.now().plusMonths(6), LocalDate.now().plusYears(3));
        learningResources.add(lr1);
        learningResources.add(lr2);
        learningResources.add(lr3);
        learningResources.add(lr4);

        learningResourceService.saveLearningResources(learningResources);

        verify(learningResourceRepository, times(learningResources.size())).save(any(LearningResources.class));
    }

    @Test
    public void deleteLearningResourceById(){
        int id = 113;

        learningResourceService.deleteById(id);

        verify(learningResourceRepository, times(1)).deleteById(id);
    }


}
