package com.groovee.api.repositories;

import com.groovee.api.domain.userList.UserList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ListRepository extends JpaRepository <UserList, UUID> {
}
