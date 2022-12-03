package es.uvigo.esei.dgss.teama.microstories.jsf.controllers;

import es.uvigo.esei.dgss.teama.microstories.service.StoryEJB;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named(value = "storyExploreController")
@SessionScoped
public class StoryExploreController implements Serializable {

    @EJB
    private StoryEJB storyEJB;

    public StoryExploreController() {
        // Empty constructor
    }

    @PostConstruct
    public void init() {
    }

    public String explore() {
        return "explore";
    }
}
