package com.westerndigital.keyinsight.JiraRestAPIsPOJO.ProjectPOJO;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Versions {
    private String self;
    private String id;
    private String name;
    private String description;
    private Boolean archived;
    private Boolean released;
    private LocalDate startDate;
    private LocalDate releaseDate;
    private Boolean overdue;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MMM/yy")
    private LocalDate userStartDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MMM/yy")
    private LocalDate userReleaseDate;
    private String projectId;
}
