package com.phoenix.howabouttoday.member.controller;


import com.phoenix.howabouttoday.accom.dto.SearchForm;
import com.phoenix.howabouttoday.config.auth.LoginUser;
import com.phoenix.howabouttoday.member.Service.MemberService;
import com.phoenix.howabouttoday.member.dto.MemberDTO;
import com.phoenix.howabouttoday.member.dto.SessionDTO;
import com.phoenix.howabouttoday.member.validator.CustomValidators;
import com.phoenix.howabouttoday.room.dto.MyReviewDTO;
import com.phoenix.howabouttoday.room.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final CustomValidators.EmailValidator EmailValidator;
    private final ReviewService reviewService;

    /* 커스텀 유효성 검증을 위해 추가 */
    @InitBinder
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(EmailValidator);

    }



    @GetMapping("/member/join")
    public String join() {
        return "/home";
    }



    @PostMapping("/member/join")
    public String joinProc(@Valid MemberDTO memberDTO, BindingResult result, Model model,
                           @RequestHeader("referer") String referer) {


        System.out.println("호출!!!!!!!!");
//        if (errors.hasErrors()) {
//            /* 회원가입 실패시 입력 데이터 값을 유지 */
//            model.addAttribute("memberDTO", memberDTO);
//
//
//
//
//            /* 유효성 통과 못한 필드와 메시지를 핸들링 */
//            Map<String, String> validatorResult = memberService.validateHandling(errors);
//            for (String key : validatorResult.keySet()) {
//                model.addAttribute(key, validatorResult.get(key));
//            }
//            /* 회원가입 페이지로 다시 리턴 */
//            return "redirect:/member/join";
//        }
        SearchForm searchForm = new SearchForm();
        model.addAttribute("searchForm",searchForm);
        if(result.hasErrors()){

            boolean memberCheck = true;
            model.addAttribute("memberCheck",memberCheck);
            model.addAttribute("memberDTO",memberDTO);

//            Map<String, String> validatorResult = memberService.validateHandling(errors);
//            for (String key : validatorResult.keySet()) {
//                model.addAttribute(key, validatorResult.get(key));

            return  "/home";
        }


        System.out.println("referer = " + referer);
        System.out.println("memberDto = " + memberDTO.toString());
        memberService.join(memberDTO);
        String url = referer.substring(21);
        System.out.println("url = " + url);

        return "redirect:/home";
    }

    @GetMapping("/loginProc")
    public String login(@RequestParam(value = "error", required = false)String error,
                        @RequestParam(value = "exception", required = false)String exception,
                        Model model) {

        model.addAttribute("error", error);
        model.addAttribute("exception", exception);


        MemberDTO memberDTO = new MemberDTO();
        model.addAttribute("memberDTO",memberDTO);

        boolean loginCheck = true;
        model.addAttribute("loginCheck",loginCheck);

        return "/home";

    }

    @GetMapping("/logout")
    public String logout() {
        return "member/logout";
    }

    /* 회원정보 수정 */
    @GetMapping("/modify")
    public String modify(@LoginUser SessionDTO sessionDTO, Model model) {
        if (sessionDTO != null) {
            model.addAttribute("member", sessionDTO.getNickname());
            model.addAttribute("member", sessionDTO.getMemberTel());
            model.addAttribute("sessionDTO", sessionDTO);
        }
        return "/user-dashboard-settings";
    }



    @GetMapping("recover")
    public String getRecover(){
        return "member/recover";
    }
    @PostMapping("recover")
    public String postRecover(){
        return "member/recover";
    }

    @GetMapping("user-dashboard")
    public String getUserDashboard(@LoginUser SessionDTO sessionDTO, Model model) {

        if(sessionDTO != null) {
            model.addAttribute("sessionDTO", sessionDTO);
        }

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


    @GetMapping("user-dashboard-profile")
    public String getUserDashboardProfile(@LoginUser SessionDTO sessionDTO, Model model) {

        if(sessionDTO != null) {
            model.addAttribute("sessionDTO", sessionDTO);
        }

        return "member/userdashboard/user-dashboard-profile";
    }
    @PostMapping("user-dashboard-profile")
    public String postUserDashboardProfile() {
        return "member/userdashboard/user-dashboard-profile";
    }





    //리뷰페이지 가기.
    @GetMapping("user-dashboard-reviews")
    public String getUserDashboardReviews(@LoginUser SessionDTO sessionDTO, Model model) {

        if(sessionDTO != null) {
            model.addAttribute("sessionDTO", sessionDTO);
        }

        List<MyReviewDTO> memberReivews = reviewService.getMemberReivew(sessionDTO.getMemberNum());

        model.addAttribute("memberReivews", memberReivews);

        return "member/userdashboard/user-dashboard-reviews";
    }






    @PostMapping("user-dashboard-reviews")
    public String postUserDashboardReviews() {
        return "member/userdashboard/user-dashboard-reviews";
    }

    @GetMapping("user-dashboard-settings")
    public String getUserDashboardSettings(@LoginUser SessionDTO sessionDTO, Model model) {

        if(sessionDTO != null) {
            model.addAttribute("sessionDTO", sessionDTO);
        }




        return "member/userdashboard/user-dashboard-settings";
    }
    @PostMapping("user-dashboard-settings")
    public String postUserDashboardSettings() {
        return "member/userdashboard/user-dashboard-settings";
    }


}
