package oh.files;

import static djf.AppPropertyType.BANNER_TITLE;
import static djf.AppPropertyType.EXPORT_DIR;
import static djf.AppPropertyType.FridayCalender;
import static djf.AppPropertyType.HOME_TEXT;
import static djf.AppPropertyType.HW_TEXT;
import static djf.AppPropertyType.MondayCalender;
import static djf.AppPropertyType.SCHEDULE_TEXT;
import static djf.AppPropertyType.SYLLABUS_TEXT;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import djf.components.AppDataComponent;
import djf.components.AppFileComponent;
import djf.modules.AppGUIModule;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.DataFormat.URL;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;
import javax.json.stream.JsonParserFactory;
import oh.OfficeHoursApp;
import static oh.OfficeHoursPropertyType.AD_TEXT_AREA;
import static oh.OfficeHoursPropertyType.BOOKS_TEXT_AREA;
import static oh.OfficeHoursPropertyType.DESCRIPTION_TEXT_AREA;
import static oh.OfficeHoursPropertyType.FAVICON_IMAGE;
import static oh.OfficeHoursPropertyType.FONTSHEET;
import static oh.OfficeHoursPropertyType.GRADING_COMPONENTS_TEXT_AREA;
import static oh.OfficeHoursPropertyType.GRADING_NOTES_TEXT_AREA;
import static oh.OfficeHoursPropertyType.HOME_CB;
import static oh.OfficeHoursPropertyType.HW_CB;
import static oh.OfficeHoursPropertyType.INSTRUCTOR_EMAIL_TEXT_FIELD;
import static oh.OfficeHoursPropertyType.INSTRUCTOR_HP_TEXT_FIELD;
import static oh.OfficeHoursPropertyType.INSTRUCTOR_NAME_TEXT_FIELD;
import static oh.OfficeHoursPropertyType.INSTRUCTOR_ROOM_TEXT_FIELD;
import static oh.OfficeHoursPropertyType.INSTRUCTOR_TEXT_AREA;
import static oh.OfficeHoursPropertyType.LEFT_FOOTER;
import static oh.OfficeHoursPropertyType.NAVBAR_IMAGE;
import static oh.OfficeHoursPropertyType.NUMBER_COMBO;
import static oh.OfficeHoursPropertyType.OUTCOMES_TEXT_AREA;
import static oh.OfficeHoursPropertyType.PPREREQUISITES_TEXT_AREA;
import static oh.OfficeHoursPropertyType.RIGHT_FOOTER;
import static oh.OfficeHoursPropertyType.SCHEDULE_CB;
import static oh.OfficeHoursPropertyType.SEMESTER_COMBO;
import static oh.OfficeHoursPropertyType.SPEC_ASSIS_TEXT_AREA;
import static oh.OfficeHoursPropertyType.SUBJECT_COMBO;
import static oh.OfficeHoursPropertyType.SYLLABUS_CB;
import static oh.OfficeHoursPropertyType.TOPICS_TEXT_AREA;
import static oh.OfficeHoursPropertyType.YEAR_COMBO;
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
import properties_manager.PropertiesManager;
import org.apache.commons.io.FileUtils;

/**
 * This class serves as the file component for the TA manager app. It provides
 * all saving and loading services for the application.
 *
 * @author Richard McKenna
 */
public class OfficeHoursFiles implements AppFileComponent {

    // THIS IS THE APP ITSELF
    OfficeHoursApp app;

    // THESE ARE USED FOR IDENTIFYING JSON TYPES
    static final String JSON_GRAD_TAS = "grad_tas";
    static final String JSON_UNDERGRAD_TAS = "undergrad_tas";
    static final String JSON_NAME = "name";
    static final String JSON_EMAIL = "email";
    static final String JSON_TYPE = "type";
    static final String JSON_OFFICE_HOURS = "officeHours";
    static final String JSON_START_HOUR = "startHour";
    static final String JSON_END_HOUR = "endHour";
    static final String JSON_START_TIME = "time";
    static final String JSON_DAY_OF_WEEK = "day";
    static final String JSON_MONDAY = "monday";
    static final String JSON_TUESDAY = "tuesday";
    static final String JSON_WEDNESDAY = "wednesday";
    static final String JSON_THURSDAY = "thursday";
    static final String JSON_FRIDAY = "friday";

    static final String INSTRUCTOR = "instructor";
    static final String INSTRUCTOR_NAME = "name";
    static final String INSTRUCTOR_EMAIL = "email";
    static final String INSTRUCTOR_ROOM = "room";
    static final String INSTRUCTOR_HP = "link";
    static final String INSTRUCTOR_HTML = "hours";

    static final String SYLLABUS = "syllabus";
    static final String SDESCRIPTION = "description";
    static final String STOPICS = "topics";
    static final String SPRE = "prerequisites";
    static final String SOUT = "outcomes";
    static final String STEXTBOOKS = "textbooks";
    static final String SGC = "gradedComponents";
    static final String SGN = "gradingNote";
    static final String SAD = "academicDishonesty";
    static final String SSA = "specialAssistance";

    static final String SUBJECT = "subjects";
    static final String SEMESTERS = "semesters";
    static final String NUMBER = "numbers";
    static final String YEAR = "years";
    static final String LECTURES_SECTION = "section";
    static final String LECTURES_DAYS = "days";
    static final String LECTURES_TIMES = "time";
    static final String LECTURES_ROOM = "room";
    static final String LECTURES = "lectures";
    static final String RECITATION_SECTION = "section";
    static final String RECITATION_DT = "day_time";
    static final String RECITATION_ROOM = "location";
    static final String RECITATION_TA1 = "ta_1";
    static final String RECITATION_TA2 = "ta_2";
    static final String RECITATIONS = "recitations";
    static final String LABS = "labs";
    static final String LAB_SECTION = "section";
    static final String LAB_DT = "day_time";
    static final String LAB_ROOM = "location";
    static final String LAB_TA1 = "ta_1";
    static final String LAB_TA2 = "ta_2";

    static final String MONDAY = "monday";
    static final String FRIDAY = "friday";
    static final String DATES = "dates";
    static final String IMAGES = "logos";
    static final String FAVICON = "favicon";
    static final String NAVBAR = "navbar";
    static final String LEFTFOOTER = "bottom_left";
    static final String RIGHTFOOTER = "bottom_right";
    static final String BANNER_OBJECTS = "chosen_banner";
    static final String CHOSEN_SUBJECT = "subject";
    static final String CHOSEN_SEMESTER = "semester";
    static final String CHOSEN_NUMBER = "number";
    static final String CHOSEN_YEAR = "year";
    static final String CHOSEN_TITLE = "title";
    static final String CHOICES = "pages";
    static final String HOME_CHECKBOX = "home";
    static final String NAME = "name";
    static final String LINK = "link";
    static final String HREF = "href";
    static final String SRC = "src";
    static final String SYLLABUS_CHECKBOX = "syllabus_checkbox";
    static final String SCHEDULE_CHECKBOX = "schedule_checkbox";
    static final String HW_CHECKBOX = "hw_checkbox";
    static final String CSS_SHEET = "css_sheet";
    static final String FONTSTYLE = "font_sheet";
    static final String STARTINGMONDAYMONTH = "startingMondayMonth";
    static final String STARTINGMONDAYDAY = "startingMondayDay";
    static final String ENDINGFMONTH = "endingFridayMonth";
    static final String ENDINGFRIDAYDAY = "endingFridayDay";
    static final String MONTH = "month";
    static final String DAY = "day";
    static final String SCHEDULE = "schedule";
    static final String SCHEDULE_TYPE = "type";
    static final String SCHEDULE_DATE = "date";
    static final String SCHEDULE_TITLE = "title";
    static final String SCHEDULE_TOPIC = "topic";
    static final String SCHEDULE_LINK = "link";
    static final String HOLIDAY = "holidays";
    static final String LECTURE = "lectures";
    static final String RRECITATIONS = "recitations";
    static final String HWS = "hws";
    static final String REFERENCE = "references";

    public OfficeHoursFiles(OfficeHoursApp initApp) {
        app = initApp;
    }

    @Override
    public void loadData(AppDataComponent data, String filePath) throws IOException {
        // CLEAR THE OLD DATA OUT
        OfficeHoursData dataManager = (OfficeHoursData) data;
        dataManager.reset();
        AppGUIModule gui = app.getGUIModule();

        // LOAD THE JSON FILE WITH ALL THE DATA
        JsonObject json = loadJSONFile(filePath);
        JsonObject pageChoice = json.getJsonObject(CHOICES);
        String homeChoice = pageChoice.getString(HOME_CHECKBOX);
        String syllabusChoice = pageChoice.getString(SYLLABUS_CHECKBOX);
        String scheduleChoice = pageChoice.getString(SCHEDULE_CHECKBOX);
        String hwChoice = pageChoice.getString(HW_CHECKBOX);
        JsonObject cssStuff = json.getJsonObject(FONTSTYLE);
        String styleSheet = cssStuff.getString(CSS_SHEET);
        ComboBox cssValue = (ComboBox) gui.getGUINode(FONTSHEET);
        if (!styleSheet.equals("")) {
            dataManager.css=styleSheet;
            cssValue.setValue(styleSheet);
        } else {
            cssValue.setValue(null);
            dataManager.css="";
        }
        CheckBox homeB = (CheckBox) gui.getGUINode(HOME_CB);
        if (homeChoice.equals("true")) {
            homeB.setSelected(true);
        }

        CheckBox syallbuscb = (CheckBox) gui.getGUINode(SYLLABUS_CB);
        if (syllabusChoice.equals("true")) {
            syallbuscb.setSelected(true);
        }
        CheckBox schedulecb = (CheckBox) gui.getGUINode(SCHEDULE_CB);
        if (scheduleChoice.equals("true")) {
            schedulecb.setSelected(true);
        }
        CheckBox hwcb = (CheckBox) gui.getGUINode(HW_CB);
        if (hwChoice.equals("true")) {
            hwcb.setSelected(true);
        }

        JsonObject syllabus = json.getJsonObject(SYLLABUS);
        String Sdescription = syllabus.getString(SDESCRIPTION);
        String Stopics = syllabus.getString(STOPICS);
        String Spre = syllabus.getString(SPRE);
        String Sout = syllabus.getString(SOUT);
        String Stextbooks = syllabus.getString(STEXTBOOKS);
        String Sgc = syllabus.getString(SGC);
        String Sgn = syllabus.getString(SGN);
        String Sad = syllabus.getString(SAD);
        String Ssa = syllabus.getString(SSA);
        SyllabusPrototype realSyllabus = dataManager.getSyllabus();
        realSyllabus.setDescription(Sdescription);
        realSyllabus.setTopics(Stopics);
        realSyllabus.setReq(Spre);
        realSyllabus.setOutcomes(Sout);
        realSyllabus.setBooks(Stextbooks);
        realSyllabus.setGC(Sgc);
        realSyllabus.setGN(Sgn);
        realSyllabus.setAD(Sad);
        realSyllabus.setSA(Ssa);
        TextArea description = (TextArea) gui.getGUINode(DESCRIPTION_TEXT_AREA);
        description.setText(Sdescription);
        TextArea topics = (TextArea) gui.getGUINode(TOPICS_TEXT_AREA);
        topics.setText(Stopics);
        TextArea preReq = (TextArea) gui.getGUINode(PPREREQUISITES_TEXT_AREA);
        preReq.setText(Spre);
        TextArea out = (TextArea) gui.getGUINode(OUTCOMES_TEXT_AREA);
        out.setText(Sout);
        TextArea books = (TextArea) gui.getGUINode(BOOKS_TEXT_AREA);
        books.setText(Stextbooks);
        TextArea GC = (TextArea) gui.getGUINode(GRADING_COMPONENTS_TEXT_AREA);
        GC.setText(Sgc);
        TextArea GN = (TextArea) gui.getGUINode(GRADING_NOTES_TEXT_AREA);
        GN.setText(Sgn);
        TextArea SAD = (TextArea) gui.getGUINode(AD_TEXT_AREA);
        SAD.setText(Sad);
        TextArea SSA = (TextArea) gui.getGUINode(SPEC_ASSIS_TEXT_AREA);
        SSA.setText(Ssa);
        JsonObject banner = json.getJsonObject(BANNER_OBJECTS);
        String chosensubject = banner.getString(CHOSEN_SUBJECT);
        String chosensemester = banner.getString(CHOSEN_SEMESTER);
        String chosennumber = banner.getString(CHOSEN_NUMBER);
        String chosenyear = banner.getString(CHOSEN_YEAR);
        String chosenTitle = banner.getString(CHOSEN_TITLE);
        TextField b = (TextField) gui.getGUINode(BANNER_TITLE);
        b.setText(chosenTitle);
        dataManager.pageTitle=chosenTitle;
        ComboBox asd = (ComboBox) gui.getGUINode(SEMESTER_COMBO);
        asd.setValue(chosensemester);
        dataManager.pageSemester=chosensemester;
        ComboBox dfg = (ComboBox) gui.getGUINode(NUMBER_COMBO);
        dfg.setValue(chosennumber);
        dataManager.pageBNumber=chosennumber;
        ComboBox ghj = (ComboBox) gui.getGUINode(YEAR_COMBO);
        ghj.setValue(chosenyear);
        dataManager.pageYear=chosenyear;
        ComboBox kkkk = (ComboBox) gui.getGUINode(SUBJECT_COMBO);
        kkkk.setValue(chosensubject);
        dataManager.pageSubject=chosensubject;
        JsonObject instructor = json.getJsonObject(INSTRUCTOR);
        String instructor_name = instructor.getString(INSTRUCTOR_NAME);
        String instructor_email = instructor.getString(INSTRUCTOR_EMAIL);
        String instructor_room = instructor.getString(INSTRUCTOR_ROOM);
        String instructor_HP = instructor.getString(INSTRUCTOR_HP);
        String instructor_HTML = instructor.getString(INSTRUCTOR_HTML);

        JsonObject images = json.getJsonObject(IMAGES);
        String favicon = images.getString(FAVICON);
        String navbarImage = images.getString(NAVBAR);
        String rightfooter = images.getString(RIGHTFOOTER);
        String leftfooter = images.getString(LEFTFOOTER);
        //  String f = relativeToAbsolute(favicon, "C:\\Users");
        //  String n = relativeToAbsolute(navbar, "C:\\Users");
        //   String r = relativeToAbsolute(rightfooter, "C:\\Users");
        // String l = relativeToAbsolute(leftfooter, "C:\\Users");
        if (!favicon.equals("./images//StonybrookSimpleLogo.png")) {
            dataManager.favicon = favicon;
            Image faviconImage = new Image("file:/" + favicon);
            ImageView simpleImage = (ImageView) gui.getGUINode(FAVICON_IMAGE);
            simpleImage.setImage(faviconImage);
        }
        if (!navbarImage.equals("./images//SBUDarkRedShieldLogo.png")) {
            dataManager.navbar = navbarImage;
            Image navBar = new Image("file:/" + navbarImage);
            ImageView s = (ImageView) gui.getGUINode(NAVBAR_IMAGE);
            s.setImage(navBar);
        }
        if (!leftfooter.equals("./images//SBUWhiteShieldLogo.png")) {
            dataManager.leftFooter = leftfooter;
            Image leftImage = new Image("file:/" + leftfooter);
            ImageView left = (ImageView) gui.getGUINode(LEFT_FOOTER);
            left.setImage(leftImage);
        }
        if (!rightfooter.equals("./images//SBUCSLogo.png")) {
            dataManager.rightFooter = rightfooter;
            Image rightImage = new Image("file:/" + rightfooter);
            ImageView right = (ImageView) gui.getGUINode(RIGHT_FOOTER);
            right.setImage(rightImage);
        }
        DatePicker mondayCalender = (DatePicker) gui.getGUINode(MondayCalender);
        DatePicker fridayCalender = (DatePicker) gui.getGUINode(FridayCalender);
        JsonObject dates = json.getJsonObject(DATES);
        String monday = dates.getString(MONDAY);
        String friday = dates.getString(FRIDAY);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate mondayDate;
        if (monday.equals("")) {
            mondayDate = null;
        } else {
            mondayDate = LocalDate.parse(monday, formatter);
        }
        LocalDate fridayDate;
        if (friday.equals("")) {
            fridayDate = null;
        } else {
            fridayDate = LocalDate.parse(friday, formatter);
        }
        mondayCalender.setValue(mondayDate);
        fridayCalender.setValue(fridayDate);
        InstructorPrototype instructorB = dataManager.getinstructor();
        instructorB.setName(instructor_name);
        instructorB.setEmail(instructor_email);
        instructorB.setRoom(instructor_room);
        instructorB.setHomePage(instructor_HP);
        instructorB.setHTML(instructor_HTML);
        TextField nameStuff = (TextField) gui.getGUINode(INSTRUCTOR_NAME_TEXT_FIELD);
        nameStuff.setText(instructor_name);
        TextField emailStuff = (TextField) gui.getGUINode(INSTRUCTOR_EMAIL_TEXT_FIELD);
        emailStuff.setText(instructor_email);
        TextField roomStuff = (TextField) gui.getGUINode(INSTRUCTOR_ROOM_TEXT_FIELD);
        roomStuff.setText(instructor_room);
        TextField hpStuff = (TextField) gui.getGUINode(INSTRUCTOR_HP_TEXT_FIELD);
        hpStuff.setText(instructor_HP);
        TextArea html = (TextArea) gui.getGUINode(INSTRUCTOR_TEXT_AREA);
        html.setText(instructor_HTML);

        // LOAD THE START AND END HOURS
        String startHour = json.getString(JSON_START_HOUR);
        String endHour = json.getString(JSON_END_HOUR);
        dataManager.initHours(startHour, endHour);

        // LOAD ALL THE GRAD TAs
        loadTAs(dataManager, json, JSON_GRAD_TAS);
        loadTAs(dataManager, json, JSON_UNDERGRAD_TAS);

        // AND THEN ALL THE OFFICE HOURS
        JsonArray jsonOfficeHoursArray = json.getJsonArray(JSON_OFFICE_HOURS);
        for (int i = 0; i < jsonOfficeHoursArray.size(); i++) {
            JsonObject jsonOfficeHours = jsonOfficeHoursArray.getJsonObject(i);
            String startTime = jsonOfficeHours.getString(JSON_START_TIME);
            DayOfWeek dow = DayOfWeek.valueOf(jsonOfficeHours.getString(JSON_DAY_OF_WEEK));
            String name = jsonOfficeHours.getString(JSON_NAME);
            TeachingAssistantPrototype ta = dataManager.getTAWithName(name);
            TimeSlot timeSlot = dataManager.getTimeSlot(startTime);
            timeSlot.toggleTA(dow, ta);
        }
        
        JsonArray subject = json.getJsonArray(SUBJECT);
        ComboBox subjectArea = (ComboBox) gui.getGUINode(SUBJECT_COMBO);
        for (int i = 0; i < subject.size(); i++) {
            
            JsonObject jsonOfficeHours = subject.getJsonObject(i);
            String subjectObject = jsonOfficeHours.getString(i + "");
            if (!(subjectArea.getItems().contains(subjectObject))) {
                subjectArea.getItems().addAll(subjectObject);
            }
        }

        JsonArray semester = json.getJsonArray(SEMESTERS);
        ComboBox semesterArea = (ComboBox) gui.getGUINode(SEMESTER_COMBO);
        for (int i = 0; i < semester.size(); i++) {
            JsonObject jsonOfficeHours = semester.getJsonObject(i);
            String subjectObject = jsonOfficeHours.getString(i + "");
            if (!(semesterArea.getItems().contains(subjectObject))) {
                semesterArea.getItems().addAll(subjectObject);
            }
        }

        JsonArray number = json.getJsonArray(NUMBER);
        ComboBox numberArea = (ComboBox) gui.getGUINode(NUMBER_COMBO);
        for (int i = 0; i < number.size(); i++) {
            JsonObject jsonOfficeHours = number.getJsonObject(i);
            String subjectObject = jsonOfficeHours.getString(i + "");
            if (!(numberArea.getItems().contains(subjectObject))) {
                numberArea.getItems().addAll(subjectObject);
            }
        }

        JsonArray year = json.getJsonArray(YEAR);
        ComboBox yearArea = (ComboBox) gui.getGUINode(YEAR_COMBO);
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int nextYear = currentYear + 1;
        for (int i = 0; i < year.size(); i++) {
            JsonObject jsonOfficeHours = year.getJsonObject(i);
            String subjectObject = jsonOfficeHours.getString(i + "");
            if (!(yearArea.getItems().contains(subjectObject))) {
                yearArea.getItems().addAll(subjectObject);
            }
        }

        JsonArray lecuturesArray = json.getJsonArray(LECTURES);
        for (int i = 0; i < lecuturesArray.size(); i++) {
            JsonObject lectureObject = lecuturesArray.getJsonObject(i);
            String section = lectureObject.getString(LECTURES_SECTION);
            String days = lectureObject.getString(LECTURES_DAYS);
            String times = lectureObject.getString(LECTURES_TIMES);
            String room = lectureObject.getString(LECTURES_ROOM);
            LecturesPrototype sample = new LecturesPrototype(section, days, times, room);

            dataManager.addLecture(sample);
        }

        JsonArray recitationArray = json.getJsonArray(RECITATIONS);
        for (int i = 0; i < recitationArray.size(); i++) {
            JsonObject recitationObject = recitationArray.getJsonObject(i);
            String section = recitationObject.getString(RECITATION_SECTION);
            String dt = recitationObject.getString(RECITATION_DT);
            String room = recitationObject.getString(RECITATION_ROOM);
            String ta1 = recitationObject.getString(RECITATION_TA1);
            String ta2 = recitationObject.getString(RECITATION_TA2);
            Recitation_Lab_Prototype sample = new Recitation_Lab_Prototype(section, dt, room, ta1, ta2);

            dataManager.addRecitation(sample);
        }

        JsonArray labArray = json.getJsonArray(LABS);
        for (int i = 0; i < labArray.size(); i++) {
            JsonObject labObject = labArray.getJsonObject(i);
            String section = labObject.getString(LAB_SECTION);
            String dt = labObject.getString(LAB_DT);
            String room = labObject.getString(LAB_ROOM);
            String ta1 = labObject.getString(LAB_TA1);
            String ta2 = labObject.getString(LAB_TA2);
            Recitation_Lab_Prototype sample = new Recitation_Lab_Prototype(section, dt, room, ta1, ta2);

            dataManager.addLab(sample);
        }
        JsonArray scheduleArray = json.getJsonArray(SCHEDULE);
        for (int i = 0; i < scheduleArray.size(); i++) {
            JsonObject scheduleObject = scheduleArray.getJsonObject(i);
            String type = scheduleObject.getString(SCHEDULE_TYPE);
            String date = scheduleObject.getString(SCHEDULE_DATE);
            String title = scheduleObject.getString(SCHEDULE_TITLE);
            String topic = scheduleObject.getString(SCHEDULE_TOPIC);
            String link = scheduleObject.getString(SCHEDULE_LINK);
            SchedulePrototype sample = new SchedulePrototype(type, date, title, topic, link);

            dataManager.addSchedule(sample);
        }

    }

    private void loadTAs(OfficeHoursData data, JsonObject json, String tas) {
        JsonArray jsonTAArray = json.getJsonArray(tas);
        for (int i = 0; i < jsonTAArray.size(); i++) {
            JsonObject jsonTA = jsonTAArray.getJsonObject(i);
            String name = jsonTA.getString(JSON_NAME);
            String email = jsonTA.getString(JSON_EMAIL);
            TAType type = TAType.valueOf(jsonTA.getString(JSON_TYPE));
            TeachingAssistantPrototype ta = new TeachingAssistantPrototype(name, email, type);
            data.addTA(ta);
        }
    }

    // HELPER METHOD FOR LOADING DATA FROM A JSON FORMAT
    private JsonObject loadJSONFile(String jsonFilePath) throws IOException {
        InputStream is = new FileInputStream(jsonFilePath);
        JsonReader jsonReader = Json.createReader(is);
        JsonObject json = jsonReader.readObject();
        jsonReader.close();
        is.close();
        return json;
    }
 public JsonArray convertToJSONArray(String asd) {
        if (asd.isEmpty()) {
            asd = "[]";
        }
        JsonReader jsonReader = Json.createReader(new StringReader(asd));
        JsonArray arr = jsonReader.readArray();
        return arr;
    }
    public void saveOHData(OfficeHoursData dataManager) throws IOException {
        AppGUIModule gui = app.getGUIModule();
        InstructorPrototype instructor = dataManager.getinstructor();
        JsonObject instructorJSON = Json.createObjectBuilder()
                .add(INSTRUCTOR_NAME, instructor.getName())
                .add(INSTRUCTOR_EMAIL, instructor.getEmail())
                .add(INSTRUCTOR_ROOM, instructor.getRoom())
                .add(INSTRUCTOR_HP, instructor.getHomepage())
                .add(INSTRUCTOR_HTML, convertToJSONArray(instructor.gethtml())).build();
        JsonArrayBuilder gradTAsArrayBuilder = Json.createArrayBuilder();
        JsonArrayBuilder undergradTAsArrayBuilder = Json.createArrayBuilder();
        Iterator<TeachingAssistantPrototype> tasIterator = dataManager.teachingAssistantsIterator();
        while (tasIterator.hasNext()) {
            TeachingAssistantPrototype ta = tasIterator.next();
            JsonObject taJson = Json.createObjectBuilder()
                    .add(JSON_NAME, ta.getName())
                    .add(JSON_EMAIL, ta.getEmail())
                    .add(JSON_TYPE, ta.getType().toString()).build();

            if (ta.getType().equals(TAType.Graduate.toString())) {
                gradTAsArrayBuilder.add(taJson);
            } else {
                undergradTAsArrayBuilder.add(taJson);
            }
        }
        JsonArray gradTAsArray = gradTAsArrayBuilder.build();
        JsonArray undergradTAsArray = undergradTAsArrayBuilder.build();

        // NOW BUILD THE OFFICE HOURS JSON OBJCTS TO SAVE
        JsonArrayBuilder officeHoursArrayBuilder = Json.createArrayBuilder();
        Iterator<TimeSlot> timeSlotsIterator = dataManager.officeHoursIterator();
        while (timeSlotsIterator.hasNext()) {
            TimeSlot timeSlot = timeSlotsIterator.next();
            for (int i = 0; i < DayOfWeek.values().length; i++) {
                DayOfWeek dow = DayOfWeek.values()[i];
                tasIterator = timeSlot.getTAsIterator(dow);
                while (tasIterator.hasNext()) {
                    TeachingAssistantPrototype ta = tasIterator.next();
                    JsonObject tsJson = Json.createObjectBuilder()
                            .add(JSON_START_TIME, timeSlot.getStartTime().replace(":", "_"))
                            .add(JSON_DAY_OF_WEEK, dow.toString())
                            .add(JSON_NAME, ta.getName()).build();
                    officeHoursArrayBuilder.add(tsJson);
                }
            }
        }
        JsonArray officeHoursArray = officeHoursArrayBuilder.build();
        JsonObject dataManagerJSO = Json.createObjectBuilder()
                .add(JSON_START_HOUR, "" + dataManager.getStartHour())
                .add(JSON_END_HOUR, "" + dataManager.getEndHour())
                .add(INSTRUCTOR, instructorJSON)
                .add(JSON_GRAD_TAS, gradTAsArray)
                .add(JSON_UNDERGRAD_TAS, undergradTAsArray)
                .add(JSON_OFFICE_HOURS, officeHoursArray)
                .build();

        // AND NOW OUTPUT IT TO A JSON FILE WITH PRETTY PRINTING
        Map<String, Object> properties = new HashMap<>(1);
        properties.put(JsonGenerator.PRETTY_PRINTING, true);
        JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
        StringWriter sw = new StringWriter();
        JsonWriter jsonWriter = writerFactory.createWriter(sw);
        jsonWriter.writeObject(dataManagerJSO);
        jsonWriter.close();
        sw.close();
        // INIT THE WRITER
        Text sd = (Text) gui.getGUINode(EXPORT_DIR);
        String dir = sd.getText();

        OutputStream os = new FileOutputStream(dir + "\\js\\OfficeHoursData.json");
        JsonWriter jsonFileWriter = Json.createWriter(os);
        jsonFileWriter.writeObject(dataManagerJSO);
        String prettyPrinted = sw.toString();
        PrintWriter pw = new PrintWriter(dir + "\\js\\OfficeHoursData.json");
        pw.write(prettyPrinted);
        pw.close();
        jsonFileWriter.close();

    }

   

    public void savePageData(OfficeHoursData dataManager) throws IOException {
        AppGUIModule gui = app.getGUIModule();
        //OfficeHoursData dataManager = (OfficeHoursData) data;
        ComboBox subjectArea = (ComboBox) gui.getGUINode(SUBJECT_COMBO);
        ComboBox semester = (ComboBox) gui.getGUINode(SEMESTER_COMBO);
        ComboBox numberBox = (ComboBox) gui.getGUINode(NUMBER_COMBO);
        TextField titleField = (TextField) gui.getGUINode(BANNER_TITLE);
        ComboBox yearBox = (ComboBox) gui.getGUINode(YEAR_COMBO);

        String favicon = absoluteToRelative(dataManager.favicon);
        String navbar = absoluteToRelative(dataManager.navbar);
        String left = absoluteToRelative(dataManager.leftFooter);
        String right = absoluteToRelative(dataManager.rightFooter);

//        String favicon = dataManager.favicon;
//        String navbar = dataManager.navbar;
//        String left = dataManager.leftFooter;
//        String right = dataManager.rightFooter;
        JsonObject a = Json.createObjectBuilder().add(HREF, favicon).build();
        JsonObject b = Json.createObjectBuilder().add(HREF, "http://www.stonybrook.edu").add(SRC, navbar).build();
        JsonObject c = Json.createObjectBuilder().add(HREF, "http://www.cs.stonybrook.edu").add(SRC, left).build();
        JsonObject d = Json.createObjectBuilder().add(HREF, "http://www.cs.stonybrook.edu").add(SRC, right).build();

        JsonObject images = Json.createObjectBuilder().add(FAVICON, a).add(NAVBAR, b).add(LEFTFOOTER, c).add(RIGHTFOOTER, d).build();
        InstructorPrototype instructor = dataManager.getinstructor();
        //  JsonArrayBuilder instructorhtml= Json.createArrayBuilder().add(instructor.gethtml().replace("\"", "").replace("\n",""));
        JsonArray wer = convertToJSONArray(instructor.gethtml());
        JsonObject instructorJSON = Json.createObjectBuilder()
                .add(INSTRUCTOR_NAME, instructor.getName())
                .add(INSTRUCTOR_EMAIL, instructor.getEmail())
                .add(INSTRUCTOR_ROOM, instructor.getRoom())
                .add(INSTRUCTOR_HP, instructor.getHomepage())
                .add(INSTRUCTOR_HTML, convertToJSONArray(instructor.gethtml())).build();
        CheckBox homeB = (CheckBox) gui.getGUINode(HOME_CB);
        String homeSelected = "";
        if (homeB.isSelected() == true) {
            homeSelected = "index.html";
        }

        CheckBox syallbuscb = (CheckBox) gui.getGUINode(SYLLABUS_CB);
        String syallabusSelected = "";
        if (syallbuscb.isSelected() == true) {
            syallabusSelected = "syllabus.html";
        }
        CheckBox schedulecb = (CheckBox) gui.getGUINode(SCHEDULE_CB);
        String scheduleSelected = "";
        if (schedulecb.isSelected() == true) {
            scheduleSelected = "schedule.html";
        }
        CheckBox hwcb = (CheckBox) gui.getGUINode(HW_CB);
        String hwSelected = "";
        if (hwcb.isSelected() == true) {
            hwSelected = "hws.html";
        }

        JsonArrayBuilder choice_box = Json.createArrayBuilder();
        JsonObject homeBox = Json.createObjectBuilder().add(NAME, "Home").add(LINK, homeSelected).build();
        choice_box.add(homeBox);
        JsonObject sy = Json.createObjectBuilder().add(NAME, "Syllabus").add(LINK, syallabusSelected).build();
        JsonObject sch = Json.createObjectBuilder().add(NAME, "Schedule").add(LINK, scheduleSelected).build();
        JsonObject hw = Json.createObjectBuilder().add(NAME, "HWs").add(LINK, hwSelected).build();
        choice_box.add(sy);
        choice_box.add(sch);
        choice_box.add(hw);

        JsonArray choices = choice_box.build();

// THEN PUT IT ALL TOGETHER IN A JsonObject
        JsonObject dataManagerJSO = Json.createObjectBuilder()
                .add(CHOSEN_SUBJECT, subjectArea.getValue().toString())
                .add(CHOSEN_NUMBER, numberBox.getValue().toString())
                .add(CHOSEN_SEMESTER, semester.getValue().toString())
                .add(CHOSEN_YEAR, yearBox.getValue().toString())
                .add(CHOSEN_TITLE, titleField.getText())
                .add(IMAGES, images)
                .add(INSTRUCTOR, instructorJSON)
                .add(CHOICES, choices)
                .build();

        // AND NOW OUTPUT IT TO A JSON FILE WITH PRETTY PRINTING
        Map<String, Object> properties = new HashMap<>(1);
        properties.put(JsonGenerator.PRETTY_PRINTING, true);
        JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
        StringWriter sw = new StringWriter();
        JsonWriter jsonWriter = writerFactory.createWriter(sw);
        jsonWriter.writeObject(dataManagerJSO);
        jsonWriter.close();
        Text sd = (Text) gui.getGUINode(EXPORT_DIR);
        String dir = sd.getText();

        OutputStream os = new FileOutputStream(dir + "\\js\\PageData.json");
        JsonWriter jsonFileWriter = Json.createWriter(os);
        jsonFileWriter.writeObject(dataManagerJSO);
        String prettyPrinted = sw.toString();
        PrintWriter pw = new PrintWriter(dir + "\\js\\PageData.json");
        pw.write(prettyPrinted);
        pw.close();
        jsonFileWriter.close();

    }

    public void saveScheduleData(OfficeHoursData dataManager) throws IOException {
        AppGUIModule gui = app.getGUIModule();
        DatePicker mondayCalender = (DatePicker) gui.getGUINode(MondayCalender);
        String monday;
        try {
            monday = mondayCalender.getValue().toString();
        } catch (NullPointerException e) {
            monday = "";
        }
        DatePicker fridayCalender = (DatePicker) gui.getGUINode(FridayCalender);
        String friday;
        try {
            friday = fridayCalender.getValue().toString();
        } catch (NullPointerException easterEgg) {
            friday = "";
        }
        ObservableList<SchedulePrototype> scheduleList = dataManager.getList();

        JsonArrayBuilder holidays = Json.createArrayBuilder();
        JsonArrayBuilder hws = Json.createArrayBuilder();
        JsonArrayBuilder recitations = Json.createArrayBuilder();
        JsonArrayBuilder lectures = Json.createArrayBuilder();
        JsonArrayBuilder reference = Json.createArrayBuilder();

        for (SchedulePrototype item : scheduleList) {
            if (item.getType().equals("Holiday")) {
                JsonObject jInnerObject = Json.createObjectBuilder()
                        .add(MONTH, item.getDate().substring(5, 7))
                        .add(DAY, item.getDate().substring(8))
                        .add(SCHEDULE_TITLE, item.getTitle())
                        .add(SCHEDULE_TOPIC, item.getTopic())
                        .add(SCHEDULE_LINK, item.getLink())
                        .build();
                holidays.add(jInnerObject);
            } else if (item.getType().equals("Lecture")) {
                JsonObject jInnerObject = Json.createObjectBuilder()
                        .add(MONTH, item.getDate().substring(5, 7))
                        .add(DAY, item.getDate().substring(8))
                        .add(SCHEDULE_TITLE, item.getTitle())
                        .add(SCHEDULE_TOPIC, item.getTopic())
                        .add(SCHEDULE_LINK, item.getLink())
                        .build();
                lectures.add(jInnerObject);
            } else if (item.getType().equals("HW")) {
                JsonObject jInnerObject = Json.createObjectBuilder()
                        .add(MONTH, item.getDate().substring(5, 7))
                        .add(DAY, item.getDate().substring(8))
                        .add(SCHEDULE_TITLE, item.getTitle())
                        .add(SCHEDULE_TOPIC, item.getTopic())
                        .add(SCHEDULE_LINK, item.getLink())
                        .build();
                hws.add(jInnerObject);
            } else if (item.getType().equals("Recitation")) {
                JsonObject jInnerObject = Json.createObjectBuilder()
                        .add(MONTH, item.getDate().substring(5, 7))
                        .add(DAY, item.getDate().substring(8))
                        .add(SCHEDULE_TITLE, item.getTitle())
                        .add(SCHEDULE_TOPIC, item.getTopic())
                        .add(SCHEDULE_LINK, item.getLink())
                        .build();
                recitations.add(jInnerObject);
            } else if (item.getType().equals("Reference")) {
                JsonObject jInnerObject = Json.createObjectBuilder()
                        .add(MONTH, item.getDate().substring(5, 7))
                        .add(DAY, item.getDate().substring(8))
                        .add(SCHEDULE_TITLE, item.getTitle())
                        .add(SCHEDULE_TOPIC, item.getTopic())
                        .add(SCHEDULE_LINK, item.getLink())
                        .build();
                reference.add(jInnerObject);
            }
        }

        JsonArray holidayBuild = holidays.build();
        JsonArray hwsBuild = hws.build();
        JsonArray recitationsBuild = recitations.build();
        JsonArray lecutresBuild = lectures.build();
        JsonArray referenceBuild = reference.build();
        JsonObject dataManagerJSO;
        if (monday.equals("") && friday.equals("")) {
            dataManagerJSO = Json.createObjectBuilder()
                    .add(STARTINGMONDAYMONTH, "")
                    .add(STARTINGMONDAYDAY, "")
                    .add(ENDINGFMONTH, "")
                    .add(ENDINGFRIDAYDAY, "")
                    .add(HOLIDAY, holidayBuild)
                    .add(LECTURE, lecutresBuild)
                    .add(REFERENCE, referenceBuild)
                    .add(RRECITATIONS, recitationsBuild)
                    .add(HWS, hwsBuild)
                    .build();
        } else if (friday.equals("")) {
            dataManagerJSO = Json.createObjectBuilder()
                    .add(STARTINGMONDAYMONTH, monday.substring(5, 7))
                    .add(STARTINGMONDAYDAY, monday.substring(8))
                    .add(ENDINGFMONTH, "")
                    .add(ENDINGFRIDAYDAY, "")
                    .add(HOLIDAY, holidayBuild)
                    .add(LECTURE, lecutresBuild)
                    .add(REFERENCE, referenceBuild)
                    .add(RRECITATIONS, recitationsBuild)
                    .add(HWS, hwsBuild)
                    .build();
        } else if (monday.equals("")) {
            dataManagerJSO = Json.createObjectBuilder()
                    .add(STARTINGMONDAYMONTH, "")
                    .add(STARTINGMONDAYDAY, "")
                    .add(ENDINGFMONTH, friday.substring(5, 7))
                    .add(ENDINGFRIDAYDAY, friday.substring(8))
                    .add(HOLIDAY, holidayBuild)
                    .add(LECTURE, lecutresBuild)
                    .add(REFERENCE, referenceBuild)
                    .add(RRECITATIONS, recitationsBuild)
                    .add(HWS, hwsBuild)
                    .build();
        } else {
            dataManagerJSO = Json.createObjectBuilder()
                    .add(STARTINGMONDAYMONTH, monday.substring(5, 7))
                    .add(STARTINGMONDAYDAY, monday.substring(8))
                    .add(ENDINGFMONTH, friday.substring(5, 7))
                    .add(ENDINGFRIDAYDAY, friday.substring(8))
                    .add(HOLIDAY, holidayBuild)
                    .add(LECTURE, lecutresBuild)
                    .add(REFERENCE, referenceBuild)
                    .add(RRECITATIONS, recitationsBuild)
                    .add(HWS, hwsBuild)
                    .build();
        }

        Map<String, Object> properties = new HashMap<>(1);
        properties.put(JsonGenerator.PRETTY_PRINTING, true);
        JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
        StringWriter sw = new StringWriter();
        JsonWriter jsonWriter = writerFactory.createWriter(sw);
        jsonWriter.writeObject(dataManagerJSO);
        jsonWriter.close();
        Text sd = (Text) gui.getGUINode(EXPORT_DIR);
        String dir = sd.getText();

        OutputStream os = new FileOutputStream(dir + "\\js\\ScheduleData.json");
        JsonWriter jsonFileWriter = Json.createWriter(os);
        jsonFileWriter.writeObject(dataManagerJSO);
        String prettyPrinted = sw.toString();
        PrintWriter pw = new PrintWriter(dir + "\\js\\ScheduleData.json");
        pw.write(prettyPrinted);
        pw.close();
        jsonFileWriter.close();

    }

    public void saveSectionsData(OfficeHoursData dataManager) throws IOException {
        AppGUIModule gui = app.getGUIModule();
        ObservableList<LecturesPrototype> lectures = dataManager.getLecturesList();
        ObservableList<Recitation_Lab_Prototype> recitations = dataManager.getRecitationsList();
        ObservableList<Recitation_Lab_Prototype> labs = dataManager.getLabsList();
        JsonArrayBuilder lecturesArray = Json.createArrayBuilder();

        for (LecturesPrototype item : lectures) {
            JsonObject jInnerObject = Json.createObjectBuilder()
                    .add(LECTURES_SECTION, item.getSection())
                    .add(LECTURES_DAYS, item.getDays())
                    .add(LECTURES_TIMES, item.getTimes())
                    .add(LECTURES_ROOM, item.getRoom()).build();
            lecturesArray.add(jInnerObject);
        }
        JsonArray lecturesBuild = lecturesArray.build();

        JsonArrayBuilder recitationArray = Json.createArrayBuilder();

        for (Recitation_Lab_Prototype item : recitations) {
            JsonObject jInnerObject = Json.createObjectBuilder()
                    .add(RECITATION_SECTION, item.getSection())
                    .add(RECITATION_DT, item.getDaystime())
                    .add(RECITATION_ROOM, item.getRoom())
                    .add(RECITATION_TA1, item.getTa1())
                    .add(RECITATION_TA2, item.getTa2()).build();
            recitationArray.add(jInnerObject);
        }
        JsonArray recitationBuild = recitationArray.build();

        JsonArrayBuilder labsArray = Json.createArrayBuilder();

        for (Recitation_Lab_Prototype item : labs) {
            JsonObject jInnerObject = Json.createObjectBuilder()
                    .add(LAB_SECTION, item.getSection())
                    .add(LAB_DT, item.getDaystime())
                    .add(LAB_ROOM, item.getRoom())
                    .add(LAB_TA1, item.getTa1())
                    .add(LAB_TA2, item.getTa2()).build();
            labsArray.add(jInnerObject);
        }
        JsonArray labsBuild = labsArray.build();

        JsonObject dataManagerJSO = Json.createObjectBuilder()
                .add(LECTURES, lecturesBuild)
                .add(LABS, labsBuild)
                .add(RECITATIONS, recitationBuild)
                .build();
        Map<String, Object> properties = new HashMap<>(1);
        properties.put(JsonGenerator.PRETTY_PRINTING, true);
        JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
        StringWriter sw = new StringWriter();
        JsonWriter jsonWriter = writerFactory.createWriter(sw);
        jsonWriter.writeObject(dataManagerJSO);
        jsonWriter.close();
        Text sd = (Text) gui.getGUINode(EXPORT_DIR);
        String dir = sd.getText();

        OutputStream os = new FileOutputStream(dir + "\\js\\SectionsData.json");
        JsonWriter jsonFileWriter = Json.createWriter(os);
        jsonFileWriter.writeObject(dataManagerJSO);
        String prettyPrinted = sw.toString();
        PrintWriter pw = new PrintWriter(dir + "\\js\\SectionsData.json");
        pw.write(prettyPrinted);
        jsonFileWriter.close();
        pw.close();
    }

    public JsonObject convertToJSONObject(String asd) {
        if (asd.isEmpty()) {
            asd = "{}";
        }
        JsonReader jsonReader = Json.createReader(new StringReader(asd));
        JsonObject arr = jsonReader.readObject();
        return arr;
    }

    public void saveSyllabusData(OfficeHoursData dataManager) throws IOException {
        AppGUIModule gui = app.getGUIModule();
        SyllabusPrototype syllabus = dataManager.getSyllabus();
        TextArea topicsArea = ((TextArea) gui.getGUINode(TOPICS_TEXT_AREA));
        TextArea booksArea = ((TextArea) gui.getGUINode(BOOKS_TEXT_AREA));
        JsonObject dataManagerJSO = Json.createObjectBuilder()
                .add(SDESCRIPTION, syllabus.getDescription())
                .add(STOPICS, convertToJSONArray(topicsArea.getText()))
                .add(SPRE, syllabus.getPrerequisites())
                .add(SOUT, convertToJSONArray(syllabus.getOutcomes()))
                .add(STEXTBOOKS, convertToJSONArray(booksArea.getText()))
                .add(SGC, convertToJSONArray(syllabus.getGradedComponents()))
                .add(SGN, syllabus.getGradingNotes())
                .add(SAD, (syllabus.getAcademicDishonesty()))
                .add(SSA, (syllabus.getSpecialAssistanse())).build();

        Map<String, Object> properties = new HashMap<>(1);
        properties.put(JsonGenerator.PRETTY_PRINTING, true);
        JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
        StringWriter sw = new StringWriter();
        JsonWriter jsonWriter = writerFactory.createWriter(sw);
        jsonWriter.writeObject(dataManagerJSO);
        jsonWriter.close();
        Text sd = (Text) gui.getGUINode(EXPORT_DIR);
        String dir = sd.getText();

        OutputStream os = new FileOutputStream(dir + "\\js\\SyllabusData.json");
        JsonWriter jsonFileWriter = Json.createWriter(os);
        jsonFileWriter.writeObject(dataManagerJSO);
        String prettyPrinted = sw.toString();
        PrintWriter pw = new PrintWriter(dir + "\\js\\SyllabusData.json");
        pw.write(prettyPrinted);
        pw.close();
        jsonFileWriter.close();
        sw.close();
        
    }

    @Override
    public void saveData(AppDataComponent data, String filePath) throws IOException {
        // GET THE DATA
        OfficeHoursData dataManager = (OfficeHoursData) data;
        SchedulePrototype schedule = dataManager.getSchedule();
        InstructorPrototype instructor = dataManager.getinstructor();
        SyllabusPrototype syllabus = dataManager.getSyllabus();
        JsonObject instructorJSON = Json.createObjectBuilder()
                .add(INSTRUCTOR_NAME, instructor.getName())
                .add(INSTRUCTOR_EMAIL, instructor.getEmail())
                .add(INSTRUCTOR_ROOM, instructor.getRoom())
                .add(INSTRUCTOR_HP, instructor.getHomepage())
                .add(INSTRUCTOR_HTML, instructor.gethtml()).build();

        String favicon = dataManager.favicon;
        String navbar = dataManager.navbar;
        String left = dataManager.leftFooter;
        String right = dataManager.rightFooter;
        JsonObject images = Json.createObjectBuilder().add(FAVICON, favicon).add(NAVBAR, navbar).add(LEFTFOOTER, left).add(RIGHTFOOTER, right).build();
        JsonArrayBuilder subjects = Json.createArrayBuilder();
        AppGUIModule gui = app.getGUIModule();
        DatePicker mondayCalender = (DatePicker) gui.getGUINode(MondayCalender);
        String monday;
        try {
            monday = mondayCalender.getValue().toString();
        } catch (NullPointerException e) {
            monday = "";
        }
        DatePicker fridayCalender = (DatePicker) gui.getGUINode(FridayCalender);
        String friday;
        try {
            friday = fridayCalender.getValue().toString();
        } catch (NullPointerException easterEgg) {
            friday = "";
        }
        JsonObject dates = Json.createObjectBuilder().add(MONDAY, monday).add(FRIDAY, friday).build();

        ComboBox subjectArea = (ComboBox) gui.getGUINode(SUBJECT_COMBO);
        ObservableList<String> items = subjectArea.getItems();
        int j = 0;
        for (String item : items) {
            JsonObject jInnerObject = Json.createObjectBuilder()
                    .add(j + "", item).build();
            subjects.add(jInnerObject);
            j++;
        }
        JsonArray subjectsBuild = subjects.build();
        CheckBox homeB = (CheckBox) gui.getGUINode(HOME_CB);
        String homeSelected = "false";
        if (homeB.isSelected() == true) {
            homeSelected = "true";
        }

        CheckBox syallbuscb = (CheckBox) gui.getGUINode(SYLLABUS_CB);
        String syallabusSelected = "false";
        if (syallbuscb.isSelected() == true) {
            syallabusSelected = "true";
        }
        CheckBox schedulecb = (CheckBox) gui.getGUINode(SCHEDULE_CB);
        String scheduleSelected = "false";
        if (schedulecb.isSelected() == true) {
            scheduleSelected = "true";
        }
        CheckBox hwcb = (CheckBox) gui.getGUINode(HW_CB);
        String hwSelected = "false";
        if (hwcb.isSelected() == true) {
            hwSelected = "true";
        }
        JsonObject choiceBoxes = Json.createObjectBuilder().add(HOME_CHECKBOX, homeSelected)
                .add(SYLLABUS_CHECKBOX, syallabusSelected)
                .add(SCHEDULE_CHECKBOX, scheduleSelected)
                .add(HW_CHECKBOX, hwSelected).build();

        JsonArrayBuilder smesters = Json.createArrayBuilder();

        ComboBox semester = (ComboBox) gui.getGUINode(SEMESTER_COMBO);

        ObservableList<String> semesters = semester.getItems();
        int d = 0;
        for (String item : semesters) {
            JsonObject jInnerObject = Json.createObjectBuilder()
                    .add(d + "", item).build();
            smesters.add(jInnerObject);
            d++;
        }
        JsonArray semestersBuild = smesters.build();

        ObservableList<LecturesPrototype> lectures = dataManager.getLecturesList();
        ObservableList<Recitation_Lab_Prototype> recitations = dataManager.getRecitationsList();
        ObservableList<Recitation_Lab_Prototype> labs = dataManager.getLabsList();
        ObservableList<SchedulePrototype> scheduleList = dataManager.getList();

        JsonArrayBuilder scheduleArray = Json.createArrayBuilder();

        for (SchedulePrototype item : scheduleList) {
            JsonObject jInnerObject = Json.createObjectBuilder()
                    .add(SCHEDULE_TYPE, item.getType())
                    .add(SCHEDULE_DATE, item.getDate())
                    .add(SCHEDULE_TITLE, item.getTitle())
                    .add(SCHEDULE_TOPIC, item.getTopic())
                    .add(SCHEDULE_LINK, item.getLink())
                    .build();
            scheduleArray.add(jInnerObject);
        }
        JsonArray scheduleBuild = scheduleArray.build();

        JsonArrayBuilder lecturesArray = Json.createArrayBuilder();

        for (LecturesPrototype item : lectures) {
            JsonObject jInnerObject = Json.createObjectBuilder()
                    .add(LECTURES_SECTION, item.getSection())
                    .add(LECTURES_DAYS, item.getDays())
                    .add(LECTURES_TIMES, item.getTimes())
                    .add(LECTURES_ROOM, item.getRoom()).build();
            lecturesArray.add(jInnerObject);
        }
        JsonArray lecturesBuild = lecturesArray.build();

        JsonArrayBuilder recitationArray = Json.createArrayBuilder();

        for (Recitation_Lab_Prototype item : recitations) {
            JsonObject jInnerObject = Json.createObjectBuilder()
                    .add(RECITATION_SECTION, item.getSection())
                    .add(RECITATION_DT, item.getDaystime())
                    .add(RECITATION_ROOM, item.getRoom())
                    .add(RECITATION_TA1, item.getTa1())
                    .add(RECITATION_TA2, item.getTa2()).build();
            recitationArray.add(jInnerObject);
        }
        JsonArray recitationBuild = recitationArray.build();

        JsonArrayBuilder labsArray = Json.createArrayBuilder();

        for (Recitation_Lab_Prototype item : labs) {
            JsonObject jInnerObject = Json.createObjectBuilder()
                    .add(LAB_SECTION, item.getSection())
                    .add(LAB_DT, item.getDaystime())
                    .add(LAB_ROOM, item.getRoom())
                    .add(LAB_TA1, item.getTa1())
                    .add(LAB_TA2, item.getTa2()).build();
            labsArray.add(jInnerObject);
        }
        JsonArray labsBuild = labsArray.build();

        JsonArrayBuilder number = Json.createArrayBuilder();

        ComboBox numberBox = (ComboBox) gui.getGUINode(NUMBER_COMBO);
        ObservableList<String> numbers = numberBox.getItems();
        int y = 0;
        for (String item : numbers) {
            JsonObject jInnerObject = Json.createObjectBuilder()
                    .add(y + "", item).build();
            number.add(jInnerObject);
            y++;
        }
        JsonArray numberBuild = number.build();

        JsonArrayBuilder year = Json.createArrayBuilder();

        ComboBox yearBox = (ComboBox) gui.getGUINode(YEAR_COMBO);
        ObservableList<String> years = yearBox.getItems();
        int z = 0;
        for (String item : years) {
            JsonObject jInnerObject = Json.createObjectBuilder()
                    .add(z + "", item).build();
            year.add(jInnerObject);
            z++;
        }
        JsonArray yearBuild = year.build();
        TextField titleField = (TextField) gui.getGUINode(BANNER_TITLE);
        JsonObject chosenBanner = Json.createObjectBuilder()
                .add(CHOSEN_SUBJECT, subjectArea.getValue().toString())
                .add(CHOSEN_SEMESTER, semester.getValue().toString())
                .add(CHOSEN_NUMBER, numberBox.getValue().toString())
                .add(CHOSEN_TITLE, titleField.getText())
                .add(CHOSEN_YEAR, yearBox.getValue().toString()).build();

        JsonObject syllabusJSON = Json.createObjectBuilder()
                .add(SDESCRIPTION, syllabus.getDescription())
                .add(STOPICS, syllabus.getTopics())
                .add(SPRE, syllabus.getPrerequisites())
                .add(SOUT, syllabus.getOutcomes())
                .add(STEXTBOOKS, syllabus.getTextbooks())
                .add(SGC, syllabus.getGradedComponents())
                .add(SGN, syllabus.getGradingNotes())
                .add(SAD, syllabus.getAcademicDishonesty())
                .add(SSA, syllabus.getSpecialAssistanse()).build();
        final ComboBox fontSheet = (ComboBox) gui.getGUINode(FONTSHEET);
        JsonObject styleSheet;
        if (fontSheet.getValue() != null) {
            styleSheet = Json.createObjectBuilder()
                    .add(CSS_SHEET, fontSheet.getValue().toString()).build();
        } else {
            styleSheet = Json.createObjectBuilder()
                    .add(CSS_SHEET, "").build();
        }
        // NOW BUILD THE TA JSON OBJCTS TO SAVE
        JsonArrayBuilder gradTAsArrayBuilder = Json.createArrayBuilder();
        JsonArrayBuilder undergradTAsArrayBuilder = Json.createArrayBuilder();
        Iterator<TeachingAssistantPrototype> tasIterator = dataManager.teachingAssistantsIterator();
        while (tasIterator.hasNext()) {
            TeachingAssistantPrototype ta = tasIterator.next();
            JsonObject taJson = Json.createObjectBuilder()
                    .add(JSON_NAME, ta.getName())
                    .add(JSON_EMAIL, ta.getEmail())
                    .add(JSON_TYPE, ta.getType().toString()).build();

            if (ta.getType().equals(TAType.Graduate.toString())) {
                gradTAsArrayBuilder.add(taJson);
            } else {
                undergradTAsArrayBuilder.add(taJson);
            }
        }
        JsonArray gradTAsArray = gradTAsArrayBuilder.build();
        JsonArray undergradTAsArray = undergradTAsArrayBuilder.build();

        // NOW BUILD THE OFFICE HOURS JSON OBJCTS TO SAVE
        JsonArrayBuilder officeHoursArrayBuilder = Json.createArrayBuilder();
        Iterator<TimeSlot> timeSlotsIterator = dataManager.officeHoursIterator();
        while (timeSlotsIterator.hasNext()) {
            TimeSlot timeSlot = timeSlotsIterator.next();
            for (int i = 0; i < DayOfWeek.values().length; i++) {
                DayOfWeek dow = DayOfWeek.values()[i];
                tasIterator = timeSlot.getTAsIterator(dow);
                while (tasIterator.hasNext()) {
                    TeachingAssistantPrototype ta = tasIterator.next();
                    JsonObject tsJson = Json.createObjectBuilder()
                            .add(JSON_START_TIME, timeSlot.getStartTime().replace(":", "_"))
                            .add(JSON_DAY_OF_WEEK, dow.toString())
                            .add(JSON_NAME, ta.getName()).build();
                    officeHoursArrayBuilder.add(tsJson);
                }
            }
        }
        JsonArray officeHoursArray = officeHoursArrayBuilder.build();

        // THEN PUT IT ALL TOGETHER IN A JsonObject
        JsonObject dataManagerJSO = Json.createObjectBuilder()
                .add(JSON_START_HOUR, "" + dataManager.getStartHour())
                .add(JSON_END_HOUR, "" + dataManager.getEndHour())
                .add(JSON_GRAD_TAS, gradTAsArray)
                .add(JSON_UNDERGRAD_TAS, undergradTAsArray)
                .add(JSON_OFFICE_HOURS, officeHoursArray)
                .add(INSTRUCTOR, instructorJSON)
                .add(DATES, dates)
                .add(SUBJECT, subjectsBuild)
                .add(SCHEDULE, scheduleBuild)
                .add(SEMESTERS, semestersBuild)
                .add(NUMBER, numberBuild)
                .add(YEAR, yearBuild)
                .add(IMAGES, images)
                .add(FONTSTYLE, styleSheet)
                .add(BANNER_OBJECTS, chosenBanner)
                .add(CHOICES, choiceBoxes)
                .add(SYLLABUS, syllabusJSON)
                .add(LECTURES, lecturesBuild)
                .add(RECITATIONS, recitationBuild)
                .add(LABS, labsBuild)
                .build();

        // AND NOW OUTPUT IT TO A JSON FILE WITH PRETTY PRINTING
        Map<String, Object> properties = new HashMap<>(1);
        properties.put(JsonGenerator.PRETTY_PRINTING, true);
        JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
        StringWriter sw = new StringWriter();
        JsonWriter jsonWriter = writerFactory.createWriter(sw);
        jsonWriter.writeObject(dataManagerJSO);
        jsonWriter.close();

        // INIT THE WRITER
        //OutputStream os = new FileOutputStream(exportdir//js//PageData.json);
        OutputStream os = new FileOutputStream(filePath);
        JsonWriter jsonFileWriter = Json.createWriter(os);
        jsonFileWriter.writeObject(dataManagerJSO);
        String prettyPrinted = sw.toString();
        PrintWriter pw = new PrintWriter(filePath);
        jsonFileWriter.close();
        pw.write(prettyPrinted);
        pw.close();
        sw.close();
    }

    // IMPORTING/EXPORTING DATA IS USED WHEN WE READ/WRITE DATA IN AN
    // ADDITIONAL FORMAT USEFUL FOR ANOTHER PURPOSE, LIKE ANOTHER APPLICATION
    @Override
    public void importData(AppDataComponent data, String filePath) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String absoluteToRelative(String absolute) {
        if (!absolute.substring(0, 8).equals("./images")) {
            PropertiesManager props = PropertiesManager.getPropertiesManager();
            String path = absolute;
            String base = "\\images\\";
            String relative = "." + absolute.substring(absolute.lastIndexOf(base));
            relative.replace("\\\\", "/");
            relative = relative.substring(0, 8) + relative.substring(8).replace("/", "//");
            return relative;
        }
        return absolute;
    }

    private String relativeToAbsolute(String relativePath, String referenceDirPath) throws IOException {
        File a = new File(referenceDirPath);
        File parentFolder = new File(a.getParent());
        File b = new File(parentFolder, relativePath);
        String absolute = b.getCanonicalPath();
        return absolute;
    }

    @Override
    public void exportData(AppDataComponent data, String filePath) throws IOException {
        AppGUIModule gui = app.getGUIModule();
        OfficeHoursData dataManger = (OfficeHoursData) data;
        Text sd = (Text) gui.getGUINode(EXPORT_DIR);
        String dir = sd.getText();
        String imageDIR = dir + "\\images";
        File export_dir = new File(dir);
        File images = new File(imageDIR);
        File cssdir = new File(dir + "\\css");
        File javascriptDIR = new File(dir + "\\js");
        if (!export_dir.exists()) {
            export_dir.mkdirs();
        }
        ComboBox font = (ComboBox) gui.getGUINode(FONTSHEET);
        String fontStyle;
        if (font.getValue() == null) {
            fontStyle = "christmas.css";
        } else {
            fontStyle = font.getValue().toString();
        }

        if (!cssdir.exists()) {
            cssdir.mkdir();
        }
        try {
            FileUtils.cleanDirectory(cssdir);
            FileUtils.copyFile(new File(".\\work\\css\\" + fontStyle), new File(cssdir + "\\" + fontStyle));
        } catch (Exception ff) {
            //  FileUtils.copyFile(new File("\\work\\" + fontStyle), new File(cssdir+ "\\christmas.css"));

        }
        if (!javascriptDIR.exists()) {
            javascriptDIR.mkdir();

        }
        File jsFile = new File(".\\app_data\\js");
        try {
            FileUtils.copyDirectory(jsFile, javascriptDIR);
        } catch (IOException e) {
            e.printStackTrace();
        }

        File faviconImage = new File(dataManger.favicon);
        File navbar = new File(dataManger.navbar);
        File rightImage = new File(dataManger.rightFooter);
        File leftImage = new File(dataManger.leftFooter);

        if (!images.exists()) {
            images.mkdir();
        }
        try {//WHY NOT IMAGE
            FileUtils.cleanDirectory(images);
            FileUtils.copyFile(faviconImage, new File(images + "\\" + faviconImage.getName()));
            FileUtils.copyFile(navbar, new File(images + "\\" + navbar.getName()));
            FileUtils.copyFile(rightImage, new File(images + "\\" + rightImage.getName()));
            FileUtils.copyFile(leftImage, new File(images + "\\" + leftImage.getName()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Text hhh = (Text) gui.getGUINode(EXPORT_DIR);
        String qwe = hhh.getText();
        File newFile = new File(qwe + "\\js\\ScheduleData.json");
        if (newFile.exists()) {
            newFile.delete();
        }
        
        File asd = new File(qwe + "\\js\\PageData.json");
        if (asd.exists()) {
            asd.delete();
        }
        File g = new File(qwe + "\\js\\OfficeHoursData.json");
        if (g.exists()) {
            g.delete();
        }
        File ccc = new File(qwe + "\\js\\SectionsData.json");
        if (ccc.exists()) {
            ccc.delete();
        }
        File cxz = new File(qwe + "\\js\\SyllabusData.json");
        if (cxz.exists()) {
            cxz.delete();
        }
        
                   File  home = new File(qwe + "\\index.html");
                   if(home.exists()){
                       home.delete();
                   }
        File syllabussd = new File(qwe + "\\syllabus.html");
        if(syllabussd.exists()){
                       syllabussd.delete();
                   }
        File schedulesd = new File(qwe + "\\schedule.html");
        if(schedulesd.exists()){
                       schedulesd.delete();
                   }
       File  hwssd = new File(qwe + "\\hws.html");
if(hwssd.exists()){
                       hwssd.delete();
                   }
        
         File index=null;
         File syllabus=null;
        File schedule =null;
        File hws = null;
        CheckBox homeB = (CheckBox) gui.getGUINode(HOME_CB);
        if (homeB.isSelected()) {
            savePageData(dataManger);
            BufferedWriter writer = new BufferedWriter(new FileWriter(qwe + "\\index.html"));
            writer.write(getIndexHTML());
            writer.close();
             index = new File(qwe + "\\index.html");

        }
        CheckBox syallbuscb = (CheckBox) gui.getGUINode(SYLLABUS_CB);
        if (syallbuscb.isSelected()) {
         syllabus = new File(qwe + "\\syllabus.html");

            saveSectionsData(dataManger);
            savePageData(dataManger);
            saveOHData(dataManger);
            saveSyllabusData(dataManger);
            BufferedWriter writer = new BufferedWriter(new FileWriter(qwe + "\\syllabus.html"));
            writer.write(getSyllabusHTML());
            writer.close();

        }
        CheckBox schedulecb = (CheckBox) gui.getGUINode(SCHEDULE_CB);

        if (schedulecb.isSelected()) {
         schedule = new File(qwe + "\\schedule.html");

            saveScheduleData(dataManger);
            savePageData(dataManger);
            BufferedWriter writer = new BufferedWriter(new FileWriter(qwe + "\\schedule.html"));
            writer.write(getScheduleHTML());
            writer.close();

        }
        CheckBox hwcb = (CheckBox) gui.getGUINode(HW_CB);
        if (hwcb.isSelected()) {
         hws = new File(qwe + "\\hws.html");

            savePageData(dataManger);
            saveScheduleData(dataManger);
            BufferedWriter writer = new BufferedWriter(new FileWriter(qwe + "\\hws.html"));
            writer.write(getHWHTML());
            writer.close();

        }
        WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();
       if(index!=null){
        URL url = index.toURI().toURL();
        webEngine.load(url.toString());}
       if(syllabus!=null){
        URL sya = syllabus.toURI().toURL();
        webEngine.load(sya.toString());}
       if(schedule!=null){
        URL sch = schedule.toURI().toURL();
        webEngine.load(sch.toString());}
       if(hws!=null){
        URL hwsss = hws.toURI().toURL();
        webEngine.load(hwsss.toString());}
        Scene scene = new Scene(browser);
        Window window = scene.getWindow();
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public String getHWHTML() {
        AppGUIModule gui = app.getGUIModule();
        ComboBox font = (ComboBox) gui.getGUINode(FONTSHEET);
        String fontStyle;
        if (font.getValue() == null) {
            fontStyle = "christmas.css";
        } else {
            fontStyle = font.getValue().toString();
        }
        String bob = "<!doctype html>\n"
                + "<html>\n"
                + "    <head>\n"
                + "        <META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n"
                + "        <title></title>\n"
                + "        <meta content=\"text/html;charset=utf-8\" http-equiv=\"Content-Type\">\n"
                + "        <link href=\"./css/course_homepage_layout.css\" rel=\"stylesheet\" type=\"text/css\">\n"
                + "        <link href=\"./css/" + fontStyle + "\" rel=\"stylesheet\" type=\"text/css\">\n"
                + "\n"
                + "        <!-- LINK TO OUR JavaScript FILE AND JQuery -->\n"
                + "        <script src=\"./js/jquery.min.js\"></script>\n"
                + "        <script src=\"./js/PageBuilder.js\"></script>\n"
                + "        <script src=\"./js/HWsBuilder.js\"></script>\n"
                + "\n"
                + "        <!-- ONCE THE PAGE FULLY LOADS INITIALIZE THE SLIDESHOW -->\n"
                + "        <script>\n"
                + "            $(document).ready()\n"
                + "            {\n"
                + "                buildPage(\"HWs\", \".\");\n"
                + "                buildHWs();\n"
                + "            }\n"
                + "        </script>\n"
                + "    </head>\n"
                + "\n"
                + "    <body>\n"
                + "        <div id=\"content\">\n"
                + "            <div id=\"navbar\"><a id=\"navbar_image_link\"></a></div>\n"
                + "\n"
                + "            <div id=\"banner\"></div>\n"
                + "\n"
                + "            <div id=\"desc\">\n"
                + "                <h3>Homework Assignments</h3>\n"
                + "\n"
                + "                <p>Note that the <a href='./schedule.html'>Schedule Page</a> also contains links to assignments.</p>\n"
                + "\n"
                + "                <table class=\"hws\">\n"
                + "                    <tbody id=\"hws\">\n"
                + "                        <tr class=\"hws\" style=\"background-color:rgb(235,215,215)\">\n"
                + "                            <th class=\"hws\">Homework</th>\n"
                + "                            <th class=\"hws\">Due Date</th>\n"
                + "                            <!--th class=\"hws\">Grading Criteria</th>-->\n"
                + "                        </tr>\n"
                + "\n"
                + "                        <!-- THIS WILL BE FILLED IN BY JAVASCRIPT -->\n"
                + "                    </tbody>\n"
                + "                </table>\n"
                + "                <br />\n"
                + "                <hr />\n"
                + "\n"
                + "                <a id=\"left_footer_image_link\"></a>\n"
                + "                <a id=\"right_footer_image_link\"></a>\n"
                + "                <p class=\"footer\">Web page created and maintained<br />\n"
                + "                    by <span id=\"author_link\"></span></p>\n"
                + "                <br /><br />\n"
                + "            </div>\n"
                + "        </div>\n"
                + "    </body>\n"
                + "</html>";
        return bob;
    }

    public String getScheduleHTML() {
        AppGUIModule gui = app.getGUIModule();
        ComboBox font = (ComboBox) gui.getGUINode(FONTSHEET);
        String fontStyle;
        if (font.getValue() == null) {
            fontStyle = "christmas.css";
        } else {
            fontStyle = font.getValue().toString();
        }
        String bob = "<!doctype html>\n"
                + "<html>\n"
                + "    <head>\n"
                + "        <META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n"
                + "        <title></title>\n"
                + "        <meta content=\"text/html;charset=utf-8\" http-equiv=\"Content-Type\">\n"
                + "        <link href=\"./css/course_homepage_layout.css\" rel=\"stylesheet\" type=\"text/css\">\n"
                + "        <link href=\"./css/" + fontStyle + "\" rel=\"stylesheet\" type=\"text/css\">\n"
                + "\n"
                + "        <!-- LINK TO OUR JavaScript FILE AND JQuery -->\n"
                + "        <script src=\"./js/jquery.min.js\"></script>\n"
                + "        <script src=\"./js/PageBuilder.js\"></script>\n"
                + "        <script src=\"./js/ScheduleBuilder.js\"></script>\n"
                + "\n"
                + "        <!-- ONCE THE PAGE FULLY LOADS INITIALIZE THE SLIDESHOW -->\n"
                + "        <script>\n"
                + "            $(document).ready()\n"
                + "            {\n"
                + "                buildPage(\"Schedule\", \".\");\n"
                + "                buildSchedule();\n"
                + "            }\n"
                + "        </script>\n"
                + "    </head>\n"
                + "\n"
                + "    <body>\n"
                + "        <div id=\"content\">\n"
                + "            <div id=\"navbar\"><a id=\"navbar_image_link\"></a></div>\n"
                + "\n"
                + "            <div id=\"banner\"></div>\n"
                + "\n"
                + "\n"
                + "            <div id=\"desc\">\n"
                + "\n"
                + "                <table border=\"1\" cellspacing=\"0\" class=\"schedule\">\n"
                + "                    <tbody id=\"schedule_table\">\n"
                + "                        <!-- THIS WILL BE FILLED IN BY JAVASCRIPT -->                        \n"
                + "                    </tbody>\n"
                + "                </table>\n"
                + "\n"
                + "                <br />\n"
                + "\n"
                + "                <hr />\n"
                + "\n"
                + "                <a id=\"left_footer_image_link\"></a>\n"
                + "                <a id=\"right_footer_image_link\"></a>\n"
                + "                <p class=\"footer\">Web page created and maintained<br />\n"
                + "                    by <span id=\"author_link\"></span></p>\n"
                + "                <br /><br />\n"
                + "            </div>\n"
                + "        </div>\n"
                + "    </body>\n"
                + "</html>";
        return bob;
    }

    public String getSyllabusHTML() {
        AppGUIModule gui = app.getGUIModule();
        ComboBox font = (ComboBox) gui.getGUINode(FONTSHEET);
        String fontStyle;
        if (font.getValue() == null) {
            fontStyle = "christmas.css";
        } else {
            fontStyle = font.getValue().toString();
        }
        String bob = "<!doctype html>\n"
                + "<html>\n"
                + "    <head>\n"
                + "        <META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n"
                + "        <title></title>\n"
                + "        <meta content=\"text/html;charset=utf-8\" http-equiv=\"Content-Type\">\n"
                + "        <link href=\"./css/course_homepage_layout.css\" rel=\"stylesheet\" type=\"text/css\">\n"
                + "        <link href=\"./css/" + fontStyle + "\" rel=\"stylesheet\" type=\"text/css\">\n"
                + "\n"
                + "        <!-- LINK TO OUR JavaScript FILE AND JQuery -->\n"
                + "        <script src=\"./js/jquery.min.js\"></script>\n"
                + "        <script src=\"./js/PageBuilder.js\"></script>\n"
                + "        <script src=\"./js/OfficeHoursBuilder.js\"></script>\n"
                + "        <script src=\"./js/SectionsBuilder.js\"></script>\n"
                + "        <script src=\"./js/SyllabusBuilder.js\"></script>\n"
                + "\n"
                + "        <!-- ONCE THE PAGE FULLY LOADS INITIALIZE THE SLIDESHOW -->\n"
                + "        <script>\n"
                + "            $(document).ready()\n"
                + "            {\n"
                + "                buildPage(\"Syllabus\", \".\");\n"
                + "                buildOfficeHours();\n"
                + "                buildSections();\n"
                + "                buildSyllabus();\n"
                + "            }\n"
                + "        </script>\n"
                + "    </head>\n"
                + "\n"
                + "    <body>\n"
                + "        <div id=\"content\">\n"
                + "            <div id=\"navbar\"><a id=\"navbar_image_link\"></a></div>\n"
                + "\n"
                + "            <div id=\"banner\"></div>\n"
                + "            <div id=\"desc\">\n"
                + "\n"
                + "                <div id=\"custom_syllabus_content\">\n"
                + "\n"
                + "                    <h3>COURSE DESCRIPTION</h3>\n"
                + "                    <p id='syllabus_course_description'></p>\n"
                + "                    <br />\n"
                + "\n"
                + "                    <h3>COURSE TOPICS</h3>\n"
                + "                    <ul id=\"syllabus_course_topics\">\n"
                + "                    </ul>\n"
                + "                    <br />\n"
                + "\n"
                + "                    <h3>PREREQUISITES</h3>\n"
                + "                    <p id=\"syllabus_prerequisites\"></p>\n"
                + "                    <br />\n"
                + "\n"
                + "                    <h3>COURSE OUTCOMES</h3>\n"
                + "                    <p>At the end of the course you should have the following knowledge and skills:</p>\n"
                + "                    <ul id=\"syllabus_course_outcomes\">\n"
                + "                    </ul>\n"
                + "                    <br />\n"
                + "\n"
                + "                    <h3>LECTURES</h3>\n"
                + "                    <table>\n"
                + "                        <tbody>\n"
                + "                            <tr id='lectures_row'>\n"
                + "                            </tr>\n"
                + "                        </tbody>\n"
                + "                    </table>\n"
                + "                    <br />\n"
                + "                    <br />\n"
                + "                    \n"
                + "                    <h3 id=\"labs_heading\"></h3>\n"
                + "                    <table>\n"
                + "                        <tbody id=\"labs_table\">\n"
                + "                        </tbody>\n"
                + "                    </table>\n"
                + "                    <br />\n"
                + "                    <br />\n"
                + "                                  \n"
                + "                    <h3 id=\"recitations_heading\"></h3>\n"
                + "                    <table>\n"
                + "                        <tbody id=\"recitations_table\">\n"
                + "                        </tbody>\n"
                + "                    </table>\n"
                + "                    <br />\n"
                + "                    <br />\n"
                + "\n"
                + "                    <h3>INSTRUCTOR</h3>                    \n"
                + "                    <table>\n"
                + "                        <tr>\n"
                + "                            <td id='instructor_data'></td>\n"
                + "                            <td id='instructor_photo'></td>\n"
                + "                        </tr>\n"
                + "                    </table>\n"
                + "                    <br />\n"
                + "                    <br />\n"
                + "\n"
                + "                    <h3 id=\"graders\">GRAD TEACHING ASSISTANTS (Grading Appointments)</h3>\n"
                + "\n"
                + "                    <table>\n"
                + "                        <tbody id=\"grad_tas\">\n"
                + "                        </tbody>\n"
                + "                    </table>\n"
                + "                    <br />\n"
                + "                    <br />\n"
                + "\n"
                + "                    <h3 id=\"tas\">UNDERGRAD TEACHING ASSISTANTS (Help)</h3>\n"
                + "\n"
                + "                    <table>\n"
                + "                        <tbody id=\"undergrad_tas\">\n"
                + "                        </tbody>\n"
                + "                    </table>\n"
                + "                    <br />\n"
                + "                    <br />\n"
                + "\n"
                + "                    <h3 id=\"office_hours_grid\">OFFICE HOURS (in Old CS 2217)</h3>\n"
                + "                    <table class=\"office_hours_schedule\" cellspacing=\"0\" border=\"1\">\n"
                + "                        <tbody id=\"office_hours_table\">\n"
                + "                            <tr>\n"
                + "                                <th class=\"sch\" width=\"150px\">Start Time</th>\n"
                + "                                <th class=\"sch\" width=\"150px\">End Time</th>\n"
                + "                                <th class=\"sch\" width=\"200px\">MONDAY</th>\n"
                + "                                <th class=\"sch\" width=\"200px\">TUESDAY</th>\n"
                + "                                <th class=\"sch\" width=\"200px\">WEDNESDAY</th>\n"
                + "                                <th class=\"sch\" width=\"200px\">THURSDAY</th>\n"
                + "                                <th class=\"sch\" width=\"200px\">FRIDAY</th>\n"
                + "                            </tr>                        \n"
                + "                        </tbody>\n"
                + "                    </table>\n"
                + "                    <br /> \n"
                + "                    <br />\n"
                + "\n"
                + "                    <h3>TEXTBOOKS</h3>                    \n"
                + "                    <div id='textbook_data'></div>\n"
                + "                    <br clear=\"all\" />\n"
                + "                    <br />\n"
                + "\n"
                + "\n"
                + "                    <h3>GRADED COMPONENTS</h3>                    \n"
                + "                    <ul id='graded_components'></ul>\n"
                + "                    <br />\n"
                + "\n"
                + "                    <h3>GRADING BREAKDOWN</h3>                    \n"
                + "                    <table id='grading_breakdown'></table>\n"
                + "                    <p id='grading_note'></p>\n"
                + "                    <br />                    \n"
                + "\n"
                + "                    <h3>ACADEMIC DISHONESTY</h3>\n"
                + "                    <div id='academic_dishonesty'></div>\n"
                + "                    <br />\n"
                + "\n"
                + "                    <h3>SPECIAL ASSISTANCE</h3>\n"
                + "                    <div id='special_assistance'></div>\n"
                + "                    <br />\n"
                + "\n"
                + "                    <hr />\n"
                + "\n"
                + "                    <a id=\"left_footer_image_link\"></a>\n"
                + "                    <a id=\"right_footer_image_link\"></a>\n"
                + "                    <p class=\"footer\">Web page created and maintained<br />\n"
                + "                        by <span id=\"author_link\"></span></p>\n"
                + "                    <br /><br />\n"
                + "                </div>\n"
                + "            </div>\n"
                + "        </div>\n"
                + "    </body>\n"
                + "</html>";
        return bob;
    }

    public String getIndexHTML() {
        AppGUIModule gui = app.getGUIModule();
        ComboBox font = (ComboBox) gui.getGUINode(FONTSHEET);
        String fontStyle;
        if (font.getValue() == null) {
            fontStyle = "christmas.css";
        } else {
            fontStyle = font.getValue().toString();
        }
        String bob = "<!doctype html>\n"
                + "<html>\n"
                + "    <head>\n"
                + "        <META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n"
                + "        <title></title>\n"
                + "        <meta content=\"text/html;charset=utf-8\" http-equiv=\"Content-Type\">\n"
                + "\n"
                + "        <link href=\"./css/course_homepage_layout.css\" rel=\"stylesheet\" type=\"text/css\">\n"
                + "        <link href=\"./css/" + fontStyle + "\" rel=\"stylesheet\" type=\"text/css\">\n"
                + "\n"
                + "        <!-- LINK TO OUR JavaScript FILE AND JQuery -->\n"
                + "        <script src=\"./js/jquery.min.js\"></script>\n"
                + "        <script src=\"./js/PageBuilder.js\"></script>\n"
                + "\n"
                + "        <!-- ONCE THE PAGE FULLY LOADS, COMPLETE BUILDING THE CONTENT -->\n"
                + "        <script>\n"
                + "            $(document).ready()\n"
                + "            {\n"
                + "                buildPage(\"Home\", \".\");\n"
                + "            }\n"
                + "        </script>\n"
                + "    </head>\n"
                + "\n"
                + "    <body>\n"
                + "        <div id=\"content\">\n"
                + "            <div id=\"navbar\">\n"
                + "                <a id=\"navbar_image_link\"></a>\n"
                + "            </div>\n"
                + "\n"
                + "            <div id=\"banner\"></div>\n"
                + "\n"
                + "            <div id=\"desc\">\n"
                + "\n"
                + "                <p>Welcome to the <span id=\"inlined_course\"></span> Web site. If you are enrolled in the course, be sure to periodically check this site for all materials and changes as the semester moves along. Note that all grades will be posted to <a href=\"http://blackboard.stonybrook.edu\">Blackboard</a>.</p>\n"
                + "                <br /><br /><br /><br /><br /><br /><br />\n"
                + "                <hr />\n"
                + "\n"
                + "                <a id=\"left_footer_image_link\"></a>\n"
                + "                <a id=\"right_footer_image_link\"></a>\n"
                + "                <p class=\"footer\">Web page created and maintained<br />\n"
                + "                    by <span id=\"author_link\"></span></p>\n"
                + "                <br /><br />\n"
                + "            </div>\n"
                + "        </div>\n"
                + "    </body>\n"
                + "</html>";
        return bob;
    }
}
