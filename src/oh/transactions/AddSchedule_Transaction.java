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
import javafx.scene.control.TextField;
import jtps.jTPS_Transaction;
import oh.OfficeHoursApp;
import static oh.OfficeHoursPropertyType.DATE_CHOOSE;
import static oh.OfficeHoursPropertyType.SCHEDULE_LINK;
import static oh.OfficeHoursPropertyType.SCHEDULE_TITLE;
import static oh.OfficeHoursPropertyType.SCHEDULE_TOPIC;
import static oh.OfficeHoursPropertyType.SCHEDULE_TYPE_COMBO;
import oh.data.OfficeHoursData;
import oh.data.SchedulePrototype;
import oh.data.TeachingAssistantPrototype;

/**
 *
 * @author justi
 */
public class AddSchedule_Transaction implements jTPS_Transaction{
     OfficeHoursData data;
    SchedulePrototype sch;
        OfficeHoursApp app;
  String oldTopic;
    String oldDate;
    String oldTitle;
    String oldType;
    String oldLink;
    public AddSchedule_Transaction(OfficeHoursApp initApp,OfficeHoursData initData,SchedulePrototype initSch,String initType, String initDate, String initTitle, String initTopic, String initLink ){
        data=initData;
        sch=initSch;
        app=initApp;
        oldTopic = initTopic;
        oldDate = initDate;
        oldTitle = initTitle;
        oldType = initType;
        oldLink = initLink;
    }
    @Override
    public void doTransaction() {
                AppGUIModule gui = app.getGUIModule();
        data.addSchedule(sch);
        ComboBox typeBox = (ComboBox) gui.getGUINode(SCHEDULE_TYPE_COMBO);
        DatePicker dateChoose = (DatePicker) gui.getGUINode(DATE_CHOOSE);
        TextField scheduleTitle = ((TextField) gui.getGUINode(SCHEDULE_TITLE));
        TextField topicField = ((TextField) gui.getGUINode(SCHEDULE_TOPIC));
        TextField linkField = ((TextField) gui.getGUINode(SCHEDULE_LINK));
         SchedulePrototype schedule = data.getSchedule();
                     schedule.setType("");
                     typeBox.setValue(schedule.getType());
                     schedule.setDate("");
                     dateChoose.setValue(null);
                     scheduleTitle.setText("");
                     schedule.setTitle("");
                     topicField.setText("");
                     schedule.setTopic("");
                     linkField.setText("");
                     schedule.setLink("");
    }

    @Override
    public void undoTransaction() {
                        AppGUIModule gui = app.getGUIModule();
        data.removeSchedule(sch);
        ComboBox typeBox = (ComboBox) gui.getGUINode(SCHEDULE_TYPE_COMBO);
        DatePicker dateChoose = (DatePicker) gui.getGUINode(DATE_CHOOSE);
        TextField scheduleTitle = ((TextField) gui.getGUINode(SCHEDULE_TITLE));
        TextField topicField = ((TextField) gui.getGUINode(SCHEDULE_TOPIC));
        TextField linkField = ((TextField) gui.getGUINode(SCHEDULE_LINK));
         SchedulePrototype schedule = data.getSchedule();
                     schedule.setType(oldType);
                     typeBox.setValue(oldType);
                     schedule.setDate(oldDate);
                      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dateTime = LocalDate.parse(oldDate, formatter);
                     dateChoose.setValue(dateTime);
                     scheduleTitle.setText(oldTitle);
                     schedule.setTitle(oldTitle);
                     topicField.setText(oldTopic);
                     schedule.setTopic(oldTopic);
                     linkField.setText(oldLink);
                     schedule.setLink(oldLink);
                     
    }
}
