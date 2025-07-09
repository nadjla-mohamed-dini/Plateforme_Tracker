package com.example.service;

import java.util.List;
import com.example.model.Student;

public interface StudentIOService {
    void exportToCsv(List<Student> students, String filePath) throws Exception;
    List<Student> importFromCsv(String filePath) throws Exception;
}
