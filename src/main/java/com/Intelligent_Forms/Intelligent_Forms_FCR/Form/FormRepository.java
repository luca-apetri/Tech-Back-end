package com.Intelligent_Forms.Intelligent_Forms_FCR.Form;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public interface FormRepository extends JpaRepository<Form, UUID> {

    Form findFormById(UUID formID);
    List<Form> findAllByUser_Id(UUID id);

}

