    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FindYourFavs.bll.util;

import FindYourFavs.be.Movie;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mac
 */

//This filter searches for name and category but we need it to search for specific IMDBRating which is an int dum dum dum...
public class SearchMovies {
    
    public static List<Movie>searchMovies(List<Movie>movieList,String filter){
      
        filter=filter.toLowerCase();
        List<Movie>filteredMovies = new ArrayList();
        
        for(Movie m: movieList){
         if(filter.length() <= m.getName().length() && filter.equals(m.getName().toLowerCase().substring(0, filter.length())))
            {
                filteredMovies.add(m);
            }
           else if(filter.length() <= m.getCategory().length() && filter.equals(m.getCategory().toLowerCase().substring(0, filter.length())))
            {
                filteredMovies.add(m);
            }
       
        }
        return filteredMovies;
    
    
    
        

    } 
}
