package com.report.service.documnent;

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
//@Component
//@ComponentScan(basePackages = {"com.report.service"})
public class Post {

    @NotNull
    @NotEmpty
    private String profile;
    @NotNull
    private String type;
    @NotNull
    private String technology[];
    @NotNull
    private String salary;
    @CreatedDate
    private Date createdAt;
    public Post() {
    }

    public Post(String profile, String type, String technology[],String salary) {
        this.profile = profile;
        this.type = type;
        this.technology = technology;
        this.salary = salary;
    }
    // No need for @Autowired and @Transient for helperUtil here
    public String getProfile() {
        return profile;
    }
    public void setProfile(String profile) {
        this.profile = profile;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String[] getTechnology() {
        return technology;
    }
    public void setTechnology(String technology[]) {
        this.technology = technology;
    }
    public String getSalary() {
        return salary;
    }
    public void setSalary(String salary) {
        this.salary = salary;
    }

}