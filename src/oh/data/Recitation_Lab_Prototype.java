/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oh.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author justi
 */
public class Recitation_Lab_Prototype {

    private final StringProperty section;
    private final StringProperty daystime;
    private final StringProperty room;
    private final StringProperty ta1;
    private final StringProperty ta2;
    public Recitation_Lab_Prototype(String initSection, String initDays, String initRoom, String init1, String init2){
        section =new SimpleStringProperty(initSection);
        daystime=new SimpleStringProperty(initDays);
        room=new SimpleStringProperty(initRoom);
        ta1=new SimpleStringProperty(init1);
        ta2=new SimpleStringProperty(init2);
    }

    public String getSection() {
        return section.get();
    }
    public void setSection(String d){
        section.set(d);
    }
    public String getDaystime() {
        return daystime.get();
    }
    public void setDaystime(String h){
        daystime.set(h);
    }
    public String getRoom() {
        return room.get();
    }
    public void setRoom(String q){
        room.set(q);
    }
    public String getTa1() {
        return ta1.get();
    }
    public void setTa1(String g){
        ta1.set(g);
    }
    public String getTa2() {
        return ta2.get();
    }
    public void setTa2(String f){
        ta2.set(f);
    }
}
