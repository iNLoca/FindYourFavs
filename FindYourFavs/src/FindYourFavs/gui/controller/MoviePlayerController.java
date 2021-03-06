/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FindYourFavs.gui.controller;

import FindYourFavs.be.Category;
import FindYourFavs.be.Movie;
import FindYourFavs.bll.Interface;
import FindYourFavs.bll.Manager;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 */
public class MoviePlayerController implements Initializable {

    //Creation of variales for this class
    private Manager manager = new Manager();
    private Category category;
    private ObservableList<Movie> movieLst;
    private ObservableList<Category> categoryLst;

    @FXML
    private Label label;
    @FXML
    private Button addmovie;
    @FXML
    private Button deletemovie;
    @FXML
    private Button addcategory;
    @FXML
    private Button deletecategory;
    @FXML
    private Button editrating;
    @FXML
    private Label lblChosenCategory;
    @FXML
    private TableView<Category> categoriesView;
    @FXML
    private TableColumn<Category, String> categoriesColumn;
    @FXML
    private Button playmovie;
    @FXML
    private TextField searchbarField;
    @FXML
    private TableView<Movie> tableview;
    @FXML
    private TableColumn<Movie, String> movietittle;
    @FXML
    private TableColumn<Movie, Float> usrrating;
    @FXML
    private TableColumn<Movie, Float> imdbrating;

    private SelectionModel<Movie> currentListSelection;
    @FXML
    private ImageView searchButton;
    @FXML
    private Button linkbtn;
    @FXML
    private Button showall;
    @FXML
    private TextField ratinglbl;
    @FXML
    private Button searchbyratingbtn;

    private Interface inter;

    /**
     * Initializes the controller class.
     */
    @Override
    
    //Method that initializes the controller. the Alert and refresh method from this class are called
    
    public void initialize(URL url, ResourceBundle rb) {
        inter = new Manager();

        Alert();

        refresh();
    }

    @FXML
    //Method that opens the addMovie window and calls the setMoviePlayerContoller method found on this class
    private void clickaddmovie(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FindYourFavs/gui/view/AskAddMovie.fxml"));
        Parent root = loader.load();
        AskAddMovieController ctrl = loader.getController();
        ctrl.setMoviePlayerController(this);

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
     //Method that opens the DeleteMovie window and calls the setMoviePlayerContoller method found on this class
    private void clickdeletemovie(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FindYourFavs/gui/view/AskDeleteMovie.fxml"));
        Parent root = loader.load();
        AskDeleteMovieController ctrl = loader.getController();
        ctrl.setMoviePlayerController(this);

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
     //Method that opens the addCategory window and calls the setMoviePlayerContoller method found on this class
    private void clickaddcategory(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FindYourFavs/gui/view/AskAddCategory.fxml"));
        Parent root = loader.load();
        AskAddCategoryController ctrl = loader.getController();
        ctrl.setMoviePlayerController(this);

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
     //Method that opens the deleteCategory window and calls the setMoviePlayerContoller method found on this class
    private void clickdeletecategory(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FindYourFavs/gui/view/AskDeleteCategory.fxml"));
        Parent root = loader.load();
        AskDeleteCategoryController ctrl = loader.getController();
        ctrl.setMoviePlayerController(this);

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
     //Method that opens the EditRating window and calls the setMoviePlayerContoller method found on this class
    private void clickEditRating(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FindYourFavs/gui/view/EditRating.fxml"));
        Parent root = loader.load();
        EditRatingController ctrl = loader.getController();
        ctrl.setMoviePlayerController(this);
        ctrl.setMovie(tableview.getSelectionModel().getSelectedItem());

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
     //Method related to the initial Alert of the program, sets up the information for the alert and then displays it
    public void Alert() {
        manager.AlertData();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("REMINDER");
        alert.setHeaderText(null);
        alert.setContentText("Remember to delete old movies! The following movies "
                + "are with user rating less than 6 stars and last viewed more than 2 years ago:\n"
                + manager.AlertData().toString());

        alert.showAndWait();
    }

    //deletes a movie from the database       
    public void deleteMovie() {
        int a= tableview.getSelectionModel().getSelectedItem().getId(); 
        manager.deleteMovieById(a);
    }

    //deletes a category from the database
    public void deleteCategory() {
        manager.deleteCategoryById(categoriesView.getSelectionModel().getSelectedItem().getId());
    }
    
    //returns the name of the selected movie in the table
    public String returnNameOfMovie() {
        return tableview.getSelectionModel().getSelectedItem().getName();
    }
    
    //returns the selected movie object in the table
    public Movie returnMovie() {
        return tableview.getSelectionModel().getSelectedItem();
    }

    //Method that reads all the movies and categories contained in the Movies and Category tables of the database and fills the tableview and catView with them, respectively 
    public void refresh() {
        movieLst = FXCollections.observableArrayList(manager.getAllMovies());
        movietittle.setCellValueFactory(new PropertyValueFactory<>("name"));
        usrrating.setCellValueFactory(new PropertyValueFactory<>("personalRating"));
        imdbrating.setCellValueFactory(new PropertyValueFactory<>("IMDBRating"));
        tableview.setItems(movieLst);

        lblChosenCategory.setText("No category chosen yet");
        categoryLst = FXCollections.observableArrayList(manager.getAllCategories());
        categoriesColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoriesView.setItems(categoryLst);
    }

    @FXML
    //Method that sets the tableview with the movies that match a certain category and also sets the lblChosenCategory text to the chosen category
    private void chosenCategory(MouseEvent event) {
        lblChosenCategory.setText(categoriesView.getSelectionModel().getSelectedItem().getName());
        movieLst = FXCollections.observableArrayList(manager.MoviesFromSelectedCategory(categoriesView.getSelectionModel().getSelectedItem().getId()));
        movietittle.setCellValueFactory(new PropertyValueFactory<>("name"));
        usrrating.setCellValueFactory(new PropertyValueFactory<>("personalRating"));
        imdbrating.setCellValueFactory(new PropertyValueFactory<>("IMDBRating"));
        tableview.setItems(movieLst);
    }

    @FXML
     //Method that gets the text from the searchbarfield when the SearchMovieBtn is pressed and sets the tableview with the items that match the criteria in the database. this is achieved
    //by using the method getAllMoviesWithFilter inside the manager class
    private void searchMovieBtn(MouseEvent event) {
        String query = searchbarField.getText();
        if (query != null) {
            movieLst = FXCollections.observableArrayList(manager.getAllMoviesWithFilter(query));
            tableview.setItems(movieLst);
        } else {
            refresh();
            
        }
    }

    @FXML
    //Method that gets the filelink from the selected movie and plays it
    private void playMovie(ActionEvent event) throws IOException {
        Movie movie = tableview.getSelectionModel().getSelectedItem();
        String currentFileLink = movie.getFileLink();
        File movieFile = new File(currentFileLink);
        if (movieFile.exists()) {
            Desktop.getDesktop().open(movieFile);
        }
    }

    @FXML
    //Method that gets the IMDBbrowser from the selected movie and opens it in an internet window
    private void clicklinkbtn(ActionEvent event) throws MalformedURLException, URISyntaxException, IOException {
        String selectedMovieBrowser = tableview.getSelectionModel().getSelectedItem().getImdbbrowser();
        Desktop.getDesktop().browse(new URL(selectedMovieBrowser).toURI());
    }

    @FXML
    //Method that executes the refresh when the showall button movies is pressed
    private void clickShowAllMovies(ActionEvent event) {
        refresh();
    }

    @FXML
    //Method that gets the text from the ratinglbl when the SearchByRatingBtn is pressed and sets the tableview with the items that match the criteria in the database. this is achieved
    //by using the method getFilteredMoviesByIMDB inside the manager class
    private void clickSearchByRatingBtn(ActionEvent event) {
        String query = ratinglbl.getText();
        if (query != null) {
            movieLst = FXCollections.observableArrayList(manager.getFilteredMoviesByIMDB(query));
            tableview.setItems(movieLst);
        } else {
            refresh();
        }
    }

}
