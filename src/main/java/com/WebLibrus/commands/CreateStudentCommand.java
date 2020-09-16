package com.WebLibrus.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class CreateStudentCommand {

    private String studentFirstName;
    private String studentLastName;
    private int studentAge;
}
