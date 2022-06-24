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
import lk.ijse.hibernate.bo.custom.RoomBO;
import lk.ijse.hibernate.bo.custom.impl.RoomBOImpl;
import lk.ijse.hibernate.dto.RoomDTO;
import lk.ijse.hibernate.view.tm.RoomTM;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class RoomManagementFormController {
    public TableView <RoomTM>tblRoom;
    public JFXTextField txtQty;
    public JFXComboBox<String> cmbType;
    public JFXButton btnDelete;
    public JFXButton btnNew;
    public TableColumn colRoomId;
    public TableColumn colType;
    public TableColumn colQty;
    public JFXTextField txtRoomTypeId;
    public JFXTextField txtKeyMoney;
    public TableColumn colKeyMoney;
    public JFXButton btnSave;
    private final RoomBO roomBO = new RoomBOImpl();

    public void initialize(){

        textClearAndBtnDisable();
        cmbType.getItems().addAll("Non-AC","Non-AC / Food","AC","AC / Food");
        loadAllRooms();

        colRoomId.setCellValueFactory(new PropertyValueFactory<>("room_type_id"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("key_money"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));

        tblRoom.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDelete.setDisable(newValue == null);
            btnSave.setText(newValue != null ? "Update" : "Save");
            btnSave.setDisable(newValue == null);

            if (newValue != null) {
                txtRoomTypeId.setText(newValue.getRoom_type_id());
                cmbType.setValue(newValue.getType());
                txtKeyMoney.setText(String.valueOf(newValue.getKey_money()));
                txtQty.setText(String.valueOf(newValue.getQty()));

                txtRoomTypeId.setDisable(false);
                txtKeyMoney.setDisable(false);
                txtQty.setDisable(false);
                cmbType.setDisable(false);

            }
        });
    }

    private void loadAllRooms() {
        try {
            List<RoomDTO> allRooms = roomBO.getAllRooms();
            for (RoomDTO roomDTO:allRooms) {
                tblRoom.getItems().add(
                       new RoomTM(roomDTO.getRoom_type_id(),roomDTO.getType(),roomDTO.getKey_money(),roomDTO.getQty())
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void textClearAndBtnDisable() {
        txtRoomTypeId.clear();
        txtKeyMoney.clear();
        txtQty.clear();
        cmbType.getSelectionModel().clearSelection();

        txtRoomTypeId.setDisable(true);
        txtKeyMoney.setDisable(true);
        txtQty.setDisable(true);
        cmbType.setDisable(true);

        btnSave.setDisable(true);
        btnDelete.setDisable(true);

    }

    public void btnNewOnAction(ActionEvent actionEvent) {
        txtRoomTypeId.setDisable(false);
        txtKeyMoney.setDisable(false);
        txtQty.setDisable(false);
        cmbType.setDisable(false);

        txtRoomTypeId.clear();
        txtKeyMoney.clear();
        txtQty.clear();
        cmbType.getSelectionModel().clearSelection();

        txtRoomTypeId.requestFocus();
        btnSave.setDisable(false);
        btnSave.setText("Save");
        tblRoom.getSelectionModel().clearSelection();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Do you Want Delete", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> buttonType =alert.showAndWait();
        if (buttonType.get().equals(ButtonType.YES)){
            try {
                String roomId = tblRoom.getSelectionModel().getSelectedItem().getRoom_type_id();
                boolean b = roomBO.deleteRoom(roomId);
                if (b){
                    new Alert(Alert.AlertType.CONFIRMATION,"Deleted").show();
                }
                tblRoom.getItems().remove(tblRoom.getSelectionModel().getSelectedItem());
                tblRoom.getSelectionModel().clearSelection();
                textClearAndBtnDisable();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {


        String roomID = txtRoomTypeId.getText();
        String type = cmbType.getValue();
        double keyMoney = Double.parseDouble(txtKeyMoney.getText());
        int qty = Integer.parseInt(txtQty.getText());


        try {
            if (btnSave.getText().equalsIgnoreCase("save")) {
                boolean save= roomBO.saveRoom(new RoomDTO(roomID,type,keyMoney,qty));

                tblRoom.getItems().add(new RoomTM(roomID,type,keyMoney,qty));
                if (!save){
                    new Alert(Alert.AlertType.ERROR,"Failed to Saved the User").show();
                }
                textClearAndBtnDisable();

            }else {
                boolean updated= roomBO.updateRoom(new RoomDTO(roomID,type,keyMoney,qty));

                if (updated){
                    new Alert(Alert.AlertType.CONFIRMATION,"Updated").show();
                }

                RoomTM selectedItem = tblRoom.getSelectionModel().getSelectedItem();
                selectedItem.setRoom_type_id(roomID);
                selectedItem.setType(type);
                selectedItem.setKey_money(keyMoney);
                selectedItem.setQty(qty);

                tblRoom.refresh();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
