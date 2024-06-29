package Utils;

import parsing.ParsElem;

import java.util.List;

public class UtilsPars {

    public String filtr(List<ParsElem> list){
        String vivod = "";
        return vivod = list.toString().replaceAll("[^A-zА-я0-9:. \n]","").replace("[","").replace("]","");
    }
}
