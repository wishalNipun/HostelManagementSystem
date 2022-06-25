package lk.ijse.hibernate.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PendingKeyMoneyFormController {
    public TableView tblReservation;
    public JFXTextField txtStudentId;
    public JFXComboBox cmbPaymentStatus;
    public JFXButton btnUpdate;
    public JFXTextField txtReservationid;
    public JFXTextField txtDate;
    public JFXTextField txtName;
    public JFXTextField txtContactNo;
    public JFXTextField txtAddress;
    public TableColumn colReservationId;
    public TableColumn colStudentId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContactNo;
    public TableColumn colDate;
    public TableColumn colPaymentStatus;

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }
}
