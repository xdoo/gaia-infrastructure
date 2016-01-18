package de.muenchen.gaia.auth.controller;

import de.muenchen.gaia.auth.dto.UserDto;
import de.muenchen.gaia.auth.entities.User;
import de.muenchen.gaia.auth.mapper.UserMapper;
import de.muenchen.gaia.auth.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

/**
 * Created by dennis_huning on 08.12.15.
 */
@Controller
public class UserRestController {

    @Autowired
    UserRepository userRepository;

    UserMapper userMapper = UserMapper.INSTANCE;

    /**
     * Aktiviert eine konfortable Möglichkeit den token in lesbare Userinformation umzuwandeln. Kann im ResourceServer über die Property "spring.oauth2.resource.userInfoUri" referenziert werden: spring.oauth2.resource.userInfoUri: http://localhost:9999/uaa/user
     */
    @RequestMapping("/user")
    @ResponseBody
    public UserDto user(Principal user) {
        final User savedUser = userRepository.findFirstByUsername(user.getName());
        return userMapper.userToUserDto(savedUser);
    }
}
