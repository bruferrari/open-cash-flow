package com.ferrarib.opencf.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bruno on 3/3/16.
 */
public class JsonChartParser<T> {

    private Class<T> clazz;
    private List<Object[]> objectList;
    private List<T> list;

    public JsonChartParser(Class<T> clazz, List<Object[]> objectList) {
        this.clazz = clazz;
        this.objectList = objectList;
    }

    public Class<T> parse() {
        list = new ArrayList<>();

        objectList.forEach(o -> {
            clazz = (Class<T>) this.buildOne();
            //TODO
        });

        return null;
    }

    private T buildOne() {
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
