package com.samoylov.gameproject.locations;

import java.util.ArrayList;

public class GroupList implements RowType {
    private String groupName;
    public GroupList(String groupName){
        this.groupName=groupName;
    }

    public String getGroupName() {
        return groupName;
    }
}
