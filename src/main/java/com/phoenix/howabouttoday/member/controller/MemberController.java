package com.phoenix.howabouttoday.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    @GetMapping("recover")
    public String getRecover(){
        return "member/recover";
    }
    @PostMapping("recover")
    public String postRecover(){
        return "member/recover";
    }

    @GetMapping("user-dashboard")
    public String getUserDashboard() {
        return "member/userdashboard/user-dashboard";
    }
    @PostMapping("user-dashboard")
    public String postUserDashboard() {
        return "member/userdashboard/user-dashboard";
    }
    private void addUsers(Model model) {
        List<String> users = Arrays.asList(new String("₩ 90,000"),
                new String("₩ 190,000"),
                new String("₩ 150,000"));

        model.addAttribute("users", users);
    }



//    @RestController
//    public class UserController {
//
//        private final UserRepository userRepository;
//
//        public UserController(UserRepository userRepository) {
//            this.userRepository = userRepository;
//        }
//
//        @GetMapping("/users")
//        public Page<User> getAllUsers() {
//            PageRequest pageRequest = PageRequest.of(0, 5);
//            return userRepository.findAll(pageRequest);
//        }
//
//        @PostConstruct
//        public void initializing() {
//            for (int i = 0; i < 100; i++) {
//                User user = User.builder()
//                        .username("User " + i)
//                        .address("Korea")
//                        .age(i)
//                        .build();
//                userRepository.save(user);
//            }
//        }
//    }

   @GetMapping("user-dashboard-booking")
    public String getUserDashboardBooking(Model model) {

        addUsers(model);
        return "member/userdashboard/user-dashboard-booking";
    }
    @PostMapping("user-dashboard-booking")
    public String postUserDashboardBooking() {
        return "member/userdashboard/user-dashboard-booking";
    }

    @GetMapping("user-dashboard-profile")
    public String getUserDashboardProfile() {
        return "member/userdashboard/user-dashboard-profile";
    }
    @PostMapping("user-dashboard-profile")
    public String postUserDashboardProfile() {
        return "member/userdashboard/user-dashboard-profile";
    }

    @GetMapping("user-dashboard-reviews")
    public String getUserDashboardReviews() {
        return "member/userdashboard/user-dashboard-reviews";
    }
    @PostMapping("user-dashboard-reviews")
    public String postUserDashboardReviews() {
        return "member/userdashboard/user-dashboard-reviews";
    }

    @GetMapping("user-dashboard-settings")
    public String getUserDashboardSettings() {
        return "member/userdashboard/user-dashboard-settings";
    }
    @PostMapping("user-dashboard-settings")
    public String postUserDashboardSettings() {
        return "member/userdashboard/user-dashboard-settings";
    }


}
