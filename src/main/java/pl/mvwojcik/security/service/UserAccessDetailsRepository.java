package pl.mvwojcik.security.service;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mvwojcik.security.model.UserAccessDetails;

import java.util.Optional;

public interface UserAccessDetailsRepository extends JpaRepository<UserAccessDetails,Long> {

    Optional<UserAccessDetails> findByUsername(String username);
}
