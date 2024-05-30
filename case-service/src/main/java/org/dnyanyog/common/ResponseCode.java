package org.dnyanyog.common;

public enum ResponseCode {
  ADD_CASE("Success", "Case Added Successfully!!"),
  ADD_CASE_FAILED("Fail", "Unable to add case."),
  UPDATE_CASE("Success", "Case updated successfully!!"),
  UPDATE_CASE_FAILED("Fail", "Unable to update case."),
  SEARCH_CASE("Success", "Case are found!!"),
  SEARCH_CASE_FAILED("Fail", "No case found."),
  DELETE_CASE("Success", "Case deleted successfully!!"),
  DELETE_CASE_FAILED("Fail", "Failed to delete the case");

  private final String status;
  private final String message;

  ResponseCode(String status, String message) {
    this.status = status;
    this.message = message;
  }

  public String getStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }
}
