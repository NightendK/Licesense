package com.example.application.views.home;

import com.example.application.views.MainLayout;
import com.example.application.views.firstHome.FirstHomeView;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
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
@Route(value = "/home", layout = FirstHomeView.class)
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

        Label sponsors = new Label();
        sponsors.setId("sponsors");
        sponsors.setText("Sponsors");

        add(sponsors);

        HorizontalLayout sponsorLayout = new HorizontalLayout();
        sponsorLayout.setSpacing(false);
        sponsorLayout.setJustifyContentMode(JustifyContentMode.AROUND);
        sponsorLayout.setSizeFull();

        Image sportVision = new Image("images/SportVision.png", "sportVision");
        sportVision.setId("sportVision");
        sportVision.setHeight("215px");
        sportVision.setWidth("200px");
        sportVision.addClickListener(e -> {
            UI.getCurrent().getPage().open("https://www.sportvision.ro/", "SportVision");
        });

        Image gg = new Image("images/deca.png", "deca");
        gg.setId("gg");
        gg.setHeight("215px");
        gg.setWidth("200px");
        gg.addClickListener(e -> {
            UI.getCurrent().getPage().open("https://www.decathlon.ro/", "Decathlon");
        });

        Image savonia = new Image("images/savonia.png", "savonia");
        savonia.setId("savonia");
        savonia.setHeight("215px");
        savonia.setWidth("200px");
        savonia.addClickListener(e -> {
            UI.getCurrent().getPage().open("https://www.savonia.ro/", "Savonia");
        });

        sponsorLayout.add(sportVision, gg, savonia);

        add(sponsorLayout);

        HorizontalLayout sponsorLayout2 = new HorizontalLayout();
        sponsorLayout2.setSpacing(false);
        sponsorLayout2.setJustifyContentMode(JustifyContentMode.AROUND);
        sponsorLayout2.setSizeFull();

        Image samsonite = new Image("images/Samsonite.png", "sportVision");
        sportVision.setId("samsonite");
        samsonite.setHeight("215px");
        samsonite.setWidth("200px");
        samsonite.addClickListener(e -> {
            UI.getCurrent().getPage().open("https://www.samsonite.ro/ro/", "Samsonite");
        });

        Image isotonic = new Image("images/Isotonic.png", "isotonic");
        isotonic.setId("isotonic");
        isotonic.setHeight("215px");
        isotonic.setWidth("200px");
        isotonic.addClickListener(e -> {
            UI.getCurrent().getPage().open("https://www.isotonix.com/", "Isotonic");
        });

        Image pep = new Image("images/pep.png", "pep");
        pep.setId("pep");
        pep.setHeight("215px");
        pep.setWidth("200px");
        pep.addClickListener(e -> {
            UI.getCurrent().getPage().open("https://www.pepandpepper.ro/", "Pep");
        });

        sponsorLayout2.add(samsonite, isotonic, pep);

        add(sponsorLayout2);


        HorizontalLayout sponsorLayout3 = new HorizontalLayout();
        sponsorLayout3.setSpacing(false);
        sponsorLayout3.setJustifyContentMode(JustifyContentMode.AROUND);
        sponsorLayout3.setSizeFull();

        Image regina = new Image("images/regina.png", "regina");
        regina.setId("regina");
        regina.setHeight("215px");
        regina.setWidth("200px");
        regina.addClickListener(e -> {
            UI.getCurrent().getPage().open("https://www.reginamaria.ro/", "Regina");
        });

        Image dorna = new Image("images/dorna.png", "dorna");
        dorna.setId("dorna");
        dorna.setHeight("215px");
        dorna.setWidth("200px");
        dorna.addClickListener(e -> {
            UI.getCurrent().getPage().open("https://ladorna.ro/", "Dorna");
        });

        Image asics = new Image("images/asics.png", "asics");
        asics.setId("asics");
        asics.setHeight("215px");
        asics.setWidth("200px");
        asics.addClickListener(e -> {
            UI.getCurrent().getPage().open("https://www.asics.com/gb/en-gb/", "Asics");
        });

        sponsorLayout3.add(regina, dorna, asics);

        add(sponsorLayout3);

    }

}
