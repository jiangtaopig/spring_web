package com.example.demo.dozer;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.junit.Test;

public class TestDozer {

    @Test
    public void test1() {

        UserDTO userDTO = new UserDTO();
        userDTO.setAge(23);
//        userDTO.setName("pig");
        userDTO.setSex("male");

        System.out.println("userDTO = " + userDTO);

        Mapper mapper = new DozerBeanMapper();

        UserEntity userEntity = new UserEntity();
        userEntity.setId(111);
        mapper.map(userDTO, userEntity);
        System.out.println("userEntity = " + userEntity);


    }
}
