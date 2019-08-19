/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oh.transactions;

import javafx.collections.ObservableList;
import jtps.jTPS_Transaction;
import oh.data.OfficeHoursData;
import oh.data.SchedulePrototype;

/**
 *
 * @author justi
 */
public class Remove_SCH_Transaction implements jTPS_Transaction {
    OfficeHoursData data;
    SchedulePrototype sch;
    public Remove_SCH_Transaction(OfficeHoursData a,SchedulePrototype b ){
        data = a;
        sch = b;
}
  @Override
    public void doTransaction() {
        data.removeSchedule(sch);
}
@Override
    public void undoTransaction() {
        data.addSchedule(sch);
}
}
