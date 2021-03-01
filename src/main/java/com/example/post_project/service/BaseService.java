package com.example.post_project.service;


import com.example.post_project.Network.CRUD_IFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public abstract class BaseService <Req,Res,loginUser,Entity> implements CRUD_IFS<Req,Res,loginUser> {
    @Autowired(required = false)
    protected JpaRepository<Entity,Long> baseRepository;


}
