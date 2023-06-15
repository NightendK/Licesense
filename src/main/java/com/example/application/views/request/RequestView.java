package com.example.application.views.request;

import com.example.application.backend.Enums.ShirtSize;
import com.example.application.backend.Model.Person;
import com.example.application.backend.Model.RequestModel;
import com.example.application.backend.Model.User;
import com.example.application.backend.Repository.UserRepository;
import com.example.application.backend.Service.RequestService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Section;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route("/request")
public class RequestView extends Section {

    private final RequestService requestService;
    private final UserRepository userRepository;
    private User user;
    private Person person;
    private final TextField firstName = new TextField("Prenume");
    private final TextField lastName = new TextField("Nume");
    private final EmailField email = new EmailField();
    private final ComboBox<ShirtSize> shirtBox = new ComboBox<>();
    private final TextField userName = new TextField("Username");
    private final Button button = new Button("Send Request");
    private String sessionUsername;

    public RequestView(RequestService requestService, UserRepository userRepository){

        this.requestService = requestService;
        this.userRepository = userRepository;

        firstName.setId("firstName");
        lastName.setId("lastName");
        email.setId("email");
        shirtBox.setId("shirtBox");
        userName.setId("userName");

        email.setLabel("Adresa de email");
        email.setErrorMessage("Introduceti o adresa de email valida!");
        email.setClearButtonVisible(true);
        email.setTooltipText("Adresa de email");
        email.setPlaceholder("nume@exemplu.com");
        email.setPrefixComponent(VaadinIcon.ENVELOPE.create());

        shirtBox.setLabel("Dimensiunea tricoului");
        shirtBox.setItems(ShirtSize.XS, ShirtSize.S, ShirtSize.M,
                ShirtSize.L, ShirtSize.XL, ShirtSize.XXL);

        VerticalLayout verticalLayout = createRequestForm();

        button.addClickListener(e -> {
            VaadinSession session = VaadinSession.getCurrent();

            sessionUsername =  session.getAttribute("username").toString();
            user = userRepository.getByUsername(sessionUsername);

            if (user == null) {
                Notification.show("User is NULL!!!");
                UI.getCurrent().close();
            }

            person = user.getPerson();
            if (person == null) {
                Notification.show("User is NULL!!!");
                UI.getCurrent().close();
            }



            RequestModel model = new RequestModel();
            model.setFirstName(firstName.getValue());
            model.setLastName(lastName.getValue());
            model.setEmail(email.getValue());
            model.setUsername(userName.getValue());
            model.setShirtSize(shirtBox.getValue());
            model.setUserId(user.getId());
            model.setPersonId(person.getId());

            requestService.add(model);
            Notification.show("Your request has been processed");
            UI.getCurrent().navigate("/home");

        });


        add(verticalLayout);
    }

    private VerticalLayout createRequestForm() {

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
        horizontalLayout2.add(shirtBox, userName);


        HorizontalLayout horizontalLayout3= new HorizontalLayout();
        horizontalLayout3.setSizeFull();
        horizontalLayout3.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        horizontalLayout3.setAlignItems(FlexComponent.Alignment.CENTER);
        horizontalLayout3.setSpacing(true);
        horizontalLayout3.add(button);


        verticalLayout.add(horizontalLayout, horizontalLayout1, horizontalLayout2, horizontalLayout3);

        return verticalLayout;
    }

}
