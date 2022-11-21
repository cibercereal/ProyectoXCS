package es.uvigo.esei.dgss.teama.microstories.jsf.controllers;

import es.uvigo.esei.dgss.teama.microstories.entities.Story;
import es.uvigo.esei.dgss.teama.microstories.service.StoryEJB;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "storyController")
@SessionScoped
public class StoryController implements Serializable {

  @EJB
  private StoryEJB storyEJB;


  public StoryController() {

  }

  @PostConstruct
  public void init() {
  }

  public List<Story> getRecentStories() {
    return storyEJB.getRecentStories();
  }

}


