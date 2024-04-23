package com.example.userservice.services;


import com.example.userservice.entities.UserEntity;
import com.example.userservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = this.userRepository.findUserEntityByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("el usuario " + username + " no existe."));

        List<SimpleGrantedAuthority> authorityList =new ArrayList<>();

        //convertimos los roles del usuario a un simple granted authority
        userEntity.getRoles()
                .forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleName().name()))));

        //por cada unos de os roles, recorre los permison y convierte a cada uno de estos en un granted authority
        //y lo agrega a la lista de granted authorities :)
        userEntity.getRoles().stream()
                .flatMap(role -> role.getPermissions().stream())
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName())));

        return new User(userEntity.getId().toString(),
                userEntity.getPassword(),
                userEntity.isEnabled(),
                userEntity.isAccountNoExpired(),
                userEntity.isCreedentialsNoExpired(),
                userEntity.isAccountNoLocked(),
                authorityList
        );
    }
}