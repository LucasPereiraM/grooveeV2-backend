package com.groovee.api.domain.userList;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ListItemUpdateDTO {
    private String entityType;
    private Long entityId;
}
