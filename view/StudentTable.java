package com.example.view;

import com.example.dao.StudentDAO;
import com.example.model.StudentFX;
import com.example.utils.TableFilter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class StudentTable {

    private static final ObservableList<StudentFX> studentList = FXCollections.observableArrayList();

    private static TableView<StudentFX> table;
    
    private static final int PAGE_SIZE = 5;
    private static int currentPage = 1;

    private static final StudentDAO studentDAO = new StudentDAO();

    public static VBox create() {

        table = new TableView<>(studentList);

        TableColumn<StudentFX, Number> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        idCol.setMinWidth(50);

        TableColumn<StudentFX, String> firstNameCol = new TableColumn<>("First Name");
        firstNameCol.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        firstNameCol.setMinWidth(120);

        TableColumn<StudentFX, String> lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        lastNameCol.setMinWidth(120);

        TableColumn<StudentFX, Number> ageCol = new TableColumn<>("Age");
        ageCol.setCellValueFactory(cellData -> cellData.getValue().ageProperty());
        ageCol.setMinWidth(80);

        TableColumn<StudentFX, Number> gradeCol = new TableColumn<>("Grade");
        gradeCol.setCellValueFactory(cellData -> cellData.getValue().gradeProperty());
        gradeCol.setMinWidth(80);

        table.getColumns().addAll(idCol, firstNameCol, lastNameCol, ageCol, gradeCol);

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

        TextField searchField = TableFilter.createSearchField(table, studentList);
        //ajout boutton pagination 
        Button prevButton = new Button("Past");
        Button nextButton = new Button("Next");

        prevButton.setOnAction(e -> {
            if (currentPage > 1) {
                currentPage--;
                loadStudentsFromDB();
            }
        });
         nextButton.setOnAction(e -> {
            currentPage++;
            loadStudentsFromDB();
        });
        HBox paginationControls = new HBox(10, prevButton, nextButton); //fin

        VBox box = new VBox(10, new Label("List of students:"), searchField, table);
        box.setStyle("-fx-padding: 20;");


        box.getChildren().add(paginationControls);
        loadStudentsFromDB();

        return box;
    }

    // Méthode pour charger la liste depuis la base de données
    public static void loadStudentsFromDB() {
    studentList.clear();

    // Récupérer les étudiants (List<Student>)
    var students = studentDAO.getStudents(currentPage, PAGE_SIZE);

    // Convertir chaque Student en StudentFX
    for (var student : students) {
        studentList.add(new StudentFX(
            student.getId(),
            student.getFirstName(),
            student.getLastName(),
            student.getAge(),
            student.getGrade()
            ));
        }
    }



    public static ObservableList<StudentFX> getStudentList() {
        return studentList;
    }

    public static StudentFX getSelectedStudent() {
        if (table != null) {
            return table.getSelectionModel().getSelectedItem();
        }
        return null;
    }
}
 