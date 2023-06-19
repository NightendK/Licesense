package com.example.application.views.register;

import com.example.application.views.SignIn.SignInView10;
import com.example.application.views.SignIn.SignInView21;
import com.example.application.views.SignIn.SignInViewChildren;
import com.example.application.views.login.LoginView;
import com.example.application.views.SignIn.SignInView42;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility;

@Route("/register")
@CssImport(value = "./styles/vaadin-app-layout-styles.css", themeFor = "vaadin-app-layout")
public class RegisterView extends AppLayout {

    AppLayout appLayout = new AppLayout();

    private Tab cursa42 = new Tab(new RouterLink("Race 42Km", SignInView42.class));
    private Tab cursa21 = new Tab(new RouterLink("Race 21Km", SignInView21.class));
    private Tab cursa10 = new Tab(new RouterLink("Race 10Km", SignInView10.class));
    private Tab cursaCopii = new Tab(new RouterLink("Children's Race", SignInViewChildren.class));
    RegisterView() {

        Footer footer = new Footer();
        footer.setId("footer");

        VerticalLayout footerLayout = new VerticalLayout();
        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setId("main");


        H1 appTitle = new H1("Registration");
        appTitle.setId("title");
        appTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        Label text42 = new Label();
        text42.setId("text_42");
        text42.setText("For participating at one of the races, you must pay a registration fee. This fee includes " +
                "taxes that cover presence of medical staff and refreshment zones but also the kit that you receive for the race." + " \n" +
                "After completing the required fields in the registration form, you will be redirected to the checkout form to conclude the payment and finalize the payment.");

       //

        Label secondLabel = new Label();
        secondLabel.setId("secondLabel");
        secondLabel.setText("Race Schedule");

        Label title42 = new Label();
        title42.setId("title42");
        title42.setText("Marathon (42Km Race): ");

        Label date42 = new Label();
        date42.setId("date42");
        date42.setText("Start date: September 24th, 10:00:00");

        Label startLocation42 = new Label();
        startLocation42.setId("startLocation");
        startLocation42.setText("Start location: Piața Presei Libere 1, București");

        Label startLocation21 = new Label();
        startLocation21.setId("startLocation");
        startLocation21.setText("Start location: Piața Presei Libere 1, București");

        Label startLocation10 = new Label();
        startLocation10.setId("startLocation");
        startLocation10.setText("Start location: Piața Presei Libere 1, București");

        Label startLocationCh = new Label();
        startLocationCh.setId("startLocation");
        startLocationCh.setText("Start location: Piața Presei Libere 1, București");

        Label title21 = new Label();
        title21.setId("title21");
        title21.setText("Semi-Marathon (21Km Race): ");

        Label date21= new Label();
        date21.setId("date21");
        date21.setText("Start date: September 25th, 10:30:00");

        Label title10 = new Label();
        title10.setId("title10");
        title10.setText("Quarter-Marathon (10Km Race): ");

        Label date10= new Label();
        date10.setId("date10");
        date10.setText("Start date: September 27th, 09:00:00");

        Label titleCh = new Label();
        titleCh.setId("titleCh");
        titleCh.setText("Children's Race (1Km Race): ");

        Label dateCh= new Label();
        dateCh.setId("dateCh");
        dateCh.setText("Start date: September 27th, 10:30:00");

        Paragraph footerText42 = new Paragraph();
        footerText42.setId("footerText42");
        footerText42.setText("42Km Race : 40$");

        Paragraph footerText21 = new Paragraph();
        footerText21.setId("footerText21");
        footerText21.setText("21Km Race : 25$");

        Paragraph footerText10 = new Paragraph();
        footerText10.setId("footerText10");
        footerText10.setText("10Km Race : 15$");

        Paragraph footerTextChildren = new Paragraph();
        footerTextChildren.setId("footerTextChildren");
        footerTextChildren.setText("Child Race : 7$");

        Label label = new Label();
        label.setId("label");
        label.setText("For participating in any of the races, you must pay a entry fee that covers taxation and kit." + " \n" +
                "The fee for each race is listed above. You will be redirected to the payment window after completing " +
                "the registration form.");

        footerLayout.add(appTitle, footerText42, footerText21, footerText10, footerTextChildren, label);
        footer.add(footerLayout);

        /*mainLayout.setSizeFull();
        mainLayout.add(text42, secondLabel, title42, date42, startLocation42,
                                            title21, date21, startLocation21,
                                            title10, date10, startLocation10,
                                            titleCh, dateCh, startLocationCh);*/

        VerticalLayout race42 = new VerticalLayout();
        race42.setSpacing(false);
        race42.setPadding(false);
        race42.add(date42, startLocation42);

        Details details42 = new Details();
        details42.setSummary(title42);
        details42.addContent(race42);

        VerticalLayout race21 = new VerticalLayout();
        race21.setSpacing(false);
        race21.setPadding(false);
        race21.add(date21, startLocation21);

        Details details21 = new Details();
        details21.setSummary(title21);
        details21.addContent(race21);

        VerticalLayout race10 = new VerticalLayout();
        race10.setSpacing(false);
        race10.setPadding(false);
        race10.add(date10, startLocation10);

        Details details10 = new Details();
        details10.setSummary(title10);
        details10.addContent(race10);

        VerticalLayout raceCh = new VerticalLayout();
        raceCh.setSpacing(false);
        raceCh.setPadding(false);
        raceCh.add(dateCh, startLocationCh);

        Details detailsCh = new Details();
        detailsCh.setSummary(titleCh);
        detailsCh.addContent(raceCh);

        VerticalLayout detailsLayout = new VerticalLayout();
        detailsLayout.setSizeFull();
        detailsLayout.add(details42, details21, details10, detailsCh);


        setContent(detailsLayout);
        addToDrawer(footer);


        cursa42.addAttachListener(e -> {
            UI.getCurrent().navigate(SignInView42.class);
        });
        cursa42.setId("cursa42");

        cursa21.addAttachListener(e -> {
            UI.getCurrent().navigate(SignInView21.class);
        });
        cursa21.setId("cursa21");

        cursa10.addAttachListener(e -> {
            UI.getCurrent().navigate(SignInView10.class);
        });
        cursa10.setId("cursa10");

        cursaCopii.addAttachListener(e -> {
            UI.getCurrent().navigate(SignInViewChildren.class);
        });
        cursaCopii.setId("cursaCopii");

        Tabs curse = new Tabs();
        curse.getStyle().set("margin", "auto");
        curse.add(cursa42);
        curse.add(cursa21);
        curse.add(cursa10);
        curse.add(cursaCopii);
        curse.setId("navbar");

        Image logo = new Image("images/marathon-logo-new.png", "logo");
        logo.setWidth("130px");
        logo.setHeight("75px");
        logo.setId("logo");
        logo.addClickListener(e -> {
            UI.getCurrent().navigate(LoginView.class);
        });

        addToNavbar(logo);

        addToNavbar(curse);

    }
}
