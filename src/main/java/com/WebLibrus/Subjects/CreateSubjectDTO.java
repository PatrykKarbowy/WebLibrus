package com.WebLibrus.Subjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CreateSubjectDTO {
    private String name;

    public static CreateSubjectDTO from (Subject subject){
        CreateSubjectDTO dto = new CreateSubjectDTO();
        dto.setName(subject.getSubjectName());
        return dto;
    }
}
