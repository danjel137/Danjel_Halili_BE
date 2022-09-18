package person_from_db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@Data
@ToString
public class Person {
    private int id;
    private String name;
    private String surname;
    private String email;

    public Person() {
    }
}
