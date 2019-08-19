package oh.workspace;

import static djf.AppPropertyType.ADDBUTTON_TEXT;
import static djf.AppPropertyType.ADDDATE;
import static djf.AppPropertyType.ADDLINK;
import static djf.AppPropertyType.ADDTITLE;
import static djf.AppPropertyType.ADDTOPIC;
import static djf.AppPropertyType.ADDType;
import static djf.AppPropertyType.APP_PATH_IMAGES;
import static djf.AppPropertyType.BANNER_TITLE;
import static djf.AppPropertyType.CB_TEXT;
import static djf.AppPropertyType.ClearBUTTON_TEXT;
import static djf.AppPropertyType.DarkRedLogo;
import static djf.AppPropertyType.Description_text;
import static djf.AppPropertyType.EMAIL_NAME;
import static djf.AppPropertyType.EXPORT_DIR;
import static djf.AppPropertyType.EXPORT_DIR_TITLE;
import static djf.AppPropertyType.FAVICON_TEXT;
import static djf.AppPropertyType.FONTS_SHEETS_TEXT;
import static djf.AppPropertyType.FRIDAYCB_TEXT;
import static djf.AppPropertyType.FridayCalender;
import static djf.AppPropertyType.HOME_TEXT;
import static djf.AppPropertyType.HP_NAME;
import static djf.AppPropertyType.HW_TEXT;
import static djf.AppPropertyType.INSTRUCTOR_NAME;
import static djf.AppPropertyType.INSTRUCTOR_TITLE;
import static djf.AppPropertyType.LABS_LABEL;
import static djf.AppPropertyType.LECTURES_TEXT;
import static djf.AppPropertyType.LEFTFOOTER_TEXT;
import djf.components.AppWorkspaceComponent;
import djf.modules.AppFoolproofModule;
import djf.modules.AppGUIModule;
import static djf.modules.AppGUIModule.ENABLED;
import djf.ui.AppNodesBuilder;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import properties_manager.PropertiesManager;
import oh.OfficeHoursApp;
import oh.OfficeHoursPropertyType;
import static oh.OfficeHoursPropertyType.*;
import oh.data.TeachingAssistantPrototype;
import oh.data.TimeSlot;
import oh.workspace.controllers.OfficeHoursController;
import oh.workspace.dialogs.TADialog;
import oh.workspace.foolproof.OfficeHoursFoolproofDesign;
import static oh.workspace.style.OHStyle.*;
import static djf.AppPropertyType.SITE_TAB;
import static djf.AppPropertyType.SYLLABUS_TAB;
import static djf.AppPropertyType.MEETING_TIMES_TAB;
import static djf.AppPropertyType.MONDAYCB_TEXT;
import static djf.AppPropertyType.MondayCalender;
import static djf.AppPropertyType.NAVBARTEXT;
import static djf.AppPropertyType.NUMBER_TITLE;
import static djf.AppPropertyType.OFFICE_H;
import static djf.AppPropertyType.OFFICE_HOURS_TAB;
import static djf.AppPropertyType.PAGE_TITLE;
import static djf.AppPropertyType.RECITATIONS_LABEL;
import static djf.AppPropertyType.RECITATION_TEXT;
import static djf.AppPropertyType.RIGHTFOOTER_TEXT;
import static djf.AppPropertyType.ROOM_NAME;
import static djf.AppPropertyType.SAI_TEXT;
import static djf.AppPropertyType.SBUCSLOGO;
import static djf.AppPropertyType.SCHEDULE_TAB;
import static djf.AppPropertyType.SCHEDULE_TEXT;
import static djf.AppPropertyType.SEMESTER_TITLE;
import static djf.AppPropertyType.SI_TEXT;
import static djf.AppPropertyType.STYLE_DETAIL;
import static djf.AppPropertyType.STYLE_TITLE;
import static djf.AppPropertyType.SUBJECT_TITLE;
import static djf.AppPropertyType.SYLLABUS_COMP;
import static djf.AppPropertyType.SYLLABUS_TEXT;
import static djf.AppPropertyType.SimpleLogo;
import static djf.AppPropertyType.TITLE_TITLE;
import static djf.AppPropertyType.WhiteLogo;
import static djf.AppPropertyType.YEAR_TITLE;
import static djf.AppPropertyType.ad_text;
import static djf.AppPropertyType.endtime_text;
import static djf.AppPropertyType.gc_text;
import static djf.AppPropertyType.gn_text;
import static djf.AppPropertyType.outcomes_text;
import static djf.AppPropertyType.prerquisites_text;
import static djf.AppPropertyType.sa_text;
import static djf.AppPropertyType.starttime_text;
import static djf.AppPropertyType.textbooks_text;
import static djf.AppPropertyType.topics_text;
import djf.ui.dialogs.AppDialogsFacade;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Parameter;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.imageio.ImageIO;
import oh.data.InstructorPrototype;
import oh.data.LecturesPrototype;
import oh.data.OfficeHoursData;
import static oh.data.OfficeHoursData.pageBNumber;
import static oh.data.OfficeHoursData.pageSemester;
import static oh.data.OfficeHoursData.pageSubject;
import static oh.data.OfficeHoursData.pageTitle;
import static oh.data.OfficeHoursData.pageYear;
import oh.data.Recitation_Lab_Prototype;
import oh.data.SchedulePrototype;
import oh.data.SyllabusPrototype;

/**
 *
 * @author McKillaGorilla
 */
public class OfficeHoursWorkspace extends AppWorkspaceComponent {

    public OfficeHoursWorkspace(OfficeHoursApp app) {
        super(app);

        // LAYOUT THE APP
        initLayout();

        // INIT THE EVENT HANDLERS
        initControllers();

        // 
        initFoolproofDesign();

        // INIT DIALOGS
        initDialogs();
    }
    public ComboBox times;
    public ComboBox entimes;

    private void initDialogs() {
        TADialog taDialog = new TADialog((OfficeHoursApp) app);
        app.getGUIModule().addDialog(OH_TA_EDIT_DIALOG, taDialog);
    }
    public Button removeTA;

    // THIS HELPER METHOD INITIALIZES ALL THE CONTROLS IN THE WORKSPACE
    private void initLayout() {
        // FIRST LOAD THE FONT FAMILIES FOR THE COMBO BOX
        PropertiesManager props = PropertiesManager.getPropertiesManager();

        // THIS WILL BUILD ALL OF OUR JavaFX COMPONENTS FOR US
        AppNodesBuilder ohBuilder = app.getGUIModule().getNodesBuilder();
        TabPane tabPane = new TabPane();
        // INIT THE HEADER ON THE LEFT
        VBox leftPane = ohBuilder.buildVBox(OH_LEFT_PANE, null, CLASS_OH_PANE, ENABLED);
        HBox tasHeaderBox = ohBuilder.buildHBox(OH_TAS_HEADER_PANE, leftPane, CLASS_OH_BOX, ENABLED);
        removeTA = new Button("-");
        tasHeaderBox.getChildren().add(removeTA);
        Text bigspace = new Text("   ");
        tasHeaderBox.getChildren().add(bigspace);

        ohBuilder.buildLabel(OfficeHoursPropertyType.OH_TAS_HEADER_LABEL, tasHeaderBox, CLASS_OH_HEADER_LABEL, ENABLED);
        HBox typeHeaderBox = ohBuilder.buildHBox(OH_GRAD_UNDERGRAD_TAS_PANE, tasHeaderBox, CLASS_OH_RADIO_BOX, ENABLED);
        ToggleGroup tg = new ToggleGroup();
        ohBuilder.buildRadioButton(OH_ALL_RADIO_BUTTON, typeHeaderBox, CLASS_OH_RADIO_BUTTON, ENABLED, tg, true);
        ohBuilder.buildRadioButton(OH_GRAD_RADIO_BUTTON, typeHeaderBox, CLASS_OH_RADIO_BUTTON, ENABLED, tg, false);
        ohBuilder.buildRadioButton(OH_UNDERGRAD_RADIO_BUTTON, typeHeaderBox, CLASS_OH_RADIO_BUTTON, ENABLED, tg, false);

        // MAKE THE TABLE AND SETUP THE DATA MODEL
        TableView<TeachingAssistantPrototype> taTable = ohBuilder.buildTableView(OH_TAS_TABLE_VIEW, leftPane, CLASS_OH_TABLE_VIEW, ENABLED);
        taTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        TableColumn nameColumn = ohBuilder.buildTableColumn(OH_NAME_TABLE_COLUMN, taTable, CLASS_OH_COLUMN);
        TableColumn emailColumn = ohBuilder.buildTableColumn(OH_EMAIL_TABLE_COLUMN, taTable, CLASS_OH_COLUMN);
        TableColumn slotsColumn = ohBuilder.buildTableColumn(OH_SLOTS_TABLE_COLUMN, taTable, CLASS_OH_CENTERED_COLUMN);
        TableColumn typeColumn = ohBuilder.buildTableColumn(OH_TYPE_TABLE_COLUMN, taTable, CLASS_OH_COLUMN);
        nameColumn.setCellValueFactory(new PropertyValueFactory<String, String>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<String, String>("email"));
        slotsColumn.setCellValueFactory(new PropertyValueFactory<String, String>("slots"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<String, String>("type"));
        nameColumn.prefWidthProperty().bind(taTable.widthProperty().multiply(1.0 / 5.0));
        emailColumn.prefWidthProperty().bind(taTable.widthProperty().multiply(2.0 / 5.0));
        slotsColumn.prefWidthProperty().bind(taTable.widthProperty().multiply(1.0 / 5.0));
        typeColumn.prefWidthProperty().bind(taTable.widthProperty().multiply(1.0 / 5.0));

        // ADD BOX FOR ADDING A TA
        HBox taBox = ohBuilder.buildHBox(OH_ADD_TA_PANE, leftPane, CLASS_OH_PANE, ENABLED);
        ohBuilder.buildTextField(OH_NAME_TEXT_FIELD, taBox, CLASS_OH_TEXT_FIELD, ENABLED);
        ohBuilder.buildTextField(OH_EMAIL_TEXT_FIELD, taBox, CLASS_OH_TEXT_FIELD, ENABLED);
        ohBuilder.buildTextButton(OH_ADD_TA_BUTTON, taBox, CLASS_OH_BUTTON, !ENABLED);

        // MAKE SURE IT'S THE TABLE THAT ALWAYS GROWS IN THE LEFT PANE
        VBox.setVgrow(taTable, Priority.ALWAYS);

        // INIT THE HEADER ON THE RIGHT
        VBox rightPane = ohBuilder.buildVBox(OH_RIGHT_PANE, null, CLASS_OH_PANE, ENABLED);
        HBox officeHoursHeaderBox = ohBuilder.buildHBox(OH_OFFICE_HOURS_HEADER_PANE, rightPane, CLASS_OH_PANE, ENABLED);
        Text eeeee = new Text("                             ");

        ohBuilder.buildLabel(OH_OFFICE_HOURS_HEADER_LABEL, officeHoursHeaderBox, CLASS_OH_HEADER_LABEL, ENABLED);
        officeHoursHeaderBox.getChildren().add(eeeee);
        Text startTime = ohBuilder.buildSmallLabel(starttime_text, officeHoursHeaderBox);
        officeHoursHeaderBox.getChildren().add(startTime);
        Text eeeebbbbbbbbe = new Text("         ");
        officeHoursHeaderBox.getChildren().add(eeeebbbbbbbbe);
        times = new ComboBox();
        officeHoursHeaderBox.getChildren().add(times);

        times.getItems().addAll(
                "9:00am", "10:00am", "11:00am", "12:00pm", "1:00pm", "2:00pm", "3:00pm", "4:00pm", "5:00pm", "6:00pm",
                "7:00pm", "8:00pm", "9:00pm"
        );

        Text endtime = ohBuilder.buildSmallLabel(endtime_text, officeHoursHeaderBox);
        Text vvdvv = new Text("         ");
        officeHoursHeaderBox.getChildren().add(vvdvv);
        officeHoursHeaderBox.getChildren().add(endtime);
        Text vvvv = new Text("         ");
        officeHoursHeaderBox.getChildren().add(vvvv);
        entimes = new ComboBox();
        officeHoursHeaderBox.getChildren().add(entimes);

        entimes.getItems().addAll(
                "9:00am", "10:00am", "11:00am", "12:00pm", "1:00pm", "2:00pm", "3:00pm", "4:00pm", "5:00pm", "6:00pm",
                "7:00pm", "8:00pm", "9:00pm"
        );
        // SETUP THE OFFICE HOURS TABLE

        TableView<TimeSlot> officeHoursTable = ohBuilder.buildTableView(OH_OFFICE_HOURS_TABLE_VIEW, rightPane, CLASS_OH_OFFICE_HOURS_TABLE_VIEW, ENABLED);
        setupOfficeHoursColumn(OH_START_TIME_TABLE_COLUMN, officeHoursTable, CLASS_OH_TIME_COLUMN, "startTime");
        setupOfficeHoursColumn(OH_END_TIME_TABLE_COLUMN, officeHoursTable, CLASS_OH_TIME_COLUMN, "endTime");
        setupOfficeHoursColumn(OH_MONDAY_TABLE_COLUMN, officeHoursTable, CLASS_OH_DAY_OF_WEEK_COLUMN, "monday");
        setupOfficeHoursColumn(OH_TUESDAY_TABLE_COLUMN, officeHoursTable, CLASS_OH_DAY_OF_WEEK_COLUMN, "tuesday");
        setupOfficeHoursColumn(OH_WEDNESDAY_TABLE_COLUMN, officeHoursTable, CLASS_OH_DAY_OF_WEEK_COLUMN, "wednesday");
        setupOfficeHoursColumn(OH_THURSDAY_TABLE_COLUMN, officeHoursTable, CLASS_OH_DAY_OF_WEEK_COLUMN, "thursday");
        setupOfficeHoursColumn(OH_FRIDAY_TABLE_COLUMN, officeHoursTable, CLASS_OH_DAY_OF_WEEK_COLUMN, "friday");

        // MAKE SURE IT'S THE TABLE THAT ALWAYS GROWS IN THE LEFT PANE
        VBox.setVgrow(officeHoursTable, Priority.ALWAYS);
        VBox ohPane = new VBox();
        ohPane.getChildren().add(leftPane);
        ohPane.getChildren().add(rightPane);

        // BOTH PANES WILL NOW GO IN A SPLIT PANE
        VBox sitePane = new VBox();

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));
        OfficeHoursData data = (OfficeHoursData) app.getDataComponent();
        InstructorPrototype instructor = data.getinstructor();
        // Category in column 2, row 1
        final ComboBox emailComboBox = ohBuilder.buildCombBox(SUBJECT_COMBO, grid, CBOB, ENABLED);
        emailComboBox.getItems().addAll(
                "CSE"
        );
        emailComboBox.setEditable(true);
        emailComboBox.setValue("CSE");
        instructor.setSubject(emailComboBox);
        OfficeHoursController controller = new OfficeHoursController((OfficeHoursApp) app);

        final ComboBox semesterComboBox = ohBuilder.buildCombBox(SEMESTER_COMBO, grid, CBOB, ENABLED);
        semesterComboBox.getItems().addAll(
                "Fall", "Spring", "Summer", "Winter"
        );
        instructor.setSemester(semesterComboBox);
        semesterComboBox.setEditable(true);
        semesterComboBox.setValue("Fall");

        final ComboBox numberComboBox = ohBuilder.buildCombBox(NUMBER_COMBO, grid, CBOB, ENABLED);
        numberComboBox.getItems().addAll(
                "219");
        numberComboBox.setEditable(true);
        instructor.setNumber(numberComboBox);
        numberComboBox.setValue("219");

        final ComboBox yearComboBox = ohBuilder.buildCombBox(YEAR_COMBO, grid, CBOB, ENABLED);;
        instructor.setYear(yearComboBox);
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int nextYear = currentYear + 1;
        yearComboBox.getItems().addAll(
                "" + currentYear, "" + nextYear
        );
        yearComboBox.setEditable(true);
        yearComboBox.setValue("" + currentYear);

        // Category in column 2, row 1
        grid.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        Text category = ohBuilder.buildBigLabel(BANNER_TITLE, grid);
        grid.add(category, 0, 0);
        Text t = ohBuilder.buildSmallLabel(SUBJECT_TITLE, grid);
        grid.add(t, 0, 1);
        Text e = ohBuilder.buildSmallLabel(SEMESTER_TITLE, grid);
        grid.add(e, 0, 2);
        Text w = ohBuilder.buildSmallLabel(TITLE_TITLE, grid);
        grid.add(w, 0, 3);
        Text u = ohBuilder.buildSmallLabel(EXPORT_DIR_TITLE, grid);
        grid.add(u, 0, 4);
        Text ttt = new Text();
        grid.add(ttt, 0, 5);

        Text b = ohBuilder.buildSmallLabel(NUMBER_TITLE, grid);
        grid.add(b, 3, 1);
        Text m = ohBuilder.buildSmallLabel(YEAR_TITLE, grid);
        grid.add(m, 3, 2);
        String courseName = emailComboBox.getValue().toString();
        String courseNumber = numberComboBox.getValue().toString();
        String semesterSeason = semesterComboBox.getValue().toString();
        String semesterYear = yearComboBox.getValue().toString();
        String bob = new String(".\\export\\" + courseName + "_" + courseNumber + "_" + semesterSeason + "_" + semesterYear + "\\public_html");
        Text asd = ohBuilder.buildTEXT(EXPORT_DIR, grid, CBOB, ENABLED);
        Label label = new Label();
        StringProperty pSubject = emailComboBox.getEditor().textProperty();
        StringProperty pNumber = numberComboBox.getEditor().textProperty();
        StringProperty pSemester = semesterComboBox.getEditor().textProperty();
        StringProperty pYear = yearComboBox.getEditor().textProperty();

        StringExpression pExport = Bindings.concat(".\\export\\", pSubject, "_", pNumber, "_", pSemester, "_", pYear, "\\public_html");

        asd.textProperty().bind(pExport);

        grid.add(asd, 1, 4);
        TextField titlefield = ohBuilder.buildCustomTextField(BANNER_TITLE, grid, CBOB, ENABLED);

        grid.add(titlefield, 1, 3);
        grid.add(emailComboBox, 1, 1);
        grid.add(semesterComboBox, 1, 2);
        grid.add(numberComboBox, 4, 1);
        grid.add(yearComboBox, 4, 2);
        sitePane.getChildren().add(grid);

        grid.setStyle("-fx-background-color:  #ffcc80;");

        GridPane stylepane = new GridPane();
        Text stylename = ohBuilder.buildBigLabel(STYLE_TITLE, stylepane);
        stylepane.add(stylename, 0, 0);
        Button faviconbutton = ohBuilder.basicButton(FAVICON_TEXT, stylepane);

        stylepane.add(faviconbutton, 0, 1);

        ImageView simpleImage = ohBuilder.buildImageView(FAVICON_IMAGE, stylepane, CBOB, ENABLED);

        faviconbutton.setOnAction(a -> {
            try {
                controller.findImage(simpleImage, "f");
            } catch (MalformedURLException ex) {
            }
        });

        stylepane.add(simpleImage, 1, 1);

        ImageView darkRedImage = ohBuilder.buildImageView(NAVBAR_IMAGE, stylepane, CBOB, ENABLED);

        stylepane.add(darkRedImage, 1, 2);

        Button navbarbutton = ohBuilder.basicButton(NAVBARTEXT, stylepane);
        stylepane.add(navbarbutton, 0, 2);
        ImageView whiteRedImage = ohBuilder.buildImageView(LEFT_FOOTER, stylepane, CBOB, ENABLED);

        navbarbutton.setOnAction(a -> {
            try {
                controller.findImage(darkRedImage, "n");
            } catch (MalformedURLException ex) {
            }
        });

        stylepane.add(whiteRedImage, 1, 3);

        Button leftfooterbutton = ohBuilder.basicButton(LEFTFOOTER_TEXT, stylepane);
        stylepane.add(leftfooterbutton, 0, 3);
        Button rightfooterbutton = ohBuilder.basicButton(RIGHTFOOTER_TEXT, stylepane);
        stylepane.add(rightfooterbutton, 0, 4);
        ImageView csImage = ohBuilder.buildImageView(RIGHT_FOOTER, stylepane, CBOB, ENABLED);

        stylepane.add(csImage, 1, 4);

        rightfooterbutton.setOnAction(a -> {
            try {
                controller.findImage(csImage, "r");
            } catch (MalformedURLException ex) {
            }
        });

        leftfooterbutton.setOnAction(a -> {
            try {
                controller.findImage(whiteRedImage, "l");
            } catch (MalformedURLException ex) {
            }
        });

        Text hiii = new Text();
        stylepane.add(hiii, 0, 5);

        Text fontsandsheets = ohBuilder.buildSmallLabel(FONTS_SHEETS_TEXT, stylepane);
        stylepane.add(fontsandsheets, 0, 6);

        final ComboBox fontSheet = ohBuilder.buildCombBox(FONTSHEET, stylepane, CBOB, ENABLED);
        fontSheet.getItems().addAll(
                "christmas.css");
        fontSheet.setEditable(true);

        stylepane.add(fontSheet, 1, 6);

        Text style_detail = ohBuilder.buildSmallLabel(STYLE_DETAIL, stylepane);

        GridPane styleMAIN = new GridPane();
        styleMAIN.add(stylepane, 0, 0);
        styleMAIN.setStyle("-fx-background-color:  #ffcc80	;");
        styleMAIN.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        Text extra = new Text();
        styleMAIN.add(extra, 0, 1);

        styleMAIN.add(style_detail, 0, 2);

        GridPane instructorMAINpane = new GridPane();
        GridPane instructorpane = new GridPane();
        instructorMAINpane.add(instructorpane, 0, 0);
        instructorpane.setStyle("-fx-background-color:  #D3D3D3	;");
        instructorMAINpane.setStyle("-fx-background-color:  #D3D3D3	;");

        Text instructorname = ohBuilder.buildBigLabel(INSTRUCTOR_TITLE, instructorpane);
        instructorpane.add(instructorname, 0, 0);
        instructorMAINpane.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        Text instnamelabel = ohBuilder.buildSmallLabel(INSTRUCTOR_NAME, instructorpane);
        instructorpane.add(instnamelabel, 0, 2);
        TextField instnametitlefield = ohBuilder.buildCustomTextField(INSTRUCTOR_NAME_TEXT_FIELD, instructorpane, CLASS_OH_TEXT_FIELD, ENABLED);
        AppGUIModule gui = app.getGUIModule();

        instnametitlefield.setText(instructor.getName());
        instructorpane.add(instnametitlefield, 1, 2);
        TextField instemailtitlefield = ohBuilder.buildCustomTextField(INSTRUCTOR_EMAIL_TEXT_FIELD, instructorpane, CLASS_OH_TEXT_FIELD, ENABLED);
        instructorpane.add(instemailtitlefield, 1, 3);
        instemailtitlefield.setText(instructor.getEmail());
        Text instemaillabel = ohBuilder.buildSmallLabel(EMAIL_NAME, instructorpane);
        instructorpane.add(instemaillabel, 0, 3);
        Text roomlabel = ohBuilder.buildSmallLabel(ROOM_NAME, instructorpane);
        instructorpane.add(roomlabel, 4, 2);
        Text hplabel = ohBuilder.buildSmallLabel(HP_NAME, instructorpane);
        instructorpane.add(hplabel, 4, 3);
        TextField instroomtitlefield = ohBuilder.buildCustomTextField(INSTRUCTOR_ROOM_TEXT_FIELD, instructorpane, CLASS_OH_TEXT_FIELD, ENABLED);
        instroomtitlefield.setText(instructor.getRoom());
        instructorpane.add(instroomtitlefield, 5, 2);
        TextField insthptitlefield = ohBuilder.buildCustomTextField(INSTRUCTOR_HP_TEXT_FIELD, instructorpane, CLASS_OH_TEXT_FIELD, ENABLED);
        insthptitlefield.setText(instructor.getHomepage());
        instructorpane.add(insthptitlefield, 5, 3);
        TitledPane tp = ohBuilder.buildTitledPane(OFFICE_H, instructorMAINpane);
        TextArea instructorhtml = ohBuilder.buildTextArea(INSTRUCTOR_TEXT_AREA, instructorpane, CLASS_OH_TEXT_FIELD, ENABLED);
        tp.setContent(instructorhtml);
        instructorhtml.setText(instructor.gethtml());
        Text space = new Text(" ");
        instructorpane.add(space, 0, 6);
        tp.setExpanded(false);
        instructorMAINpane.add(tp, 0, 1);

        //for pages box
        GridPane pages = new GridPane();
        sitePane.getChildren().add(pages);
        pages.setStyle("-fx-background-color:  #D3D3D3	;");
        sitePane.getChildren().add(styleMAIN);
        sitePane.getChildren().add(instructorMAINpane);
        sitePane.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        pages.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        Text pagesname = ohBuilder.buildBigLabel(PAGE_TITLE, pages);
        pages.add(pagesname, 0, 0);
        CheckBox cb = ohBuilder.buildCheckBox(HOME_CB, HOME_TEXT, CBOB, pages, ENABLED);

        cb.setIndeterminate(false);
        pages.add(cb, 0, 2);

        CheckBox syallbuscb = ohBuilder.buildCheckBox(SYLLABUS_CB, SYLLABUS_TEXT, CBOB, pages, ENABLED);
        syallbuscb.setIndeterminate(false);
        pages.add(syallbuscb, 5, 2);
        CheckBox schedulecb = ohBuilder.buildCheckBox(SCHEDULE_CB, SCHEDULE_TEXT, CBOB, pages, ENABLED);
        schedulecb.setIndeterminate(false);
        pages.add(schedulecb, 10, 2);
        workspace = new BorderPane();
        CheckBox hwcb = ohBuilder.buildCheckBox(HW_CB, HW_TEXT, CBOB, pages, ENABLED);
        hwcb.setIndeterminate(false);
        pages.add(hwcb, 15, 2);
        Text spaceppew = new Text();
        pages.add(spaceppew, 0, 3);
        workspace = new BorderPane();
        tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
        Tab tab = ohBuilder.buildTab(SITE_TAB, tabPane);
        tab.setStyle("-fx-background-color: #ffcc66;-fx-pref-width: 220");
        ScrollPane s1 = new ScrollPane();
        s1.setContent(sitePane);
        s1.setFitToWidth(ENABLED);
        tab.setContent(s1);

        VBox syllabusPane = new VBox();
        //  syllabusPane.setStyle("-fx-background-color: #ffd699	;");
        Text syllabus_c = ohBuilder.buildBigLabel(SYLLABUS_COMP, pages);
        syllabusPane.getChildren().add(syllabus_c);
        Text fgh = new Text();
        syllabusPane.getChildren().add(fgh);

        TitledPane description = ohBuilder.buildTitledPaneinV(Description_text, syllabusPane);
        description.setStyle("-fx-background-color:  #D3D3D3	;");
        TextArea descriptionarea = ohBuilder.buildTextArea(DESCRIPTION_TEXT_AREA, syllabusPane, CLASS_OH_TEXT_FIELD, ENABLED);
        description.setContent(descriptionarea);
        Text dspace = new Text("\n");
        syllabusPane.getChildren().add(description);
        syllabusPane.getChildren().add(dspace);
        description.setExpanded(false);

        TitledPane topics = ohBuilder.buildTitledPaneinV(topics_text, syllabusPane);
        topics.setStyle("-fx-background-color:  #D3D3D3	;");
        TextArea topicsarea = ohBuilder.buildTextArea(TOPICS_TEXT_AREA, syllabusPane, CLASS_OH_TEXT_FIELD, ENABLED);
        topics.setContent(topicsarea);
        Text bb = new Text("\n");
        syllabusPane.getChildren().add(topics);
        syllabusPane.getChildren().add(bb);
        topics.setExpanded(false);

        TitledPane prerequisites = ohBuilder.buildTitledPaneinV(prerquisites_text, syllabusPane);
        prerequisites.setStyle("-fx-background-color:  #D3D3D3	;");
        TextArea prearea = ohBuilder.buildTextArea(PPREREQUISITES_TEXT_AREA, syllabusPane, CLASS_OH_TEXT_FIELD, ENABLED);
        prerequisites.setContent(prearea);
        Text hh = new Text("\n");
        syllabusPane.getChildren().add(prerequisites);
        syllabusPane.getChildren().add(hh);
        prerequisites.setExpanded(false);

        TitledPane outcomes = ohBuilder.buildTitledPaneinV(outcomes_text, syllabusPane);
        outcomes.setStyle("-fx-background-color:  #D3D3D3	;");
        TextArea outcomesarea = ohBuilder.buildTextArea(OUTCOMES_TEXT_AREA, syllabusPane, CLASS_OH_TEXT_FIELD, ENABLED);
        outcomes.setContent(outcomesarea);
        Text jj = new Text("\n");
        syllabusPane.getChildren().add(outcomes);
        syllabusPane.getChildren().add(jj);
        outcomes.setExpanded(false);

        TitledPane textbooks = ohBuilder.buildTitledPaneinV(textbooks_text, syllabusPane);
        textbooks.setStyle("-fx-background-color:  #D3D3D3	;");
        TextArea textbooksarea = ohBuilder.buildTextArea(BOOKS_TEXT_AREA, syllabusPane, CLASS_OH_TEXT_FIELD, ENABLED);
        textbooks.setContent(textbooksarea);
        Text ll = new Text("\n");
        syllabusPane.getChildren().add(textbooks);
        syllabusPane.getChildren().add(ll);
        textbooks.setExpanded(false);

        TitledPane gc = ohBuilder.buildTitledPaneinV(gc_text, syllabusPane);
        gc.setStyle("-fx-background-color:  #D3D3D3	;");
        TextArea gcarea = ohBuilder.buildTextArea(GRADING_COMPONENTS_TEXT_AREA, syllabusPane, CLASS_OH_TEXT_FIELD, ENABLED);
        gc.setContent(gcarea);
        Text mm = new Text("\n");
        syllabusPane.getChildren().add(gc);
        syllabusPane.getChildren().add(mm);
        gc.setExpanded(false);
        TitledPane gn = ohBuilder.buildTitledPaneinV(gn_text, syllabusPane);
        gn.setStyle("-fx-background-color:  #D3D3D3	;");
        TextArea gnarea = ohBuilder.buildTextArea(GRADING_NOTES_TEXT_AREA, syllabusPane, CLASS_OH_TEXT_FIELD, ENABLED);
        gn.setContent(gnarea);
        Text oo = new Text("\n");
        syllabusPane.getChildren().add(gn);
        syllabusPane.getChildren().add(oo);
        gn.setExpanded(false);

        TitledPane ad = ohBuilder.buildTitledPaneinV(ad_text, syllabusPane);
        ad.setStyle("-fx-background-color:  #D3D3D3	;");
        TextArea adarea = ohBuilder.buildTextArea(AD_TEXT_AREA, syllabusPane, CLASS_OH_TEXT_FIELD, ENABLED);
        ad.setContent(adarea);
        Text xx = new Text("\n");
        syllabusPane.getChildren().add(ad);
        syllabusPane.getChildren().add(xx);
        ad.setExpanded(false);

        TitledPane sa = ohBuilder.buildTitledPaneinV(sa_text, syllabusPane);
        sa.setStyle("-fx-background-color:  #D3D3D3	;");
        TextArea saarea = ohBuilder.buildTextArea(SPEC_ASSIS_TEXT_AREA, syllabusPane, CLASS_OH_TEXT_FIELD, ENABLED);
        sa.setContent(saarea);
        Text kk = new Text("\n");
        syllabusPane.getChildren().add(sa);
        syllabusPane.getChildren().add(kk);
        sa.setExpanded(false);

        ScrollPane s2 = new ScrollPane();
        s2.setContent(syllabusPane);
        s2.setStyle("-fx-background: #ffd699	;");
        s2.setFitToWidth(ENABLED);
        s2.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        // s2.setFitToHeight(ENABLED);
        Tab qw = ohBuilder.buildTab(SYLLABUS_TAB, tabPane);
        HBox syallabus_hbox = new HBox();
        syallabus_hbox.getChildren().add(new Label("syllabus"));
        syallabus_hbox.setAlignment(Pos.CENTER);
        qw.setContent(s2);

        Tab we = ohBuilder.buildTab(MEETING_TIMES_TAB, tabPane);

        VBox entireMeetingTimePane = new VBox();
        VBox lectures = new VBox();
        HBox lecturestop = new HBox(10);
        lecturestop.setStyle("-fx-background: #ffd699	;");

        Button lecturesADD = new Button("+");
        lecturesADD.setOnAction(p -> {
            controller.processAddLectures();

        });
        lecturestop.getChildren().add(lecturesADD);
        Button lecturesSUBTRACT = new Button("-");

        lecturestop.getChildren().add(lecturesSUBTRACT);
        Text lectureslabel = ohBuilder.buildMediumLabel(LECTURES_TEXT, lecturestop);
        lecturestop.getChildren().add(lectureslabel);
        lectures.getChildren().add(lecturestop);
        lectures.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        TableView<LecturesPrototype> jhg = ohBuilder.buildTableView(LECTURES_TABLE_VIEW, lectures, CLASS_OH_OFFICE_HOURS_TABLE_VIEW, ENABLED);
        jhg.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        makeLectureColumn(SECTION_COLUMN, jhg, CLASS_OH_DAY_OF_WEEK_COLUMN, "section");
        makeLectureColumn(DAYS_COLUMN, jhg, CLASS_OH_DAY_OF_WEEK_COLUMN, "days");
        makeLectureColumn(TIME_COLUMN, jhg, CLASS_OH_DAY_OF_WEEK_COLUMN, "times");
        makeLectureColumn(ROOM_COLUMN, jhg, CLASS_OH_DAY_OF_WEEK_COLUMN, "room");

        lecturesSUBTRACT.setOnAction(p -> {
            controller.processRemoveLectures(jhg.getSelectionModel().getSelectedItem());

        });

        jhg.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if (mouseEvent.getClickCount() == 2) {
                        LecturesPrototype chosen = jhg.getSelectionModel().getSelectedItem();
                        Stage window = app.getGUIModule().getWindow();
                        boolean df = AppDialogsFacade.showEditLectureDialog(chosen.getDays(), chosen.getRoom(), chosen.getSection(), chosen.getTimes(), window);
                        LecturesPrototype fff = new LecturesPrototype(AppDialogsFacade.section, AppDialogsFacade.days, AppDialogsFacade.times, AppDialogsFacade.rooms);
                        if (df == true) {
                            controller.processEditLecture(chosen, fff);
                        }

                        jhg.refresh();

                    } else {

                    }
                }
            }
        });
        entireMeetingTimePane.getChildren().add(lectures);

        VBox recitations = new VBox();
        HBox recitationstop = new HBox(10);
        recitationstop.setStyle("-fx-background: #ffd699	;");
        Button recitationsadd = new Button("+");
        recitationsadd.setOnAction(p -> {
            controller.processAddRecitations();

        });
        recitationstop.getChildren().add(recitationsadd);
        Button recitationsubtract = new Button("-");

        recitationstop.getChildren().add(recitationsubtract);
        Text recitationslabel = ohBuilder.buildMediumLabel(RECITATIONS_LABEL, lecturestop);
        recitationstop.getChildren().add(recitationslabel);
        Text recitationlabel = ohBuilder.buildMediumLabel(RECITATION_TEXT, recitationstop);
        recitationstop.getChildren().add(recitationlabel);
        recitations.getChildren().add(recitationstop);
        entireMeetingTimePane.getChildren().add(recitations);
        recitations.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        TableView<Recitation_Lab_Prototype> recitationTable = ohBuilder.buildTableView(RECITATION_TABLE, recitations, CLASS_OH_OFFICE_HOURS_TABLE_VIEW, ENABLED);
        recitationTable.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        makeRecitationsColumn(RECITATION_SECTION_COLUMN, recitationTable, CLASS_OH_DAY_OF_WEEK_COLUMN, "section");
        makeRecitationsColumn(DAYSANDTIME_COLUMN, recitationTable, CLASS_OH_DAY_OF_WEEK_COLUMN, "daystime");
        makeRecitationsColumn(RECITAION_ROOM_COLUMN, recitationTable, CLASS_OH_DAY_OF_WEEK_COLUMN, "room");
        makeRecitationsColumn(TA1_COLUMN, recitationTable, CLASS_OH_DAY_OF_WEEK_COLUMN, "ta1");
        makeRecitationsColumn(TA2_COLUMN, recitationTable, CLASS_OH_DAY_OF_WEEK_COLUMN, "ta2");
        recitationsubtract.setOnAction(qqqq -> {
            controller.processRemoveRecitations(recitationTable.getSelectionModel().getSelectedItem());

        });
        recitationTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if (mouseEvent.getClickCount() == 2) {
                        Recitation_Lab_Prototype chosen = recitationTable.getSelectionModel().getSelectedItem();
                        Stage window = app.getGUIModule().getWindow();
                        Boolean df = AppDialogsFacade.showEditRecitationsDialog(chosen.getDaystime(), chosen.getRoom(),
                                chosen.getSection(), chosen.getTa1(), chosen.getTa2(), window);
                        Recitation_Lab_Prototype fff = new Recitation_Lab_Prototype(AppDialogsFacade.RecitationSection, AppDialogsFacade.RecitationDT, AppDialogsFacade.RecitationRoom, AppDialogsFacade.RecitationTA1, AppDialogsFacade.RecitationTA2);
                        if (df == true) {
                            controller.processEditRecitation(chosen, fff);
                        }
                    }
                    recitationTable.refresh();

                }
            }
        });
        VBox labs = new VBox();
        HBox labstop = new HBox(10);
        labstop.setStyle("-fx-background: #ffd699	;");
        Button labsadd = new Button("+");
        labsadd.setOnAction(p -> {
            controller.processAddLab();

        });
        labstop.getChildren().add(labsadd);
        Button labssubtract = new Button("-");

        labstop.getChildren().add(labssubtract);
        Text labslabel = ohBuilder.buildMediumLabel(LABS_LABEL, labstop);
        labstop.getChildren().add(labslabel);
        labs.getChildren().add(labstop);
        entireMeetingTimePane.getChildren().add(labs);
        labs.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        TableView<Recitation_Lab_Prototype> LabsTable = ohBuilder.buildTableView(LABS_TABLE, labs, CLASS_OH_OFFICE_HOURS_TABLE_VIEW, ENABLED);
        LabsTable.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        makeRecitationsColumn(LABS_SECTION_COLUMN, LabsTable, CLASS_OH_DAY_OF_WEEK_COLUMN, "section");
        makeRecitationsColumn(LABS_DAYSANDTIME_COLUMN, LabsTable, CLASS_OH_DAY_OF_WEEK_COLUMN, "daystime");
        makeRecitationsColumn(LABS_ROOM_COLUMN, LabsTable, CLASS_OH_DAY_OF_WEEK_COLUMN, "room");
        makeRecitationsColumn(LABS_TA1_COLUMN, LabsTable, CLASS_OH_DAY_OF_WEEK_COLUMN, "ta1");
        makeRecitationsColumn(LABS_TA2_COLUMN, LabsTable, CLASS_OH_DAY_OF_WEEK_COLUMN, "ta2");
        labssubtract.setOnAction(p -> {

            controller.processRemoveLab(LabsTable.getSelectionModel().getSelectedItem());

        });
        LabsTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if (mouseEvent.getClickCount() == 2) {
                        Recitation_Lab_Prototype chosen = LabsTable.getSelectionModel().getSelectedItem();
                        Stage window = app.getGUIModule().getWindow();
                        Boolean df = AppDialogsFacade.showEditLabDialog(chosen.getDaystime(), chosen.getRoom(),
                                chosen.getSection(), chosen.getTa1(), chosen.getTa2(), window);
                        Recitation_Lab_Prototype fff = new Recitation_Lab_Prototype(AppDialogsFacade.LabSection, AppDialogsFacade.LabDT, AppDialogsFacade.LabRoom, AppDialogsFacade.LabTA1, AppDialogsFacade.LabTA2);
                        if (df == true) {
                            controller.processEditLab(chosen, fff);
                        }
                    }
                    LabsTable.refresh();

                }
            }
        });
        ScrollPane s3 = new ScrollPane();
        s3.setContent(entireMeetingTimePane);

        s3.setStyle("-fx-background: #D3D3D3	;");
        s3.setFitToWidth(ENABLED);
        we.setContent(s3);

        Tab tr = ohBuilder.buildTab(OFFICE_HOURS_TAB, tabPane);
        HBox ohtabbox = new HBox();
        ohtabbox.getChildren().add(new Label("oh"));
        ohtabbox.setAlignment(Pos.CENTER);
        tr.setContent(ohPane);

        Tab gg = ohBuilder.buildTab(SCHEDULE_TAB, tabPane);
        ScrollPane s4 = new ScrollPane();
        VBox entireSchedule = new VBox();
        GridPane CalenderBoundaries = new GridPane();
        CalenderBoundaries.setStyle("-fx-background-color: #D3D3D3		;");
        CalenderBoundaries.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        Text cblabel = ohBuilder.buildMediumLabel(CB_TEXT, CalenderBoundaries);
        CalenderBoundaries.add(cblabel, 0, 0);
        Text spcaebuffer = new Text();
        CalenderBoundaries.add(spcaebuffer, 0, 1);
        HBox cbBox = new HBox(10);
        Text mondaylabel = ohBuilder.buildSmallLabel(MONDAYCB_TEXT, CalenderBoundaries);
        CalenderBoundaries.add(cbBox, 0, 2);
        final DatePicker datePicker = ohBuilder.buildDateBox(MondayCalender, cbBox, CBOB, ENABLED);
        final DatePicker fridayCalender = ohBuilder.buildDateBox(FridayCalender, cbBox, CBOB, ENABLED);

        datePicker.setOnAction(new EventHandler() {
            public void handle(Event t) {
                LocalDate date = datePicker.getValue();
                LocalDate fridayDate = fridayCalender.getValue();
                try {
                    if (!(fridayDate.equals(null) || date.equals(null))) {
                        if (date.compareTo(fridayDate) > 0) {
                            datePicker.setValue(fridayDate);
                        }
                    }
                } catch (NullPointerException e) {

                }
            }

        });

        Text spbuffer = new Text();
        cbBox.getChildren().add(mondaylabel);
        cbBox.getChildren().add(datePicker);
        cbBox.getChildren().add(spbuffer);
        Text fridaylabel = ohBuilder.buildSmallLabel(FRIDAYCB_TEXT, CalenderBoundaries);
        cbBox.getChildren().add(fridaylabel);

        fridayCalender.setOnAction(new EventHandler() {
            public void handle(Event t) {
                LocalDate mondayDate = datePicker.getValue();
                LocalDate date = fridayCalender.getValue();

                if (!(date.equals(null) || mondayDate.equals(null))) {
                    if (mondayDate.compareTo(date) > 0) {
                        fridayCalender.setValue(mondayDate);
                    }
                }
            }
        });

        cbBox.getChildren().add(fridayCalender);
        Text fds = new Text("  ");
        CalenderBoundaries.add(fds, 0, 3);

        VBox schedulePane = new VBox();
        schedulePane.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        schedulePane.setStyle("-fx-background-color: #ffcc80;");
        HBox schduleTop = new HBox(10);
        schedulePane.getChildren().add(schduleTop);
        Button removeSchedule = new Button("-");
        TableView<SchedulePrototype> ScheduleTable = ohBuilder.buildTableView(SCHEDULE_TABLE, labs, CLASS_OH_OFFICE_HOURS_TABLE_VIEW, ENABLED);

        schduleTop.getChildren().add(removeSchedule);
        Text schduleBuilderText = ohBuilder.buildMediumLabel(SI_TEXT, schduleTop);
        schduleTop.getChildren().add(schduleBuilderText);

        schedulePane.getChildren().add(ScheduleTable);

        ScheduleTable.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        makeScheduleColumn(STYPE_COLUMN, ScheduleTable, CLASS_OH_DAY_OF_WEEK_COLUMN, "type");

        makeScheduleColumn(SDATE_COLUMN, ScheduleTable, CLASS_OH_DAY_OF_WEEK_COLUMN, "date");
        makeScheduleColumn(STITLE_COLUMN, ScheduleTable, CLASS_OH_DAY_OF_WEEK_COLUMN, "title");
        makeScheduleColumn(STOPIC_COLUMN, ScheduleTable, CLASS_OH_DAY_OF_WEEK_COLUMN, "topic");

        entireSchedule.getChildren().add(CalenderBoundaries);
        entireSchedule.getChildren().add(schedulePane);
        GridPane addedit = new GridPane();
        entireSchedule.getChildren().add(addedit);
        addedit.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        Text addi = ohBuilder.buildMediumLabel(SAI_TEXT, addedit);
        addedit.add(addi, 0, 0);
        Text fff = new Text();
        addedit.add(fff, 0, 1);
        Text addType = ohBuilder.buildSmallLabel(ADDType, addedit);
        addedit.add(addType, 0, 2);
        final ComboBox typeChoose = ohBuilder.buildCombBox(SCHEDULE_TYPE_COMBO, addedit, CBOB, ENABLED);
        typeChoose.getItems().addAll(
                "Holiday", "Lecture", "HW", "Recitation", "Reference"
        );

        addedit.add(typeChoose, 1, 2);

        Text oooo = new Text();
        addedit.add(oooo, 0, 3);
        Text addDate = ohBuilder.buildSmallLabel(ADDDATE, addedit);
        addedit.add(addDate, 0, 4);
        final DatePicker dateChoose = ohBuilder.buildDateBox(DATE_CHOOSE, addedit, CBOB, ENABLED);
        dateChoose.setOnAction(new EventHandler() {
            public void handle(Event t) {
                LocalDate date = dateChoose.getValue();
                LocalDate mondayDate = datePicker.getValue();
                try {
                    if (!(date == null || mondayDate == null)) {
                        if (mondayDate.compareTo(date) > 0) {
                            dateChoose.setValue(mondayDate);
                        }
                    }
                    LocalDate fridayDate = fridayCalender.getValue();
                    if (!(fridayDate == null || date == null)) {
                        if (date.compareTo(fridayDate) > 0) {
                            dateChoose.setValue(fridayDate);
                        }
                    }
                } catch (NullPointerException e) {

                }
            }
        });

        addedit.add(dateChoose, 1, 4);
        Text asfdsg = new Text();
        addedit.add(asfdsg, 0, 5);
        Text addTitle = ohBuilder.buildSmallLabel(ADDTITLE, addedit);
        addedit.add(addTitle, 0, 6);
        TextField titleTextfield = ohBuilder.buildCustomTextField(SCHEDULE_TITLE, addedit, CLASS_OH_TEXT_FIELD, ENABLED);
        addedit.add(titleTextfield, 1, 6);

        Text bbnn = new Text();
        addedit.add(bbnn, 0, 7);
        Text addTopic = ohBuilder.buildSmallLabel(ADDTOPIC, addedit);
        addedit.add(addTopic, 0, 8);
        TextField topicTextfield = ohBuilder.buildCustomTextField(SCHEDULE_TOPIC, addedit, CLASS_OH_TEXT_FIELD, ENABLED);
        addedit.add(topicTextfield, 1, 8);
        Text nbv = new Text();
        addedit.add(nbv, 0, 9);
        Text addLink = ohBuilder.buildSmallLabel(ADDLINK, addedit);
        addedit.add(addLink, 0, 10);
        TextField linktextfield = ohBuilder.buildCustomTextField(SCHEDULE_LINK, addedit, CLASS_OH_TEXT_FIELD, ENABLED);
        addedit.add(linktextfield, 1, 10);
        HBox buttonsselect = new HBox(20);
        Button editButton = ohBuilder.basicButton(ADDBUTTON_TEXT, buttonsselect);
        buttonsselect.getChildren().add(editButton);
        removeSchedule.setOnAction(s -> {
            SchedulePrototype scheduleType = ScheduleTable.getSelectionModel().getSelectedItem();
            controller.processScheduleRemoval(scheduleType);
            editButton.setText("Add");
        });
        editButton.setOnAction(p -> {
            if (editButton.getText().equals("Add")) {
                controller.processAddSchedule();
                SchedulePrototype schedule = data.getSchedule();
                schedule.setType("");
                typeChoose.setValue(schedule.getType());
                schedule.setDate("");
                dateChoose.setValue(null);
                titleTextfield.setText("");
                schedule.setTitle("");
                topicTextfield.setText("");
                schedule.setTopic("");
                linktextfield.setText("");
                schedule.setLink("");
                editButton.setText("Add");
            } else if (editButton.getText().equals("Edit")) {
                SchedulePrototype scheduleType = ScheduleTable.getSelectionModel().getSelectedItem();
                controller.processEditExistingSchedule(scheduleType);
                SchedulePrototype schedule = data.getSchedule();
                schedule.setType("");
                typeChoose.setValue(schedule.getType());
                schedule.setDate("");
                dateChoose.setValue(null);
                titleTextfield.setText("");
                schedule.setTitle("");
                topicTextfield.setText("");
                schedule.setTopic("");
                linktextfield.setText("");
                schedule.setLink("");
                editButton.setText("Add");
            }
        });
        ScheduleTable.setOnMouseClicked((MouseEvent event) -> {

            if (event.getButton().equals(MouseButton.PRIMARY)) {
                if (ScheduleTable.getSelectionModel().getSelectedItem() == null) {
                    SchedulePrototype schedule = data.getSchedule();
                    schedule.setType("");
                    typeChoose.setValue(schedule.getType());
                    schedule.setDate("");
                    dateChoose.setValue(null);
                    titleTextfield.setText("");
                    schedule.setTitle("");
                    topicTextfield.setText("");
                    schedule.setTopic("");
                    linktextfield.setText("");
                    schedule.setLink("");
                    editButton.setText("Add");
                } else {
                    SchedulePrototype scheduleType = ScheduleTable.getSelectionModel().getSelectedItem();
                    SchedulePrototype dataSchedule = data.getSchedule();
                    try {
                        editButton.setText("Edit");
                        dataSchedule.setType(scheduleType.getType());
                        typeChoose.setValue(scheduleType.getType());
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        dataSchedule.setDate(scheduleType.getDate());
                        LocalDate dateTime = LocalDate.parse(scheduleType.getDate(), formatter);
                        dateChoose.setValue(dateTime);
                        dataSchedule.setTitle(scheduleType.getTitle());
                        titleTextfield.setText(scheduleType.getTitle());
                        dataSchedule.setTopic(scheduleType.getTopic());
                        topicTextfield.setText(scheduleType.getTopic());
                        dataSchedule.setLink(scheduleType.getLink());
                        linktextfield.setText(scheduleType.getLink());
                    } catch (NullPointerException d) {

                    }
                }
            }
        });

        ScheduleTable.setRowFactory(new Callback<TableView<SchedulePrototype>, TableRow<SchedulePrototype>>() {
            @Override
            public TableRow<SchedulePrototype> call(TableView<SchedulePrototype> tableView2) {
                final TableRow<SchedulePrototype> row = new TableRow<>();
                row.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        final int index = row.getIndex();

                        if (index >= 0 && index < ScheduleTable.getItems().size() && ScheduleTable.getSelectionModel().isSelected(index)) {
                            ScheduleTable.getSelectionModel().clearSelection();
                            event.consume();
                        } else {

                        }
                    }
                });
                return row;
            }
        });

        Button clearButton = ohBuilder.basicButton(ClearBUTTON_TEXT, buttonsselect);
        clearButton.setOnAction(a -> {
            SchedulePrototype schedule = data.getSchedule();
            schedule.setType("");
            typeChoose.setValue(schedule.getType());
            schedule.setDate("");
            dateChoose.setValue(null);
            titleTextfield.setText("");
            schedule.setTitle("");
            topicTextfield.setText("");
            schedule.setTopic("");
            linktextfield.setText("");
            schedule.setLink("");
        });
        buttonsselect.getChildren().add(clearButton);
        addedit.add(buttonsselect, 0, 11);
        addedit.setStyle("-fx-background-color: #D3D3D3;");
        s4.setContent(entireSchedule);
        s4.setFitToWidth(ENABLED);
        gg.setContent(s4);

        tabPane.getSelectionModel().selectedItemProperty().addListener((ob, o, n) -> {

            n.setStyle("-fx-background-color: #ffcc66;-fx-pref-width: 220");
            o.setStyle("-fx-background-color: transparent;-fx-pref-width: 220 ");

        });

        // AND PUT EVERYTHING IN THE WORKSPACE
        ((BorderPane) workspace).setCenter(tabPane);
    }

    private void setupOfficeHoursColumn(Object columnId, TableView tableView, String styleClass, String columnDataProperty) {
        AppNodesBuilder builder = app.getGUIModule().getNodesBuilder();
        TableColumn<TeachingAssistantPrototype, String> column = builder.buildTableColumn(columnId, tableView, styleClass);
        column.setCellValueFactory(new PropertyValueFactory<TeachingAssistantPrototype, String>(columnDataProperty));
        column.prefWidthProperty().bind(tableView.widthProperty().multiply(1.0 / 7.0));
        column.setCellFactory(col -> {
            return new TableCell<TeachingAssistantPrototype, String>() {
                @Override
                protected void updateItem(String text, boolean empty) {
                    super.updateItem(text, empty);
                    if (text == null || empty) {
                        setText(null);
                        setStyle("");
                    } else {
                        // CHECK TO SEE IF text CONTAINS THE NAME OF
                        // THE CURRENTLY SELECTED TA
                        setText(text);
                        TableView<TeachingAssistantPrototype> tasTableView = (TableView) app.getGUIModule().getGUINode(OH_TAS_TABLE_VIEW);
                        TeachingAssistantPrototype selectedTA = tasTableView.getSelectionModel().getSelectedItem();
                        if (selectedTA == null) {
                            setStyle("");
                        } else if (text.contains(selectedTA.getName())) {
                            setStyle("-fx-background-color: red");
                        } else {
                            setStyle("");
                        }
                    }

                }
            };
        });
    }

    private void makeLectureColumn(Object columnId, TableView tableView, String styleClass, String columnDataProperty) {
        AppNodesBuilder builder = app.getGUIModule().getNodesBuilder();
        TableColumn<LecturesPrototype, String> column = builder.buildTableColumn(columnId, tableView, styleClass);
        column.setCellValueFactory(new PropertyValueFactory<>(columnDataProperty));
        column.prefWidthProperty().bind(tableView.widthProperty().multiply(1.0 / 4.0));

        column.setCellFactory(col -> {
            return new TableCell<LecturesPrototype, String>() {
                @Override
                protected void updateItem(String text, boolean empty) {
                    super.updateItem(text, empty);
                    if (text == null || empty) {
                        setText(null);
                        setStyle("");
                    } else {
                        setText(text);
                        // CHECK TO SEE IF text CONTAINS THE NAME OF
                        // THE CURRENTLY SELECTED TA

                    }
                }

            };
        });
    }

    private void makeScheduleColumn(Object columnId, TableView tableView, String styleClass, String columnDataProperty) {
        AppNodesBuilder builder = app.getGUIModule().getNodesBuilder();
        TableColumn<SchedulePrototype, String> column = builder.buildTableColumn(columnId, tableView, styleClass);
        column.setCellValueFactory(new PropertyValueFactory<>(columnDataProperty));
        column.prefWidthProperty().bind(tableView.widthProperty().multiply(1.0 / 4.0));
        column.setCellFactory(col -> {
            return new TableCell<SchedulePrototype, String>() {
                @Override
                protected void updateItem(String text, boolean empty) {
                    super.updateItem(text, empty);
                    if (text == null || empty) {
                        setText(null);
                        setStyle("");
                    } else {
                        // CHECK TO SEE IF text CONTAINS THE NAME OF
                        // THE CURRENTLY SELECTED TA
                        setText(text);
                        TableView<SchedulePrototype> tasTableView = (TableView) app.getGUIModule().getGUINode(SCHEDULE_TABLE);

                    }
                }
            };
        });
    }

    private void makeRecitationsColumn(Object columnId, TableView tableView, String styleClass, String columnDataProperty) {
        AppNodesBuilder builder = app.getGUIModule().getNodesBuilder();
        TableColumn<String, String> column = builder.buildTableColumn(columnId, tableView, styleClass);
        column.setCellValueFactory(new PropertyValueFactory<String, String>(columnDataProperty));
        column.prefWidthProperty().bind(tableView.widthProperty().multiply(1.0 / 5.0));
        column.setCellFactory(col -> {
            return new TableCell<String, String>() {
                @Override
                protected void updateItem(String text, boolean empty) {
                    super.updateItem(text, empty);
                    if (text == null || empty) {
                        setText(null);
                        setStyle("");
                    } else {
                        // CHECK TO SEE IF text CONTAINS THE NAME OF
                        // THE CURRENTLY SELECTED TA
                        setText(text);
                        TableView<TeachingAssistantPrototype> tasTableView = (TableView) app.getGUIModule().getGUINode(OH_TAS_TABLE_VIEW);
                        TeachingAssistantPrototype selectedTA = tasTableView.getSelectionModel().getSelectedItem();
                        if (selectedTA == null) {
                            setStyle("");
                        } else if (text.contains(selectedTA.getName())) {
                            setStyle("-fx-background-color: red");
                        } else {
                            setStyle("");
                        }
                    }
                }
            };
        });
    }

    public void changeEditButton() {
        AppGUIModule gui = app.getGUIModule();
        Button editButton = (Button) gui.getGUINode(ADDBUTTON_TEXT);
        editButton.setText("Add");

    }

    public void initControllers() {
        OfficeHoursController controller = new OfficeHoursController((OfficeHoursApp) app);
        AppGUIModule gui = app.getGUIModule();
        OfficeHoursData data = (OfficeHoursData) app.getDataComponent();
        InstructorPrototype instructor = data.getinstructor();
        ComboBox cssFile = (ComboBox) gui.getGUINode(FONTSHEET);

        cssFile.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    String bob = cssFile.getValue().toString();
                    if (!(cssFile.getItems().contains(bob))) {
                        cssFile.getItems().add(bob);
                    }
                } else {
                    String bob = cssFile.getValue().toString();
                    if (!(cssFile.getItems().contains(bob))) {
                        cssFile.getItems().add(bob);
                    }
                    controller.processEditPage(data.pageSubject, data.pageSemester, data.pageBNumber, data.pageYear, data.pageTitle,bob);

                }

            }
        });

        ComboBox subjectCombo = (ComboBox) gui.getGUINode(SUBJECT_COMBO);
        subjectCombo.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {

                if (newPropertyValue) {
                    String bob = subjectCombo.getValue().toString();
                    if (!(subjectCombo.getItems().contains(bob))) {
                        subjectCombo.getItems().add(bob);
                        instructor.setSubject(subjectCombo);

                    }
                } else {
                    String bob = subjectCombo.getValue().toString();
                    if (!(subjectCombo.getItems().contains(bob))) {
                        subjectCombo.getItems().add(bob);
                        instructor.setSubject(subjectCombo);

                    }
                    controller.processEditPage(bob, data.pageSemester, data.pageBNumber, data.pageYear, data.pageTitle,data.css);

                }

            }
        });

        ComboBox semesterCombo = (ComboBox) gui.getGUINode(SEMESTER_COMBO);
        semesterCombo.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    String bob = semesterCombo.getValue().toString();
                    if (!(semesterCombo.getItems().contains(bob))) {
                        semesterCombo.getItems().add(bob);
                        instructor.setSemester(semesterCombo);

                    }
                } else {
                    String bob = semesterCombo.getValue().toString();
                    if (!(semesterCombo.getItems().contains(bob))) {
                        semesterCombo.getItems().add(bob);
                        instructor.setSemester(semesterCombo);

                    }
                    System.out.println(bob + " bob");
                    controller.processEditPage(data.pageSubject, bob, data.pageBNumber, data.pageYear, data.pageTitle,data.css);

                }

            }
        });

        ComboBox numberCombo = (ComboBox) gui.getGUINode(NUMBER_COMBO);
        numberCombo.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    String bob = numberCombo.getValue().toString();

                    if (!(numberCombo.getItems().contains(bob))) {
                        numberCombo.getItems().add(bob);
                        instructor.setNumber(numberCombo);

                    }
                } else {
                    String bob = numberCombo.getValue().toString();
                    System.out.println(bob + " update");
                    if (!(numberCombo.getItems().contains(bob))) {
                        numberCombo.getItems().add(bob);
                        instructor.setNumber(numberCombo);

                    }
                    controller.processEditPage(data.pageSubject, data.pageSemester, bob, data.pageYear, data.pageTitle,data.css);

                }

            }
        });

        ComboBox yearCombo = (ComboBox) gui.getGUINode(YEAR_COMBO);
        yearCombo.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    String bob = yearCombo.getValue().toString();
                    if (!(yearCombo.getItems().contains(bob))) {
                        yearCombo.getItems().add(bob);
                        instructor.setYear(yearCombo);

                    }
                } else {
                    String bob = yearCombo.getValue().toString();
                    if (!(yearCombo.getItems().contains(bob))) {
                        yearCombo.getItems().add(bob);
                        instructor.setYear(yearCombo);

                    }
                    controller.processEditPage(data.pageSubject, data.pageSemester, data.pageBNumber, bob, data.pageTitle,data.css);

                }

            }
        });

        TextField title = (TextField) gui.getGUINode(BANNER_TITLE);
        data.pageTitle = title.getText();
        title.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                } else {
                    controller.processEditPage(data.pageSubject, data.pageSemester, data.pageBNumber, data.pageYear, title.getText(),data.css);

                }

            }
        });
        SyllabusPrototype syllabus = data.getSyllabus();
        SchedulePrototype schedule = data.getSchedule();
        ComboBox type = (ComboBox) gui.getGUINode(SCHEDULE_TYPE_COMBO);
        Button faviconbutton = (Button) gui.getGUINode(FAVICON_TEXT);
        ImageView simpleImage = (ImageView) gui.getGUINode(FAVICON_IMAGE);
        PropertiesManager props = PropertiesManager.getPropertiesManager();

        try {
            String simpleName = props.getProperty(SimpleLogo);
            String simplePath = props.getProperty(APP_PATH_IMAGES) + "/" + simpleName;
            data.setFavicon(simplePath);
            File simpleFile = new File(simplePath);
            BufferedImage bufferedImage = ImageIO.read(simpleFile);
            Image bannerImage = SwingFXUtils.toFXImage(bufferedImage, null);
            simpleImage.setImage(bannerImage);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        ImageView navbar = (ImageView) gui.getGUINode(NAVBAR_IMAGE);
        try {
            String darkredname = props.getProperty(DarkRedLogo);
            String darkredpath = props.getProperty(APP_PATH_IMAGES) + "/" + darkredname;
            data.setNavbar(darkredpath);
            File darkredfile = new File(darkredpath);
            BufferedImage darkredimage = ImageIO.read(darkredfile);
            Image darkredpng = SwingFXUtils.toFXImage(darkredimage, null);
            navbar.setImage(darkredpng);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        ImageView left = (ImageView) gui.getGUINode(LEFT_FOOTER);
        try {
            String whiteredname = props.getProperty(WhiteLogo);
            String whiteredpath = props.getProperty(APP_PATH_IMAGES) + "/" + whiteredname;
            data.setLeft(whiteredpath);
            File whiteredfile = new File(whiteredpath);
            BufferedImage whiteredimage = ImageIO.read(whiteredfile);
            Image whiteredpng = SwingFXUtils.toFXImage(whiteredimage, null);
            left.setImage(whiteredpng);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        ImageView right = (ImageView) gui.getGUINode(RIGHT_FOOTER);

        try {
            String csname = props.getProperty(SBUCSLOGO);
            String cspath = props.getProperty(APP_PATH_IMAGES) + "/" + csname;
            File csfile = new File(cspath);
            data.rightFooter = cspath;
            BufferedImage csbimage = ImageIO.read(csfile);
            Image cspng = SwingFXUtils.toFXImage(csbimage, null);
            right.setImage(cspng);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        type.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    type.setValue(schedule.getType());
                } else {
                    String oldDescription = schedule.getTopic();
                    String newDescri = type.getValue().toString();

                    if (!(oldDescription.equals(newDescri))) {
                        controller.processEditSchedule(newDescri, schedule.getDate(), schedule.getTitle(), schedule.getTopic(), schedule.getLink());
                    }

                }

            }
        });

        TextField scheduleTitle = ((TextField) gui.getGUINode(SCHEDULE_TITLE));
        scheduleTitle.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    scheduleTitle.setText(schedule.getTitle());
                } else {

                    String oldName = schedule.getTitle();
                    String newName = scheduleTitle.getText();
                    if (!(oldName.equals(newName))) {
                        controller.processEditSchedule(schedule.getType(), schedule.getDate(), newName, schedule.getTopic(), schedule.getLink());
                    }

                }

            }
        });
        ComboBox subject = (ComboBox) gui.getGUINode(SUBJECT_COMBO);

        TextField topicField = ((TextField) gui.getGUINode(SCHEDULE_TOPIC));
        topicField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    topicField.setText(schedule.getTopic());
                } else {
                    String oldName = schedule.getTopic();
                    String newName = topicField.getText();
                    if (!(oldName.equals(newName))) {
                        controller.processEditSchedule(schedule.getType(), schedule.getDate(), schedule.getTitle(), newName, schedule.getLink());
                    }

                }

            }
        });
        TextField linkField = ((TextField) gui.getGUINode(SCHEDULE_LINK));
        linkField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    linkField.setText(schedule.getLink());
                } else {
                    String oldName = schedule.getLink();
                    String newName = linkField.getText();
                    if (!(oldName.equals(newName))) {
                        controller.processEditSchedule(schedule.getType(), schedule.getDate(), schedule.getTitle(), schedule.getTopic(), newName);
                    }

                }

            }
        });

        DatePicker dateChoose = (DatePicker) gui.getGUINode(DATE_CHOOSE);
        dateChoose.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    if (!(schedule.getDate().equals("") || schedule.getDate() == null)) {
                        LocalDate dateTime = LocalDate.parse(schedule.getDate(), formatter);
                        dateChoose.setValue(dateTime);
                    } else {

                    }

                } else {
                    String oldDescription = schedule.getDate();
                    String newDescri = dateChoose.getValue().toString();

                    if (!(oldDescription.equals(newDescri))) {
                        controller.processEditSchedule(schedule.getType(), newDescri, schedule.getTitle(), schedule.getTopic(), schedule.getLink());
                    }

                }

            }
        });

        TextArea descriptionArea = ((TextArea) gui.getGUINode(DESCRIPTION_TEXT_AREA));
        descriptionArea.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    descriptionArea.setText(syllabus.getDescription());
                } else {
                    String oldDescription = syllabus.getDescription();
                    String newDescri = descriptionArea.getText();
                    if (!(oldDescription.equals(newDescri))) {
                        controller.processEditSyllabus(newDescri, syllabus.getTopics(), syllabus.getPrerequisites(),
                                syllabus.getOutcomes(), syllabus.getTextbooks(), syllabus.getGradedComponents(),
                                syllabus.getGradingNotes(), syllabus.getAcademicDishonesty(), syllabus.getSpecialAssistanse());
                    }

                }

            }
        });

        TextArea topicsArea = ((TextArea) gui.getGUINode(TOPICS_TEXT_AREA));
        topicsArea.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    topicsArea.setText(syllabus.getTopics());
                } else {
                    String oldDescription = syllabus.getTopics();
                    String newDescri = topicsArea.getText();
                    if (!(oldDescription.equals(newDescri))) {
                        controller.processEditSyllabus(syllabus.getDescription(), newDescri, syllabus.getPrerequisites(),
                                syllabus.getOutcomes(), syllabus.getTextbooks(), syllabus.getGradedComponents(),
                                syllabus.getGradingNotes(), syllabus.getAcademicDishonesty(), syllabus.getSpecialAssistanse());
                    }

                }

            }
        });
        TextArea preArea = ((TextArea) gui.getGUINode(PPREREQUISITES_TEXT_AREA));
        preArea.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    preArea.setText(syllabus.getPrerequisites());
                } else {
                    String oldDescription = syllabus.getPrerequisites();
                    String newDescri = preArea.getText();
                    if (!(oldDescription.equals(newDescri))) {
                        controller.processEditSyllabus(syllabus.getDescription(), syllabus.getTopics(), newDescri,
                                syllabus.getOutcomes(), syllabus.getTextbooks(), syllabus.getGradedComponents(),
                                syllabus.getGradingNotes(), syllabus.getAcademicDishonesty(), syllabus.getSpecialAssistanse());
                    }

                }

            }
        });
        TextArea outArea = ((TextArea) gui.getGUINode(OUTCOMES_TEXT_AREA));
        outArea.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    outArea.setText(syllabus.getOutcomes());
                } else {
                    String oldDescription = syllabus.getOutcomes();
                    String newDescri = outArea.getText();
                    if (!(oldDescription.equals(newDescri))) {
                        controller.processEditSyllabus(syllabus.getDescription(), syllabus.getTopics(), syllabus.getPrerequisites(),
                                newDescri, syllabus.getTextbooks(), syllabus.getGradedComponents(),
                                syllabus.getGradingNotes(), syllabus.getAcademicDishonesty(), syllabus.getSpecialAssistanse());
                    }

                }

            }
        });
        TextArea booksArea = ((TextArea) gui.getGUINode(BOOKS_TEXT_AREA));
        booksArea.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    booksArea.setText(syllabus.getTextbooks());
                } else {
                    String oldDescription = syllabus.getTextbooks();
                    String newDescri = booksArea.getText();
                    if (!(oldDescription.equals(newDescri))) {
                        controller.processEditSyllabus(syllabus.getDescription(), syllabus.getTopics(), syllabus.getPrerequisites(),
                                syllabus.getOutcomes(), newDescri, syllabus.getGradedComponents(),
                                syllabus.getGradingNotes(), syllabus.getAcademicDishonesty(), syllabus.getSpecialAssistanse());
                    }

                }

            }
        });
        TextArea GCAREA = ((TextArea) gui.getGUINode(GRADING_COMPONENTS_TEXT_AREA));
        GCAREA.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    GCAREA.setText(syllabus.getGradedComponents());
                } else {
                    String oldDescription = syllabus.getGradedComponents();
                    String newDescri = GCAREA.getText();
                    if (!(oldDescription.equals(newDescri))) {
                        controller.processEditSyllabus(syllabus.getDescription(), syllabus.getTopics(), syllabus.getPrerequisites(),
                                syllabus.getOutcomes(), syllabus.getTextbooks(), newDescri,
                                syllabus.getGradingNotes(), syllabus.getAcademicDishonesty(), syllabus.getSpecialAssistanse());
                    }

                }

            }
        });
        TextArea GNAREA = ((TextArea) gui.getGUINode(GRADING_NOTES_TEXT_AREA));
        GNAREA.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    GNAREA.setText(syllabus.getGradingNotes());
                } else {
                    String oldDescription = syllabus.getTopics();
                    String newDescri = GNAREA.getText();
                    if (!(oldDescription.equals(newDescri))) {
                        controller.processEditSyllabus(syllabus.getDescription(), syllabus.getTopics(), syllabus.getPrerequisites(),
                                syllabus.getOutcomes(), syllabus.getTextbooks(), syllabus.getGradedComponents(),
                                newDescri, syllabus.getAcademicDishonesty(), syllabus.getSpecialAssistanse());
                    }

                }

            }
        });
        TextArea ADAREA = ((TextArea) gui.getGUINode(AD_TEXT_AREA));
        ADAREA.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    ADAREA.setText(syllabus.getAcademicDishonesty());
                } else {
                    String oldDescription = syllabus.getAcademicDishonesty();
                    String newDescri = ADAREA.getText();
                    if (!(oldDescription.equals(newDescri))) {
                        controller.processEditSyllabus(syllabus.getDescription(), syllabus.getTopics(), syllabus.getPrerequisites(),
                                syllabus.getOutcomes(), syllabus.getTextbooks(), syllabus.getGradedComponents(),
                                syllabus.getGradingNotes(), newDescri, syllabus.getSpecialAssistanse());
                    }

                }

            }
        });
        TextArea SPECAREA = ((TextArea) gui.getGUINode(SPEC_ASSIS_TEXT_AREA));
        SPECAREA.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    SPECAREA.setText(syllabus.getSpecialAssistanse());
                } else {
                    String oldDescription = syllabus.getSpecialAssistanse();
                    String newDescri = SPECAREA.getText();
                    if (!(oldDescription.equals(newDescri))) {
                        controller.processEditSyllabus(syllabus.getDescription(), syllabus.getTopics(), syllabus.getPrerequisites(),
                                syllabus.getOutcomes(), syllabus.getTextbooks(), syllabus.getGradedComponents(),
                                syllabus.getGradingNotes(), syllabus.getAcademicDishonesty(), newDescri);
                    }

                }

            }
        });

        TextArea htmlFiled = ((TextArea) gui.getGUINode(INSTRUCTOR_TEXT_AREA));

        htmlFiled.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    htmlFiled.setText(instructor.gethtml());
                } else {
                    String oldhtml = instructor.gethtml();
                    String newhtml = htmlFiled.getText();
                    if (!(oldhtml.equals(newhtml))) {
                        controller.processEditInstructor(instructor.getName(), instructor.getEmail(), instructor.getRoom(), instructor.getHomepage(), newhtml);
                    }

                }

            }
        });
        TextField instNameField = ((TextField) gui.getGUINode(INSTRUCTOR_NAME_TEXT_FIELD));
        instNameField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    instNameField.setText(data.getInstructorName());
                } else {
                    OfficeHoursData data = (OfficeHoursData) app.getDataComponent();
                    String oldName = data.getInstructorName();
                    String newName = instNameField.getText();
                    if (!(oldName.equals(newName))) {
                        controller.processEditInstructor(newName, instructor.getEmail(), instructor.getRoom(), instructor.getHomepage(), instructor.gethtml());
                    }

                }

            }
        });

        TextField instEmailField = ((TextField) gui.getGUINode(INSTRUCTOR_EMAIL_TEXT_FIELD));
        instEmailField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    instEmailField.setText(instructor.getEmail());
                } else {
                    String oldEmail = instructor.getEmail();
                    String newEmail = instEmailField.getText();
                    if (!(oldEmail.equals(newEmail))) {
                        controller.processEditInstructor(instructor.getName(), newEmail, instructor.getRoom(), instructor.getHomepage(), instructor.gethtml());
                    }

                }

            }
        });
        TextField instRoomField = ((TextField) gui.getGUINode(INSTRUCTOR_ROOM_TEXT_FIELD));
        instRoomField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    instRoomField.setText(instructor.getRoom());
                } else {
                    String oldRoom = instructor.getRoom();
                    String newRoom = instRoomField.getText();
                    if (!(oldRoom.equals(newRoom))) {
                        controller.processEditInstructor(instructor.getName(), instructor.getEmail(), newRoom, instructor.getHomepage(), instructor.gethtml());
                    }

                }

            }
        });
        TextField instHPField = ((TextField) gui.getGUINode(INSTRUCTOR_HP_TEXT_FIELD));
        instHPField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    instHPField.setText(instructor.getHomepage());
                } else {
                    String oldHP = instructor.getHomepage();
                    String newHP = instHPField.getText();
                    if (!(oldHP.equals(newHP))) {
                        controller.processEditInstructor(instructor.getName(), instructor.getEmail(), instructor.getRoom(), newHP, instructor.gethtml());
                    }

                }

            }
        });

        // FOOLPROOF DESIGN STUFF
        TextField nameTextField = ((TextField) gui.getGUINode(OH_NAME_TEXT_FIELD));
        TextField emailTextField = ((TextField) gui.getGUINode(OH_EMAIL_TEXT_FIELD));

        nameTextField.textProperty().addListener(e -> {
            controller.processTypeTA();
        });
        emailTextField.textProperty().addListener(e -> {
            controller.processTypeTA();
        });

        // FIRE THE ADD EVENT ACTION
        nameTextField.setOnAction(e -> {
            controller.processAddTA();
        });
        emailTextField.setOnAction(e -> {
            controller.processAddTA();
        });
        ((Button) gui.getGUINode(OH_ADD_TA_BUTTON)).setOnAction(e -> {
            controller.processAddTA();
        });

        TableView officeHoursTableView = (TableView) gui.getGUINode(OH_OFFICE_HOURS_TABLE_VIEW);
        officeHoursTableView.getSelectionModel().setCellSelectionEnabled(true);
        officeHoursTableView.setOnMouseClicked(e -> {
            controller.processToggleOfficeHours();
        });

        // DON'T LET ANYONE SORT THE TABLES
        TableView tasTableView = (TableView) gui.getGUINode(OH_TAS_TABLE_VIEW);
        for (int i = 0; i < officeHoursTableView.getColumns().size(); i++) {
            ((TableColumn) officeHoursTableView.getColumns().get(i)).setSortable(false);
        }
        for (int i = 0; i < tasTableView.getColumns().size(); i++) {
            ((TableColumn) tasTableView.getColumns().get(i)).setSortable(false);
        }

        tasTableView.setOnMouseClicked(e -> {
            app.getFoolproofModule().updateAll();
            if (e.getClickCount() == 2) {
                controller.processEditTA();
            }
            controller.processSelectTA();
        });

        RadioButton allRadio = (RadioButton) gui.getGUINode(OH_ALL_RADIO_BUTTON);
        allRadio.setOnAction(e -> {
            controller.processSelectAllTAs();
        });
        RadioButton gradRadio = (RadioButton) gui.getGUINode(OH_GRAD_RADIO_BUTTON);
        gradRadio.setOnAction(e -> {
            controller.processSelectGradTAs();
        });
        RadioButton undergradRadio = (RadioButton) gui.getGUINode(OH_UNDERGRAD_RADIO_BUTTON);
        undergradRadio.setOnAction(e -> {
            controller.processSelectUndergradTAs();
        });
        removeTA.setOnAction(e -> {
            TeachingAssistantPrototype selectedTA = (TeachingAssistantPrototype) tasTableView.getSelectionModel().getSelectedItem();
            OfficeHoursData data1 = (OfficeHoursData) app.getDataComponent();
            data1.removeTA(selectedTA);

        });

        times.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            String timestuff = (String) newVal;
            OfficeHoursData data1 = (OfficeHoursData) app.getDataComponent();
            boolean check = data1.updateStartTime(timestuff);
            if (check == false) {
                times.setValue(oldVal);
            }
        });
        entimes.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            String timestuff = (String) newVal;
            OfficeHoursData data1 = (OfficeHoursData) app.getDataComponent();
            boolean check = data1.updateEndTime(timestuff);
            if (check == false) {
                entimes.setValue(oldVal);
            }
        });
    }

    public void initFoolproofDesign() {
        AppGUIModule gui = app.getGUIModule();
        AppFoolproofModule foolproofSettings = app.getFoolproofModule();
        foolproofSettings.registerModeSettings(OH_FOOLPROOF_SETTINGS,
                new OfficeHoursFoolproofDesign((OfficeHoursApp) app));
    }

    @Override
    public void processWorkspaceKeyEvent(KeyEvent ke) {
        // WE AREN'T USING THIS FOR THIS APPLICATION
    }

    @Override
    public void showNewDialog() {
        // WE AREN'T USING THIS FOR THIS APPLICATION
    }
}
