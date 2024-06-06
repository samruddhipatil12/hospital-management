package org.dnyanyog.repo;

import java.util.List;
import java.util.Optional;
import org.dnyanyog.entity.Cases;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaseRepository extends JpaRepository<Cases, String> {
  Optional<Cases> findByCaseId(String caseId);

  List<Cases> findByPatientId(String patientId);
}
