/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defectToolBox;

import defectCounter.DefectSet;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

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
	TextField textPrefix;

	@FXML
	TextField textMin;
	@FXML
	TextField textMax;

	static Properties prop;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		textPrefix.setTooltip(new Tooltip("Enter prefix, example Prefix-"));
		textPrefix.setDisable(false);
		textPrefix.selectAll();		
		textPrefix.setText(readProperties("prefix"));
		textMax.setText(textPrefix.getText() + "9999999");
	}

	@FXML
	private void buttonCheckClicked(Event event) {
		DefectSet leftSide = new DefectSet(prefix);
		DefectSet rightSide = new DefectSet(prefix);

		leftSide.load(textInputLeft.getText(), textPrefix.getText(), textMax.getText());
		rightSide.load(textInputRight.getText(), textPrefix.getText(), textMax.getText());

		labelInputLeft.setText(leftSide.size() + " ID(s) found.");
		labelInputRight.setText(rightSide.size() + " ID(s) found.");

		DefectSet outputLeft = leftSide.subtract(rightSide);
		DefectSet outputRight = rightSide.subtract(leftSide);
		DefectSet intersect = leftSide.intersect(rightSide);

		textOutputLeft.setStyle("-fx-text-fill: blue;");
		textOutputLeft.setText(outputLeft.listAll());
		labelOutputLeft.setText(outputLeft.size() + " ID(s) not in Left");

		textOutputRight.setStyle("-fx-text-fill: red;");
		textOutputRight.setText(outputRight.listAll());
		labelOutputRight.setText(outputRight.size() + " ID(s) not in Right");

		textOutputCenter.setStyle("-fx-text-fill: green;");
		textOutputCenter.setText(intersect.listAll());
		labelOutputCenter.setText(intersect.size() + " ID(s) in both side");
	}

	@FXML
	private void textPrefixOnRelease(Event event) {
		// textMax.setText(textPrefix.getText() + String.valueOf(Integer.MAX_VALUE).toString());
		textMax.setText(textPrefix.getText() + "9999999");
		writeProperties("prefix",textPrefix.getText());
	}

	public static void writeProperties(String key, String value) {

		prop = new Properties();
		OutputStream output = null;

		try {

			output = new FileOutputStream("config.properties");
			//output = new FileOutputStream(FXMLDocumentController.class.getResource("config.properties").getFile());

			// set the properties value
			prop.setProperty(key, value);
			//prop.setProperty("prefix", "A prefix");

			// save properties to project root folder
			prop.store(output, null);

		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

	 
	public static String readProperties(String key) {
		String value ="";
		prop = new Properties();
		InputStream input = null;
		try {

			//input = FXMLDocumentController.class.getClassLoader().getResourceAsStream("config.properties");
			input = new FileInputStream("config.properties");
			// load a properties file
			prop.load(input);
			value = prop.getProperty(key).toString();

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return value;
	}

}
