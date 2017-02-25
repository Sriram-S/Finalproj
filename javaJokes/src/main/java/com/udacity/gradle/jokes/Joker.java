package com.udacity.gradle.jokes;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Joker {

    int id;
    public ArrayList<String> jokes=new ArrayList();
    public Joker(){
        jokes.add("She: I have a doctor’s appointment today but I really don’t want to go… \n" +
                "-\n" +
                "He: Just call in sick then.\n");


        jokes.add("A recent scientific study showed that out of 2,293,618,367 people, 94% are too lazy to actually read that number." );

        jokes.add("Girl: You would be a good dancer except for two things. \n" +
                "Boy: What are the two things? \n" +
                "Girl: Your feet. ");

        jokes.add("Patient: Doctor! You've got to help me! Nobody ever listens to me. No one ever pays any attention to what I have to say.\n" +
                "Doctor: Next please! ");

        jokes.add("Mother: \"Did you enjoy your first day at school?\" \n" +
                "Girl: \"First day? Do you mean I have to go back tomorrow? ");

        jokes.add("Headmaster: I've had complaints about you, Johnny, from all your teachers. What have you been doing? \n" +
                "Johnny: Nothing, sir. \n" +
                "Headmaster: Exactly. ");

        jokes.add("Hey, man! Please call me a taxi. \n" +
                "Yes, sir. You are a taxi");

        jokes.add("\"I was born in California.\" \n" +
                "\"Which part?\" \n" +
                "\"All of me.\"");
    }


    public String getJokes() {
        Random ran = new Random();
        int x = ran.nextInt(jokes.size());
        return jokes.get(x).toString();
    }
}
