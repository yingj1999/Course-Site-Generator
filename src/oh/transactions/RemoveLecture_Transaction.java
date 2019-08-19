/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oh.transactions;

import jtps.jTPS_Transaction;
import oh.data.LecturesPrototype;
import oh.data.OfficeHoursData;

/**
 *
 * @author justi
 */
public class RemoveLecture_Transaction implements jTPS_Transaction {
        OfficeHoursData data;
LecturesPrototype bf;
    public RemoveLecture_Transaction(OfficeHoursData initData, LecturesPrototype a ){
                data = initData;
                bf = a;
    }
     @Override
    public void doTransaction() {
        data.removeLectures(bf);
        
    }

    @Override
    public void undoTransaction() {
        data.addLecture(bf);
       
    }
}
