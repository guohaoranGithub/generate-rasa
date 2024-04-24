package com.miplus.generaterasa.config;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SessionConfig {
    //todo 暂时写死
    private Integer sessionExpirationTime = 60;
    private Boolean carryOverSlotsToNewSession = true;
}
