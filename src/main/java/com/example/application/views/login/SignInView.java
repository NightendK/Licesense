package com.example.application.views.login;

import com.example.application.backend.Enums.Sex;
import com.example.application.backend.Enums.ShirtSize;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Section;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("/sign")
public class SignInView extends Section {

    public TextField firstName = new TextField("Prenume");
    public TextField lastName = new TextField("Nume");
    public EmailField email = new EmailField();
    public ComboBox<Sex> comboBox = new ComboBox<>();
    public ComboBox<ShirtSize> shirtBox = new ComboBox<>();

    public TextField userName = new TextField("Username");
    public PasswordField passwordField = new PasswordField("Password");
    public PasswordField checkPassword = new PasswordField("Confirm Password");


    public SignInView() {
        email.setLabel("Adresa de email");
        email.setErrorMessage("Introduceti o adresa de email valida!");
        email.setClearButtonVisible(true);
        email.setTooltipText("Adresa de email");

        comboBox.setLabel("Sex");
        comboBox.setItems(Sex.BARBAT, Sex.FEMEIE);

        shirtBox.setLabel("Dimensiunea tricoului");
        shirtBox.setItems(ShirtSize.XS, ShirtSize.S, ShirtSize.M,
                          ShirtSize.L, ShirtSize.XL, ShirtSize.XXL);

        firstName.setTooltipText("Introduceti prenumele dumneavoastra");
        lastName.setTooltipText("Introduceti numele dumneavoastra de familie");

        firstName.setMaxWidth("200px");
        lastName.setMaxWidth("200px");
        email.setMaxWidth("200px");

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.AROUND);
        verticalLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        verticalLayout.add(lastName, firstName, email, comboBox, shirtBox, userName, passwordField, checkPassword);

        add(verticalLayout);
    }

}
