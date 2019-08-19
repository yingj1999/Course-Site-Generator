/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oh.transactions;
import djf.modules.AppGUIModule;
import javafx.scene.control.TextArea;
import jtps.jTPS_Transaction;
import oh.OfficeHoursApp;
import static oh.OfficeHoursPropertyType.AD_TEXT_AREA;
import static oh.OfficeHoursPropertyType.BOOKS_TEXT_AREA;
import static oh.OfficeHoursPropertyType.DESCRIPTION_TEXT_AREA;
import static oh.OfficeHoursPropertyType.GRADING_COMPONENTS_TEXT_AREA;
import static oh.OfficeHoursPropertyType.GRADING_NOTES_TEXT_AREA;
import static oh.OfficeHoursPropertyType.OUTCOMES_TEXT_AREA;
import static oh.OfficeHoursPropertyType.PPREREQUISITES_TEXT_AREA;
import static oh.OfficeHoursPropertyType.SPEC_ASSIS_TEXT_AREA;
import static oh.OfficeHoursPropertyType.TOPICS_TEXT_AREA;
import oh.data.InstructorPrototype;
import oh.data.SyllabusPrototype;
/**
 *
 * @author justi
 */

public class EditSyllabus_Transaction implements jTPS_Transaction{
    SyllabusPrototype syllabus;
    String olddescription, newDescription;
    String oldtopics, newTopics;
    String oldPRE, newPRE;
    String oldOut, newOut;
    String oldext, newText;
    String oldGC, newGC;
    String oldGN, newGN;
    String oldAC, newAC;
    String oldSA, newSA;
        OfficeHoursApp app;

    public EditSyllabus_Transaction(OfficeHoursApp initApp,SyllabusPrototype fd, String intD, String initTopics, String initPRE, String initOut,
            String initText, String initGC, String initGN, String initAC, String initSA){
        syllabus = fd;
        olddescription=fd.getDescription();
        oldtopics=fd.getTopics();
        oldPRE=fd.getPrerequisites();
        oldOut=fd.getOutcomes();
        oldext=fd.getTextbooks();
        oldGC=fd.getGradedComponents();
        oldGN=fd.getGradingNotes();
        oldAC=fd.getAcademicDishonesty();
        oldSA=fd.getSpecialAssistanse();
         newDescription=intD;
   newTopics=initTopics;
   newPRE=initPRE;
     newOut=initOut;
    newText=initText;
     newGC=initGC;
     newGN=initGN;
     newAC=initAC;
     newSA=initSA;
              app=initApp;
    
    }
      @Override
    public void doTransaction() {
                        AppGUIModule gui = app.getGUIModule();

       syllabus.setDescription(newDescription);
       syllabus.setTopics(newTopics);
       syllabus.setReq(newPRE);
       syllabus.setOutcomes(newOut);
       syllabus.setBooks(newText);
       syllabus.setGC(newGC);
       syllabus.setGN(newGN);
       syllabus.setAD(newAC);
       syllabus.setSA(newSA);
             TextArea description =(TextArea)gui.getGUINode(DESCRIPTION_TEXT_AREA);
        description.setText(newDescription);
        TextArea topics =(TextArea)gui.getGUINode(TOPICS_TEXT_AREA);
        topics.setText(newTopics);
        TextArea preReq =(TextArea)gui.getGUINode(PPREREQUISITES_TEXT_AREA);
        preReq.setText(newPRE);
        TextArea out =(TextArea)gui.getGUINode(OUTCOMES_TEXT_AREA);
        out.setText(newOut);
        TextArea books =(TextArea)gui.getGUINode(BOOKS_TEXT_AREA);
        books.setText(newText);
        TextArea GC =(TextArea)gui.getGUINode(GRADING_COMPONENTS_TEXT_AREA);
        GC.setText(newGC);
        TextArea GN =(TextArea)gui.getGUINode(GRADING_NOTES_TEXT_AREA);
        GN.setText(newGN);
         TextArea SAD =(TextArea)gui.getGUINode(AD_TEXT_AREA);
        SAD.setText(newAC);
        TextArea SSA =(TextArea)gui.getGUINode(SPEC_ASSIS_TEXT_AREA);
        SSA.setText(newSA);
        
    }

    @Override
    public void undoTransaction() {
                        AppGUIModule gui = app.getGUIModule();

       syllabus.setDescription(olddescription);
       syllabus.setTopics(oldtopics);
       syllabus.setReq(oldPRE);
       syllabus.setOutcomes(oldOut);
       syllabus.setBooks(oldext);
       syllabus.setGC(oldGC);
       syllabus.setGN(oldGN);
       syllabus.setAD(oldAC);
       syllabus.setSA(oldSA);
             TextArea description =(TextArea)gui.getGUINode(DESCRIPTION_TEXT_AREA);
        description.setText(olddescription);
        TextArea topics =(TextArea)gui.getGUINode(TOPICS_TEXT_AREA);
        topics.setText(oldtopics);
        TextArea preReq =(TextArea)gui.getGUINode(PPREREQUISITES_TEXT_AREA);
        preReq.setText(oldPRE);
        TextArea out =(TextArea)gui.getGUINode(OUTCOMES_TEXT_AREA);
        out.setText(oldOut);
        TextArea books =(TextArea)gui.getGUINode(BOOKS_TEXT_AREA);
        books.setText(oldext);
        TextArea GC =(TextArea)gui.getGUINode(GRADING_COMPONENTS_TEXT_AREA);
        GC.setText(oldGC);
        TextArea GN =(TextArea)gui.getGUINode(GRADING_NOTES_TEXT_AREA);
        GN.setText(oldGN);
         TextArea SAD =(TextArea)gui.getGUINode(AD_TEXT_AREA);
        SAD.setText(oldAC);
        TextArea SSA =(TextArea)gui.getGUINode(SPEC_ASSIS_TEXT_AREA);
        SSA.setText(oldSA);
        
    }
}
