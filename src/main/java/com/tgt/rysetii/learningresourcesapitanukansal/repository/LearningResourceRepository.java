package com.tgt.rysetii.learningresourcesapitanukansal.repository;

import com.tgt.rysetii.learningresourcesapitanukansal.entity.LearningResources;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearningResourceRepository extends JpaRepository<LearningResources ,Integer> {

}
