package service;

import dto.user.RegisterRequest;
import dto.user.UserDto;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface UserService {
    @WebMethod(operationName = "createUser")
    @WebResult(name = "createUserResult")
    UserDto createUser(@WebParam(name="request") RegisterRequest request);

    @WebMethod(operationName = "getUser")
    @WebResult (name = "getUserResult")
    UserDto getUserById (@WebParam(name="userId") Long userId);

    @WebMethod(operationName = "getUsers")
    @WebResult (name = "getUsersResult")
    List<UserDto> getUsers();

    @WebMethod(operationName = "updateUser")
    @WebResult (name = "updateUserResult")
    UserDto updateUserById (@WebParam(name="userId") Long userId,
                            @WebParam(name="userDto") UserDto userDto);

    @WebMethod(operationName = "deleteUser")
    @WebResult (name = "deleteUserResult")
    void deleteUserById (@WebParam(name="userId") Long userId);
}


