/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oh.transactions;

import djf.modules.AppGUIModule;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import jtps.jTPS_Transaction;
import oh.OfficeHoursApp;
import static oh.OfficeHoursPropertyType.DATE_CHOOSE;
import static oh.OfficeHoursPropertyType.DESCRIPTION_TEXT_AREA;
import static oh.OfficeHoursPropertyType.SCHEDULE_LINK;
import static oh.OfficeHoursPropertyType.SCHEDULE_TITLE;
import static oh.OfficeHoursPropertyType.SCHEDULE_TOPIC;
import static oh.OfficeHoursPropertyType.SCHEDULE_TYPE_COMBO;
import oh.data.SchedulePrototype;

/**
 *
 * @author justi
 */
public class EditSchedule_Transaction implements jTPS_Transaction {

    SchedulePrototype schedule;

    String oldTopic, newTopic;
    String oldDate, newDate;
    String oldTitle, newTitle;
    String oldType, newType;
    String oldLink, newLink;
    OfficeHoursApp app;

    public EditSchedule_Transaction(OfficeHoursApp initApp, SchedulePrototype initSchedule, String initType, String initDate, String initTitle, String initTopic, String initLink) {
        schedule = initSchedule;
        app = initApp;
        oldTopic = initSchedule.getTopic();
        oldDate = initSchedule.getDate();
        oldTitle = initSchedule.getTitle();
        oldType = initSchedule.getType();
        oldLink = initSchedule.getLink();
        newTopic = initTopic;
        newDate = initDate;
        newTitle = initTitle;
        newType = initType;
        newLink = initLink;
    }

    @Override
    public void doTransaction() {
        AppGUIModule gui = app.getGUIModule();
        schedule.setDate(newDate);
        schedule.setLink(newLink);
        schedule.setTitle(newTitle);
        schedule.setTopic(newTopic);
        schedule.setType(newType);
        ComboBox typeBox = (ComboBox) gui.getGUINode(SCHEDULE_TYPE_COMBO);
        typeBox.setValue(newType);
        DatePicker dateChoose = (DatePicker) gui.getGUINode(DATE_CHOOSE);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (!(newDate.equals("") || newDate == null)) {
            LocalDate dateTime = LocalDate.parse(newDate, formatter);
            dateChoose.setValue(dateTime);
        }
        TextField scheduleTitle = ((TextField) gui.getGUINode(SCHEDULE_TITLE));
        scheduleTitle.setText(newTitle);
        TextField topicField = ((TextField) gui.getGUINode(SCHEDULE_TOPIC));
        topicField.setText(newTopic);
        TextField linkField = ((TextField) gui.getGUINode(SCHEDULE_LINK));
        linkField.setText(newLink);
    }

    @Override
    public void undoTransaction() {
        AppGUIModule gui = app.getGUIModule();
        schedule.setDate(oldDate);
        schedule.setLink(oldLink);
        schedule.setTitle(oldTitle);
        schedule.setTopic(oldTopic);
        schedule.setType(oldType);
        ComboBox typeBox = (ComboBox) gui.getGUINode(SCHEDULE_TYPE_COMBO);
        typeBox.setValue(oldType);
        DatePicker dateChoose = (DatePicker) gui.getGUINode(DATE_CHOOSE);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (!(oldDate.equals("") || oldDate == null)) {
            LocalDate dateTime = LocalDate.parse(oldDate, formatter);
            dateChoose.setValue(dateTime);
        } else {
            dateChoose.setValue(null);
        }
        TextField scheduleTitle = ((TextField) gui.getGUINode(SCHEDULE_TITLE));
        scheduleTitle.setText(oldTitle);
        TextField topicField = ((TextField) gui.getGUINode(SCHEDULE_TOPIC));
        topicField.setText(oldTopic);
        TextField linkField = ((TextField) gui.getGUINode(SCHEDULE_LINK));
        linkField.setText(oldLink);
    }

}
