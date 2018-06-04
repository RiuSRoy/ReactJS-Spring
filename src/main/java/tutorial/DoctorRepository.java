package tutorial;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;

//@PreAuthorize("hasRole('ROLE_MANAGER')")
public interface DoctorRepository extends MongoRepository<Doctor,String> {
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    void delete(Doctor doctor);
}

