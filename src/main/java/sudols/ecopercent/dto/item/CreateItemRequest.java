package sudols.ecopercent.dto.item;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateItemRequest {

    // TODO: image 자료형 뭐로 해야하는지?
    private String image;

    private String nickname;

    private String category;

    private String type;

    private String brand;

    private Integer price;

    private LocalDate purchaseDate;

}
