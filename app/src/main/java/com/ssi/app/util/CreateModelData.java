package com.ssi.app.util;

import com.ssi.app.datamodel.NewOrder.Dataorder;

import java.util.ArrayList;
import java.util.List;

public class CreateModelData {

    public static List<Dataorder> createDataOrder(){

        List<Dataorder> dataorder = new ArrayList<Dataorder>();

        Dataorder data = new Dataorder();
        data.setOrderNo("ST 12312121212");
        data.setShipName("2 (Task)");

        dataorder.add(data);

        data = new Dataorder();
        data.setOrderNo("ST 323232323");
        data.setShipName("3 (Task)");

        dataorder.add(data);


        return dataorder;
    }

    public static List<Dataorder> createDataTask(){

        List<Dataorder> dataorder = new ArrayList<Dataorder>();

        Dataorder data = new Dataorder();
        data.setOrderNo("Task 1");
        data.setShipName("PT Armada Uang");

        dataorder.add(data);

        data = new Dataorder();
        data.setOrderNo("Task 2");
        data.setShipName("PT Balik Bolak");

        dataorder.add(data);


        return dataorder;
    }


}
