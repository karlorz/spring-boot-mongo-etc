package com.report.service.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.Date;

@Document(collection = "posts")
@TypeAlias("PostModal")
@Data
@Component
@ComponentScan(basePackages = {"com.report.service"})
public class PostModal {

    @NotNull
    @NotEmpty
    private String profile;

    @NotNull
    private String type;

    @NotNull
    private String description;

    @NotNull
    private String experience;

    @NotNull
    private String technology[];

    @NotNull
    private String salary;

    @CreatedDate
    private Date createdAt;

    // No need for @Autowired and @Transient for helperUtil here


}