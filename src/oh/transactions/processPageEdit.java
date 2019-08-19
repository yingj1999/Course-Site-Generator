/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oh.transactions;

import static djf.AppPropertyType.BANNER_TITLE;
import djf.modules.AppGUIModule;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import jtps.jTPS_Transaction;
import oh.OfficeHoursApp;
import static oh.OfficeHoursPropertyType.AD_TEXT_AREA;
import static oh.OfficeHoursPropertyType.BOOKS_TEXT_AREA;
import static oh.OfficeHoursPropertyType.DESCRIPTION_TEXT_AREA;
import static oh.OfficeHoursPropertyType.FONTSHEET;
import static oh.OfficeHoursPropertyType.GRADING_COMPONENTS_TEXT_AREA;
import static oh.OfficeHoursPropertyType.GRADING_NOTES_TEXT_AREA;
import static oh.OfficeHoursPropertyType.NUMBER_COMBO;
import static oh.OfficeHoursPropertyType.OUTCOMES_TEXT_AREA;
import static oh.OfficeHoursPropertyType.PPREREQUISITES_TEXT_AREA;
import static oh.OfficeHoursPropertyType.SEMESTER_COMBO;
import static oh.OfficeHoursPropertyType.SPEC_ASSIS_TEXT_AREA;
import static oh.OfficeHoursPropertyType.SUBJECT_COMBO;
import static oh.OfficeHoursPropertyType.TOPICS_TEXT_AREA;
import static oh.OfficeHoursPropertyType.YEAR_COMBO;
import oh.data.OfficeHoursData;
import static oh.data.OfficeHoursData.pageBNumber;
import static oh.data.OfficeHoursData.pageSemester;
import static oh.data.OfficeHoursData.pageSubject;
import static oh.data.OfficeHoursData.pageTitle;
import static oh.data.OfficeHoursData.pageYear;

/**
 *
 * @author justi
 */
public class processPageEdit implements jTPS_Transaction {

    OfficeHoursApp app;
    String newSubject, newSemester, newNumber, newYear, newTitle,newCss;
    String oldSubject, oldSemester, oldNumber, oldYear, oldTitle,oldCss;
AppGUIModule gui;
OfficeHoursData data;
    public processPageEdit(OfficeHoursApp app, String pageSubject, String pageSemester, String pageBNumber, String pageYear, String pageTitle, String initCss) {
         gui = app.getGUIModule();
         data = (OfficeHoursData) app.getDataComponent();
        newSubject = pageSubject;
        newSemester = pageSemester;
        newNumber = pageBNumber;
        newYear = pageYear;
        newTitle = pageTitle;
        newCss=initCss;
        oldSubject = data.pageSubject;
        oldSemester = data.pageSemester;
        oldNumber = data.pageBNumber;
        oldYear = data.pageYear;
        oldTitle = data.pageTitle;
        oldCss=data.css;
    }

    @Override
    public void doTransaction() {
        if (!oldSubject.equals(newSubject)) {
            data.pageSubject = newSubject;
            ComboBox subjectCombo = (ComboBox) gui.getGUINode(SUBJECT_COMBO);
            subjectCombo.setValue(newSubject);
            
        } else if (!oldSemester.equals(newSemester)) {
            data.pageSemester = newSemester;
            ComboBox semesterCombo = (ComboBox) gui.getGUINode(SEMESTER_COMBO);
            semesterCombo.setValue(newSemester);
        } else if (!oldNumber.equals(newNumber)) {
            data.pageBNumber = newNumber;
            ComboBox numberCombo = (ComboBox) gui.getGUINode(NUMBER_COMBO);
            if (!(numberCombo.getItems().contains(newNumber))) {
                        numberCombo.getItems().add(newNumber);

                    }
            numberCombo.setValue(newNumber);
             
        } 
        else if(!oldCss.equals(newCss)){
            data.css=newCss;
            ComboBox cssFile=(ComboBox)gui.getGUINode(FONTSHEET);
            cssFile.setValue(newCss);
        }
        
        else if (!oldYear.equals(newYear)) {
            data.pageYear = newYear;
            ComboBox yearCombo = (ComboBox) gui.getGUINode(YEAR_COMBO);
            yearCombo.setValue(newYear);
        } else if (!oldTitle.equals(newTitle)) {
            data.pageTitle = newTitle;
            TextField title = (TextField) gui.getGUINode(BANNER_TITLE);
            title.setText(newTitle);
       }
    }

    @Override
    public void undoTransaction() {

        ComboBox subjectCombo = (ComboBox) gui.getGUINode(SUBJECT_COMBO);
        subjectCombo.setValue(oldSubject);
        data.pageSubject = oldSubject;
        data.pageSemester = oldSemester;
        data.pageBNumber = oldNumber;
        data.pageYear = oldYear;
        data.pageTitle = oldTitle;
data.css=oldCss;
        ComboBox cssFile=(ComboBox)gui.getGUINode(FONTSHEET);
cssFile.setValue(oldCss);
        ComboBox semesterCombo = (ComboBox) gui.getGUINode(SEMESTER_COMBO);
        semesterCombo.setValue(oldSemester);
        ComboBox numberCombo = (ComboBox) gui.getGUINode(NUMBER_COMBO);
        numberCombo.setValue(oldNumber);
        ComboBox yearCombo = (ComboBox) gui.getGUINode(YEAR_COMBO);
        yearCombo.setValue(oldYear);
        TextField title = (TextField) gui.getGUINode(BANNER_TITLE);
        title.setText(oldTitle);
    }
}
