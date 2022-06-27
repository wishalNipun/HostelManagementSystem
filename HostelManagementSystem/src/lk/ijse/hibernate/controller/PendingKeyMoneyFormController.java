package lk.ijse.hibernate.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.hibernate.bo.BOFactory;
import lk.ijse.hibernate.bo.custom.PendingKeyMoneyBO;
import lk.ijse.hibernate.bo.custom.impl.PendingKeyMoneyBOImpl;
import lk.ijse.hibernate.dto.CustomDTO;

import java.util.List;

public class PendingKeyMoneyFormController {
    public TableView <CustomDTO>tblReservation;
    public JFXTextField txtStudentId;
    public JFXComboBox cmbPaymentStatus;
    public JFXButton btnUpdate;
    public JFXTextField txtDate;
    public JFXTextField txtName;
    public TableColumn colReservationId;
    public TableColumn colStudentId;
    public TableColumn colName;
    public TableColumn colDate;
    public TableColumn colPaymentStatus;
    public JFXTextField txtReservationId;
    final private PendingKeyMoneyBO pendingKeyMoneyBO = (PendingKeyMoneyBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.PENDINGKEYMONEY);
    public TableColumn colType;
    public TableColumn colRoomTypeId;
    public TableColumn colKeyMoney;
    public JFXTextField txtType;
    public JFXTextField txtRoomTypeId;
    public JFXTextField txtKeyMoney;

    public void initialize(){
        colReservationId.setCellValueFactory(new PropertyValueFactory<>("res_id"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colRoomTypeId.setCellValueFactory(new PropertyValueFactory<>("room_type_id"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("key_money"));
        colPaymentStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        loadAllData();

        txtReservationId.setEditable(false);
        txtName.setEditable(false);
        txtRoomTypeId.setEditable(false);
        txtStudentId.setEditable(false);
        txtType.setEditable(false);
        txtKeyMoney.setEditable(false);
        txtDate.setEditable(false);

        txtReservationId.setDisable(true);
        txtName.setDisable(true);
        txtRoomTypeId.setDisable(true);
        txtStudentId.setDisable(true);
        txtType.setDisable(true);
        txtKeyMoney.setDisable(true);
        txtDate.setDisable(true);
        btnUpdate.setDisable(true);

        tblReservation.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue!= null){
                txtReservationId.setDisable(false);
                txtName.setDisable(false);
                txtRoomTypeId.setDisable(false);
                txtStudentId.setDisable(false);
                txtType.setDisable(false);
                txtKeyMoney.setDisable(false);
                txtDate.setDisable(false);
                btnUpdate.setDisable(false);

                txtReservationId.setText(newValue.getRes_id());
                txtName.setText(newValue.getName());
                txtRoomTypeId.setText(newValue.getRoom_type_id());
                txtStudentId.setText(newValue.getStudent_id());
                txtType.setText(newValue.getType());
                txtKeyMoney.setText(String.valueOf(newValue.getKey_money()));
                cmbPaymentStatus.setValue(newValue.getStatus());
                txtDate.setText(String.valueOf(newValue.getDate()));
            }else {
                txtReservationId.clear();
                txtName.clear();
                txtRoomTypeId.clear();
                txtStudentId.clear();
                txtType.clear();
                txtKeyMoney.clear();
                cmbPaymentStatus.getSelectionModel().clearSelection();
                txtDate.clear();
            }
        });

        cmbPaymentStatus.getItems().addAll("Paid","Paid Later");
    }

    private void loadAllData() {
        try {
            List<CustomDTO> list = pendingKeyMoneyBO.getAllPendingKeyMoneyReservationsUsingReservationStatus();

            for (CustomDTO dto:list
                 ) {
                tblReservation.getItems().add(
                      new CustomDTO(dto.getRes_id(),dto.getStudent_id(),dto.getName(),dto.getRoom_type_id(),dto.getType(),dto.getKey_money(),dto.getStatus(),dto.getDate())
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {

        try {
            boolean b = pendingKeyMoneyBO.updateReservationUsingId(txtReservationId.getText(), String.valueOf(cmbPaymentStatus.getValue()));
            if (b){

                new Alert(Alert.AlertType.CONFIRMATION,"Updated").show();
            }
            tblReservation.getItems().remove(tblReservation.getSelectionModel().getSelectedItem());
            tblReservation.getSelectionModel().clearSelection();
            txtReservationId.clear();
            txtName.clear();
            txtRoomTypeId.clear();
            txtStudentId.clear();
            txtType.clear();
            txtKeyMoney.clear();
            cmbPaymentStatus.getSelectionModel().clearSelection();
            txtDate.clear();

            txtReservationId.setDisable(true);
            txtName.setDisable(true);
            txtRoomTypeId.setDisable(true);
            txtStudentId.setDisable(true);
            txtType.setDisable(true);
            txtKeyMoney.setDisable(true);
            txtDate.setDisable(true);
            btnUpdate.setDisable(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
