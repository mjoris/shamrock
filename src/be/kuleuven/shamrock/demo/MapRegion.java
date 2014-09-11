package be.kuleuven.shamrock.demo;

import be.kuleuven.shamrock.interactivemap.*;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.Region;
import javafx.scene.web.WebView;


public class MapRegion extends Region {
    protected WebView browser;
    protected LeafletMap map;

    public MapRegion(LeafletMap map) {
        this.map = map;
        browser = map.getWebView();
        getChildren().add(browser);
    }

    @Override
    protected void layoutChildren() {
        double w = getWidth();
        double h = getHeight();
        layoutInArea(browser,0,0,w,h,0, HPos.CENTER, VPos.CENTER);
    }

    @Override
    protected double computePrefWidth(double height) {
        return 750;
    }

    @Override
    protected double computePrefHeight(double width) {
        return 600;
    }
}