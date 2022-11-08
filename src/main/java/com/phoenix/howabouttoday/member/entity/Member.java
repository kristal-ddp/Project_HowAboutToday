package com.phoenix.howabouttoday.member.entity;

import com.phoenix.howabouttoday.board.entity.Board;
import com.phoenix.howabouttoday.board.entity.Event;
import com.phoenix.howabouttoday.member.wishlist.domain.WishList;
import com.phoenix.howabouttoday.payment.entity.Coupon;
import com.phoenix.howabouttoday.payment.entity.Orders;
import com.phoenix.howabouttoday.reserve.domain.Reservation.Cart;
import com.phoenix.howabouttoday.reserve.domain.Reservation.Reservation;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long memberNum;

  /*로그인할 회원 아이디(이메일)*/
  @Column(name = "email", length = 50)
  private String email;

  @Column(name = "pwd", length = 100) //패스워드
  private String pwd;

  @Column(name= "nickname") //닉네임
  private String nickname;

  @Column(nullable = true)
  private String memberTel;

  @Enumerated(EnumType.STRING)
  private Role role;

  private LocalDate joinDate;
  private LocalDate withdrawdate;

  private String memberOriginalFileName;
  private String memberSaveFileName;

  //양방향 매핑을 위해 추가
  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
  private List<Orders> orders = new ArrayList<>();

  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
  private List<Board> boardList = new ArrayList<>();

  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
  private List<Coupon> coupons = new ArrayList<>();

  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
  private List<WishList> wishLists = new ArrayList<>();

  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
  private List<Cart> carts = new ArrayList<>();

  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
  private List<Reservation> reservations = new ArrayList<>();


  /* 전화번호와 password만 수정 가능 */
  public void modify(String pwd, String memberTel) {
    this.pwd = pwd;
    this.memberTel = memberTel;
  }

  @Builder
  public Member(String email, String pwd, String nickname, String memberTel, Role role, LocalDate joinDate, LocalDate withdrawdate, String memberOriginalFileName, String memberSaveFileName) {
    this.email = email;
    this.pwd = pwd;
    this.nickname = nickname;
    this.memberTel = memberTel;
    this.role = role;
    this.joinDate = joinDate;
    this.withdrawdate = withdrawdate;
    this.memberOriginalFileName = memberOriginalFileName;
    this.memberSaveFileName = memberSaveFileName;
  }
  //회원정보 수정
  public void modify(String pwd, String nickname, String memberTel ) {
    this.pwd = pwd;
    this.nickname = nickname;
    this.memberTel = memberTel;

  }

  //비밀번호 찾기
  public void findPwd(String pwd) {
    this.pwd = pwd;
  }

  public void addCoupon(Coupon coupon){
    this.coupons.add(coupon);
  }


  // 회원 탈퇴
  public void withdraw(Role role) {
    this.role = role;
  }

}

