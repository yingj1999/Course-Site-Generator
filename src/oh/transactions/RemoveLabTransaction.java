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
public class RemoveLabTransaction implements jTPS_Transaction{
    OfficeHoursData data;
    Recitation_Lab_Prototype init;
     public RemoveLabTransaction(OfficeHoursData initData,Recitation_Lab_Prototype cc) {
        data = initData;
        init =cc;
    }

    @Override
    public void doTransaction() {
        data.removeLab(init);
        
    }

    @Override
    public void undoTransaction() {
        data.addLab(init);
      
    }
}
