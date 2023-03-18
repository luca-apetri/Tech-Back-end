package com.Intelligent_Forms.Intelligent_Forms_FCR.field;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FieldRepository extends JpaRepository<DynamicFields, UUID> {
    List<DynamicFields> findAllByForm_Id(UUID id);
}
