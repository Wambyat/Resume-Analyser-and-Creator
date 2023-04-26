package com.example.demo;
import java.io.IOException;
import java.util.List;
import com.example.demo.predictivekeyboard;

public class tester
{
	
    public static void main(String args[]) throws IOException
    {
    	predictivekeyboard keyboard = new predictivekeyboard();
        List<String> suggestions = keyboard.getSuggestions();
        for (String suggestion : suggestions) {
            System.out.println(suggestion);
        }
    }
}