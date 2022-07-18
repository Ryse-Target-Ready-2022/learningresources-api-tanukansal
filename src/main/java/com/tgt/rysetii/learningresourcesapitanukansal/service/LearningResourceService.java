package com.tgt.rysetii.learningresourcesapitanukansal.service;

import com.tgt.rysetii.learningresourcesapitanukansal.entity.LearningResourceStatus;
import com.tgt.rysetii.learningresourcesapitanukansal.entity.LearningResources;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class LearningResourceService {
    public void saveLearningResources(List<LearningResources> resources){
        try{
            BufferedWriter bw=new BufferedWriter(new FileWriter("LearningResources.csv",true));
            for (LearningResources resource:resources){
                bw.newLine();
                StringBuffer line=new StringBuffer();
                line.append(resource.getId());
                line.append(",");
                line.append(resource.getName());
                line.append(",");
                line.append(resource.getCostPrice());
                line.append(",");
                line.append(resource.getSellingPrice());
                line.append(",");
                line.append(resource.getProductStatus());
                line.append(",");
                line.append(resource.getCreatedDate());
                line.append(",");
                line.append(resource.getPublishedDate());
                line.append(",");
                line.append(resource.getRetiredDate());
            }
            bw.flush();
            bw.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private List<LearningResources> loadLearningResources(String file) {
        List<LearningResources> resources= new ArrayList<>();
        Path pathToFile= Paths.get(file);
        try(BufferedReader br= Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
            String line=br.readLine();
            while (line!=null){
                String[] attributes=line.split(",");
                LearningResources resource= createResource(attributes);
                resources.add(resource);
                line=br.readLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return resources;

    }
    private  LearningResources createResource(String[] attributes){
        DateTimeFormatter df=DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Integer id=Integer.parseInt(attributes[0]);
        String name=attributes[1];
        Double costPrice=Double.parseDouble(attributes[2]);
        Double sellingPrice=Double.parseDouble(attributes[3]);
        LearningResourceStatus productStatus=LearningResourceStatus.valueOf(attributes[4]);
        LocalDate createdDate=LocalDate.parse(attributes[5],df);
        LocalDate publishedDate=LocalDate.parse(attributes[6],df);
        LocalDate retiredDate=LocalDate.parse(attributes[7],df);
        LearningResources resource=new LearningResources(id,name,costPrice,sellingPrice,productStatus,createdDate,publishedDate,retiredDate);
        return resource;
    }
    public  List<LearningResources> getLearningResources(){
        List<LearningResources> resources = loadLearningResources("LearningResources.csv");
        return resources;
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
}
