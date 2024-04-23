package com.example.userservice;

import com.example.userservice.entities.ERole;
import com.example.userservice.entities.PermissionsEntity;
import com.example.userservice.entities.RoleEntity;
import com.example.userservice.entities.UserEntity;
import com.example.userservice.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner init(UserRepository userRepository){
        return args -> {
            //crate permissions
            PermissionsEntity createPermissions = PermissionsEntity.builder()
                    .name("CREATE")
                    .build();
            PermissionsEntity readPermissions = PermissionsEntity.builder()
                    .name("READ")
                    .build();
            PermissionsEntity updatePermissions = PermissionsEntity.builder()
                    .name("UPDATE")
                    .build();
            PermissionsEntity deletePermissions = PermissionsEntity.builder()
                    .name("DELETE")
                    .build();
            PermissionsEntity refactorPermissions = PermissionsEntity.builder()
                    .name("REFACTOR")
                    .build();

            //create roles
            RoleEntity roleAdmin = RoleEntity.builder()
                    .roleName(ERole.ADMIN)
                    .permissions(Set.of(createPermissions, readPermissions, updatePermissions, deletePermissions))
                    .build();
            RoleEntity roleUser = RoleEntity.builder()
                    .roleName(ERole.USER)
                    .permissions(Set.of(createPermissions, readPermissions))
                    .build();
            RoleEntity roleInvited = RoleEntity.builder()
                    .roleName(ERole.INVITED)
                    .permissions(Set.of(readPermissions))
                    .build();
            RoleEntity roleDeveloper = RoleEntity.builder()
                    .roleName(ERole.DEVELOPER)
                    .permissions(Set.of(createPermissions, readPermissions, updatePermissions, deletePermissions, refactorPermissions))
                    .build();

            //create users
            UserEntity userEmilio = UserEntity.builder()
                    .name("44416945L")
                    .surname("colombo")
                    .email("emiliocolombo97@gmail.com")
                    .phone(2494671197L)
                    .bithdate(new Date(2024, 1, 1))
                    .password("$2a$10$/PjoSwfvtPs5AcyENLYtsO/wEDsihSxDqWmye52x6wdQD9IGk1b/e")
                    .isEnabled(true)
                    .accountNoExpired(true)
                    .accountNoLocked(true)
                    .creedentialsNoExpired(true)
                    .roles(Set.of(roleDeveloper))
                    .build();
            UserEntity userAlejandro = UserEntity.builder()
                    .name("44416944L")
                    .surname("colombo")
                    .email("emiliocolombo97@gmail.com")
                    .phone(2494671197L)
                    .bithdate(new Date(2024, 1, 1))
                    .password("$2a$10$/PjoSwfvtPs5AcyENLYtsO/wEDsihSxDqWmye52x6wdQD9IGk1b/e")
                    .isEnabled(true)
                    .accountNoExpired(true)
                    .accountNoLocked(true)
                    .creedentialsNoExpired(true)
                    .roles(Set.of(roleAdmin))
                    .build();
            UserEntity userClara = UserEntity.builder()
                    .name("44416943L")
                    .surname("colombo")
                    .email("emiliocolombo97@gmail.com")
                    .phone(2494671197L)
                    .bithdate(new Date(2024, 1, 1))
                    .password("$2a$10$/PjoSwfvtPs5AcyENLYtsO/wEDsihSxDqWmye52x6wdQD9IGk1b/e")
                    .isEnabled(true)
                    .accountNoExpired(true)
                    .accountNoLocked(true)
                    .creedentialsNoExpired(true)
                    .roles(Set.of(roleUser))
                    .build();
            UserEntity userFelipe = UserEntity.builder()
                    .name("44416942L")
                    .surname("colombo")
                    .email("emiliocolombo97@gmail.com")
                    .phone(2494671197L)
                    .bithdate(new Date(2024, 1, 1))
                    .password("$2a$10$/PjoSwfvtPs5AcyENLYtsO/wEDsihSxDqWmye52x6wdQD9IGk1b/e")
                    .isEnabled(true)
                    .accountNoExpired(true)
                    .accountNoLocked(true)
                    .creedentialsNoExpired(true)
                    .roles(Set.of(roleInvited))
                    .build();

            userRepository.saveAll(List.of(userFelipe, userEmilio, userAlejandro, userClara));
        };
    }

}
