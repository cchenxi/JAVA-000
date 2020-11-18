package io.github.cchenxi.w5.spring.boot.obj;

import java.util.List;

import lombok.Data;

@Data
public class Klass { 
    
    List<Student> students;
    
    public void dong(){
        System.out.println(this.getStudents());
    }
    
}
