package es.uvigo.esei.dgss.teama.microstories.jsf.controllers;

import es.uvigo.esei.dgss.teama.microstories.domain.entities.Genre;
import es.uvigo.esei.dgss.teama.microstories.domain.entities.Publication;
import es.uvigo.esei.dgss.teama.microstories.domain.entities.Story;
import es.uvigo.esei.dgss.teama.microstories.domain.entities.Theme;
import es.uvigo.esei.dgss.teama.microstories.service.StoryEJB;
import static jdk.nashorn.internal.objects.NativeString.toLowerCase;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named(value = "storyExploreController")
@SessionScoped
public class StoryExploreController implements Serializable {

    @EJB
    private StoryEJB storyEJB;

    private static final int PAGE_NUMBER = 1;
    private static final int MAX_ITEMS = 6;
    private Genre selectedGenre;
    private Theme selectedTheme;
    private String selectedDate;
    private Publication selectedPublication;
    private List<Story> exploredStories;


    public StoryExploreController() {
        // Empty constructor
    }

    @PostConstruct
    public void init() {

    }

    public String explore() {
        return "explore";
    }

    public String exploreStories() {
        this.exploredStories = this.storyEJB.exploreStory(this.selectedGenre,
                this.selectedTheme, this.selectedPublication, PAGE_NUMBER, MAX_ITEMS);
        System.out.println(this.exploredStories);
        return "explore";
    }

    public String formatLowerCase(String text) {
        return toLowerCase(text);
    }

    public Genre getSelectedGenre() {
        return selectedGenre;
    }

    public void setSelectedGenre(Genre selectedGenre) {
        this.selectedGenre = selectedGenre;
    }

    public Theme getSelectedTheme() {
        return selectedTheme;
    }

    public void setSelectedTheme(Theme selectedTheme) {
        this.selectedTheme = selectedTheme;
    }

    public Publication getSelectedPublication() {
        return selectedPublication;
    }

    public void setSelectedPublication(Publication selectedPublication) {
        this.selectedPublication = selectedPublication;
    }

    public Genre[] getListGenre() {
        return Genre.values();
    }

    public Theme[] getListTheme() {
        return Theme.values();
    }

    public Publication[] getListPublication() {
        return Publication.values();
    }

}
