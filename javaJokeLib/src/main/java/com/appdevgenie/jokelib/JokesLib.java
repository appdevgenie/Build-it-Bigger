package com.appdevgenie.jokelib;

import java.util.Random;

public class JokesLib {

    private String jokes[] = {
            "Maybe if we start telling people the brain is an app they will start using it.",
            "My email password has been hacked. That's the third time I've had to rename the cat.",
            "My mom said that if I don't get off my computer and do my homework she'll slam my head on the keyboard, but I think she's jokinfjreoiwjrtwe4to8rkljreun8f4ny84c8y4t58lym4wthylmhawt4mylt4amlathnatyn",
            "A computer once beat me at chess, but it was no match for me at kick boxing.",
            "You must be an angel, because your texture mapping is so divine!",
            "Why do Java developers wear glasses? Because they cant C#.",
            "A clean house is the sign of a broken computer.",
            "I changed my password to \"incorrect\". So whenever I forget what it is the computer will say \"Your password is incorrect\".",
            "A TV can insult your intelligence, but nothing rubs it in like a computer.",
            "What did E.T.'s mother say to him when he got home? \"Where on Earth have you been?!\"",
            "If Bill Gates had a penny for every time I had to reboot my computer ...oh wait, he does.",
            "I love the F5 key. It\'s just so refreshing.",
            "I named my hard drive \"dat ass\" so once a month my computer asks if I want to 'back dat ass up'.",
            "I'm changing my name to 'Benefits' on Facebook. Next time someone adds me, It will say \"you are now friends with Benefits.\""
    };

    public String getJoke(){
        return jokes[new Random().nextInt(jokes.length)];
    }
}
