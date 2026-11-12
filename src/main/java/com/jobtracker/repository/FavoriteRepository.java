package com.jobtracker.repository;

import com.jobtracker.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

/** Favorite 엔티티의 기본 CRUD 및 페이징 작업을 제공한다. */
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
}
