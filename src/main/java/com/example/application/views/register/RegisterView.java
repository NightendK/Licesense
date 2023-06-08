package com.example.application.views.register;

import com.example.application.views.login.SignInView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.LumoUtility;

@Route("/register")
@CssImport(value = "./styles/vaadin-app-layout-styles.css", themeFor = "vaadin-app-layout")
public class RegisterView extends AppLayout {

    AppLayout appLayout = new AppLayout();

    private Tab cursa42 = new Tab(new RouterLink("Cursa 42Km", SignInView.class));
    private Tab cursa21 = new Tab(new RouterLink("Cursa 21Km", SignInView.class));
    private Tab cursa10 = new Tab(new RouterLink("Cursa 10Km", SignInView.class));
    private Tab cursaCopii = new Tab(new RouterLink("Cursa copii", SignInView.class));
    RegisterView() {

        Footer footer = new Footer();
        footer.setId("footer");

        VerticalLayout footerLayout = new VerticalLayout();


        H1 appTitle = new H1("Registration");
        appTitle.setId("title");
        appTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        TextArea text42 = new TextArea();
        text42.setId("text_42");
        text42.setValue("For participating at the marathon, you must pay a registration tax of 40$." + " \n" +
                "After completing the required informations in the registration, you will be redirected to the payment window.");

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

        text42.setReadOnly(true);
        setContent(text42);
        addToDrawer(footer);


        cursa42.addAttachListener(e -> {
            UI.getCurrent().navigate(SignInView.class);
        });
        cursa42.setId("cursa42");

        cursa21.addAttachListener(e -> {
            UI.getCurrent().navigate(SignInView.class);
        });
        cursa21.setId("cursa21");

        cursa10.addAttachListener(e -> {
            UI.getCurrent().navigate(SignInView.class);
        });
        cursa10.setId("cursa10");

        cursaCopii.addAttachListener(e -> {
            UI.getCurrent().navigate(SignInView.class);
        });
        cursaCopii.setId("cursaCopii");

        Tabs curse = new Tabs();
        curse.getStyle().set("margin", "auto");
        curse.add(cursa42);
        curse.add(cursa21);
        curse.add(cursa10);
        curse.add(cursaCopii);
        curse.setId("navbar");

        addToNavbar(curse);

    }
}
