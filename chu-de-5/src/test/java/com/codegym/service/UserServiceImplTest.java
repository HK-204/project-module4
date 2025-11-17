package com.codegym.service;

import com.codegym.entity.User;
import com.codegym.repository.UserRepository;
import com.codegym.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private BCryptPasswordEncoder mockPasswordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testSaveUser_ShouldEncodePassword_AndSaveUser() {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("raw123");

        when(mockPasswordEncoder.encode(anyString())).thenReturn("da_ma_hoa");
        userService.save(user);

        verify(mockPasswordEncoder, times(1)).encode("raw123");

        verify(mockUserRepository, times(1)).save(user);

        assertEquals("da_ma_hoa", user.getPassword());
    }
}