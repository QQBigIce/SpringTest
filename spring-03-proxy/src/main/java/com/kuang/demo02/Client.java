package com.kuang.demo02;

public class Client {
    public static void main(String[] args) {
        UserService userServiceImpl = new UserServiceImpl();
        UserServiceProxy userService = new UserServiceProxy();
        userService.setUserService(userServiceImpl);
        userService.add();
        userService.delete();
        userService.update();
        userService.query();
    }
}
