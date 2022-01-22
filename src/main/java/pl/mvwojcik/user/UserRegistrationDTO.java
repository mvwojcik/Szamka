package pl.mvwojcik.user;

import lombok.Getter;

@Getter
public class UserRegistrationDTO {
    private String username;

    private String password;

    private Double weight;

    private Integer height;

    private Integer dailyKcal;

    private Integer activityLevel;
}
