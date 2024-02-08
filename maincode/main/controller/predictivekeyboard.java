package maincode.main.controller;
import java.io.IOException;
import java.util.*;

public class predictivekeyboard {
    public List<String> lista;
    public void getSuggestions(String input) throws IOException 
    {
        SuggestionEngine obj1 = new SuggestionEngine();
        int numPredictions = 3;
        this.lista = obj1.getSuggestions(input,numPredictions);
    } 
}