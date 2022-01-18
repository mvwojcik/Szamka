package pl.mvwojcik.user;

public class UserMapper {
   public static UserDTO mapToDto(User user) {
        return new UserDTO.UserDTOBuilder()
                .username(user.getUsername())
                .dailyKcal(user.getDailyKcal())
                .height(user.getHeight())
                .weight(user.getWeight())
                .activityLevel(user.getActivityLevel()).build();
    }
}
