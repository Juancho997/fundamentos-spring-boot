package com.fundamentos.springboot.fundamentos.repository;

import com.fundamentos.springboot.fundamentos.dto.UserDto;
import com.fundamentos.springboot.fundamentos.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

// JpaRepository ≥ a través de Herencia, podemos acceder a métodos predefinidos que nos permitirán accionar contra la BD
// JpaRepository<Entidad a mapear, el tipo de dato del Id de la entidad>
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    // armamos una query, pero sobre OBJETOS
    // u ≥ el usuario que se está evaluando actualmente, simíl bucle for
    // u.email = findUserByEmail(email)
    // u.name = findUserByName(name)...
    @Query("Select u from User u WHERE u.email=?1")
    Optional<User> findByUserEmail(String email);

    @Query("Select u from User u WHERE u.name like ?1%")
    List<User> findAndSort(String name, Sort sort);

    //Query methods
    List<User> findByName(String name);

    Optional<User> findByEmailAndName(String email, String name);

    List<User> findByNameLike(String name);

    List<User> findByNameOrEmail(String name, String email);

    List<User> findByBirthDateBetween(LocalDate start, LocalDate end);

    List<User> findByNameLikeOrderByIdDesc(String name);

    List<User> findByNameContainingOrderByIdDesc(String name);

//    Usamos un DTO
    @Query("SELECT new com.fundamentos.springboot.fundamentos.dto.UserDto(u.id, u.name, u.birthDate)" +
        " FROM User u " +
            " WHERE u.birthDate = :birthDateParam " +
            " AND u.email = :emailParam"
    )

    Optional<UserDto> getAllByBirthDateAndEmail(
            @Param("birthDateParam") LocalDate birthDate,
            @Param("emailParam") String email);



    List<User> findAll();

}