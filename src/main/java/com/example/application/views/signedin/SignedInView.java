package com.example.application.views.signedin;

import com.example.application.components.VotingButtons;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
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

    public SignedInView() {
        setSpacing(false);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username = "empty";
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        }
        else{
            username = principal.toString();
        }


        add(new VotingButtons(username));

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

}
