package es.uvigo.esei.dgss.teama.microstories.jsf.controllers;

import es.uvigo.esei.dgss.teama.microstories.domain.entities.Story;
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
  Story selectedStory;
  private static final int PAGE_NUMBER = 1;
  private static final int MAX_ITEMS = 6;
  private String searchText;
  private List<Story> searchedStoriesByText;

  @EJB
  private StoryEJB storyEJB;


  public StoryController() {

  }

  @PostConstruct
  public void init() {
    this.searchText = "";
    this.searchedStoriesByText = new ArrayList<Story>();
  }

  public List<Story> getRecentStories() {
    return storyEJB.getRecentStories();
  }

  public String searchStories() {
    this.searchedStoriesByText = this.storyEJB.getStoriesByText(this.searchText, PAGE_NUMBER, MAX_ITEMS);
    return "searchStories";
  }

  public String getSearchText() {
    return searchText;
  }

  public void setSearchText(String searchText) {
    this.searchText = searchText;
  }
  public String updateSelectedStory(int id) {
    this.selectedStory = storyEJB.getById(id);
    return "storyModal";
  }
  public Story getSelectedStory() {
    return selectedStory;
  }
  public String returnIndex(){
    return "index";
  }

}


