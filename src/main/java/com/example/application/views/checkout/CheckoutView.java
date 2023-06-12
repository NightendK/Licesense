package com.example.application.views.checkout;

import com.example.application.backend.Enums.Race;
import com.example.application.backend.Enums.Role;
import com.example.application.backend.Model.Person;
import com.example.application.backend.Repository.PersonRepository;
import com.example.application.backend.Service.AuthService;
import com.example.application.views.register.RegisterView;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfWriter;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.lumo.LumoUtility.AlignItems;
import com.vaadin.flow.theme.lumo.LumoUtility.Background;
import com.vaadin.flow.theme.lumo.LumoUtility.BorderRadius;
import com.vaadin.flow.theme.lumo.LumoUtility.BoxSizing;
import com.vaadin.flow.theme.lumo.LumoUtility.Display;
import com.vaadin.flow.theme.lumo.LumoUtility.Flex;
import com.vaadin.flow.theme.lumo.LumoUtility.FlexDirection;
import com.vaadin.flow.theme.lumo.LumoUtility.FlexWrap;
import com.vaadin.flow.theme.lumo.LumoUtility.FontSize;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import com.vaadin.flow.theme.lumo.LumoUtility.Height;
import com.vaadin.flow.theme.lumo.LumoUtility.JustifyContent;
import com.vaadin.flow.theme.lumo.LumoUtility.ListStyleType;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import com.vaadin.flow.theme.lumo.LumoUtility.MaxWidth;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding;
import com.vaadin.flow.theme.lumo.LumoUtility.Position;
import com.vaadin.flow.theme.lumo.LumoUtility.TextColor;



import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;


@Route("/checkout")
@CssImport("./styles/views/checkout/checkout-view.css")
public class CheckoutView extends Div {
    private static final Set<String> tari = new LinkedHashSet<>();
    private final TextField name = new TextField("Full-Name");
    private final EmailField email = new EmailField("Email address");
    private final ComboBox<String> countrySelect = new ComboBox<>("Country");
    private final TextArea address = new TextArea("Address");
    private final TextField postalCode = new TextField("Postal Code");
    private final TextField city = new TextField("City");
    private final TextField cardNumber = new TextField("Card Number");
    private final TextField cardHolder = new TextField("Cardholder name");
    private final Select<String> expirationMonth = new Select<>();
    private final TextField securityCode = new TextField("Security Code");
    private final Select<String> expirationYear = new Select<>();
    private static boolean flag = false;
    private PersonRepository personRepository;
    private AuthService authService;
    private Person person = new Person();

    static {
        tari.addAll(Arrays.asList("Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola",
                "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia",
                "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize",
                "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegovina", "Botswana", "Bouvet Island",
                "Brazil", "British Indian Ocean Territory", "British Virgin Islands", "Brunei Darussalam", "Bulgaria",
                "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands",
                "Central African Republic", "Chad", "Chile", "China", "Christmas Island", "Cocos (Keeling) Islands",
                "Colombia", "Comoros", "Congo", "Cook Islands", "Costa Rica", "Croatia", "Cuba", "Cyprus",
                "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador",
                "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Falkland Islands",
                "Faroe Islands", "Federated States of Micronesia", "Fiji", "Finland", "France", "French Guiana",
                "French Polynesia", "French Southern Territories", "Gabon", "Gambia", "Georgia", "Germany", "Ghana",
                "Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guinea",
                "Guinea-Bissau", "Guyana", "Haiti", "Heard Island and McDonald Islands", "Honduras", "Hong Kong",
                "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Israel", "Italy", "Ivory Coast",
                "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Kuwait", "Kyrgyzstan", "Laos",
                "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau",
                "Macedonia", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands",
                "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico", "Moldova", "Monaco", "Mongolia",
                "Montserrat", "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands",
                "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue",
                "Norfolk Island", "North Korea", "Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau",
                "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn", "Poland", "Portugal",
                "Puerto Rico", "Qatar", "Reunion", "Romania", "Russian Federation", "Rwanda", "Saint Kitts and Nevis",
                "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe",
                "Saudi Arabia", "Senegal", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia",
                "Solomon Islands", "Somalia", "South Africa", "South Georgia and the South Sandwich Islands",
                "South Korea", "Spain", "Sri Lanka", "St. Helena", "St. Pierre and Miquelon", "Sudan", "Suriname",
                "Svalbard and Jan Mayen Islands", "Swaziland", "Sweden", "Switzerland", "Syrian Arab Republic",
                "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago",
                "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine",
                "United Arab Emirates", "United Kingdom", "United States", "United States Minor Outlying Islands",
                "United States Virgin Islands", "Uruguay", "Uzbekistan", "Vanuatu", "Vatican City State", "Venezuela",
                "Vietnam", "Wallis and Futuna Islands", "Western Sahara", "Yemen", "Yugoslavia", "Zaire", "Zambia",
                "Zimbabwe"));
    }

    public CheckoutView(PersonRepository personRepository, AuthService authService) {
        this.personRepository = personRepository;
        this.authService = authService;
        addClassNames("check-out-view");
        addClassNames(Display.FLEX, FlexDirection.COLUMN, Height.FULL);
        setId("checkout");

        Main content = new Main();
        content.addClassNames(Display.GRID, Gap.XLARGE, AlignItems.START, JustifyContent.CENTER, MaxWidth.SCREEN_MEDIUM,
                Margin.Horizontal.AUTO, Padding.Bottom.LARGE, Padding.Horizontal.LARGE);

        content.add(createCheckoutForm());
        add(content);
    }

    private Component createCheckoutForm() {
        Section checkoutForm = new Section();
        checkoutForm.addClassNames(Display.FLEX, FlexDirection.COLUMN, Flex.GROW);

        H2 header = new H2("Checkout");
        header.addClassNames(Margin.Bottom.NONE, Margin.Top.XLARGE, FontSize.XXXLARGE);
        Paragraph note = new Paragraph("All fields marked * are required");
        note.addClassNames(Margin.Bottom.XLARGE, Margin.Top.NONE, TextColor.SECONDARY);
        checkoutForm.add(header, note);

        checkoutForm.add(createPersonalDetailsSection());
        checkoutForm.add(createPaymentSection());
        checkoutForm.add(createShippingAddressSection());
        checkoutForm.add(new Hr());
        checkoutForm.add(createFooter());

        return checkoutForm;
    }

    private Section createPersonalDetailsSection() {
        Section personalDetails = new Section();
        personalDetails.addClassNames(Display.FLEX, FlexDirection.COLUMN, Margin.Bottom.XLARGE, Margin.Top.MEDIUM);

        Paragraph stepOne = new Paragraph("Checkout 1/3");
        stepOne.addClassNames(Margin.NONE, FontSize.SMALL, TextColor.SECONDARY);

        H3 header = new H3("Personal details");
        header.addClassNames(Margin.Bottom.MEDIUM, Margin.Top.SMALL, FontSize.XXLARGE);

        name.setRequiredIndicatorVisible(true);
        name.setPattern("[\\p{L} \\-]+");
        name.addClassNames(Margin.Bottom.SMALL);

        email.setRequiredIndicatorVisible(true);
        email.addClassNames(Margin.Bottom.SMALL);
        email.setPlaceholder("nume@mail.com");

        personalDetails.add(stepOne, header, name, email);
        return personalDetails;
    }

    private Section createShippingAddressSection() {
        Section shippingDetails = new Section();
        shippingDetails.addClassNames(Display.FLEX, FlexDirection.COLUMN, Margin.Bottom.XLARGE, Margin.Top.MEDIUM);

        Paragraph stepTwo = new Paragraph("Checkout 3/3");
        stepTwo.addClassNames(Margin.NONE, FontSize.SMALL, TextColor.SECONDARY);

        H3 header = new H3("Shipping address");
        header.addClassNames(Margin.Bottom.MEDIUM, Margin.Top.SMALL, FontSize.XXLARGE);

        countrySelect.setRequiredIndicatorVisible(true);
        countrySelect.addClassNames(Margin.Bottom.SMALL);

        address.setMaxLength(200);
        address.setRequiredIndicatorVisible(true);
        address.addClassNames(Margin.Bottom.SMALL);
        address.setPlaceholder("Str.{Value} Nr.{Value}");

        Div subSection = new Div();
        subSection.addClassNames(Display.FLEX, FlexWrap.WRAP, Gap.MEDIUM);

        postalCode.setRequiredIndicatorVisible(true);
        postalCode.setPattern("[\\d \\p{L}]*");
        postalCode.addClassNames(Margin.Bottom.SMALL);

        city.setRequiredIndicatorVisible(true);
        city.addClassNames(Flex.GROW, Margin.Bottom.SMALL);

        subSection.add(postalCode, city);

        countrySelect.setItems(tari);
        shippingDetails.add(stepTwo, header, countrySelect, address, subSection);



        return shippingDetails;
    }

    private Component createPaymentSection() {
        Section paymentInfo = new Section();
        paymentInfo.addClassNames(Display.FLEX, FlexDirection.COLUMN, Margin.Bottom.XLARGE, Margin.Top.MEDIUM);

        Paragraph stepThree = new Paragraph("Checkout 2/3");
        stepThree.addClassNames(Margin.NONE, FontSize.SMALL, TextColor.SECONDARY);

        H3 header = new H3("Card informations");
        header.addClassNames(Margin.Bottom.MEDIUM, Margin.Top.SMALL, FontSize.XXLARGE);

        cardNumber.setRequiredIndicatorVisible(true);
        cardNumber.addClassNames(Margin.Bottom.SMALL);
        cardNumber.setPlaceholder("0000-0000-0000-0000");
        cardNumber.setMinLength(19);
        cardNumber.setMaxLength(19);
        cardNumber.setAllowedCharPattern("[0-9-]");

        Div subSectionOne = new Div();
        subSectionOne.addClassNames(Display.FLEX, FlexWrap.WRAP, Gap.MEDIUM);

        cardHolder.setRequiredIndicatorVisible(true);
        cardHolder.setPattern("[\\p{L} \\-]+");
        cardHolder.addClassNames(Margin.Bottom.SMALL);

        securityCode.setRequiredIndicatorVisible(true);
        securityCode.setPattern("[0-9]{3,4}");
        securityCode.setPlaceholder("---");
        securityCode.addClassNames(Flex.GROW, Margin.Bottom.SMALL);
        securityCode.setHelperText("Back of the card");
        securityCode.setAllowedCharPattern("[0-9]");
        securityCode.setMinLength(3);
        securityCode.setMaxLength(3);

        subSectionOne.add(cardHolder, securityCode);

        Div subSectionTwo = new Div();
        subSectionTwo.addClassNames(Display.FLEX, FlexWrap.WRAP, Gap.MEDIUM);

        expirationMonth.setLabel("Expiration month");
        expirationMonth.setRequiredIndicatorVisible(true);
        expirationMonth.setItems("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12");

        expirationYear.setLabel("Expiration year");
        expirationYear.setRequiredIndicatorVisible(true);
        expirationYear.setItems("23", "24", "25", "26", "27", "28", "29", "30", "31", "32");

        subSectionTwo.add(expirationMonth, expirationYear);

        paymentInfo.add(stepThree, header, cardNumber, subSectionOne, subSectionTwo);
        return paymentInfo;
    }

    private Footer createFooter() {
        Footer footer = new Footer();
        footer.addClassNames(Display.FLEX, AlignItems.CENTER, JustifyContent.BETWEEN, Margin.Vertical.MEDIUM);

        Button cancel = new Button("Cancel order");
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        cancel.addClickListener(e -> {
            UI.getCurrent().navigate(RegisterView.class);
        });

        Button pay = new Button("Pay", new Icon(VaadinIcon.LOCK));
        pay.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);
        pay.addClickListener(e -> {
            payment(name.getValue(), email.getValue(), countrySelect.getValue(), address.getValue(),
                    postalCode.getValue(), city.getValue(), cardNumber.getValue(),
                    cardHolder.getValue(), expirationMonth.getValue(), expirationYear.getValue(), securityCode.getValue());

            if (flag) {
                Notification error = new Notification();
                error.show("Form was not completed rightly");
                error.addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
            else if (!flag) {
                Notification succes = new Notification();
                succes.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                succes.show("Payment was made!");
                person = (Person) VaadinSession.getCurrent().getAttribute("person");
                personRepository.save(person);
                authService.createRoutes(Role.USER);
                try {
                    generatePDF();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (DocumentException ex) {
                    throw new RuntimeException(ex);
                }
                UI.getCurrent().navigate("/home");
            }
        });

        footer.add(cancel, pay);
        return footer;
    }

    private ListItem createListItem(String primary, String secondary, String price) {
        ListItem item = new ListItem();
        item.addClassNames(Display.FLEX, JustifyContent.BETWEEN);

        Div subSection = new Div();
        subSection.addClassNames(Display.FLEX, FlexDirection.COLUMN);

        subSection.add(new Span(primary));
        Span secondarySpan = new Span(secondary);
        secondarySpan.addClassNames(FontSize.SMALL, TextColor.SECONDARY);
        subSection.add(secondarySpan);

        Span priceSpan = new Span(price);

        item.add(subSection, priceSpan);
        return item;
    }

    private void payment(String name, String email, String country, String address,
                         String postalCode, String city, String cardNumber,
                         String cardHolder, String month, String year, String securityCode) {

        if (name.isEmpty()) {
            flag = true;
            Notification.show("Please enter your name");
        }

        else if (email.isEmpty()) {
            flag = true;
            Notification.show("Please enter your email");
        }

        else if (cardNumber.isEmpty()) {
            flag = true;
            Notification.show("Please enter your card number");
        }

        else if (cardNumber.length() != 19) {
            flag = true;
            Notification.show("Your card number length is not appropriate");
        }

        else if (cardHolder.isEmpty()) {
            flag = true;
            Notification.show("Please enter your careholder name.");
        }

        else if (securityCode.isEmpty()) {
            flag = true;
            Notification.show("Please enter your security code.");
        }

        else if (securityCode.length() != 3) {
            flag = true;
            Notification.show("Your security code is not the appropriate length.");
        }

        else if (month.isEmpty()) {
            flag = true;
            Notification.show("Please select your card expiration month.");
        }

        else if (year.isEmpty()) {
            flag = true;
            Notification.show("Please select your card expiration year.");
        }

        else if (country.isEmpty()) {
            flag = true;
            Notification.show("Please select your country");
        }

        else if (address.isEmpty()) {
            flag = true;
            Notification.show("Please enter your address");
        }

        else if (postalCode.isEmpty()) {
            flag = true;
            Notification.show("Please enter postal code.");
        }

        else if (city.isEmpty()) {
            flag = true;
            Notification.show("Please enter your city code.");
        }

    }

    public void generatePDF() throws FileNotFoundException, DocumentException {

        String payedValue = "";
        person = (Person) VaadinSession.getCurrent().getAttribute("person");

        if (person.getRace().equals(Race.CURSA_42KM)) {
            payedValue = Person.CURSA_42KM_VALUE;
        }

        else if (person.getRace().equals(Race.CURSA_21KM)) {
            payedValue = Person.CURSA_21KM_VALUE;
        }

        else if (person.getRace().equals(Race.CURSA_10KM)) {
            payedValue = Person.CURSA_10KM_VALUE;
        }

        else if (person.getRace().equals(Race.CURSA_COPII)) {
            payedValue = Person.CURSA_COPII_VALUE;
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 19, Font.BOLD);
        Font contentFont = new Font(Font.FontFamily.TIMES_ROMAN, 15);

        String fileLocation = "C:\\Users\\Admin\\Desktop\\Receipt" + "_" + person.getFirstName() + person.getLastName() +".pdf";
        Document document = new Document();
        PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(fileLocation));
        document.open();



        com.itextpdf.text.Paragraph title = new com.itextpdf.text.Paragraph();
        title.add(new com.itextpdf.text.Paragraph("Receipt\n\n\n", titleFont));
        document.add(title);


        com.itextpdf.text.Paragraph firstContent = new com.itextpdf.text.Paragraph();
        firstContent.add(new com.itextpdf.text.Paragraph("The following item has been purchased:\n", contentFont));
        document.add(firstContent);

        com.itextpdf.text.Paragraph secondContent = new com.itextpdf.text.Paragraph();
        secondContent.add(new com.itextpdf.text.Paragraph("Race : " + person.getRace() + "\n" +
                "Name : " + name.getValue() + "\n" + "Email : " + email.getValue() + "\n" +
                "Payed ammount : " + payedValue + "\n\n", contentFont));
        document.add(secondContent);

        com.itextpdf.text.Paragraph thirdContent = new com.itextpdf.text.Paragraph();
        thirdContent.add(new com.itextpdf.text.Paragraph("Date of the purchase : " + dtf.format(now) + "\n\n", contentFont));
        document.add(thirdContent);

        com.itextpdf.text.Paragraph fourthContent = new com.itextpdf.text.Paragraph();
        fourthContent.add(new com.itextpdf.text.Paragraph("Receipt address informations: \n", contentFont));
        document.add(fourthContent);

        com.itextpdf.text.Paragraph fifthContent = new com.itextpdf.text.Paragraph();
        fifthContent.add(new com.itextpdf.text.Paragraph("Country : " + countrySelect.getValue() + "\n" +
                "City : " + city.getValue() + "\n" +
                "Address : " + address.getValue() + "\n", contentFont));
        document.add(fifthContent);

        document.close();
    }
}