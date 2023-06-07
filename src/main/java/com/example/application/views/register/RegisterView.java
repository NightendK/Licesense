package com.example.application.views.register;

import com.example.application.views.login.SignInView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility;

@Route("/register")
public class RegisterView extends AppLayout {

    AppLayout appLayout = new AppLayout();

    private Tab cursa42 = new Tab(new RouterLink("Cursa 42Km", SignInView.class));
    private Tab cursa21 = new Tab(new RouterLink("Cursa 21Km", SignInView.class));
    private Tab cursa10 = new Tab(new RouterLink("Cursa 10Km", SignInView.class));
    private Tab cursaCopii = new Tab(new RouterLink("Cursa copii", SignInView.class));
    RegisterView() {

        Footer layout = new Footer();
        layout.add(new H1("This is the footer"));

        H1 appName = new H1("Register");
        appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
        Header header = new Header(appName);


        addToDrawer(header,layout);

        cursa42.addAttachListener(e -> {
            UI.getCurrent().navigate(SignInView.class);
        });

        /*Component registerButtons = new com.example.application.views.login.RegisterView();
        setContent(registerButtons);*/

        Tabs curse = new Tabs();
        curse.getStyle().set("margin", "auto");
        curse.add(cursa42);
        curse.add(cursa21);
        curse.add(cursa10);
        curse.add(cursaCopii);

        addToNavbar(curse);

    }
}
