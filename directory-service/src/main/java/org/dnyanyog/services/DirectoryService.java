package org.dnyanyog.services;

import org.dnyanyog.dto.DirectoryRequest;
import org.dnyanyog.dto.DirectoryResponse;

public interface DirectoryService {
  public DirectoryResponse addUser(DirectoryRequest request);

  public DirectoryResponse updateUser(Long userid, DirectoryRequest request);

  public DirectoryResponse getSingleUser(Long userid);

  public DirectoryResponse Deleteuser(Long userid);
}
