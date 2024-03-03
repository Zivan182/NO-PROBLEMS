package ru.noproblems.backend.service.dto;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterParams {
    private String search;
    private List<String> topic;  
    private List<String> olympiad; 
    private Long complexityfrom;
    private Long complexityto;
    private Long yearfrom;   
    private Long yearto;

    private List<String> liked;  
    private List<String> solved;  
    private List<String> added;  

    private Long page;
}
