package com.example.post_project.web;

import com.example.post_project.Network.CRUD_IFS;
import com.example.post_project.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
public abstract class CRUD_Controller<Req,Res,loginUser,Entity> implements CRUD_IFS<Req,Res,loginUser> {

    @Autowired(required = false)
    protected BaseService<Req, Res, loginUser, Entity> baseService;

    @Override
    @PostMapping("")
    public ResponseEntity<Res> Create(Req req, loginUser loginUser) {
        return baseService.Create(req,loginUser);
    }

    @Override
    @PutMapping("")
    public Long update(Req req, loginUser loginUser) {
        return baseService.update(req,loginUser);
    }

    @Override
    @DeleteMapping("{id}")
    public Long delete(Long id, loginUser loginUser) {
        return baseService.delete(id,loginUser);
    }
}
