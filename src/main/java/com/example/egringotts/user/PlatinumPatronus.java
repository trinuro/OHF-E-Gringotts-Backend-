package com.example.egringotts.user;

public class PlatinumPatronus extends AbstractUser{
    @Override
    public String toString() {
        return """
                Platinum Patronus (I am the top dog!)
                                      ____
                                     / ___`\\
                         /|         ( (   \\ \\
                    |^v^v  V|        \\ \\/) ) )
                    \\  ____ /         \\_/ / /
                    ,Y`    `,            / /
                    ||  -  -)           { }
                    \\\\   _\\ |           | |
                     \\\\ / _`\\_         / /
                     / |  ~ | ``\\     _|_|
                  ,-`  \\    |  \\ \\  ,//(_}
                 /      |   |   | \\/  \\| |
                |       |   |   | '   ,\\ \\
                |     | \\   /  /\\  _/`  | |
                \\     |  | |   | ``     | |
                 |    \\  \\ |   |        | |
                 |    |   |/   |        / /
                 |    |        |        | |
                """;
    }

    @Override
    public String getType() {
        return "platinumPatronus";
    }
}
