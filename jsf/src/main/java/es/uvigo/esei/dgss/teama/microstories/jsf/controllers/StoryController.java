package es.uvigo.esei.dgss.teama.microstories.jsf.controllers;

import es.uvigo.esei.dgss.teama.microstories.domain.entities.Genre;
import es.uvigo.esei.dgss.teama.microstories.domain.entities.Story;
import es.uvigo.esei.dgss.teama.microstories.domain.entities.Theme;
import es.uvigo.esei.dgss.teama.microstories.service.StoryEJB;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
  
  private int currentPage;
  private int totalPages;
  private String textFilter;
  
  private List<Story> storiesFilteredByText = new ArrayList<>();
 

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
  
  public int getCurrentPage() {
    return currentPage;
  }

  public void setCurrentPage(int currentPage) {
    this.currentPage = currentPage;
  }
  
  public int getTotalPages() {
    return totalPages;
  }

  public void setTotalPages(int totalPages) {
    this.totalPages = totalPages;
  }

  public String getTextFilter() {
    return textFilter;
  }

  public void setTextFilter(String textFilter) {
    this.textFilter = textFilter;
  }

  public List<Story> getStoriesFilteredByText() {
    return storiesFilteredByText;
  }

  public void setStoriesFilteredByText(List<Story> storiesFilteredByText) {
    this.storiesFilteredByText = storiesFilteredByText;
  }

  public String searchText() {
    storyEJB.getStoriesByText(textFilter, 1, 9);

    return "searchStories";
  }

  public void searchText(String page) {
    storyEJB.getStoriesByText(textFilter, Integer.parseInt(page), 9);
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


