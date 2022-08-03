package com.c57lee.possystemdemo.userinterface;

import com.c57lee.possystemdemo.obj.Location;
import com.c57lee.possystemdemo.userinterface.component.TextRectangle;
import javafx.geometry.Bounds;

public class LocationSettingTextRectangle extends TextRectangle {
    LocationSettingUI ui;

    public LocationSettingTextRectangle(LocationSettingUI ui){
        super();
        this.ui = ui;
    }

    public LocationSettingTextRectangle(Location l, LocationSettingUI ui){
        super(l.getLocationName(),l.getLocationX(),l.getLocationY(),l.getLocationWidth(),l.getLocationHeight());
        this.ui=ui;
    }

    public void setUI(LocationSettingUI ui){
        this.ui = ui;
    }

    @Override
    public void actionOnMouseClicked(){
        Bounds bounds = this.getBoundsInParent();
        ui.selectLocation(new Location(getText(),bounds.getMinX(),bounds.getMinY(),bounds.getWidth(),bounds.getHeight()));
    }

    public Location asLocation(){
        Bounds bounds = this.getBoundsInParent();
        return new Location(getText(), bounds.getMinX(), bounds.getMinY(), bounds.getWidth(), bounds.getHeight());
    }
}
