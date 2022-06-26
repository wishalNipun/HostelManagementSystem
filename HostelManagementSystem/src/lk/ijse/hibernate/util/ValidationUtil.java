package lk.ijse.hibernate.util;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.TextField;

import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class ValidationUtil {
    public static Object validate(LinkedHashMap<TextField, Pattern> map, JFXButton btn) {
        for (TextField key : map.keySet()) {
            Pattern pattern = map.get(key);
            if (!pattern.matcher(key.getText()).matches()){
                //if the input is not matching
                addError(key,btn);
                return key;
            }
            removeError(key,btn);
        }
        return true;
    }

    private static void removeError(TextField txtField,JFXButton btn) {
        txtField.setStyle("-fx-text-fill: green");
        btn.setDisable(false);
    }

    private static void addError(TextField txtField,JFXButton btn) {
        if (txtField.getText().length() > 0) {
            txtField.setStyle("-fx-text-fill: red");
        }
        btn.setDisable(true);
    }

}
