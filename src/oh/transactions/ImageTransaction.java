/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oh.transactions;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import jtps.jTPS_Transaction;

/**
 *
 * @author justi
 */
public class ImageTransaction implements jTPS_Transaction {
    Image oldImage;
    Image newImage;
    ImageView imageView;
    public ImageTransaction(ImageView ig, Image initOld, Image newOld){
        oldImage = initOld;
        newImage = newOld;
        imageView=ig;
    }
    @Override
    public void doTransaction(){
        imageView.setImage(newImage);
    }
    @Override
    public void undoTransaction(){
        imageView.setImage(oldImage);
    }
}
