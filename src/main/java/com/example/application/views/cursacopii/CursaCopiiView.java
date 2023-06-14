package com.example.application.views.cursacopii;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;

import java.util.Calendar;
import java.util.Date;

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
    private static int SECONDS_IN_A_DAY = 24 * 60 * 60;
    private Label counter = new Label();
    private Label daysField = new Label();
    private Label hoursField = new Label();
    private Label minField = new Label();
    private Label secField = new Label();

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

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setSizeFull();
        horizontalLayout.setAlignItems(Alignment.CENTER);
        horizontalLayout.setId("timerLayout");

        counter.setId("counter");

        var ui = UI.getCurrent();
        Calendar marathonDay = Calendar.getInstance();
        marathonDay.setTime(new Date(0));
        marathonDay.set(Calendar.DAY_OF_MONTH, 27);
        marathonDay.set(Calendar.MONTH, 8);
        marathonDay.set(Calendar.YEAR, 2023);

        daysField.setId("daysField");
        hoursField.setId("hoursField");
        minField.setId("minField");
        secField.setId("secField");

        ui.setPollInterval(1000);
        ui.addPollListener(e -> {

            Calendar today = Calendar.getInstance();

            Long diff = marathonDay.getTimeInMillis() - today.getTimeInMillis();
            Long diffSec = diff / 1000;

            Long days = diffSec / SECONDS_IN_A_DAY;
            Long secondsDay = diffSec % SECONDS_IN_A_DAY;
            Long seconds = secondsDay % 60;
            Long minutes = (secondsDay / 60) % 60;
            Long hours = (secondsDay / 3600);

           /* String date = new String("Days:" + days.toString() + "  hours:" + hours.toString() + "  minutes:" + minutes.toString() + "  seconds:" + seconds.toString());
            counter.setText(date);*/

            daysField.setText(days.toString());
            hoursField.setText(hours.toString());
            minField.setText(minutes.toString());
            secField.setText(seconds.toString());

            counter.setText(days.toString() + " : " + hours.toString() + " : " +
                    minutes.toString() + " : " + seconds.toString());

        });

        Label timeTitle = new Label();
        timeTitle.setText("\n\nCountdown");
        timeTitle.setId("countdown");
        add(timeTitle);
        horizontalLayout.add(counter);
        add(horizontalLayout);

        Label daysLabel = new Label();
        daysLabel.setId("daysLabel");
        daysLabel.setText("Days");

        Label hoursLabel = new Label();
        hoursLabel.setId("hoursLabel");
        hoursLabel.setText("Hours");

        Label minutesLabel = new Label();
        minutesLabel.setId("minutesLabel");
        minutesLabel.setText("Minutes");

        Label secondsLabel = new Label();
        secondsLabel.setId("secondsLabel");
        secondsLabel.setText("Seconds");


        add(daysLabel, hoursLabel, minutesLabel, secondsLabel);
    }

}
