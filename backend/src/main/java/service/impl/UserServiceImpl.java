package service.impl;

import dto.RegisterRequest;
import dto.UserDto;
import entity.User;
import repository.impl.UserRepository;
import service.UserService;

public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Override
    public UserDto createUser(RegisterRequest request) {
        User user = new User(request.getEmail(), request.getPassword(),
                request.getName());
        request.getProfileImage().ifPresent(user::setProfileImage);
        UserDto userCreated = new UserDto();
        userCreated.setName(user.getName());
        userCreated.setProfileImage(user.getProfileImage());
        return userCreated;
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Could not find user with id " + id));
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        return userDto;
    }

    @Override
    public UserDto updateUserById(Long id, UserDto userDto) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Could not find user with id " + id));
        UserDto updatedUser = new UserDto();
        if (!userDto.getName().isEmpty()){
            user.setName(userDto.getName());
            updatedUser.setName(userDto.getName());
        }
        if(!userDto.getProfileImage().isEmpty()){
            user.setProfileImage(userDto.getProfileImage());
            updatedUser.setProfileImage(userDto.getProfileImage());
        }
        return updatedUser;
    }

    @Override
    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id))
            throw new RuntimeException("Could not find user with id " + id);
        userRepository.deleteById(id);
    }
}
