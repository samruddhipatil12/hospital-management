package org.dnyanyog.controller;

import org.dnyanyog.dto.DirectoryRequest;
import org.dnyanyog.dto.DirectoryResponse;
import org.dnyanyog.services.DirectoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DirectoryController {

  @Autowired DirectoryServiceImp directoryService;

  @PostMapping(
      path = "/api/v1/directory/add",
      consumes = {"application/json", "application/xml"},
      produces = {"application/json", "application/xml"})
  public DirectoryResponse addUser(@RequestBody DirectoryRequest request) throws Exception {
    return directoryService.addUser(request);
  }

  @PostMapping(path = "/api/v1/directory/{userid}")
  public DirectoryResponse updateUser(
      @PathVariable long userid, @RequestBody DirectoryRequest request) {
    return directoryService.updateUser(userid, request);
  }

  @GetMapping(path = "/api/v1/directory/{userid}")
  public DirectoryResponse getSingleUser(@PathVariable long userid) {

    return directoryService.getSingleUser(userid);
  }

  @DeleteMapping(path = "/api/v1/directory/{userid}")
  public DirectoryResponse Deleteuser(@PathVariable long userid) {
    return directoryService.deleteUser(userid);
  }
}
