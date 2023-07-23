package service.impl;

import dto.user.RegisterRequest;
import dto.user.UserDto;
import entity.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import repository.impl.UserRepository;
import service.UserService;

import javax.jws.WebService;
import java.util.List;
import java.util.stream.Collectors;

//@ApplicationScoped
@WebService(endpointInterface = "service.UserService")
public class UserServiceImpl implements UserService {

//    @Inject
    UserRepository userRepository = new UserRepository();

    public UserDto createUser(RegisterRequest request) {
        User user = new User(request.getEmail(), request.getPassword(),
                request.getName());
        request.getProfileImage().ifPresent(user::setProfileImage);
        UserDto userCreated = new UserDto();
        userCreated.setName(user.getName());
        userCreated.setProfileImage(user.getProfileImage());
        userRepository.save(user);
        return userCreated;
    }

    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("Could not find user with id " + userId));
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        return userDto;
    }

    public List<UserDto> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::convertToUserDto)
                .collect(Collectors.toList());
    }

    public UserDto updateUserById(Long userId, UserDto userDto) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("Could not find user with id " + userId));
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

    public void deleteUserById(Long userId) {
        if (!userRepository.existsById(userId))
            throw new RuntimeException("Could not find user with id " + userId);
        userRepository.deleteById(userId);
    }

    private UserDto convertToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setProfileImage(user.getProfileImage());
        return userDto;
    }
}
