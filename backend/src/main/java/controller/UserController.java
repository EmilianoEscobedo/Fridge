package controller;

import dto.user.RegisterRequest;
import dto.user.UserDto;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.List;

@WebService
public class UserController{

    UserService userService = new UserServiceImpl();

    @WebMethod(operationName = "createUser")
    @WebResult(name = "createUserResult")
    public UserDto createUser(@WebParam(name="request") RegisterRequest request) {
        return userService.createUser(request);
    }

    @WebMethod(operationName = "getUser")
    @WebResult (name = "getUserResult")
    public UserDto getUserById(@WebParam(name="userId") Long userId) {
        return userService.getUserById(userId);
    }

    @WebMethod(operationName = "getUsers")
    @WebResult (name = "getUsersResult")
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    @WebMethod(operationName = "updateUser")
    @WebResult (name = "updateUserResult")
    public UserDto updateUserById(@WebParam(name="userId") Long userId,
                                  @WebParam(name="userDto") UserDto userDto) {
        return userService.updateUserById(userId, userDto);
    }

    @WebMethod(operationName = "deleteUser")
    @WebResult (name = "deleteUserResult")
    public void deleteUserById(@WebParam(name="userId") Long userId) {
        userService.deleteUserById(userId);
    }
}
