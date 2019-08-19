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
public class SchedulePrototype {
    private final StringProperty type;
    private final StringProperty date;
    private final StringProperty title;
    private final StringProperty topic;
    private final StringProperty link;
    public SchedulePrototype(String initType, String initDate, String initTitle, String initTopic, String initLink){
        type=new SimpleStringProperty(initType);
        date=new SimpleStringProperty(initDate);
        title=new SimpleStringProperty(initTitle);
        topic=new SimpleStringProperty(initTopic);
        link =new SimpleStringProperty(initLink);
    }
    public SchedulePrototype deepCopy(SchedulePrototype g){
        SchedulePrototype b = new SchedulePrototype(g.getType(),g.getDate(),g.getTitle(),g.getTopic(),g.getLink());
        return b;
    }
    public String getType() {
        return type.get();
    }
    public void setType(String a){
        type.set(a);
    }
    public String getDate() {
        return date.get();
    }
    public void setDate(String a){
        date.set(a);
    }
    public String getTitle() {
        return title.get();
    }
    public void setTitle(String a){
        title.set(a);
    }
    public String getTopic() {
        return topic.get();
    }
    public void setTopic(String a){
        topic.set(a);
    }
    public String getLink() {
        return link.get();
    }
    public void setLink(String a){
        link.set(a);
    }
}
