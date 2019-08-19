/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oh.transactions;

import djf.modules.AppGUIModule;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import jtps.jTPS_Transaction;
import oh.OfficeHoursApp;
import static oh.OfficeHoursPropertyType.LECTURES_TABLE_VIEW;
import oh.data.LecturesPrototype;
import oh.data.OfficeHoursData;

/**
 *
 * @author justi
 */

public class EditLectureTransaction implements jTPS_Transaction{
        OfficeHoursData data;
            LecturesPrototype chosen, edited;
            String oldDay, oldRoom, oldSection, oldTime;
            OfficeHoursApp app;
           public EditLectureTransaction (OfficeHoursApp initApp, OfficeHoursData initData,LecturesPrototype initChosen, LecturesPrototype initEdited ){
               data=initData;
               chosen = initChosen;
               edited=initEdited;
               oldDay = chosen.getDays();
               oldRoom=chosen.getRoom();
               oldSection=chosen.getSection();
               oldTime=chosen.getTimes();
               app = initApp;
           }
            @Override
    public void doTransaction() {
                AppGUIModule gui = app.getGUIModule();
                TableView <LecturesPrototype> jhg =(TableView)gui.getGUINode(LECTURES_TABLE_VIEW);
        chosen.setDays(edited.getDays());
        chosen.setRoom(edited.getRoom());
        chosen.setSection(edited.getSection());
        chosen.settimes(edited.getTimes());
        jhg.refresh();
    }

    @Override
    public void undoTransaction() {
         AppGUIModule gui = app.getGUIModule();
                TableView <LecturesPrototype> jhg =(TableView)gui.getGUINode(LECTURES_TABLE_VIEW);
        chosen.setDays(oldDay);
        chosen.setRoom(oldRoom);
        chosen.setSection(oldSection);
        chosen.settimes(oldTime);
                jhg.refresh();

    }

}
