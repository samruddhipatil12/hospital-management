package org.dnyanyog.repo;

import java.util.List;
import org.dnyanyog.entity.Patients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientsRepository extends JpaRepository<Patients, String> {
  // List<Patients> findByPatient_id(String patient_id);

  List<Patients> findByPatientId(String patientId);
}
