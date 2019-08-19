/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oh.transactions;
import djf.modules.AppGUIModule;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import jtps.jTPS_Transaction;
import oh.OfficeHoursApp;
import static oh.OfficeHoursPropertyType.INSTRUCTOR_EMAIL_TEXT_FIELD;
import static oh.OfficeHoursPropertyType.INSTRUCTOR_HP_TEXT_FIELD;
import static oh.OfficeHoursPropertyType.INSTRUCTOR_NAME_TEXT_FIELD;
import static oh.OfficeHoursPropertyType.INSTRUCTOR_ROOM_TEXT_FIELD;
import static oh.OfficeHoursPropertyType.INSTRUCTOR_TEXT_AREA;
import oh.data.InstructorPrototype;
/**
 *
 * @author justi
 */
public class EditInstructor_Transaction implements jTPS_Transaction{
    InstructorPrototype taToEdit;
    String oldName, newName;
    String oldEmail, newEmail;
    String oldRoom, newRoom;
    String oldHomePage, newHomePage;
    String oldHTML, newHTML;
    OfficeHoursApp app;
    public EditInstructor_Transaction(OfficeHoursApp initApp,InstructorPrototype initTAToEdit, 
            String name, String email, String room, String homepage,String html) {
        taToEdit = initTAToEdit;
        oldName = initTAToEdit.getName();
        oldEmail = initTAToEdit.getEmail();
        oldRoom = initTAToEdit.getRoom();
        oldHomePage=initTAToEdit.getHomepage();
        oldHTML=initTAToEdit.gethtml();
        newName = name;
        newEmail = email;
        newRoom = room;
        newHomePage=homepage;
        newHTML=html;
        app=initApp;
    }


    @Override
    public void doTransaction() {
                AppGUIModule gui = app.getGUIModule();

        taToEdit.setName(newName);
        taToEdit.setEmail(newEmail);
        taToEdit.setRoom(newRoom);
        taToEdit.setHomePage(newHomePage);
        taToEdit.setHTML(newHTML);
         TextField nameStuff = (TextField) gui.getGUINode(INSTRUCTOR_NAME_TEXT_FIELD);
        nameStuff.setText(newName);
        TextField emailStuff = (TextField) gui.getGUINode(INSTRUCTOR_EMAIL_TEXT_FIELD);
        emailStuff.setText(newEmail);
        TextField roomStuff = (TextField) gui.getGUINode(INSTRUCTOR_ROOM_TEXT_FIELD);
        roomStuff.setText(newRoom);
        TextField hpStuff = (TextField) gui.getGUINode(INSTRUCTOR_HP_TEXT_FIELD);
        hpStuff.setText(newHomePage);
        TextArea html =(TextArea) gui.getGUINode(INSTRUCTOR_TEXT_AREA);
        html.setText(newHTML);
    }

    @Override
    public void undoTransaction() {
                        AppGUIModule gui = app.getGUIModule();
        taToEdit.setName(oldName);
        taToEdit.setEmail(oldEmail);
        taToEdit.setRoom(oldRoom);
        taToEdit.setHomePage(oldHomePage);
        taToEdit.setHTML(oldHTML);
        TextField nameStuff = (TextField) gui.getGUINode(INSTRUCTOR_NAME_TEXT_FIELD);
        nameStuff.setText(oldName);
        TextField emailStuff = (TextField) gui.getGUINode(INSTRUCTOR_EMAIL_TEXT_FIELD);
        emailStuff.setText(oldEmail);
        TextField roomStuff = (TextField) gui.getGUINode(INSTRUCTOR_ROOM_TEXT_FIELD);
        roomStuff.setText(oldRoom);
        TextField hpStuff = (TextField) gui.getGUINode(INSTRUCTOR_HP_TEXT_FIELD);
        hpStuff.setText(oldHomePage);
        TextArea html =(TextArea) gui.getGUINode(INSTRUCTOR_TEXT_AREA);
        html.setText(oldHTML);
    }
}
