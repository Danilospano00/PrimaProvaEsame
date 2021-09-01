package org.esame.utils;

import org.esame.models.User;
import org.esame.models.dtos.UserDTO;

public class UserTransformer{

    public static User fromDTO(UserDTO dto) {
        return new User(dto.getFirstName(), dto.getLastName(), dto.getUsername(), dto.getPassword(), dto.getEmail());
    }

    public static UserDTO toDTO(User model, boolean pushPassword) {
        UserDTO result = new UserDTO(model.getFirstName(), model.getLastName(), model.getUsername(), model.getEmail());
        if (pushPassword)
            result.setPassword(model.getPassword());
        return result;
    }
}