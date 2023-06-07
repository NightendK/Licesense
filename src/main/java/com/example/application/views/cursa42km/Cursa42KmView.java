package com.example.application.views.cursa42km;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;

@PageTitle("Cursa 42Km")
//@Route(value = "42km", layout = MainLayout.class)
public class Cursa42KmView extends VerticalLayout {

    public Cursa42KmView() {
        /*setSpacing(false);

        Image img = new Image("images/empty-plant.png", "placeholder plant");
        img.setWidth("200px");
        add(img);

        H2 header = new H2("This place intentionally left empty");
        header.addClassNames(Margin.Top.XLARGE, Margin.Bottom.MEDIUM);
        add(header);
        add(new Paragraph("It’s a place where you can grow your own UI 🤗"));

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");*/

        add(new Paragraph("This is the first paragraph introduces as a test for " +
                "future implementations. Let's hope it works as i expect.\n " +
                "\nLet's see if this also creates a new line xD"));  // only the new line doesn't work

        Image img = new Image("images/run.jpg", "run");
        img.setWidth("200px");
        add(img);
    }

}
