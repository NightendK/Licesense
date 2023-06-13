package com.example.application.views.cursacopii;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;

@PageTitle("Cursa Copii")
@CssImport("./styles/views/cursa42/cursa42-view.css")
//@Route(value = "copii", layout = MainLayout.class)
public class CursaCopiiView extends VerticalLayout {
    private Label date = new Label();
    private Label startTime = new Label();
    private Label distance = new Label();
    private Label limitTime = new Label();
    private Label startLocation = new Label();
    private Image raceImage = new Image("images/CursaCopii.png", "race");

    public CursaCopiiView() {

        setSizeFull();
        setSpacing(true);

        date.setId("date");
        date.setText("Date : 27 september 2023");

        startTime.setId("startTime");
        startTime.setText("Start time : 10:30");

        distance.setId("distance");
        distance.setText("Distance : 1Km");

        limitTime.setId("limitTime");
        limitTime.setText("The race must be completed in maximum 12 minutes. Candidates who complete the race in a time greater than 12 minutes " +
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
