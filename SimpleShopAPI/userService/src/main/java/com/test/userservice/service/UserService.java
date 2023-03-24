package com.test.userservice.service;

import com.test.userservice.dto.UserRegistrationRequestDTO;
import com.test.userservice.dto.UserRequestDTO;
import com.test.userservice.dto.UserResponseDTO;
import com.test.userservice.entity.User;
import com.test.userservice.exception.NotFoundException;
import com.test.userservice.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public UserResponseDTO findUserById(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Unable to find user by id :: " + id));
        UserResponseDTO userResponse = modelMapper.map(user,UserResponseDTO.class);
        return userResponse;
    }
    public List<UserResponseDTO> findAllUsers(){
        List<User> userList = userRepository.findAll();
        List<UserResponseDTO> responseUserList = userList.stream()
                .map(it -> modelMapper.map(it, UserResponseDTO.class))
                .toList();
        return responseUserList;
    }
    public UserResponseDTO registerUser(UserRegistrationRequestDTO request) {
        User user = modelMapper.map(request, User.class);
        userRepository.save(user);
        UserResponseDTO userResponse = modelMapper.map(user, UserResponseDTO.class);
        return userResponse;

    }
    public UserResponseDTO deleteUser(Long id){
        User foundUser = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Unable to find user by id :: " + id));
        UserResponseDTO userResponse = modelMapper.map(foundUser,UserResponseDTO.class);
        return userResponse;
    }
    public UserResponseDTO updateUser(Long id,UserRequestDTO request){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Unable to find user by id :: " + id));
        User updatedUser = modelMapper.map(request,User.class);
        updatedUser.setId(id);
        UserResponseDTO userResponse = modelMapper.map(updatedUser,UserResponseDTO.class);
        return userResponse;
    }


}
