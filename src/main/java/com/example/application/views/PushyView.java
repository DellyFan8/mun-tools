//package com.example.application.views;
//
//import com.example.application.database.AnswersService;
//import com.vaadin.flow.component.AttachEvent;
//import com.vaadin.flow.component.DetachEvent;
//import com.vaadin.flow.component.UI;
//import com.vaadin.flow.component.html.Span;
//import com.vaadin.flow.component.orderedlayout.VerticalLayout;
//import com.vaadin.flow.component.page.Push;
//import com.vaadin.flow.router.Route;
//
//import javax.annotation.security.RolesAllowed;
//
//
//@Route(value = "push", layout = MainLayout.class)
//@RolesAllowed("admin")
//public class PushyView extends VerticalLayout {
//    private FeederThread thread;
//    AnswersService answersService;
//
//    public PushyView(AnswersService answersService){
//        this.answersService = answersService;
//    }
//
//    @Override
//    protected void onAttach(AttachEvent attachEvent) {
//        add(new Span("Waiting for updates"));
//
//        // Start the data feed thread
//        thread = new FeederThread(attachEvent.getUI(), this, answersService);
//        thread.start();
//    }
//
//    @Override
//    protected void onDetach(DetachEvent detachEvent) {
//        // Cleanup
//        thread.interrupt();
//        thread = null;
//    }
//
//    private static class FeederThread extends Thread {
//        String status;
//        private final UI ui;
//        private final PushyView view;
//
//        private int count = 0;
//        AnswersService answersService;
//
//        public FeederThread(UI ui, PushyView view, AnswersService answersService) {
//            this.ui = ui;
//            this.view = view;
//            this.answersService=answersService;
//        }
//
//        @Override
//        public void run() {
//            status = answersService.checkStatus();
//     //      System.out.println(status);
//            try {
//                // Update the data for a while
//                while (1 < 100) {
//                    // Sleep to emulate background work
//                    Thread.sleep(2000);
//                    String message = "This is update " + count++;
//
//                    ui.access(() -> view.add(new Span(message)));
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}