package lk.ijse.hibernate.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hibernate.bo.custom.LogBO;
import lk.ijse.hibernate.bo.custom.impl.LogBOImpl;
import lk.ijse.hibernate.dto.UserDTO;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class LoginFormController {
    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;
    public Label lblWrong;
    public AnchorPane loginFormContext;
    private final LogBO logBO = new LogBOImpl();
    public void btnLoginOnAction(ActionEvent actionEvent) throws Exception {

        if(txtUserName.getText().equals("") && txtPassword.getText().equals("")){
            lblWrong.setText("Please enter your UserName and Password");

        }else if (txtUserName.getText()!=null&& txtPassword.getText()!=null){
           // List<UserDTO> userDetails = logBO.getUserDetails(txtUserName.getText(), txtPassword.getText());


            setUI("DashBoardForm");

        }else {
            new Alert(Alert.AlertType.WARNING,"Try Again !").show();
        }
    }

    private void setUI(String URI) throws IOException {

        Stage stage1= (Stage)loginFormContext.getScene().getWindow();
        stage1.close();
        URL resource = getClass().getResource("/lk/ijse/hibernate/view/"+URI+".fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage2= new Stage();
        stage2.setTitle("Hostel Management System");
        Image image = new Image("/lk/ijse/hibernate/assets/images/SoftwareIcon.png");
        stage2.getIcons().add(image);
        stage2.setScene(scene);
        stage2.centerOnScreen();
        stage2.show();
    }
}
