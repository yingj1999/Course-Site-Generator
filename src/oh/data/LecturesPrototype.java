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
public class LecturesPrototype {
    private final StringProperty section;
    private final StringProperty days;
    private final StringProperty times;
    private final StringProperty room; 
    public LecturesPrototype(String initSection, String initDays, String initTimes, String initRoom){
        section=new SimpleStringProperty(initSection);
        days=new SimpleStringProperty(initDays);
        times =new SimpleStringProperty(initTimes);
        room=new SimpleStringProperty(initRoom);
    }

    public String getSection() {
        return section.get();
    }
    public void setSection(String bob){
        section.set(bob);
    }
            
    public String getDays() {
        return days.get();
    }
    public void setDays(String hg){
        days.set(hg);
    }
    public String getTimes() {
        return times.get();
    }
    public void settimes(String y){
        times.set(y);
    }

    public String getRoom() {
        return room.get();
    }
    public void setRoom(String q){
        room.set(q);
    }
    
}
