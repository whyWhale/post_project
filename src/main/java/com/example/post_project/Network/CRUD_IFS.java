package com.example.post_project.Network;

import org.springframework.http.ResponseEntity;

public interface CRUD_IFS <Req,Res,loginUser>{
    ResponseEntity<Res> Create(Req req,loginUser user) ;
    Long update(Req req,loginUser user);
    Long delete(Long id,loginUser user);

}
