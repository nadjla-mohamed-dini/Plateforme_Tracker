package com.example.service;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.List;

import com.example.model.Student;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

public class StudentIOServiceImpl implements StudentIOService {

    @Override
    public void exportToCsv(List<Student> students, String filePath) throws Exception {
        try (Writer writer = new FileWriter(filePath)) {
            StatefulBeanToCsv<Student> beanToCsv = new StatefulBeanToCsvBuilder<Student>(writer)
                    .withApplyQuotesToAll(false)
                    .build();
            beanToCsv.write(students);
        }
    }

    @Override
    public List<Student> importFromCsv(String filePath) throws Exception {
        try (Reader reader = new FileReader(filePath)) {
            CsvToBean<Student> csvToBean = new CsvToBeanBuilder<Student>(reader)
                    .withType(Student.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            return csvToBean.parse();
        }
    }
}
