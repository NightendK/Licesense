package com.example.application.views.login;

import com.example.application.backend.Enums.Race;
import com.example.application.backend.Enums.Role;
import com.example.application.backend.Enums.Sex;
import com.example.application.backend.Enums.ShirtSize;
import com.example.application.backend.Service.AuthService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Section;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;


@Route("/sign")
@CssImport("./styles/views/sign/signIn-view.css")
public class SignInView extends Section {

    private final AuthService authService;

    private final TextField firstName = new TextField("Prenume");
    private final TextField lastName = new TextField("Nume");

    private final EmailField email = new EmailField();
    private final ComboBox<Sex> comboBox = new ComboBox<>();
    private final ComboBox<ShirtSize> shirtBox = new ComboBox<>();

    private final ComboBox<Race> raceBox = new ComboBox<>();

    private final TextField userName = new TextField("Username");
    private final PasswordField passwordField = new PasswordField("Password");
    private final PasswordField checkPassword = new PasswordField("Confirm Password");

    private final Button button = new Button("Sign In");

    public SignInView(AuthService authService) {
        this.authService = authService;

        email.setLabel("Adresa de email");
        email.setErrorMessage("Introduceti o adresa de email valida!");
        email.setClearButtonVisible(true);
        email.setTooltipText("Adresa de email");
        email.setPlaceholder("nume@exemplu.com");
        email.setPrefixComponent(VaadinIcon.ENVELOPE.create());


        firstName.setId("firstName");
        lastName.setId("lastName");
        email.setId("email");
        comboBox.setId("sexBox");
        shirtBox.setId("shirtBox");
        raceBox.setId("raceBox");
        userName.setId("userName");
        passwordField.setId("password");
        checkPassword.setId("checkPassword");
        button.setId("button");

        comboBox.setLabel("Sex");
        comboBox.setItems(Sex.BARBAT, Sex.FEMEIE);


        shirtBox.setLabel("Dimensiunea tricoului");
        shirtBox.setItems(ShirtSize.XS, ShirtSize.S, ShirtSize.M,
                          ShirtSize.L, ShirtSize.XL, ShirtSize.XXL);

        raceBox.setLabel("Cursa");
        raceBox.setItems(Race.CURSA_42KM, Race.CURSA_21KM, Race.CURSA_10KM, Race.CURSA_COPII);

        firstName.setTooltipText("Introduceti prenumele dumneavoastra");
        lastName.setTooltipText("Introduceti numele dumneavoastra de familie");

        VerticalLayout verticalLayout = createSignInForm();

        button.addClickListener(e -> {
            register(firstName.getValue(), lastName.getValue(), email.getValue(),
                    userName.getValue(), passwordField.getValue(), checkPassword.getValue(),
                    comboBox.getValue(), shirtBox.getValue(), raceBox.getValue());
        });
        add(verticalLayout);
    }

    private VerticalLayout createSignInForm() {

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setSizeFull();
        verticalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        verticalLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        verticalLayout.setSpacing(true);

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setSizeFull();
        horizontalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        horizontalLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        horizontalLayout.setSpacing(true);
        horizontalLayout.add(lastName, firstName);

        HorizontalLayout horizontalLayout1 = new HorizontalLayout();
        horizontalLayout1.setSizeFull();
        horizontalLayout1.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        horizontalLayout1.setAlignItems(FlexComponent.Alignment.CENTER);
        horizontalLayout1.setSpacing(true);
        horizontalLayout1.add(email);

        HorizontalLayout horizontalLayout2 = new HorizontalLayout();
        horizontalLayout2.setSizeFull();
        horizontalLayout2.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        horizontalLayout2.setAlignItems(FlexComponent.Alignment.CENTER);
        horizontalLayout2.setSpacing(true);
        horizontalLayout2.add(comboBox, shirtBox, raceBox);

        HorizontalLayout horizontalLayout3 = new HorizontalLayout();
        horizontalLayout3.setSizeFull();
        horizontalLayout3.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        horizontalLayout3.setAlignItems(FlexComponent.Alignment.CENTER);
        horizontalLayout3.setSpacing(true);
        horizontalLayout3.add(userName);

        HorizontalLayout horizontalLayout4 = new HorizontalLayout();
        horizontalLayout4.setSizeFull();
        horizontalLayout4.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        horizontalLayout4.setAlignItems(FlexComponent.Alignment.CENTER);
        horizontalLayout4.setSpacing(true);
        horizontalLayout4.add(passwordField, checkPassword);

        HorizontalLayout horizontalLayout5= new HorizontalLayout();
        horizontalLayout5.setSizeFull();
        horizontalLayout5.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        horizontalLayout5.setAlignItems(FlexComponent.Alignment.CENTER);
        horizontalLayout5.setSpacing(true);
        horizontalLayout5.add(button);


        verticalLayout.add(horizontalLayout, horizontalLayout1, horizontalLayout2,
                           horizontalLayout3, horizontalLayout4, horizontalLayout5);

        return verticalLayout;
    }

    private void register(String firstName, String lastName, String email,
                          String userName, String password, String checkPassword,
                          Sex sex, ShirtSize shirtSize, Race race) {

        if (firstName.trim().isEmpty()) {
            Notification.show("Enter your firstname!");
        }

        else if (lastName.trim().isEmpty()) {
            Notification.show("Enter your lastname");
        }

        else if (email.trim().isEmpty()) {
            Notification.show("Enter a valid email!");
        }

        else if (userName.trim().isEmpty()) {
            Notification.show("Enter a username!");
        }

        else if (password.trim().isEmpty()) {
            Notification.show("Enter a password!");
        }

        else if (!password.equals(checkPassword)) {
            Notification.show("Passwords don't match!");
        }
        else {
            authService.signUp(firstName, lastName, email,
                    userName, password, checkPassword, sex, shirtSize, race);
            Notification.show("Registration succesfull!");
            UI.getCurrent().navigate("/checkout");
        }
    }

}
