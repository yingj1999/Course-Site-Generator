/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oh.transactions;

import javafx.collections.ObservableList;
import jtps.jTPS_Transaction;
import oh.data.LecturesPrototype;
import oh.data.OfficeHoursData;

/**
 *
 * @author justi
 */
public class AddLecture_Transaction implements jTPS_Transaction {

    OfficeHoursData data;
    LecturesPrototype init;

    public AddLecture_Transaction(OfficeHoursData initData) {
        data = initData;
        init = new LecturesPrototype("?", "?", "?", "?");
    }

    @Override
    public void doTransaction() {
        data.addLecture(init);
        //ObservableList lecturesList = data.getLecturesList();
        //lecturesList.add(init);
    }

    @Override
    public void undoTransaction() {
        data.removeLectures(init);
       // ObservableList lecturesList = data.getLecturesList();
        //lecturesList.remove(init);
    }
}
