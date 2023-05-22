package com.example.hidinginplaintextjavafx;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * @author Tyrell R
 * @version 1.0
 *
 * A javaFX controller class which provides GUI functionality.
 */
public class MainFormController {

    final static String ENCODE_MODE = "Encode";
    final static String DECODE_MODE = "Decode";
    @FXML
    private Text inputLabel;
    @FXML
    private Text outputLabel;
    @FXML
    private Button actionButton;
    @FXML
    private Button copyButton;
    @FXML
    private ChoiceBox modeChoiceBox;
    @FXML
    private ChoiceBox grammarChoiceBox;
    @FXML
    private TextArea inputTextArea;
    @FXML
    private TextArea outputTextArea;
    @FXML
    private ImageView iconImageView;

    private boolean isCurrentGrammarValid = false;

    private ObservableList<String> modes = FXCollections.observableArrayList(ENCODE_MODE,DECODE_MODE);

    /**
     * Initalise the GUI.
     */
    @FXML
    private void initialize() {
        initialiseModes();
        refreshGrammars();
        initialiseImages();
        grammarChoiceBoxListener();

    }

    private void initialiseModes() {
        modeChoiceBox.setItems(modes);
        modeChoiceBox.setValue(modes.get(0));

        modeChoiceBoxListener();
    }

    private void initialiseImages() {
        Image icon = new Image("file:icon.jpg");
        iconImageView.setImage(icon);
    }

    /**
     * Gets the selected mode in the mode choice box.
     * @return ENCODE_MODE OR DECODE_MODE constant.
     */
    private String getMode() {
        return modeChoiceBox.getValue().toString();
    }

    /**
     * Mode choice box listener. It switches the mode of the application to the
     * mode selected in the mode choice box.
     */
    private void modeChoiceBoxListener() {
        modeChoiceBox.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                    switchModes(modes.get(new_val.intValue()));
                });
    }

    /**
     * Grammar choice box listener. It checks if the newly selected grammar is valid.
     */
    private void grammarChoiceBoxListener() {
        grammarChoiceBox.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                    if(!(new_val.intValue() == -1)){
                        try {
                            checkGrammarValid(grammarChoiceBox.getItems().get(new_val.intValue()).toString());
                        } catch (FileNotFoundException e) {

                        }
                    }
                });
    }

    /**
     * Checks if a grammar is valid. if not valid it outputs the reason why to the
     * output text area.
     * @param grammarFileName
     * @throws FileNotFoundException
     */
    private void checkGrammarValid(String grammarFileName) throws FileNotFoundException {
        Grammar g = FileOperations.parseGrammarTextFile(grammarFileName);
        String s = g.checkGrammar();
        Boolean isValid = (s == "");
        if(!isValid) {
            String errorMessage = "Error(s): \"" + grammarFileName + "\" " + s;
            outputTextArea.setText(errorMessage);
            isCurrentGrammarValid = false;
        } else {
            isCurrentGrammarValid = true;
        }
    }

    /**
     * Get the name of the currently selected grammar in the grammar choicebox.
     * @return grammar name.
     */
    private String getGrammar() {
        if(grammarChoiceBox.getValue() == null) {
            return "";
        } else {
            return grammarChoiceBox.getValue().toString();
        }
    }

    /**
     * Switches the gui mode.
     * @param mode
     */
    @FXML private void switchModes(String mode) {
        if(mode == ENCODE_MODE) {
            inputLabel.setText("Input (secret message)");
            outputLabel.setText("Output (generated text)");
            inputTextArea.setPromptText("Enter a short message to encode");
            actionButton.setText("Encode");
        } else if(mode == DECODE_MODE) {
            inputLabel.setText("Input (generated text)");
            outputLabel.setText("Output (secret message)");
            inputTextArea.setPromptText("Enter generated text to decode");
            actionButton.setText("Decode");
        }
    }

    /**
     * Loads all the grammars from the grammar folder into the grammar choicebox.
     */
    @FXML
    private void refreshGrammars() {
        File[] grammars = FileOperations.getGrammarFiles();
        ObservableList<String> files = FXCollections.observableArrayList();

        for(int i = 0; i < grammars.length; i++) {
            files.add(grammars[i].getName());
        }
        grammarChoiceBox.setItems(files);
    }


    /**
     * The encode or decode button.
     */
    @FXML
    private void actionButtonClick() {
        String currentMode = getMode();
        String input = inputTextArea.getText();
        boolean isASCII = StringOperations.isOnlyASCII(input);

        if(getGrammar() == "") {
            outputTextArea.setText("ERROR: Please select a grammar");
        } else if (!isCurrentGrammarValid) {
            outputTextArea.setText("The selected grammar is not valid");
        } else if(currentMode == ENCODE_MODE) {
            if(!isASCII) {
                outputTextArea.setText("The input text contains non ASCII characters. Please only use ASCII characters.");
            } else {
                try{
                    encode();
                } catch (FileNotFoundException e) {
                    System.out.println("error");
                }
            }

        } else if(currentMode == DECODE_MODE) {
            try {
                decode();
            } catch (FileNotFoundException e) {
                System.out.println("error");
            }

        }

    }

    /**
     * Encodes the message on the left and outputs the text on the right.
     * @throws FileNotFoundException
     */
    private void encode() throws FileNotFoundException {
        String secretMessage = inputTextArea.getText();
        String grammarFilename = getGrammar();

        Grammar g = FileOperations.parseGrammarTextFile(grammarFilename);
        System.out.println(g);
        Encoder e = new Encoder();
        e.run("START",secretMessage,g);
        outputTextArea.setText(e.generatedText);
    }

    /**
     * Decodes the text on the left and outputs the message on the right.
     * @throws FileNotFoundException
     */
    private void decode() throws FileNotFoundException {
        String text = inputTextArea.getText();
        String grammarFilename = getGrammar();
        Grammar g = FileOperations.parseGrammarTextFile(grammarFilename);
        Decoder d = new Decoder();
        d.decode(text,g);
        text = StringOperations.convertBinaryStringToString(d.bitsGenerated);
        boolean isTextEmpty = text.length() == 0;
        boolean isTextError = "-1(ERROR)" == text;
        if(isTextEmpty || isTextError) {
            outputTextArea.setText("Error: Inputted generated text is not valid. Try \"Decode (Ignore Whitespace Mode).\"");
        } else {
            outputTextArea.setText(text);
        }

    }

    /**
     * Copies whatever text is in the output text area.
     */
    @FXML private void copyOutputText() {
        StringSelection selection = new StringSelection(outputTextArea.getText());
        Clipboard clipBoard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipBoard.setContents(selection, null);
    }


}