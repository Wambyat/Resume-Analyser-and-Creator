package com.example.demo;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.Loader;


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