package com.example.egringotts.user;

public class SilverSnitch extends AbstractUser{
    private final static String type = "silverSnitch";

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String toString(){
        return """
                Silver Snitch {I am mid but ain't no snitch!}
                        ___
                     __|___|__
                      ('o_o')
                      _\\~-~/_    ______.
                     //\\__/\\ \\ ~(_]---'
                    / )O  O( .\\/_)
                    \\ \\    / \\_/
                    )/_|  |_\\
                   // /(\\/)\\ \\
                   /_/      \\_\\
                  (_||      ||_)
                    \\| |__| |/
                     | |  | |
                     | |  | |
                     |_|  |_|
                JRO  /_\\  /_\\
                """;
    }
}
