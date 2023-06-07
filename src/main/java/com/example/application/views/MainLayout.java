package com.example.application.views;


import com.example.application.backend.Enums.Role;
import com.example.application.backend.Model.User;
import com.example.application.backend.Service.AuthService;
import com.example.application.components.appnav.AppNav;
import com.example.application.components.appnav.AppNavItem;
import com.example.application.views.cursa10km.Cursa10kmView;
import com.example.application.views.cursa21km.Cursa21KmView;
import com.example.application.views.cursa42km.Cursa42KmView;
import com.example.application.views.cursacopii.CursaCopiiView;
import com.example.application.views.home.HomeView;
import com.example.application.views.logout.LogoutView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.ArrayList;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {

    private H2 viewTitle;
    private final AuthService authService;

    private static final long serialVersionUID = 1113799434508676095L;

    public MainLayout(AuthService authService) {
        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
        this.authService = authService;
    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.getElement().setAttribute("aria-label", "Menu toggle");

        viewTitle = new H2();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        addToNavbar(true, toggle, viewTitle);
    }

    private void addDrawerContent() {
        H1 appName = new H1("Marathon App");
        appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
        Header header = new Header(appName);

        Scroller scroller = new Scroller(createNavigation());

        addToDrawer(header, scroller, createFooter());
    }

    private AppNav createNavigation() {
        // AppNav is not yet an official component.
        // For documentation, visit https://github.com/vaadin/vcf-nav#readme
        AppNav nav = new AppNav();

        nav.addItem(new AppNavItem("Home", HomeView.class, LineAwesomeIcon.HOME_SOLID.create()));
        nav.addItem(new AppNavItem("Cursa 42Km", Cursa42KmView.class, LineAwesomeIcon.RUNNING_SOLID.create()));
        nav.addItem(new AppNavItem("Cursa 21Km", Cursa21KmView.class, LineAwesomeIcon.RUNNING_SOLID.create()));
        nav.addItem(new AppNavItem("Cursa 10km", Cursa10kmView.class, LineAwesomeIcon.RUNNING_SOLID.create()));
        nav.addItem(new AppNavItem("Cursa Copii", CursaCopiiView.class, LineAwesomeIcon.RUNNING_SOLID.create()));
        nav.addItem(new AppNavItem("Logout", LogoutView.class, LineAwesomeIcon.RUNNING_SOLID.create()));
        return nav;
    }

    private Footer createFooter() {
        Footer layout = new Footer();

        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}
