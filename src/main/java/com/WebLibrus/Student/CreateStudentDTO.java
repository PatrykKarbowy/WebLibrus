package com.WebLibrus.Student;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CreateStudentDTO {

    private String firstName;
    private String lastName;
    private int age;

    public static CreateStudentDTO from(Student student){
        CreateStudentDTO dto = new CreateStudentDTO();
        dto.setAge(student.getStudentAge());
        dto.setFirstName(student.getStudentFirstName());
        dto.setLastName(student.getStudentLastName());
        return dto;
    }
}
