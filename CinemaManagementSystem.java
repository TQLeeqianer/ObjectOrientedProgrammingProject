package com.cinema.management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Movie implements Serializable {
    /**
     * int id
     */
    private int id;
    /**
     * string title
     */
    private String title;
    /**
     * string genre
     */
    private String genre;
    /**
     * int duration
     */
    private int duration;
    /**
     * string language
     */
    private String language;
    /**
     * string rating
     */
    private String rating;

    /**
     * @param id
     * int id
     * @param title
     * String title
     * @param genre
     * String genre
     * @param duration
     * int duration
     * @param language
     * String language
     * @param rating
     * String rating
     */
    public Movie(int id, String title, String genre, int duration, String language, String rating) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.language = language;
        this.rating = rating;
    }

    /**
     * @return ID
     */
    public int getId() {
        return id;
    }

    /**
     * @return Title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * @return duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * @return language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @return rating
     */
    public String getRating() {
        return rating;
    }

    /**
     * @return id,title,genre,duration,language,rating
     */
    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", duration=" + duration +
                ", language='" + language + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }
}

class Screen implements Serializable {
    /**
     * int screenNumber
     */
    private int screenNumber;
    /**
     * int capacity
     */
    private int capacity;
    /**
     * boolean[] seats
     */
    private boolean[] seats; // true if the seat is booked, false if available

    /**
     * @param screenNumber
     * int screenNumber
     * @param capacity
     * int capacity
     */
    public Screen(int screenNumber, int capacity) {
        this.screenNumber = screenNumber;
        this.capacity = capacity;
        this.seats = new boolean[capacity];
    }

    /**
     * @return screenNumber
     */
    public int getScreenNumber() {
        return screenNumber;
    }

    /**
     * @return capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * @param seatNumber
     * int seatNumber
     * @return seatNumber
     */
    public boolean isSeatAvailable(int seatNumber) {
        return seatNumber > 0 && seatNumber <= capacity && !seats[seatNumber - 1];
    }

    /**
     * @param seatNumber
     * int seatNumber
     */
    public void bookSeat(int seatNumber) {
        if (isSeatAvailable(seatNumber)) {
            seats[seatNumber - 1] = true;
        }
    }

    /**
     * @return seats
     */
    public boolean[] getSeats() {
        return seats;
    }

    /**
     * @return screenNumber,capacity
     */
    @Override
    public String toString() {
        return "Screen{" +
                "screenNumber=" + screenNumber +
                ", capacity=" + capacity +
                '}';
    }
}

class Show implements Serializable {
    /**
     * int id
     */
    private int id;
    /**
     * Movie movie
     */
    private Movie movie;
    /**
     * Screen screen
     */
    private Screen screen;
    /**
     * String showTime
     */
    private String showTime;

    /**
     * @param id
     * int id
     * @param movie
     * Movie movie
     * @param screen
     * Screen screen
     * @param showTime
     * String showTime
     */
    public Show(int id, Movie movie, Screen screen, String showTime) {
        this.id = id;
        this.movie = movie;
        this.screen = screen;
        this.showTime = showTime;
    }

    /**
     * @return ID
     */
    public int getId() {
        return id;
    }

    /**
     * @return movie
     */
    public Movie getMovie() {
        return movie;
    }

    /**
     * @return screen
     */
    public Screen getScreen() {
        return screen;
    }

    /**
     * @return showTime
     */
    public String getShowTime() {
        return showTime;
    }

    /**
     * @return ID,movie,screen,showTime
     */
    @Override
    public String toString() {
        return "Show{" +
                "id=" + id +
                ", movie=" + movie +
                ", screen=" + screen +
                ", showTime='" + showTime + '\'' +
                '}';
    }
}

class Booking implements Serializable {
    /**
     * int id
     */
    private int id;
    /**
     * Show show
     */
    private Show show;
    /**
     * int seatNumber
     */
    private int seatNumber;
    /**
     * String customerName
     */
    private String customerName;

    /**
     * @param id
     * int id
     * @param show
     * Show show
     * @param seatNumber
     * int seatNumber
     * @param customerName
     * String customerName
     */
    public Booking(int id, Show show, int seatNumber, String customerName) {
        this.id = id;
        this.show = show;
        this.seatNumber = seatNumber;
        this.customerName = customerName;
    }

    /**
     * @return ID
     */
    public int getId() {
        return id;
    }

    /**
     * @return show
     */
    public Show getShow() {
        return show;
    }

    /**
     * @return seatNumber
     */
    public int getSeatNumber() {
        return seatNumber;
    }

    /**
     * @return customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @return ID,show,seatNumber,customerName
     */
    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", show=" + show +
                ", seatNumber=" + seatNumber +
                ", customerName='" + customerName + '\'' +
                '}';
    }
}

/**
 * CinemaManagementSystem
 */
public class CinemaManagementSystem {
    /**
     * List<Movie> movies
     */
    private List<Movie> movies;
    /**
     * List<Screen> screens
     */
    private List<Screen> screens;
    /**
     * List<Show> shows
     */
    private List<Show> shows;
    /**
     * List<Booking> bookings
     */
    private List<Booking> bookings;
    /**
     * Scanner scanner
     */
    private Scanner scanner;
    /**
     * Connection connection
     */
    private Connection connection;

    /**
     * new movies,new screens,new shows, new bookings,new scanner
     */
    public CinemaManagementSystem() {
        movies = new ArrayList<>();
        screens = new ArrayList<>();
        shows = new ArrayList<>();
        bookings = new ArrayList<>();
        scanner = new Scanner(System.in);

        // Initialize database connection
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:cinema.db");
            initializeDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @throws SQLException
     */
    private void initializeDatabase() throws SQLException {
        Statement statement = connection.createStatement();
        String movieTable = "CREATE TABLE IF NOT EXISTS movies (id INTEGER PRIMARY KEY, title TEXT, genre TEXT, duration INTEGER, language TEXT, rating TEXT)";
        String screenTable = "CREATE TABLE IF NOT EXISTS screens (screenNumber INTEGER PRIMARY KEY, capacity INTEGER)";
        String showTable = "CREATE TABLE IF NOT EXISTS shows (id INTEGER PRIMARY KEY, movieId INTEGER, screenNumber INTEGER, showTime TEXT)";
        String bookingTable = "CREATE TABLE IF NOT EXISTS bookings (id INTEGER PRIMARY KEY, showId INTEGER, seatNumber INTEGER, customerName TEXT)";
        statement.execute(movieTable);
        statement.execute(screenTable);
        statement.execute(showTable);
        statement.execute(bookingTable);
    }

    /**
     * @param movie
     * add movie
     */
    public void addMovie(Movie movie) {
        movies.add(movie);
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO movies VALUES (?, ?, ?, ?, ?, ?)");
            statement.setInt(1, movie.getId());
            statement.setString(2, movie.getTitle());
            statement.setString(3, movie.getGenre());
            statement.setInt(4, movie.getDuration());
            statement.setString(5, movie.getLanguage());
            statement.setString(6, movie.getRating());
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Movie Added Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param screen
     * add screen
     */
    public void addScreen(Screen screen) {
        screens.add(screen);
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO screens VALUES (?, ?)");
            statement.setInt(1, screen.getScreenNumber());
            statement.setInt(2, screen.getCapacity());
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Screen Added Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param show
     * add show
     */
    public void addShow(Show show) {
        shows.add(show);
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO shows VALUES (?, ?, ?, ?)");
            statement.setInt(1, show.getId());
            statement.setInt(2, show.getMovie().getId());
            statement.setInt(3, show.getScreen().getScreenNumber());
            statement.setString(4, show.getShowTime());
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Show Added Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param booking
     * add booking
     */
    public void bookTicket(Booking booking) {
        bookings.add(booking);
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO bookings VALUES (?, ?, ?, ?)");
            statement.setInt(1, booking.getId());
            statement.setInt(2, booking.getShow().getId());
            statement.setInt(3, booking.getSeatNumber());
            statement.setString(4, booking.getCustomerName());
            statement.executeUpdate();
            booking.getShow().getScreen().bookSeat(booking.getSeatNumber()); // Update seat status
            JOptionPane.showMessageDialog(null, "Seat Booked Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * view available tickets
     */
    public void viewAvailableMovies() {
        StringBuilder movieList = new StringBuilder();
        for (Movie movie : movies) {
            movieList.append(movie.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, movieList.toString());
    }

    /**
     * view shows
     */
    public void viewShows() {
        StringBuilder showList = new StringBuilder();
        for (Show show : shows) {
            showList.append(show.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, showList.toString());
    }

    /**
     * view booking
     */
    public void viewBookings() {
        StringBuilder bookingList = new StringBuilder();
        for (Booking booking : bookings) {
            bookingList.append(booking.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, bookingList.toString());
    }

    /**
     * @param showId
     * int showId
     */
    public void showAvailableSeats(int showId) {
        Show show = findShowById(showId);
        if (show != null) {
            boolean[] seats = show.getScreen().getSeats();
            StringBuilder availableSeats = new StringBuilder("Available Seats:\n");
            for (int i = 0; i < seats.length; i++) {
                if (!seats[i]) {
                    availableSeats.append("Seat ").append(i + 1).append("\n");
                }
            }
            JTextArea textArea = new JTextArea(availableSeats.toString());
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(250, 150));
            JOptionPane.showMessageDialog(null, scrollPane, "Available Seats", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid Show ID.");
        }
    }

    /**
     * @param id
     * int id
     * @return movie
     */
    public Movie findMovieById(int id) {
        for (Movie movie : movies) {
            if (movie.getId() == id) {
                return movie;
            }
        }
        return null;
    }

    /**
     * @param screenNumber
     * int screenNumber
     * @return screen
     */
    public Screen findScreenByNumber(int screenNumber) {
        for (Screen screen : screens) {
            if (screen.getScreenNumber() == screenNumber) {
                return screen;
            }
        }
        return null;
    }

    /**
     * @param id
     * int id
     * @return show
     */
    public Show findShowById(int id) {
        for (Show show : shows) {
            if (show.getId() == id) {
                return show;
            }
        }
        return null;
    }

    /**
     * sava data to file
     */
    public void saveDataToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("cinema_data.dat"))) {
            oos.writeObject(movies);
            oos.writeObject(screens);
            oos.writeObject(shows);
            oos.writeObject(bookings);
            System.out.println("Data saved to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * load data to file
     */
    @SuppressWarnings("unchecked")
    public void loadDataFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("cinema_data.dat"))) {
            movies = (List<Movie>) ois.readObject();
            screens = (List<Screen>) ois.readObject();
            shows = (List<Show>) ois.readObject();
            bookings = (List<Booking>) ois.readObject();
            System.out.println("Data loaded from file.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * show GUI
     */
    public void showGUI() {
        JFrame frame = new JFrame("Cinema Management System");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(5, 2));

        JButton addMovieButton = new JButton("Add Movie");
        JButton viewMoviesButton = new JButton("View Movies");
        JButton addScreenButton = new JButton("Add Screen");
        JButton addShowButton = new JButton("Add Show");
        JButton viewShowsButton = new JButton("View Shows");
        JButton bookSeatButton = new JButton("Book Seat");
        JButton showSeatsButton = new JButton("Show Available Seats");

        addMovieButton.addActionListener(new ActionListener() {
            /**
             * @param e
             * new ActionListener()
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = JOptionPane.showInputDialog("Enter Movie Title:");
                String genre = JOptionPane.showInputDialog("Enter Movie Genre:");
                int duration = Integer.parseInt(JOptionPane.showInputDialog("Enter Movie Duration (in minutes):"));
                String language = JOptionPane.showInputDialog("Enter Movie Language:");
                String rating = JOptionPane.showInputDialog("Enter Movie Rating:");
                Movie movie = new Movie(movies.size() + 1, title, genre, duration, language, rating);
                addMovie(movie);
            }
        });

        viewMoviesButton.addActionListener(new ActionListener() {
            /**
             * @param e
             * viewAvailableMovies()
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                viewAvailableMovies();
            }
        });

        addScreenButton.addActionListener(new ActionListener() {
            /**
             * @param e
             * addScreenButton
             *
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                int screenNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter Screen Number:"));
                int capacity = Integer.parseInt(JOptionPane.showInputDialog("Enter Screen Capacity:"));
                Screen screen = new Screen(screenNumber, capacity);
                addScreen(screen);
            }
        });

        addShowButton.addActionListener(new ActionListener() {
            /**
             * @param e
             * actionPerformed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                int movieId = Integer.parseInt(JOptionPane.showInputDialog("Enter Movie ID:"));
                int screenNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter Screen Number:"));
                String showTime = JOptionPane.showInputDialog("Enter Show Time (e.g., 18:00):");

                Movie movie = findMovieById(movieId);
                Screen screen = findScreenByNumber(screenNumber);
                if (movie != null && screen != null) {
                    Show show = new Show(shows.size() + 1, movie, screen, showTime);
                    addShow(show);
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid Movie ID or Screen Number");
                }
            }
        });

        viewShowsButton.addActionListener(new ActionListener() {
            /**
             * @param e
             * viewShows
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                viewShows();
            }
        });

        bookSeatButton.addActionListener(new ActionListener() {
            /**
             * @param e
             * actionPerformed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                int showId = Integer.parseInt(JOptionPane.showInputDialog("Enter Show ID:"));
                int seatNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter Seat Number:"));
                String customerName = JOptionPane.showInputDialog("Enter Customer Name:");

                Show show = findShowById(showId);
                if (show != null && show.getScreen().isSeatAvailable(seatNumber)) {
                    Booking booking = new Booking(bookings.size() + 1, show, seatNumber, customerName);
                    bookTicket(booking);
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid Show ID or Seat Number");
                }
            }
        });

        showSeatsButton.addActionListener(new ActionListener() {
            /**
             * @param e
             * showSeatsButton
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                int showId = Integer.parseInt(JOptionPane.showInputDialog("Enter Show ID:"));
                showAvailableSeats(showId);
            }
        });

        frame.add(addMovieButton);
        frame.add(viewMoviesButton);
        frame.add(addScreenButton);
        frame.add(addShowButton);
        frame.add(viewShowsButton);
        frame.add(bookSeatButton);
        frame.add(showSeatsButton);
        frame.setVisible(true);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        CinemaManagementSystem system = new CinemaManagementSystem();
        system.loadDataFromFile();
        system.showGUI();
        system.saveDataToFile();
    }
}
