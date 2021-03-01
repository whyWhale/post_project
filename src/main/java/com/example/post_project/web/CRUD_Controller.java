package com.example.post_project.web;

import com.example.post_project.Network.CRUD_IFS;
import com.example.post_project.config.auth.LoginUser;
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
    public ResponseEntity Create(@RequestBody Req req, @LoginUser loginUser loginUser) {
        return baseService.Create(req,loginUser);
    }

    @Override
    @PutMapping("")
    public Long Update(@RequestBody Req req,@LoginUser loginUser loginUser) {
        return baseService.Update(req,loginUser);
    }

    @Override
    @DeleteMapping("{id}")
    public Long Delete(Long id, @LoginUser loginUser loginUser) {
        return baseService.Delete(id,loginUser);
    }

    @Override
    public ResponseEntity<Res> Read(@LoginUser loginUser loginUser) {
        return baseService.Read(loginUser);
    }
}
