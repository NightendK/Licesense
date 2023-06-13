package com.example.application.views.profile;

import com.example.application.backend.Enums.Race;
import com.example.application.backend.Enums.Sex;
import com.example.application.backend.Model.Person;
import com.example.application.backend.Model.User;
import com.example.application.backend.Repository.PersonRepository;
import com.example.application.backend.Repository.UserRepository;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;


@Route("/profile")
@CssImport("./styles/views/profile/profile-view.css")
public class ProfileView extends VerticalLayout {
    private final UserRepository userRepository;
    private final PersonRepository personRepository;
    private final HorizontalLayout horizontalLayout = new HorizontalLayout();
    private String sessionUsername;
    private User user;
    private Person person;
    private Label welcomeLabel = new Label();
    private Label nameLabel = new Label();
    private Label emailLabel = new Label();
    private Label raceLabel = new Label();
    private Label kitLabel = new Label();
    private Avatar avatar = new Avatar();
    private Image shirt = new Image();
    private Button delButton = new Button("Delete Acccount");
    private Button updateButton = new Button("Modify");

    public ProfileView(UserRepository userRepository, PersonRepository personRepository) {

        this.userRepository = userRepository;
        this.personRepository = personRepository;

        setSizeFull();
        setAlignItems(Alignment.AUTO);

        shirt.setSrc("images/raceShirt.png");
        shirt.setId("shirt");
        shirt.setHeight("450px");

        Label shirtLabel = new Label();
        shirtLabel.setId("shirtLabel");
        shirtLabel.setText("Race shirt");

        Details shirtDetail = new Details();
        shirtDetail.setSummary(shirtLabel);
        shirtDetail.addContent(shirt);

        VaadinSession session = VaadinSession.getCurrent();
        try {
            sessionUsername =  session.getAttribute("username").toString();
            user = userRepository.getByUsername(sessionUsername);
            if (user == null) {
                Notification.show("User is NULL!!!");
                UI.getCurrent().close();
            }
            person = user.getPerson();

            welcomeLabel.setId("welcomeLabel");
            welcomeLabel.setText("Welcome " + person.getFirstName());

            if (person.getSex().equals(Sex.FEMEIE)) {
                avatar.setImage("images/female_avatar.png");
                avatar.setHeight("90px");
                avatar.setWidth("90px");

                horizontalLayout.setSpacing(true);
                horizontalLayout.add(avatar, welcomeLabel);
            }

            else if (person.getSex().equals(Sex.BARBAT)) {
                avatar.setImage("images/male-avatar.jpg");
                avatar.setHeight("90px");
                avatar.setWidth("90px");

                horizontalLayout.setSpacing(true);
                horizontalLayout.add(avatar, welcomeLabel);
            }

            ///////////////////////////////////////////

            avatar.setId("avatar");

            nameLabel.setId("welcomeLabel");
            nameLabel.setText("Full-name: " + person.getFirstName() + " " + person.getLastName());

            emailLabel.setId("welcomeLabel");
            emailLabel.setText("Email: " + person.getEmail());

            kitLabel.setId("welcomeLabel");
            kitLabel.setText("Kit size: " + person.getShirtSize());

            raceLabel.setId("welcomeLabel");
            if (person.getRace().equals(Race.CURSA_42KM)) {
                raceLabel.setText("Race you are registred to: 42Km");
            }
            else if (person.getRace().equals(Race.CURSA_21KM)) {
                raceLabel.setText("Race you are registred to: 21Km");
            }

            else if (person.getRace().equals(Race.CURSA_10KM)) {
                raceLabel.setText("Race you are registred to: 10Km");
            }

            else if (person.getRace().equals(Race.CURSA_COPII)) {
                raceLabel.setText("Race you are registred to: Children's Race");
            }

        } catch(Exception e) {
            Notification.show("User not identified");
        }

        delButton.setId("delButton");
        updateButton.setId("updateButton");

        delButton.addClickListener(e -> {
            ConfirmDialog dialog = new ConfirmDialog();
            dialog.setHeader("Delete account");
            dialog.setText("You are about to delete your account. By doing this your place in the race will be removed. " +
                    "Are you sure you want to proceed?");

            dialog.setCancelable(true);
            dialog.setRejectable(false);

            dialog.setConfirmText("Delete");
            dialog.open();
            dialog.addConfirmListener(event -> {
                VaadinSession session1 = VaadinSession.getCurrent();
                sessionUsername =  session1.getAttribute("username").toString();
                user = userRepository.getByUsername(sessionUsername);
                if (user == null) {
                    Notification.show("User is NULL!!!");
                    UI.getCurrent().close();
                }
                person = user.getPerson();
                personRepository.delete(person);
                Notification.show("Account was deleted successfully");
                UI.getCurrent().navigate("/loginA");

            });
        });


        add(horizontalLayout, nameLabel, emailLabel, kitLabel, raceLabel, delButton, shirtDetail);
    }
}
