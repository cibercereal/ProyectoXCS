package es.uvigo.esei.dgss.teama.microstories.jsf.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "storyController")
@SessionScoped
public class StoryController implements Serializable {
  private List<String> recentStories;


  public StoryController() {

  }


  public List<String> getRecentStories() {

    return recentStories;
  }

  public void setRecentStories(List<String> recentStories) {
    this.recentStories = recentStories;
  }

  @PostConstruct
  public void init() {

    recentStories = new ArrayList<>();
    recentStories.add("Story 1");
    recentStories.add("Story 2");
    recentStories.add("Story 3");
    recentStories.add("Story 4");
    recentStories.add("Story 5");
    recentStories.add("Story 6");

  }



}


