package com.example.post_project.Network;

import org.springframework.http.ResponseEntity;

public interface CRUD_IFS <Req,Res,loginUser>{
    ResponseEntity Create(Req req,loginUser user) ;
    ResponseEntity<Res> Read(loginUser user);
    Long Update(Req req,loginUser user);
    Long Delete(Long id,loginUser user);

}
