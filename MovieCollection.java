import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class MovieCollection
{
    private ArrayList<Movie> movies;
    private Scanner scanner;

    public MovieCollection(String fileName)
    {
        importMovieList(fileName);
        scanner = new Scanner(System.in);
    }

    public ArrayList<Movie> getMovies()
    {
        return movies;
    }

    public void menu()
    {
        String menuOption = "";

        System.out.println("Welcome to the movie collection!");
        System.out.println("Total: " + movies.size() + " movies");

        while (!menuOption.equals("q"))
        {
            System.out.println("------------ Main Menu ----------");
            System.out.println("- search (t)itles");
            System.out.println("- search (k)eywords");
            System.out.println("- search (c)ast");
            System.out.println("- see all movies of a (g)enre");
            System.out.println("- list top 50 (r)ated movies");
            System.out.println("- list top 50 (h)igest revenue movies");
            System.out.println("- (q)uit");
            System.out.print("Enter choice: ");
            menuOption = scanner.nextLine();

            if (!menuOption.equals("q"))
            {
                processOption(menuOption);
            }
        }
    }

    private void processOption(String option)
    {
        if (option.equals("t"))
        {
            searchTitles();
        }
        else if (option.equals("c"))
        {
            searchCast();
        }
        else if (option.equals("k"))
        {
            searchKeywords();
        }
        else if (option.equals("g"))
        {
            listGenres();
        }
        else if (option.equals("r"))
        {
            listHighestRated();
        }
        else if (option.equals("h"))
        {
            listHighestRevenue();
        }
        else
        {
            System.out.println("Invalid choice!");
        }
    }

    private void searchTitles()
    {
        System.out.print("Enter a title search term: ");
        String searchTerm = scanner.nextLine();

        // prevent case sensitivity
        searchTerm = searchTerm.toLowerCase();

        // arraylist to hold search results
        ArrayList<Movie> results = new ArrayList<Movie>();

        // search through ALL movies in collection
        for (int i = 0; i < movies.size(); i++)
        {
            String movieTitle = movies.get(i).getTitle();
            movieTitle = movieTitle.toLowerCase();

            if (movieTitle.indexOf(searchTerm) != -1)
            {
                //add the Movie objest to the results list
                results.add(movies.get(i));
            }
        }

        // sort the results by title
        sortResults(results);

        // now, display them all to the user
        for (int i = 0; i < results.size(); i++)
        {
            String title = results.get(i).getTitle();

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = results.get(choice - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void sort(ArrayList<String> words)
    {
        int checks =0;
        for (int j = 1; j < words.size(); j++)
        {
            String temp = words.get(j);
            int possibleIndex = j;
            while (possibleIndex > 0 && temp.compareTo(words.get(possibleIndex -1)) < 0)
            {
                checks++;
                words.set(possibleIndex, words.get(possibleIndex-1));

                possibleIndex--;
            }
            words.set(possibleIndex, temp);

        }
        System.out.println(checks);

    }

    private void sortResults(ArrayList<Movie> listToSort)
    {
        for (int j = 1; j < listToSort.size(); j++)
        {
            Movie temp = listToSort.get(j);
            String tempTitle = temp.getTitle();

            int possibleIndex = j;
            while (possibleIndex > 0 && tempTitle.compareTo(listToSort.get(possibleIndex - 1).getTitle()) < 0)
            {
                listToSort.set(possibleIndex, listToSort.get(possibleIndex - 1));
                possibleIndex--;
            }
            listToSort.set(possibleIndex, temp);
        }
    }

    private void displayMovieInfo(Movie movie)
    {
        System.out.println();
        System.out.println("Title: " + movie.getTitle());
        System.out.println("Tagline: " + movie.getTagline());
        System.out.println("Runtime: " + movie.getRuntime() + " minutes");
        System.out.println("Year: " + movie.getYear());
        System.out.println("Directed by: " + movie.getDirector());
        System.out.println("Cast: " + movie.getCast());
        System.out.println("Overview: " + movie.getOverview());
        System.out.println("User rating: " + movie.getUserRating());
        System.out.println("Box office revenue: " + movie.getRevenue());
    }

    private void searchCast() {
        System.out.print("Enter a cast search term: ");
        String searchTerm = scanner.nextLine();

        // prevent case sensitivity
        String h = searchTerm.toLowerCase();

        // arraylist to hold search results
        ArrayList<String> casts = new ArrayList<String>();

        // search through ALL movies in collection
        for (int i = 0; i < movies.size(); i++) {
            String casta = (movies.get(i).getCast());

            String[] cast = casta.split("\\|");

            for (int j = 0; j < cast.length; j++) {
                int count = 0;
                int idx = 0;

                while (count == 0 && idx < casts.size()) {
                    if (casts.get(idx).equals(cast[j])) {
                        count++;
                    }
                    idx++;
                }
                if (count == 0) {
                    casts.add(cast[j]);
                }
            }
        }
        ArrayList<String> temp = new ArrayList<String>();

        for (int i = 0; i < casts.size(); i++) {
            String y = (casts.get(i)).toLowerCase();
            if ((y.contains(h))) {
                temp.add(casts.get(i));
            }
        }
        sort(temp);

        for (int i = 0; i < temp.size(); i++) {
            System.out.println("" + i + ". " + temp.get(i));
        }

        System.out.println("Which actor/actress would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        String star = casts.get(choice - 1);
        ArrayList<Movie> s = new ArrayList<Movie>();
        for (int i = 0; i < movies.size(); i++) {
            String[] c = (movies.get(i).getCast()).split("\\|");
            for (int j = 0; j < c.length; j++) {
                if(c[j].equals(star))
                {
                    s.add(movies.get(i));
                }
            }
        }
        sortResults(s);
        for (int i = 0; i < s.size(); i++) {
            System.out.println("" + i + ". " + s.get(i).getTitle());
        }
        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choose = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = s.get(choose);
        displayMovieInfo(selectedMovie);
        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void searchKeywords()
    {
        System.out.print("Enter a keyword search term: ");
        String searchTerm = scanner.nextLine();

        // prevent case sensitivity
        searchTerm = searchTerm.toLowerCase();

        // arraylist to hold search results
        ArrayList<Movie> results = new ArrayList<Movie>();

        // search through ALL movies in collection
        for (int i = 0; i < movies.size(); i++)
        {
            String movieKeyword = movies.get(i).getKeywords();
            movieKeyword = movieKeyword.toLowerCase();

            if (movieKeyword.indexOf(searchTerm) != -1)
            {
                //add the Movie objest to the results list
                results.add(movies.get(i));
            }
        }

        // sort the results by title
        sortResults(results);

        // now, display them all to the user
        for (int i = 0; i < results.size(); i++)
        {
            String title = results.get(i).getTitle();

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = results.get(choice - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();

    }

    private void listGenres() {
        ArrayList<String> g = new ArrayList<String>();
        for (int i = 0; i < movies.size(); i++) {
            String[] genres = (movies.get(i).getGenres()).split("\\|");


            for (int j = 0; j < genres.length; j++) {
                int count = 0;
                int idx = 0;

                while (count == 0 && idx < g.size()) {
                    if (g.get(idx).equals(genres[j])) {
                        count++;
                    }
                    idx++;

                }
                if (count == 0) {
                    g.add(genres[j]);
                }
            }
        }
        sort(g);

        for (int i = 0; i < g.size(); i++) {

            System.out.println("" + i + ". " + g.get(i));
        }
        System.out.println("Which genre would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        String gen = g.get(choice);
        ArrayList<Movie> s = new ArrayList<Movie>();
        for (int i = 0; i < movies.size(); i++) {
            String[] c = (movies.get(i).getGenres()).split("\\|");
            for (int j = 0; j < c.length; j++) {
                if(c[j].equals(gen))
                {
                    s.add(movies.get(i));

                }

            }

        }
        sortResults(s);
        for (int i = 0; i < s.size(); i++) {

            System.out.println("" + i + ". " + s.get(i).getTitle());
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choose = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = s.get(choose);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();

    }

    private void listHighestRated()
    {
        for (int j = 0; j < movies.size() - 1; j++)
        {
            int minIndex = j;
            for (int k = j + 1; k < movies.size(); k++)
            {

                if (movies.get(k).getUserRating() > movies.get(minIndex).getUserRating())
                {
                    minIndex = k;
                }
            }
            Movie temp = movies.get(j);
            movies.set(j,movies.get(minIndex));
            movies.set(minIndex, temp);
        }

        for(int i = 0; i < 50; i++) {
            System.out.println(i + "." + movies.get(i).getTitle() + " " + movies.get(i).getUserRating());
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");
        int choose = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = movies.get(choose);
        displayMovieInfo(selectedMovie);
        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void listHighestRevenue()
    {
        for (int j = 0; j < movies.size() - 1; j++)
        {
            int minIndex = j;
            for (int k = j + 1; k < movies.size(); k++)
            {
                if (movies.get(k).getRevenue() > movies.get(minIndex).getRevenue())
                {
                    minIndex = k;
                }
            }
            Movie temp = movies.get(j);
            movies.set(j,movies.get(minIndex));
            movies.set(minIndex, temp);
        }
        for(int i = 0; i < 50; i++) {
            System.out.println(i + "." + movies.get(i).getTitle() + " " + movies.get(i).getRevenue());
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");
        int choose = scanner.nextInt();
        scanner.nextLine();
        Movie selectedMovie = movies.get(choose);
        displayMovieInfo(selectedMovie);
        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void importMovieList(String fileName)
    {
        try
        {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();

            movies = new ArrayList<Movie>();

            while ((line = bufferedReader.readLine()) != null)
            {
                String[] movieFromCSV = line.split(",");

                String title = movieFromCSV[0];
                String cast = movieFromCSV[1];
                String director = movieFromCSV[2];
                String tagline = movieFromCSV[3];
                String keywords = movieFromCSV[4];
                String overview = movieFromCSV[5];
                int runtime = Integer.parseInt(movieFromCSV[6]);
                String genres = movieFromCSV[7];
                double userRating = Double.parseDouble(movieFromCSV[8]);
                int year = Integer.parseInt(movieFromCSV[9]);
                int revenue = Integer.parseInt(movieFromCSV[10]);

                Movie nextMovie = new Movie(title, cast, director, tagline, keywords, overview, runtime, genres, userRating, year, revenue);
                movies.add(nextMovie);
            }
            bufferedReader.close();
        }
        catch(IOException exception)
        {
            // Print out the exception that occurred
            System.out.println("Unable to access " + exception.getMessage());
        }
    }
}


