package org.dnyanyog.repo;

import java.util.List;
import org.dnyanyog.entity.Cases;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaseRepository extends JpaRepository<Cases, Long> {

  List<Cases> findByPatientId(Long patientId);
}
