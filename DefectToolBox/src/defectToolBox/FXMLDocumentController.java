/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defectToolBox;

import defectCounter.DefectSet;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.Clipboard;
import javafx.scene.input.DataFormat;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author quan
 */
public class FXMLDocumentController implements Initializable {

    private String prefix = "";

    @FXML
    TextArea textInputLeft;
    @FXML
    TextArea textInputRight;
    @FXML
    TextArea textOutputLeft;
    @FXML
    TextArea textOutputRight;
    @FXML
    TextArea textOutputCenter;

    @FXML
    Label labelInputLeft;
    @FXML
    Label labelInputRight;
    @FXML
    Label labelOutputLeft;
    @FXML
    Label labelOutputRight;
    @FXML
    Label labelOutputCenter;
    @FXML
    Button buttonCheck;
    @FXML
    RadioButton checkJIRA;
    @FXML
    RadioButton checkLegacyDTS;

    @FXML
    RadioButton checkOther;

    @FXML
    TextField textPrefix;

    @FXML
    TextField textMin;
    @FXML
    TextField textMax;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void buttonCheckClicked(Event event) {
        DefectSet leftSide = new DefectSet(prefix);
        DefectSet rightSide = new DefectSet(prefix);

        leftSide.load(textInputLeft.getText(), textPrefix.getText(), textMax.getText());
        rightSide.load(textInputRight.getText(), textPrefix.getText(), textMax.getText());

        labelInputLeft.setText(leftSide.size() + " defect(s) found.");
        labelInputRight.setText(rightSide.size() + " defect(s) found.");

        DefectSet outputLeft = leftSide.subtract(rightSide);
        DefectSet outputRight = rightSide.subtract(leftSide);
        DefectSet intersect = leftSide.intersect(rightSide);

        textOutputLeft.setStyle("-fx-text-fill: blue;");
        textOutputLeft.setText(outputLeft.listAll());
        labelOutputLeft.setText(outputLeft.size() + " missed defect(s) found.");

        textOutputRight.setStyle("-fx-text-fill: red;");
        textOutputRight.setText(outputRight.listAll());
        labelOutputRight.setText(outputRight.size() + " missed defect(s) found.");

        textOutputCenter.setStyle("-fx-text-fill: green;");
        textOutputCenter.setText(intersect.listAll());
        labelOutputCenter.setText(intersect.size() + " defect(s) covered by both side.");
    }

    @FXML
    private void changeToLegacyDefectCount() {
        checkLegacyDTS.setSelected(true);
        checkJIRA.setSelected(false);
        checkOther.setSelected(false);
        textPrefix.setDisable(false);
        textPrefix.setText("9999");
        textPrefix.setPromptText("Enter min value");
        textPrefix.setTooltip(new Tooltip("Enter min value"));
        textMax.setText("999999999");
    }

    @FXML
    private void changeToJIRADefectCount() {
        checkLegacyDTS.setSelected(false);
        checkJIRA.setSelected(true);
        checkOther.setSelected(false);
        textPrefix.setDisable(true);
        textPrefix.setText("ECOA-");
        textMax.setText("ECOA-99999");
    }

    @FXML
    private void changeToOtherDefectCount() {
        checkLegacyDTS.setSelected(false);
        checkJIRA.setSelected(false);
        checkOther.setSelected(true);
        textPrefix.setPromptText("Enter prefix");
        textPrefix.setTooltip(new Tooltip("Enter prefix"));
        textPrefix.setDisable(false);
        textPrefix.selectAll();
        textMax.setText(textPrefix.getText() + "99999");
    }

    @FXML
    private void txtPrefixOnRelease() {
        textMax.setText(textPrefix.getText() + "99999");
    }
}
