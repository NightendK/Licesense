package com.example.application.views.login;

import com.example.application.backend.Service.AuthService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.router.RouterLink;


@Route(value = "/login")
@RouteAlias(value = "")
@PageTitle("Login")
@CssImport("./styles/views/login/login-view.css")
public class LoginView extends VerticalLayout {
        public LoginView(AuthService authService) {
            setId("login-view");
            var username = new TextField("Username");
            username.setMaxWidth("250px");
            var password = new PasswordField("Password");
            password.setMaxWidth("250px");
            setAlignItems(FlexComponent.Alignment.CENTER);
            setMaxWidth("200px");
            add(
                    new H1("Marathon App"),
                    username,
                    password,
                    new Button("Login", buttonClickEvent -> {
                        try {
                            authService.authenticate(username.getValue(), password.getValue());
                            UI.getCurrent().navigate("/home");
                        } catch (AuthService.AuthException e) {
                            Notification.show("Wrong Credidentials!");
                        }
                    }),

                    new RouterLink("Register", RegisterView.class)
            );
        }

}

