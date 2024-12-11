package com.example.inventario.service;

import com.example.inventario.dto.UserMapper;
import com.example.inventario.dto.request.UserDto;
import com.example.inventario.dto.response.UserDtoGet;
import com.example.inventario.entity.User;
import com.example.inventario.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto save(UserDto userDto) {
        User user = userMapper.toUser(userDto);
        return userMapper.toUserDto(userRepository.save(user));
    }

    @Override
    public Optional<UserDtoGet> findById(Long id) {
        return userRepository.findById(id).map(userMapper::toUserDtoGet);
    }

    @Override
    public Optional<UserDtoGet> findByUsername(String username) {
        return userRepository.findByUsername(username).map(userMapper::toUserDtoGet);
    }

    @Override
    public Optional<UserDtoGet> findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password).map(userMapper::toUserDtoGet);
    }

    @Override
    public Optional<UserDto> update(Long id, UserDto userDto) {
        return userRepository.findById(id).map(oldUser -> {
            oldUser.setName(userDto.name());
            oldUser.setUsername(userDto.username());
            oldUser.setPassword(userDto.password());
            oldUser.setAddress(userDto.address());
            oldUser.setPhone(userDto.phone());
           return userMapper.toUserDto(userRepository.save(oldUser));
        });
    }

    @Override
    public List<UserDtoGet> getAllUsers() {
        List<User> users = userRepository.findAll();
        return toListUserDtoGet(users);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    private List<UserDtoGet> toListUserDtoGet(List<User> users) {
        List<UserDtoGet> userDtosGets = new ArrayList<>();
        for (User user : users) {
            userDtosGets.add(userMapper.toUserDtoGet(user));
        }
        return userDtosGets;
    }
}
