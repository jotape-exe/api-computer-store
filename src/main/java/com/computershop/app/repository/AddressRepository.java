package com.computershop.app.repository;

import com.computershop.app.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    //@Query(value = "SELECT * FROM ADDRESS WHERE CEP = ?1", nativeQuery = true)
    Optional<Address> findByCep(String cep);
}
