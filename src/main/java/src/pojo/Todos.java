package src.pojo;

import lombok.Data;

@Data
public class Todos {
    private int userId;
    private int id;
    private String title;
    private boolean completed;
}
