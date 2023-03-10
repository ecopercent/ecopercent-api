package sudols.ecopercent.dto.item;

import lombok.*;
import sudols.ecopercent.domain.Item;

import java.util.Date;

@Builder
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class ItemPatchDetailDto {
    private String image;

    private String nickname;

    private String type;

    private String brand;

    private Integer price;

    private Date purchaseDate;

    public Item toEntity() {
        return Item.builder()
                .image(image)
                .nickname(nickname)
                .type(type)
                .brand(brand)
                .price(price)
                .purchaseDate(purchaseDate)
                .build();
    }
}
