package oh.workspace.controllers;

import static djf.AppPropertyType.APP_PATH_IMAGES;
import djf.modules.AppGUIModule;
import djf.ui.dialogs.AppDialogsFacade;
import java.io.File;
import java.net.MalformedURLException;
import javafx.collections.ObservableList;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import oh.OfficeHoursApp;
import static oh.OfficeHoursPropertyType.LECTURES_TABLE_VIEW;
import static oh.OfficeHoursPropertyType.OH_EMAIL_TEXT_FIELD;
import static oh.OfficeHoursPropertyType.OH_FOOLPROOF_SETTINGS;
import static oh.OfficeHoursPropertyType.OH_NAME_TEXT_FIELD;
import static oh.OfficeHoursPropertyType.OH_NO_TA_SELECTED_CONTENT;
import static oh.OfficeHoursPropertyType.OH_NO_TA_SELECTED_TITLE;
import static oh.OfficeHoursPropertyType.OH_OFFICE_HOURS_TABLE_VIEW;
import static oh.OfficeHoursPropertyType.OH_TAS_TABLE_VIEW;
import static oh.OfficeHoursPropertyType.OH_TA_EDIT_DIALOG;
import oh.data.InstructorPrototype;
import oh.data.LecturesPrototype;
import oh.data.OfficeHoursData;
import oh.data.Recitation_Lab_Prototype;
import oh.data.SchedulePrototype;
import oh.data.SyllabusPrototype;
import oh.data.TAType;
import oh.data.TeachingAssistantPrototype;
import oh.data.TimeSlot;
import oh.data.TimeSlot.DayOfWeek;
import oh.transactions.AddLabTransaction;
import oh.transactions.AddLecture_Transaction;
import oh.transactions.AddRecitationTransaction;
import oh.transactions.AddSchedule_Transaction;
import oh.transactions.AddTA_Transaction;
import oh.transactions.EditExistingSchedule_Transaction;
import oh.transactions.EditInstructor_Transaction;
import oh.transactions.EditLabTransaction;
import oh.transactions.EditLectureTransaction;
import oh.transactions.EditRecitationTransaction;
import oh.transactions.EditSchedule_Transaction;
import oh.transactions.EditSyllabus_Transaction;
import oh.transactions.EditTA_Transaction;
import oh.transactions.ImageTransaction;
import oh.transactions.RemoveLabTransaction;
import oh.transactions.RemoveLecture_Transaction;
import oh.transactions.RemoveRecitationTransaction;
import oh.transactions.Remove_SCH_Transaction;
import oh.transactions.ToggleOfficeHours_Transaction;
import oh.transactions.processPageEdit;
import oh.workspace.dialogs.TADialog;
import properties_manager.PropertiesManager;

/**
 *
 * @author McKillaGorilla
 */
public class OfficeHoursController {

    OfficeHoursApp app;

    public OfficeHoursController(OfficeHoursApp initApp) {
        app = initApp;
    }

    public void processAddTA() {
        AppGUIModule gui = app.getGUIModule();
        TextField nameTF = (TextField) gui.getGUINode(OH_NAME_TEXT_FIELD);
        String name = nameTF.getText();
        TextField emailTF = (TextField) gui.getGUINode(OH_EMAIL_TEXT_FIELD);
        String email = emailTF.getText();
        OfficeHoursData data = (OfficeHoursData) app.getDataComponent();
        TAType type = data.getSelectedType();
        if (data.isLegalNewTA(name, email)) {
            TeachingAssistantPrototype ta = new TeachingAssistantPrototype(name.trim(), email.trim(), type);
            AddTA_Transaction addTATransaction = new AddTA_Transaction(data, ta);
            app.processTransaction(addTATransaction);

            // NOW CLEAR THE TEXT FIELDS
            nameTF.setText("");
            emailTF.setText("");
            nameTF.requestFocus();
        }
        app.getFoolproofModule().updateControls(OH_FOOLPROOF_SETTINGS);
    }
    public void processAddLectures(){
                OfficeHoursData data = (OfficeHoursData) app.getDataComponent();
                AddLecture_Transaction bob = new AddLecture_Transaction(data);
                app.processTransaction(bob);

    }
    public void processAddRecitations(){
        OfficeHoursData data = (OfficeHoursData) app.getDataComponent();
                AddRecitationTransaction bob = new AddRecitationTransaction(data);
                app.processTransaction(bob);
    }
    public void changeHome(String f){
                OfficeHoursData data = (OfficeHoursData) app.getDataComponent();
                data.cbhome=f;
    }
    public void processAddLab(){
         OfficeHoursData data = (OfficeHoursData) app.getDataComponent();
                AddLabTransaction bob = new AddLabTransaction(data);
                app.processTransaction(bob);
    }
    public void processEditPage(String initSubject, String initSemester,String initNumber,  String initYear, String initTitle,String initCss){
        processPageEdit bob = new processPageEdit(app, initSubject,   initSemester, initNumber,  initYear,  initTitle,initCss);
                app.processTransaction(bob);
    }
    public void processRemoveRecitations(Recitation_Lab_Prototype b){
         OfficeHoursData data = (OfficeHoursData) app.getDataComponent();
                RemoveRecitationTransaction bob = new RemoveRecitationTransaction(data,b);
                app.processTransaction(bob);
    }
    public void processRemoveLab(Recitation_Lab_Prototype b){
        OfficeHoursData data = (OfficeHoursData) app.getDataComponent();
                RemoveLabTransaction bob = new RemoveLabTransaction(data,b);
                app.processTransaction(bob);
    }
    public void processRemoveLectures(LecturesPrototype b){
        OfficeHoursData data = (OfficeHoursData) app.getDataComponent();
                RemoveLecture_Transaction bob = new RemoveLecture_Transaction(data,b);
                app.processTransaction(bob);
    }
    public void processEditLecture(LecturesPrototype chosen, LecturesPrototype edited){
                OfficeHoursData data = (OfficeHoursData) app.getDataComponent();
        EditLectureTransaction s = new EditLectureTransaction(app,data,chosen, edited);
                        app.processTransaction(s);        
    }
    public void processEditRecitation(Recitation_Lab_Prototype chosen, Recitation_Lab_Prototype edited){
        OfficeHoursData data = (OfficeHoursData) app.getDataComponent();
        EditRecitationTransaction s = new EditRecitationTransaction(app,data,chosen, edited);
                        app.processTransaction(s); 
    }
    public void processEditLab(Recitation_Lab_Prototype chosen, Recitation_Lab_Prototype edited){
            OfficeHoursData data = (OfficeHoursData) app.getDataComponent();
        EditLabTransaction s = new EditLabTransaction(app,data,chosen, edited);
                        app.processTransaction(s); 
    }
public void processAddSchedule() {
            AppGUIModule gui = app.getGUIModule();
        OfficeHoursData data = (OfficeHoursData) app.getDataComponent();
        SchedulePrototype oldSchedule = data.getSchedule();
        SchedulePrototype newSchedule = oldSchedule.deepCopy(oldSchedule);
        AddSchedule_Transaction sch = new AddSchedule_Transaction(app,data,newSchedule, oldSchedule.getType(),
        oldSchedule.getDate(),oldSchedule.getTitle(),oldSchedule.getTopic(),oldSchedule.getLink());
        app.processTransaction(sch);
        
    }
public void processEditExistingSchedule(SchedulePrototype b) {
        OfficeHoursData data = (OfficeHoursData) app.getDataComponent();
        
        EditExistingSchedule_Transaction sch = new EditExistingSchedule_Transaction(app,data,b);
        app.processTransaction(sch);
        
    }
public void processScheduleRemoval(SchedulePrototype scheduleType){
            OfficeHoursData data = (OfficeHoursData) app.getDataComponent();
            Remove_SCH_Transaction b = new Remove_SCH_Transaction(data, scheduleType);
            app.processTransaction(b);
}
    public void processVerifyTA() {

    }
    public void findImage(ImageView imgView, String choice) throws MalformedURLException {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
                    OfficeHoursData data = (OfficeHoursData) app.getDataComponent();

        File file = new File(props.getProperty(APP_PATH_IMAGES));
        // MAKE RELATIVE PATH USING THE NAME OF THE FILE AND THE ABOVE FILE
        FileChooser fC = new FileChooser();
        fC.setInitialDirectory(file);
        fC.setTitle("Open Resource File");
        fC.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Image Files","*.jpg", "*.png","*.gif"));
        File selectedFile = fC.showOpenDialog(app.getGUIModule().getWindow());
        
        if (selectedFile != null) {
            String filePath = selectedFile.getPath();
            System.out.println(data.favicon+" "+data.navbar+" "+data.leftFooter+" "+data.rightFooter);
            if(choice.equals("f")){
                data.setFavicon(filePath);
            }
            else if(choice.equals("n")){
                data.setNavbar(filePath);
            }
            else if(choice.equals("l")){
                data.setLeft(filePath);
            }
            else if (choice.equals("r")){
            data.setRight(filePath);
        }
                        System.out.println(data.favicon+" "+data.navbar+" "+data.leftFooter+" "+data.rightFooter);

            System.out.println(data.favicon);
            Image newImage = new Image("file:/" + filePath);
            ImageTransaction transaction = new ImageTransaction(imgView, imgView.getImage(), newImage);
            app.processTransaction(transaction);
        }
    }
    public void processToggleOfficeHours() {
        AppGUIModule gui = app.getGUIModule();
        TableView<TimeSlot> officeHoursTableView = (TableView) gui.getGUINode(OH_OFFICE_HOURS_TABLE_VIEW);
        ObservableList<TablePosition> selectedCells = officeHoursTableView.getSelectionModel().getSelectedCells();
        if (selectedCells.size() > 0) {
            TablePosition cell = selectedCells.get(0);
            int cellColumnNumber = cell.getColumn();
            OfficeHoursData data = (OfficeHoursData)app.getDataComponent();
            if (data.isDayOfWeekColumn(cellColumnNumber)) {
                DayOfWeek dow = data.getColumnDayOfWeek(cellColumnNumber);
                TableView<TeachingAssistantPrototype> taTableView = (TableView)gui.getGUINode(OH_TAS_TABLE_VIEW);
                TeachingAssistantPrototype ta = taTableView.getSelectionModel().getSelectedItem();
                if (ta != null) {
                    TimeSlot timeSlot = officeHoursTableView.getSelectionModel().getSelectedItem();
                    ToggleOfficeHours_Transaction transaction = new ToggleOfficeHours_Transaction(data, timeSlot, dow, ta);
                    app.processTransaction(transaction);
                }
                else {
                    Stage window = app.getGUIModule().getWindow();
                    AppDialogsFacade.showMessageDialog(window, OH_NO_TA_SELECTED_TITLE, OH_NO_TA_SELECTED_CONTENT);
                }
            }
            int row = cell.getRow();
            cell.getTableView().refresh();
        }
    }

    public void processTypeTA() {
        app.getFoolproofModule().updateControls(OH_FOOLPROOF_SETTINGS);
    }

    public void processEditTA() {
        OfficeHoursData data = (OfficeHoursData)app.getDataComponent();
        if (data.isTASelected()) {
            TeachingAssistantPrototype taToEdit = data.getSelectedTA();
            TADialog taDialog = (TADialog)app.getGUIModule().getDialog(OH_TA_EDIT_DIALOG);
            taDialog.showEditDialog(taToEdit);
            TeachingAssistantPrototype editTA = taDialog.getEditTA();
            if (editTA != null) {
                EditTA_Transaction transaction = new EditTA_Transaction(taToEdit, editTA.getName(), editTA.getEmail(), editTA.getType());
                app.processTransaction(transaction);
            }
        }
    }
public void processEditInstructor( String newName, String newEmail, String newRoom, String newHP,String newHTML) {
        OfficeHoursData data = (OfficeHoursData)app.getDataComponent();
                        InstructorPrototype instructor = data.getinstructor();

        if(!(instructor.getName().equals(newName))){
               EditInstructor_Transaction transaction = new EditInstructor_Transaction(app,instructor, newName, 
                       instructor.getEmail(), instructor.getRoom(),instructor.getHomepage(),instructor.gethtml());
                app.processTransaction(transaction);
        }
        else if(!(instructor.getEmail().equals(newEmail))){
               EditInstructor_Transaction transaction = new EditInstructor_Transaction(app,instructor, instructor.getName(), 
                       newEmail, instructor.getRoom(),instructor.getHomepage(),instructor.gethtml());
                app.processTransaction(transaction);
        }
        else if(!(instructor.getRoom().equals(newRoom))){
               EditInstructor_Transaction transaction = new EditInstructor_Transaction(app,instructor, instructor.getName(), 
                       instructor.getEmail(), newRoom,instructor.getHomepage(),instructor.gethtml());
                app.processTransaction(transaction);
        }
        else if(!(instructor.getHomepage().equals(newHP))){
               EditInstructor_Transaction transaction = new EditInstructor_Transaction(app,instructor, instructor.getName(), 
                       instructor.getEmail(), instructor.getRoom(),newHP,instructor.gethtml());
                app.processTransaction(transaction);
        }
        else if(!(instructor.gethtml().equals(newHTML))){
               EditInstructor_Transaction transaction = new EditInstructor_Transaction(app,instructor, instructor.getName(), 
                       instructor.getEmail(), instructor.getRoom(),instructor.getHomepage(),newHTML);
                app.processTransaction(transaction);
        }
    }
public void processEditSchedule(String initType, String initDate, String initTitle, String initTopic, String initLink){
     OfficeHoursData data = (OfficeHoursData)app.getDataComponent();
                        SchedulePrototype schedule = data.getSchedule();
            if(!(schedule.getType().equals(initType))){
                EditSchedule_Transaction transaction = new EditSchedule_Transaction(app, schedule,initType,
                schedule.getDate(),schedule.getTitle(),schedule.getTopic(),schedule.getLink());
                
                                app.processTransaction(transaction);

            }
            else if((!(schedule.getDate().equals(initDate)))||schedule.getDate()==null){
                EditSchedule_Transaction transaction = new EditSchedule_Transaction(app,schedule, schedule.getType(),
                initDate,schedule.getTitle(),schedule.getTopic(),schedule.getLink());
                                app.processTransaction(transaction);

            }
            else if(!(schedule.getTitle().equals(initTitle))){
                EditSchedule_Transaction transaction = new EditSchedule_Transaction(app,schedule, schedule.getType(),
                schedule.getDate(),initTitle,schedule.getTopic(),schedule.getLink());
                                app.processTransaction(transaction);

            }
            else if(!(schedule.getTopic().equals(initTopic))){
                EditSchedule_Transaction transaction = new EditSchedule_Transaction(app,schedule, schedule.getType(),
                schedule.getDate(),schedule.getTitle(),initTopic,schedule.getLink());
                                app.processTransaction(transaction);

            }
            else if(!(schedule.getLink().equals(initLink))){
                EditSchedule_Transaction transaction = new EditSchedule_Transaction(app,schedule, schedule.getType(),
                schedule.getDate(),schedule.getTitle(),schedule.getTopic(),initLink);
                                app.processTransaction(transaction);

            }
}
public void processEditSyllabus( String newD, String newTopics, String newPre, String newOut,String newText,
        String newGC, String newGN, String newAD, String newSA) {
        OfficeHoursData data = (OfficeHoursData)app.getDataComponent();
                        SyllabusPrototype syllabus = data.getSyllabus();
        if(!(syllabus.getDescription().equals(newD))){
             EditSyllabus_Transaction transaction = new EditSyllabus_Transaction(app,syllabus, newD,
                     syllabus.getTopics(),syllabus.getPrerequisites(),
                    syllabus.getOutcomes(),syllabus.getTextbooks(),syllabus.getGradedComponents(),
                    syllabus.getGradingNotes(),syllabus.getAcademicDishonesty(),syllabus.getSpecialAssistanse());
                app.processTransaction(transaction);
        }
        else if(!(syllabus.getTopics().equals(newTopics))){
             EditSyllabus_Transaction transaction = new EditSyllabus_Transaction(app,syllabus, syllabus.getDescription(),
                     newTopics,syllabus.getPrerequisites(),
                    syllabus.getOutcomes(),syllabus.getTextbooks(),syllabus.getGradedComponents(),
                    syllabus.getGradingNotes(),syllabus.getAcademicDishonesty(),syllabus.getSpecialAssistanse());
                app.processTransaction(transaction);
        }
        else if(!(syllabus.getPrerequisites().equals(newPre))){
             EditSyllabus_Transaction transaction = new EditSyllabus_Transaction(app,syllabus, syllabus.getDescription(),
                     syllabus.getTopics(),newPre,
                    syllabus.getOutcomes(),syllabus.getTextbooks(),syllabus.getGradedComponents(),
                    syllabus.getGradingNotes(),syllabus.getAcademicDishonesty(),syllabus.getSpecialAssistanse());
                app.processTransaction(transaction);
        }
        else if(!(syllabus.getOutcomes().equals(newOut))){
             EditSyllabus_Transaction transaction = new EditSyllabus_Transaction(app,syllabus, syllabus.getDescription(),
                     syllabus.getTopics(),syllabus.getPrerequisites(),
                    newOut,syllabus.getTextbooks(),syllabus.getGradedComponents(),
                    syllabus.getGradingNotes(),syllabus.getAcademicDishonesty(),syllabus.getSpecialAssistanse());
                app.processTransaction(transaction);
        }
        else if(!(syllabus.getTextbooks().equals(newText))){
             EditSyllabus_Transaction transaction = new EditSyllabus_Transaction(app,syllabus, syllabus.getDescription(),
                     syllabus.getTopics(),syllabus.getPrerequisites(),
                    syllabus.getOutcomes(),newText,syllabus.getGradedComponents(),
                    syllabus.getGradingNotes(),syllabus.getAcademicDishonesty(),syllabus.getSpecialAssistanse());
                app.processTransaction(transaction);
        }
        else if(!(syllabus.getGradedComponents().equals(newGC))){
             EditSyllabus_Transaction transaction = new EditSyllabus_Transaction(app,syllabus, syllabus.getDescription(),
                     syllabus.getTopics(),syllabus.getPrerequisites(),
                    syllabus.getOutcomes(),syllabus.getTextbooks(),newGC,
                    syllabus.getGradingNotes(),syllabus.getAcademicDishonesty(),syllabus.getSpecialAssistanse());
                app.processTransaction(transaction);
        }
        else if(!(syllabus.getGradingNotes().equals(newGN))){
             EditSyllabus_Transaction transaction = new EditSyllabus_Transaction(app,syllabus, syllabus.getDescription(),
                     syllabus.getTopics(),syllabus.getPrerequisites(),
                    syllabus.getOutcomes(),syllabus.getTextbooks(),syllabus.getGradedComponents(),
                    newGN,syllabus.getAcademicDishonesty(),syllabus.getSpecialAssistanse());
                app.processTransaction(transaction);
        }
        else if(!(syllabus.getAcademicDishonesty().equals(newAD))){
             EditSyllabus_Transaction transaction = new EditSyllabus_Transaction(app,syllabus, syllabus.getDescription(),
                     syllabus.getTopics(),syllabus.getPrerequisites(),
                    syllabus.getOutcomes(),syllabus.getTextbooks(),syllabus.getGradedComponents(),
                    syllabus.getGradingNotes(),newAD,syllabus.getSpecialAssistanse());
                app.processTransaction(transaction);
        }
        else if(!(syllabus.getSpecialAssistanse().equals(newSA))){
             EditSyllabus_Transaction transaction = new EditSyllabus_Transaction(app,syllabus, syllabus.getDescription(),
                     syllabus.getTopics(),syllabus.getPrerequisites(),
                    syllabus.getOutcomes(),syllabus.getTextbooks(),syllabus.getGradedComponents(),
                    syllabus.getGradingNotes(),syllabus.getAcademicDishonesty(),newSA);
                app.processTransaction(transaction);
        }
    }
    public void processSelectAllTAs() {
        OfficeHoursData data = (OfficeHoursData)app.getDataComponent();
        data.selectTAs(TAType.All);
    }

    public void processSelectGradTAs() {
        OfficeHoursData data = (OfficeHoursData)app.getDataComponent();
        data.selectTAs(TAType.Graduate);
    }

    public void processSelectUndergradTAs() {
        OfficeHoursData data = (OfficeHoursData)app.getDataComponent();
        data.selectTAs(TAType.Undergraduate);
    }

    public void processSelectTA() {
        AppGUIModule gui = app.getGUIModule();
        TableView<TimeSlot> officeHoursTableView = (TableView) gui.getGUINode(OH_OFFICE_HOURS_TABLE_VIEW);
        officeHoursTableView.refresh();
    }
}