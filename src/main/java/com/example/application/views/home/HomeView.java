package com.example.application.views.home;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import jakarta.annotation.security.DenyAll;
import jakarta.annotation.security.PermitAll;

@PageTitle("Home")
@CssImport("./styles/views/home/home-view.css")
public class HomeView extends VerticalLayout {

    private Image raceLogo = new Image();

    public HomeView() {

        setSizeFull();
        setSpacing(true);
        raceLogo.setSrc("images/RaceLogoV2.png");
        raceLogo.setId("raceLogo");
        add(raceLogo);

        Label text = new Label();
        text.setId("text");
        text.setText("We are pleased to welcome you to the first edition of the Road Runner Race. This year's competition" +
                " will take place in Bucharest, capital of Romania");

        Label text2 = new Label();
        text2.setId("text");
        text2.setText("The competition is divided into 4 races: 42Km race, 21Km race, 10Km race and a race " +
                "for the children, 1Km race. Our objective is to offer the chance for any individual to participate into a physical activity outside.");

        Label text3 = new Label();
        text3.setId("text");
        text3.setText("Road Runner Race (R.R.R): We are an organization that support physical activity, especially running as a means " +
                "of meditation and stress relief. Our goal is to organise races in every capital in the European Union so we can promote" +
                " physical activity in our increasing sedentary lifestyle. The profits we make will be donated to charity and to research into how" + "" +
                " running can reduce our daily life stress.");


        add(text, text2, text3);
    }

}
