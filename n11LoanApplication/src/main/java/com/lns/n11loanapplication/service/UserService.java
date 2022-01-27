package com.lns.n11loanapplication.service;

import com.lns.n11loanapplication.api.errorHandling.exception.BusinessException;
import com.lns.n11loanapplication.converter.UserConverter;
import com.lns.n11loanapplication.dao.UserDao;
import com.lns.n11loanapplication.data.dto.UserCreditDto;
import com.lns.n11loanapplication.data.dto.UserDto;
import com.lns.n11loanapplication.data.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {
@Autowired
    UserDao userDao;

    public List<UserDto> findAll()
    {
        List<User> userList =userDao.findAll();
        return UserConverter.INSTANCE.userListConvertToUserDtoList(userList);
    }

    public UserDto findByUserTckn(Long tckn)
    {
        User user =userDao.findByUserTckn(tckn);
        if(user==null)
        {
            throw new BusinessException("user.id-not-exists", tckn);
        }
        return UserConverter.INSTANCE.userConvertToUserDto(user);


    }

    public UserDto save (UserDto userDto)
    {
        User user = UserConverter.INSTANCE.userDtoConvertToUser(userDto);
        user = userDao.save(user);
        return UserConverter.INSTANCE.userConvertToUserDto(user);
    }

    public void delete (Long id)
    {
        userDao.deleteById(id);
    }

    public UserCreditDto findUserForCreditByTckn(Long tckn)
    {
        UserDto userDto = findByUserTckn(tckn);
        return UserConverter.INSTANCE.userDtoConvertToUserCreditDto(userDto);
    }
    public void deleteAll()
    {
        userDao.deleteAll();
    }
}
