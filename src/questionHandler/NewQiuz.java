/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package questionHandler;

/**
 *
 * @author UCHENNA
 */
import java.io.File;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.util.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class NewQiuz {

    public NewQiuz(ObservableList<Questions> questions, Stage stage) {
        try {
            FileChooser file = new FileChooser();
            stage.setAlwaysOnTop(false);
            file.setTitle("Save Quiz as...");
            file.setInitialFileName("Quiz" + String.valueOf((int) (Math.random() * 1000)));
            file.getExtensionFilters().add(new FileChooser.ExtensionFilter("QUIZ-MASTER (.quizfile)", "*.quizfile"));
            Workbook workbook = new HSSFWorkbook();
            FileOutputStream output = new FileOutputStream(file.showSaveDialog(null));

            Sheet sheet = workbook.createSheet("newsheet");
            int iter = 0;
            for (Questions ques : questions) {

                Row row = sheet.createRow(iter);
                row.createCell(0)
                        .setCellValue(ques.getQuestion());
                row.createCell(1).setCellValue(ques.getAText());
                row.createCell(2).setCellValue(ques.getBText());
                row.createCell(3).setCellValue(ques.getCText());
                row.createCell(4).setCellValue(ques.getDText());
                row.createCell(5).setCellValue(ques.getAnswer());
                System.out.println(iter);
                iter++;

            }
            workbook.write(output);
        } catch (IOException ex) {
            Logger.getLogger(NewQiuz.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public NewQiuz() {

    }

}
