/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oh.transactions;

import djf.modules.AppGUIModule;
import javafx.scene.control.TableView;
import jtps.jTPS_Transaction;
import oh.OfficeHoursApp;
import static oh.OfficeHoursPropertyType.LECTURES_TABLE_VIEW;
import static oh.OfficeHoursPropertyType.RECITATION_TABLE;
import oh.data.LecturesPrototype;
import oh.data.OfficeHoursData;
import oh.data.Recitation_Lab_Prototype;

/**
 *
 * @author justi
 */
public class EditRecitationTransaction implements jTPS_Transaction {
    OfficeHoursData data;
            Recitation_Lab_Prototype chosen, edited;
            String oldSection, oldDT, oldRoom, oldTA1, oldTA2;
                        OfficeHoursApp app;

           public EditRecitationTransaction (OfficeHoursApp initApp, OfficeHoursData initData,Recitation_Lab_Prototype initChosen, Recitation_Lab_Prototype initEdited ){
               data=initData;
               chosen = initChosen;
               edited=initEdited;
               app=initApp;
               oldSection = chosen.getSection();
               oldDT = chosen.getDaystime();
               oldRoom = chosen.getRoom();
               oldTA1=chosen.getTa1();
               oldTA2=chosen.getTa2();
           }
            @Override
    public void doTransaction() {
        AppGUIModule gui = app.getGUIModule();
                TableView <Recitation_Lab_Prototype> jhg =(TableView)gui.getGUINode(RECITATION_TABLE);
        chosen.setDaystime(edited.getDaystime());
        chosen.setRoom(edited.getRoom());
        chosen.setSection(edited.getSection());
        chosen.setTa1(edited.getTa1());
        chosen.setTa2(edited.getTa2());
            jhg.refresh();

    }

    @Override
    public void undoTransaction() {
AppGUIModule gui = app.getGUIModule();
                TableView <Recitation_Lab_Prototype> jhg =(TableView)gui.getGUINode(RECITATION_TABLE);
                chosen.setDaystime(oldDT);
        chosen.setRoom(oldRoom);
        chosen.setSection(oldSection);
        chosen.setTa1(oldTA1);
        chosen.setTa2(oldTA2);
        jhg.refresh();    
    }
}
