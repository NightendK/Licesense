package com.example.application.views.firstHome;

import com.example.application.backend.Service.AuthService;
import com.example.application.components.appnav.AppNav;
import com.example.application.components.appnav.AppNavItem;
import com.example.application.views.MainLayout;
import com.example.application.views.home.HomeView;
import com.example.application.views.login.LoginView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.io.Serial;

@PageTitle("Home")
@CssImport(value = "./styles/vaadin-app-layout-styles.css", themeFor = "vaadin-app-layout")
@Route(value = "/firstHome", layout = MainLayout.class)
@RouteAlias(value = "")
public class FirstHomeView extends AppLayout {
    private H2 viewTitle;
    private final AuthService authService;
    private final Icon profileIcon = new Icon(VaadinIcon.USER);
    @Serial
    private static final long serialVersionUID = 1113799434508676095L;
    private final AppNavItem homeItem = new AppNavItem("Home", HomeView.class, LineAwesomeIcon.HOME_SOLID.create());
    private final AppNavItem login = new AppNavItem("Login/Register", LoginView.class, LineAwesomeIcon.RUNNING_SOLID.create());

    public FirstHomeView(AuthService authService) {

        homeItem.setId("homeNav");
        profileIcon.setId("profileIcon");
        login.setId("logout");

        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
        this.authService = authService;
    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.setId("toggle");
        toggle.getElement().setAttribute("aria-label", "Menu toggle");

        viewTitle = new H2();
        viewTitle.setId("viewTitle");

        addToNavbar(true, toggle, viewTitle);
    }

    private void addDrawerContent() {
        H1 appName = new H1("Marathon App");
        appName.setId("appName");
        Header header = new Header(appName);

        Scroller scroller = new Scroller(createNavigation());

        addToDrawer(header, scroller, createFooter());
    }

    private AppNav createNavigation() {

        AppNav nav = new AppNav();

        nav.addItem(homeItem);
        nav.addItem(login);

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


