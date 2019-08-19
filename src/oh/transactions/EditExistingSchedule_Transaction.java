/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oh.transactions;

import djf.modules.AppGUIModule;
import javafx.collections.ObservableList;
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

/**
 *
 * @author justi
 */
public class EditExistingSchedule_Transaction implements jTPS_Transaction {
    OfficeHoursData data;
    SchedulePrototype a;
            OfficeHoursApp app;

    String newTopic;
    String newDate;
    String newTitle;
    String newType;
    SchedulePrototype people;
    String newLink;
    public EditExistingSchedule_Transaction(OfficeHoursApp s,OfficeHoursData initdata,SchedulePrototype b ){
        data = initdata;
        a=b;
        app=s;
                        AppGUIModule gui = app.getGUIModule();
        ComboBox typeBox = (ComboBox) gui.getGUINode(SCHEDULE_TYPE_COMBO);
        newType=typeBox.getValue().toString();
        DatePicker dateChoose = (DatePicker) gui.getGUINode(DATE_CHOOSE);
        newDate=dateChoose.getValue().toString();
        TextField scheduleTitle = ((TextField) gui.getGUINode(SCHEDULE_TITLE));
        newTitle=scheduleTitle.getText();
        TextField topicField = ((TextField) gui.getGUINode(SCHEDULE_TOPIC));
        newTopic=topicField.getText();
        TextField linkField = ((TextField) gui.getGUINode(SCHEDULE_LINK));
        newLink=linkField.getText();
        people = new SchedulePrototype(newType, newDate,newTitle,newTopic,newLink );
    }
     @Override
    public void doTransaction() {
        ObservableList<SchedulePrototype> v = data.getList();
        v.remove(a);
        v.add(people);
                }

    @Override
    public void undoTransaction() {
ObservableList<SchedulePrototype> v = data.getList();
        v.remove(people);
        v.add(a);       // ObservableList lecturesList = data.getLecturesList();
        //lecturesList.remove(init);
    }
}
