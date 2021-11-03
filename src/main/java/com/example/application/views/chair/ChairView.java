package com.example.application.views.chair;

import com.example.application.database.AnswersService;
import com.example.application.views.signedin.SignedInView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.example.application.views.MainLayout;
import javax.annotation.security.RolesAllowed;

@PageTitle("Chair")
@Route(value = "chair_tools", layout = MainLayout.class)
@RolesAllowed("admin")
public class ChairView extends VerticalLayout {

    H3 results;
    H3 status;
    Text extras;
    AnswersService answersService;
    public ChairView(AnswersService answersService) {
        this.answersService = answersService;
        setSpacing(false);

//        Image img = new Image("images/empty-plant.png", "placeholder plant");
//        img.setWidth("200px");
//        add(img);
//
//        add(new H2("This place intentionally left empty"));
//        add(new Paragraph("It’s a place where you can grow your own UI 🤗"));
//
//        setSizeFull();
//        setJustifyContentMode(JustifyContentMode.CENTER);
//        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
//        getStyle().set("text-align", "center");

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setWidthFull();
        horizontalLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        Button openVoting = new Button("Open Voting");
        Button closeVoting = new Button("Close Voting");
        openVoting.addClickListener(e->open());
        closeVoting.addClickListener((e->close()));
        openVoting.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        closeVoting.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        openVoting.setWidth("200px");
        closeVoting.setWidth("200px");
        horizontalLayout.add(openVoting, closeVoting);
        status = new H3("Results will appear once you close voting");
        results = new H3("");
        extras = new Text("");





        add(new H1("Chairing Tools"), horizontalLayout,status, results,extras);



        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");

    }

    private void close() {
        status.setText("Voting is now closed");
        results.setText("Votes Yay- "+answersService.getNumEach()[0]+"  |  Votes Nay- "+answersService.getNumEach()[1]);
        extras.setText("Total Votes- "+(answersService.getNumEach()[0]+answersService.getNumEach()[1])+"\nPercent in favor- "+(Math.round((100.0*answersService.getNumEach()[0])/(answersService.getNumEach()[0]+answersService.getNumEach()[1]))));
        answersService.setStatus("closed");
    }

    private void open() {
        answersService.clearVotes();
        results.setText("");
        extras.setText("");
        status.setText("Voting is now open");
        answersService.setStatus("open");
    }

}
