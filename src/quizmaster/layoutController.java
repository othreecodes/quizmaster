/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizmaster;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import questionHandler.NewQiuz;
import questionHandler.Questions;

/**
 *
 * @author UCHENNA
 */
public class layoutController implements Initializable {

    ObservableList<Questions> questions = FXCollections.observableArrayList();

    @FXML
    private JFXTextArea questionTextArea;

    @FXML
    private ToggleGroup answerrGroup;

    @FXML
    private JFXTextField ATextField;

    @FXML
    private JFXTextField CtextField;

    @FXML
    private JFXTextField BTextField;

    @FXML
    private JFXTextField DTextField;

    @FXML
    private Label questionLabel;
    @FXML
    private Button createbutton;

    Stage Questionstage;
    Stage mainWindow;

    @FXML
    public void createQuiz(ActionEvent e) {

        try {

            Button source = (Button) e.getSource();
            mainWindow = (Stage) source.getScene().getWindow();

            Questionstage.setAlwaysOnTop(true);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/questionHandler/createquiz.fxml"));

            loader.setController(this);
            Parent root = (Parent) loader.load();

            Scene scene = new Scene(root);
            Questionstage.setScene(scene);
            Questionstage.centerOnScreen();

            Questionstage.show();

            // mainWindow.toBack();
        } catch (IOException ex) {
            Logger.getLogger(layoutController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //allQuestions = FXCollections.observableArrayList();
        Questionstage = new Stage();
        Questionstage.setOnCloseRequest(d -> {
            Questionstage.close();

        });

        Questionstage.getIcons().add(new Image(getClass().getResource("/quizmaster/icon.png").toString()));
        currentQuestion = 0;
        LastNumber =0;
        
    }
    @FXML
    private JFXButton saveButton;

    @FXML
    public void nextQuestion(ActionEvent e) {
        int questionNumber = 0;
        saveButton.setText("NEXT");
        Questions quest = new Questions();
        quest.setQuestion(questionTextArea.getText());
        quest.setAText(ATextField.getText());
        quest.setBText(BTextField.getText());
        quest.setCText(CtextField.getText());
        quest.setDText(DTextField.getText());
        quest.setQuestionNumber(questionNumber);
        quest.setAnswer(((RadioButton) answerrGroup.getSelectedToggle()).getId());
        System.out.println(quest.getAnswer());

        questionLabel.setText(String.valueOf((Integer.parseInt(questionLabel.getText()) + 1)));
        questions.add(quest);

        saveButton.setText("SAVE");
        questionTextArea.clear();
        ATextField.clear();
        BTextField.clear();
        CtextField.clear();
        DTextField.clear();
        answerrGroup.selectToggle(null);

        questionNumber++;

        try {
            if (answerrGroup.getSelectedToggle().isSelected()) {

            } else {

            }

        } catch (Exception ex) {
        }

        System.out.println("dhd");
    }

    public void closestage(Stage stage) {

        stage.close();
    }

    String quizType;

    @FXML
    public void finish(ActionEvent e) {

        for (Questions qt : questions) {
            System.out.println(qt.getQuestion());
            System.out.println(qt.getAText());
            System.out.println(qt.getBText());
            System.out.println(qt.getCText());
            System.out.println(qt.getDText());
            System.out.println(qt.getAnswer());

        }

        Questionstage.setAlwaysOnTop(false);
        Questionstage = (Stage) DTextField.getScene().getWindow();
        NewQiuz wi = new NewQiuz(questions, Questionstage);

        Questionstage.toBack();
        Questionstage.close();

    }
    ArrayList<Questions> allQuestions;
    int currentQuestion = 0;

    @FXML
    void startQuiz() {
        quizType = "quick";
        try {
            FileChooser choose = new FileChooser();
            choose.getExtensionFilters().add(new FileChooser.ExtensionFilter("QUIZ-MASTER FILE (.quizfile)", "*.quizfile"));
            Questionstage = new Stage();
            // NewQiuz wi = new NewQiuz();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/questionHandler/questionhandler.fxml"));
            loader.setController(this);
            Parent root = (Parent) loader.load();
            Questionstage.setScene(new Scene(root));
            Questionstage.setMaximized(true);
            Questionstage.show();

            allQuestions = NewQiuzz(Questionstage, choose.showOpenDialog(Questionstage));
//           NewQiuz wi = new NewQiuz(questions, Questionstage);

        } catch (IOException ex) {
            Logger.getLogger(layoutController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // createQuestion(0);
//           
    }

    Questions name;

    String[] createQuestion(int number) {
        for (int i = 0; i < allQuestions.size(); i++) {
            System.out.println(allQuestions.get(i).getQuestion());
            name = allQuestions.get(number);
            System.out.println(allQuestions.get(number).getQuestion());
            System.out.println(name.getBText());
        }
        return null;

    }

    int init = 0;
    int LastNumber = 0;

    @FXML
    public void start(ActionEvent e) {
        if (quizType.equals("quick")) {
            try {
                Button ed = (Button) e.getSource();
                // startThread();
                if (ed.isVisible()) {
                    ed.setVisible(false);
                }
                if (ed.getText().equals("FINISH")) {
                    Questionstage.close();
                }
            } catch (Exception es) {
            }
//        if(init == 0){
//               init++;
           
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        if (questionsw.get(currentQuestion).getQuestionNumber() == LastNumber) {
                            submitButton.setText("FINISH");
                        }
                    } catch (IndexOutOfBoundsException e) {
                        Questionstage.close();
                    }
                    Alabel.setText(questionsw.get(currentQuestion).getAText());
                    Blabel.setText(questionsw.get(currentQuestion).getBText());
                    Clabel.setText(questionsw.get(currentQuestion).getCText());
                    Dlabel.setText(questionsw.get(currentQuestion).getDText());
                    questionview.setText(questionsw.get(currentQuestion).getQuestion());
                    currentNumber.setText(String.valueOf(questionsw.get(currentQuestion).getQuestionNumber()));

                }

            });
        } else if (quizType.equals("normal")) {
            try {
                Button ed = (Button) e.getSource();
                if (ed.isVisible()) {
                    ed.setVisible(false);
                }
               

            } catch (Exception es) {
            }

            try {
                  if(!submitButton.getText().equals("FINISH")){
                currentQuestion = Integer.parseInt(JOptionPane.showInputDialog("Choose a number between 1 and " + String.valueOf(LastNumber))) - 1;
                
                String remaining = "";
                if (chosenNumbers.size() == LastNumber-1) {
                    submitButton.setText("FINISH");

                }
                else if (chosenNumbers.contains(currentQuestion)) {

                    for (int i = 0; i < LastNumber; i++) {

                        if (!chosenNumbers.contains(i)) {
                            remaining += (i + 1) + ", ";
                        }

                    }
                  
                    JOptionPane.showMessageDialog(null, "Number already taken\nChoose any of These " + remaining);
                    start(null);
                    
                } 
                Alabel.setText(questionsw.get(currentQuestion).getAText());
                Blabel.setText(questionsw.get(currentQuestion).getBText());
                Clabel.setText(questionsw.get(currentQuestion).getCText());
                Dlabel.setText(questionsw.get(currentQuestion).getDText());
                questionview.setText(questionsw.get(currentQuestion).getQuestion());
                currentNumber.setText(String.valueOf(questionsw.get(currentQuestion).getQuestionNumber()));
                chosenNumbers.add(currentQuestion);
                System.out.println(chosenNumbers.size());
                  }
            } catch (Exception ec) {
                start(null);
            }
            
        }

//         String nj = questionsw.toString();
//            Alabel.setText(nj);
        // System.out.println(createQuestion(init));
//               n.setText(questionsw.toString());
//            questionview.setText(name.getQuestion());
//            questionview.setText("Question");
//Blabel.setText(allQuestions.get(0).getBText());
//Clabel.setText(allQuestions.get(0).getCText());
//Dlabel.setText(allQuestions.get(0).getDText());
//currentQuestion = 0;
//        System.out.println("mouse aj");
//            System.out.println(init);
//     
//        }
    }

    ArrayList<Questions> questionsw;

    public ArrayList<Questions> NewQiuzz(Stage stage, File file) {
        questionsw = new ArrayList<>();

        Runnable runnable = new Runnable() {
            public void run() {
                try {
                    Workbook book = new HSSFWorkbook(new FileInputStream(file));

                    Sheet sheet = book.getSheetAt(0);
                    int counter = 1;
                    for (Row row : sheet) {

                        Questions q = new Questions();
                        row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                        q.setQuestion(row.getCell(0).getStringCellValue());
                        row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                        q.setAText(row.getCell(1).getStringCellValue());
                        row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                        q.setBText(row.getCell(2).getStringCellValue());
                        row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                        q.setCText(row.getCell(3).getStringCellValue());
                        row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                        q.setDText(row.getCell(4).getStringCellValue());
                        row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                        q.setAnswer(row.getCell(5).getStringCellValue());
                        q.setQuestionNumber(counter);

                        questionsw.add(q);
                        LastNumber = counter;
                        counter++;

                    }
                } catch (IOException ex) {
                    Logger.getLogger(NewQiuz.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        };
        Platform.runLater(runnable);
        System.out.println(file.getAbsoluteFile());

        return questionsw;
    }

    public void submitAnswer(ActionEvent e) {
        if (quizType.equals("quick")) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    if (quizAnswer.getSelectedToggle() == null) {

                        System.out.println("CHOOSE A TOOGLE");
                        JOptionPane.showMessageDialog(null, "CHOOSE AN ANSWER", JOptionPane.MESSAGE_PROPERTY, JOptionPane.WARNING_MESSAGE);
                    } else {
                        JFXRadioButton ans = (JFXRadioButton) quizAnswer.getSelectedToggle();
                        String chosenAnswer = ans.getAccessibleText();
                        if (chosenAnswer == null ? questionsw.get(currentQuestion).getAnswer() == null : chosenAnswer.equals(questionsw.get(currentQuestion).getAnswer())) {
                            JOptionPane.showMessageDialog(null, "CORRECT!!!", JOptionPane.MESSAGE_PROPERTY, JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "WRONG!!!", JOptionPane.MESSAGE_PROPERTY, JOptionPane.ERROR_MESSAGE);
                            JOptionPane.showMessageDialog(null, "Correct Answer:" + questionsw.get(currentQuestion).getAnswer(), JOptionPane.MESSAGE_PROPERTY, JOptionPane.ERROR_MESSAGE);

                        }
                        System.out.print(ans.getAccessibleText());
                        System.err.println(questionsw.get(currentQuestion).getAnswer());

                        start(null);
                        currentQuestion++;
                        quizAnswer.selectToggle(null);
                    }

                }
            });
        } else if (quizType.equals("normal")) {
             if (submitButton.getText().equals("FINISH")) {
                    Questionstage.close();
                }
            boolean error = false;
            Platform.runLater(new Runnable() {
                @Override
                public void run() {

                    if (quizAnswer.getSelectedToggle() == null) {

                        System.out.println("CHOOSE A TOOGLE");
                        JOptionPane.showMessageDialog(null, "CHOOSE AN ANSWER", JOptionPane.MESSAGE_PROPERTY, JOptionPane.WARNING_MESSAGE);
                    } else {
                        JFXRadioButton ans = (JFXRadioButton) quizAnswer.getSelectedToggle();
                        String chosenAnswer = ans.getAccessibleText();
                        if (chosenAnswer == null ? questionsw.get(currentQuestion).getAnswer() == null : chosenAnswer.equals(questionsw.get(currentQuestion).getAnswer())) {
                            JOptionPane.showMessageDialog(null, "CORRECT!!!", JOptionPane.MESSAGE_PROPERTY, JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "WRONG!!!", JOptionPane.MESSAGE_PROPERTY, JOptionPane.ERROR_MESSAGE);
                            JOptionPane.showMessageDialog(null, "Correct Answer:" + questionsw.get(currentQuestion).getAnswer(), JOptionPane.MESSAGE_PROPERTY, JOptionPane.ERROR_MESSAGE);

                        }
                        System.out.print(ans.getAccessibleText());
                        System.err.println(questionsw.get(currentQuestion).getAnswer());

                        start(null);

                        quizAnswer.selectToggle(null);
                    }

                }
            });

        }

    }

    @FXML
    private Label questionview;

    @FXML
    private Label currentNumber;

    @FXML
    private JFXRadioButton Aradio;

    @FXML
    private Label Alabel;

    @FXML
    private ToggleGroup quizAnswer;

    @FXML
    private JFXRadioButton Bradio;

    @FXML
    private Label Blabel;

    @FXML
    private JFXRadioButton Cradio;

    @FXML
    private Label Clabel;

    @FXML
    private ToggleGroup quizAnswer1;

    @FXML
    private JFXRadioButton Dradio;

    @FXML
    private Label Dlabel;

    @FXML
    private JFXButton submitButton;

    @FXML
    public void normalQuiz(ActionEvent e) {
        quizType = "normal";
        try {
            FileChooser choose = new FileChooser();
            choose.getExtensionFilters().add(new FileChooser.ExtensionFilter("QUIZ-MASTER FILE (.quizfile)", "*.quizfile"));
            Questionstage = new Stage();
            // NewQiuz wi = new NewQiuz();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/questionHandler/questionhandler2.fxml"));
            loader.setController(this);
            Parent root = (Parent) loader.load();
            Questionstage.setScene(new Scene(root));
            Questionstage.setMaximized(true);
            Questionstage.show();
            nomQuiz(choose.showOpenDialog(Questionstage));
        } catch (IOException ex) {
            Logger.getLogger(layoutController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    ArrayList<Integer> chosenNumbers;

    public void nomQuiz(File file) {
        chosenNumbers = new ArrayList<>();
        questionsw = new ArrayList<>();

        Runnable runnable = new Runnable() {
            public void run() {
                try {
                    Workbook book = new HSSFWorkbook(new FileInputStream(file));

                    Sheet sheet = book.getSheetAt(0);
                    int counter = 1;
                    for (Row row : sheet) {

                        Questions q = new Questions();
                        row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                        q.setQuestion(row.getCell(0).getStringCellValue());
                        row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                        q.setAText(row.getCell(1).getStringCellValue());
                        row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                        q.setBText(row.getCell(2).getStringCellValue());
                        row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                        q.setCText(row.getCell(3).getStringCellValue());
                        row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                        q.setDText(row.getCell(4).getStringCellValue());
                        row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                        q.setAnswer(row.getCell(5).getStringCellValue());
                        q.setQuestionNumber(counter);

                        questionsw.add(q);
                        LastNumber = counter;
                        counter++;

                    }
                } catch (IOException ex) {
                    Logger.getLogger(NewQiuz.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        };
        Platform.runLater(runnable);
        System.out.println(file.getAbsoluteFile());

    }

    @FXML
    Label timerLabel;
    private void startThread() {
    Platform.runLater(new Runnable() {
        @Override
        public void run() {
            String time = "";
            int counter = 0;
            int counter2 = 0;
            int sec;
            int min = 0;
            for(counter = 0;counter <=60;counter++){
             for(counter2 = 0;counter2 <=60;counter2++){
                 try {
                     sec = counter2;
                     Thread.sleep(1000);
                     time = String.valueOf(min)+" : "+String.valueOf(sec);
                 } catch (InterruptedException ex) {
                     Logger.getLogger(layoutController.class.getName()).log(Level.SEVERE, null, ex);
                 }
             
            }
             min = counter;
             timerLabel.setText(time);
            }
            
            
            
        }
        
    });
        
    }
    
    public void createMessageDialog(String info){
        JFXDialog f = new JFXDialog();
        f.show();
       
    
    }
    
    public void createInputDialog(String info){
    
    
    }

  
    
    
}
