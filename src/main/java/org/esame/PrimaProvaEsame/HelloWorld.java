package org.esame.PrimaProvaEsame;
import static spark.Spark.*;

public class HelloWorld {
    public static void main(String[] args) {
        get("/ciao", (req, res) -> "Hello World");
    }
}
