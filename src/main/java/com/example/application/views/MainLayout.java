package com.example.application.views;

import com.example.application.backend.Service.AuthService;
import com.example.application.components.appnav.AppNav;
import com.example.application.components.appnav.AppNavItem;
import com.example.application.views.cursa10km.Cursa10kmView;
import com.example.application.views.cursa21km.Cursa21KmView;
import com.example.application.views.cursa42km.Cursa42KmView;
import com.example.application.views.cursacopii.CursaCopiiView;
import com.example.application.views.grid.GridView;
import com.example.application.views.home.HomeView;
import com.example.application.views.logout.LogoutView;
import com.example.application.views.profile.ProfileView;
import com.example.application.views.request.RequestGridView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.router.PageTitle;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.io.Serial;

/**
 * The main view is a top-level placeholder for other views.
 */
@CssImport("./styles/views/main/main-view.css")
public class MainLayout extends AppLayout {
    private H2 viewTitle;
    private final AuthService authService;
    private final Icon profileIcon = new Icon(VaadinIcon.USER);
    @Serial
    private static final long serialVersionUID = 1113799434508676095L;
    private final AppNavItem homeItem = new AppNavItem("Home", HomeView.class, LineAwesomeIcon.HOME_SOLID.create());
    private final AppNavItem race42Item = new AppNavItem("Cursa 42Km", Cursa42KmView.class, LineAwesomeIcon.RUNNING_SOLID.create());
    private final AppNavItem race21Item = new AppNavItem("Cursa 21Km", Cursa21KmView.class, LineAwesomeIcon.RUNNING_SOLID.create());
    private final AppNavItem race10Item = new AppNavItem("Cursa 10Km", Cursa10kmView.class, LineAwesomeIcon.RUNNING_SOLID.create());
    private final AppNavItem raceChItem = new AppNavItem("Cursa Copii", CursaCopiiView.class, LineAwesomeIcon.RUNNING_SOLID.create());
    private final AppNavItem logout = new AppNavItem("Logout", LogoutView.class, LineAwesomeIcon.RUNNING_SOLID.create());
    private final AppNavItem profile = new AppNavItem("Profile", ProfileView.class, profileIcon);
    private final AppNavItem grid = new AppNavItem("Grid", GridView.class, LineAwesomeIcon.COG_SOLID.create());
    private final AppNavItem gridRequest = new AppNavItem("Requests", RequestGridView.class, LineAwesomeIcon.COG_SOLID.create());

    public MainLayout(AuthService authService) {

        homeItem.setId("homeNav");
        profile.setId("idNav");
        grid.setId("grid");
        gridRequest.setId("grid");
        profileIcon.setId("profileIcon");
        race42Item.setId("race42Nav");
        race21Item.setId("race21Nav");
        race10Item.setId("race10Nav");
        raceChItem.setId("chNav");
        logout.setId("logout");

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
        H1 appName = new H1("Roade Runner Race");
        appName.setId("appName");
        Header header = new Header(appName);

        Scroller scroller = new Scroller(createNavigation());

        addToDrawer(header, scroller, createFooter());
    }

    private AppNav createNavigation() {

        AppNav nav = new AppNav();

        String username = UI.getCurrent().getSession().getAttribute("username").toString();

        if (username.equals("root")) {
            nav.addItem(homeItem);
            nav.addItem(grid);
            nav.addItem(gridRequest);
            nav.addItem(race42Item);
            nav.addItem(race21Item);
            nav.addItem(race10Item);
            nav.addItem(raceChItem);
            nav.addItem(logout);
        } else {
            nav.addItem(homeItem);
            nav.addItem(profile);
            nav.addItem(race42Item);
            nav.addItem(race21Item);
            nav.addItem(race10Item);
            nav.addItem(raceChItem);
            nav.addItem(logout);
        }

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
