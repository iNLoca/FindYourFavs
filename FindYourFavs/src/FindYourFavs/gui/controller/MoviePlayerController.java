/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FindYourFavs.gui.controller;

import FindYourFavs.be.Category;
import FindYourFavs.be.Movie;
import FindYourFavs.bll.Manager;
import java.io.IOException;
import java.net.URL;
import java.util.EventObject;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


/**
 * FXML Controller class
 */
public class MoviePlayerController implements Initializable {

    Manager manager = new Manager();
    Category category;
    

    @FXML
    private Label label;
    @FXML
    private TableView<Movie> tableview;
    @FXML
    private Button addmovie;
    @FXML
    private Button deletemovie;
    @FXML
    private Button addcategory;
    @FXML
    private Button deletecategory;
    @FXML
    private Button addrating;
    @FXML
    private Button editrating;
    @FXML
    private TableColumn<Movie, String> movietittle;
    @FXML
    private TableColumn<Movie, Integer> usrrating;
    @FXML
    private TableColumn<Movie, Integer> imdbrating;
    @FXML
    private Label lblChosenCategory;
    @FXML
    private TableView<Category> categoriesView;
    @FXML
    private TableColumn<Category, String> categoriesColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refresh();
       
     /*   categoriesView.getSelectionModel().selectedItemProperty().addListener((observable, oldSelected, newSelected) -> {
            if (newSelected != null) {
                lblChosenCategory.setText(newSelected.getName());
            }
            refresh();
        });*/
    }
    

    @FXML
    private void clickaddmovie(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FindYourFavs/gui/view/AskAddMovie.fxml"));
        Parent root = loader.load();
        AskAddMovieController ctrl = loader.getController();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void clickdeletemovie(ActionEvent event) throws IOException {
        /*
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FindYourFavs/gui/view/AskDeleteMovie.fxml"));
        Parent root = loader.load();
        AskDeleteMovieController ctrl = loader.getController();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();*/
        deleteMovie();
        refresh();
    }

    @FXML
    private void clickaddcategory(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FindYourFavs/gui/view/AskAddCategory.fxml"));
        Parent root = loader.load();
        AskAddCategoryController ctrl = loader.getController();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void clickdeletecategory(ActionEvent event) throws IOException {
/*
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FindYourFavs/gui/view/AskDeleteCategory.fxml"));
        Parent root = loader.load();
        AskDeleteCategoryController ctrl = loader.getController();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();*/
        deleteCategory();
        refresh();
    }

    @FXML
    private void clickAddRating(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FindYourFavs/gui/view/AddRating.fxml"));
        Parent root = loader.load();
        AddRatingController ctrler = loader.getController();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void clickEditRating(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FindYourFavs/gui/view/EditRating.fxml"));
        Parent root = loader.load();
        EditRatingController ctrl = loader.getController();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public int getSelectedPersonalRating() {
        int rtng;

        rtng = 3;//tableview.getSelectionModel().getSelectedItem().getId();   
        return rtng;
    }

    private void deleteMovie() {
        //deletes a movie but only from the view
        tableview.getItems().remove(tableview.getSelectionModel().getSelectedItem());
        
        //deletes a movie from the database       
        manager.deleteMovieById(tableview.getSelectionModel().getSelectedItem().getId());

    }
    
    private void deleteCategory(){
        //deletes a category but only from the view
        categoriesView.getItems().remove(categoriesView.getSelectionModel().getSelectedItem());
        
        //deletes a category from the database       
        manager.deleteCategoryById(categoriesView.getSelectionModel().getSelectedItem().getId());
    }
    
    private void displayChosenCategory(){
   /*     if ((categoriesView.getSelectionModel().getSelectedItem()) != null){
            lblChosenCategory.setText(categoriesView.getSelectionModel().getSelectedItem().getName());
        }*/
    }
    
    public String returnNameOfMovie(){
        return tableview.getSelectionModel().getSelectedItem().getName();
    }

    private void refresh() {
        
        ObservableList<Movie> movieLst = FXCollections.observableArrayList(manager.getAllMovies());
        movietittle.setCellValueFactory(new PropertyValueFactory<>("name"));
        usrrating.setCellValueFactory(new PropertyValueFactory<>("personalRating"));
        imdbrating.setCellValueFactory(new PropertyValueFactory<>("IMDBRating"));
        tableview.setItems(movieLst);

        ObservableList<Category> categoryLst = FXCollections.observableArrayList(manager.getAllCategories());
        categoriesColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoriesView.setItems(categoryLst);     
        
        
        
        
    }
}
