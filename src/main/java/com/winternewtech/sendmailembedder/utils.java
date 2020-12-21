package com.winternewtech.sendmailembedder;

class RegisterData {
    public String name;
    public String email;
    public String project;

}

class Response {

    public String data;
    public Integer status;

    public Response(Integer status, String data) {
        this.status = status;
        this.data = data;
    }
}
