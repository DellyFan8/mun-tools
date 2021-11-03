package com.example.application.views.signedin;

import com.example.application.components.VotingButtons;
import com.example.application.database.AnswersService;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.example.application.views.MainLayout;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.annotation.security.RolesAllowed;

@PageTitle("Country Dashboard")
@Route(value = "signed_in", layout = MainLayout.class)
@RolesAllowed({"user"})
public class SignedInView extends VerticalLayout {

    private FeederThread thread;
    AnswersService answersService;
    H3 status;



    public SignedInView(AnswersService answersService) {
        this.answersService = answersService;
        status = new H3("Voting is currently "+answersService.checkStatus());
        setSpacing(false);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username = "empty";
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        }
        else{
            username = principal.toString();
        }
        HorizontalLayout horizontalLayout1 = new HorizontalLayout(status);
        horizontalLayout1.setWidthFull();
        horizontalLayout1.setVerticalComponentAlignment(Alignment.CENTER);
        horizontalLayout1.setJustifyContentMode(JustifyContentMode.CENTER);



        add(new VotingButtons(username, answersService), horizontalLayout1);

        //Add Image to the page
//        Image img = new Image("images/empty-plant.png", "placeholder plant");
//        img.setWidth("200px");
//        add(img);

        //Add Username on screen
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        String username;
//        if (principal instanceof UserDetails) {
//            username = ((UserDetails)principal).getUsername();
//        }
//        else{
//            username = principal.toString();
//        }
//        add(new H2());
//        add(new Paragraph(username));

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {

        // Start the data feed thread
        thread = new FeederThread(attachEvent.getUI(), this, answersService);
        thread.start();
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        // Cleanup
        thread.interrupt();
        thread = null;
    }

    private static class FeederThread extends Thread {
        String status;
        private final UI ui;
        private final SignedInView view;

        private int count = 0;
        AnswersService answersService;

        public FeederThread(UI ui, SignedInView view, AnswersService answersService) {
            this.ui = ui;
            this.view = view;
            this.answersService=answersService;
        }

        @Override
        public void run() {
            status = answersService.checkStatus();
           // System.out.println(status);
            try {
                // Update the data for a while
                while (1 < 100) {
                    // Sleep to emulate background work
                    Thread.sleep(3000);
                   // String message = "This is update " + count++;
                    if(answersService.checkStatus().equals(status) == false){
                        status = answersService.checkStatus();
                        ui.access(() -> view.status.setText("Voting is currently "+status));
                    }

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
