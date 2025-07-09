package com.example.service;

import java.io.FileReader;
import java.io.Reader;
import java.util.List;

import com.example.model.Student;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CSVService {

    public List<Student> readStudentsFromCSV(String filePath) {
        try (Reader reader = new FileReader(filePath)) {
            CsvToBean<Student> csvToBean = new CsvToBeanBuilder<Student>(reader)
                    .withType(Student.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            return csvToBean.parse();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of(); // Retourne une liste vide en cas d'erreur
        }
    }
}
