package com.adidas.lld.paymentgateway.code.user;

public class UserController {
  UserService userService;
  public UserController(){
    /**
     * Obviously dependency can be injected but this way we just did because we did not want inject for the case of simplicity
     *
     *
     */
    userService = new UserService();
  }
  public UserDTO addUser(UserDTO user){
    return userService.addUser(user);
  }

  public UserDTO getUser(int userId){
    return userService.getUser(userId) ;
  }
}

