package com.anyvision.facekeyexample.models.GetGroups;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementArray;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

@Root(strict = false)
public class ArrayOfGroupRow {

    @ElementList( entry = "GroupRow", inline = true, required = false)
    private List<GroupRow> group;

//    @ElementList(inline = true, required = false)
//    @Path("ArrayOfGroupRow")
//    private ArrayList<GroupRow> GroupRow;

//    public GroupRow getGroupRow ()
//    {
//        return GroupRow;
//    }

//    public String getGroupRow(){
//        return  group;
//    }

    public List<GroupRow> getGroup(){
        return  group;
    }

//    public ArrayList<GroupRow> getGroupRow ()
//    {
//        return group;
//    }

//    public GroupRow getGroup(){
//        return group;
//    }



}