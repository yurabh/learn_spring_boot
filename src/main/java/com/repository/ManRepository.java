package com.repository;

import com.domain.Man;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface ManRepository extends JpaRepository<Man, Integer> {

    List<Man> findAllByNameLike(String name);

    @Query("SELECT m FROM Man m WHERE m.name = :name AND m.age = :age")
    Man findByNameAndAge(@Param("name") String name, @Param("age") int age);

    @Query("SELECT m FROM Man m")
    List<Man> findByNameAndAgeAndSortByName(Sort sort);

    @Override
    @EntityGraph(attributePaths = {"emails", "phone"})
    List<Man> findAll();
}
