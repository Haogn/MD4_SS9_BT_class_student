package rikkei.academy.dto;

import rikkei.academy.model.entity.ClassRoom;

import javax.xml.crypto.Data;
import java.sql.Date;

public class StudentDTO {
    private Integer id ;
    private String name ;
    private Date birthday;
    private String className ;
    private Integer classID ;
    public StudentDTO() {

    }

    public StudentDTO(Integer id, String name, Date birthday, String className, Integer classID) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.className = className;
        this.classID = classID;
    }

    public StudentDTO(String name, Date birthday, Integer classID) {
        this.name = name;
        this.birthday = birthday;
        this.classID = classID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getClassID() {
        return classID;
    }

    public void setClassID(Integer classID) {
        this.classID = classID;
    }
}
