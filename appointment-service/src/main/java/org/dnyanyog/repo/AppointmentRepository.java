package org.dnyanyog.repo;

import java.util.List;
import org.dnyanyog.entity.Appointments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointments, Long> {
  List<Appointments> findByPatientId(String patientId);
}
