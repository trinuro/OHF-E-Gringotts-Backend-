package com.example.egringotts.user;

public class Goblin extends AbstractUser{
    public final static String type = "goblin";

    @Override
    public String getType() {
        return type;
    }
    @Override
    public String toString() {
        return """
                Goblins{Administrator!}
                             ,      ,
                            /(.-""-.)\\
                        |\\  \\/      \\/  /|
                        | \\ / =.  .= \\ / |
                        \\( \\   o\\/o   / )/
                         \\_, '-/  \\-' ,_/
                           /   \\__/   \\
                           \\ \\__/\\__/ /
                         ___\\ \\|--|/ /___
                       /`    \\      /    `\\
                  jgs /       '----'       \\
                """;
    }
}
