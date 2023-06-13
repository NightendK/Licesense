package com.example.application.views.cursa42km;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Calendar;
import java.util.Date;


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
    public static int SECONDS_IN_A_DAY = 24 * 60 * 60;

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

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setSizeFull();
        horizontalLayout.setAlignItems(Alignment.CENTER);
        horizontalLayout.setId("timerLayout");

        var ui = UI.getCurrent();
        Calendar marathonDay = Calendar.getInstance();
        marathonDay.setTime(new Date(0));
        marathonDay.set(Calendar.DAY_OF_MONTH, 24);
        marathonDay.set(Calendar.MONTH, 8);
        marathonDay.set(Calendar.YEAR, 2023);

        Calendar today = Calendar.getInstance();
        Long diff = marathonDay.getTimeInMillis() - today.getTimeInMillis();

        Long diffSec = diff / 1000;

        Long days = diffSec / SECONDS_IN_A_DAY;
        Long secondsDay = diffSec % SECONDS_IN_A_DAY;
        Long seconds = secondsDay % 60;
        Long minutes = (secondsDay / 60) % 60;
        Long hours = (secondsDay / 3600); // % 24 not needed

        String date = new String("Days:" + days.toString() + "  hours:" + hours.toString() + "  minutes:" + minutes.toString() + "  seconds:" + seconds.toString());

        ui.access(() -> {
            horizontalLayout.add(new Label(date));
            ui.push();
        });

        add(horizontalLayout);


    }

}
