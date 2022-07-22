package com.tgt.rysetii.learningresourcesapitanukansal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tgt.rysetii.learningresourcesapitanukansal.controllers.LearningResourceController;
import com.tgt.rysetii.learningresourcesapitanukansal.entity.LearningResourceStatus;
import com.tgt.rysetii.learningresourcesapitanukansal.entity.LearningResources;
import com.tgt.rysetii.learningresourcesapitanukansal.service.LearningResourceService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LearningResourceController.class)
public class LearningResourceControllerTests {

    @MockBean
    private LearningResourceService learningResourceService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllResources() throws Exception{
        List<LearningResources> learningResources = new ArrayList<>();
        LearningResources lr1 = new LearningResources(110, "Test 1", 115.0, 134.0, LearningResourceStatus.Planning, LocalDate.now(), LocalDate.now().plusMonths(5), LocalDate.now().plusYears(2));
        LearningResources lr2 = new LearningResources(111, "Test 2", 122.0, 174.0, LearningResourceStatus.Planning, LocalDate.now(), LocalDate.now().plusMonths(6), LocalDate.now().plusYears(3));
        learningResources.add(lr1);
        learningResources.add(lr2);

        String expectedResponse = objectMapper.writeValueAsString(learningResources);

        when(learningResourceService.getLearningResources()).thenReturn(learningResources);

        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get("/resources/"))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(expectedResponse, mvcResult.getResponse().getContentAsString());

    }
    @Test
    public void postNewResource() throws Exception{
        List<LearningResources> learningResources = new ArrayList<>();
        LearningResources lr1 = new LearningResources(110, "Test 1", 115.0, 134.0, LearningResourceStatus.Planning, LocalDate.now(), LocalDate.now().plusMonths(5), LocalDate.now().plusYears(2));
        LearningResources lr2 = new LearningResources(111, "Test 2", 122.0, 174.0, LearningResourceStatus.Planning, LocalDate.now(), LocalDate.now().plusMonths(6), LocalDate.now().plusYears(3));
        learningResources.add(lr1);
        learningResources.add(lr2);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/resources/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(learningResources)))
                .andExpect(status().isCreated());

    }
    @Test
    public void deleteResourceById() throws Exception{
        Integer id=109;

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/resources/" + id))
                .andExpect(status().isOk());
    }
}
