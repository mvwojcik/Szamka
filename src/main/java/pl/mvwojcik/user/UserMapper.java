package pl.mvwojcik.user;

import pl.mvwojcik.allergen.data.AllergenMapper;

import java.util.stream.Collectors;

public class UserMapper {
   public static UserDTO mapToDto(User user) {
        return new UserDTO.UserDTOBuilder()
                .username(user.getUsername())
                .dailyKcal(user.getDailyKcal())
                .height(user.getHeight())
                .weight(user.getWeight())
                .activityLevel(user.getActivityLevel())
                .allergens(user.getAllergens().stream().map(AllergenMapper::mapAllergenToAllergenDTO).collect(Collectors.toSet())).build();
    }
}
