package com.anyvision.facekeyexample.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(strict = false)
public class ValueTeste {

    @Element(name = "Value", required = false)
    private String Value;


}
