package com.Intelligent_Forms.Intelligent_Forms_FCR.Submission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, UUID> {
   List<Submission> findAllByForm_Id(UUID id);

}
