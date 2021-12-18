package org.example.dbgenerator;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class AuthorCsv {
    @CsvBindByName(column = "first_name")
    private String firstName;
    @CsvBindByName(column = "last_name")
    private String lastName;
    @CsvBindByName(column = "age")
    private Integer age;
    @CsvBindByName(column = "nationality")
    private String nationality;
}
