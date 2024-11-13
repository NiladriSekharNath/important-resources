package com.adidas.lld.paymentgateway.code.user;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserService {
  static List<User> userList = new ArrayList<>();

  public UserDTO addUser(UserDTO userDTO){

    User user = new User();
    user.setUserName(userDTO.getUserName());
    user.setEmail(userDTO.getMail());
    user.setUserId(UUID. randomUUID().toString());
    userList.add(user);
    return convertDTOToUser(user);

  }

  public UserDTO getUser(int userId){
    for(User user : userList){
      if(user.getUserId().equals(userId)) {
        return convertDTOToUser(user);
      }
    }
    return null;
  }

  private UserDTO convertDTOToUser(User user) {
    UserDTO userDTO = new UserDTO();
    userDTO.setUserName(user.getUserName());
    userDTO.setMail(user.getEmail());
    userDTO.setUserId(user.getUserId());
    return userDTO;
  }
}
