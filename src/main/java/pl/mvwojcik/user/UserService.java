package pl.mvwojcik.user;

import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pl.mvwojcik.comunication.ContentResponse;
import pl.mvwojcik.comunication.ServiceResponse;
import pl.mvwojcik.error.ErrorConstants;
import pl.mvwojcik.error.ErrorResponse;
import pl.mvwojcik.security.model.UserAccessDetails;
import pl.mvwojcik.security.service.MyUserDetailsService;

import javax.transaction.Transactional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UsersRepository usersRepository;
    private final MyUserDetailsService userDetailsService;

    public ServiceResponse getUser(String usernameToFind, Authentication authentication) {
        return Option.ofOptional(usersRepository.findByUsername(usernameToFind))
                .toEither(ErrorConstants.userNotFound(usernameToFind))
                .flatMap(u -> UserValidation.matchingUsernameValidation(u, authentication))
                .map(UserMapper::mapToDto)
                .map(user -> new ContentResponse(HttpStatus.OK, user))
                .fold(Function.identity(), Function.identity());
    }

    public ServiceResponse registerUser(UserRegistrationDTO userRegistrationDTO) {
        return UserValidation.validateUserRegistration(userRegistrationDTO)
                .toEither()
                .flatMap(this::checkIfUserExists)
                .flatMap(this::createUser)
                .map(this::prepareResponse)
                .fold(Function.identity(), Function.identity());
    }

    private ContentResponse<UserDTO> prepareResponse(UserDTO userDTO) {
        return new ContentResponse<>(HttpStatus.CREATED, userDTO);
    }

    @Transactional
    public Either<ErrorResponse, UserDTO> createUser(UserRegistrationDTO userRegistrationDTO) {
        User user = usersRepository.save(createApplicationUser(userRegistrationDTO));
        UserAccessDetails userAccessDetails = userDetailsService.save(userRegistrationDTO);
        return (user.getId() != null && userAccessDetails.getId() != null) ?
                Either.right(UserMapper.mapToDto(user)) :
                Either.left(ErrorConstants.userNotCreated(userRegistrationDTO.getUsername()));
    }

    public User createApplicationUser(UserRegistrationDTO userRegistrationDTO) {
        return User.builder().username(userRegistrationDTO.getUsername())
                .activityLevel(userRegistrationDTO.getActivityLevel())
                .dailyKcal(userRegistrationDTO.getDailyKcal())
                .height(userRegistrationDTO.getHeight())
                .weight(userRegistrationDTO.getWeight())
                .build();
    }


    public Either<ErrorResponse, UserRegistrationDTO> checkIfUserExists(UserRegistrationDTO userRegistrationDTO) {
        return usersRepository.existsByUsername(userRegistrationDTO.getUsername()) ?
                Either.left(ErrorConstants.userExists(userRegistrationDTO.getUsername())) :
                Either.right(userRegistrationDTO);
    }

}
