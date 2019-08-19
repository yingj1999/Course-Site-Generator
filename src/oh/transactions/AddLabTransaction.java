/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oh.transactions;

import jtps.jTPS_Transaction;
import oh.data.OfficeHoursData;
import oh.data.Recitation_Lab_Prototype;

/**
 *
 * @author justi
 */
public class AddLabTransaction implements jTPS_Transaction {
          OfficeHoursData data;
    Recitation_Lab_Prototype init;
 public AddLabTransaction(OfficeHoursData initData) {
        data = initData;
        init = new Recitation_Lab_Prototype("?", "?", "?", "?","?");
    }
   @Override
    public void doTransaction() {
        data.addLab(init);
        //ObservableList lecturesList = data.getLecturesList();
        //lecturesList.add(init);
    }

    @Override
    public void undoTransaction() {
        data.removeLab(init);
       // ObservableList lecturesList = data.getLecturesList();
        //lecturesList.remove(init);
    }
}
