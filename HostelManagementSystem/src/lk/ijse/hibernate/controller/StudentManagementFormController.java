package lk.ijse.hibernate.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.hibernate.bo.custom.StudentBO;
import lk.ijse.hibernate.bo.custom.impl.StudentBOImpl;
import lk.ijse.hibernate.dto.StudentDTO;
import lk.ijse.hibernate.dto.UserDTO;
import lk.ijse.hibernate.view.tm.StudentTM;
import lk.ijse.hibernate.view.tm.UserTM;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public class StudentManagementFormController {
    public TableView <StudentTM>tblStudent;
    public JFXTextField txtStudentId;
    public TableColumn colStudentId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colDOB;
    public TableColumn colGender;
    public JFXTextField txtAddress;
    public JFXButton btnSave;
    public JFXTextField txtContactNo;
    public JFXButton btnNew;
    public JFXButton btnDelete;
    public JFXTextField txtName;
    public JFXTextField txtDOB;
    private final  StudentBO studentBO = new StudentBOImpl();
    public JFXComboBox <String>cmbGender;
    public TableColumn colContactNo;

    public void initialize(){

        textClearAndBtnDisable();

        cmbGender.getItems().addAll("Male","Female","Other");

        loadAllStudents();

        colStudentId.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContactNo.setCellValueFactory(new PropertyValueFactory<>("contact_no"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));

        tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDelete.setDisable(newValue == null);
            btnSave.setText(newValue != null ? "Update" : "Save");
            btnSave.setDisable(newValue == null);

            if (newValue != null) {
                txtStudentId.setText(newValue.getStudent_id());
                txtName.setText(newValue.getName());
                txtContactNo.setText(newValue.getContact_no());
                txtAddress.setText(newValue.getAddress());
                txtDOB.setText(String.valueOf(newValue.getDob()));
                cmbGender.setValue(newValue.getGender());

                txtStudentId.setDisable(false);
                txtName.setDisable(false);
                txtAddress.setDisable(false);
                txtContactNo.setDisable(false);
                txtDOB.setDisable(false);
                cmbGender.setDisable(false);

            }
        });

    }

    private void loadAllStudents() {
        try {
            List<StudentDTO> allStudents = studentBO.getAllStudents();
            for (StudentDTO dto:allStudents
            ) {
                tblStudent.getItems().add(
                       new StudentTM(dto.getStudent_id(), dto.getName(), dto.getAddress(), dto.getContact_no(),dto.getDob(), dto.getGender())
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void textClearAndBtnDisable() {
        txtStudentId.clear();
        txtName.clear();
        txtAddress.clear();
        txtContactNo.clear();
        txtDOB.clear();
        cmbGender.getSelectionModel().clearSelection();

        txtStudentId.setDisable(true);
        txtName.setDisable(true);
        txtAddress.setDisable(true);
        txtContactNo.setDisable(true);
        txtDOB.setDisable(true);
        cmbGender.setDisable(true);

        txtStudentId.setEditable(false);
        btnSave.setDisable(true);
        btnDelete.setDisable(true);
    }

    private void generateNewId() {
        try {
            String lastStudentId = studentBO.generateStudentId();
            int newId = Integer.parseInt(lastStudentId.substring(1, 4))+1;
            if (newId < 10) {
                txtStudentId.setText("S00"+newId);
            }else if (newId < 100) {
                txtStudentId.setText("S0"+newId);
            }else {
                txtStudentId.setText("S"+newId);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String studentId = txtStudentId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contactNo = txtContactNo.getText();
        LocalDate dob = LocalDate.parse(txtDOB.getText());
        String gender = cmbGender.getValue();

        System.out.println(txtDOB.getText());

        try {
            if (btnSave.getText().equalsIgnoreCase("save")) {


                boolean save=studentBO.saveStudent(new StudentDTO(studentId,name,address,contactNo,dob ,gender));
                tblStudent.getItems().add(new StudentTM(studentId,name,address,contactNo,dob,gender));
                if (!save){
                    new Alert(Alert.AlertType.ERROR,"Failed to Saved the User").show();
                }
                textClearAndBtnDisable();

            }else {
                boolean updated=studentBO.updateStudent(new StudentDTO(studentId,name,address,contactNo,dob ,gender));

                if (updated){
                    new Alert(Alert.AlertType.CONFIRMATION,"Updated").show();
                }

                StudentTM selectedItem = tblStudent.getSelectionModel().getSelectedItem();
                selectedItem.setName(name);
                selectedItem.setAddress(address);
                selectedItem.setContact_no(contactNo);
                selectedItem.setDob(dob);
                selectedItem.setGender(gender);
                tblStudent.refresh();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Do you Want Delete", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> buttonType =alert.showAndWait();
        if (buttonType.get().equals(ButtonType.YES)){
            try {
                String studentId = tblStudent.getSelectionModel().getSelectedItem().getStudent_id();
                boolean b = studentBO.deleteStudent(studentId);
                if (b){
                    new Alert(Alert.AlertType.CONFIRMATION,"Deleted").show();
                }
                tblStudent.getItems().remove(tblStudent.getSelectionModel().getSelectedItem());
                tblStudent.getSelectionModel().clearSelection();
                textClearAndBtnDisable();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void btnNewOnAction(ActionEvent actionEvent) {
        txtStudentId.setDisable(false);
        txtName.setDisable(false);
        txtAddress.setDisable(false);
        txtContactNo.setDisable(false);
        txtDOB.setDisable(false);
        cmbGender.setDisable(false);

        txtStudentId.clear();
        generateNewId();
        txtName.clear();
        txtAddress.clear();
        txtContactNo.clear();
        txtDOB.clear();
        cmbGender.getSelectionModel().clearSelection();

        txtName.requestFocus();
        btnSave.setDisable(false);
        btnSave.setText("Save");
        tblStudent.getSelectionModel().clearSelection();
    }
}
