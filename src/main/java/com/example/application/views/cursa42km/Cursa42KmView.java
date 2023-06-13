package com.example.application.views.cursa42km;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@PageTitle("Cursa 42Km")
@CssImport("./styles/views/cursa42/cursa42-view.css")
//@Route(value = "42km", layout = MainLayout.class)
public class Cursa42KmView extends VerticalLayout {

    private Label date = new Label();
    private Label startTime = new Label();
    private Label distance = new Label();
    private Label limitTime = new Label();
    private Label startLocation = new Label();
    private Image raceImage = new Image("images/Cursa42.png", "race");

    public Cursa42KmView() {

        setSizeFull();
        setSpacing(true);

        date.setId("date");
        date.setText("Date : 24 september 2023");

        startTime.setId("startTime");
        startTime.setText("Start time : 10:00");

        distance.setId("distance");
        distance.setText("Distance : 42Km");

        limitTime.setId("limitTime");
        limitTime.setText("The race must be completed in maximum 6 hours. Candidates who complete the race in a time greater than 6 hours " +
                "won't be displayed on the time board.");

        startLocation.setId("startLoc");
        startLocation.setText("Start location: Piața Presei Libere 1, București\n\n");
        add(date, startTime, startLocation, distance, limitTime);

        Label title = new Label();
        title.setId("raceMap");
        title.setText("\n\nRace Map");

        add(title);

        raceImage.setId("raceImage");
        add(raceImage);
    }


}
