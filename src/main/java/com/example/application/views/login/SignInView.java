package com.example.application.views.login;

import com.example.application.backend.Enums.Race;
import com.example.application.backend.Enums.Sex;
import com.example.application.backend.Enums.ShirtSize;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Section;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.text.Normalizer;

@Route("/sign")
@CssImport("./styles/views/sign/signIn-view.css")
public class SignInView extends Section {

    public TextField firstName = new TextField("Prenume");
    public TextField lastName = new TextField("Nume");

    public EmailField email = new EmailField();
    public ComboBox<Sex> comboBox = new ComboBox<>();
    public ComboBox<ShirtSize> shirtBox = new ComboBox<>();

    public ComboBox<Race> raceBox = new ComboBox<>();

    public TextField userName = new TextField("Username");
    public PasswordField passwordField = new PasswordField("Password");
    public PasswordField checkPassword = new PasswordField("Confirm Password");


    public SignInView() {

        email.setLabel("Adresa de email");
        email.setErrorMessage("Introduceti o adresa de email valida!");
        email.setClearButtonVisible(true);
        email.setTooltipText("Adresa de email");
        email.setPlaceholder("nume@exemplu.com");
        email.setPrefixComponent(VaadinIcon.ENVELOPE.create());


        firstName.setId("firstName");
        lastName.setId("lastName");
        email.setId("email");


        comboBox.setLabel("Sex");
        comboBox.setItems(Sex.BARBAT, Sex.FEMEIE);

        shirtBox.setLabel("Dimensiunea tricoului");
        shirtBox.setItems(ShirtSize.XS, ShirtSize.S, ShirtSize.M,
                          ShirtSize.L, ShirtSize.XL, ShirtSize.XXL);

        raceBox.setLabel("Cursa");
        raceBox.setItems(Race.CURSA_42KM, Race.CURSA_21KM, Race.CURSA_10KM, Race.CURSA_COPII);

        firstName.setTooltipText("Introduceti prenumele dumneavoastra");
        lastName.setTooltipText("Introduceti numele dumneavoastra de familie");

        /*firstName.setMaxWidth("200px");
        lastName.setMaxWidth("200px");
        email.setMaxWidth("200px");*/

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

        verticalLayout.add(horizontalLayout, horizontalLayout1);
        add(verticalLayout);
    }

}
