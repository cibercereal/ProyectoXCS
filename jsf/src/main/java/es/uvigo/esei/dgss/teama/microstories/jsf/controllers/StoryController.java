package es.uvigo.esei.dgss.teama.microstories.jsf.controllers;

import es.uvigo.esei.dgss.teama.microstories.domain.entities.Genre;
import es.uvigo.esei.dgss.teama.microstories.domain.entities.Story;
import es.uvigo.esei.dgss.teama.microstories.service.StoryEJB;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "storyController")
@SessionScoped
public class StoryController implements Serializable {
  Story selectedStory;
  private static final int PAGE_NUMBER = 0;
  private static final int MAX_ITEMS = 6;
  private String searchText;
  private List<Story> searchedStoriesByText;

  @EJB
  private StoryEJB storyEJB;
  
  private int currentPage;
  private int totalPages;
 

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

  public List<Story> getSearchedStoriesByText() {
	return searchedStoriesByText;
  }

  public void setSearchedStoriesByText(List<Story> searchedStoriesByText) {
	this.searchedStoriesByText = searchedStoriesByText;
  }

  public String searchStories() {
	this.searchedStoriesByText = this.storyEJB.getStoriesByText(this.searchText, PAGE_NUMBER, MAX_ITEMS);
	this.totalPages = storyEJB.getTotalPagesSearchText(this.searchText, MAX_ITEMS);
	return "searchStories";
  }
  
  public String searchStories(String page) {
	this.searchedStoriesByText = this.storyEJB.getStoriesByText(this.searchText, Integer.parseInt(page), MAX_ITEMS);
	this.totalPages = storyEJB.getTotalPagesSearchText(this.searchText, MAX_ITEMS);
	this.currentPage = Integer.parseInt(page);
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
  public List<Story> getMostReadStories(){
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DATE,-30);
    List<Story> storyGenre = storyEJB.findHottestStories(Genre.STORY,calendar.getTime(),new Date(),2);
    List<Story> poetryGenre = storyEJB.findHottestStories(Genre.POETRY,calendar.getTime(),new Date(),2);
    List<Story> nanoStoryGenre = storyEJB.findHottestStories(Genre.NANOSTORY,calendar.getTime(),new Date(),2);
    List<Story> storiesMostRead = new ArrayList<>();
    storiesMostRead.addAll(storyGenre);
    storiesMostRead.addAll(poetryGenre);
    storiesMostRead.addAll(nanoStoryGenre);

    return storiesMostRead;
  }
}


