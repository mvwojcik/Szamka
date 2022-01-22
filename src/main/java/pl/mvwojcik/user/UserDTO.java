package pl.mvwojcik.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class UserDTO {
    private String username;

    private Double weight;

    private Integer height;

    private Integer dailyKcal;

    private Integer activityLevel;
}
