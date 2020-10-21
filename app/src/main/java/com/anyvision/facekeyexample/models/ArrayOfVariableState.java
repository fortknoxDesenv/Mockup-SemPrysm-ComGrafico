package com.anyvision.facekeyexample.models;

import org.simpleframework.xml.ElementArray;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root(strict = false)
public class ArrayOfVariableState {

    @Namespace(reference = "http://www.w3.org/2001/XMLSchema")
    @ElementArray(name = "VariableState", required = false)
    private com.anyvision.facekeyexample.models.VariableState[] VariableState;

public com.anyvision.facekeyexample.models.VariableState[] getVariableStateTeste(){
    return VariableState;
}
}
