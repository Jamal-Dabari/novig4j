package model.markets;

import java.util.Optional;

public class Player {
    private final String id;
    private final Optional<String> name;
    private final Optional<String> fullName;
    private final Optional<String> postition;


    public Player(String id, Optional<String> name, Optional<String> fullName, Optional<String> postition) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.postition = postition;
    }
}
