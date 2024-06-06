package org.dnyanyog.repo;

import java.util.List;
import java.util.Optional;
import org.dnyanyog.entity.Appointments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointments, Long> {
  List<Appointments> findByPatientId(String patientId);

  Optional<Appointments> findByAppointmentId(String appointmentId);
}
