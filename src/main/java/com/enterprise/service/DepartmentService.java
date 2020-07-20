package com.enterprise.service;

import com.enterprise.entity.ResultMap;

public interface DepartmentService {

    ResultMap syncDeptAndUser(String token);
}
