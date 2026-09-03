package io.github.novig4j.model.markets;

import java.util.Optional;

public record Player(String id, Optional<String> name, Optional<String> fullName, Optional<String> position)  {
}
