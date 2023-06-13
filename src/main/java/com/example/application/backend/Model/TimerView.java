package com.example.application.backend.Model;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.util.Calendar;
import java.util.Date;

@Route("/time")
public class TimerView extends VerticalLayout {
    public static int SECONDS_IN_A_DAY = 24 * 60 * 60;
    public TimerView() {

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

        String date = new String(days.toString() + ":" + hours.toString() + ":" + minutes.toString() + ":" + seconds.toString());

        ui.access(() -> {
           add(new Label(date));
           ui.push();
        });
    }


}