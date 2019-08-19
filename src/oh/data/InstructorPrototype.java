/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oh.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.ComboBox;

/**
 *
 * @author justi
 */
public class InstructorPrototype {
    private final StringProperty name;
    private final StringProperty email;
    private final StringProperty room;
    private final StringProperty homepage;
    private final StringProperty html;
    private ComboBox subject;
    private ComboBox number;
    private ComboBox semester;
    private ComboBox year;
     public InstructorPrototype(String initName, String initEmail, String initRoom, String inithomepage,String inithtml) {
        name = new SimpleStringProperty(initName);
        email = new SimpleStringProperty(initEmail);
        room = new SimpleStringProperty(initRoom);
        homepage = new SimpleStringProperty(inithomepage);
        html=new SimpleStringProperty(inithtml);
        subject=new ComboBox();
        number=new ComboBox();
        semester   =new ComboBox();
        year=new ComboBox();
    }

    public ComboBox getSubject() {
        return subject;
    }

    public void setSubject(ComboBox subject) {
        this.subject = subject;
    }

    public ComboBox getNumber() {
        return number;
    }

    public void setNumber(ComboBox number) {
        this.number = number;
    }

    public ComboBox getSemester() {
        return semester;
    }

    public void setSemester(ComboBox semester) {
        this.semester = semester;
    }

    public ComboBox getYear() {
        return year;
    }

    public void setYear(ComboBox year) {
        this.year = year;
    }
     
     public String gethtml(){
         return html.get();
     }
     public void setHTML(String newhtml){
         html.set(newhtml);
     }

    public String getName() {
        return name.get();
    }
 public void setName(String initName) {
        name.set(initName);
    }
    public String getEmail() {
        return email.get();
    }
 public void setEmail(String initEmail) {
        email.set(initEmail);
    }
    public String getRoom() {
        return room.get();
    }
public void setRoom(String rooom){
    room.set(rooom);
}
    public String getHomepage() {
        return homepage.get();
    }
public void setHomePage(String pahehome){
    homepage.set(pahehome);
}
}
