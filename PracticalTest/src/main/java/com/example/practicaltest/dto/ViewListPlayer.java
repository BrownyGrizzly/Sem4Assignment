package com.example.practicaltest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ViewListPlayer {
    private int id;
    private String name;
    private String age;
    private String indexName;
    private Float value;
}
