package com.fundamentos.springboot.fundamentos.repository;

import com.fundamentos.springboot.fundamentos.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// JpaRepository ≥ a través de Herencia, podemos acceder a métodos predefinidos que nos permitirán accionar contra la BD
// JpaRepository<Entidad a mapear, el tipo de dato del Id de la entidad>
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
