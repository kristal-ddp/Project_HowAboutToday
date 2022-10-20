package com.phoenix.howabouttoday.member.wishlist;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepository extends JpaRepository<WishList,Long> {
}