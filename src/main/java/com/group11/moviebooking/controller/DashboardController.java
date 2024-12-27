package com.group11.moviebooking.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.group11.moviebooking.Mapper.MappingDTOtoJSON;
import com.group11.moviebooking.entity.CustomerEntity;
import com.group11.moviebooking.entity.PromotionEntity;
import com.group11.moviebooking.model.CustomerDTO;
import com.group11.moviebooking.model.MovieDTO;
import com.group11.moviebooking.model.RevenueDTO;
import com.group11.moviebooking.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller
//@RestController
//@RequestMapping("/dashboard")
public class DashboardController {

    private final MovieService movieService;
    private final RevenueService revenueService;
    private final CustomerService customerService;
    private final PromotionService promotionService;
    private final RoomService roomService;
    private MappingDTOtoJSON map;
    private CustomerController customerController;

    @Autowired
    public DashboardController(MovieService movieService, RevenueService revenueService, CustomerService customerService, PromotionService promotionService, RoomService roomService) {
        this.movieService = movieService;
        this.revenueService = revenueService;
        this.customerService = customerService;
        this.promotionService = promotionService;
        this.roomService = roomService;
    }

    @GetMapping("/dashboard")
    public ModelAndView showDashboard() {
        ModelAndView modelAndView = new ModelAndView("/dashboard");
        HashMap<Object, Object> report = getTicketsSoldAndRevenue();
        ArrayList<MovieDTO> moviesMovieByLatestMovies = this.movieService.getLatestMovies();
        modelAndView.addObject("total_tickets_sold", report.get("total_tickets_sold"));
        System.out.println("1222222222222");
        modelAndView.addObject("total_revenue", report.get("total_revenue"));
        modelAndView.addObject("customers_size", this.customerService.getAllCustomers().size());
        modelAndView.addObject("NewMovies", moviesMovieByLatestMovies);
        List<RevenueDTO> chart = getRevenueForLast7Days();
        try {
            ObjectMapper mapper = new ObjectMapper();
            String chartJson = mapper.writeValueAsString(chart); // Chuyển đổi thành JSON
            modelAndView.addObject("chart", chartJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<MovieDTO> topSelling = getTopSellingMovies();
        try {
            ObjectMapper mapper = new ObjectMapper();
            String chartJson = mapper.writeValueAsString(topSelling); // Chuyển đổi thành JSON
            modelAndView.addObject("topselling", topSelling);
            System.out.println("chả= " + chartJson);
            System.out.println(topSelling);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<PromotionEntity> promotions = promotionService.getAllPromotions();
        modelAndView.addObject("promotions", promotions);
        return modelAndView;
    }

    @ModelAttribute("SignUp")
    public CustomerDTO signUpCustomer() {
        return new CustomerDTO();
    }

    @GetMapping("/create-User")
    public ModelAndView showCreateUser(Model model1) {
        CustomerDTO customer = new CustomerDTO();
        customer.setCustomer_name("Name");
        customer.setCustomer_email("Email");
        customer.setCustomer_password("Password");
        customer.setCustomer_phone("Phone");
        customer.setCustomer_date_of_birth("dd/MM/yyyy");
        customer.setCustomer_gender("Gender");
        customer.setCustomer_is_active((byte) 0);
        model1.addAttribute("Register", new CustomerDTO());
        model1.addAttribute("title", "Create User");
        model1.addAttribute("description", "input the infomation of customers");
        model1.addAttribute("action", "/create-User");
        model1.addAttribute("customer_placeholder", customer);
        model1.addAttribute("customer_value", new CustomerDTO());
        return new ModelAndView("/create_User").addObject(model1);
    }

    @GetMapping(value = "create_Movie")
    public ModelAndView showCreateMovie(Model model1) {

        MovieDTO movie_placeholder = new MovieDTO();
        movie_placeholder.setMovie_id(0);
        movie_placeholder.setMovie_title("Title of movie");
        movie_placeholder.setMovie_description("Description");
        movie_placeholder.setMovie_rating(0);
        movie_placeholder.setMovie_duration(0);
        movie_placeholder.setMovie_trailer_url("Trailer URL");
        movie_placeholder.setMovie_release_date("/dd/mm/yyyy");
        movie_placeholder.setMovie_created_at("/dd/mm/yyyy");
        movie_placeholder.setMovie_main_actor("Main actor");
        movie_placeholder.setMovie_director("Director");
        movie_placeholder.setMovie_studio("studio");
        movie_placeholder.setMovie_country("country");
        movie_placeholder.setMovie_genre("Genre");
        movie_placeholder.setMovie_for_age(0);
        movie_placeholder.setMovie_poster_url("Image URL");

        model1.addAttribute("Movie_data", new MovieDTO());
        model1.addAttribute("title", "Create Movie");
        model1.addAttribute("description", "input the infomation of movies");
        model1.addAttribute("action", "/create-Movie");
        model1.addAttribute("movie_placeholder", movie_placeholder);
        model1.addAttribute("movie_value", new MovieDTO());
        return new ModelAndView("/create_Movie").addObject(model1);
    }

    @PostMapping(value = "/create-Movie")
    public ModelAndView createMovie(@ModelAttribute("Movie_data") MovieDTO movie_data, Model model) {
        ModelAndView modelAndView = new ModelAndView("/create_Movie");
        if (movie_data.getMovie_title() == null ||
                movie_data.getMovie_director() == null ||
                movie_data.getMovie_genre() == null ||
                movie_data.getMovie_for_age() == 0 ||
                movie_data.getMovie_trailer_url() == null) {
            model.addAttribute("error", "error movie information missing");
            MovieDTO movie_placeholder = new MovieDTO();
            movie_placeholder.setMovie_id(0);
            movie_placeholder.setMovie_title("Title of movie");
            movie_placeholder.setMovie_description("Description");
            movie_placeholder.setMovie_rating(0);
            movie_placeholder.setMovie_duration(0);
            movie_placeholder.setMovie_trailer_url("Trailer URL");
            movie_placeholder.setMovie_release_date("/dd/mm/yyyy");
            movie_placeholder.setMovie_created_at("/dd/mm/yyyy");
            movie_placeholder.setMovie_main_actor("Main actor");
            movie_placeholder.setMovie_director("Director");
            movie_placeholder.setMovie_studio("studio");
            movie_placeholder.setMovie_country("country");
            movie_placeholder.setMovie_genre("Genre");
            movie_placeholder.setMovie_for_age(0);
            movie_placeholder.setMovie_poster_url("Image URL");

            model.addAttribute("Movie_data", new MovieDTO());
            model.addAttribute("title", "Create Movie");
            model.addAttribute("description", "input the infomation of movies");
            model.addAttribute("action", "/create-Movie");
            model.addAttribute("movie_placeholder", movie_placeholder);
            model.addAttribute("movie_value", new MovieDTO());
            System.out.println("ditmemay");
            return modelAndView;
        }
        System.out.println(this.movieService.add(movie_data));
        modelAndView.setViewName("tables-movie");
        List<MovieDTO> movies = this.movieService.getallMovies();
        modelAndView.addObject("moviesList", movies);
        modelAndView.addObject(movies);
        return modelAndView;
    }

    @PostMapping(value = "/create-User")
    public ModelAndView createUser(@ModelAttribute("Register") CustomerDTO user_SignUp, Model model) {
        List<CustomerEntity> users = this.customerService.getAllCustomers();
        ModelAndView modelAndView = new ModelAndView("/create_User");
        if (this.customerService.getCustomerByEmail(user_SignUp.getCustomer_email()) != null) {
            model.addAttribute("error", "Email already exists");
            model.addAttribute("Register", new CustomerDTO());
            model.addAttribute("title", "Create User");
            model.addAttribute("description", "input the infomation of customers");
            model.addAttribute("customer_placeholder", new CustomerDTO());
            model.addAttribute("customer_value", user_SignUp);
            return modelAndView;
        }
        CustomerEntity user_SignUpEntity = this.customerService.ConvertCustomerDTOtoCustomer(user_SignUp);
        System.out.println("sign up " + user_SignUpEntity);
        this.customerService.addCustomer(user_SignUpEntity);
        List<CustomerEntity> customers = this.customerService.getAllCustomers();
        System.out.println("customers: " + customers);
        model.addAttribute("customersList", customers);
        modelAndView.setViewName("tables-customer");
        modelAndView.addObject(customers);
        return modelAndView;
    }

    @GetMapping("/editCustomer/{id}")
    public ModelAndView editCustomer(@PathVariable("id") Long customerId, Model model) {
        CustomerEntity customer = this.customerService.getCustomerbyId(customerId);
        CustomerDTO customer_placeholder = new CustomerDTO();
        customer_placeholder.setCustomer_name("Name");
        customer_placeholder.setCustomer_email("Email");
        customer_placeholder.setCustomer_password("Password");
        customer_placeholder.setCustomer_phone("Phone");
        customer_placeholder.setCustomer_date_of_birth("/dd/mm/yyyy");
        customer_placeholder.setCustomer_gender("Gender");
        model.addAttribute("action", "/editCustomer");
        model.addAttribute("customer_value", customer);
        model.addAttribute("Register", new CustomerDTO());
        model.addAttribute("title", "Edit User");
        model.addAttribute("description", "input the new infomation of customers");
        model.addAttribute("customer_placeholder", customer_placeholder);
        ModelAndView modelAndView = new ModelAndView("/create_User");
        return modelAndView;
    }

    @GetMapping("/edit-movie/{id}")
    public ModelAndView editMovie(@PathVariable("id") int movieId, Model model) {
        MovieDTO movie = this.movieService.getMovieByMovieId(movieId);

        MovieDTO movie_placeholder = new MovieDTO();
        movie_placeholder.setMovie_id(0);
        movie_placeholder.setMovie_title("Title of movie");
        movie_placeholder.setMovie_description("Description");
        movie_placeholder.setMovie_rating(0);
        movie_placeholder.setMovie_duration(0);
        movie_placeholder.setMovie_trailer_url("Trailer URL");
        movie_placeholder.setMovie_release_date("/dd/mm/yyyy");
        movie_placeholder.setMovie_created_at("/dd/mm/yyyy");
        movie_placeholder.setMovie_main_actor("Main actor");
        movie_placeholder.setMovie_director("Director");
        movie_placeholder.setMovie_studio("studio");
        movie_placeholder.setMovie_country("country");
        movie_placeholder.setMovie_genre("Genre");
        movie_placeholder.setMovie_for_age(0);
        movie_placeholder.setMovie_poster_url("Image URL");

        model.addAttribute("action", "/edit_movie");

        model.addAttribute("movie_value", movie);
        model.addAttribute("Movie_data", movie);
        model.addAttribute("title", "Edit Movie");
        model.addAttribute("description", "input the new infomation of Movie");
        model.addAttribute("movie_placeholder", movie_placeholder);

        ModelAndView modelAndView = new ModelAndView("/edit_Movie");
        return modelAndView;
    }

    @PostMapping("/edit_movie")
    public String updateMovie(@ModelAttribute("Movie_data") MovieDTO movie_data) {
        System.out.println(movie_data);
        System.out.println(this.movieService.update(movie_data));
        return "redirect:/Movies";
        // Chuyển hướng lại trang danh sách khách hàng sau khi cập nhật
    }

    @GetMapping("/deleteMovie/{id}")
    public String deleteMovie(@PathVariable("id") int movieId, Model model) throws Exception {
        System.out.println(this.movieService.delete(movieId));
        return "redirect:/Movies"; // Chuyển hướng lại trang danh sách phim sau khi xóa
    }

    @PostMapping("/editCustomer")
    public String updateCustomer(@ModelAttribute("Register") CustomerDTO user_SignUp, Model model) {
        List<CustomerEntity> users = this.customerService.getAllCustomers();
        System.out.println(users);
        CustomerEntity user_SignUpEntity = this.customerService.ConvertCustomerDTOtoCustomer(user_SignUp);
        user_SignUpEntity.setCustomer_id(this.customerService.getCustomerByEmail(user_SignUp.getCustomer_email()).getCustomer_id());
        this.customerService.edit(user_SignUpEntity);
        List<CustomerEntity> customers = this.customerService.getAllCustomers();
        model.addAttribute("customersList", customers);
        return "redirect:/Customers";
        // Chuyển hướng lại trang danh sách khách hàng sau khi cập nhật
    }


    @GetMapping("/deleteCustomer/{id}")
    public String deleteCustomer(@PathVariable("id") Long customerId, Model model) throws Exception {
        this.customerService.updateCustomerStatus(customerId);
        List<CustomerEntity> customers = this.customerService.getAllCustomers();
        model.addAttribute("customersList", customers);
//        customerRepository.deleteById(customerId);
        return "redirect:/Customers"; // Chuyển hướng lại trang danh sách khách hàng sau khi xóa
    }

    @GetMapping(value = "/Movies")
    public String showMovies(Model model, @RequestParam(value = "page") Optional<String> pageOptional) {
        int page = 1;
        try {
            if (pageOptional.isPresent()) {
                page = Integer.parseInt(pageOptional.get());
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid page number!");
            page = 1;
        }
        List<MovieDTO> movies = this.movieService.getallMovies(page);
        model.addAttribute("moviesList", movies);
        return "tables-movie";
    }


    @ResponseBody
    public HashMap<Object, Object> getTicketsSoldAndRevenue() {
        return movieService.getTicketsSoldAndRevenue();
    }

    @ResponseBody
    public List<RevenueDTO> getRevenueForLast7Days() {
        return revenueService.findWeekRevenue();
    }

    @ResponseBody
    public ArrayList<MovieDTO> getTopSellingMovies() {
        return movieService.getTopSellingMovies();
    }

}
