package com.kuang.demo01;

public class Client {
    public static void main(String[] args) {
        // 房东要出租房子
        Host host = new Host();
        // 代理，中介帮房东出租房子，但是代理角色一般会有一些附属操作
        Rent rent = new Proxy(host);
        // 你不用找房东，直接找中介即可
        rent.rent();
    }
}
