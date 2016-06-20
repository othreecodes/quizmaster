/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package questionHandler;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;

/**
 *
 * @author UCHENNA
 */
public class Questions {

    private String question;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAText() {
        return AText;
    }

    public void setAText(String AText) {
        this.AText = AText;
    }

    public String getCText() {
        return CText;
    }

    public void setCText(String CText) {
        this.CText = CText;
    }

    public String getBText() {
        return BText;
    }

    public void setBText(String BText) {
        this.BText = BText;
    }

    public String getDText() {
        return DText;
    }

    public void setDText(String DText) {
        this.DText = DText;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    private  String AText;

    private  String CText;

    private  String BText;

    private  String DText;

    private String answer;        
    private int questionNumber ;
    
 
    
    
    
    
    

}
