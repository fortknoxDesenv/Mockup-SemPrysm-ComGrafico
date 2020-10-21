package com.anyvision.facekeyexample.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(strict = false)
public class PreviousValueTeste {

    @Element(name = "PreviousValue", required = false)
    private String PreviousValue;


}
