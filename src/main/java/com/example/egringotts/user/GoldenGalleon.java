package com.example.egringotts.user;

public class GoldenGalleon extends AbstractUser {
    public final static String type = "goldenGalleon";

    @Override
    public String getType() {
        return type;
    }
    @Override
    public String toString(){
        return """
                Golden Galleon {I love gold but ain't no gold digger!}
                                           .-""-.
                                          (___/\\ \\
                        ,                 (|^ ^ ) )
                       /(                _)_\\=_/  (
                 ,..__/ `\\          ____(_/_ ` \\   )
                  `\\    _/        _/---._/(_)_  `\\ (
                jgs '--\\ `-.__..-'    /.    (_), |  )
                        `._        ___\\_____.'_| |__/
                           `~----"`   `-.........'
                """;
    }
}
