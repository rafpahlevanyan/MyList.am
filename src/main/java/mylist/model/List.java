package mylist.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class List {

    private int id;
    private String title;
    private double price;
    private String description;
    private String picUrl;
    private User user;
    private Category category;

}
