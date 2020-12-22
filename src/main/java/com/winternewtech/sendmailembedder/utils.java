package com.winternewtech.sendmailembedder;

import org.springframework.data.annotation.Id;

class RegisterData {
    public String name;
    public String email;
    public String project;

}

class LoginData {
    public String id;
    public String project;
    public String email;
}

class Response {

    public String data;
    public Integer status;

    public Response(Integer status, String data) {
        this.status = status;
        this.data = data;
    }
}
