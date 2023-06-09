package com.example.application.views.profile;

import com.example.application.backend.Model.Person;
import com.example.application.backend.Model.User;
import com.example.application.backend.Repository.PersonRepository;
import com.example.application.backend.Repository.UserRepository;
import com.example.application.views.login.LoginView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
@Route("/profile")
public class ProfileView extends VerticalLayout {

    Button button = new Button("Test");

    TextArea textArea = new TextArea();

    private final UserRepository userRepository;

    private final PersonRepository personRepository;
    private String sessionUsername;
    private User user;
    private Person person;
    public ProfileView(UserRepository userRepository, PersonRepository personRepository) {

        this.userRepository = userRepository;
        this.personRepository = personRepository;

        setSizeFull();
        setAlignItems(Alignment.CENTER);

        VaadinSession session = VaadinSession.getCurrent();
        try {
            sessionUsername =  session.getAttribute("username").toString();
            user = userRepository.getByUsername(sessionUsername);
            if (user == null) {
                Notification.show("User is NULL!!!");
                UI.getCurrent().close();
            }

            person = user.getPerson();

            textArea.setValue(person.getEmail());

        } catch(Exception e) {
            // no username is session
        }

        button.addClickListener(e -> {
            Notification.show(user.getUsername());
        });

        add(button, textArea);
    }
}
