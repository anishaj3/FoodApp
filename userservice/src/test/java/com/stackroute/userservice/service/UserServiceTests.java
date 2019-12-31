package com.stackroute.userservice.service;

import com.stackroute.userservice.entity.User;
import com.stackroute.userservice.exception.UserAlreadyExsitsException;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.repo.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@ContextConfiguration
public class UserServiceTests {

    @Mock
    private transient UserRepository repo;

    private transient User user;

    @InjectMocks
    private transient UserServiceImpl service;

    transient Optional<User> options;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        user = new User("jon", "John", "Doe", "password", new Date());
        options = Optional.of(user);
    }

    @Test
    public void testSaveUserSuccess() throws UserAlreadyExsitsException, UserNotFoundException {
        when(repo.save(user)).thenReturn(user);
        final boolean flag = service.saveUser(user);
        assertTrue("Cannot save user", flag);
        verify(repo,times(1)).save(user);
        verify(repo,times(1)).findById(user.getUserId());
    }

    @Test(expected = UserAlreadyExsitsException.class)
    public void testSaveFoodFailure() throws UserAlreadyExsitsException, UserNotFoundException {
        when(repo.findById(user.getUserId())).thenReturn(options);
        when(repo.save(user)).thenReturn(user);
        final boolean flag = service.saveUser(user);
        assertFalse("Saving food failed",flag);
        verify(repo,times(1)).findById(user.getUserId());
    }

    @Test
    public void testLoginSuccess() throws UserNotFoundException {
        when(repo.findByUserIdAndPassword(user.getUserId(), user.getPassword())).thenReturn(user);
        final User u = service.findByUserIdAndPassword(user.getUserId(), user.getPassword());
        assertNotNull(u);
        assertTrue("Cannot login  user", u.getUserId().equals(user.getUserId()));
        verify(repo,times(1)).findByUserIdAndPassword(user.getUserId(), user.getPassword());
    }


    @Test(expected = UserNotFoundException.class)
    public void testLoginFailure() throws UserNotFoundException {
        when(repo.findByUserIdAndPassword(user.getUserId(), user.getPassword())).thenReturn(null);
        final User u = service.findByUserIdAndPassword(user.getUserId(), user.getPassword());
        assertNotNull(u);
        assertTrue("Cannot login  user", u.getUserId().equals(user.getUserId()));
        verify(repo,times(1)).findByUserIdAndPassword(user.getUserId(), user.getPassword());
    }

}
