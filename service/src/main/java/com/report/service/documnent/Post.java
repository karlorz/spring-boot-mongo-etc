package com.report.service.documnent;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
//import org.springframework.context.annotation.ComponentScan;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
//import org.springframework.stereotype.Component;

import java.util.Date;

@Document(collection = "posts")
@TypeAlias("PostModal")
@Data
//@Component
//@ComponentScan(basePackages = {"com.report.service"})
public class Post {

    @Setter
    @Getter
    @NotNull
    @NotEmpty
    private String profile;
    @Setter
    @Getter
    @NotNull
    private String type;
    @Setter
    @Getter
    @NotNull
    private String[] technology;
    @Setter
    @Getter
    @NotNull
    private String salary;
    @CreatedDate
    private Date createdAt;

    public Post() {
    }

    public Post(String profile, String type, String[] technology,String salary) {
        this.profile = profile;
        this.type = type;
        this.technology = technology;
        this.salary = salary;
    }

}