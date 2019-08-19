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
public class SyllabusPrototype {
    private final StringProperty description;
    private final StringProperty topics;
    private final StringProperty prerequisites;
    private final StringProperty outcomes;
    private final StringProperty textbooks;
    private final StringProperty gradedComponents;
    private final StringProperty gradingNotes;
    private final StringProperty academicDishonesty;
    private final StringProperty specialAssistanse;
    public SyllabusPrototype(String initDes, String initTopics, String pre, String out, String text,
            String gradedC, String grdadedN, String ad, String SA){
        description=new SimpleStringProperty(initDes);
        topics = new SimpleStringProperty(initTopics);
       prerequisites = new SimpleStringProperty(pre);
        outcomes     = new SimpleStringProperty(out);
        textbooks          = new SimpleStringProperty(text);
        gradedComponents          = new SimpleStringProperty(gradedC);
        gradingNotes          = new SimpleStringProperty(grdadedN);
       academicDishonesty         = new SimpleStringProperty(ad);
        specialAssistanse         = new SimpleStringProperty(SA);
    }

    public String getDescription() {
        return description.get();
    }
    public void setDescription(String a){
        description.set(a);
    }
    public String getTopics() {
        return topics.get();
    }
public void setTopics(String a){
        topics.set(a);
    }
    public String getPrerequisites() {
        return prerequisites.get();
    }
public void setReq(String a){
        prerequisites.set(a);
    }
    public String getOutcomes() {
        return outcomes.get();
    }
public void setOutcomes(String a){
        outcomes.set(a);
    }
    public String getTextbooks() {
        return textbooks.get();
    }
public void setBooks(String a){
        textbooks.set(a);
    }
    public String getGradedComponents() {
        return gradedComponents.get();
    }
public void setGC(String a){
        gradedComponents.set(a);
    }
    public String getGradingNotes() {
        return gradingNotes.get();
    }
public void setGN(String a){
        gradingNotes.set(a);
    }
    public String getAcademicDishonesty() {
        return academicDishonesty.get();
    }
public void setAD(String a){
        academicDishonesty.set(a);
    }
    public String getSpecialAssistanse() {
        return specialAssistanse.get();
    }
      public void setSA(String a){
        specialAssistanse.set(a);
    }      
}
