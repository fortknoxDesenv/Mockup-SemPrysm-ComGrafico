package com.anyvision.facekeyexample.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(strict = false)
public class ChamadoGrafico {

    @Element(name = "Name", required = false)
    @Path("VariableState[1]")
    private String name1;

    @Namespace(reference = "http://www.w3.org/2001/XMLSchema")
    @Element(name = "Value", required = false)
    @Path("VariableState[1]")
    private int value1;

    @Element(name = "Name", required = false)
    @Path("VariableState[2]")
    private String name2;

    @Namespace(reference = "http://www.w3.org/2001/XMLSchema")
    @Element(name = "Value", required = false)
    @Path("VariableState[2]")
    private int value2;

    @Element(name = "Name", required = false)
    @Path("VariableState[3]")
    private String name3;

    @Namespace(reference = "http://www.w3.org/2001/XMLSchema")
    @Element(name = "Value", required = false)
    @Path("VariableState[3]")
    private int value3;

    @Element(name = "Name", required = false)
    @Path("VariableState[4]")
    private String name4;

    @Namespace(reference = "http://www.w3.org/2001/XMLSchema")
    @Element(name = "Value", required = false)
    @Path("VariableState[4]")
    private int value4;

    @Element(name = "Name", required = false)
    @Path("VariableState[5]")
    private String name5;

    @Namespace(reference = "http://www.w3.org/2001/XMLSchema")
    @Element(name = "Value", required = false)
    @Path("VariableState[5]")
    private int value5;

    //Recupera as solicitações que as agencias solicitaram
    public ArrayList<String> GetListaGestaoControleSala() {
        ArrayList<String> listaGestaoCtr = new ArrayList<String>();

        if (name1 != null) listaGestaoCtr.add(name1 + ";" + value1);
        if (name2 != null) listaGestaoCtr.add(name2 + ";" + value2);
        if (name3 != null) listaGestaoCtr.add(name3 + ";" + value3);
        if (name4 != null) listaGestaoCtr.add(name4 + ";" + value4);
        if (name5 != null) listaGestaoCtr.add(name5 + ";" + value5);


        return listaGestaoCtr;
    }

    //Pega a soma dos valores para ser apresentado no grafico
    public int GetPorcentagemTotalGestaoControleSala() {
        int soma = 0;
        soma = value1 + value2 + value3;
        return soma;
    }
}
