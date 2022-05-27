package com.kindnessocean.shared.model.user;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtTokenDto implements Serializable {
    private String token;
    private Long tokenValidityInMs;
}
