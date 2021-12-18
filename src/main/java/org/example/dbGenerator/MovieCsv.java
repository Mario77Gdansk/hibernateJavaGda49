package org.example.dbgenerator;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class MovieCsv {
//    movie,year,type,time
    @CsvBindByName(column = "movie")
    private String movie;
    @CsvBindByName(column = "year")
    private Integer year;
    @CsvBindByName(column = "type")
    private String type;
    @CsvBindByName(column = "time")
    private Integer time;
}
