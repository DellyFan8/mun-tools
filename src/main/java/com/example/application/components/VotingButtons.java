package com.example.application.components;

import com.example.application.data.entity.User;
import com.example.application.database.AnswersRepository;
import com.example.application.database.AnswersService;
import com.example.application.database.Votes;
import com.example.application.security.AuthenticatedUser;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.auth.AccessAnnotationChecker;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class VotingButtons extends VerticalLayout {
    Button voteYay;
    Button voteNay;
    String username;
    H3 status;
    H1 title;
    AnswersService answersService;

    public VotingButtons(String username, AnswersService answersService){
        this.answersService = answersService;
        this.username = username;
        voteYay = new Button("Yay");
        voteNay = new Button("Nay");
        voteYay.addClickListener(e->yay());
        voteNay.addClickListener(e->nay());
        voteNay.setWidth("200px");
        voteYay.setWidth("200px");
        voteNay.addThemeVariants(ButtonVariant.LUMO_ERROR);
        voteYay.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        title = new H1("Welcome to the MUN Dashboard");

        HorizontalLayout horizontalLayout = new HorizontalLayout(voteYay, voteNay);
        horizontalLayout.setWidthFull();
        horizontalLayout.setVerticalComponentAlignment(Alignment.CENTER);
        horizontalLayout.setJustifyContentMode(JustifyContentMode.CENTER);
//        HorizontalLayout horizontalLayout1 = new HorizontalLayout(status);
//        horizontalLayout1.setWidthFull();
//        horizontalLayout1.setVerticalComponentAlignment(Alignment.CENTER);
//        horizontalLayout1.setJustifyContentMode(JustifyContentMode.CENTER);
        HorizontalLayout horizontalLayout2 = new HorizontalLayout(title);
        horizontalLayout2.setWidthFull();
        horizontalLayout2.setVerticalComponentAlignment(Alignment.CENTER);
        horizontalLayout2.setJustifyContentMode(JustifyContentMode.CENTER);

        setAlignItems(Alignment.START);

        add(horizontalLayout2, horizontalLayout);

    }

    private void nay() {
        if(answersService.checkForDup(username) == -1){
            Notification.show("You Voted Nay!", 5000, Notification.Position.BOTTOM_START);
           // status.setText("You have voted Nay");
            Votes votes = new Votes(1, "", "1", username, "GA");
            answersService.saveVotes(votes);
        }
        else {
            Notification.show("You already voted!");
        }

//
//
//        Notification.show("You Voted Nay");
//        status.setText("You have voted Nay");
//        Votes votes = new Votes(1, "", "1", username, "GA");
//        answersService.saveVotes(votes);
    }

    private void yay() {
        if(answersService.checkForDup(username) == -1){
            Notification.show("You Voted Yay!", 5000, Notification.Position.BOTTOM_START);
         //   status.setText("You have voted Yay");
            Votes votes = new Votes(1, "1", "", username, "GA");
            answersService.saveVotes(votes);
        }
        else {
            Notification.show("You already voted!");
        }
    }

}
