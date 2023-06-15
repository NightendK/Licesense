package com.example.application.views.login;

import com.example.application.backend.Service.AuthService;
import com.example.application.views.MainLayout;
import com.example.application.views.register.RegisterView;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinSession;


@Route(value = "/login")
@RouteAlias(value = "")
@PageTitle("Login")
@CssImport("./styles/views/login/login-view.css")
public class LoginView extends Composite<LoginOverlay> {

    Label title = new Label();
        public LoginView(AuthService authService) {

            title.setId("loginTitle");
            title.setText("Road Runner Race");

        LoginI18n log = LoginI18n.createDefault();
        LoginI18n.Form logForm = log.getForm();
        logForm.setForgotPassword("Register");
        log.setForm(logForm);

        LoginOverlay loginOverlay = getContent();
        loginOverlay.setI18n(log);
        loginOverlay.setTitle(title);
        loginOverlay.setDescription("Welcome to our Web Application");
        loginOverlay.setOpened(true);

        addClassName("login-overlay-view");
        loginOverlay.getElement().getThemeList().add("dark");

        loginOverlay.addLoginListener(event -> {
            try {
                authService.authenticate(event.getUsername(), event.getPassword());
                VaadinSession.getCurrent().setAttribute("username", event.getUsername());
                UI.getCurrent().navigate("/home");
            } catch (AuthService.AuthException e) {
                Notification.show("Wrong Credidentials!");
                loginOverlay.close();
                loginOverlay.setOpened(true);
            }
        });

        loginOverlay.addForgotPasswordListener(e -> {
            loginOverlay.close();
            UI.getCurrent().navigate(RegisterView.class);
        });
    }


}

