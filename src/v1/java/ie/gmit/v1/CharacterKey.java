package ie.gmit.v1;

import java.util.Optional;

public interface  CharacterKey {

    char get(Position position);
    Optional<Position> get(char ch);

}
